package dip.modules.writers;

import java.util.concurrent.BlockingQueue;

public abstract class AbstractWriter<OUTPUT> implements Runnable, Writer {
	protected BlockingQueue<OUTPUT> q;

	public AbstractWriter(BlockingQueue<OUTPUT> q) {
		this.q = q;
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
