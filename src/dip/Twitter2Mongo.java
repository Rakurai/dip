package dip;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.mongodb.DBObject;
import com.mongodb.DBCollection;

import dip.core.Core;
import dip.core.RunnableConverter;
import dip.core.RunnableReader;
import dip.core.RunnableWriter;
import dip.modules.Converter;
import dip.modules.Reader;
import dip.modules.Writer;
import dip.modules.managers.StatusManager;
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
			Reader<Object, String> reader = new Twitter4jReader();
			core.addReader(new RunnableReader<Object, String>(inputQueue, reader));

			Converter<String, DBObject> converter = new JSONStringtoMongoDBObjectConverter();
			core.addConverter(new RunnableConverter<String, DBObject>(inputQueue, outputQueue, converter));

			MongoCollectionFactory factory = new MongoCollectionFactory("thecave.cs.clemson.edu", 27017);
			Writer<DBObject, DBCollection> writer = new MongoWriter(factory.getCollection("twitter", "feed")) {
				int n = 1;
				@Override
				public void write(DBObject obj, DBCollection collection) {
					super.write(obj, collection);
					System.out.println("writting feed " + n++);
				}
			};
			core.addWriter(new RunnableWriter<DBObject, DBCollection>(outputQueue, writer));
			
			StatusManager manager = new StatusManager();
			manager.addQueue(inputQueue);
			manager.addQueue(outputQueue);
			
			core.addManager(manager);
			core.start();

			factory.cleanup();
			System.out.println("Stopped.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
