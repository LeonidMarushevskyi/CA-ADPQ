package com.agreeya.chhs.response.hhsapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Transfer Object for Facility
 * 
 * @author AgreeYa Solutions
 *
 */
public class Facility {

	@SerializedName("county_name")
	@Expose
	private String countyName;
	@SerializedName("facility_address")
	@Expose
	private String facilityAddress;
	@SerializedName("facility_administrator")
	@Expose
	private String facilityAdministrator;
	@SerializedName("facility_capacity")
	@Expose
	private String facilityCapacity;
	@SerializedName("facility_city")
	@Expose
	private String facilityCity;
	@SerializedName("facility_name")
	@Expose
	private String facilityName;
	@SerializedName("facility_number")
	@Expose
	private String facilityNumber;
	@SerializedName("facility_state")
	@Expose
	private String facilityState;
	@SerializedName("facility_status")
	@Expose
	private String facilityStatus;
	@SerializedName("facility_telephone_number")
	@Expose
	private String facilityTelephoneNumber;
	@SerializedName("facility_type")
	@Expose
	private String facilityType;
	@SerializedName("facility_zip")
	@Expose
	private String facilityZip;
	@SerializedName("license_first_date")
	@Expose
	private String licenseFirstDate;
	@SerializedName("licensee")
	@Expose
	private String licensee;
	@SerializedName("location")
	@Expose
	private Location location;
	@SerializedName("location_address")
	@Expose
	private String locationAddress;
	@SerializedName("location_city")
	@Expose
	private String locationCity;
	@SerializedName("location_state")
	@Expose
	private String locationState;
	@SerializedName("location_zip")
	@Expose
	private String locationZip;
	@SerializedName("regional_office")
	@Expose
	private String regionalOffice;

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getFacilityAddress() {
		return facilityAddress;
	}

	public void setFacilityAddress(String facilityAddress) {
		this.facilityAddress = facilityAddress;
	}

	public String getFacilityAdministrator() {
		return facilityAdministrator;
	}

	public void setFacilityAdministrator(String facilityAdministrator) {
		this.facilityAdministrator = facilityAdministrator;
	}

	public String getFacilityCapacity() {
		return facilityCapacity;
	}

	public void setFacilityCapacity(String facilityCapacity) {
		this.facilityCapacity = facilityCapacity;
	}

	public String getFacilityCity() {
		return facilityCity;
	}

	public void setFacilityCity(String facilityCity) {
		this.facilityCity = facilityCity;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFacilityNumber() {
		return facilityNumber;
	}

	public void setFacilityNumber(String facilityNumber) {
		this.facilityNumber = facilityNumber;
	}

	public String getFacilityState() {
		return facilityState;
	}

	public void setFacilityState(String facilityState) {
		this.facilityState = facilityState;
	}

	public String getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public String getFacilityTelephoneNumber() {
		return facilityTelephoneNumber;
	}

	public void setFacilityTelephoneNumber(String facilityTelephoneNumber) {
		this.facilityTelephoneNumber = facilityTelephoneNumber;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public String getFacilityZip() {
		return facilityZip;
	}

	public void setFacilityZip(String facilityZip) {
		this.facilityZip = facilityZip;
	}

	public String getLicenseFirstDate() {
		return licenseFirstDate;
	}

	public void setLicenseFirstDate(String licenseFirstDate) {
		this.licenseFirstDate = licenseFirstDate;
	}

	public String getLicensee() {
		return licensee;
	}

	public void setLicensee(String licensee) {
		this.licensee = licensee;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getLocationState() {
		return locationState;
	}

	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}

	public String getLocationZip() {
		return locationZip;
	}

	public void setLocationZip(String locationZip) {
		this.locationZip = locationZip;
	}

	public String getRegionalOffice() {
		return regionalOffice;
	}

	public void setRegionalOffice(String regionalOffice) {
		this.regionalOffice = regionalOffice;
	}

}
