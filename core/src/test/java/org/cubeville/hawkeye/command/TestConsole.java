package org.cubeville.hawkeye.command;

public class TestConsole extends ConsoleCommandSender {

	@Override
	public void sendMessage(String... message) {
	}

	@Override
	public boolean hasPermission(String permission) {
		return true;
	}

}
