package com.agreeya.chhs.exception;

/**
 * Top-level exception for account aggregation services.
 * @author Agreeya Solutions
 */
public class MembershipException extends CHHSException {

	public MembershipException() {

	}

	public MembershipException(String errorCode) {
		super(errorCode);
	}

	public MembershipException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public MembershipException(String errorCode, String exceptionMessage, ErrorDetail errorDetail) {
		super(errorCode, exceptionMessage, errorDetail);
	}

	public MembershipException(String errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public MembershipException(String errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	public MembershipException(Throwable cause) {
		super(cause);
	}

}
