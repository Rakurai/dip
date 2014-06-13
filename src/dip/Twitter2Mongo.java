package dip;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.mongodb.DBObject;

import dip.core.Core;
import dip.modules.mongo.MongoCollectionFactory;
import dip.modules.mongo.MongoWriter;
import dip.modules.converters.mongo.JSONStringtoMongoDBObjectConverter;
import dip.modules.twitter.Twitter4jReader;

public class Twitter2Mongo {


	public static void main(String[] args) {
		BlockingQueue<String> inputQueue = new ArrayBlockingQueue<String>(100);
		BlockingQueue<DBObject> outputQueue = new ArrayBlockingQueue<DBObject>(100);
		Core core = new Core();

		try {
			core.addReader(new Twitter4jReader(inputQueue));
			
			core.addConverter(new JSONStringtoMongoDBObjectConverter(inputQueue, outputQueue));

			MongoCollectionFactory factory = new MongoCollectionFactory("thecave.cs.clemson.edu", 27017);
			core.addWriter(new MongoWriter(outputQueue, factory.getCollection("twitter", "feed")));

			core.start();

			factory.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
