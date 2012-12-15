package org.cubeville.util;

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

}
