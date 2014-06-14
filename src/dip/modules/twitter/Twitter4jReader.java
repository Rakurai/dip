package dip.modules.twitter;

import java.util.concurrent.BlockingQueue;
import dip.modules.AbstractReader;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStreamFactory;
//import twitter4j.*;
import twitter4j.TwitterStream;
//import twitter4j.auth.AccessToken;
//import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterObjectFactory;

public class Twitter4jReader extends AbstractReader<String> {
	
	public Twitter4jReader(BlockingQueue<String> q) {
		super(q);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void run() {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
//                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                //System.out.println(status.getUser().getName() + " : " + status.getText());
                String rawjson = TwitterObjectFactory.getRawJSON(status);
                try {
                        q.put(rawjson);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

	@Override
	protected String read() {
		// TODO Auto-generated method stub
		return null;
	}

}
