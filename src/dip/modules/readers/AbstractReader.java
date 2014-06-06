package dip.modules.readers;

import java.util.concurrent.BlockingQueue;

public abstract class AbstractReader<INPUT> implements Runnable, Reader {
	protected BlockingQueue<INPUT> q;

	public AbstractReader(BlockingQueue<INPUT> q) {
		this.q = q;
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
