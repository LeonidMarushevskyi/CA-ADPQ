package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.UserDAO;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService service;
    private final User user = mock(User.class);
    private final User user2 = mock(User.class);
    private final User user3 = mock(User.class);

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Before
    public void setup(){
        UserDAO dao = mock(UserDAO.class);
        when(dao.find(1)).thenReturn(user);
        when(dao.find(2)).thenReturn(user2);
        when(dao.findByUsername("joey.doe@example.com")).thenReturn(user);
        when(dao.update(user)).thenReturn(user);
        when(dao.create(user2)).thenReturn(2L);
        when(dao.create(user3)).thenReturn(3L);
        service = new UserService(dao);
        
        when(user.getEmail()).thenReturn("joey.doe@example.com");
        when(user.getId()).thenReturn(1L);
        when(user2.getEmail()).thenReturn("joey.doe@example.com");
        when(user3.getEmail()).thenReturn("unique.email");
        when(user3.getId()).thenReturn(3L);
    }

    @Test
    public void findUserByIdReturnsUser(){
        assertEquals(user, service.findUserById(1));
    }

    @Test
    public void findUserByUsernameReturnsUser(){
        assertEquals(user, service.findUserByUserName("joey.doe@example.com"));
    }
    
    @Test
    public void updateUserReturnsUser() {
    	User returnedUser = service.updateUser(1, user);
    	assertNotNull(returnedUser);
    	assertEquals(returnedUser, user);
    }
    
    @Test
    public void updateNonExistingUserThrowsException() {
        thrown.expect(ServicesException.class);
        thrown.expectMessage("does not exist");
         
    	service.updateUser(99, user);
    }
    
    @Test
    public void updateNonUniqueEmailUserThrowsException() {
        thrown.expect(ServicesException.class);
        thrown.expectMessage("Email not unique");
         
    	service.updateUser(2, user2);
    }

    @Test
    public void createUseReturnsUser() {
    	long id = service.createUser(user3);
    	assertEquals(0, id);
    }
    
    @Test
    public void createAlreadyExistingUserThrowsException() {
        thrown.expect(ServicesException.class);
        thrown.expectMessage("Email not unique");
         
    	service.createUser(user2);
    }
}