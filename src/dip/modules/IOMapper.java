package dip.modules;

public abstract class IOMapper<T> {
	public abstract T acquire();
	public void release(T val) {}
}
