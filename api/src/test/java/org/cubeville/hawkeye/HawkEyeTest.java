package org.cubeville.hawkeye;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HawkEyeTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testSetEngineOnlyOnce() {
		PluginEngine engine = mock(PluginEngine.class);

		HawkEye.setEngine(engine);

		exception.expect(UnsupportedOperationException.class);
		HawkEye.setEngine(engine);
	}

	@Test
	public void testIsStatic() {
		for (Method method : HawkEye.class.getDeclaredMethods()) {
			assertTrue(method.getName(), Modifier.isStatic(method.getModifiers()));
		}
	}

}
