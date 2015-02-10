package org.cubeville.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CaseInsensitiveMapTest {

	@Test
	public void testCaseInsensitiveMap() {
		Map<String, Object> caseSensitive = new HashMap<String, Object>();
		caseSensitive.put("Num1", new Object());

		Map<String, Object> test = new CaseInsensitiveMap<Object>();
		test.putAll(caseSensitive);
		test.put("NUm2", new Object());

		assertEquals("num1", test.get("NUM1"), test.get("num1"));
		assertTrue("num2", test.containsKey("Num2"));

		test.remove("nUm1");
		assertFalse("remove", test.containsKey("num1"));
	}

}
