package dip.modules.readers;

import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractModule;
import dip.modules.RunState;

public abstract class AbstractReader<INPUT> extends AbstractModule implements Reader {
	protected BlockingQueue<INPUT> q;

	public AbstractReader(BlockingQueue<INPUT> q) {
		this.q = q;
	}

	protected abstract INPUT read() throws Exception;

	@Override
	public void run() {
		try {
			while (runState == RunState.RUN)
				q.put(read());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
