package dip.core;

import java.util.concurrent.BlockingQueue;

import dip.modules.Module;
import dip.modules.Reader;

public class RunnableReader<INPUT, OUTPUT> extends AbstractRunnableModule {
	private BlockingQueue<OUTPUT> output;
	private Reader<INPUT, OUTPUT> reader;
	
	public RunnableReader(BlockingQueue<OUTPUT> output, Reader<INPUT, OUTPUT> reader) {
		this.output = output;
		this.reader = reader;
	}

	@Override
	public void run() {
		try {
			while (runState == RunState.RUN) {
				INPUT inputVector = reader.acquireInputVector();
				OUTPUT obj = reader.read(inputVector);
				reader.releaseInputVector(inputVector);

				if (obj == null)
					break;
				
				core.getRegistry().register(obj);
				output.put(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Module getModule() {
		return reader;
	}
}
