package dip;

import json.JSONObject;

public interface DipQueue {
	public JSONObject pull();
	public void push(JSONObject obj);
}
