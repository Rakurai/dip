package dip.modules.mongo;

import com.mongodb.DBObject;

import dip.modules.CompositeConverter;
import dip.modules.json.JSONObjectToStringConverter;
import dip.modules.json.XMLStringToJSONObjectConverter;

public class XMLStringToMongoDBObjectConverter extends CompositeConverter<String, DBObject> {
	public XMLStringToMongoDBObjectConverter() throws Exception {
		addConverter(new XMLStringToJSONObjectConverter());
		addConverter(new JSONObjectToStringConverter());
		addConverter(new JSONStringtoMongoDBObjectConverter());
	}
}
