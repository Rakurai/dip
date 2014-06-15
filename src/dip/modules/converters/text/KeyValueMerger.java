package dip.modules.converters.text;

import java.util.Map.Entry;

import dip.modules.AbstractConverter;

public class KeyValueMerger extends
		AbstractConverter<Entry<String, String>, String> {

	private String delimiter;

	protected KeyValueMerger(String delimiter) {
		this.delimiter = delimiter;
	}

	@Override
	public String convert(Entry<String, String> entry) throws Exception {
		return entry.getKey() + delimiter + entry.getValue();
	}
}
