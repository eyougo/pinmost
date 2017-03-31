package com.pinmost.api.common.exception;

public class CustomerException extends BaseException {
	private String errorCode;
	private Object[] stubParams;
	public CustomerException(String message) {
		super(message);
	}

	public CustomerException(String message, String errorCode, Object[] stubParams) {
		super(message);
		this.errorCode = errorCode;
		this.stubParams = stubParams;
	}

	public CustomerException(String message, Throwable ex, String errorCode, 
			Object[] stubParams) {
		super(message, ex);
		this.errorCode = errorCode;
		this.stubParams = stubParams;
	}

	public CustomerException(String message, Throwable ex, String errorCode) {
		super(message, ex);
		this.errorCode = errorCode;
	}

	public CustomerException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getStubParams() {
		return stubParams;
	}

}
