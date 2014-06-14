package dip.modules.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractMultiWriter;
import dip.modules.IOMapper;

public class FileMultiWriter extends AbstractMultiWriter<String, File> {

	boolean append = false;

	public FileMultiWriter(BlockingQueue<String> queue, IOMapper<File> mapper) {
		super(queue, mapper);
	}

	public FileMultiWriter(BlockingQueue<String> queue, IOMapper<File> mapper, boolean append) {
		super(queue, mapper);
		this.append = append;
	}

	public void write(String str, File file) throws Exception
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));
		writer.write(str);
		writer.close();
    }
}
