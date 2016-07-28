package com.tabordasolutions.cws.parentportal.resources;

import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.auth.SessionForm;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SessionResourceTest {
    private SessionResource resource;
    private SessionForm form;
    private final Session session = mock(Session.class);

    @Before
    public void setup() {
        form = new SessionForm("joey.doe@example.com", "password1");
        SessionService service = mock(SessionService.class);
        when(service.login("joey.doe@example.com", "password1")).thenReturn(session);
        resource = new SessionResource(service);
    }

    @Test
    public void loginReturnsSession() {
        assertEquals(session, resource.login(form));
    }
}