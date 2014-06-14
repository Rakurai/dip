package dip;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import dip.core.Core;
import dip.modules.IOMapper;
import dip.modules.file.FileMultiWriter;
import dip.modules.twitter.TwitterReader;

import org.apache.commons.lang3.RandomStringUtils;

public class Twitter2File {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Need an output path.");
			return;
		}
		
		final String outpath = args[0];

		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
		Core core = new Core();

		core.addReader(new TwitterReader(queue));

		core.addWriter(new FileMultiWriter(queue, new IOMapper<File>() {
			public File acquire() {
				return new File(outpath + "/" + RandomStringUtils.randomAlphabetic(10));
			}
		}));

		try {
			core.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
