package com.cgi.chhs.adpq.service.micro;

public class ServiceHealth {
	
	public String status;

	public ServiceHealth(String status)
	{
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
