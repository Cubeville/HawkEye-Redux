package org.cubeville.hawkeye.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommandTest {

	@Test
	public void testCommandFlags() {
		CommandData data = new CommandData("/cmd", "-ab -c");

		assertEquals(data.length(), 0);
		assertTrue(data.hasFlag('a'));
		assertTrue(data.hasFlag('b'));
		assertTrue(data.hasFlag('c'));
		assertFalse(data.hasFlag('z'));
	}

	@Test
	public void testCommandArgs() {
		CommandData data = new CommandData("/cmd", "arg1 2 arg3 4.5 arg5");

		assertEquals(data.length(), 5);
		assertEquals("arg1", data.getString(0));
		assertEquals(2, data.getInt(1));
		assertEquals("arg3", data.getString(2));
		assertEquals(4.5d, data.getDouble(3), 0);
		assertEquals("arg3 4.5 arg5", data.getFullString(2));
	}

	@Test
	public void testCommandComplex() {
		CommandData data = new CommandData("/cmd", "-a arg1 -b arg2 -c arg3");

		assertEquals(data.length(), 3);
		assertEquals("arg1", data.getString(0));
		assertEquals("arg2", data.getString(1));
		assertEquals("arg3", data.getString(2));
		assertTrue(data.hasFlag('a'));
		assertTrue(data.hasFlag('b'));
		assertTrue(data.hasFlag('c'));
		assertFalse(data.hasFlag('d'));
	}

}
