package dip.core;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

import dip.core.RunnableModule;

public class CoreWorker implements Runnable {
	private List<RunnableModule> readers;
	private List<RunnableModule> converters;
	private List<RunnableModule> writers;
	private List<RunnableModule> tools;

	private List<Thread> readerThreads = new ArrayList<Thread>();
	private List<Thread> converterThreads = new ArrayList<Thread>();
	private List<Thread> writerThreads = new ArrayList<Thread>();
	private List<Thread> toolThreads = new ArrayList<Thread>();

	private UncaughtExceptionHandler handler = new UncaughtExceptionHandler() {
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			terminateThreads(readerThreads);
			terminateThreads(converterThreads);
			terminateThreads(writerThreads);
			terminateThreads(toolThreads);
		}
	};

	public CoreWorker(List<RunnableModule> readers, List<RunnableModule> converters, List<RunnableModule> writers, List<RunnableModule> tools) {
		this.readers = readers;
		this.converters = converters;
		this.writers = writers;
		this.tools = tools;
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

		for (RunnableModule module: tools) {
			module.setRunState(RunState.RUN);
			Thread thread = new Thread(module);
			thread.setUncaughtExceptionHandler(handler);
			toolThreads.add(thread);
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

		terminateThreads(toolThreads);
	}

	private void terminateThreads(List<Thread> threads) {
		for (Thread thread: threads)
			thread.interrupt();
	}
}
