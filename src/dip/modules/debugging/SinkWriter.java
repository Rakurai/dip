package dip.modules.debugging;

import dip.core.Metadata;
import dip.modules.AbstractWriter;
import dip.modules.Writer;

public class SinkWriter extends AbstractWriter<Object, Object> {

	public SinkWriter() {
		super(null);
	}
	
	@Override
	public void write(Object obj, Object outvector, Metadata metadata) throws Exception {
		// do nothing
	}
}
