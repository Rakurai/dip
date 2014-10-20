package dip;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;

import json.XML;
import dip.core.MapWrapper;
import dip.modules.CompositeConverter;
import dip.modules.CompositeConverter;
import dip.modules.Converter;
import dip.modules.jackson.XmlStringToMapConverter;
import dip.modules.json.JSONObjectToStringConverter;
import dip.modules.json.XMLStringToJSONObjectConverter;
import dip.modules.mongo.JSONStringtoMongoDBObjectConverter;
import dip.modules.mongo.XMLStringToMongoDBObjectConverter;

public class Test {

	public static void main(String[] args) throws Exception {
//		Converter<String, Map> c = new XmlStringToMapConverter();
		System.out.println(System.getProperty("sun.arch.data.model"));
		String json = "{\"this\":\"is\",\"a\":{\"little\":\"test\"}}";
		String xml = "<this>is</this><a><little>test</little></a>";

//		FileInputStream fos = new FileInputStream("/Users/jason/bmw/structures");
		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/jason/bmw/structures")));
		BufferedInputStream stream = new BufferedInputStream(new FileInputStream("/Users/jason/bmw/structures"));
		String last_line = null;
		long m = 0;
		char c = 'a';

		Map<MapWrapper, String> map = new HashMap<MapWrapper, String>();
		StringBuilder sb = new StringBuilder();
		sb.append("test1");
		String t1 = sb.toString();
		sb = new StringBuilder("test1");
		String t2 = sb.toString();
		map.put(new MapWrapper(t1), json);
		map.put(new MapWrapper(t2), json);
		System.out.println(map.size());
		System.out.println(map.containsKey(new MapWrapper(t1)));
		System.out.println(map.containsKey(t2));
		/*		while(true) {
			String line = reader.();
			if (line == null)
				break;
			m += line.length();
			last_line = line;
		}
*/				
	}

}
