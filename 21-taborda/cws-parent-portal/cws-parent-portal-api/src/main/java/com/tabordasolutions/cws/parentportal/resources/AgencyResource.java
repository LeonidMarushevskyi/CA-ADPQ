package com.tabordasolutions.cws.parentportal.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tabordasolutions.cws.parentportal.api.Agency;


@Path("/agencies")
@Produces(MediaType.APPLICATION_JSON)
public interface AgencyResource {
	@GET
	public List<Agency> listAgencies(@QueryParam("zipcode") String zipcode);
}
