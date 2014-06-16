package dip.modules.converters.text;

import java.util.AbstractMap;
import java.util.Map.Entry;

import dip.core.Metadata;
import dip.modules.AbstractConverter;

public class KeyValueSplitter extends AbstractConverter<String, Entry<String, String>> {

	private String delimiter;

	public KeyValueSplitter(String delimiter) {
		this.delimiter = delimiter;
	}

	@Override
	public Entry<String, String> convert(String str, Metadata metadata) throws Exception {
		String[] pair = str.split(delimiter, 2);
		return new AbstractMap.SimpleEntry<String, String>(pair[0], pair[1]);
	}
}
