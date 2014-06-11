package dip;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.bson.BSONObject;

import com.mongodb.DBObject;

import json.JSONObject;
import dip.core.Core;
import dip.modules.writers.mongo.MongoWriter;
import dip.modules.converters.AbstractConverter;
import dip.modules.converters.JSONStringtoMongoDBObjectConverter;
import dip.modules.readers.twitter.Twitter4jReader;

public class Twitter2Mongo {


	public static void main(String[] args) {
		BlockingQueue<String> inputQueue = new ArrayBlockingQueue<String>(100);
		BlockingQueue<DBObject> outputQueue = new ArrayBlockingQueue<DBObject>(100);
		Core core = new Core();

		try {
			core.addReader(new Twitter4jReader(inputQueue));

			MongoWriter writer = new MongoWriter(outputQueue);
			writer.init("thecave.cs.clemson.edu", 27017, "twitter", "feed");
			core.addWriter(writer);

			core.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
