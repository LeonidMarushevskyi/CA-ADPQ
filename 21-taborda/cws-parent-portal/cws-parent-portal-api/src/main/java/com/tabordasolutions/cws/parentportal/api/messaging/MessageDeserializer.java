package com.tabordasolutions.cws.parentportal.api.messaging;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tabordasolutions.cws.parentportal.api.Message;

public class MessageDeserializer extends JsonDeserializer<Message> {
    @Override
    public Message deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null;
    }
}
