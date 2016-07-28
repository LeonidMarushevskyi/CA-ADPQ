package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ConversationServiceTest {
    ConversationService service;
    ConversationDAO mockedConversationDao;
    MessageDAO mockMessageDao;
    Conversation mockedConversation;
    Message message;
    User sender;
    User receiver;
    long id = 1;

    @Before
    public void setup(){
        mockedConversationDao = mock(ConversationDAO.class);
        mockMessageDao = mock(MessageDAO.class);
        service = new ConversationService(mockedConversationDao, mockMessageDao);
        sender = new User();
        receiver = new User();
        mockedConversation = mock(Conversation.class);
        when(mockedConversationDao.find(id)).thenReturn(mockedConversation);
        message = new Message();
        message.setConversation(mockedConversation);
        message.setAuthor(new User());
        message.setRecipient(new User());
        when(mockedConversation.getBaseMessage()).thenReturn(message);
    }


    @Test
    public void testSavingConversation(){
        Conversation conversation = new Conversation();
        service.save(conversation, sender, receiver);
        verify(mockedConversationDao).create(conversation);
    }

    @Test
    public void testFindingConversation(){
        service.find(id, sender);
        verify(mockedConversationDao).find(id);
    }

}