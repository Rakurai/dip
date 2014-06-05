package dip;

import java.util.ArrayList;
import java.util.List;

import dip.queues.Queue;
import dip.modules.readers.Reader;
import dip.modules.writers.Writer;
import dip.modules.Module;

public class Core {
	List<Reader> readers = new ArrayList<Reader>();
	List<Writer> writers = new ArrayList<Writer>();
	List<Module> modules = new ArrayList<Module>();
	
	Queue queue;

	public Core(Queue queue) {
		this.queue = queue;
	}

	public void addReader(Reader reader) {
		readers.add(reader);
		modules.add(reader);
	}
	public void addWriter(Writer writer) {
		writers.add(writer);
		modules.add(writer);
	}
	
	public void start() throws Exception {

		if (readers.size() < 1 || writers.size() < 1)
			throw new Exception("Must have at least one reader and one writer.");
		
		List<Thread> threads = new ArrayList<Thread>();

		for (Module module: modules) {
			Thread thread = new Thread(module);
			threads.add(thread);
			thread.run();
		}

		for (Thread thread: threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (Module module: modules)
			module.cleanup();
	}
}
