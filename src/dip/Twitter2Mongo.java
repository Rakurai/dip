package dip;

import dip.queues.Queue;
import dip.queues.BlockingQueue;
import dip.modules.writers.mongo.MongoWriter;
import dip.modules.readers.twitter.TwitterReader;

public class Twitter2Mongo {


	public static void main(String[] args) {
		Queue queue = new BlockingQueue();
		Core core = new Core(queue);

		try {
			core.addReader(new TwitterReader(queue));

			MongoWriter writer = new MongoWriter(queue);
			writer.init("thecave.cs.clemson.edu", 27017, "twitter", "feed");
			core.addWriter(writer);

			core.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
