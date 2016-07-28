package com.tabordasolutions.cws.parentportal.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

public class ConversationDAOTest {
    private SessionFactory sessionFactory;
    private ConversationDAO dao;

    @Before
    public void setup(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();
        dao = new ConversationDAO(sessionFactory);
    }

    @After
    public void teardown() {sessionFactory.close();}

    //@Test
    public void saveConversation() throws Exception{
        Conversation savedConversation = dao.create(buildConversation());
        assertTrue("Expected conversation to be greater than 0", savedConversation.getId() > 0);
    }

    //@Test
    public void findConversation() throws Exception{
        Conversation savedConversation = dao.create(buildConversation());
        Conversation foundConversation = dao.find(savedConversation.getId());
        assertEquals("Expected to find the previously saved conversation", savedConversation.getId(), foundConversation.getId());
    }

    private Conversation buildConversation(){
        Message message = new Message(0L, new Date(), new User(), new User(), "String subject", "String body");
        Conversation conversation = new Conversation();
        conversation.setDateCreated(new Date());
        conversation.setRead(false);
        conversation.setReceiver("fred");
        conversation.setSender("barney");
        conversation.setSubject("A message");
        conversation.setBaseMessage(message);
        return conversation;
    }
}