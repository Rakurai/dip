package dip;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import json.JSONObject;
import dip.modules.AbstractConverter;
import dip.modules.writers.mongo.MongoWriter;
import dip.modules.readers.twitter.TwitterReader;

public class Twitter2Mongo {


	public static void main(String[] args) {
		BlockingQueue<JSONObject> inputQueue = new ArrayBlockingQueue<JSONObject>(100);
		BlockingQueue<JSONObject> outputQueue = new ArrayBlockingQueue<JSONObject>(100);
		Core core = new Core();

		try {
			core.addReader(new TwitterReader(inputQueue));

			MongoWriter writer = new MongoWriter(outputQueue);
			writer.init("thecave.cs.clemson.edu", 27017, "twitter", "feed");
			core.addWriter(writer);

			core.addConverter(new AbstractConverter<JSONObject, JSONObject>(inputQueue, outputQueue) {
				@Override
				protected JSONObject convert(JSONObject obj) {
					return obj;
				}
			});
			
			core.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
