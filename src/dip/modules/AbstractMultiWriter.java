package dip.modules;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import dip.modules.AbstractModule;
import dip.core.RunState;

public abstract class AbstractMultiWriter<INPUT, OUTPUT> extends AbstractModule implements Writer {
	protected BlockingQueue<INPUT> queue;
	protected IOMapper<OUTPUT> outputMapper = null;

	public AbstractMultiWriter(BlockingQueue<INPUT> queue, IOMapper<OUTPUT> mapper) {
		this.queue = queue;
		outputMapper = mapper;
	}

	protected abstract void write(INPUT obj, OUTPUT outvector) throws Exception;

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

				core.getRegistry().deregister(obj);
				OUTPUT output = outputMapper.acquire();
				write(obj, output);
				outputMapper.release(output);
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
