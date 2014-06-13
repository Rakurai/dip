package dip.modules;

import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractModule;
import dip.core.RunState;

public abstract class AbstractReader<OUTPUT> extends AbstractModule implements Reader {
	protected BlockingQueue<OUTPUT> q;

	public AbstractReader(BlockingQueue<OUTPUT> q) {
		this.q = q;
	}

	protected abstract OUTPUT read() throws Exception;

	@Override
	public void run() {
		try {
			while (runState == RunState.RUN) {
				OUTPUT obj = read();
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
