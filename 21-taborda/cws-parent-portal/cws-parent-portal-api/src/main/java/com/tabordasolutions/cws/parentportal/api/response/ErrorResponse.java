package com.tabordasolutions.cws.parentportal.api.response;

public class ErrorResponse {
	private String message;
	private final boolean success = false;
	
	public ErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}
}
