package dip.modules.managers;

import java.util.concurrent.BlockingQueue;

import dip.modules.Module;

public interface Manager extends Module {

	void addQueue(BlockingQueue<?> stringQueue);

}
