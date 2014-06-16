package dip.modules;

import dip.core.Metadata;

public interface Writer<INPUT, OUTPUT> {
	public void write(INPUT input, OUTPUT outputVector, Metadata metadata) throws Exception;
	public OUTPUT acquireOutputVector(Metadata metadata);
	public void releaseOutputVector(OUTPUT outputVector);
}
