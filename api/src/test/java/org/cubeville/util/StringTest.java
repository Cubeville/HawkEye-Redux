package org.cubeville.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringTest {

	private static final String[] array = new String[]{"This", "is", "an", "array"};

	@Test
	public void testStringBuilder() {
		String expected = "This is an array";
		assertThat(StringUtil.buildString(array, " "), is(expected));
	}

	@Test
	public void testStringBuilderStartIndex() {
		String expected = "an array";
		assertThat(StringUtil.buildString(array, " ", 2), is(expected));
	}

	@Test
	public void testStringBuilderSeparator() {
		String expected = "is,an,array";
		assertThat(StringUtil.buildString(array, ",", 1), is(expected));
	}

}
