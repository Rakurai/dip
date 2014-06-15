package dip.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

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
			while (runState != RunState.STOP) {
				INPUT obj = input.poll(1, TimeUnit.SECONDS);

				if (obj == null) {
					if (runState == RunState.FINISH)
						break;
					else
						continue;
				}

				core.getRegistry().deregister(obj);
				OUTPUT outputVector = writer.acquireOutputVector();
				writer.write(obj, outputVector);
				writer.releaseOutputVector(outputVector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Module getModule() {
		return writer;
	}
}
