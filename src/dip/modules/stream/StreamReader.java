package dip.modules.stream;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractReader;

public class StreamReader extends AbstractReader<String> {

	private DataInputStream stream;

	public StreamReader(BlockingQueue<String> queue, InputStream stream) {
		super(queue);
		this.stream = new DataInputStream(stream);
	}

	@Override
	protected String read() throws Exception {
		return stream.readUTF();
	}

}
