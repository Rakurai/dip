package dip.modules.stream;

import java.io.BufferedWriter;
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
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
		writer.write(str);
	}

}
