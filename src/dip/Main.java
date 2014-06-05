package dip;

public class Main {

	public static void main(String[] args) {
		DipQueue q = new DipQueueImpl();

		DipReader reader = new TwitterDipReader(q);
		Thread readerThread = new Thread(reader);
		readerThread.run();
		
		DipWriter writer = new FileDipWriter(q);
		Thread writerThread = new Thread(writer);
		writerThread.run();

		try {
			readerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
