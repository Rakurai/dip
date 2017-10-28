package dip.core;

import java.util.concurrent.BlockingQueue;

public abstract class AbstractRunnableProcessor<INPUT, OUTPUT> extends AbstractRunnableModule {
	protected BlockingQueue<INPUT> input;
	protected BlockingQueue<OUTPUT> output;

	public AbstractRunnableProcessor(BlockingQueue<INPUT> input, BlockingQueue<OUTPUT> output) {
		this.input = input;
		this.output = output;
	}
	
}
