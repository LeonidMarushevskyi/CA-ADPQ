package com.portlandwebworks.chhs.adoptioncenters.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 *
 * @author nick
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatLng {
	
	@JsonProperty("latitude")
	private BigDecimal lat;
	@JsonProperty("longitude")
	private BigDecimal lng;

	public LatLng() {
	}

	public LatLng(BigDecimal lat, BigDecimal lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	
}
