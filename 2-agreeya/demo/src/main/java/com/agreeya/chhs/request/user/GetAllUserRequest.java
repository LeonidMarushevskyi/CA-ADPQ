
package com.agreeya.chhs.request.user;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.agreeya.chhs.request.AbstractRequiresUserContext;
import com.agreeya.chhs.request.ValidationResult;
import com.agreeya.chhs.request.WSRequest;

/**
 * @author amit.sharma
 *
 */
public class GetAllUserRequest extends AbstractRequiresUserContext implements WSRequest {
	@NotNull
	@Min(value = 1)
	private Long id;

	@Override
	public ValidationResult validate() {
		ValidationResult vr = new ValidationResult();
		/*
		 * if (id == null || id <=0) { vr.addMessage(HibernateValidationErrorUtil.getProperty("tta.user.getUserByIdRequest.id")); }
		 */
		return vr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}


