package dip.modules.stream;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractWriter;

public class StreamWriter extends AbstractWriter<String> {

	private BufferedWriter writer;

	public StreamWriter(BlockingQueue<String> queue, OutputStream stream) {
		super(queue);
		this.writer = new BufferedWriter(new OutputStreamWriter(stream));
	}

	@Override
	protected void write(String str) throws Exception {
		writer.write(str);
	}
	
	@Override
	public void cleanup() {
		try {
			writer.flush(); // stream to be closed outside
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
