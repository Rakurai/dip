package dip.core;

import dip.core.Core;
import dip.core.RunState;
import dip.modules.Module;

public interface RunnableModule extends Runnable {
	public void setRunState(RunState state);
	public void setCore(Core core);
	public Module getModule();
}
