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

import java.util.HashSet;
import java.util.Set;

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
	 * Set of strings that can be used to represent each time unit
	 */
	private static final Set<String> sec;
	private static final Set<String> min;
	private static final Set<String> hour;
	private static final Set<String> day;
	private static final Set<String> week;
	private static final Set<String> month;
	private static final Set<String> year;

	static {
		sec = new HashSet<String>(4);
		sec.add("s");
		sec.add("sec");
		sec.add("second");
		sec.add("seconds");

		min = new HashSet<String>(4);
		min.add("m");
		min.add("min");
		min.add("minute");
		min.add("minutes");

		hour = new HashSet<String>(4);
		hour.add("h");
		hour.add("hr");
		hour.add("hour");
		hour.add("hours");

		day = new HashSet<String>(3);
		day.add("d");
		day.add("day");
		day.add("days");

		week = new HashSet<String>(4);
		week.add("w");
		week.add("wk");
		week.add("week");
		week.add("weeks");

		month = new HashSet<String>(4);
		month.add("n");
		month.add("mo");
		month.add("month");
		month.add("months");

		year = new HashSet<String>(4);
		year.add("y");
		year.add("yr");
		year.add("year");
		year.add("years");
	}

	/**
	 * Parses a time string
	 *
	 * Time is specified in the format #y#mo#w#d#h#m#s, such that 3d8h is equal
	 * to 3 days + 8 hours. y = year, mo = month, w = week, d = day, h = hour,
	 * m = minute, s = second. All time units are optional, but at least one
	 * must be specified.
	 *
	 * @param time Time in the format #y#mo#w#d#h#m#s
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
		String unit = "";

		for (int i = 0; i < length; i++) {
			String s = time.substring(i, i + 1);

			if (isInteger(s)) {
				// Character is a number, keep parsing until we hit a letter
				tmp += s;
				continue;
			} else {
				unit += s.toLowerCase();
			}

			if (tmp.isEmpty()) {
				throw new IllegalArgumentException("Invalid time unit specified: " + s);
			}

			if (length > (i + 1) && !isInteger(time.substring(i + 1, i + 2))) {
				// Next character is another letter
				continue;
			}

			int num = Integer.parseInt(tmp);

			if (sec.contains(unit)) seconds = num;
			else if (min.contains(unit)) minutes = num;
			else if (hour.contains(unit)) hours = num;
			else if (day.contains(unit)) days = num;
			else if (week.contains(unit)) weeks = num;
			else if (month.contains(unit)) months = num;
			else if (year.contains(unit)) years = num;
			else throw new IllegalArgumentException("Invalid time unit specified: " + unit);

			tmp = "";
			unit = "";
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
	 * Checks if the specified string can be interpreted as an integer
	 *
	 * @param s String to check
	 * @return True if string is a valid integer, false if not
	 */
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
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
