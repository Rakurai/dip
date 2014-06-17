package dip.modules.json;

import json.JSONObject;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class JSONObjectToStringConverter extends AbstractConverter<JSONObject, String> {
	@Override
	public String convert(JSONObject obj, Metadata metadata) throws Exception {
		return obj.toString();
	}
}
