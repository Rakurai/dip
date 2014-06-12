package dip.modules;

import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractModule;
import dip.core.RunState;

public abstract class AbstractReader<INPUT> extends AbstractModule implements Reader {
	protected BlockingQueue<INPUT> q;

	public AbstractReader(BlockingQueue<INPUT> q) {
		this.q = q;
	}

	protected abstract INPUT read() throws Exception;

	@Override
	public void run() {
		try {
			while (runState == RunState.RUN) {
				INPUT obj = read();
				if (obj == null)
					break;
				q.put(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
