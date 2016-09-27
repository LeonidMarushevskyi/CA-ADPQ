package com.agreeya.chhs.util;

/**
 * error codes and messages sent in the REST API responses
 * @author AgreeYa Solutions
 *
 */
public class CHHSErrorCodes {

	// ------------------------------------------------------------------//
	// General error codes and messages
	// ------------------------------------------------------------------//
	public static final String SERVICE_NOT_AVAILABLE_CODE = "GEN-000";
	public static final String UNAUTHORIZED = "GEN-001";
	public static final String ACCESS_DENIED = "GEN-002";
	public static final String INVALID_SERVICE_REQUEST = "GEN-003";
	public static final String REQUEST_PAYLOAD_NOT_FOUND = "GEN-004";
	public static final String INVALID_REQUEST_PAYLOAD_STRUCTURE = "GEN-005";
	public static final String INVALID_REQUEST_PARAMETERS = "GEN-006";
	public static final String INVALID_USERNAME_PASSWORD = "GEN-007";
	public static final String SERVER_ERROR = "GEN-008";
	public static final String INVALID_SESSION_ID = "GEN-010";
	public static final String TIMEOUT_EXCEPTION = "GEN-011";
	public static final String SECURE_PROTOCOL_REQUIRED = "GEN-012";
	public static final String CREATE_USERCONTEXT_FAILED = "GEN-018";
	public static final String LOGOUT_USER_FAILED = "GEN-019";
	public static final String INVALID_REQUEST = "GEN-020";

	public static final String SERVICE_NOT_AVAILABLE_MSG = "CHHS System is currently unavailable.  Please try again later.";
	public static final String UNAUTHORIZED_MESSAGE = "Unauthorized access";
	public static final String ACCESS_DENIED_MESSAGE = "Access denied";
	public static final String INVALID_SERVICE_REQUEST_MESSAGE = "Invalid service request";
	public static final String REQUEST_PAYLOAD_NOT_FOUND_MESSAGE = "Request payload not found";
	public static final String INVALID_REQUEST_PAYLOAD_STRUCTURE_MESSAGE = "Invalid request payload structure";
	public static final String INVALID_REQUEST_PARAMETERS_MESSAGE = "Invalid request parameter";
	public static final String INVALID_USERNAME_PASSWORD_MESSAGE = "Invalid username or password";
	public static final String UNSUPPORTED_REQUEST_CONTENT_TYPE_MESSAGE = "Unsupported requested content type";
	public static final String SERVER_ERROR_MESSAGE = "Failed to process request";
	public static final String INVALID_SESSION_ID_MESSAGE = "Invalid Session id";
	public static final String SECURE_PROTOCOL_REQUIRED_MESSAGE = "SSL Protocol required";
	public static final String CREATE_USERCONTEXT_FAILED_MESSAGE = "Unable to create User Context";
	public static final String LOGOUT_USER_FAILED_MESSAGE = "Unable to logout the user";
	public static final String GET_REGISTERED_USER_FAILED_MESSAGE = "get registered user failed";
	public static final String INVALID_REQUEST_MESSAGE = "INVALID_REQUEST";
	public static final String USER_DETAILS_CHECK_FAILED = "USR-010";
	public static final String USER_DETAILS_CHECK_FAILED_MESSAGE = "Failed to check the user detail";
	

	
	public static final String GET_FACILITIES_FAILED = "FAC-001";
	public static final String GET_FACILITIES_FAILED_MESSAGE = "Failed to get Facilities";
	
	
	
	// ------------------------------------------------------------------//
	// AES encryption/decryption error codes and messages
	// ------------------------------------------------------------------//
	public static final String AES_ENCRYPTION_FAILED = "AES-001";
	public static final String AES_DECRYPTION_FAILED = "AES-002";
	public static final String AES_GET_WSPARAM_FAILED = "AES-003";
	public static final String AES_ENCRYPTION_FAILED_FAILED_MESSAGE = "Failed to encrypt text by AES algo.";
	public static final String AES_DECRYPTION_FAILED_FAILED_MESSAGE = "Failed to decrypt text by AES algo.";
	public static final String GET_AES_ENCRYPT_PARAM_FAILD = "AES-004";
	public static final String GET_AES_ENCRYPT_PARAM_FAILD_MESSAGE = "Failed to get AES encrypted param";
	public static final String REQUESTED_PARAM_NOT_FOUND = "AES-005";
	public static final String REQUESTED_PARAM_NOT_FOUND_MESSAGE = "data is not exist for this request";
	public static final String LINK_EXPIRED = "AES-006";
	public static final String LINK_EXPIRED_MESSAGE = "link has been expired";
	public static final String DECRYPT_DATA_ERROR_MESSAGE = "data not decrypt";
	public static final String DATA_ENCRYPTION_ERROR_MESSAGE = "data not encrypted";

	public static final String GET_WSPARAM_FAILED_FAILED_MESSAGE = "Failed to get web service param by api key";
	// ------------------------------------------------------------------//
	// MD5 encryption/decryption error codes and messages
	// ------------------------------------------------------------------//
	public static final String MD5_ENCRYPTION_FAILED = "AES-001";
	public static final String MD5_DECRYPTION_FAILED = "AES-002";
	public static final String MD5_ENCRYPTION_FAILED_FAILED_MESSAGE = "Failed to encrypt text by MD5 algo.";
	public static final String MD5_DECRYPTION_FAILED_FAILED_MESSAGE = "Failed to decrypt text by MD5 algo.";

	// ------------------------------------------------------------------//
	// codes and messaging when retrieving the role for the components
	// ------------------------------------------------------------------//
	public static final String ROLE_COMPONENT_NOT_FOUND = "RLCMP-001";
	public static final String ROLE_COMPONENT_NOT_FOUND_MESSAGE = "Role for the component not found";

	public static final String USER_REGISTRATION_FAILED = "USR-001";
	public static final String USER_REGISTRATION_FAILED_MESSAGE = "User registration failed";
	public static final String USER_SAVE_FAILED = "USR-011";
	public static final String USER_SAVE_FAILED_MESSAGE = "USer save/update failed";
	public static final String USER_NOT_ACTIVE = "USR-002";
	public static final String USER_NOT_ACTIVE_MESSAGE = "USer is not Active";
	public static final String USER_NOT_FOUND = "USR-003";
	public static final String USER_NOT_FOUND_MESSAGE = "User Not found";
	public static final String SAVE_USER_FAILED_MESSAGE = "Failed to save Users";
	
	
	public static final String USER_INBOX_GET_FAILED = "INB-001";
	public static final String  USER_INBOX_GET_FAILED_MESSAGE = "Failed to check the user detail";
	
	public static final String CHANGE_DATE_FROM_STRING_FAILED_MESSAGE = "Failed to convert date from string";
}
