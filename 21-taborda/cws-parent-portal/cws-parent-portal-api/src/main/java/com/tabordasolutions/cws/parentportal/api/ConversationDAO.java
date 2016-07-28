package com.tabordasolutions.cws.parentportal.api;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class ConversationDAO extends AbstractDAO<Conversation> {

    public ConversationDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Conversation create(Conversation conversation){
        return persist(conversation);
    }
    public Conversation find(long id){
        return get(id);
    }

    public void evictConversation(Conversation conversation){
        if(conversation != null){
            currentSession().evict(conversation);
        }

    }
    public Conversation save(Conversation conversation){
        return persist(conversation);
    }
}
