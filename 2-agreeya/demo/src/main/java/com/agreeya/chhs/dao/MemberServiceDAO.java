package com.agreeya.chhs.dao;

import com.agreeya.chhs.model.User;
import com.agreeya.chhs.model.UserSession;

/**
 * Provides methods to store and fetches the membership details.
 *  @author AgreeYa Solutions
 * 
 */

public interface MemberServiceDAO extends BaseDAO {

	User authenticateUser(String userName, String password);

	void createOrUpdateUserSession(UserSession userSession);

	UserSession findPrevUserSession(User user);

	UserSession findUserSession(String sessionId, int userId);

	User findUserByUserName(String userName);

	void logoutUser(int userId);

	User getEncryptPassword(String userName, String userStatus);
}
