/*
 * HawkEye Redux
 * Copyright (C) 2012-2015 Cubeville <http://www.cubeville.org> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
