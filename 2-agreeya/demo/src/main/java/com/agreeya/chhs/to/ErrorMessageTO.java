package com.agreeya.chhs.to;

/**
 * Class used by the REST layer to transmit any error messages that are to be sent to the client. This class encapsulates an 'CHHS'-
 * specific error message instead of the raw error messages that might have been received from any external system.
 * 
 * @author AgreeYa Solutions
 */
public class ErrorMessageTO {

	private String system = "CHHS-Dev";
	private String chhsErrorCode;
	private String chhsErrorMessage;

	public ErrorMessageTO() {

	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getChhsErrorCode() {
		return chhsErrorCode;
	}

	public void setChhsErrorCode(String chhsErrorCode) {
		this.chhsErrorCode = chhsErrorCode;
	}

	public String getChhsErrorMessage() {
		return chhsErrorMessage;
	}

	public void setChhsErrorMessage(String chhsErrorMessage) {
		this.chhsErrorMessage = chhsErrorMessage;
	}

}
