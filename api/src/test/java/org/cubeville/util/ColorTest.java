package org.cubeville.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ColorTest {

	@Test
	public void formatColorCodes() {
		String orig = "&0&1&2&3&4&5&6&7&8&9&A&a&B&b&C&c&D&d&E&e&F&f&G&g";
		String expected = Chat.BLACK + Chat.DARK_BLUE + Chat.DARK_GREEN + Chat.DARK_CYAN + Chat.DARK_RED + Chat.PURPLE + Chat.GOLD + Chat.GRAY + Chat.DARK_GRAY + Chat.BLUE + Chat.GREEN + Chat.GREEN + Chat.CYAN + Chat.CYAN + Chat.RED + Chat.RED + Chat.PINK + Chat.PINK + Chat.YELLOW + Chat.YELLOW + Chat.WHITE + Chat.WHITE + "&G&g";

		assertThat(Chat.format(orig), is(expected));
	}

}
