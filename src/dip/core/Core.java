package dip.core;

import java.util.ArrayList;
import java.util.List;

import dip.modules.converters.Converter;
import dip.modules.managers.Manager;
import dip.modules.Reader;
import dip.modules.Writer;
import dip.modules.Module;

public class Core {
	private CoreWorker worker = null;
	private Thread workerThread = null;
	
	List<Module> readers = new ArrayList<Module>();
	List<Module> converters = new ArrayList<Module>();
	List<Module> writers = new ArrayList<Module>();
	
	public void addReader(Reader reader) {
		readers.add(reader);
	}

	public void addConverter(Converter converter) {
		converters.add(converter);
	}

	public void addWriter(Writer writer) {
		writers.add(writer);
	}

	public void addManager(Manager manager) {
		writers.add(manager);
	}

	public void start() throws Exception {
		startAsync();
		workerThread.join();
	}
	
	public void startAsync() throws Exception {
		if (readers.size() < 1 || writers.size() < 1) {
			throw new Exception("Must have at least one reader and one writer.");
		}
		
		worker = new CoreWorker(readers, converters, writers);

		workerThread = new Thread(worker);
		workerThread.run();
	}
}
