package com.tabordasolutions.cws.parentportal.api.response;

import com.tabordasolutions.cws.parentportal.api.Conversation;

public class ConversationCreateResponse {
    private Conversation Conversation;
    private boolean success;

    public ConversationCreateResponse(Conversation conversation, boolean success) {
        Conversation = conversation;
        this.success = success;
    }
    public Conversation getConversation() {
        return Conversation;
    }

    public void setConversation(Conversation conversation) {
        Conversation = conversation;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
