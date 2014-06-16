package dip.modules.mongo;

import dip.core.Metadata;
import dip.modules.AbstractWriter;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MongoWriter extends AbstractWriter<DBObject, DBCollection> {
	public MongoWriter(DBCollection collection) {
		super(collection);
	}
	
	@Override
	public void write(DBObject obj, DBCollection collection, Metadata metadata) {
		while (true) {
			collection.insert(obj);
		}
	}
}
