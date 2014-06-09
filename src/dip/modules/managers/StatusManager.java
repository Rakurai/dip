package dip.modules.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import dip.modules.AbstractModule;
import dip.modules.Module;
import dip.modules.RunState;

public class StatusManager extends AbstractModule {
	List<BlockingQueue<?>> queues = new ArrayList<BlockingQueue<?>>();

	public void addQueue(BlockingQueue<?> queue) {
		queues.add(queue);
	}
	
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		try {
			while (runState == RunState.RUN) {
				System.out.print("Queue lengths: ");
				for (BlockingQueue<?> queue: queues) {
					System.out.print(queue.size() + " ");
				}
				System.out.println();
				Thread.sleep(1000);
			}
		}
		catch (InterruptedException e) {
			// whatever
		}
	}

}
