package dip.modules.stream;

import java.io.OutputStream;

public abstract class OutputStreamFactory {
	public abstract OutputStream getStream();
	public void finish(OutputStream stream) {} // what to do after a call to getStream, such as close it
}
