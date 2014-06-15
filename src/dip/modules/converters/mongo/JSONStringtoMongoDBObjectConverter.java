package dip.modules.converters.mongo;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import dip.modules.AbstractConverter;

public class JSONStringtoMongoDBObjectConverter extends AbstractConverter<String, DBObject> {

	@Override
	public DBObject convert(String json) {
		DBObject bson = (DBObject) JSON.parse(json);
		return bson;
	}

}
