package dip.modules.stream;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractWriter;

public class StreamWriter extends AbstractWriter<String> {

	private DataOutputStream stream;

	public StreamWriter(BlockingQueue<String> queue, OutputStream stream) {
		super(queue);
		this.stream = new DataOutputStream(stream);
	}

	@Override
	protected void write(String str) throws Exception {
		stream.writeUTF(str);
	}
}
