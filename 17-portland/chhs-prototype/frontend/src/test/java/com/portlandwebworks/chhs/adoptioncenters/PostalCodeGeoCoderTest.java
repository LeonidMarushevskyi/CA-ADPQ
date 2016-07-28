package com.portlandwebworks.chhs.adoptioncenters;

import com.portlandwebworks.chhs.adoptioncenters.model.LatLng;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.Before;
import static org.junit.Assert.*;
import org.springframework.web.client.RestTemplate;
import static org.easymock.EasyMock.*;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author nick
 */
public class PostalCodeGeoCoderTest {

	private RestTemplate tmpl;
	private PostalCodeGeoCoder geocoder;

	public PostalCodeGeoCoderTest() {
	}

	@Before
	public void setUp() {
		tmpl = createMock(RestTemplate.class);
		geocoder = new PostalCodeGeoCoder(tmpl);
	}

	@Test
	public void testGeocodePostalCode() {
		System.out.println("geocodePostalCode");
		ResponseEntity<String> resp = createMock(ResponseEntity.class);
		expect(tmpl.getForEntity(isA(String.class), eq(String.class), isA(Map.class))).andReturn(resp);
		expect(resp.getStatusCode()).andReturn(HttpStatus.OK);
		expect(resp.getBody()).andReturn(sampleJson);

		String postalCode = "94207";
		replay(tmpl, resp);
		LatLng result = geocoder.geocodePostalCode(postalCode);
		verify(tmpl, resp);
		assertNotNull(result);
		assertEquals(new BigDecimal("38.4"), result.getLat());
		assertEquals(new BigDecimal("-121.3"), result.getLng());
	}

	private String sampleJson = "{\n"
			+ "   \"results\" : [\n"
			+ "      {\n"
			+ "         \"address_components\" : [\n"
			+ "            {\n"
			+ "               \"long_name\" : \"94207\",\n"
			+ "               \"short_name\" : \"94207\",\n"
			+ "               \"types\" : [ \"postal_code\" ]\n"
			+ "            },\n"
			+ "            {\n"
			+ "               \"long_name\" : \"Johnston Business Park\",\n"
			+ "               \"short_name\" : \"Johnston Business Park\",\n"
			+ "               \"types\" : [ \"neighborhood\", \"political\" ]\n"
			+ "            },\n"
			+ "            {\n"
			+ "               \"long_name\" : \"Sacramento\",\n"
			+ "               \"short_name\" : \"Sacramento\",\n"
			+ "               \"types\" : [ \"locality\", \"political\" ]\n"
			+ "            },\n"
			+ "            {\n"
			+ "               \"long_name\" : \"Sacramento County\",\n"
			+ "               \"short_name\" : \"Sacramento County\",\n"
			+ "               \"types\" : [ \"administrative_area_level_2\", \"political\" ]\n"
			+ "            },\n"
			+ "            {\n"
			+ "               \"long_name\" : \"California\",\n"
			+ "               \"short_name\" : \"CA\",\n"
			+ "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n"
			+ "            },\n"
			+ "            {\n"
			+ "               \"long_name\" : \"United States\",\n"
			+ "               \"short_name\" : \"US\",\n"
			+ "               \"types\" : [ \"country\", \"political\" ]\n"
			+ "            }\n"
			+ "         ],\n"
			+ "         \"formatted_address\" : \"Sacramento, CA 94207, USA\",\n"
			+ "         \"geometry\" : {\n"
			+ "            \"location\" : {\n"
			+ "               \"lat\" : 38.4,\n"
			+ "               \"lng\" : -121.3\n"
			+ "            },\n"
			+ "            \"location_type\" : \"APPROXIMATE\",\n"
			+ "            \"viewport\" : {\n"
			+ "               \"northeast\" : {\n"
			+ "                  \"lat\" : 38.6013489802915,\n"
			+ "                  \"lng\" : -121.4486510197085\n"
			+ "               },\n"
			+ "               \"southwest\" : {\n"
			+ "                  \"lat\" : 38.59865101970851,\n"
			+ "                  \"lng\" : -121.4513489802915\n"
			+ "               }\n"
			+ "            }\n"
			+ "         },\n"
			+ "         \"place_id\" : \"ChIJywnnjFTXmoARCyvjxBGBFUI\",\n"
			+ "         \"types\" : [ \"postal_code\" ]\n"
			+ "      }\n"
			+ "   ],\n"
			+ "   \"status\" : \"OK\"\n"
			+ "}";

}
