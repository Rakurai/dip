package dip;

import dip.DipQueue;
import dip.DipQueueImpl;
import modules.writers.file.FileNamer;
import modules.writers.file.FileDipWriter;
import modules.readers.twitter.TwitterDipReader;

import org.apache.commons.lang3.RandomStringUtils;

public class Twitter2File {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Need an output path.");
			return;
		}
		
		final String outpath = args[0];
		
		DipQueue q = new DipQueueImpl();

		TwitterDipReader reader = new TwitterDipReader(q);
		Thread readerThread = new Thread(reader);
		readerThread.run();
		
		FileDipWriter writer = new FileDipWriter(q, new FileNamer() {
			public String getName() {
				return outpath + "/" + RandomStringUtils.randomAlphabetic(10);
			}
		});
		Thread writerThread = new Thread(writer);
		writerThread.run();

		try {
			readerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
