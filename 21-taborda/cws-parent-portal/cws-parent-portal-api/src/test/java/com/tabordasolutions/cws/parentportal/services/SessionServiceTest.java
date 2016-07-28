package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.auth.Cryptography;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SessionServiceTest {
    private SessionService service;
    private String username = "joey.doe@example.com";
    private String encryptedToken = "myEncyptedToken";
    private String decryptedToken = username + ":password";
    private String badToken = "myHackeToken";
    private Cryptography crypto;

    @Before
    public void setup(){
        UserService service = mock(UserService.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(service.findUserByUserName(username)).thenReturn(user);

        crypto = mock(Cryptography.class);
        when(crypto.decrypt(badToken)).thenReturn("joey.doe@example.com:BOGUS");
        when(crypto.decrypt(encryptedToken)).thenReturn(decryptedToken);
        when(crypto.encrypt(decryptedToken)).thenReturn(encryptedToken);
        when(user.getPassword()).thenReturn("password");
        this.service = new SessionService(service, crypto);
    }

    @Test
    public void loginWithValidCredentialsReturnsValidSession(){
        assertTrue(service.login("joey.doe@example.com", "password").isSuccess());
    }

    @Test
    public void loginWithValidCredentialsReturnsSessionWithUserId(){
        assertEquals(1, service.login("joey.doe@example.com", "password").getUserId());
    }

    @Test
    public void loginWithInvalidUserReturnsInvalidSession(){
        assertFalse(service.login("bogus@example.com", "password1").isSuccess());
    }

    @Test
    public void loginWithInvalidPasswordReturnsInvalidSession(){
        assertFalse(service.login("joey.doe@example.com", "BOGUS").isSuccess());
    }

    @Test
    public void loginWithValidToken(){ 
    	assertTrue(service.loginWithToken(encryptedToken).isSuccess()); 
    	}
    
    

    @Test
    public void loginWithInValidToken(){ assertFalse(service.loginWithToken(badToken).isSuccess()); }
}