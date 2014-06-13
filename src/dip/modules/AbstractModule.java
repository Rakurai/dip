package dip.modules;

import dip.core.Core;
import dip.core.RunState;

public abstract class AbstractModule implements Module {
	protected RunState runState;
	protected Core core;
	
	public void setRunState(RunState state) {
		this.runState = state;
	}
	
	public void setCore(Core core) {
		this.core = core;
	}
}
