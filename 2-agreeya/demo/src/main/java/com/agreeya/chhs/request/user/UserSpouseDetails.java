package com.agreeya.chhs.request.user;

/**
 * Transfer Object class for User Spouse Information
 * 
 * @author AgreeYa Solutions
 *
 */
public class UserSpouseDetails {
	private String contactNo;
	private String dob;
	private String firstName;
	private String gender;
	private String hobbies;
	private String income;
	private String occupation;
	private String preference;
	private String race;
	private String religion;
	
	
	public UserSpouseDetails(String contactNo, String dob,
			String firstName, String gender, String hobbies,
			String income, String occupation, String preference, String race, String religion) {
		super();
		this.contactNo = contactNo;
		this.dob = dob;
		this.firstName = firstName;
		this.gender = gender;
		this.hobbies = hobbies;
		this.income = income;
		this.occupation = occupation;
		this.preference = preference;
		this.race = race;
		this.religion = religion;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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


	
	
}
