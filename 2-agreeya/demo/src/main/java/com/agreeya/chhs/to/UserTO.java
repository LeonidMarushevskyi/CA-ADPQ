package com.agreeya.chhs.to;

import com.agreeya.chhs.request.user.UserFamilyDetails;
import com.agreeya.chhs.request.user.UserLicenceDetails;
import com.agreeya.chhs.request.user.UserPersonal;
import com.agreeya.chhs.request.user.UserProfile;

/**
 * User TO class 
 * @author AgreeYa Solutions
 *
 */
public class UserTO {
	UserProfile personalProfile;
	UserPersonal personalDetails;
	UserFamilyDetails familyDetails;
	UserLicenceDetails licenceDetails;
	public UserProfile getPersonalProfile() {
		return personalProfile;
	}
	public void setPersonalProfile(UserProfile personalProfile) {
		this.personalProfile = personalProfile;
	}
	public UserPersonal getPersonalDetails() {
		return personalDetails;
	}
	public void setPersonalDetails(UserPersonal personalDetails) {
		this.personalDetails = personalDetails;
	}
	public UserFamilyDetails getFamilyDetails() {
		return familyDetails;
	}
	public void setFamilyDetails(UserFamilyDetails familyDetails) {
		this.familyDetails = familyDetails;
	}
	public UserLicenceDetails getLicenceDetails() {
		return licenceDetails;
	}
	public void setLicenceDetails(UserLicenceDetails licenceDetails) {
		this.licenceDetails = licenceDetails;
	}
	
}
