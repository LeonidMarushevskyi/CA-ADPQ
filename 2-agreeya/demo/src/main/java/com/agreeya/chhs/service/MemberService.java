package com.agreeya.chhs.service;

import java.sql.SQLException;

import org.hibernate.HibernateException;

import com.agreeya.chhs.exception.UserException;
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.model.User;
import com.agreeya.chhs.model.UserSession;
import com.agreeya.chhs.request.GetUserInboxRequest;
import com.agreeya.chhs.request.SaveUserRequest;
import com.agreeya.chhs.request.UserRegistrationRequest;
import com.agreeya.chhs.response.UserInboxResponse;

/**
 * Interface for general membership services functions.
 * @author AgreeYa Solutions
 */
public interface MemberService {

	User authenticateUser(String userName, String password);

	void createOrUpdateUserSession(UserSession userSession);

	UserSession findPrevUserSession(User user);

	UserSession findUserSession(String sessionId, int userId);

	String getUserPassword(String userName);

	void logoutUser(int userId);

	User checkUserDetailExist(String userName);

	String getEncryptPassword(String userName, String userStatus);
	
	String registerUser(UserRegistrationRequest request) throws HibernateException, SQLException, UserException, WSException;
	
	String saveUser(SaveUserRequest request) throws HibernateException, SQLException, UserException, WSException;

	UserInboxResponse getUserInbox(GetUserInboxRequest request, UserInboxResponse response);
}
