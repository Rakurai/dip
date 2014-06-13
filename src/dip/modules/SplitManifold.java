package dip.modules;

import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;

import dip.modules.converters.AbstractConverter;

public class SplitManifold<KEY, VALUE> extends AbstractConverter<Entry<KEY, VALUE>, VALUE> {

	private BlockingQueue<KEY> keyQueue;

	protected SplitManifold(BlockingQueue<Entry<KEY, VALUE>> inputQueue, BlockingQueue<KEY> keyQueue, BlockingQueue<VALUE> valueQueue) {
		super(inputQueue, valueQueue);
		this.keyQueue = keyQueue;
	}

	@Override
	protected VALUE convert(Entry<KEY, VALUE> entry) throws Exception {
		KEY key = entry.getKey();
		core.getRegistry().register(key, core.getRegistry().getMetadata(entry));
		keyQueue.put(key);
		
		return entry.getValue();
	}
}
