package com.agreeya.chhs.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.agreeya.chhs.util.Constants.ErrorType;

/**
 * Top level exception class for the various services in CHHS application.
 * @author AgreeYa Solutions
 * 
 */
public class CHHSException extends RuntimeException {

	private String errorCode;
	private String exceptionMessage;
	private List<ErrorDetail> errorDetails = new ArrayList<ErrorDetail>();
	private ErrorType errorType = ErrorType.EXCEPTION;

	public CHHSException() {
	}

	public CHHSException(String message) {
		super(message);
	}

	public CHHSException(Throwable cause) {
		super(cause);
	}

	public CHHSException(String message, Throwable cause) {
		super(message, cause);
	}

	public CHHSException(String errorCode, String exceptionMessage) {
		super(errorCode);
		this.errorCode = errorCode;
		this.exceptionMessage = exceptionMessage;
	}

	public CHHSException(String errorCode, String exceptionMessage, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.exceptionMessage = exceptionMessage;
	}

	public CHHSException(String errorCode, String exceptionMessage, ErrorDetail errorDetails) {
		this(errorCode, exceptionMessage, Arrays.asList(errorDetails));
	}

	public CHHSException(String errorCode, String exceptionMessage, List<ErrorDetail> errorDetails) {
		super(errorCode);
		this.errorCode = errorCode;
		this.exceptionMessage = exceptionMessage;
		this.errorDetails = errorDetails;
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

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}
}
