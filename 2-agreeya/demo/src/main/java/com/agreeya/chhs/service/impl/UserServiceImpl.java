package com.agreeya.chhs.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.agreeya.chhs.dao.UserDAO;
import com.agreeya.chhs.service.UserService;
import com.agreeya.chhs.to.UserTO;

/**
 * @author amit.sharma
 * 
 */
public class UserServiceImpl implements UserService {

	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private UserDAO userDAO;

	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	/**
	 * This Method to Get All user from DB
	 * 
	 * @throws SQLException
	 * @throws HibernateException
	 */
	public List<UserTO> getAllUser() throws HibernateException, SQLException {
		log.info("enter into UserServiceImpl for getAllUser method ...................");
		return null;
	}

	/************************** Getter and Setters ******************************/
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
