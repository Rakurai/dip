package dip.modules.writers;

import dip.modules.Module;
import dip.queues.Queue;

public abstract class Writer implements Runnable, Module {
	protected Queue q;

	public Writer(Queue q) {
		this.q = q;
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
