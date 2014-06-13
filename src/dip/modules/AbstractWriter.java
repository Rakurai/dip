package dip.modules;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import dip.modules.AbstractModule;
import dip.core.RunState;

public abstract class AbstractWriter<INPUT> extends AbstractModule implements Writer {
	protected BlockingQueue<INPUT> queue;

	public AbstractWriter(BlockingQueue<INPUT> queue) {
		this.queue = queue;
	}

	protected abstract void write(INPUT obj) throws Exception;

	@Override
	public void run() {
		try {
			while (runState != RunState.STOP) {
				INPUT obj = queue.poll(1, TimeUnit.SECONDS);

				if (obj == null) {
					if (runState == RunState.FINISH)
						break;
					else
						continue;
				}

				core.deregister(obj);
				write(obj);
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
