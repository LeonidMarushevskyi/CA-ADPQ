package com.agreeya.chhs.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * POJO For email Request 
 * @author AgreeYa Solutions
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class GetUserInboxRequest extends AbstractRequiresUserContext implements WSRequest {

	@Override
	public ValidationResult validate() {
		ValidationResult vr = new ValidationResult();

		return vr;
	}

}
