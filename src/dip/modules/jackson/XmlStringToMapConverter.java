package dip.modules.jackson;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

@SuppressWarnings("rawtypes")
public class XmlStringToMapConverter extends AbstractConverter<String, Map> {
	private XmlMapper mapper = new XmlMapper();
	XMLInputFactory factory = XMLInputFactory.newInstance();
	
	@Override
	public Map convert(String in, Metadata metadata) throws Exception {
		// TODO Auto-generated method stub
		XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(in));
		return mapper.readValue(reader, HashMap.class);
	}

}
