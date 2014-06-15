package dip.modules;

public abstract class AbstractWriter<INPUT, OUTPUT> implements Writer<INPUT, OUTPUT> {
	protected IOMapper<OUTPUT> outputMapper = null;

	public AbstractWriter(IOMapper<OUTPUT> mapper) {
		outputMapper = mapper;
	}
	
	public AbstractWriter(final OUTPUT outputVector) {
		this(new IOMapper<OUTPUT>() {
			@Override
			public OUTPUT acquire() {
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
	public OUTPUT acquireOutputVector() {
		return outputMapper.acquire();
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
