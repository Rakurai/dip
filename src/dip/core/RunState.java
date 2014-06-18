package dip.core;

public class RunState {
	public enum State {
		RUN,
		FINISH,
		STOP
	}

	private State state;

	public RunState(State state) {
		set(state);
	}
	
	public void set(State state) {
		this.state = state;
	}

	public State get() {
		return state;
	}
}
