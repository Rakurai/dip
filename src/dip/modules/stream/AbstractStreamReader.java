package dip.modules.stream;

import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractReader;

public abstract class AbstractStreamReader<INPUT> extends AbstractReader<INPUT> {

	protected InputStreamFactory factory;

	public AbstractStreamReader(BlockingQueue<INPUT> q, InputStreamFactory factory) {
		super(q);
		this.factory = factory;
	}

}
