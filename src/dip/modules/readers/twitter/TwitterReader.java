package dip.modules.readers.twitter;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import com.twitter.hbc.twitter4j.Twitter4jStatusClient;

import dip.modules.readers.AbstractReader;
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

public class TwitterReader extends AbstractReader<String> {
	
	public TwitterReader(BlockingQueue<String> q) {
		super(q);
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		
		
		StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
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
        
        
     // Create an appropriately sized blocking queue
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);

        // Define our endpoint: By default, delimited=length is set (we need this for our processor)
        // and stall warnings are on.
        StatusesSampleEndpoint endpoint = new StatusesSampleEndpoint();

        //PREPARE
        //FOR
        //THE WORST
        //PROGRAMMING PRACTICES
        //EVAAAAAAAR
        
        String consumerKey="5KkuMvsSvxGxLtzVI6k1Q";
        String consumerSecret="zjCdxMKJwjJ19nMuETLqBi3G30tFWvIxrJfYr06zk";
        String token="2319463477-mgOb9OGJDc8GoBYRYK0SgPUinHYhFJCkCMwkMZV";
        String secret="tmvPqp6KTbKRpzkmduiE4hbrITHeWh96jK8dnkUQPk13O";
        
        
        Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
        // Authentication auth = new BasicAuth(username, password);

        // Create a new BasicClient. By default gzip is enabled.
        BasicClient client = new ClientBuilder()
          .hosts(Constants.STREAM_HOST)
          .endpoint(endpoint)
          .authentication(auth)
          .processor(new StringDelimitedProcessor(queue))
          .build();

        // Create an executor service which will spawn threads to do the actual work of parsing the incoming messages and
        // calling the listeners on each message
        int numProcessingThreads = 4;
        ExecutorService service = Executors.newFixedThreadPool(numProcessingThreads);

        // Wrap our BasicClient with the twitter4j client
        ArrayList<StatusListener> listeners = new ArrayList<StatusListener>();
        listeners.add(listener);
        Twitter4jStatusClient t4jClient = new Twitter4jStatusClient(
          client, queue, listeners, service);

        // Establish a connection
        t4jClient.connect();
        for (int threads = 0; threads < numProcessingThreads; threads++) {
          // This must be called once per processing thread
          t4jClient.process();
        }
	}
	
    public void deprecated_run() {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
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

}
