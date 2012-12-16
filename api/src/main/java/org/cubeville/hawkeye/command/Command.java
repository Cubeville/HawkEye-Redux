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
