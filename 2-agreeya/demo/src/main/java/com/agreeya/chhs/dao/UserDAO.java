/**
 * 
 */
package com.agreeya.chhs.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;

import com.agreeya.chhs.exception.UserException;
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.model.User;
import com.agreeya.chhs.request.GetUserInboxRequest;
import com.agreeya.chhs.request.SaveUserRequest;
import com.agreeya.chhs.request.UserRegistrationRequest;
import com.agreeya.chhs.response.UserInboxResponse;

/**
 * Provides methods to store and fetches the user details.
 * @author AgreeYa Solutions
 * 
 */
public interface UserDAO extends BaseDAO {

	/**
	 * check user exist by user name and user email id.
	 * 
	 * @param username
	 * @return string msg
	 */
	User checkUserDetailExist(String userName);
	
	String registerUser(UserRegistrationRequest request)  
			throws HibernateException, SQLException, UserException, WSException;
	
	String saveUser(SaveUserRequest request)  
			throws HibernateException, SQLException, UserException, WSException;

	UserInboxResponse getUserInbox(GetUserInboxRequest request, UserInboxResponse response);

	String getUserSessionForTest();
}
