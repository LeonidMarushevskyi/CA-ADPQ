package com.agreeya.chhs.exception;

import java.util.List;

/**
 * Top-level exception class for the REST services.
 * 
 */
public class WSException extends Exception {
	private String errorCode;
	private String exceptionMessage;
	private List<ErrorDetail> errorDetails;

	public WSException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.exceptionMessage = message;
	}

	public WSException(String errorCode, String message, List<ErrorDetail> errorDetails) {
		super(message);
		this.errorCode = errorCode;
		this.exceptionMessage = message;
		this.errorDetails = errorDetails;
	}

	public WSException(String errorCode, String message, List<ErrorDetail> errorDetails, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.exceptionMessage = message;
		this.errorDetails = errorDetails;
	}

	public WSException(String errorCode, String message, String exceptionMessage) {
		super(message);
		this.errorCode = errorCode;
		this.exceptionMessage = exceptionMessage;
	}

	public WSException(String errorCode, String exceptionMessage, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.exceptionMessage = exceptionMessage;
	}

	public WSException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public WSException(String errorCode, String exceptionMessage, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.exceptionMessage = exceptionMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public List<ErrorDetail> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(List<ErrorDetail> errorDetails) {
		this.errorDetails = errorDetails;
	}
}
