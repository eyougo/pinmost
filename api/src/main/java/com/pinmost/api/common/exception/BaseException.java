package com.pinmost.api.common.exception;


public abstract class BaseException extends Exception {

	/**
	 * generated
	 */
	private static final long serialVersionUID = 6962542366661392884L;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
	
}
