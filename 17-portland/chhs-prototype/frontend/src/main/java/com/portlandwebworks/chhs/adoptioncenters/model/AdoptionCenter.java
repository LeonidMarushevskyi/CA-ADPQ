package com.portlandwebworks.chhs.adoptioncenters.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 *
 * @author nick
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdoptionCenter {
	@JsonProperty("facility_number")
	private String id;
	@JsonProperty("facility_name")
	private String name;
	@JsonProperty("facility_address")
	private String street;
	@JsonProperty("facility_city")
	private String city;
	@JsonProperty("facility_state")
	private String state;
	@JsonProperty("facility_zip")
	private String zip;
	@JsonProperty("facility_telephone_number")
	private String phone;
	@JsonProperty("location")
	private LatLng location;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public LatLng getLocation() {
		return location;
	}

	public void setLocation(LatLng location) {
		this.location = location;
	}
}
