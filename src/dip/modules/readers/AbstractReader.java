package dip.modules.readers;

import java.util.concurrent.BlockingQueue;

import dip.modules.Module;

public abstract class AbstractReader<INPUT> implements Runnable, Module {
	protected BlockingQueue<INPUT> q;

	public AbstractReader(BlockingQueue<INPUT> q) {
		this.q = q;
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
