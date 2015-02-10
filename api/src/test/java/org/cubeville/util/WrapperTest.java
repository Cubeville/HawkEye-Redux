package org.cubeville.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WrapperTest {

	@Test
	public void testPair() {
		String left = "Left";
		Object right = new Object();

		Pair<String, Object> pair = new Pair<String, Object>(left, right);

		assertThat("left", pair.getLeft(), is(left));
		assertThat("right", pair.getRight(), is(right));

		Pair<String, Object> pair2 = Pair.of("Test", new Object());
		assertNotEquals("not equal", pair, pair2);

		pair2.setLeft(left);
		pair2.setRight(right);

		assertEquals("equal", pair, pair2);
	}

	@Test
	public void testTriplet() {
		int left = 4;
		String middle =  "middle";
		Object right = new Object();

		Triplet<Integer, String, Object> triplet = new Triplet<Integer, String, Object>(left, middle, right);

		assertThat("left", triplet.getLeft(), is(left));
		assertThat("middle", triplet.getMiddle(), is(middle));
		assertThat("right", triplet.getRight(), is(right));

		Triplet<Integer, String, Object> triplet2 = Triplet.of(2, "middle", new Object());
		assertNotEquals("not equal", triplet, triplet2);

		triplet2.setLeft(left);
		triplet2.setMiddle(middle);
		triplet2.setRight(right);

		assertEquals("equal", triplet, triplet2);
	}

}
