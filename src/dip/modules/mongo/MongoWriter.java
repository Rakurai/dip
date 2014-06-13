package dip.modules.mongo;

import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractWriter;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MongoWriter extends AbstractWriter<DBObject> {
	private DBCollection collection;
	
	public MongoWriter(BlockingQueue<DBObject> queue, DBCollection collection) {
		super(queue);
		this.collection = collection;
	}
	
	@Override
	public void write(DBObject obj) {
		while (true) {
			collection.insert(obj);
		}
	}
}
