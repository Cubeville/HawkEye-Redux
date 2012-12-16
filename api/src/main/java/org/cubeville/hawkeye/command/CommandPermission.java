package org.cubeville.hawkeye.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandPermission {

	/**
	 * List of permission nodes required to use this command
	 *
	 * Command will be processed if the sender has one or more of these nodes.
	 */
	String[] value();

}
