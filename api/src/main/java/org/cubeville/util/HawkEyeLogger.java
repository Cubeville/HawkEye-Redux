/*
 * HawkEye Redux
 * Copyright (C) 2012 Cubeville <http://www.cubeville.org> and contributors
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

package org.cubeville.util;

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

}
