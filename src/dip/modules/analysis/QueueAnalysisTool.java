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

	long prevtime;
	int prevcounter;
	
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

		long nowtime = System.nanoTime();
		long elapsedtime = nowtime-prevtime;
		prevtime = nowtime;
		int nowcounter = RunnableWriter.getCounter();
		int elapsedcounter = nowcounter-prevcounter;
		prevcounter = nowcounter;
		System.out.print(" (" + (elapsedcounter / (elapsedtime/1000000000.) * 60) + " records/minute");
		System.out.println();
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}
}
