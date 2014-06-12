package dip.modules;

import dip.core.RunState;

public interface Module extends Runnable {
	public void setRunState(RunState state);
	public void cleanup();
}
