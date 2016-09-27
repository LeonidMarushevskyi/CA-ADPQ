/**
 * 
 */
package com.agreeya.chhs.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import com.agreeya.chhs.to.UserTO;


/**
 * @author amit.sharma
 *
 */
public interface UserService {


	/**
	 * This method is used to get all the Users
	 * 
	 * @throws HibernateException
	 * @throws SQLException
	 */
	List<UserTO> getAllUser() throws HibernateException, SQLException;
	
	
	
}
