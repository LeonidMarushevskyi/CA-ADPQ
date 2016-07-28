package com.tabordasolutions.cws.parentportal.api;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class UserSerializerTest {
    private UserSerializer serializer;
    private JsonGenerator jsonGenerator;
    private StringWriter stringWriter;
    private User user;
    private String firstName;
    private String lastName;
    private String inCareOf;
    private String streetAddress1;
    private String streetAddress2;
    private String state;
    private String city;
    private String zip;

    private String imageUrl;
    private String email;
    private String password;

    @Before
    public void setup() throws IOException {
        serializer = new UserSerializer();
        stringWriter = new StringWriter();
        jsonGenerator = new JsonFactory().createGenerator(stringWriter);

        firstName = "Fred`";
        lastName = "Flinstone";
        inCareOf = "BAM BAM";
        streetAddress1 = "1 main st";
        streetAddress2 = "" ;
        state = "AZ";
        city = "Bedrock";
        zip = "11111";
        imageUrl = "http://someurl.com";
        email = "fred@foo.comx";
        password = "password";

        user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setInCareOf(inCareOf);
        user.setStreetAddress1(streetAddress1);
        user.setStreetAddress2(streetAddress2);
        user.setCity(city);
        user.setState(state);
        user.setZip(zip);
        user.setImageUrl(imageUrl);
        user.setEmail(email);
        user.setPassword(password);
    }
    @Test
    public void testSerializesEmptyConversation() throws IOException {
        user = new User();
        serializer.serialize(user, jsonGenerator, null);
        jsonGenerator.flush();
        String json = stringWriter.toString();
        assertTrue("Expected json to contain name value",  json.contains("\"name\":{"));
        assertTrue("Expected json to contain first_name value",  json.contains("\"first_name\":null"));
        assertTrue("Expected json to contain last_name value",  json.contains("\"last_name\":null"));
        assertTrue("Expected json to contain address value",  json.contains("\"address\":{"));
        assertTrue("Expected json to contain street_address value",  json.contains("\"street_address\":null"));
        assertTrue("Expected json to contain street_address_2 value",  json.contains("\"street_address_2\":null"));
        assertTrue("Expected json to contain state value",  json.contains("\"state\":null"));
        assertTrue("Expected json to contain city value",  json.contains("\"city\":null"));
        assertTrue("Expected json to contain zip value",  json.contains("\"zip\":null"));
        assertTrue("Expected json to contain case_workers value",  json.contains("\"case_workers\":[]"));
        assertFalse("Expected json to contain image value",  json.contains("\"image\":null"));
        assertTrue("Expected json to contain email value",  json.contains("\"email\":null"));
        assertTrue("Expected json to contain password value",  json.contains("\"password\":\"\""));
    }

    @Test
    public void testSerializesConversation() throws IOException {
        serializer.serialize(user, jsonGenerator, null);
        jsonGenerator.flush();
        String json = stringWriter.toString();

        assertTrue("Expected json to contain name value",  json.contains("\"name\":{"));
        assertTrue("Expected json to contain first_name value",  json.contains("\"first_name\":\"" + firstName ));
        assertTrue("Expected json to contain last_name value",  json.contains("\"last_name\":\"" + lastName));
        assertTrue("Expected json to contain address value",  json.contains("\"address\":{"));
        assertTrue("Expected json to contain street_address value",  json.contains("\"street_address\":\"" + streetAddress1));
        assertTrue("Expected json to contain street_address_2 value",  json.contains("\"street_address_2\":\"" + streetAddress2));
        assertTrue("Expected json to contain state value",  json.contains("\"state\":\"" + state));
        assertTrue("Expected json to contain city value",  json.contains("\"city\":\"" + city));
        assertTrue("Expected json to contain zip value",  json.contains("\"zip\":\"" + zip));
        assertTrue("Expected json to contain case_workers value",  json.contains("\"case_workers\":[]"));
        assertTrue("Expected json to contain image value",  json.contains("\"image\":\"" + imageUrl));
        assertTrue("Expected json to contain email value",  json.contains("\"email\":\"" + email));
        assertTrue("Expected json to contain password value",  json.contains("\"password\":\"\""));
    }

}