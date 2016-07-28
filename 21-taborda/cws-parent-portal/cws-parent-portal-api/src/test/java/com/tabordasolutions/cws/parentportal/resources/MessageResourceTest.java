package com.tabordasolutions.cws.parentportal.resources;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.MessageService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;

public class MessageResourceTest {
    MessageResource resource;
    MessageService mockMessageService;
    ConversationService mockConversationService;
    UserService mockUserService;
    SessionService mockSessionService;
    Message unsavedMessage;
    Message savedMessage;

    @Before
    public void setup(){
        mockMessageService = mock(MessageService.class);
        resource = new MessageResource(mockMessageService, mockConversationService, mockSessionService,mockUserService);
        unsavedMessage = buildMessage();
        savedMessage = buildMessage();
        savedMessage.setId(8);
        when(mockMessageService.save(unsavedMessage)).thenReturn(savedMessage);
        when(mockMessageService.find(1)).thenReturn(savedMessage);
    }


    @Test
    public void testShowingAMessage(){
        resource.show(1, "mytoken");
        verify(mockMessageService).find(1);
    }

    private Message buildMessage(){
        return new Message(0L, new Date(), new User(), new User(), "String subject", "String body");
    }
}