package dip.core;

import java.util.ArrayList;
import java.util.List;

import dip.modules.Module;

public class CoreWorker implements Runnable {
	List<Module> readers;
	List<Module> converters;
	List<Module> writers;
	
	public CoreWorker(List<Module> readers, List<Module> converters, List<Module> writers) {
		this.readers = readers;
		this.converters = converters;
		this.writers = writers;
	}

	@Override
	public void run() {
		List<Thread> readerThreads = new ArrayList<Thread>();
		List<Thread> converterThreads = new ArrayList<Thread>();
		List<Thread> writerThreads = new ArrayList<Thread>();

		// start all threads

		for (Module module: readers) {
			module.setRunState(RunState.RUN);
			Thread thread = new Thread(module);
			readerThreads.add(thread);
			thread.start();
		}

		for (Module module: converters) {
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
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		// signal the converters to finish up their queue
		
		for (Module module: converters)
			module.setRunState(RunState.FINISH);

		// wait for the converters to finish
		
		for (Thread thread: converterThreads)
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		// signal the writers to finish their queue
		
		for (Module module: writers)
			module.setRunState(RunState.FINISH);

		// wait for the writers to finish

		for (Thread thread: writerThreads)
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		// clean up all modules

		for (Module module: readers)
			module.cleanup();
		for (Module module: converters)
			module.cleanup();
		for (Module module: writers)
			module.cleanup();

	}

}
