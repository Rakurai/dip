package dip.modules;

import java.util.concurrent.BlockingQueue;

public class SinkWriter extends AbstractWriter<Object> {

	public SinkWriter(BlockingQueue<Object> queue) {
		super(queue);
	}

	@Override
	protected void write(Object obj) throws Exception {
		// do nothing
	}
}
