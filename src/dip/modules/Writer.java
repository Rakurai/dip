package dip.modules;

public interface Writer<INPUT, OUTPUT> {
	public void write(INPUT input, OUTPUT outputVector) throws Exception;
	public OUTPUT acquireOutputVector();
	public void releaseOutputVector(OUTPUT outputVector);
}
