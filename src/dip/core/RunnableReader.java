package dip.core;

import java.util.concurrent.BlockingQueue;

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
			while (runState == RunState.RUN) {
				Metadata metadata = core.getRegistry().newMetadata();
				OUTPUT obj = read(metadata);

				if (obj == null)
					break;

				queue(obj, metadata);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected OUTPUT read(Metadata metadata) throws Exception {
		INPUT inputVector = reader.acquireInputVector(metadata);
		OUTPUT obj = reader.read(inputVector, metadata);
		reader.releaseInputVector(inputVector);
		return obj;
	}

	protected void queue(OUTPUT obj, Metadata metadata) throws InterruptedException {
		core.getRegistry().register(obj, metadata);
		outputQueue.put(obj);
	}
	
	@Override
	public Module getModule() {
		return (Module) reader;
	}
}
