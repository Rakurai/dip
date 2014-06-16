package dip.modules;

import dip.core.Core;

public interface Module {
	public void setCore(Core core);
	public Core getCore();
	public void cleanup();
}
