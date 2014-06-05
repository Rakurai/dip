package dip;

public abstract class DipReader implements Runnable {
	protected DipQueue q;

	public DipReader(DipQueue q) {
		this.q = q;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
