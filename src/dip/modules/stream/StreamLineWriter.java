package dip.modules.stream;

import java.io.DataOutputStream;
import java.util.concurrent.BlockingQueue;

public class StreamLineWriter extends AbstractStreamWriter<String> {

	public StreamLineWriter(BlockingQueue<String> q, OutputStreamFactory factory) {
		super(q, factory);
	}

	@Override
	protected void write(String str) throws Exception {
		DataOutputStream stream = new DataOutputStream(factory.getStream());
		stream.writeUTF(str + "\n");
	}
}
