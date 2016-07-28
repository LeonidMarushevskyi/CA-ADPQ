package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.auth.Cryptography;
import com.tabordasolutions.cws.parentportal.auth.Session;

public class SessionService {
    private static final int USERNAME = 0;
    private static final int PASSWORD = 1;

    private UserService userService;
    private Cryptography cryptography;

    public SessionService(UserService userService, Cryptography cryptography) {
        this.userService = userService;
        this.cryptography = cryptography;
    }

    public Session login(String username, String password) {
        User user = userService.findUserByUserName(username);
        boolean valid = user == null ? false : isValid(password, user);
        long userId = user != null ? user.getId() : -1;
        return new Session(valid, userId, buildToken(username, password));
    }

    public Session loginWithToken(String token){
        String usernameAndPassword  = cryptography.decrypt(token);
        String[] credentials = usernameAndPassword.split(":");
        return login(credentials[USERNAME], credentials[PASSWORD]);
    }

    public  User getUserByToken(String token) {
        Session session = loginWithToken(token);
        long userId = session.getUserId();
        return userService.findUserById(userId);
    }

    public User getUser(long id) {
        return userService.findUserById(id);
    }

    private boolean isValid(String string, User user) {
        //return string != null && string.length() > 0 && Character.isLowerCase(string.charAt(0));
    	return string != null && string.length() > 0 && user.getPassword().trim().equals(string.trim());
    }

    private String buildToken(String userName, String password) {
        String phrase = userName + ":" + password;
        return cryptography.encrypt(phrase);
    }
}
