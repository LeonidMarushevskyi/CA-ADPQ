package com.agreeya.chhs.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Validate Session Request
 * @author AgreeYa Solutions
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class SessionValidateRequest extends AbstractRequiresUserContext implements WSRequest {

	@Override
	public ValidationResult validate() {
		ValidationResult vr = new ValidationResult();
		return vr;
	}

}
