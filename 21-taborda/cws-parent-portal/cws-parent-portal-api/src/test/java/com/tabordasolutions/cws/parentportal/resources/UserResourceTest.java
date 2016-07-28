package com.tabordasolutions.cws.parentportal.resources;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tabordasolutions.cws.parentportal.api.UserDAO;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.response.UserResponse;
import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;

public class UserResourceTest {
    private UserService service;
    private final User user = mock(User.class);
    private final UserService mockedService = mock(UserService.class);
    private final SessionService mockedSessonService = mock(SessionService.class);
    private final Session session = mock(Session.class);
    
    @Before
    public void setup() {
        UserDAO dao = mock(UserDAO.class);
        when(dao.find(1)).thenReturn(user);
        when(dao.findByUsername("joey.doe@example.com")).thenReturn(user);
        when(user.getEmail()).thenReturn("joey.doe@example.com");
        service = new UserService(dao);
        
        when(mockedService.updateUser(1, user)).thenReturn(user);
        when(mockedService.createUser((User)Mockito.anyObject())).thenReturn(1L);
        
        when(mockedSessonService.login(Mockito.anyString(), Mockito.anyString())).thenReturn(session);
        when(user.getEmail()).thenReturn("joey.doe@example.com");
        
    }

    @Test
    public void userReturnsUserResponse() {
        UserResource resource = new UserResource(service, mockedSessonService);
        assertTrue(resource.user(1) instanceof UserResponse);
    }
    
    @Test
    public void findUserByUserNameReturnsUserObject() {
    	UserResource resource = new UserResource(service, mockedSessonService);
    	assertEquals(user, resource.findUserByUserName("joey.doe@example.com"));
    }
    
    @Test
    public void updateUserReturnsUserResponse() {
    	UserResource resource = new UserResource(mockedService, mockedSessonService);
    	assertTrue(resource.updateUser(1, user) instanceof UserResponse);
    }
    
    @Test
    public void createUserReturnsSession() {
    	UserResource resource = new UserResource(mockedService, mockedSessonService);
    	User.Builder builder = new User.Builder();
		User user = builder.city("Sacramento").email("a@test.com")
				.firstName("John").lastName("Doe")
				.imageUrl("http:www.xyzabc.co").inCareOf("Baby Boy")
				.password("password").state("CA")
				.streetAddress1("123 Main Street").zip("90210").build();
		Object session = resource.createUser(user);
		assertNotNull(session);
		assertTrue(session instanceof Session);
    }
}