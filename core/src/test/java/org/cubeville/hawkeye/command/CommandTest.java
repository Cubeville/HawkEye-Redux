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

package org.cubeville.hawkeye.command;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import org.cubeville.hawkeye.entity.TestPlayer;

public class CommandTest {

	private final TestCommandManager manager = new TestCommandManager();
	private final TestConsole console = new TestConsole();

	@Before
	public void registerCommands() {
		try {
			manager.registerCommands(new Commands());
		} catch (CommandException e) {
		}
	}

	@Test
	public void testValidCommand() throws CommandException {
		manager.registerCommands(new ValidCommand());
	}

	@Test(expected = CommandException.class)
	public void testInvalidCommand() throws CommandException {
		manager.registerCommands(new InvalidCommand());
	}

	@Test(expected = CommandException.class)
	public void testInvalidCommandArgs() throws CommandException {
		manager.registerCommands(new InvalidArgsCommand());
	}

	@Test(expected = CommandPlayerException.class)
	public void testInvalidSender() throws CommandException {
		manager.execute("playercmd", console);
	}

	@Test
	public void testPermission() throws CommandException {
		manager.execute("permtest", new TestPlayer("perm2"));
	}

	@Test(expected = CommandPermissionException.class)
	public void testNoPermission() throws CommandException {
		manager.execute("permtest", new TestPlayer("perm3"));
	}

	@Test(expected = CommandUsageException.class)
	public void testArgLength() throws CommandException {
		manager.execute("argtest arg1 arg2 arg3", console);
	}

	@Test
	public void testFlags() throws CommandException {
		manager.execute("flagtest -ac arg1 -d", console);
	}

	@Test(expected = CommandUsageException.class)
	public void testInvalidFlags() throws CommandException {
		manager.execute("flagtest -z", console);
	}

	@Test
	public void testAliases() throws CommandException {
		assertThat(manager.getBaseCommand("alias"), is("alias"));
		assertThat(manager.getBaseCommand("cmdalias arg1"), is("alias"));
		assertThat(manager.getBaseCommand("subcmd alias arg2"), is("alias"));
		assertThat(manager.getBaseCommand("other"), is(not("alias")));
	}

	private class ValidCommand {
		@Command(command = "hawkeye command",
				aliases = {"hawkeye cmd", "command"},
				description = "Test",
				player = false,
				flags = "z",
				min = 0,
				max = 3)
		public void command(CommandSender sender, CommandData data) {
		}
	}

	private class InvalidCommand {
		@Command(command = "command")
		public void flippedArgs(CommandData data, CommandSender sender) {
		}
	}

	private class InvalidArgsCommand {
		@Command(command = "command",
				min = 5,
				max = 3)
		public void invalidArgs(CommandSender sender, CommandData data) {
		}
	}

	private class Commands {
		@Command(command = "playercmd",
				player = true)
		public void playercmd(CommandSender sender, CommandData data) {
		}

		@Command(command = "permtest")
		@CommandPermission({"perm1", "perm2"})
		public void permtest(CommandSender sender, CommandData data) {
		}

		@Command(command = "argtest",
				min = 1,
				max = 2)
		public void argtest(CommandSender sender, CommandData data) {
		}

		@Command(command = "flagtest",
				flags = "abcde")
		public void flagtest(CommandSender sender, CommandData data) {
		}

		@Command(command = "alias",
				aliases = {"cmdalias", "subcmd alias"})
		public void aliastest(CommandSender sender, CommandData data) {
		}
	}

}
