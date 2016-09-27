package com.agreeya.chhs.bd;

import com.agreeya.chhs.exception.WSException;

/**
 * Interface for AES Security methods
 * @author AgreeYa Solutions
 * 
 */
public interface AESSecurityBD {

	/* overloaded method to encrypt the data for email */
	String encryptAES(String data) throws WSException;
	
	String decryptData(String encodedString) throws WSException;

}
