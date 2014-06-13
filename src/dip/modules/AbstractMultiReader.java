package dip.modules;

import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractModule;
import dip.core.RunState;

public abstract class AbstractMultiReader<INPUT, OUTPUT> extends AbstractModule implements Reader {
	protected BlockingQueue<OUTPUT> queue;
	protected IOMapper<INPUT> mapper;
	
	public AbstractMultiReader(BlockingQueue<OUTPUT> queue, IOMapper<INPUT> mapper) {
		this.queue = queue;
		this.mapper = mapper;
	}

	protected abstract OUTPUT read(INPUT in) throws Exception;

	@Override
	public void run() {
		try {
			while (runState == RunState.RUN) {
				OUTPUT obj = read(mapper.get());
				if (obj == null)
					break;
				core.register(obj);
				queue.put(obj);
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
