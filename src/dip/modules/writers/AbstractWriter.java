package dip.modules.writers;

import java.util.concurrent.BlockingQueue;

public abstract class AbstractWriter<OUTPUT> implements Runnable, Writer {
	protected BlockingQueue<OUTPUT> q;

	public AbstractWriter(BlockingQueue<OUTPUT> q) {
		this.q = q;
	}

	protected abstract void write(OUTPUT obj) throws Exception;

	@Override
	public void run() {
		while (true) {
			try {
				write(q.take());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
