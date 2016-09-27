package com.agreeya.chhs.request.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Transfer Object class for User profile and account details
 * @author AgreeYa Solutions
 *
 */
public class UserProfile {

	@NotNull
	@NotBlank
	private String homestudy;
	
	@NotNull
	private String training;
	
	@NotNull
	@Email(message = "Please provide a valid email address")
	@NotBlank
	private String useremail;
	
	@NotNull
	@NotBlank
	private String userName;
	
	@NotNull
	@NotBlank
	private String password;
	
	public UserProfile(String homestudy, String training, String useremail, String userName, String password) {
		super();
		this.homestudy = homestudy;
		this.training = training;
		this.useremail = useremail;
		this.userName = userName;
		this.password = password;
	}
	
	public String getHomestudy() {
		return homestudy;
	}
	public void setHomestudy(String homestudy) {
		this.homestudy = homestudy;
	}
	public String getTraining() {
		return training;
	}
	public void setTraining(String training) {
		this.training = training;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
