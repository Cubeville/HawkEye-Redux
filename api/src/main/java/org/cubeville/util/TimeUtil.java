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

package org.cubeville.util;

public class TimeUtil {

	/**
	 * Time length constants
	 */
	public static final int SECOND = 1;
	public static final int MINUTE = SECOND * 60;
	public static final int HOUR = MINUTE * 60;
	public static final int DAY = HOUR * 24;
	public static final int WEEK = DAY * 7;
	public static final int MONTH = DAY * 30;
	public static final int YEAR = DAY * 365;

	/**
	 * Parses a time string
	 *
	 * Time is specified in the format #y#m#w#d#h#m#s, such that 3d8h is equal
	 * to 3 days + 8 hours. y = year, m = month, w = week, d = day, h = hour,
	 * m = minute, s = second. All time units are optional, but at least one
	 * must be specified.
	 *
	 * @param time Time in the format #y#m#w#d#h#m#s
	 * @return Length of time string in seconds
	 * @throws IllegalArgumentException If the time input has an invalid format
	 */
	public static int parseTime(String time) throws IllegalArgumentException {
		if (time.equalsIgnoreCase("unlimited")) return -1;

		int seconds = 0;
		int minutes = 0;
		int hours = 0;
		int days = 0;
		int weeks = 0;
		int months = 0;
		int years = 0;

		int length = time.length();
		String tmp = "";

		for (int i = 0; i < length; i++) {
			String s = time.substring(i, i + 1);

			try {
				Integer.parseInt(s);
				// Digit is a number, keep parsing until we hit a letter
				tmp += s;
				continue;
			} catch (NumberFormatException ignore) {
			}

			if (tmp.isEmpty()) {
				throw new IllegalArgumentException("Invalid time unit specified: " + s);
			}

			int num = Integer.parseInt(tmp);
			if (s.equalsIgnoreCase("s")) seconds = num;
			else if (s.equalsIgnoreCase("m")) minutes = num;
			else if (s.equalsIgnoreCase("h")) hours = num;
			else if (s.equalsIgnoreCase("d")) days = num;
			else if (s.equalsIgnoreCase("w")) weeks = num;
			else if (s.equalsIgnoreCase("m")) months = num;
			else if (s.equalsIgnoreCase("y")) years = num;
			else throw new IllegalArgumentException("Invalid time unit specified: " + s);

			tmp = "";
		}

		int t = seconds * SECOND;
		t += minutes * MINUTE;
		t += hours * HOUR;
		t += days * DAY;
		t += weeks * WEEK;
		t += months * MONTH;
		t += years * YEAR;

		if (t == 0) throw new IllegalArgumentException("No time unit specified.");
		if (t < 0) throw new IllegalArgumentException("Time must be greater than 0.");

		return t;
	}

	/**
	 * Wrapper method
	 *
	 * @return Unix timestamp in seconds (not milliseconds)
	 */
	public static int now() {
		return (int) (System.currentTimeMillis() / 1000L);
	}

}
