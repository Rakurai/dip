package dip.modules.stream;

import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractWriter;

public abstract class AbstractStreamWriter<INPUT> extends AbstractWriter<INPUT> {

	protected OutputStreamFactory factory;

	public AbstractStreamWriter(BlockingQueue<INPUT> q, OutputStreamFactory factory) {
		super(q);
		this.factory = factory;
	}
}
