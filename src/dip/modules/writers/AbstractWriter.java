package dip.modules.writers;

import java.util.concurrent.BlockingQueue;

import dip.modules.Module;

public abstract class AbstractWriter<OUTPUT> implements Runnable, Module {
	protected BlockingQueue<OUTPUT> q;

	public AbstractWriter(BlockingQueue<OUTPUT> q) {
		this.q = q;
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
