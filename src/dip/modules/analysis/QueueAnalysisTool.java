package dip.modules.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import dip.core.Core;
import dip.core.RunnableReader;
import dip.core.RunnableWriter;
import dip.modules.AbstractModule;

public class QueueAnalysisTool extends AbstractModule implements AnalysisTool {
	List<BlockingQueue<?>> queues = new ArrayList<BlockingQueue<?>>();

	public void addQueue(BlockingQueue<?> queue) {
		queues.add(queue);
	}
	
	@Override
	public void analyze() {
		System.out.print("Queue lengths: ");
		for (BlockingQueue<?> queue: queues) {
			System.out.print(queue.size() + " ");
		}
		System.out.print(", ");
		System.out.print(RunnableReader.getCounter() + " records read");
		System.out.print(", ");
		System.out.print(RunnableWriter.getCounter() + " records written");
		System.out.println();
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}
}
