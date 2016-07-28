package com.tabordasolutions.cws.parentportal.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.ConversationDAO;
import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.MessageDAO;
import com.tabordasolutions.cws.parentportal.api.User;

public class ConversationService {
    private ConversationDAO conversationDao;
    private MessageDAO messageDAO;

    public ConversationService(ConversationDAO conversationDao, MessageDAO messageDAO) {
        this.conversationDao = conversationDao;
        this.messageDAO = messageDAO;
    }

    public List<Conversation> conversationsAsRecipients(User user){
        HashMap<Long, TreeSet<Message>> conversationsData = new HashMap<Long, TreeSet<Message>>();

        List<Message> recipients = messageDAO.findMessagesByRecipient(user);

        for(Message message : recipients){
            TreeSet<Message> messages = conversationsData.get(message.getConversation().getId());
            if( messages == null ) {
            	messages = new TreeSet<Message>();
            	conversationsData.put(message.getConversation().getId(), messages );
            }
            messages.add(message);
        }
        
        List<Conversation> conversations = new ArrayList<Conversation>();
        
        for(Long id : conversationsData.keySet()) {
        	TreeSet<Message> messages = conversationsData.get(id);
        	Message baseMessage = messages.first();
        	if(baseMessage.getRecipient().equals(user)) {
	        	Conversation conversation = baseMessage.getConversation();
	        	conversation.updateMessage(baseMessage);
	        	conversations.add(conversation);
        	}
        }

        return conversations;
    }

    public List<Conversation> conversationsAsSender(User user){
        HashMap<Long, TreeSet<Message>> conversationsData = new HashMap<Long, TreeSet<Message>>();

        List<Message> senders = messageDAO.findMessagesBySender(user);

        for(Message message : senders){
            TreeSet<Message> messages = conversationsData.get(message.getConversation().getId());
            if( messages == null ) {
            	messages = new TreeSet<Message>();
            	conversationsData.put(message.getConversation().getId(), messages );
            }
            messages.add(message);
        }
        
        List<Conversation> conversations = new ArrayList<Conversation>();
        
        for(Long id : conversationsData.keySet()) {
        	TreeSet<Message> messages = conversationsData.get(id);
        	Message baseMessage = messages.first();
        	if(baseMessage.getAuthor().equals(user)) {
	        	Conversation conversation = baseMessage.getConversation();
	        	conversation.updateMessage(baseMessage);
	        	conversations.add(conversation);
        	}
        }

        return conversations;
    }

    public Conversation save(Conversation conversation, User sender, User recipient){
        conversation.setDateCreated(new Date());
        Message message = createInitialMessageForConversation(conversation, sender, recipient);
        conversation = addMessagePropertiesToConversation(conversation,message);
        Conversation savedConversation = conversationDao.create(conversation);
        messageDAO.create(message);
        return savedConversation;
    }

    private Message createInitialMessageForConversation(Conversation conversation, User sender, User recipient){
        Message message = new Message();
        message.setAuthor(sender);
        message.setRecipient(recipient);
        message.setDateCreated(conversation.getDateCreated());
        message.setSubject(conversation.getSubject());
        message.setBody(conversation.getInitializer());
        message.setConversation(conversation);
        return message;
    }

    private Conversation addMessagePropertiesToConversation(Conversation conversation, Message message){
        conversation.setSender(message.getAuthor().getFullName());
        conversation.setReceiver(message.getRecipient().getFullName());
        conversation.setSubject(message.getSubject());
        conversation.setBaseMessage(message);
        conversation.setInitializer(message.getAuthor().getFullName());
        return conversation;
    }

    public Conversation find(long id, User user ){
        Conversation conversation = conversationDao.find(id);
        if (conversation == null) { return new Conversation(); }
        Message baseMessage = messageDAO.findByConversation(conversation);
        conversation.setBaseMessage(baseMessage);

        if (conversationContainsUser(user, conversation)) {
            addMessagePropertiesToConversation(conversation, conversation.getBaseMessage());
        }else{
            conversation = new Conversation();
        }

        return conversation;
    }

    private boolean conversationContainsUser(User user, Conversation conversation){
        if (user == null || conversation == null) { return false; }
        if (conversation.getBaseMessage() == null) { return false; }
        return matchesUser(user, conversation.getBaseMessage().getAuthor()) || matchesUser(user, conversation.getBaseMessage().getRecipient());
    }
    private boolean matchesUser(User user, User messenger){
        if ( user == null || messenger == null) { return false; }
        if ( user.getId() == null || user.getId() <= 0 || messenger.getId() <= 0) { return false; }
        return user.getId() == messenger.getId();
    }
}
