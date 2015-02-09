package org.cubeville.hawkeye.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommandTest {

	@Test
	public void testCommandFlags() {
		CommandData data = new CommandData("/cmd", "-ab -c");

		assertEquals("arg length", data.length(), 0);
		assertTrue("flag a", data.hasFlag('a'));
		assertTrue("flag b", data.hasFlag('b'));
		assertTrue("flag c", data.hasFlag('c'));
		assertFalse("flag z", data.hasFlag('z'));
	}

	@Test
	public void testCommandArgs() {
		CommandData data = new CommandData("/cmd", "arg1 2 arg3 4.5 arg5");

		assertEquals("arg length", data.length(), 5);
		assertEquals("arg1", "arg1", data.getString(0));
		assertEquals("arg2", 2, data.getInt(1));
		assertEquals("arg3", "arg3", data.getString(2));
		assertEquals("arg4", 4.5d, data.getDouble(3), 0);
		assertEquals("args3-5", "arg3 4.5 arg5", data.getFullString(2));
	}

	@Test
	public void testCommandComplex() {
		CommandData data = new CommandData("/cmd", "-a arg1 -b arg2 -c arg3");

		assertEquals(data.length(), 3);
		assertEquals("arg1", "arg1", data.getString(0));
		assertEquals("arg2", "arg2", data.getString(1));
		assertEquals("arg3", "arg3", data.getString(2));
		assertTrue("flag a", data.hasFlag('a'));
		assertTrue("flag b", data.hasFlag('b'));
		assertTrue("flag c", data.hasFlag('c'));
		assertFalse("flag d", data.hasFlag('d'));
	}

}
