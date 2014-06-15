package dip.modules.map;

import java.util.Map;

import dip.modules.AbstractWriter;
import dip.modules.IOMapper;

public class MapWriter<KEY, VALUE> extends AbstractWriter<VALUE, KEY> {

	private Map<KEY, VALUE> map;

	public MapWriter(IOMapper<KEY> outputMapper, Map<KEY, VALUE> map) {
		super(outputMapper);
		this.map = map;
	}

	@Override
	public void write(VALUE value, KEY key) throws Exception {
		map.put(key, value);
	}
}
