package dip.modules;

import dip.core.Metadata;

public abstract class AbstractWriter<INPUT, OUTPUT> extends AbstractModule implements Writer<INPUT, OUTPUT> {
	protected IOMapper<OUTPUT> outputMapper = null;

	public AbstractWriter(IOMapper<OUTPUT> mapper) {
		outputMapper = mapper;
	}
	
	public AbstractWriter(final OUTPUT outputVector) {
		this(new IOMapper<OUTPUT>() {
			@Override
			public OUTPUT acquire(Metadata metadata) {
				return outputVector;
			}

			@Override
			public void release(OUTPUT val) {
				// do nothing
			}
		});
	}

	public AbstractWriter() {
		this((OUTPUT)null);
	}
	
	@Override
	public OUTPUT acquireOutputVector(Metadata metadata) {
		return outputMapper.acquire(metadata);
	}

	@Override
	public void releaseOutputVector(OUTPUT outputVector) {
		outputMapper.release(outputVector);
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
