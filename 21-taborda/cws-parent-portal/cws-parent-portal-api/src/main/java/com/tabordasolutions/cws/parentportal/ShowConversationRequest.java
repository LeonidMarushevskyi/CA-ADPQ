package com.tabordasolutions.cws.parentportal;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;

public class ShowConversationRequest {
    @PathParam("foo")
    private long id;
    @HeaderParam("X-Auth-Token")
    private String token;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
