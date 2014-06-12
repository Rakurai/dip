package dip.modules;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import dip.modules.AbstractModule;
import dip.core.RunState;

public abstract class AbstractWriter<OUTPUT> extends AbstractModule implements Writer {
	protected BlockingQueue<OUTPUT> q;

	public AbstractWriter(BlockingQueue<OUTPUT> q) {
		this.q = q;
	}

	protected abstract void write(OUTPUT obj) throws Exception;

	@Override
	public void run() {
		try {
			while (runState != RunState.STOP) {
				OUTPUT out = q.poll(1, TimeUnit.SECONDS);

				if (out == null) {
					if (runState == RunState.FINISH)
						break;
					else
						continue;
				}

				write(out);
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
