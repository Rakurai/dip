package dip.core;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import dip.core.RunState.State;
import dip.modules.Module;

public class OutputManifold<T> extends AbstractRunnableProcessor<List<T>, T> {

	public OutputManifold(BlockingQueue<List<T>> input, BlockingQueue<T> output) {
		super(input, output);
	}
	
	@Override
	public Module getModule() {
		return null;
	}

	@Override
	public void run() {
		try {
			while (runState.get() != State.STOP) {
				List<T> in = input.poll(1, TimeUnit.SECONDS);

				if (in == null) {
					if (runState.get() == State.FINISH)
						break;
					else
						continue;
				}

				Metadata metadata = core.getRegistry().deregister(in);
				
				for (T o: in) {
					core.getRegistry().register(o, metadata);
					output.put(o);
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
