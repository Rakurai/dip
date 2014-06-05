package dip;

public abstract class DipWriter implements Runnable {
	private DipQueue q;

	public DipWriter(DipQueue q) {
		this.q = q;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
