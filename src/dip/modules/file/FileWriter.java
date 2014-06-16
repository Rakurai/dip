package dip.modules.file;

import java.io.File;

import dip.modules.AbstractWriter;
import dip.modules.IOMapper;
import dip.core.Metadata;

public class FileWriter extends AbstractWriter<String, File> {

	boolean append = false;
	
	public FileWriter(File file) {
		super(file);
		append = true;
	}

	public FileWriter(IOMapper<File> mapper) {
		super(mapper);
	}

	public FileWriter(IOMapper<File> mapper, boolean append) {
		super(mapper);
		this.append = append;
	}

	public void write(String str, File file, Metadata metadata) throws Exception
	{
		java.io.FileWriter writer = new java.io.FileWriter(file, append);
		writer.write(str);
		writer.close();
    }
}
