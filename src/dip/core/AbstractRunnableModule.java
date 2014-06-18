package dip.core;

public abstract class AbstractRunnableModule implements RunnableModule {
	protected RunState runState;
	protected Core core;
	private static int counter = 0;

	protected synchronized static void incrementCounter() {
		counter++;
	}
	
	public static int getCounter() {
		return counter;
	}

	public void setRunState(RunState state) {
		this.runState = state;
	}
	
	public void setCore(Core core) {
		this.core = core;
	}
}
