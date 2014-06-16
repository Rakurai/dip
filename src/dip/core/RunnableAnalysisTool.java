package dip.core;

import dip.core.AbstractRunnableModule;
import dip.core.RunState;
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
			while (runState == RunState.RUN) {
				tool.analyze();
				Thread.sleep(1000);
			}
		}
		catch (InterruptedException e) {
			// whatever
		}
	}

	@Override
	public Module getModule() {
		return tool;
	}

}
