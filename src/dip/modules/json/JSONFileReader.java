package dip.modules.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dip.core.Metadata;
import dip.modules.AbstractReader;

public class JSONFileReader extends AbstractReader<File, JsonNode> {

	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public JsonNode read(File inputVector, Metadata metadata) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(inputVector, JsonNode.class);
	}
}
