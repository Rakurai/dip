package dip.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Registry {
	private ConcurrentMap<Object, Metadata> registry = new ConcurrentHashMap<Object, Metadata>();
	
	public void register(Object obj, Metadata m) {
		registry.put(obj, m);
	}
	
	public Metadata deregister(Object obj) {
		return registry.remove(obj);
	}

	public void update(Object old_obj, Object new_obj) {
		Metadata m = deregister(old_obj);
		register(new_obj, m);
	}

	public Metadata get(Object obj) {
		return registry.get(obj);
	}

	public Metadata newMetadata() {
		return new Metadata();
	}
}
