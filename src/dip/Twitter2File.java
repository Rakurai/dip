package dip;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import json.JSONObject;
import dip.modules.AbstractConverter;
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

		BlockingQueue<JSONObject> inputQueue = new ArrayBlockingQueue<JSONObject>(100);
		BlockingQueue<String> outputQueue = new ArrayBlockingQueue<String>(100);
		Core core = new Core();

		core.addReader(new TwitterReader(inputQueue));

		core.addWriter(new FileWriter(outputQueue, new FileNamer() {
			public String getName() {
				return outpath + "/" + RandomStringUtils.randomAlphabetic(10);
			}
		}));

		core.addConverter(new AbstractConverter<JSONObject, String>(inputQueue, outputQueue) {
			@Override
			protected String convert(JSONObject obj) {
				return obj.toString();
			}
		});
		
		try {
			core.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
