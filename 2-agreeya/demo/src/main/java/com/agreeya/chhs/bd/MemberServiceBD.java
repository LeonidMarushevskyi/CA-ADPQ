package com.agreeya.chhs.bd;

import java.sql.SQLException;

import org.hibernate.HibernateException;

import com.agreeya.chhs.exception.UserException;
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.request.GetUserInboxRequest;
import com.agreeya.chhs.request.SaveUserRequest;
import com.agreeya.chhs.request.UserLogoutRequest;
import com.agreeya.chhs.request.UserRegistrationRequest;
import com.agreeya.chhs.response.SaveUserResponse;
import com.agreeya.chhs.response.UserInboxResponse;
import com.agreeya.chhs.response.UserLogoutResponse;
import com.agreeya.chhs.response.UserRegistrationResponse;
import com.agreeya.chhs.to.UserContextTO;
import com.agreeya.chhs.to.UserTO;

/**
 * Business delegate interface for membership-related services.
 */
public interface MemberServiceBD {

	UserContextTO authenticateUser(String userName, String password);

	boolean isUserSessionValid(String sessionId, int userId);

	UserTO checkUserDetailExist(String userName);

	void logoutUser(UserLogoutRequest userLogoutRequest, UserLogoutResponse userLogoutResponse);

	UserRegistrationResponse registerUser(UserRegistrationRequest request, UserRegistrationResponse response) 
			throws HibernateException, SQLException, UserException, WSException;
	
	SaveUserResponse saveUser(SaveUserRequest request, SaveUserResponse response) 
			throws HibernateException, SQLException, UserException, WSException;
	
	UserInboxResponse getUserInbox(GetUserInboxRequest request, UserInboxResponse response);
}
