package org.cubeville.hawkeye.command;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.cubeville.util.StringUtil;

public class CommandData {

	private final String command;
	private final String[] originalArgs;
	private final List<String> processedArgs;
	private final Set<Character> flags;

	public CommandData(String args) {
		this(args.split(" "));
	}

	public CommandData(String[] args) {
		originalArgs = args;

		processedArgs = new LinkedList<String>();
		flags = new HashSet<Character>();

		command = args[0];
		for (int i = 1; i < args.length; i++) {
			String arg = args[i];
			if (arg.length() == 0) continue;

			if (arg.charAt(0) == '-' && arg.length() >= 2) {
				for (int j = 1; j < arg.length(); j++) {
					flags.add(arg.charAt(j));
				}

				continue;
			}

			processedArgs.add(arg);
		}
	}

	public int length() {
		return processedArgs.size();
	}

	public String getCommand() {
		return command;
	}

	public String getString(int index) {
		return processedArgs.get(index);
	}

	public String getFullString(int startIndex) {
		return StringUtil.buildString(processedArgs, " ", startIndex);
	}

	public int getInt(int index) throws NumberFormatException {
		return Integer.parseInt(processedArgs.get(index));
	}

	public double getDouble(int index) throws NumberFormatException {
		return Double.parseDouble(processedArgs.get(index));
	}

	public boolean hasFlag(char flag) {
		return flags.contains(flag);
	}

}
