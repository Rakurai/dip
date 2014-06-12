package dip.modules.converters.mongo;

import java.util.concurrent.BlockingQueue;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import dip.modules.converters.AbstractConverter;

public class JSONStringtoMongoDBObjectConverter extends AbstractConverter<String, DBObject> {

	public JSONStringtoMongoDBObjectConverter(BlockingQueue<String> input, BlockingQueue<DBObject> output) {
		super(input, output);
	}

	@Override
	protected DBObject convert(String json) {
		DBObject bson = (DBObject) JSON.parse(json);
		return bson;
	}

}
