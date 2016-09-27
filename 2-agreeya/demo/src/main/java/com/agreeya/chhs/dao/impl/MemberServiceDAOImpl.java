package com.agreeya.chhs.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.agreeya.chhs.dao.MemberServiceDAO;
import com.agreeya.chhs.exception.MembershipException;
import com.agreeya.chhs.model.User;
import com.agreeya.chhs.model.UserSession;
import com.agreeya.chhs.util.Constants;

/**
 * This class has methods that store and fetches the membership details
 * @author amit.sharma
 *
 */
@Transactional
public class MemberServiceDAOImpl extends BaseDAOImpl implements MemberServiceDAO {

	private static Logger log = Logger.getLogger(MemberServiceDAOImpl.class);

	/*
	 * 
	 * @purpose : This method is used to find login
	 * 
	 * @param : userName Password
	 */
	@Override
	public User authenticateUser(String userName, String password) {
		log.info("enter into MemberServiceDAOImpl authenticateUser() mtehod ....................");
		if (log.isInfoEnabled()) {
			log.info("methos authenticateUser Started");
		}

		Query query = sessionFactory.getCurrentSession()
				.createQuery("From User ua where ua.userName = :userName and ua.status IN ('ACTIVE','INCOMPLETE')");
		query.setParameter("userName", userName);
		return (User) query.uniqueResult();
	}

	/*
	 * 
	 * @purpose : This method is used to create user Session
	 * 
	 * @param : user
	 */
	@Override
	public void createOrUpdateUserSession(UserSession userSession) {
		log.info("enter into MemberServiceDAOImpl createOrUpdateUserSession() method ....................");
		if (log.isInfoEnabled()) {
			log.info("method createOrUpdateUserSession Started");
		}

		if (userSession == null) {
			log.error("User Session Details is not provided!");
			throw new MembershipException("User Session Details is not provided!");
		}

		if (log.isDebugEnabled()) {
			log.debug("Subscriber id: " + userSession.getUser().getUserID() + " Session Id:" + userSession.getSessionId());
		}

		sessionFactory.getCurrentSession().merge(userSession);

		if (log.isInfoEnabled()) {
			log.info("methos createOrUpdateUserSession completeds");
		}

	}

	/*
	 * 
	 * @purpose : This method is used to find existing user Session
	 * 
	 * @param : userId Session Id
	 */
	@Override
	public UserSession findUserSession(String sessionId, int userId) {
		log.info("enter into MemberServiceDAOImpl findUserSession() method....................");
		Query query = sessionFactory.getCurrentSession()
				.createQuery("From UserSession us where us.user.userID = :id and us.sessionId = :sessId");
		query.setParameter("id", userId);
		query.setParameter("sessId", sessionId);
		return (UserSession) query.uniqueResult();

	}

	/*
	 * 
	 * @purpose : This method is used to find existing user Session
	 * 
	 * @param : user
	 */
	@Override
	public UserSession findPrevUserSession(User user) {
		log.info("enter into MemberServiceDAOImpl findPrevUserSession() method ....................");
		Query query = sessionFactory.getCurrentSession()
				.createQuery("From UserSession as us where us.user.userID = :usrId");
		query.setParameter("usrId", user.getUserID());
		return (UserSession) query.uniqueResult();

	}

	/*
	 * 
	 * @purpose : This method is used to Find Existing User Details
	 * 
	 * @param : userName
	 */
	@Override
	public User findUserByUserName(String userName) {
		log.info("enter into MemberServiceDAOImpl findUserByUserName() method...................");
		Query query = sessionFactory.getCurrentSession().createQuery("From User ua where ua.userName = :usrname");
		query.setParameter("usrname", userName);
		return (User) query.uniqueResult();
	}

	/*
	 * 
	 * @purpose : This method is used to logout the user and set the user
	 * session expiry date to current date
	 * 
	 * @param : userid
	 */
	@Override
	@Transactional
	public void logoutUser(int userId) {
		log.info("enter into MemberServiceDAOImpl logoutUser() method....................");
		Query query = sessionFactory.getCurrentSession()
				.createQuery("Update UserSession us set us.expiryDate=now()" + " where us.user.userID= :userId ");
		query.setParameter("userId", userId);

		query.executeUpdate();
	}

	@Override
	public User getEncryptPassword(String userName, String userStatus) {
		log.info("enter into MemberServiceDAOImpl getEncryptPassword() method ....................");
		User user = null;
		if (log.isInfoEnabled()) {
			log.info("methos getEncriptPassword() Started");
		}
		if (userStatus.equals(Constants.UserStatusType.ACTIVE.toString())) {

			Query query = sessionFactory.getCurrentSession()
					.createQuery("From User ua where ua.userName = :userName "
					  + "and  ua.status IN ('ACTIVE','INCOMPLETE')");
			query.setParameter("userName", userName);
			user = (User) query.uniqueResult();

		} else if (userStatus.equals(Constants.UserStatusType.INACTIVE.toString())) {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("From User ua where ua.userName = ? AND ua.status = 'inactive'");
			query.setParameter("userName", userName);
			user = (User) query.uniqueResult();
		}

		return user;
	}

}
