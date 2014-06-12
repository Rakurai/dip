package dip.modules.stream;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractMultiWriter;
import dip.modules.IOMapper;

public class MultiStreamWriter extends AbstractMultiWriter<String, OutputStream> {

	public MultiStreamWriter(BlockingQueue<String> queue, IOMapper<OutputStream> mapper) {
		super(queue, mapper);
	}

	@Override
	protected void write(String str, OutputStream stream) throws Exception {
		DataOutputStream dstream = new DataOutputStream(stream);
		dstream.writeUTF(str);
	}

}
