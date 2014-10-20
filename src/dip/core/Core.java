package dip.core;

import java.util.ArrayList;
import java.util.List;

public class Core {
	private CoreWorker worker = null;
	private Thread workerThread = null;
	private Registry registry = new Registry();
	
	List<RunnableModule> readers = new ArrayList<RunnableModule>();
	List<RunnableModule> converters = new ArrayList<RunnableModule>();
	List<RunnableModule> writers = new ArrayList<RunnableModule>();
	List<RunnableModule> tools = new ArrayList<RunnableModule>();
	
	public void addModule(RunnableModule module) {
		module.setCore(this);
	}

	public void addReader(RunnableReader<?, ?> module) {
		addModule(module);
		readers.add(module);
	}

	public void addProcessor(AbstractRunnableProcessor<?, ?> module) {
		addModule(module);
		converters.add(module);
	}

	public void addWriter(RunnableWriter<?, ?> module) {
		addModule(module);
		writers.add(module);
	}

	public void addAnalysisTool(RunnableAnalysisTool module) {
		addModule(module);
		tools.add(module);
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
		
		worker = new CoreWorker(readers, converters, writers, tools);

		workerThread = new Thread(worker);
		workerThread.run();
	}
}
