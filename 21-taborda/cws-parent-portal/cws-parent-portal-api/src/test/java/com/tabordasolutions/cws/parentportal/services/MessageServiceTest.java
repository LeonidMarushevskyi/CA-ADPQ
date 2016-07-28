package com.tabordasolutions.cws.parentportal.services;


import com.tabordasolutions.cws.parentportal.api.ConversationDAO;
import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.MessageDAO;
import com.tabordasolutions.cws.parentportal.api.User;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MessageServiceTest {

    MessageDAO mockMessageDao;
    ConversationDAO mockConverstaionDao;
    @Before
    public void setup(){
        mockMessageDao = mock(MessageDAO.class);
        mockConverstaionDao = mock(ConversationDAO.class);

    }
    @Test
    public void testSavingMessage() {
        Message message = new Message();
        MessageService service = new MessageService(mockMessageDao, mockConverstaionDao);
        service.save(message);
        verify(mockMessageDao).create(message);
    }

    @Test
    public void testFindingAMessage() {
        MessageService service = new MessageService(mockMessageDao, mockConverstaionDao);
        service.find(1);
        verify(mockMessageDao).find(1);
    }

    @Test
    public void testFindingMessagesByRecipent(){
        User user = new User();
        MessageService service = new MessageService(mockMessageDao, mockConverstaionDao);
        service.findByRecipient(user);
        verify(mockMessageDao).findMessagesByRecipient(user);
    }

    @Test
    public void testFindingMessagesBySender(){
        User user = new User();
        MessageService service = new MessageService(mockMessageDao, mockConverstaionDao);
        service.findBySender(user);
        verify(mockMessageDao).findMessagesBySender(user);
    }
}
