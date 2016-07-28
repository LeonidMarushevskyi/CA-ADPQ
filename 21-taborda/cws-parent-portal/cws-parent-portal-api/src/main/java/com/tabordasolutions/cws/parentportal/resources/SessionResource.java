package com.tabordasolutions.cws.parentportal.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.auth.SessionForm;
import com.tabordasolutions.cws.parentportal.services.SessionService;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("/session")
@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionResource {

    private SessionService sessionService;

    public SessionResource(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    
    @ApiOperation("Login and provide session to user")
    @UnitOfWork
    @POST
    public Session login(SessionForm form) {
        return sessionService.login(form.getEmail(), form.getPassword());
    }
}
