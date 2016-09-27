package com.agreeya.chhs.exception;

/**
 * @author Agreeya Solutions
 * @Top-level exception for AES security(enc/dec) services.
 */
public class AESSecurityException extends CHHSException {

	private static final long serialVersionUID = 1L;

	public AESSecurityException() {

	}

	public AESSecurityException(String errorCode) {
		super(errorCode);
	}

	public AESSecurityException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public AESSecurityException(String errorCode, String exceptionMessage, ErrorDetail errorDetail) {
		super(errorCode, exceptionMessage, errorDetail);
	}

	public AESSecurityException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public AESSecurityException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public AESSecurityException(Throwable cause) {
		super(cause);
	}
}
