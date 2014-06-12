package dip.modules.stream;

import java.io.InputStream;

public abstract class InputStreamFactory {
	public abstract InputStream getStream();
	public void finish(InputStream stream) {} // what to do after a call to getStream
}
