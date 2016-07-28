package com.tabordasolutions.cws.parentportal.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class CreateConversationRequestDeserializer extends JsonDeserializer<CreateConversationRequest> {
    @Override
    public CreateConversationRequest deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        CreateConversationRequest request;
        try {
            ObjectCodec oc = jsonParser.getCodec();
            JsonNode node = oc.readTree(jsonParser);

            request = new CreateConversationRequest();
            request.setSubject(node.get("subject").asText());
            request.setMessage(node.get("init_message").asText());
            request.setReceiverId(node.get("receiver").asLong(Long.MIN_VALUE));

        }catch(JsonProcessingException e) {
            System.out.println("Threw JSON Deserializing Error. Unprocessable request, skipping....");
            e.printStackTrace();
            throw e;
        } catch(IOException e){
            System.out.println("Threw IO Exception. Unprocessable request, skipping....");
            e.printStackTrace();
            throw e;
        }
        return request;
    }
}
