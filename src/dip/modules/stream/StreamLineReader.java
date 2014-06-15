package dip.modules.stream;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import dip.modules.AbstractReader;
import dip.modules.IOMapper;

public class StreamLineReader extends AbstractReader<BufferedReader, String> {

	public StreamLineReader(InputStream stream) {
		super(new BufferedReader(new InputStreamReader(stream)));
	}

	public StreamLineReader(BufferedReader reader) {
		super(reader);
	}

	public StreamLineReader(IOMapper<BufferedReader> mapper) {
		super(mapper);
	}

	@Override
	public String read(BufferedReader reader) throws Exception {
		return reader.readLine();
	}
}
