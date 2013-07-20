/*
 * HawkEye Redux
 * Copyright (C) 2012-2013 Cubeville <http://www.cubeville.org> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.cubeville.hawkeye.util;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.cubeville.hawkeye.PluginEngine;

/**
 * Basic custom logger with a HawkEye prefix
 */
public class HawkEyeLogger extends Logger {

	private static final String PREFIX = "[HawkEye] ";

	public HawkEyeLogger(PluginEngine engine) {
		super(engine.getClass().getCanonicalName(), null);
		setLevel(Level.ALL);
	}

	@Override
	public void log(LogRecord record) {
		record.setMessage(PREFIX + record.getMessage());
		super.log(record);
	}

	/**
	 * Logs a debug message
	 *
	 * @param message Message to log
	 */
	public void debug(String message) {
		info("[DEBUG] " + message);
	}

	/**
	 * Logs an exception
	 *
	 * @param message Message describing the error
	 * @param e Exception that caused the error
	 */
	public void error(String message, Exception e) {
		severe("------------------------------------------------------------");
		severe("An error occurred: " + message);
		severe("");
		printThrowable(e, false);
		severe("------------------------------------------------------------");
	}

	/**
	 * Prints a stack trace for a Throwable
	 *
	 * @param t Throwable to print stack trace for
	 * @param isCause Whether or not this throwable is the cause of another
	 */
	private void printThrowable(Throwable t, boolean isCause) {
		if (isCause) {
			severe("Caused by: " + t.getClass().getName());
		} else {
			severe(t.getClass().getName() + ": " + t.getMessage());
		}

		for (StackTraceElement element : t.getStackTrace()) {
			severe("\tat " + element.toString());
		}

		Throwable cause = t.getCause();
		if (cause != null) {
			printThrowable(cause, true);
		}
	}

}
