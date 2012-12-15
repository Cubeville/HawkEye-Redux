/*
 * HawkEye
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

package org.cubeville.hawkeye;

public class HawkEye {

	private static HawkEyeEngine engine;

	/**
	 * No instantiation
	 */
	private HawkEye() { }

	/**
	 * Gets the current HawkEye engine
	 *
	 * @return HawkEye engine instance
	 */
	public static HawkEyeEngine getEngine() {
		return engine;
	}

	/**
	 * Sets the HawkEye engine. Can only be set once
	 *
	 * @param engine HawkEye engine instance
	 */
	public static void setEngine(HawkEyeEngine engine) {
		if (HawkEye.engine != null) {
			throw new UnsupportedOperationException("HawkEye engine can only be defined once");
		}

		HawkEye.engine = engine;
	}

}
