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

import java.util.Arrays;
import java.util.List;

public class StringUtil {

	/**
	 * Converts an array of strings into one string
	 *
	 * @param args Array of strings to join
	 * @param separator Delimiter to separate each string with
	 * @return Full string
	 */
	public static String buildString(String[] args, String separator) {
		return buildString(args, separator, 0);
	}

	public static String buildString(String separator, String... args) {
		return buildString(args, separator);
	}

	public static String buildString(String[] args) {
		return buildString(args, " ");
	}

	public static String buildString(String[] args, int startIndex) {
		return buildString(args, " ", startIndex);
	}

	/**
	 * Converts an array of strings into one string
	 *
	 * @param args Array of strings to join
	 * @param separator Delimiter to separate each string with
	 * @param startIndex Index of array to start at
	 * @return Full string
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

	/**
	 * Converts a comma-delimited string to a list
	 *
	 * @param arg Comma-delimited string to convert
	 * @return List of strings
	 */
	public static List<String> split(String arg) {
		return split(arg, ",");
	}

	/**
	 * Converts a delimited string to a list
	 *
	 * @param arg Delimited string to convert
	 * @param delimiter String delimiter
	 * @return List of strings
	 */
	public static List<String> split(String arg, String delimiter) {
		return Arrays.asList(arg.split(delimiter));
	}

}
