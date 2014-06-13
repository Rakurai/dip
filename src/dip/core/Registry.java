package dip.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Registry {
	private ConcurrentMap<Object, Metadata> registry = new ConcurrentHashMap<Object, Metadata>();
	
	public void register(Object obj) {
		registry.put(obj, new Metadata());
	}
	
	public void update(Object old_obj, Object new_obj) {
		Metadata m = registry.remove(old_obj);
		registry.put(new_obj, m);
	}
	
	public void deregister(Object obj) {
		registry.remove(obj);
	}

	public Metadata getMetadata(Object obj) {
		return registry.get(obj);
	}
}
