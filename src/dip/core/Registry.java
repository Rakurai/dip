package dip.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Registry {
	private ConcurrentMap<Object, Metadata> registry = new ConcurrentHashMap<Object, Metadata>();
	
	public synchronized void register(Object obj, Metadata m) throws Exception {
		if (obj == null)
			throw new Exception("Trying to register null obj.");
		if (m == null)
			throw new Exception("Trying to register null metadata.");

		registry.put(obj, m);
	}
	
	public synchronized Metadata deregister(Object obj) throws Exception {
		if (!registry.containsKey(obj))
			throw new Exception("No metadata found for object.");

		return registry.remove(obj);
	}

	public synchronized Metadata get(Object obj) throws Exception {
		if (!registry.containsKey(obj))
			throw new Exception("No metadata found for object.");

		return registry.get(obj);
	}

	public synchronized Metadata newMetadata() {
		return new Metadata();
	}
}
