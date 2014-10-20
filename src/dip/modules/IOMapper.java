package dip.modules;

import dip.core.Metadata;

public abstract class IOMapper<T> {
	public abstract T acquire(Metadata metadata);
	public abstract void release(T val);
	public abstract void cleanup() throws Exception;
}
