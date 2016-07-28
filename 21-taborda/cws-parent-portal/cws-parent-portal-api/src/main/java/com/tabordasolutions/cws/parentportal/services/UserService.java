package com.tabordasolutions.cws.parentportal.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.UserDAO;

public class UserService {
	public static final String CASEWORKER = "cws.com";
	public static final Logger LOGGER = LoggerFactory
			.getLogger(UserService.class);
    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public User findUserByUserName(String username){
        return dao.findByUsername(username.toLowerCase());
    }

    public User findUserById(long id){
		User user = dao.find(id);
		user = loadCaseworkers(user);
		return user;
    }
    
    /**
     * Updates the given user.  Ensures user exists and email is unique.
     * 
     * @param id	The id of the user
     * @param user	The user to be updated
     * @return		The updated user
     */
    public User updateUser(long id, User user) {
    	User existingUser = findUserById(id);
    	//TODO : let db handle checks below.  Use ExceptionMapper.
    	if(existingUser == null) {
    		LOGGER.warn("User with id:{} not found", id);
    		throw new ServicesException("User does not exist");
    	}
    	User existingWithUsername = findUserByUserName(user.getEmail().toLowerCase());
    	if( existingWithUsername != null && existingWithUsername.getId() != id) {
    		LOGGER.warn("Unable to update user email to non unique email {}", user.getEmail());
    		throw new ServicesException("Email not unique");
    	}
    	if( isPasswordChanged(user) ) {
    		if( !isCorrectPassword(user, existingWithUsername)) {
	    		LOGGER.warn("Unabl to update user, password incorrect {}", user.getEmail());
    			throw new ServicesException("Password incorrect" )	;
	    	}
    	}

    	return dao.update(copyUser(existingUser, user));
    }

	private User loadCaseworkers(User user){
		String caseworkerPattern = "%" + CASEWORKER;
		List<User> caseworkers = dao.findCaseworkers(caseworkerPattern);
		if(user != null && !isCaseworker(user)) {
			user.setCaseworkers(caseworkers);
		}
		return user;
	}

	private boolean isCaseworker(User user){
		return user.getEmail().endsWith(CASEWORKER);
	}
    	
    private boolean isPasswordChanged(User user) {
    	return( user.getNewPassword() != null && user.getPassword().trim().length() > 0); 
    }
    
    private boolean isCorrectPassword(User user, User existingUser) {
    	String existingPasswordFromDb = existingUser.getPassword();
    	existingPasswordFromDb = existingPasswordFromDb != null ? existingPasswordFromDb.trim() : "";
    	
    	String passwordFromUser = user.getPassword();
    	passwordFromUser = passwordFromUser != null ? passwordFromUser.equals("null") ? "" : passwordFromUser.trim() : "";

    	return existingPasswordFromDb.trim().equals(passwordFromUser.trim());
    }

    /**
     * Creates a new user. Ensures user does not already exist.
     * 
     * @param user	The user to be created
     * @return	The created user ( new id is populated )
     */
    public long createUser(User user) {
    	//TODO : let db handle check below.  Use ExceptionMapper.
    	User existingUser = findUserByUserName(user.getEmail().toLowerCase());
    	if( existingUser == null ) {
    		User.Builder builder = new User.Builder();
			existingUser = builder
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.inCareOf(user.getInCareOf())
					.streetAddress1(user.getStreetAddress1())
					.streetAddress2(user.getStreetAddress2())
					.state(user.getState())
					.city(user.getCity())
					.zip(user.getZip())
					.imageUrl(user.getImageUrl())
					.email(user.getEmail().toLowerCase())
					.password(user.getPassword())
					.build(); 
    		return dao.create(existingUser);	
    	} else {
    		LOGGER.warn("Unable to create user with non unique email {}", user.getEmail());
    		throw new ServicesException("Email not unique" );
    	}
    }
    
    private User copyUser(User user, User copyFrom) {
    	user.setFirstName(copyFrom.getFirstName());
    	user.setLastName(copyFrom.getLastName());
    	user.setInCareOf(copyFrom.getInCareOf());
    	user.setStreetAddress1(copyFrom.getStreetAddress1());
    	user.setStreetAddress2(copyFrom.getStreetAddress2());
    	user.setState(copyFrom.getState());
    	user.setCity(copyFrom.getCity());
    	user.setZip(copyFrom.getZip());
    	user.setImageUrl(copyFrom.getImageUrl());
    	user.setEmail(copyFrom.getEmail().toLowerCase());
    	if(  (copyFrom.getNewPassword() != null && copyFrom.getNewPassword().trim().length() > 0 )) {
    		user.setPassword(copyFrom.getNewPassword().trim());
    	}
    	return user;
    }
}
