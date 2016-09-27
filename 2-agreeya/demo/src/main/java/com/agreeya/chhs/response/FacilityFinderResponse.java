package com.agreeya.chhs.response;

import com.agreeya.chhs.response.hhsapi.FacilityList;

/**
 *  Facility finder API response
 * @author AgreeYa Solutions
 *
 */
public class FacilityFinderResponse extends WSResponse {

	public FacilityList facilities;
	
	public FacilityList getFacilities() {
		return facilities;
	}

	public void setFacilities(FacilityList facilities) {
		this.facilities = facilities;
	}
}
