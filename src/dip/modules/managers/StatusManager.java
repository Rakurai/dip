package dip.modules.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import dip.core.AbstractRunnableModule;
import dip.core.RunState;
import dip.modules.Module;

public class StatusManager extends AbstractRunnableModule implements Manager {
	List<BlockingQueue<?>> queues = new ArrayList<BlockingQueue<?>>();

	public void addQueue(BlockingQueue<?> queue) {
		queues.add(queue);
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

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Module getModule() {
		return this;
	}

}
