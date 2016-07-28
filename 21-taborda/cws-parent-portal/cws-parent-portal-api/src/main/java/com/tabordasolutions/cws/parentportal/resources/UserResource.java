package com.tabordasolutions.cws.parentportal.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.response.UserResponse;
import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.services.ServicesException;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;

@Api("/users")
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	public static final Logger LOGGER = LoggerFactory
			.getLogger(UserResource.class);
	private UserService userService;
	private SessionService sessionService;
	
	public UserResource(UserService userService, SessionService sessionService) {
		this.userService = userService;
		this.sessionService = sessionService;
	}

	@ApiOperation("Find current logged in user based on session id in X-Auth-Token Header")
	@UnitOfWork
	@Path("/me")
	@GET
	public UserResponse me(@HeaderParam("X-Auth-Token") @ApiParam(required=true, value = "User session token")  String token) {
		Session session = sessionService.loginWithToken(token);
		return new UserResponse(userService.findUserById(session.getUserId()), true);
	}
	
	@ApiOperation("Find user by id")
	@UnitOfWork
	@Path("/{id}")
	@GET
	public UserResponse user(@PathParam("id")@ApiParam(required=true, value="id of the user") long id) {
		return new UserResponse(userService.findUserById(id), true);
	}

	@ApiOperation("Find user by username")
	@UnitOfWork
	@Path("/username/{username}")
	@GET
	public User findUserByUserName(@PathParam("username") @ApiParam(required=true, value="email address of the user") String username) {
		return userService.findUserByUserName(username);
	}

	@ApiOperation("Update user by given id")
	@UnitOfWork
	@Path("/{id}")
	@PUT
	public UserResponse updateUser(@PathParam("id") @ApiParam(required=true, value="id of the user")long id, User user) {
		try {
			return new UserResponse(userService.updateUser(id, user), true);
		} catch (ServicesException e) {
			//TODO : Handle through ExcpetionMapper
			LOGGER.error("Unable to update " + user, e);
			return new UserResponse(user, false, e.getMessage());
		}
	}

	@ApiOperation("Update current logged in user based on session id in X-Auth-Token Header")
	@UnitOfWork
	@Path("/me")
	@PUT
	public UserResponse updateMe(@HeaderParam("X-Auth-Token") @ApiParam(required=true, value = "User session token") String token, @ApiParam(required=true, value="The updated user")User user) {
		Session session = sessionService.loginWithToken(token);
		try {
			user = userService.updateUser(session.getUserId(), user);
			return new UserResponse(user, true);
		} catch (ServicesException e) {
			//TODO : Handle through ExceptionMapper
			LOGGER.error("Unable to update " + user, e);
			return new UserResponse(user, false, e.getMessage());
		}
	}
	
	@ApiOperation("Create User")
	@UnitOfWork
	@Path("/")
	@POST
	public Object createUser(@ApiParam(required=true, value="The user to create")User user) {
		
		try {
			long newId = userService.createUser(user);
			
			User.Builder builder = new User.Builder();
			User newUser = builder.id(newId)
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.inCareOf(user.getInCareOf())
				.streetAddress1(user.getStreetAddress1())
				.streetAddress2(user.getStreetAddress2())
				.state(user.getState())
				.city(user.getCity())
				.zip(user.getZip())
				.imageUrl(user.getImageUrl())
				.email(user.getEmail().toLowerCase())
				.password(user.getPassword())
				.build();
			
			return sessionService.login(newUser.getEmail(), newUser.getPassword());
		} catch (ServicesException e) {
			//TODO : Handle through ExcpetionMapper
			LOGGER.error("Unable to create: " + user.toString(), e);
			return new UserResponse(user, false, e.getMessage());
		}
	}
}
