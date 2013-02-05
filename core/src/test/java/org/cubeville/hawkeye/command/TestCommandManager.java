package org.cubeville.hawkeye.command;

public class TestCommandManager extends SimpleCommandManager {

	public TestCommandManager() {
		super(null);
	}

	public String getBaseCommand(String command) {
		return super.parseCommand(command).getLeft();
	}

}
