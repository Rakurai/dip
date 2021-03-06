package dip;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.mongodb.DBObject;
import com.mongodb.DBCollection;

import dip.core.Core;
import dip.core.RunnableAnalysisTool;
import dip.core.RunnableConverter;
import dip.core.RunnableReader;
import dip.core.RunnableWriter;
import dip.modules.Converter;
import dip.modules.Reader;
import dip.modules.Writer;
import dip.modules.mongo.MongoCollectionFactory;
import dip.modules.mongo.MongoWriter;
import dip.modules.analysis.QueueAnalysisTool;
import dip.modules.debugging.SinkWriter;
import dip.modules.mongo.JSONStringtoMongoDBObjectConverter;
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
			core.addProcessor(new RunnableConverter<String, DBObject>(inputQueue, outputQueue, converter));

			MongoCollectionFactory factory = new MongoCollectionFactory("thecave.cs.clemson.edu", 27017);
			Writer<DBObject, DBCollection> writer = new MongoWriter(factory.getCollection("twitter", "feed"));
			core.addWriter(new RunnableWriter<DBObject, DBCollection>(outputQueue, writer));
			
			QueueAnalysisTool tool = new QueueAnalysisTool();
			tool.addQueue(inputQueue);
			tool.addQueue(outputQueue);			
			core.addAnalysisTool(new RunnableAnalysisTool(tool));

			core.start();

			factory.cleanup();
			System.out.println("Stopped.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
