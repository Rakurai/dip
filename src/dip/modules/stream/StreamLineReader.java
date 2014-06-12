package dip.modules.stream;

import java.io.DataInputStream;
import java.util.concurrent.BlockingQueue;

public class StreamLineReader extends AbstractStreamReader<String> {

	public StreamLineReader(BlockingQueue<String> q, InputStreamFactory factory) {
		super(q, factory);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected String read() throws Exception {
		DataInputStream stream = new DataInputStream(factory.getStream());
		return stream.readLine();
	}

}
