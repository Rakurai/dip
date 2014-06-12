package dip.modules;

import dip.core.RunState;

public abstract class AbstractModule implements Module {
	protected RunState runState;
	
	public void setRunState(RunState state) {
		this.runState = state;
	}
}
