package com.tabordasolutions.cws.parentportal.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;

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
import com.tabordasolutions.cws.parentportal.api.CreateConversationRequest;
import com.tabordasolutions.cws.parentportal.api.response.ConversationCreateResponse;
import com.tabordasolutions.cws.parentportal.api.response.ConversationListResponse;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;

@Api("/conversation")
@Path("/conversation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConversationResource {
	public static final Logger LOGGER = LoggerFactory
			.getLogger(ConversationResource.class);
	
    private String SENDER = "sender";
    private ConversationService conversationService;
    private SessionService sessionService;
    @SuppressWarnings("unused")
	private UserService userService;

    public ConversationResource(ConversationService service, SessionService sessionService, UserService userService) {
        this.conversationService = service;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @UnitOfWork
    @Path("/")
    @POST
    public ConversationCreateResponse create(@HeaderParam("X-Auth-Token") String token, CreateConversationRequest createRequest){
        Conversation conversation = new Conversation();
        conversation.setInitializer(createRequest.getMessage());
        conversation.setSubject(createRequest.getSubject());
        log("received request to create conversation for: " + conversation);

        Conversation createdConversation = conversationService.save(conversation, sessionService.getUserByToken(token), sessionService.getUser(createRequest.getReceiverId()));
        log("Saved conversation for request: " + conversation);
        return new ConversationCreateResponse(createdConversation, isConversationSaved(createdConversation) );
    }

    @UnitOfWork
    @Path("/{id}")
    @GET
    public ConversationCreateResponse show(@PathParam("id") long id, @HeaderParam("X-Auth-Token" )String token){
        Conversation conversation = conversationService.find(id, sessionService.getUserByToken(token));
        return new ConversationCreateResponse(conversation, isConversationSaved(conversation) );
    }

    private boolean isConversationSaved(Conversation conversation){
        return conversation != null && conversation.getId() > 0;
    }

    @UnitOfWork
    @Path("/")
    @GET
    public ConversationListResponse list(@QueryParam("select")String type, @HeaderParam("X-Auth-Token") String token){
        List <Conversation>conversations;
        if (type.equals(SENDER)){
            conversations = conversationService.conversationsAsSender(sessionService.getUserByToken(token));
        }else{
            conversations = conversationService.conversationsAsRecipients(sessionService.getUserByToken(token));
        }

        return new ConversationListResponse(conversations, true);
    }

    private void log(String message){
        LOGGER.debug(message);
    }
}
