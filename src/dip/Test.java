package dip;

import java.io.IOException;
import java.util.Map;

import com.mongodb.DBObject;

import json.XML;
import dip.modules.CompositeConverter;
import dip.modules.CompositeConverter;
import dip.modules.Converter;
import dip.modules.jackson.XmlStringToMapConverter;
import dip.modules.json.JSONObjectToStringConverter;
import dip.modules.json.XMLStringToJSONObjectConverter;
import dip.modules.mongo.JSONStringtoMongoDBObjectConverter;
import dip.modules.mongo.XMLStringToMongoDBObjectConverter;

public class Test {

	public static void main(String[] args) {
//		Converter<String, Map> c = new XmlStringToMapConverter();
		
		String json = "{\"this\":\"is\",\"a\":{\"little\":\"test\"}}";
		String xml = "<this>is</this><a><little>test</little></a>";

		CompositeConverter<String, DBObject> c;
		try {
			c = new XMLStringToMongoDBObjectConverter();
			System.out.println(c.convert(xml, null));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
