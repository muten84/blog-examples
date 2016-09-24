/**
 * 
 */
package it.eng.area118.sdocommon.time.impl;

/**
 * @author Bifulco Luigi
 *
 */
public class TimeException extends RuntimeException {

	private static final long serialVersionUID = -6448016867514302256L;

	public TimeException() {
		super();
	}

	public TimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeException(String message) {
		super(message);
	}

	public TimeException(Throwable cause) {
		super(cause);
	}

}
