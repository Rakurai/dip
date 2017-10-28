package dip.modules.map;

import java.util.Map;

import dip.core.Metadata;
import dip.modules.AbstractWriter;

public class ReduceWriter<KEY, VALUE> extends AbstractWriter<Map<KEY, VALUE>, Map<KEY, VALUE>> {

	public ReduceWriter(Map<KEY, VALUE> map) {
		super(map);
	}

	@Override
	public void write(Map<KEY, VALUE> input, Map<KEY, VALUE> outputVector, Metadata metadata) throws Exception {
		outputVector.putAll(input);
	}

}
