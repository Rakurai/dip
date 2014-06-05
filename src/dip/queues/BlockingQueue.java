package dip.queues;

import java.util.concurrent.ArrayBlockingQueue;

import json.JSONObject;

public class BlockingQueue implements Queue {
	ArrayBlockingQueue<JSONObject> queue = new ArrayBlockingQueue<JSONObject>(100);
	
	@Override
	public JSONObject pull() throws Exception {
		return queue.take();
	}

	@Override
	public void push(JSONObject obj) throws Exception {
		queue.put(obj);
	}

}
