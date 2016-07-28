package com.tabordasolutions.cws.parentportal.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.CreateConversationRequest;
import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.response.ConversationCreateResponse;
import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;

public class ConversationResourceTest {
    private ConversationResource resource;
    private String senderToken = "senderToken";
    private String receiverToken = "receiverToken";
    ConversationService mockedConversationService;
    Session session;
    UserService mockUserService;
    SessionService mockedSessionService;
    Conversation successfulConversation;
    Conversation unsavedConversation;
    User sender;
    User receiver;


    @Before
    public void setup(){
        sender = new User();
        sender.setId(7L);
        receiver = new User();
        receiver.setId(8L);
        unsavedConversation = new Conversation();
        successfulConversation = new Conversation();
        successfulConversation.setId(100);
        session = new Session(true, sender.getId(), senderToken);

        mockedConversationService = mock(ConversationService.class);
        when(mockedConversationService.save((Conversation)any(),(User)any(),(User)any())).thenReturn(successfulConversation);

        mockedSessionService = mock(SessionService.class);
        when(mockedSessionService.loginWithToken(senderToken)).thenReturn(session);
        when(mockedSessionService.getUserByToken(senderToken)).thenReturn(sender);
        when(mockedSessionService.getUserByToken(receiverToken)).thenReturn(receiver);
        when(mockedSessionService.getUser(receiver.getId())).thenReturn(receiver);

        mockUserService = mock(UserService.class);
        when(mockUserService.findUserById(sender.getId())).thenReturn(sender);
        when(mockUserService.findUserById(receiver.getId())).thenReturn(receiver);

        resource = new ConversationResource(mockedConversationService, mockedSessionService, mockUserService);
    }

    @Test
    public void listOfConverstaionsForRecipients(){
        resource.list("receiver", senderToken);
        verify(mockedConversationService).conversationsAsRecipients(sender);
    }

    @Test
    public void listOfConverstaionsForSenders(){
        resource.list("sender", senderToken);
        verify(mockedConversationService).conversationsAsSender(sender);
    }

    @Test
    public void createConversationIsSaved(){
        CreateConversationRequest request = new CreateConversationRequest(receiver.getId(), unsavedConversation.getSubject(), unsavedConversation.getInitializer());
        ConversationCreateResponse response = resource.create(senderToken, request);
        verify(mockedConversationService).save(unsavedConversation, sender, receiver);
        assertEquals("expect response to contain the saved conversation", successfulConversation, response.getConversation());
    }

    @Test
    public void successfulCreateConversationReturnsSuccess(){
        CreateConversationRequest request = new CreateConversationRequest(receiver.getId(), unsavedConversation.getSubject(), unsavedConversation.getInitializer());
        ConversationCreateResponse response = resource.create(senderToken, request);
        assertTrue("Expect response to indicate success", response.isSuccess());
    }

    @Test
    public void unsucessfulCreateConversationReturnsUnsuccess(){
        mockedConversationService = mock(ConversationService.class);
        when(mockedConversationService.save(unsavedConversation, sender, receiver)).thenReturn(unsavedConversation);
        resource = new ConversationResource(mockedConversationService, mockedSessionService, mockUserService);

        CreateConversationRequest request = new CreateConversationRequest(receiver.getId(), unsavedConversation.getSubject(), unsavedConversation.getInitializer());
        ConversationCreateResponse response = resource.create(senderToken, request);
        assertFalse("Expect status to indicate failure", response.isSuccess());
    }
    @Test
    public void createConversationReturnsSavedConverstaion(){
        Conversation conversation = new Conversation();
        when(mockedConversationService.save(conversation, sender, receiver)).thenReturn(conversation);
        CreateConversationRequest request = new CreateConversationRequest(receiver.getId(), unsavedConversation.getSubject(), unsavedConversation.getInitializer());
        ConversationCreateResponse response = resource.create(senderToken, request);
        assertEquals("Expect response to contain conversation", conversation, response.getConversation());
    }

    @Test
    public void findSavedConversation(){
        resource.show(sender.getId(), senderToken);
        verify(mockedConversationService).find(sender.getId(), sender);
    }
}
