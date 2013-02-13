package org.cubeville.hawkeye.command;

import java.util.List;

public class TestCommandManager extends SimpleCommandManager {

	public TestCommandManager() {
		super();
	}

	@Override
	protected void registerCommands(List<String> commands) { }

	public String getBaseCommand(String command) {
		return super.parseCommand(command).getLeft();
	}

}
