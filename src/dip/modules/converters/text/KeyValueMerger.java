package dip.modules.converters.text;

import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;

import dip.modules.converters.AbstractConverter;

public class KeyValueMerger extends
		AbstractConverter<Entry<String, String>, String> {

	private String delimiter;

	protected KeyValueMerger(BlockingQueue<Entry<String, String>> input, BlockingQueue<String> output, String delimiter) {
		super(input, output);
		this.delimiter = delimiter;
	}

	@Override
	protected String convert(Entry<String, String> entry) throws Exception {
		return entry.getKey() + delimiter + entry.getValue();
	}
}
