package dip.modules.mongo;

import com.mongodb.DBObject;

import dip.modules.AbstractCompositeConverter;
import dip.modules.json.JSONObjectToStringConverter;
import dip.modules.json.XMLStringToJSONObjectConverter;

public class XMLStringToMongoDBObjectConverter extends AbstractCompositeConverter<String, DBObject> {
	public XMLStringToMongoDBObjectConverter() {
		addConverter(new XMLStringToJSONObjectConverter());
		addConverter(new JSONObjectToStringConverter());
		addConverter(new JSONStringtoMongoDBObjectConverter());
	}
}
