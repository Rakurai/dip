package dip.core;

import java.util.HashMap;
import java.util.Map;

public class Metadata {
	private Map<String, Object> data = new HashMap<String, Object>();
	
	public void set(String key, Object value) {
		data.put(key, value);
	}

	public Object get(String key) {
		return data.get(key);
	}
}
