package com.portlandwebworks.chhs.adoptioncenters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portlandwebworks.chhs.adoptioncenters.model.AdoptionCenter;
import com.portlandwebworks.chhs.adoptioncenters.model.LatLng;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author nick
 */
public class AdoptionCenterLocatorTest {

	private ObjectMapper mapper = new ObjectMapper();
	private RestTemplate rest;
	private PostalCodeGeoCoder geocoder;
	private AdoptionCenterLocator locator;

	public AdoptionCenterLocatorTest() {
	}

	@Before
	public void setUp() {
		rest = createMock(RestTemplate.class);
		geocoder = createMock(PostalCodeGeoCoder.class);
		locator = new AdoptionCenterLocator(geocoder, rest, mapper);
	}

	@Test
	public void testFindCentersNear() {
		String postalCode = "11111";
		Integer miles = 50;
		expect(geocoder.geocodePostalCode(postalCode)).andReturn(new LatLng(BigDecimal.ONE, BigDecimal.ONE));
		expect(rest.getForObject(isA(String.class), eq(String.class), isA(Map.class))).andReturn(sampleJson);
		replay(geocoder, rest);
		List<AdoptionCenter> result = locator.findCentersNear(postalCode, miles);
		verify(geocoder, rest);
		assertEquals(2, result.size());
		assertNotNull(result.get(0).getLocation());
		assertEquals(new BigDecimal("-121.415475"), result.get(0).getLocation().getLng());
	}

	private String sampleJson = "[ {\n"
			+ "  \"location\" : {\n"
			+ "    \"needs_recoding\" : false,\n"
			+ "    \"longitude\" : \"-121.415475\",\n"
			+ "    \"latitude\" : \"38.590199\",\n"
			+ "    \"human_address\" : \"{\\\"address\\\":\\\"1337 HOWE AVENUE STE. 107\\\",\\\"city\\\":\\\"SACRAMENTO\\\",\\\"state\\\":\\\"CA\\\",\\\"zip\\\":\\\"95825\\\"}\"\n"
			+ "  },\n"
			+ "  \"closed_date\" : \"2012-01-19T00:00:00\",\n"
			+ "  \"facility_type\" : \"ADOPTION AGENCY\",\n"
			+ "  \"facility_address\" : \"1337 HOWE AVENUE STE. 107\",\n"
			+ "  \"facility_state\" : \"CA\",\n"
			+ "  \"license_first_date\" : \"1996-06-01T00:00:00\",\n"
			+ "  \"facility_capacity\" : \"0\",\n"
			+ "  \"facility_city\" : \"SACRAMENTO\",\n"
			+ "  \"facility_telephone_number\" : \"(916) 641-0661\",\n"
			+ "  \"facility_name\" : \"BETTER LIFE CHILDREN SERVICES-AA\",\n"
			+ "  \"facility_administrator\" : \"MCNEIL, WINIFRED\",\n"
			+ "  \"facility_status\" : \"CLOSED\",\n"
			+ "  \"facility_number\" : \"347000166\",\n"
			+ "  \"facility_zip\" : \"95825\",\n"
			+ "  \"regional_office\" : \"23\",\n"
			+ "  \"licensee\" : \"BETTER LIFE CHILDREN SERVICES, INC.\",\n"
			+ "  \"county_name\" : \"SACRAMENTO\"\n"
			+ "}\n"
			+ ", {\n"
			+ "  \"location\" : {\n"
			+ "    \"needs_recoding\" : false,\n"
			+ "    \"longitude\" : \"-121.435352\",\n"
			+ "    \"latitude\" : \"38.599996\",\n"
			+ "    \"human_address\" : \"{\\\"address\\\":\\\"1555 RIVER PARK DR. SUITE 100\\\",\\\"city\\\":\\\"SACRAMENTO\\\",\\\"state\\\":\\\"CA\\\",\\\"zip\\\":\\\"95815\\\"}\"\n"
			+ "  },\n"
			+ "  \"closed_date\" : \"2014-02-10T00:00:00\",\n"
			+ "  \"facility_type\" : \"ADOPTION AGENCY\",\n"
			+ "  \"facility_address\" : \"1555 RIVER PARK DR. SUITE 100\",\n"
			+ "  \"facility_state\" : \"CA\",\n"
			+ "  \"license_first_date\" : \"2008-08-14T00:00:00\",\n"
			+ "  \"facility_capacity\" : \"0\",\n"
			+ "  \"facility_city\" : \"SACRAMENTO\",\n"
			+ "  \"facility_telephone_number\" : \"(916) 487-4658\",\n"
			+ "  \"facility_name\" : \"HOLT INTERNATIONAL CHILDREN'S SERVICES\",\n"
			+ "  \"facility_administrator\" : \"SHOCKENCY, CYNTHIA\",\n"
			+ "  \"facility_status\" : \"CLOSED\",\n"
			+ "  \"facility_number\" : \"347004210\",\n"
			+ "  \"facility_zip\" : \"95815\",\n"
			+ "  \"regional_office\" : \"23\",\n"
			+ "  \"licensee\" : \"HOLT INTERNATIONAL CHILDREN'S SERVICES\",\n"
			+ "  \"county_name\" : \"SACRAMENTO\"\n"
			+ "}]";
}
