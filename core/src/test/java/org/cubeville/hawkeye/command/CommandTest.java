package org.cubeville.hawkeye.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.cubeville.hawkeye.ServerInterface;
import org.cubeville.hawkeye.entity.TestPlayer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CommandTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private final CommandManager manager = new SimpleCommandManager(mock(ServerInterface.class));
	private final CommandSender console = mock(ConsoleCommandSender.class);
	private final Callback callback = mock(Callback.class);

	@Before
	public void registerCommands() {
		try {
			manager.registerCommands(new Commands(callback));
		} catch (CommandException e) { }
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

	@Test
	public void testRequirePlayer() throws CommandException {
		manager.execute("playercmd", new TestPlayer());
		verify(callback).called("playercmd");

		exception.expect(CommandPlayerException.class);
		manager.execute("playercmd", console);
	}

	@Test
	public void testPermission() throws CommandException {
		manager.execute("permtest", new TestPlayer("perm2"));
		verify(callback).called("permtest");
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
		verify(callback).called("flagtest");
		verify(callback).hasFlag('a');
		verify(callback, never()).hasFlag('b');
		verify(callback).hasFlag('c');
		verify(callback).hasFlag('d');
		verify(callback, never()).hasFlag('e');
		verify(callback).args("arg1");
	}

	@Test(expected = CommandUsageException.class)
	public void testInvalidFlags() throws CommandException {
		manager.execute("flagtest -z", console);
	}

	@Test
	public void testAliases() throws CommandException {
		CommandSender sender = new TestPlayer();

		manager.execute("alias", sender);
		verify(callback).called("alias");
		verify(callback).args("");

		manager.execute("cmdalias arg1", sender);
		verify(callback, times(2)).called("alias");
		verify(callback).args("arg1");

		manager.execute("subcmd alias arg2", sender);
		verify(callback, times(3)).called("alias");
		verify(callback).args("arg2");

		exception.expect(CommandException.class);
		manager.execute("other", sender);
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
		/**
		 * @see {@link Callback}
		 */
		private final Callback callback;

		private Commands(Callback callback) {
			this.callback = callback;
		}

		@Command(command = "playercmd",
				player = true)
		public void playercmd(CommandSender sender, CommandData data) {
			callback.called("playercmd");
		}

		@Command(command = "permtest")
		@CommandPermission({"perm1", "perm2"})
		public void permtest(CommandSender sender, CommandData data) {
			callback.called("permtest");
		}

		@Command(command = "argtest",
				min = 1,
				max = 2)
		public void argtest(CommandSender sender, CommandData data) {
			callback.called("argtest");
		}

		@Command(command = "flagtest",
				flags = "abcde")
		public void flagtest(CommandSender sender, CommandData data) {
			callback.called("flagtest");
			for (Character flag : data.getFlags()) callback.hasFlag(flag);
			callback.args(data.getFullString(0));
		}

		@Command(command = "alias",
				aliases = {"cmdalias", "subcmd alias"})
		public void aliastest(CommandSender sender, CommandData data) {
			callback.called("alias");
			callback.args(data.getFullString(0));
		}
	}

	/**
	 * The Commands class is unable to be directly mocked because it results in
	 * loss of method annotations, rendering them unable to be registered by
	 * the command manager.  To get around this problem we pass in a mocked
	 * callback that can be used to verify whether or not the expected command
	 * was run.
	 */
	private interface Callback {
		void called(String cmd);
		void hasFlag(char... ch);
		void args(String args);
	}

}
