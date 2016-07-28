package com.tabordasolutions.cws.parentportal.services;

import java.util.*;

import com.tabordasolutions.cws.parentportal.api.ConversationDAO;
import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.MessageDAO;
import com.tabordasolutions.cws.parentportal.api.User;

public class MessageService {
    MessageDAO messageDAO;
    ConversationDAO conversationDAO;

    public MessageService(MessageDAO messageDAO, ConversationDAO conversaitonDAO) {
        this.messageDAO = messageDAO;
        this.conversationDAO = conversaitonDAO;
    }

    public Message save(Message message){
        conversationDAO.save(message.getConversation());
        return messageDAO.create(message);
    }

    public Message find(long id){
        Message message = messageDAO.find(id);
        return message != null ? message : new Message();
    }

    public List<Message> findByRecipient(User recipient){
        return messageDAO.findMessagesByRecipient(recipient);
    }

    public List<Message> findBySender(User sender){
        return messageDAO.findMessagesBySender(sender);
    }

    public List<Message> messagesFor(long userId){
        return null;
    }
}
