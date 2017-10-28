import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.hadoop.hbase.client.Put;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import dip.core.AbstractRunnableProcessor;
import dip.core.Core;
import dip.core.Metadata;
import dip.core.OutputManifold;
import dip.core.RunnableConverter;
import dip.core.RunnableReader;
import dip.core.RunnableWriter;
import dip.modules.AbstractConverter;
import dip.modules.AbstractWriter;
import dip.modules.Module;
import dip.modules.Reader;
import dip.modules.debugging.SinkWriter;
import dip.modules.stream.BufferedLineReader;


public class OutputManifoldTest {
	Core core;
	String inputString = "1 2 3 4 5";
	String outputString;

	@Before
	public void setUp() throws Exception {
		core = new Core();

		InputStream stream = new ByteArrayInputStream(inputString.getBytes("UTF-8"));
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		Reader<BufferedReader, String> reader = new BufferedLineReader(br);
		
		BlockingQueue<String> inputQueue = new ArrayBlockingQueue<String>(100);
		BlockingQueue<List<Integer>> listQueue = new ArrayBlockingQueue<List<Integer>>(100);
		BlockingQueue<Integer> outputQueue = new ArrayBlockingQueue<Integer>(100);

		core.addReader(new RunnableReader<BufferedReader, String>(inputQueue, reader));
		core.addProcessor(new RunnableConverter<String, List<Integer>>(inputQueue, listQueue, new AbstractConverter<String, List<Integer>>() {

			@Override
			public List<Integer> convert(String in, Metadata metadata)
					throws Exception {
				List<Integer> list = new ArrayList<Integer>();
				for (String e: in.split(" ")) {
					list.add(Integer.parseInt(e));
				}
				return list;
			}
			
		}));

		core.addProcessor(new OutputManifold<Integer>(listQueue, outputQueue));
		core.addWriter(new RunnableWriter<Integer, Object>(outputQueue, new AbstractWriter<Integer, Object>((Object)null) {

			@Override
			public void write(Integer input, Object outputVector,
					Metadata metadata) throws Exception {
				System.out.println(input);
			}
			
		}));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			core.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
