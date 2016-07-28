package com.tabordasolutions.cws.parentportal.api;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testFullName(){
        String firstName = "Fred";
        String lastName = "Flinstone";
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        assertEquals("Expect full name to be first and last name", firstName + " " + lastName, user.getFullName());

    }

}