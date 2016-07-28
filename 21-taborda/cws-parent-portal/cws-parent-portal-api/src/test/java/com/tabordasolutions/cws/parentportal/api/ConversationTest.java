package com.tabordasolutions.cws.parentportal.api;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ConversationTest {
    private Conversation conversation;
    private User parent;
    private User caseworker;

    @Before
    public void setup(){
        parent = new User();
        parent.setFirstName("Fred");
        parent.setLastName("Flinstone");
        caseworker = new User();
        caseworker.setFirstName("Barney");
        caseworker.setLastName("Rubble");

        conversation = new Conversation();
    }

    @Test
    public void testAllAttributesExist(){
        String subject = "Subject line";
        Date originalMessageDate = new Date(System.currentTimeMillis() - 10000);
        Message originalMessage = new Message(1, originalMessageDate, caseworker, parent, subject, "the body");
        Date createDate = new Date();
        Date modifiedDate = new Date();
        String initializer = "Fred";
        String sender = "Barney";
        String receiver = "Fred";
        Set<Message> messages =  new TreeSet<Message>(Arrays.asList(new Message(2, new Date(),parent,caseworker, subject, "another body"), new Message(3, new Date(), caseworker, parent, subject, "some other body")));

        conversation.setBaseMessage(originalMessage);
        conversation.setDateCreated(createDate);
        conversation.setDateUpdated(modifiedDate);
        conversation.setInitializer(initializer);
        conversation.setReceiver(receiver);
        conversation.setSender(sender);
        conversation.setMessages(new HashSet<Message>(messages));
        conversation.setRead(true);
        conversation.setSubject(subject);
       // assertEquals(originalMessage, conversation.getBaseMessage());
        assertEquals(createDate, conversation.getDateCreated());
        assertEquals(modifiedDate, conversation.getDateUpdated());
        assertEquals(initializer, conversation.getInitializer());
        assertEquals(receiver, conversation.getReceiver());
        assertEquals(sender, conversation.getSender());
        assertEquals(messages, conversation.getMessages());
        assertEquals(subject, conversation.getSubject());
        assertEquals(true, conversation.isRead());
    }
}