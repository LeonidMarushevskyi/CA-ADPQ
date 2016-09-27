package com.agreeya.chhs.request;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;



/**
 * Request POJO for Loginn API
 * @author AgreeYa Solutions
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CreateUserContextRequest extends AbstractDoesNotRequireAnyContext implements WSRequest {

	@NotNull
	@NotEmpty
	private String userName;

	@NotNull
	@NotEmpty
	private String password;

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

	@Override
	public ValidationResult validate() {
		ValidationResult vr = new ValidationResult();

		// Code commented as this check is done by the Hibernate Validator annotations.
		// However, this method can be used for more complex condition checks that cannot be
		// easily performed by Hibernate Validator.

		if (userName == null || userName.isEmpty()) {
			vr.addMessage("username is not provided!.");
		}

		if (password == null || password.isEmpty()) {
			vr.addMessage("password is not provided!.");
		}

		return vr;
	}
}
