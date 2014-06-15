package dip.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import dip.modules.Converter;
import dip.modules.Module;
import dip.core.RunState;

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
			while (runState != RunState.STOP) {
				INPUT in = input.poll(1, TimeUnit.SECONDS);

				if (in == null) {
					if (runState == RunState.FINISH)
						break;
					else
						continue;
				}

				OUTPUT out = converter.convert(in);
				core.getRegistry().update(in, out);
				output.put(out);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Module getModule() {
		return converter;
	}
}
