package dip;

import java.io.IOException;
import java.util.Map;

import json.XML;
import dip.modules.Converter;
import dip.modules.jackson.XmlStringToMapConverter;

public class Test {

	public static void main(String[] args) {
//		Converter<String, Map> c = new XmlStringToMapConverter();
		
		String json = "{\"this\":\"is\",\"a\":{\"little\":\"test\"}}";
		String xml = "<this>is</this><a><little>test</little></a>";

		System.out.println(XML.toJSONObject(xml).toString());
		
	}

}
