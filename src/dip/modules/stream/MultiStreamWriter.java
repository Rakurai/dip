package dip.modules.stream;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractMultiWriter;
import dip.modules.IOMapper;

public class MultiStreamWriter extends AbstractMultiWriter<String, OutputStream> {

	public MultiStreamWriter(BlockingQueue<String> queue, IOMapper<OutputStream> mapper) {
		super(queue, mapper);
	}

	@Override
	protected void write(String str, OutputStream stream) throws Exception {
		// note: do not wrap this in a BufferedWriter, because the stream could be closed
		// after this call completes (or it might not).  Without a buffer all bytes are
		// written immediately.
		OutputStreamWriter writer = new OutputStreamWriter(stream);
		writer.write(str);
	}
}
