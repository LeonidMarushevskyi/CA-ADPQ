package com.tabordasolutions.cws.parentportal.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import jersey.repackaged.com.google.common.collect.ImmutableList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.google.common.collect.ImmutableMap;
import com.tabordasolutions.cws.parentportal.api.Agency;

public class ChhsOpenDataAgencyResourceTest {
	private ChhsOpenDataAgencyResource chhsOpenDataAgencyResource;
	
	private Client client;
	private String apiUrlString;
	private String apiKey;
	private String apiQueryParam;
	private List<ImmutableMap<String,String>> jsonAgencyWithUnkown;
	private List<ImmutableMap<String,String>> jsonAgencyWithMissing;
	private Invocation.Builder invocationBuilder;
	
    @Rule
    public ExpectedException thrown = ExpectedException.none();
	
    @Before
    public void setup() {
    	client = Mockito.mock(Client.class);
    	apiUrlString = "someApiUrlString";
    	apiKey = "someapikey";
    	apiQueryParam = "facility_zip";
    	WebTarget webTarget = Mockito.mock(WebTarget.class);
    	invocationBuilder = Mockito.mock(Invocation.Builder.class);

    	
    	Mockito.when(client.target(Matchers.anyString())).thenReturn(webTarget);
    	Mockito.when(webTarget.queryParam(Matchers.anyString(), Matchers.anyString())).thenReturn(webTarget);
    	Mockito.when(webTarget.request()).thenReturn(invocationBuilder);
    	Mockito.when(invocationBuilder.header(Matchers.anyString(), Matchers.anyString())).thenReturn(invocationBuilder);
    }
    
    @Test
    public void testListAgenciesWithUnknownProperties() {
    	Mockito.when(invocationBuilder.get(List.class)).thenReturn( jsonAgencyWithUnknown() );
    	chhsOpenDataAgencyResource = new ChhsOpenDataAgencyResource(client, apiUrlString, apiKey, apiQueryParam);
    	List<Agency> agencies = chhsOpenDataAgencyResource.listAgencies("3333");
    	assertNotNull(agencies);
    	assertEquals(1, agencies.size());
    }

    @Test
    public void testListAgenciesWithMissingProperties() {
    	Mockito.when(invocationBuilder.get(List.class)).thenReturn( jsonAgencyWithMissing() );
    	chhsOpenDataAgencyResource = new ChhsOpenDataAgencyResource(client, apiUrlString, apiKey, apiQueryParam);
    	List<Agency> agencies = chhsOpenDataAgencyResource.listAgencies("3333");
    	assertNotNull(agencies);
    	assertEquals(1, agencies.size());
    }
    
    public void testClientNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("client");
         
    	new ChhsOpenDataAgencyResource(null, apiUrlString, apiKey, apiQueryParam);
    }
    
    @Test
    public void testApiUrlNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("apiUrl");
        
    	new ChhsOpenDataAgencyResource(client, null, apiKey, apiQueryParam);
    }
    
    @Test
    public void testApiKeyNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("apiKey");
        
    	new ChhsOpenDataAgencyResource(client, apiUrlString, null, apiQueryParam);
    }
    
    @Test
    public void testApiQueryParamNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("apiQueryParam");
        
    	new ChhsOpenDataAgencyResource(client, apiUrlString, apiKey, null);	
    }
    
    @Test
    public void testApiUrlEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("apiUrl");
        
    	new ChhsOpenDataAgencyResource(client, "", apiKey, apiQueryParam);
    	
    }
    
    @Test
    public void testApiKeyEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("apiKey");
        
    	new ChhsOpenDataAgencyResource(client, apiUrlString, "", apiQueryParam);
    }

    @Test
    public void testApiQueryParamEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("apiQueryParam");
        
    	new ChhsOpenDataAgencyResource(client, apiUrlString, apiKey, "");
    }
    
    @Test
    public void testNoZipcodeGiven() {
    	chhsOpenDataAgencyResource = new ChhsOpenDataAgencyResource(client, apiUrlString, apiKey, apiQueryParam);
    	List<Agency> agencies = chhsOpenDataAgencyResource.listAgencies(null);
    	assertNotNull(agencies);
    	assertEquals(0, agencies.size());
    	Mockito.verify(client, Mockito.never()).target(Mockito.anyString());
    }
    
    private List<ImmutableMap<String,String>> jsonAgencyWithUnknown() {
    	if( jsonAgencyWithUnkown == null ) {
    		ImmutableMap.Builder<String,String> mapBuilder = ImmutableMap.builder();
    		mapBuilder.put("facility_name", "name1")
    				.put("location_address", "address1")
    				.put("location_state", "state1")
    				.put("location_city", "city1")
    				.put("location_zip", "zip1")
    				.put("facility_telephone_number", "phone1")
    				.put("facility_type", "type1")
    				.put("junk1", "junk1")
    				.put("junk2", "junk2");
    	
    		ImmutableList.Builder<ImmutableMap<String, String>> listBuilder = ImmutableList.builder();
    		jsonAgencyWithUnkown = listBuilder.add(mapBuilder.build()).build();
    	}
    	
    	return jsonAgencyWithUnkown;
    }
    
    private List<ImmutableMap<String,String>> jsonAgencyWithMissing() {
    	if( jsonAgencyWithMissing == null ) {
    		ImmutableMap.Builder<String,String> mapBuilder = ImmutableMap.builder();
    		mapBuilder.put("facility_name", "name1")
    				.put("location_address", "address1")
    				.put("location_state", "state1")
    				.put("location_city", "city1")
    				.put("location_zip", "zip1");
    	
    		ImmutableList.Builder<ImmutableMap<String, String>> listBuilder = ImmutableList.builder();
    		jsonAgencyWithMissing = listBuilder.add(mapBuilder.build()).build();
    	}
    	
    	return jsonAgencyWithMissing;
    }

}
