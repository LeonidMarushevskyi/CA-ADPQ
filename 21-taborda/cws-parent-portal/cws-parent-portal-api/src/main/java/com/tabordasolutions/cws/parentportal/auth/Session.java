package com.tabordasolutions.cws.parentportal.auth;


public class Session {

    private boolean success;
    private long userId;
    private String token;

    public Session(boolean success, long userId, String token) {
        this.success = success;
        this.userId = success ? userId : -1;
        this.token = success ? token : "";
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() { return token; }

    public long getUserId() { return userId; }
}
