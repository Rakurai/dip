package modules.writers;

import modules.DipModule;
import dip.DipQueue;

public abstract class DipWriter implements Runnable, DipModule {
	protected DipQueue q;

	public DipWriter(DipQueue q) {
		this.q = q;
	}

	@Override
	public void cleanup() {
		// do nothing
	}
}
