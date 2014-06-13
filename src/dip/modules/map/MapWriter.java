package dip.modules.map;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractMultiWriter;
import dip.modules.IOMapper;

public class MapWriter<KEY, VALUE> extends AbstractMultiWriter<VALUE, KEY> {

	private Map<KEY, VALUE> map;

	public MapWriter(BlockingQueue<VALUE> queue, IOMapper<KEY> outputMapper, Map<KEY, VALUE> map) {
		super(queue, outputMapper);
		this.map = map;
	}

	@Override
	protected void write(VALUE value, KEY key) throws Exception {
		map.put(key, value);
	}
}
