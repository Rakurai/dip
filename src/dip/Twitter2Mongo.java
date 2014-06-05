package dip;

import modules.writers.mongo.MongoDipWriter;
import modules.writers.twitter.TwitterDipReader;

public class Twitter2Mongo {


	public static void main(String[] args) {
		try {
			DipQueue q = new DipQueueImpl();
	
			TwitterDipReader reader = new TwitterDipReader(q);
			Thread readerThread = new Thread(reader);
			readerThread.run();
			
			MongoDipWriter writer = new MongoDipWriter(q);
			writer.init("thecave.cs.clemson.edu", 27017, "twitter", "feed");
			Thread writerThread = new Thread(writer);
			writerThread.run();
	
			readerThread.join();
			writerThread.join();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
