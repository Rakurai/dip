package dip;

public abstract class DipReader implements Runnable {
	private DipQueue q;

	public DipReader(DipQueue q) {
		this.q = q;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
