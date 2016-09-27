package com.agreeya.chhs.request.user;

/**
 * Transfer Object class for User family Information
 * 
 * @author AgreeYa Solutions
 *
 */
public class UserKidsDetails {

	private String kidName;
	private String age;
	private String hobbies;
	

	public UserKidsDetails(String kidName, String age, String hobbies) {
		super();
		this.kidName = kidName;
		this.age = age;
		this.hobbies = hobbies;
	}

	public String getKidName() {
		return kidName;
	}

	public void setKidName(String kidName) {
		this.kidName = kidName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	

	
	

}
