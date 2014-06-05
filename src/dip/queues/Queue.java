package dip.queues;

import json.JSONObject;

public interface Queue {
	public JSONObject pull() throws Exception;
	public void push(JSONObject obj) throws Exception;
}
