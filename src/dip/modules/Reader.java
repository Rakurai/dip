package dip.modules;

public interface Reader<INPUT, OUTPUT> {
	public OUTPUT read(INPUT inputVector) throws Exception;
	public INPUT acquireInputVector();
	public void releaseInputVector(INPUT inputVector);
}
