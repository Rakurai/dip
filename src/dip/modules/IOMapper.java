package dip.modules;

public abstract class IOMapper<T> {
	public abstract T get();
	public void finish(T val) {}
}
