package org.cubeville.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;

public class LRUCacheTest {

	@Test
	public void testLRUCache() {
		Map<Integer, Object> cache = new LRUCache<Integer, Object>(3);

		for (int i = 1; i <= 5; i++) {
			cache.put(i, new Object());
		}

		assertThat("full", cache.size(), is(3));
		assertFalse("removed eldest", cache.containsKey(1) || cache.containsKey(2));
	}

}
