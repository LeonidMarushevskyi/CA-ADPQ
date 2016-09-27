package com.agreeya.chhs.service.impl;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.agreeya.chhs.bd.AESSecurityBD;
import com.agreeya.chhs.dao.MemberServiceDAO;
import com.agreeya.chhs.dao.UserDAO;
import com.agreeya.chhs.exception.MembershipException;
import com.agreeya.chhs.exception.UserException;
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.model.User;
import com.agreeya.chhs.model.UserSession;
import com.agreeya.chhs.request.GetUserInboxRequest;
import com.agreeya.chhs.request.SaveUserRequest;
import com.agreeya.chhs.request.UserRegistrationRequest;
import com.agreeya.chhs.response.UserInboxResponse;
import com.agreeya.chhs.service.MemberService;
import com.agreeya.chhs.util.CHHSErrorCodes;
import com.agreeya.chhs.util.Constants;


/**
 * Implementation class for MemberService
 * @author AgreeYa Solutions
 */
public class MemberServiceImpl implements MemberService {

	private static Logger log = Logger.getLogger(MemberServiceImpl.class);
	private MemberServiceDAO memberServiceDAO;
	private UserDAO userDAO;
	private AESSecurityBD aESSecurityBD;

	@Override
	public User authenticateUser(String userName, String password) {
		User user = null;
		try {
			log.info("enter into MemberServiceImpl authenticateUser() method ....................");
			user = memberServiceDAO.authenticateUser(userName, password);
			log.info("exit from MemberServiceImpl authenticateUser() method ....................");
		} catch (MembershipException me) {
			throw new MembershipException(CHHSErrorCodes.UNAUTHORIZED, CHHSErrorCodes.UNAUTHORIZED_MESSAGE);
		}
		return user;
	}

	@Override
	public void createOrUpdateUserSession(UserSession userSession) {
		memberServiceDAO.createOrUpdateUserSession(userSession);

	}

	@Override
	public UserSession findPrevUserSession(User user) {
		return memberServiceDAO.findPrevUserSession(user);
	}

	@Override
	public UserSession findUserSession(String sessionId, int userId) {
		return memberServiceDAO.findUserSession(sessionId, userId);
	}

	@Override
	public String getUserPassword(String userName) {
		log.info("enter into MemberServiceImpl getUserPassword() method ....................");
		User user = memberServiceDAO.findUserByUserName(userName);
		String password = null;
		if (user != null) {
			if (user.getStatus().equalsIgnoreCase(Constants.UserStatusType.ACTIVE.toString())) {
				try {
					password = aESSecurityBD.decryptData(user.getPassword());
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					log.error(CHHSErrorCodes.DECRYPT_DATA_ERROR_MESSAGE, ex);
				}
			} else {
				throw new MembershipException(CHHSErrorCodes.USER_NOT_ACTIVE, CHHSErrorCodes.USER_NOT_ACTIVE_MESSAGE);
			}
		} else {
			throw new MembershipException(CHHSErrorCodes.USER_NOT_FOUND, CHHSErrorCodes.USER_NOT_FOUND_MESSAGE);
		}
		log.info("exit from MemberServiceImpl getUserPassword() method....................");
		return password;
	}

	/*
	 * @purpose : This method is used to check user detail is exist
	 * 
	 * @param : userName
	 * 
	 * 
	 */
	@Override
	public User checkUserDetailExist(String userName) {
		log.info("enter into MemberServiceImpl checkUserDetailExist() method....................");
		return userDAO.checkUserDetailExist(userName);
	}

	@Override
	public String getEncryptPassword(String userName, String userStatus) {
		User usr = null;
		try {
			log.info("enter into MemberServiceImpl authenticateUser () method ....................");
			usr = memberServiceDAO.getEncryptPassword(userName, userStatus);
			log.info("exit from MemberServiceImpl authenticateUser () method ....................");
		} catch (MembershipException me) {
			throw new MembershipException(CHHSErrorCodes.UNAUTHORIZED, CHHSErrorCodes.UNAUTHORIZED_MESSAGE);
		}
		return usr.getPassword();
	}

	public void logoutUser(int userId) {
		log.info("enter into MemberServiceDAOImpl logoutUser() method....................");
		memberServiceDAO.logoutUser(userId);
	}
	
	@Override
	public String registerUser(UserRegistrationRequest request) throws
	        HibernateException, SQLException, UserException, WSException {
		log.info("enter into UserServiceImpl for registerUser method ...................");
		String msg = "";
		
		msg = userDAO.registerUser(request);
		log.info("exit from UserServiceImpl for registerUser method return msg .." + msg);
		return msg;

	}
	
	@Override
	public String saveUser(SaveUserRequest request) throws
	        HibernateException, SQLException, UserException, WSException {
		log.info("enter into UserServiceImpl for saveUser method ...................");
		String msg = "";
		
		msg = userDAO.saveUser(request);
		log.info("exit from UserServiceImpl for saveUser method return msg .." + msg);
		return msg;

	}

	@Override
	public UserInboxResponse getUserInbox(GetUserInboxRequest request, UserInboxResponse response) {
		log.info("enter into UserServiceImpl for getUserInbox method ...................");
		userDAO.getUserInbox(request, response);
		log.info("exit from UserServiceImpl for getUserInbox method");
		return response;
	}

	/*********************** setter getter method *****************************/

	public MemberServiceDAO getMemberServiceDAO() {
		return memberServiceDAO;
	}

	public void setMemberServiceDAO(MemberServiceDAO memberServiceDAO) {
		this.memberServiceDAO = memberServiceDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public AESSecurityBD getaESSecurityBD() {
		return aESSecurityBD;
	}

	public void setaESSecurityBD(AESSecurityBD aESSecurityBD) {
		this.aESSecurityBD = aESSecurityBD;
	}
}