package dip;

import dip.queues.Queue;
import dip.queues.BlockingQueue;
import dip.modules.writers.file.FileNamer;
import dip.modules.writers.file.FileWriter;
import dip.modules.readers.twitter.TwitterReader;

import org.apache.commons.lang3.RandomStringUtils;

public class Twitter2File {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Need an output path.");
			return;
		}
		
		final String outpath = args[0];

		Queue queue = new BlockingQueue();
		Core core = new Core(queue);

		core.addReader(new TwitterReader(queue));
		core.addWriter(new FileWriter(queue, new FileNamer() {
			public String getName() {
				return outpath + "/" + RandomStringUtils.randomAlphabetic(10);
			}
		}));

		try {
			core.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
