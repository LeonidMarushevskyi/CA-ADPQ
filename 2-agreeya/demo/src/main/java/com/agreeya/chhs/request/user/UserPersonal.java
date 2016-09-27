package com.agreeya.chhs.request.user;

/**
 * Transfer Object class for User personal Information
 * 
 * @author AgreeYa Solutions
 *
 */
public class UserPersonal {

	private String contactNo;
	private String dob;
	private String firstName;
	private String gender;
	private String hobbies;
	private String income;
	private String lastName;
	private String maritalStatus;
	private String occupation;
	private String preference;
	private String race;
	private String religion;
	
	private UserSpouseDetails spouseDetails;
	

	public UserPersonal(String contactNo, String dob, String firstName, 
			String gender, String hobbies, String income,
			String lastName, String maritalStatus, String occupation, String preference, String race, String religion,
			UserSpouseDetails spouseDetails) {
		super();
		this.contactNo = contactNo;
		this.dob = dob;
		this.firstName = firstName;
		this.gender = gender;
		this.hobbies = hobbies;
		this.income = income;
		this.lastName = lastName;
		this.maritalStatus = maritalStatus;
		this.occupation = occupation;
		this.preference = preference;
		this.race = race;
		this.religion = religion;
		this.spouseDetails = spouseDetails;
	}

	public String getContactNo() {
		return contactNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public UserSpouseDetails getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(UserSpouseDetails spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	
	
}
