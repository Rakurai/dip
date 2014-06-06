package dip.modules.converters;

import java.util.concurrent.BlockingQueue;

import dip.modules.Module;

public abstract class AbstractConverter<INPUT, OUTPUT> implements Module {
	private BlockingQueue<INPUT> input;
	private BlockingQueue<OUTPUT> output;

	protected AbstractConverter(BlockingQueue<INPUT> input, BlockingQueue<OUTPUT> output) {
		this.input = input;
		this.output = output;
	}

	protected abstract OUTPUT convert(INPUT obj) throws Exception;
	
	@Override
	public void run() {
		while (true) {
			try {
				INPUT in = input.take();
				OUTPUT out = convert(in);
				output.put(out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
