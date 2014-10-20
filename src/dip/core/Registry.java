package dip.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Registry {
	private ConcurrentMap<MapWrapper, Metadata> registry = new ConcurrentHashMap<MapWrapper, Metadata>();

	public synchronized void register(Object obj, Metadata m) throws Exception {
		if (obj == null)
			throw new Exception("Trying to register null obj.");
		if (m == null)
			throw new Exception("Trying to register null metadata.");

		registry.put(new MapWrapper(obj), m);
	}
	
	public synchronized Metadata deregister(Object obj) throws Exception {
		MapWrapper w = new MapWrapper(obj);
		if (!registry.containsKey(w))
			throw new Exception("No metadata found for object.");

		return registry.remove(w);
	}

	public synchronized Metadata get(Object obj) throws Exception {
		MapWrapper w = new MapWrapper(obj);
		if (!registry.containsKey(w))
			throw new Exception("No metadata found for object.");

		return registry.get(w);
	}

	public synchronized Metadata newMetadata() {
		return new Metadata();
	}
}
