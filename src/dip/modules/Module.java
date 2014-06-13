package dip.modules;

import dip.core.Core;
import dip.core.RunState;

public interface Module extends Runnable {
	public void setRunState(RunState state);
	public void setCore(Core core);
	public void cleanup();
}
