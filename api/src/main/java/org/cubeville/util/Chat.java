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

public class Chat {

	/**
	 * Minecraft color escape sequence character
	 */
	public static final char FORMAT_CHAR = '\u00A7';

	/**
	 * Chat colors
	 */
	public static final String BLACK = FORMAT_CHAR + "0";
	public static final String DARK_BLUE = FORMAT_CHAR + "1";
	public static final String DARK_GREEN = FORMAT_CHAR + "2";
	public static final String DARK_CYAN = FORMAT_CHAR + "3";
	public static final String DARK_RED = FORMAT_CHAR + "4";
	public static final String PURPLE = FORMAT_CHAR + "5";
	public static final String GOLD = FORMAT_CHAR + "6";
	public static final String GRAY = FORMAT_CHAR + "7";
	public static final String DARK_GRAY = FORMAT_CHAR + "8";
	public static final String BLUE = FORMAT_CHAR + "9";
	public static final String GREEN = FORMAT_CHAR + "a";
	public static final String CYAN = FORMAT_CHAR + "b";
	public static final String RED = FORMAT_CHAR + "c";
	public static final String PINK = FORMAT_CHAR + "d";
	public static final String YELLOW = FORMAT_CHAR + "e";
	public static final String WHITE = FORMAT_CHAR + "f";

	/**
	 * Chat formatting
	 */
	public static final String MAGIC = FORMAT_CHAR + "k";
	public static final String BOLD = FORMAT_CHAR + "l";
	public static final String STRIKETHROUGH = FORMAT_CHAR + "m";
	public static final String UNDERLINE = FORMAT_CHAR + "n";
	public static final String ITALIC = FORMAT_CHAR + "o";

	/**
	 * Reset sequence
	 */
	public static final String RESET = FORMAT_CHAR + "r";

	/**
	 * String containing all valid formatting codes
	 */
	public static final String valid = "0123456789AaBbCcDdEeFfKkLlMmNnOoPpRr";

	/**
	 * Replaces color codes (prefixed with ampersands) with the Minecraft color
	 * escape sequences
	 *
	 * @param message Message to format
	 * @return Formatted message
	 */
	public static String format(String message) {
		return format(message, '&');
	}

	/**
	 * Replaces color codes (with specified prefix) with the Minecraft color
	 * escape sequences
	 *
	 * @param message Message to format
	 * @param escape Character used to escape color codes
	 * @return Formatted message
	 */
	public static String format(String message, char escape) {
		if (message.isEmpty()) return "";

		char[] s = message.toCharArray();
		int len = s.length;

		for (int i = 0; i < len; i++) {
			char check = s[i];

			// Check if this is the escape character
			if (check != escape) continue;

			// Make sure another character exists
			if ((i + 1) == len) break;

			char next = s[i + 1];

			// Check if it's a valid color character
			if (valid.indexOf(next) > -1) {
				s[i] = FORMAT_CHAR;
				i++;
				// Uppercase causes issues
				s[i] = Character.toLowerCase(s[i]);
			}
		}

		return new String(s);
	}

}
