package modules.readers;

import modules.DipModule;
import dip.DipQueue;

public abstract class DipReader implements Runnable, DipModule {
	protected DipQueue q;

	public DipReader(DipQueue q) {
		this.q = q;
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
