package it.eng.area118.sdocommon.dao;

public class DataAccessException extends Exception {

	private static final long serialVersionUID = -3414124238055736870L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
