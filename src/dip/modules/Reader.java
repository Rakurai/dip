package dip.modules;

import dip.core.Metadata;

public interface Reader<INPUT, OUTPUT> {
	public OUTPUT read(INPUT inputVector, Metadata metadata) throws Exception;
	public INPUT acquireInputVector(Metadata metadata);
	public void releaseInputVector(INPUT inputVector);
}
