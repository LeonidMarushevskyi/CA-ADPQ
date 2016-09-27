package com.agreeya.chhs.to;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * POJO for UserContext that will be used for all API validation and Security
 * @author AgreeYa Solutions
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserContextTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 763546184279223036L;
	@NotNull
	private int userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String loggedInDate;
	private String roleName;
	private int profileStage;
	private String userStatus;
	private Map<String, Boolean> userRoleMap = new HashMap<String, Boolean>();

	@NotNull
	private String sessionId;

	public UserContextTO() {

	}

	
	public UserContextTO(int userId, String firstName, String lastName, String userName, String loggedInDate, String roleName,
			int profileStage, String userStatus, Map<String, Boolean> userRoleMap, String sessionId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.loggedInDate = loggedInDate;
		this.roleName = roleName;
		this.profileStage = profileStage;
		this.userStatus = userStatus;
		this.userRoleMap = userRoleMap;
		this.sessionId = sessionId;
	}


	public int getProfileStage() {
		return profileStage;
	}


	public void setProfileStage(int profileStage) {
		this.profileStage = profileStage;
	}


	public String getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String, Boolean> getUserRoleMap() {
		return userRoleMap;
	}

	public void setUserRoleMap(Map<String, Boolean> userRoleMap) {
		this.userRoleMap = userRoleMap;
	}

	public String getLoggedInDate() {
		return loggedInDate;
	}

	public void setLoggedInDate(String loggedInDate) {
		this.loggedInDate = loggedInDate;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
