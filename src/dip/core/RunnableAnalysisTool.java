package dip.core;

import dip.core.AbstractRunnableModule;
import dip.core.RunState.State;
import dip.modules.Module;
import dip.modules.analysis.AnalysisTool;

public class RunnableAnalysisTool extends AbstractRunnableModule {
	private AnalysisTool tool;

	public RunnableAnalysisTool(AnalysisTool tool) {
		this.tool = tool;
	}
	
	@Override
	public void run() {
		try {
			while (runState.get() == State.RUN) {
				tool.analyze();
				Thread.sleep(1000);
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Module getModule() {
		return tool;
	}

}
