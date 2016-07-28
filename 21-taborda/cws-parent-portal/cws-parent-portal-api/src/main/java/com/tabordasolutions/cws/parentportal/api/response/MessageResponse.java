package com.tabordasolutions.cws.parentportal.api.response;

import com.tabordasolutions.cws.parentportal.api.Message;

public class MessageResponse {
    private Message message;
    private boolean success;

    public MessageResponse(Message message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
