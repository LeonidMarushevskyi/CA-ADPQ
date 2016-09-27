package com.agreeya.chhs.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.agreeya.chhs.to.ErrorMessageTO;
import com.agreeya.chhs.util.WSConstants;

/**
 * Superclass for all the REST Response classes.
 * 
 * Contains the common status methods for setting the the status of the REST call. A value of '0' designates success.
 *@author AgreeYa Solutions
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WSResponse {

	protected String status = WSConstants.RESPONSE_OK; // default status is '0'
	protected String message;
	protected ErrorMessageTO error;

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatusAndMessage(String status, String message) {
		error = new ErrorMessageTO();
		error.setChhsErrorCode(status);
		error.setChhsErrorMessage(message);
		this.status = WSConstants.RESPONSE_ERROR;
	}

	public void setStatusAndMessage(String system, String status, String message) {
		error = new ErrorMessageTO();
		error.setChhsErrorCode(status);
		error.setChhsErrorMessage(message);
		this.status = WSConstants.RESPONSE_ERROR;
	}

	public ErrorMessageTO getError() {
		return error;
	}

	public void setError(ErrorMessageTO error) {
		this.error = error;
	}
}
