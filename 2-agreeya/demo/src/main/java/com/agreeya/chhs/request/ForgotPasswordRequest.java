package com.agreeya.chhs.request;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author AgreeYa Solutions
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ForgotPasswordRequest extends AbstractDoesNotRequireAnyContext implements WSRequest {

	@NotNull
	// @Length(max = 40, message = "The user name must be less than 15 characters")
	// @Pattern(regexp = "^[a-zA-Z]+$", message = "Please provide a valid user name")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public ValidationResult validate() {
		ValidationResult vr = new ValidationResult();

		// Code commented as this check is done by the Hibernate Validator annotations.
		// However, this method can be used for more complex condition checks that cannot be
		// easily performed by Hibernate Validator.

		if (userName == null || userName.isEmpty()) {
			vr.addMessage("username is not provided!.");
		}
		return vr;
	}

}
