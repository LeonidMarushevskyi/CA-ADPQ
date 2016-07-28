package com.tabordasolutions.cws.parentportal.api;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class ConversationSerializerTest {
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    CreateConversationSerializer serializer;
    JsonGenerator jsonGenerator;
    StringWriter stringWriter;
    Conversation conversation;
    String subject;
    Message originalMessage ;
    Date createDate ;
    Date modifiedDate ;
    User parent;
    User caseworker;

    @Before
    public void setup() throws IOException {
        serializer = new CreateConversationSerializer();
        stringWriter = new StringWriter();
        jsonGenerator = new JsonFactory().createGenerator(stringWriter);

        parent = new User();
        parent.setFirstName("Fred");
        parent.setLastName("Flinstone");
        caseworker = new User();
        caseworker.setFirstName("Barney");
        caseworker.setLastName("Rubble");

        subject = "Subject line";
        originalMessage = new Message(1, new Date(), parent, caseworker, subject, "the body");
        createDate = new Date();
        modifiedDate = new Date();
        conversation = new Conversation();
        conversation.setBaseMessage(originalMessage);
        Set<Message> messages = new HashSet<Message>();
        messages.add(originalMessage);
        conversation.setMessages(messages);
        conversation.setDateCreated(createDate);
        conversation.setDateUpdated(modifiedDate);
        conversation.setInitializer(parent.getFullName());
        conversation.setReceiver(caseworker.getFullName());
        conversation.setSender(parent.getFullName());
        conversation.setRead(true);
        conversation.setSubject(subject);
    }

    public Conversation buildConversation(){
        User sender = new User();
        User receiver = new User();
        conversation = new Conversation();
        Message message = new Message();
        message.setAuthor(sender);
        message.setAuthor(receiver);
        conversation.getMessages().add(message);
        return conversation;
    }

    @Test
    public void testSerializesEmptyConversation() throws IOException {
        Conversation conversation = buildConversation();
        serializer.serialize(conversation, jsonGenerator, null);
        jsonGenerator.flush();
        String json = stringWriter.toString();
        assertTrue("Expected json to contain intializer value", json.contains("\"initializer\":null,"));
        assertTrue("Expected json to contain sender value",  json.contains("\"sender\":null"));
        assertTrue("Expected json to contain receiver value",  json.contains("\"receiver\":null"));
        assertTrue("Expected json to contain date value",  json.contains("\"date\":\""));
        assertTrue("Expected json to contain update_date value",  json.contains("\"update_date\":\""));
        assertTrue("Expected json to contain subject value",  json.contains("\"subject\":null"));
        assertTrue("Expected json to contain init_message value",  json.contains("\"init_message\":null"));
        assertTrue("Expected json to contain read value",  json.contains("\"read\":\"false\""));
        assertTrue("Expected json to contain messages",  json.contains("\"messages\":[]"));
    }

    //@Test
    public void testSerializesConversation() throws IOException {

        serializer.serialize(conversation, jsonGenerator, null);
        jsonGenerator.flush();
        String json = stringWriter.toString();

        assertTrue("Expected json to contain intializer value", json.contains("\"initializer\":\"" + parent.getFullName() + "\""));
        assertTrue("Expected json to contain sender value",  json.contains("\"sender\":\"" + parent.getFullName() + "\""));
        assertTrue("Expected json to contain receiver value",  json.contains("\"receiver\":\"" + caseworker.getFullName() + "\""));
        assertTrue("Expected json to contain date value",  json.contains("\"date\":\"" + df.format(createDate)+ "\""));
        assertTrue("Expected json to contain update_date value",  json.contains("\"update_date\":\"" + df.format(modifiedDate) + "\""));
        assertTrue("Expected json to contain subject value",  json.contains("\"subject\":\"" + subject +"\""));
        assertTrue("Expected json to contain init_message value",  json.contains("\"init_message\":\"" + originalMessage.getBody() + "\""));
        assertTrue("Expected json to contain read value",  json.contains("\"read\":\"true\""));
        assertTrue("Expected json to contain messages",  json.contains("\"messages\":["));
    }
}