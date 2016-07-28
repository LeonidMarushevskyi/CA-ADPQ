package com.portlandwebworks.chhs.exceptions;

/**
 *
 * @author nick
 */
public class RemoteClientException extends RuntimeException {

	private final String responseBody;

	private final int status;
	
	public RemoteClientException(String msg, String responseBody, int status) {
		super(msg);
		this.responseBody = responseBody;
		this.status = status;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public int getStatus() {
		return status;
	}
	
}
