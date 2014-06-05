package dip;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import json.JSONObject;

public class DipQueueImpl implements DipQueue {
	BlockingQueue<JSONObject> queue = new ArrayBlockingQueue<JSONObject>(100);
	
	@Override
	public JSONObject pull() throws Exception {
		return queue.take();
	}

	@Override
	public void push(JSONObject obj) throws Exception {
		queue.put(obj);
	}

}
