package com.tabordasolutions.cws.parentportal.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.CreateMessageRequest;
import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.response.MessageResponse;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.MessageService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;

@Api("/message")
@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	public static final Logger LOGGER = LoggerFactory
			.getLogger(MessageResource.class);
	
    private String RECEIVER = "receiver";
    private MessageService messageService;
    private ConversationService conversationService;
    private SessionService sessionService;
    @SuppressWarnings("unused")
	private UserService userService;

    public MessageResource() { }

    public MessageResource(MessageService messageService, ConversationService conversationService, SessionService sessionService, UserService userService) {
        this.messageService = messageService;
        this.conversationService = conversationService;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @ApiOperation("Create a message")
    @UnitOfWork
    @Path("/")
    @POST
    public MessageResponse create(@HeaderParam("X-Auth-Token") String token, CreateMessageRequest createRequest){
        Conversation conversation = conversationService.find(createRequest.getConversationId(),sessionService.getUserByToken(token));
        Message message = createRequest.buildMessage(sessionService.getUserByToken(token) , sessionService.getUser(createRequest.getReceiver()), conversation);
        log("received request to create message for: " + message);
        log("creating message for conversation" + conversation);
        Message createdMessage = messageService.save(message);
        log("saved message to conversation" + conversation);
        boolean success = createdMessage.getId() > 0 ? true : false;
        return new MessageResponse(createdMessage, success );
    }

    @ApiOperation("Get message by id")
    @UnitOfWork
    @GET
    @Path("/{id}")
    public MessageResponse show(@PathParam("id") long id, @HeaderParam("X-Auth-Token" )String token){
        Message message =  messageService.find(id);
        return new MessageResponse(message, message.getId() > 0 );
    }

    @ApiOperation("List messages")
    @UnitOfWork
    @Path("/")
    @GET
    public List<Message> list(@QueryParam("select") @ApiParam(value="Messages for?", required = false, defaultValue="sender" ) String type, @HeaderParam("X-Auth-Token") String token){
        if (type == RECEIVER){
            return messageService.findByRecipient(sessionService.getUserByToken(token));

        }else{
            return messageService.findBySender(sessionService.getUserByToken(token));
        }
    }
    private void log(String message){
        LOGGER.debug(message);
    }
}
