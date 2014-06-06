package dip.core;

import java.util.ArrayList;
import java.util.List;

import dip.modules.converters.AbstractConverter;
import dip.modules.converters.Converter;
import dip.modules.readers.Reader;
import dip.modules.writers.Writer;
import dip.modules.Module;

public class Core {
	List<Reader> readers = new ArrayList<Reader>();
	List<Writer> writers = new ArrayList<Writer>();
	List<AbstractConverter<?,?>> converters = new ArrayList<AbstractConverter<?,?>>();
	List<Module> modules = new ArrayList<Module>();
	
	public void addReader(Reader reader) {
		readers.add(reader);
		modules.add(reader);
	}
	public void addWriter(Writer writer) {
		writers.add(writer);
		modules.add(writer);
	}
	
	public void addConverter(AbstractConverter<?,?> converter) {
		converters.add(converter);
		modules.add(converter);
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
