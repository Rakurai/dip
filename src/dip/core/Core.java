package dip.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dip.modules.converters.Converter;
import dip.modules.managers.Manager;
import dip.modules.Reader;
import dip.modules.Writer;
import dip.modules.Module;

public class Core {
	private CoreWorker worker = null;
	private Thread workerThread = null;
	private Registry registry = new Registry();
	
	List<Module> readers = new ArrayList<Module>();
	List<Module> converters = new ArrayList<Module>();
	List<Module> writers = new ArrayList<Module>();
	
	public void addModule(Module module) {
		module.setCore(this);
	}
	
	public void addReader(Reader module) {
		addModule(module);
		readers.add(module);
	}

	public void addConverter(Converter module) {
		addModule(module);
		converters.add(module);
	}

	public void addWriter(Writer module) {
		addModule(module);
		writers.add(module);
	}

	public void addManager(Manager module) {
		addModule(module);
		writers.add(module);
	}

	public Registry getRegistry() {
		return registry;
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
