/**
 * 
 */
package com.agreeya.chhs.exception;

/**
 * User Exception
 * @author AgreeYa Solutions
 *
 */
public class UserException extends CHHSException {

	private static final long serialVersionUID = 1L;

	public UserException() {

	}

	public UserException(String errorCode) {
		super(errorCode);
	}

	public UserException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public UserException(String errorCode, String exceptionMessage, ErrorDetail errorDetail) {
		super(errorCode, exceptionMessage, errorDetail);
	}

	public UserException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public UserException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public UserException(Throwable cause) {
		super(cause);
	}

}
