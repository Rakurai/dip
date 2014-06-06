package dip.modules.readers;

import java.util.concurrent.BlockingQueue;

public abstract class AbstractReader<INPUT> implements Runnable, Reader {
	protected BlockingQueue<INPUT> q;

	public AbstractReader(BlockingQueue<INPUT> q) {
		this.q = q;
	}

	protected abstract INPUT read();

	@Override
	public void run() {
		while (true) {
			try {
				q.put(read());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
