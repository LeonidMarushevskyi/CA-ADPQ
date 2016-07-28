package com.tabordasolutions.cws.parentportal.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/application")
@Produces(MediaType.APPLICATION_JSON)
public class ParentPortalResource {
	
	private String applicationName;
	
	public ParentPortalResource(String applicationName) {
		this.applicationName = applicationName;
	}
	
	@GET
	public String getApplicationName() {
		return applicationName;
	}
}
