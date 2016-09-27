package com.agreeya.chhs.request;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.agreeya.chhs.request.user.UserFamilyDetails;
import com.agreeya.chhs.request.user.UserKidsDetails;
import com.agreeya.chhs.request.user.UserLicenceDetails;
import com.agreeya.chhs.request.user.UserPersonal;
import com.agreeya.chhs.request.user.UserProfile;

/**
 * Request POJO for Save User API
 * @author AgreeYa Solutions
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class SaveUserRequest extends AbstractRequiresUserContext implements WSRequest {

	UserProfile personalProfile;
	UserPersonal personalDetails;
	UserFamilyDetails familyDetails;
	UserLicenceDetails licenceDetails;

	@NotNull
	int registrationStage;

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

	public int getRegistrationStage() {
		return registrationStage;
	}

	public void setRegistrationStage(int registrationStage) {
		this.registrationStage = registrationStage;
	}


	@Override
	public ValidationResult validate() {
		ValidationResult vr = new ValidationResult();

		UserProfile pro = getPersonalProfile();

		if (registrationStage > 4 || registrationStage < 0) {
			vr.addMessage("Invalid Registration stage!.");
		}

		if (pro.getTraining() == null || pro.getTraining().isEmpty()) {
			vr.addMessage("Training is not provided!.");
		}

		if (pro.getPassword() == null || pro.getPassword().isEmpty()) {
			vr.addMessage("password is not provided!.");
		}

		if (pro.getUserName() == null || pro.getUserName().isEmpty()) {
			vr.addMessage("UserName is not provided!.");
		}

		if (pro.getUseremail() == null || pro.getUseremail().isEmpty()) {
			vr.addMessage("email is not provided!.");
		}

		if (pro.getHomestudy() == null || pro.getHomestudy().isEmpty()) {
			vr.addMessage("Homestudy is not provided!.");
		}

		int stage = getRegistrationStage();
		if (stage ==  2) {
			if (null == this.personalDetails) {
				vr.addMessage("Personal Details Missing");
			}
			if (this.personalDetails != null) {
				validatePersonalDetails(vr, getPersonalDetails());
			}
		} else if (stage == 3) {
			if (null == this.personalDetails) {
				vr.addMessage("Personal Details Missing");
			}
			if (null == this.familyDetails) {
				vr.addMessage("Family Details Missing");
			}
			if (null != this.personalDetails && null != this.familyDetails) {
				validatePersonalDetails(vr, getPersonalDetails());
				validateFamilyDetails(vr, getFamilyDetails());
			}
		} else if (stage == 4) {
			if (null == this.personalDetails) {
				vr.addMessage("Personal Details Missing");
			}
			if (null == this.familyDetails) {
				vr.addMessage("Family Details Missing");
			}
			if (null == this.licenceDetails) {
				vr.addMessage("Licence Details Missing");
			}
			if (null != this.personalDetails && null != this.familyDetails && null != this.licenceDetails) {
				validatePersonalDetails(vr, getPersonalDetails());
				validateFamilyDetails(vr, getFamilyDetails());
				validateLicenceDetails(vr, getLicenceDetails());
			}
		}

		return vr;
	}

	private ValidationResult validatePersonalDetails(ValidationResult vr, UserPersonal per) {


		if (per.getFirstName() == null || per.getFirstName().isEmpty()) {
			vr.addMessage("FirstName is not provided!.");
		}

		if (per.getLastName() == null || per.getLastName().isEmpty()) {
			vr.addMessage("LastName is not provided!.");
		}

		if (per.getContactNo() == null || per.getContactNo().isEmpty()) {
			vr.addMessage("Contact No is not provided!.");
		}

		if (per.getDob() == null || per.getDob().isEmpty()) {
			vr.addMessage("Date Of Birth is not provided!.");
		}


		return vr;
	}

	private ValidationResult validateFamilyDetails(ValidationResult vr, UserFamilyDetails fam) {
		if (fam.getDescription() == null || fam.getDescription().isEmpty()) {
			vr.addMessage("Family Description is not provided!.");
		}

		if (fam.getNumberOfKids() == null || fam.getNumberOfKids().isEmpty()) {
			fam.setNumberOfKids("0");
		}

		if (fam.getKidsPref() == null || fam.getKidsPref().isEmpty()) {
			vr.addMessage("kids pref is not provided!.");
		}
		if (fam.getHaveKids() == null || fam.getHaveKids().isEmpty()) {
			vr.addMessage("Have Kids is not answered!.");
		}

		if ("Y".equalsIgnoreCase(fam.getHaveKids())) {
			if (null == fam.getKids() || fam.getKids().size() == 0) {
				vr.addMessage("Kids details is not provided!.");
			}
			if (fam.getKids().size() > 0 && fam.getKids() != null) {
				for (UserKidsDetails kid : fam.getKids()) {
					if (kid.getKidName() == null || kid.getKidName().isEmpty()) {
						vr.addMessage("Kid Name not provided!");
					}
					if (kid.getAge() == null || kid.getAge().isEmpty()) {
						vr.addMessage("Kid Age not provided!");
					}
					if (kid.getHobbies() == null || kid.getHobbies().isEmpty()) {
						vr.addMessage("Kid Hobbies not provided!");
					}
				}
			}

		}

		return vr;
	}

	private ValidationResult validateLicenceDetails(ValidationResult vr, UserLicenceDetails lic) {
		if (lic.getLicenceNo() == null || lic.getLicenceNo().isEmpty()) {
			vr.addMessage("Licence Number is not provided!.");
		}
		if (lic.getAgencyContact() == null || lic.getAgencyContact().isEmpty()) {
			vr.addMessage("Agency Contact is not provided!.");
		}
		if (lic.getAgencyWorker()  == null || lic.getAgencyWorker().isEmpty()) {
			vr.addMessage("Agency Worker is not provided!.");
		}
		if (lic.getDateOfIssue() == null || lic.getDateOfIssue().isEmpty()) {
			vr.addMessage("Date of issue is not provided!.");
		}
		return vr;
	}

}
