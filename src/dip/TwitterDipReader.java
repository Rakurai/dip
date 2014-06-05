package dip;

import java.io.*;

import twitter4j.*;
//import twitter4j.auth.AccessToken;
//import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterObjectFactory;

public class TwitterDipReader extends DipReader {
	
	public TwitterDipReader(DipQueue q) {
		super(q);
		// TODO Auto-generated constructor stub
	}
	
    public void run() throws TwitterException {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                //System.out.println(status.getUser().getName() + " : " + status.getText());
                String rawjson = TwitterObjectFactory.getRawJSON(status);
                try {
                        q.post(rawjson);
                } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Failed to post tweet to queue.");
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        twitterStream.sample();
    }

}
