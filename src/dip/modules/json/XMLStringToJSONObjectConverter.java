package dip.modules.json;

import json.JSONObject;
import json.XML;
import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class XMLStringToJSONObjectConverter extends AbstractConverter<String, JSONObject> {

	@Override
	public JSONObject convert(String in, Metadata metadata) throws Exception {
		return XML.toJSONObject(in);
	}
}
