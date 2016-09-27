/**
 * 
 */
package com.agreeya.chhs.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.agreeya.chhs.to.UserContextTO;

/**
 * Response object for Save User
 * @author AgreeYa Solutions
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SaveUserResponse extends WSResponse {
	private UserContextTO userContext;

	public UserContextTO getUserContext() {
		return userContext;
	}

	public void setUserContext(UserContextTO userContext) {
		this.userContext = userContext;
	}
}
