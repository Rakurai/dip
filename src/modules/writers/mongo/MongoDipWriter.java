package modules.writers.mongo;

import modules.writers.DipWriter;
import json.JSONObject;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import dip.DipQueue;

public class MongoDipWriter extends DipWriter {
	private static MongoClient client = null;
	private DB db;
	private DBCollection collection;
	
	public MongoDipWriter(DipQueue q) {
		super(q);
	}
	
	public synchronized void init(String host, int port, String db_name, String coll_name) throws Exception {
		if (client == null)
			this.client = new MongoClient(host, port);

		db = client.getDB(db_name);
		collection = db.getCollection(coll_name);
	}

	@Override
	public synchronized void cleanup() {
		super.cleanup();

		if (client != null) {
			client.close();
			client = null;
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				JSONObject json = q.pull();
				DBObject bson = (DBObject) JSON.parse(json.toString());
				collection.insert(bson);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
