package dip.core;

import java.util.concurrent.BlockingQueue;

import dip.core.RunState.State;
import dip.modules.Module;
import dip.modules.Reader;

public class RunnableReader<INPUT, OUTPUT> extends AbstractRunnableModule {
	private BlockingQueue<OUTPUT> outputQueue;
	private Reader<INPUT, OUTPUT> reader;
	
	public RunnableReader(BlockingQueue<OUTPUT> outputQueue, Reader<INPUT, OUTPUT> reader) {
		this.outputQueue = outputQueue;
		this.reader = reader;
	}

	@Override
	public void run() {
		try {
			while (runState.get() != State.STOP) {
				Metadata metadata = core.getRegistry().newMetadata();
				OUTPUT obj = read(metadata);

				if (obj == null) {
					if (runState.get() == State.FINISH)
						break;
					else
						continue;
				}

				queue(obj, metadata);
				incrementCounter();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected OUTPUT read(Metadata metadata) throws Exception {
		OUTPUT obj = null;
		INPUT inputVector = reader.acquireInputVector(metadata);
		if (inputVector != null) {
			obj = reader.read(inputVector, metadata);
			reader.releaseInputVector(inputVector);
		}
		return obj;
	}

	protected void queue(OUTPUT obj, Metadata metadata) throws Exception {
		core.getRegistry().register(obj, metadata);
		outputQueue.put(obj);
	}
	
	private static int counter = 0;

	protected synchronized static void incrementCounter() {
		counter++;
	}
	
	public static int getCounter() {
		return counter;
	}

	@Override
	public Module getModule() {
		return (Module) reader;
	}
}
