package com.tabordasolutions.cws.parentportal.services;

public class ServicesException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServicesException() {
		super();
	}

	public ServicesException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServicesException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServicesException(String message) {
		super(message);
	}

	public ServicesException(Throwable cause) {
		super(cause);
	}

}
