package com.portlandwebworks.chhs.messages;

import com.fasterxml.jackson.core.type.TypeReference;
import com.portlandwebworks.chhs.messages.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Component
@Scope("request")
@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

	private final Logger log = LoggerFactory.getLogger(MessageResource.class);
	private final MessageClient messageClient;

	@Autowired
	public MessageResource(MessageClient messageClient) {
		this.messageClient = messageClient;
	}

	@GET
	public List<Message> get() throws IOException {
		log.debug("Getting all messages");
		return messageClient.getMessages();
	}

	@POST
	public Response register(Message message) {
		log.info("Sending message.");
		messageClient.postMessage(message);
		return Response.ok(message).build();
	}

	@DELETE
	@Path("/{messageId}")
	public Response register(@PathParam("messageId") Integer messageId) {
		log.info("Deleting message {}.", messageId);
		messageClient.delete(messageId);
		return Response.ok().build();
	}

}
