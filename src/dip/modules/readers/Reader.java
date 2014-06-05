package dip.modules.readers;

import dip.modules.Module;
import dip.queues.Queue;

public abstract class Reader implements Runnable, Module {
	protected Queue q;

	public Reader(Queue q) {
		this.q = q;
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
