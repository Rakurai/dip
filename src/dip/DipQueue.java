package dip;

import json.JSONObject;

public interface DipQueue {
	public JSONObject pull() throws Exception;
	public void push(JSONObject obj) throws Exception;
}
