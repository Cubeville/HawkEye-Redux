package org.cubeville.hawkeye.sql;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = -7616468708256365694L;

	public DatabaseException() {
		super();
	}

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseException(Throwable cause) {
		super(cause);
	}

}
