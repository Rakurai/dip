package dip.core;

public abstract class AbstractRunnableModule implements RunnableModule {
	protected RunState runState;
	protected Core core;
		
	public void setRunState(RunState state) {
		this.runState = state;
	}
	
	public void setCore(Core core) {
		this.core = core;
	}
}
