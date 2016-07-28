package com.portlandwebworks.chhs.adoptioncenters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portlandwebworks.chhs.adoptioncenters.model.LatLng;
import com.portlandwebworks.chhs.adoptioncenters.model.AdoptionCenter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author nick
 */
@Component
public class AdoptionCenterLocator {

	private final static Logger log = LoggerFactory.getLogger(AdoptionCenterLocator.class);
	private final BigDecimal MILES_TO_METERS = new BigDecimal("1609.34");
	private final BigDecimal DEFAULT_RADIUS = new BigDecimal("80467.2");
	private final Integer DEFAULT_LIMIT = 50;

	private final RestTemplate template;
	private final ObjectMapper objectMapper;
	private final PostalCodeGeoCoder geoCoder;

	@Autowired
	public AdoptionCenterLocator(PostalCodeGeoCoder geoCoder, ObjectMapper objectMapper) {
		this.geoCoder = geoCoder;
		this.objectMapper = objectMapper;
		this.template = new RestTemplate();
	}

	public AdoptionCenterLocator(PostalCodeGeoCoder geoCoder, RestTemplate template, ObjectMapper objectMapper) {
		this.geoCoder = geoCoder;
		this.template = template;
		this.objectMapper = objectMapper;
	}

	/**
	 *
	 * @param postalCode
	 * @param miles Optional, can be null. Defaults to 50 mile radius.
	 * @return
	 */
	public List<AdoptionCenter> findCentersNear(String postalCode, Integer miles) {
		log.info("Looking for locations within {} miles.", miles);
		final BigDecimal meterRadius;
		if (miles != null) {
			meterRadius = BigDecimal.valueOf(miles).multiply(MILES_TO_METERS).setScale(4, RoundingMode.CEILING);
		} else {
			meterRadius = DEFAULT_RADIUS;
		}
		LatLng loc = geoCoder.geocodePostalCode(postalCode);
		Map<String, Object> params = new HashMap<>();
		params.put("facility_type", "ADOPTION AGENCY");
		params.put("$where", "facility_status != \"CLOSED\" AND within_circle(location," + loc.getLat() + "," + loc.getLng() + "," + meterRadius.toPlainString() + ")");
		params.put("$limit", DEFAULT_LIMIT);
		String respJson = template.getForObject("https://chhs.data.ca.gov/resource/v9bn-m9p9.json?facility_type={facility_type}&$where={$where}&$limit={$limit}",
				String.class,
				params);
		try {
			return objectMapper.readValue(respJson, new TypeReference<List<AdoptionCenter>>() {
			});
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
