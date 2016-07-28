package com.tabordasolutions.cws.parentportal.api.response;

import com.tabordasolutions.cws.parentportal.api.User;

public class UserResponse {
	private User user;
	private boolean success;
	private String message;
	
	public UserResponse(User user, boolean success, String message) {
		super();
		this.user = user;
		this.success = success;
		this.message = message;
	}
	
	public UserResponse(User user, boolean success) {
		super();
		this.user = user;
		this.success = success;
		this.message = "SUCCESS";
	}

	public User getUser() {
		return user;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
