package com.agreeya.chhs.request;

/**
 * User Logout API request POJO
 * @author AgreeYa Solutions
 *
 */
public class UserLogoutRequest extends AbstractRequiresUserContext implements WSRequest {

	@Override
	public ValidationResult validate() {
		ValidationResult vr = new ValidationResult();
		return vr;
	}

}
