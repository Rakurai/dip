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
	
	public void run()
	{
		while(true)
		{
			try {
				String stuff = q.take();
				save(stuff);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
    //Taken from https://github.com/yusuke/twitter4j/blob/master/twitter4j-examples/src/main/java/twitter4j/examples/json/SaveRawJSON.java
    private void save(String rawJSON) throws IOException {
//    	String pwd = System.getProperty("user.dir");
        String fileName = namer.getName();
        //System.out.println("Attempting to save to location: " + filename);
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
            bw.write(rawJSON);
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