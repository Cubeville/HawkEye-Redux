package org.cubeville.hawkeye;

public class HawkEye {

	private static HawkEyeEngine engine;

	/**
	 * No instantiation
	 */
	private HawkEye() { }

	/**
	 * Gets the current HawkEye engine
	 *
	 * @return HawkEye engine instance
	 */
	public static HawkEyeEngine getEngine() {
		return engine;
	}

	/**
	 * Sets the HawkEye engine. Can only be set once
	 *
	 * @param engine HawkEye engine instance
	 */
	public static void setEngine(HawkEyeEngine engine) {
		if (HawkEye.engine != null) {
			throw new UnsupportedOperationException("HawkEye engine can only be defined once");
		}

		HawkEye.engine = engine;
	}

}
