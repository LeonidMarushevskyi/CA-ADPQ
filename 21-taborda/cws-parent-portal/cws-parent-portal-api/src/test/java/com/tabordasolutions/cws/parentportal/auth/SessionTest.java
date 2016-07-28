package com.tabordasolutions.cws.parentportal.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SessionTest {
    private boolean validLogin = true;
    private boolean inValidLogin = false;
    String token = "token";
    String emptyToken = "";
    long userId = 4;

    @Test
    public void sessionSuccessfulyCreated(){
        Session session = new Session(validLogin, userId, token);
        assertTrue(session.isSuccess());
    }

    @Test
    public void sessionNotSuccessfulyCreated(){
        Session session = new Session(inValidLogin, userId, token);
        assertFalse(session.isSuccess());
    }

    @Test
    public void successfulSessionContainsToken(){
        Session session = new Session(validLogin, userId, token);
        assertNotNull(session.getToken());
        assertTrue("We expect a non-zero token to be set", session.getToken().length() > 0);
    }

    @Test
    public void unSuccessfulSessionDoesNotContainsToken(){
        Session session = new Session(inValidLogin, userId, token);
        assertEquals(session.getToken(), emptyToken);
    }

    @Test
    public void successfulSessionContainsUserId(){
        Session session = new Session(validLogin, userId, token);
        assertNotNull(session.getToken());
        assertEquals(session.getUserId(), userId);
    }

    @Test
    public void unSuccessfulSessionDoesNotContainsValidUserId(){
        Session session = new Session(inValidLogin, userId, token);
        assertEquals(session.getUserId(), -1);
    }
}