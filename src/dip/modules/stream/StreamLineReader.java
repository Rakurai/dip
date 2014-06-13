package dip.modules.stream;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractReader;

public class StreamLineReader extends AbstractReader<String> {

	private BufferedReader reader;

	public StreamLineReader(BlockingQueue<String> queue, InputStream stream) {
		super(queue);
		this.reader = new BufferedReader(new InputStreamReader(stream));
	}

	@Override
	protected String read() throws Exception {
		return reader.readLine();
	}

}
