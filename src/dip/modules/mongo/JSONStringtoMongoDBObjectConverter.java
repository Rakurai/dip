package dip.modules.mongo;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class JSONStringtoMongoDBObjectConverter extends AbstractConverter<String, DBObject> {

	@Override
	public DBObject convert(String json, Metadata metadata) {
		DBObject bson = (DBObject) JSON.parse(json);
		return bson;
	}

}
