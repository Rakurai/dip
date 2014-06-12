package dip.modules.converters;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import dip.modules.AbstractModule;
import dip.core.RunState;

public abstract class AbstractConverter<INPUT, OUTPUT> extends AbstractModule implements Converter {
	private BlockingQueue<INPUT> input;
	private BlockingQueue<OUTPUT> output;

	protected AbstractConverter(BlockingQueue<INPUT> input, BlockingQueue<OUTPUT> output) {
		this.input = input;
		this.output = output;
	}

	protected abstract OUTPUT convert(INPUT obj) throws Exception;
	
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

				OUTPUT out = convert(in);
				output.put(out);
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
