package dip.modules;

public interface Module extends Runnable {
	public void setRunState(RunState state);
	public void cleanup();
}
