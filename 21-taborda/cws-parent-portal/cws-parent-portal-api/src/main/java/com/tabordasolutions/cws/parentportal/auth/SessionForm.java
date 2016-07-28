package com.tabordasolutions.cws.parentportal.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class SessionForm {
    @JsonProperty
    @NotEmpty
    protected String email;
    @JsonProperty
    @NotEmpty
    private String password;

    public SessionForm() {
    }

    public SessionForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
