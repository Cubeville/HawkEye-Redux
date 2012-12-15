package org.cubeville.hawkeye.config;

import java.io.File;

public interface FileConfiguration extends Configuration {

	/**
	 * Loads the configuration
	 */
	public void load();

	/**
	 * Loads the configuration from a specified file
	 *
	 * @param file File to load from
	 */
	public void load(File file);

	/**
	 * Saves the configuration
	 */
	public void save();

	/**
	 * Saves the configuration to a specified file
	 *
	 * @param file File to save to
	 */
	public void save(File file);

	/**
	 * Reloads the configuration from file
	 */
	public void reload();

}
