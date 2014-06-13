package dip.modules.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoCollectionFactory {
	private MongoClient client;

	public MongoCollectionFactory(String host, int port) throws UnknownHostException {
		this.client = new MongoClient(host, port);
	}
	
	public DBCollection getCollection(String db_name, String collection_name) {
		DB db = client.getDB(db_name);
		return db.getCollection(collection_name);
	}
	
	public void cleanup() {
		this.client.close();
	}
}
