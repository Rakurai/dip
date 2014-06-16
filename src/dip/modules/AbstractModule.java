package dip.modules;

import dip.core.Core;

public abstract class AbstractModule implements Module {
	private Core core;
	
	@Override
	public void setCore(Core core) {
		this.core = core;
	}

	@Override
	public Core getCore() {
		return core;
	}

	@Override
	public abstract void cleanup();
}
