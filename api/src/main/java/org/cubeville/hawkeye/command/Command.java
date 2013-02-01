/*
 * HawkEye Redux
 * Copyright (C) 2012-2013 Cubeville <http://www.cubeville.org> and contributors
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

package org.cubeville.hawkeye.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {

	/**
	 * Command name
	 */
	String command();

	/**
	 * Command aliases
	 */
	String[] aliases() default {};

	/**
	 * Command usage (shown to the user if they enter it wrong)
	 */
	String usage() default "";

	/**
	 * Command description
	 */
	String description() default "";

	/**
	 * Whether or not this command can only be run by a player
	 */
	boolean player() default false;

	/**
	 * List of flags that can be used with this command
	 */
	String flags() default "";

	/**
	 * Minimum number of arguments required to process
	 */
	int min() default 0;

	/**
	 * Maximum number of arguments allowed (-1 for unlimited)
	 */
	int max() default -1;

}
