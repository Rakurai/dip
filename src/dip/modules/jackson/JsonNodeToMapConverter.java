package dip.modules.jackson;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

@SuppressWarnings("rawtypes")
public class JsonNodeToMapConverter extends AbstractConverter<JsonNode, Map> {

	ObjectMapper mapper = new ObjectMapper();

	@Override
	public Map convert(JsonNode in, Metadata metadata)
			throws Exception {
		return mapper.treeToValue(in, HashMap.class);
	}

}
