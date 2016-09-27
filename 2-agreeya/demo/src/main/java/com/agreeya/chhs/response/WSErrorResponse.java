package com.agreeya.chhs.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.agreeya.chhs.exception.ErrorDetail;
import com.agreeya.chhs.to.ErrorMessageTO;
import com.agreeya.chhs.util.WSConstants;

/**
 * Response TO for the representing an error in the REST call. The error could either be because of an input data validation failure, or an
 * internal server error.
 * @author AgreeYa Solutions
 */
@XmlRootElement(name = "ErrorResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class WSErrorResponse extends WSResponse {

	@XmlElementWrapper(name = "ErrorDetails")
	@XmlElement(name = "ErrorDetail")
	protected List<ErrorDetail> errorDetails;

	public WSErrorResponse() {

	}

	public WSErrorResponse(String errorCode, String message, List<ErrorDetail> errorDetails) {
		// this.status = errorCode;
		// this.message = message;
		this.errorDetails = errorDetails;
		error = new ErrorMessageTO();
		error.setChhsErrorCode(errorCode);
		error.setChhsErrorMessage(message);
		this.status = WSConstants.RESPONSE_ERROR;
	}

	public WSErrorResponse(String errorCode, String message) {
		// this.status = errorCode;
		// this.message = message;
		error = new ErrorMessageTO();
		error.setChhsErrorCode(errorCode);
		error.setChhsErrorMessage(message);
		this.status = WSConstants.RESPONSE_ERROR;
	}

	public WSErrorResponse(String system, String errorCode, String message, List<ErrorDetail> errorDetails) {
		this.errorDetails = errorDetails;
		error = new ErrorMessageTO();
		error.setSystem(system);
		error.setChhsErrorCode(errorCode);
		error.setChhsErrorMessage(message);
		this.status = WSConstants.RESPONSE_ERROR;
	}

	public WSErrorResponse(String system, String errorCode, String message) {
		error = new ErrorMessageTO();
		error.setSystem(system);
		error.setChhsErrorCode(errorCode);
		error.setChhsErrorMessage(message);
		this.status = WSConstants.RESPONSE_ERROR;
	}

	public List<ErrorDetail> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(List<ErrorDetail> errorDetails) {
		if (errorDetails != null && errorDetails.size() > 0) {
			this.errorDetails = errorDetails;
		}
	}

}
