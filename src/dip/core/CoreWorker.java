package dip.core;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

import dip.core.RunnableModule;

public class CoreWorker implements Runnable {
	List<RunnableModule> readers;
	List<RunnableModule> converters;
	List<RunnableModule> writers;

	List<Thread> readerThreads = new ArrayList<Thread>();
	List<Thread> converterThreads = new ArrayList<Thread>();
	List<Thread> writerThreads = new ArrayList<Thread>();

	UncaughtExceptionHandler handler = new UncaughtExceptionHandler() {
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			terminateThreads();
		}
	};

	public CoreWorker(List<RunnableModule> readers, List<RunnableModule> converters, List<RunnableModule> writers) {
		this.readers = readers;
		this.converters = converters;
		this.writers = writers;
	}

	@Override
	public void run() {
		

		// start all threads

		for (RunnableModule module: readers) {
			module.setRunState(RunState.RUN);
			Thread thread = new Thread(module);
			thread.setUncaughtExceptionHandler(handler);
			readerThreads.add(thread);
			thread.start();
		}

		for (RunnableModule module: converters) {
			module.setRunState(RunState.RUN);
			Thread thread = new Thread(module);
			thread.setUncaughtExceptionHandler(handler);
			converterThreads.add(thread);
			thread.start();
		}

		for (RunnableModule module: writers) {
			module.setRunState(RunState.RUN);
			Thread thread = new Thread(module);
			thread.setUncaughtExceptionHandler(handler);
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
		
		for (RunnableModule module: converters)
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
		
		for (RunnableModule module: writers)
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

		for (RunnableModule module: readers)
			module.getModule().cleanup();
		for (RunnableModule module: converters)
			module.getModule().cleanup();
		for (RunnableModule module: writers)
			module.getModule().cleanup();

	}

	private void terminateThreads() {
		for (Thread thread: readerThreads)
			thread.interrupt();

		for (Thread thread: converterThreads)
			thread.interrupt();

		for (Thread thread: writerThreads)
			thread.interrupt();
	}
}
