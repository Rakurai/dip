package dip.core;

import java.util.ArrayList;
import java.util.List;

import dip.modules.converters.AbstractConverter;
import dip.modules.converters.Converter;
import dip.modules.readers.Reader;
import dip.modules.writers.Writer;
import dip.modules.Module;
import dip.modules.RunState;

public class Core {
	List<Reader> readers = new ArrayList<Reader>();
	List<Converter> converters = new ArrayList<Converter>();
	List<Writer> writers = new ArrayList<Writer>();
	
	public void addReader(Reader reader) {
		readers.add(reader);
	}

	public void addConverter(Converter converter) {
		converters.add(converter);
	}

	public void addWriter(Writer writer) {
		writers.add(writer);
	}
	
	public void start() throws Exception {
		if (readers.size() < 1 || writers.size() < 1)
			throw new Exception("Must have at least one reader and one writer.");
		
		List<Thread> readerThreads = new ArrayList<Thread>();
		List<Thread> converterThreads = new ArrayList<Thread>();
		List<Thread> writerThreads = new ArrayList<Thread>();

		// start all threads

		for (Module module: readers) {
			Thread thread = new Thread(module);
			readerThreads.add(thread);
			thread.start();
		}

		for (Converter module: converters) {
			module.setRunState(RunState.RUN);
			Thread thread = new Thread(module);
			converterThreads.add(thread);
			thread.start();
		}

		for (Module module: writers) {
			module.setRunState(RunState.RUN);
			Thread thread = new Thread(module);
			writerThreads.add(thread);
			thread.start();
		}

		// wait for the readers to finish (if they're not indefinite)

		for (Thread thread: readerThreads)
			thread.join();

		// signal the converters to finish up their queue
		
		for (Module module: converters)
			module.setRunState(RunState.FINISH);

		// wait for the converters to finish
		
		for (Thread thread: converterThreads)
			thread.join();

		// signal the writers to finish their queue
		
		for (Module module: writers)
			module.setRunState(RunState.FINISH);

		// wait for the writers to finish

		for (Thread thread: writerThreads)
			thread.join();

		// clean up all modules

		for (Module module: readers)
			module.cleanup();
		for (Module module: converters)
			module.cleanup();
		for (Module module: writers)
			module.cleanup();
	}
}
