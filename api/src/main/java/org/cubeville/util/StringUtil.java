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

package org.cubeville.util;

import java.util.List;

public class StringUtil {

	/**
	 * Converts an array of strings into one string
	 *
	 * @param args Array of strings to join
	 * @param separator Delimiter to separate each string with
	 * @return
	 */
	public static String buildString(String[] args, String separator) {
		return buildString(args, separator, 0);
	}

	/**
	 * Converts an array of strings into one string
	 *
	 * @param args Array of strings to join
	 * @param separator Delimiter to separate each string with
	 * @param startIndex Index of array to start at
	 * @return
	 */
	public static String buildString(String[] args, String separator, int startIndex) {
		StringBuilder output = new StringBuilder();

		String tmp = "";
		for (int i = startIndex; i < args.length; i++) {
			output.append(tmp);
			tmp = separator;
			output.append(args[i]);
		}

		return output.toString();
	}

	public static String buildString(List<String> args, String separator) {
		return buildString(args, separator, 0);
	}

	public static String buildString(List<String> args, String separator, int startIndex) {
		return buildString(args.toArray(new String[args.size()]), separator, startIndex);
	}

}
