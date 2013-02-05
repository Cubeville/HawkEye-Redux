package org.cubeville.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TimeTest {

	@Test
	public void testParseTime() {
		assertEquals(TimeUtil.parseTime("5d"), 5 * TimeUtil.DAY);
		assertEquals(TimeUtil.parseTime("1m30s"), 90);

		int expected = TimeUtil.YEAR
				+ 4 * TimeUtil.MONTH
				+ 2 * TimeUtil.WEEK
				+ 3 * TimeUtil.DAY
				+ 8 * TimeUtil.HOUR
				+ 12 * TimeUtil.MINUTE
				+ 42 * TimeUtil.SECOND;
		assertEquals(TimeUtil.parseTime("1y4mo2w3d8h12m42s"), expected);
	}

	@Test
	public void testUnlimited() {
		assertEquals(TimeUtil.parseTime("unlimited"), -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeTime() {
		TimeUtil.parseTime("-1d");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidUnit() {
		TimeUtil.parseTime("8g");
	}

}
