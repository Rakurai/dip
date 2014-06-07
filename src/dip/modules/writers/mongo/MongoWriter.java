package dip.modules.writers.mongo;

import java.util.concurrent.BlockingQueue;

import dip.modules.writers.AbstractWriter;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoWriter extends AbstractWriter<DBObject> {
	private static MongoClient client = null;
	private DB db;
	private DBCollection collection;
	
	public MongoWriter(BlockingQueue<DBObject> q) {
		super(q);
	}
	
	public synchronized void init(String host, int port, String db_name, String coll_name) throws Exception {
		if (client == null)
			this.client = new MongoClient(host, port);

		db = client.getDB(db_name);
		collection = db.getCollection(coll_name);
	}

	@Override
	public void write(DBObject obj) {
		while (true) {
			collection.insert(obj);
		}
	}

	@Override
	public synchronized void cleanup() {
		super.cleanup();

		if (client != null) {
			client.close();
			client = null;
		}
	}
}
