package com.tabordasolutions.cws.parentportal.api.response;

import com.tabordasolutions.cws.parentportal.api.Conversation;

import java.util.List;

public class ConversationListResponse {
    List<Conversation> conversations;
    boolean succes;

    public ConversationListResponse(List<Conversation> conversations, boolean succes) {
        this.conversations = conversations;
        this.succes = succes;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }
}
