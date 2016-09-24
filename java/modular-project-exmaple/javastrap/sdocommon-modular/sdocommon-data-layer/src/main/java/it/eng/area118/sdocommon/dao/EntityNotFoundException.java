package it.eng.area118.sdocommon.dao;

public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 7185588583171580432L;

	public EntityNotFoundException() {
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}

}
