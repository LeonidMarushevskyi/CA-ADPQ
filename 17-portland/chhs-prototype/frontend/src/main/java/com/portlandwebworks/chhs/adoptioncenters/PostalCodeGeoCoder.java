package com.portlandwebworks.chhs.adoptioncenters;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.portlandwebworks.chhs.adoptioncenters.model.LatLng;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author nick
 */
@Component
public class PostalCodeGeoCoder {

	private static final Logger log = LoggerFactory.getLogger(AdoptionCenterLocator.class);
	private static final LatLng DEFAULT_LOC = new LatLng(BigDecimal.valueOf(38.6), BigDecimal.valueOf(-121.45));
	
	private final RestTemplate template;

	public PostalCodeGeoCoder() {
		this.template = new RestTemplate();
	}
	
	public PostalCodeGeoCoder(RestTemplate template) {
		this.template = template;
	}

	/**
	 * Uses GoogleAPIs to geocode a postal code to get the lat/lng to work with
	 * the public data set api.
	 *
	 * @param postalCode
	 * @return
	 */
	public LatLng geocodePostalCode(String postalCode) {
		log.info("GeoCoding postal code of: {}", postalCode);
		Map<String, String> vars = new HashMap<>();
		vars.put("address", postalCode);
		ResponseEntity<String> googleResp = template.getForEntity("http://maps.googleapis.com/maps/api/geocode/json?address={address}", String.class, vars);
		if (googleResp.getStatusCode() == HttpStatus.OK) {
			Gson gson = new Gson();
			JsonElement elem = gson.fromJson(googleResp.getBody(), JsonElement.class);
			JsonObject obj = elem.getAsJsonObject();
			JsonArray results = obj.getAsJsonArray("results");
			if (results.size() > 0) {
				JsonObject firstResult = results.get(0).getAsJsonObject();
				JsonObject location = firstResult.getAsJsonObject("geometry").getAsJsonObject("location");
				return new LatLng(location.get("lat").getAsBigDecimal(), location.get("lng").getAsBigDecimal());
			} else {
				log.warn("Could get geocode results for postal code: {}", postalCode);
			}
		} else {
			log.warn("Could not get google geocode response for postal code {}. Status of {} returned.", postalCode, googleResp.getStatusCode());
		}

		return DEFAULT_LOC;

	}
}
