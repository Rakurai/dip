package dip.modules.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import dip.core.Metadata;
import dip.modules.AbstractReader;
import dip.modules.IOMapper;

public class BufferedLineReader extends AbstractReader<BufferedReader, String> {

	public BufferedLineReader(File file) throws FileNotFoundException {
		super(new BufferedReader(new FileReader(file)));
	}

	public BufferedLineReader(InputStream stream) {
		super(new BufferedReader(new InputStreamReader(stream)));
	}

	public BufferedLineReader(BufferedReader reader) {
		super(reader);
	}

	public BufferedLineReader(IOMapper<BufferedReader> mapper) {
		super(mapper);
	}

	@Override
	public String read(BufferedReader inputVector, Metadata metadata) throws Exception {
		return inputVector.readLine();
	}

}
