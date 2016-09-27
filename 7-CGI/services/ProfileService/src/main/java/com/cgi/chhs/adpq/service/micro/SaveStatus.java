package com.cgi.chhs.adpq.service.micro;

public class SaveStatus {

	private boolean success;
	private String message;
	private Long savedObjectId;
	
	public Long getSavedObjectId() {
		return savedObjectId;
	}
	public void setSavedObjectId(Long savedObjectId) {
		this.savedObjectId = savedObjectId;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
