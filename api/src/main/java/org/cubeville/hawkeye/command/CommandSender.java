package org.cubeville.hawkeye.command;

public interface CommandSender {

	/**
	 * Gets this command sender's name
	 *
	 * @return This command sender's name
	 */
	String getName();

	/**
	 * Gets this command sender's display name
	 *
	 * @return This command sender's display name
	 */
	String getDisplayName();

	/**
	 * Sends a message to this command sender
	 *
	 * @param message Message(s) to send
	 */
	void sendMessage(String... message);

	/**
	 * Checks if this command sender has a permission node
	 *
	 * @param permission Permission node to check for
	 * @return True if this command sender has permission, false if not
	 */
	boolean hasPermission(String permission);

	/**
	 * Checks if this command sender is a player
	 *
	 * @return True if command sender is a player, false if not
	 */
	boolean isPlayer();

}
