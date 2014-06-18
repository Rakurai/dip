package dip.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import dip.modules.Converter;
import dip.modules.Module;
import dip.core.RunState.State;

public class RunnableConverter<INPUT, OUTPUT> extends AbstractRunnableModule {
	private BlockingQueue<INPUT> input;
	private BlockingQueue<OUTPUT> output;
	private Converter<INPUT, OUTPUT> converter;

	public RunnableConverter(BlockingQueue<INPUT> input, BlockingQueue<OUTPUT> output, Converter<INPUT, OUTPUT> converter) {
		this.input = input;
		this.output = output;
		this.converter = converter;
	}

	@Override
	public void run() {
		try {
			while (runState.get() != State.STOP) {
				INPUT in = input.poll(1, TimeUnit.SECONDS);

				if (in == null) {
					if (runState.get() == State.FINISH)
						break;
					else
						continue;
				}

				Metadata metadata = core.getRegistry().deregister(in);
				OUTPUT out = converter.convert(in, metadata);
				
				if (out != null) {
					core.getRegistry().register(out, metadata);
					output.put(out);
					incrementCounter();
				}
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
		return (Module)converter;
	}
}
