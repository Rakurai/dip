package dip.modules.converters.text;

import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;

import dip.modules.converters.AbstractConverter;

public class KeyValueSplitter extends AbstractConverter<String, Entry<String, String>> {

	private String delimiter;

	public KeyValueSplitter(BlockingQueue<String> input, BlockingQueue<Entry<String, String>> output, String delimiter) {
		super(input, output);
		this.delimiter = delimiter;
	}

	@Override
	protected Entry<String, String> convert(String str) throws Exception {
		String[] pair = str.split(delimiter, 2);
		return new AbstractMap.SimpleEntry<String, String>(pair[0], pair[1]);
	}
}
