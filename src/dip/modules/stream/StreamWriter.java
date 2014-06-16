package dip.modules.stream;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

import dip.core.Metadata;
import dip.modules.AbstractWriter;
import dip.modules.IOMapper;

public class StreamWriter extends AbstractWriter<String, OutputStreamWriter> {

	public StreamWriter(OutputStream stream) {
		super(new OutputStreamWriter(stream));
	}

	public StreamWriter(OutputStreamWriter writer) {
		super(writer);
	}

	public StreamWriter(IOMapper<OutputStreamWriter> mapper) {
		super(mapper);
	}
	
	@Override
	public void write(String str, OutputStreamWriter writer, Metadata metadata) throws Exception {
		writer.write(str);
	}
}
