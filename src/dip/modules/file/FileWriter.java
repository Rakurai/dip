package dip.modules.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractWriter;

public class FileWriter extends AbstractWriter<String> {
	FileFactory factory;
	public FileWriter(BlockingQueue<String> q, FileFactory factory) {
		super(q);
		this.factory = factory;
	}
	
	public void write(String str) throws Exception
	{
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        File file = factory.getFile();
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
