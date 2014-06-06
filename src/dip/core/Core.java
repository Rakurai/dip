package dip.core;

import java.util.ArrayList;
import java.util.List;

import dip.modules.readers.AbstractReader;
import dip.modules.writers.AbstractWriter;
import dip.modules.AbstractConverter;
import dip.modules.Module;

public class Core {
	List<AbstractReader<?>> readers = new ArrayList<AbstractReader<?>>();
	List<AbstractWriter<?>> writers = new ArrayList<AbstractWriter<?>>();
	List<AbstractConverter<?,?>> converters = new ArrayList<AbstractConverter<?,?>>();
	List<Module> modules = new ArrayList<Module>();
	
	public void addReader(AbstractReader<?> reader) {
		readers.add(reader);
		modules.add(reader);
	}
	public void addWriter(AbstractWriter<?> writer) {
		writers.add(writer);
		modules.add(writer);
	}
	
	public void addConverter(AbstractConverter<?,?> converter) {
		converters.add(converter);
		modules.add(converter);
	}
	
	public void start() throws Exception {

		if (readers.size() < 1 || writers.size() < 1 || converters.size() < 1)
			throw new Exception("Must have at least one reader and one writer and one converter.");
		
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
