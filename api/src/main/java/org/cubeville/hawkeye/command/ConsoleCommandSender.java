package org.cubeville.hawkeye.command;

public abstract class ConsoleCommandSender implements CommandSender {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getDisplayName() {
		return "*Console";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isPlayer() {
		return false;
	}

}
