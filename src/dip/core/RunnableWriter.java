package dip.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import dip.core.RunState.State;
import dip.modules.Module;
import dip.modules.Writer;

public class RunnableWriter<INPUT, OUTPUT> extends AbstractRunnableModule {
	private BlockingQueue<INPUT> input;
	private Writer<INPUT, OUTPUT> writer;

	public RunnableWriter(BlockingQueue<INPUT> input, Writer<INPUT, OUTPUT> writer) {
		this.input = input;
		this.writer = writer;
	}
	
	@Override
	public void run() {
		try {
			while (runState.get() != State.STOP) {
				INPUT obj = input.poll(1, TimeUnit.SECONDS);

				if (obj == null) {
					if (runState.get() == State.FINISH)
						break;
					else
						continue;
				}

				Metadata metadata = core.getRegistry().deregister(obj);
				OUTPUT outputVector = writer.acquireOutputVector(metadata);
				writer.write(obj, outputVector, metadata);
				writer.releaseOutputVector(outputVector);
				incrementCounter();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
		return (Module) writer;
	}
}
