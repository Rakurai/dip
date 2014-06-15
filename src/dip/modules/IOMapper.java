package dip.modules;

public abstract class IOMapper<T> {
	public abstract T acquire();
	public abstract void release(T val);
}
