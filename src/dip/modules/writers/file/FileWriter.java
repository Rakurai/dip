package dip.modules.writers.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.BlockingQueue;

import dip.modules.writers.AbstractWriter;

public class FileWriter extends AbstractWriter<String> {
	FileNamer namer;
	public FileWriter(BlockingQueue<String> q, FileNamer namer) {
		super(q);
		this.namer = namer;
	}
	
	public void write(String str) throws Exception
	{
        String fileName = namer.getName();
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        File file = new File(fileName);
        System.out.println(file.canWrite());
        file.createNewFile();
        try {
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write(str);
            bw.flush();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ignore) {
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException ignore) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
}
