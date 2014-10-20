package dip.modules;

import dip.core.Metadata;

public abstract class AbstractReader<INPUT, OUTPUT> extends AbstractModule implements Reader<INPUT, OUTPUT> {
	protected IOMapper<INPUT> inputMapper = null;

	public AbstractReader(IOMapper<INPUT> mapper) {
		inputMapper = mapper;
	}
	
	public AbstractReader(final INPUT inputVector) {
		this(new IOMapper<INPUT>() {
			@Override
			public INPUT acquire(Metadata metadata) {
				return inputVector;
			}

			@Override
			public void release(INPUT val) {
				// do nothing
			}

			@Override
			public void cleanup() throws Exception {
				// do nothing
			}
		});
	}

	public AbstractReader() {
		this((INPUT)null);
	}

	@Override
	public INPUT acquireInputVector(Metadata metadata) {
		return inputMapper.acquire(metadata);
	}

	@Override
	public void releaseInputVector(INPUT inputVector) {
		inputMapper.release(inputVector);
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
