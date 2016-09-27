package com.agreeya.chhs.util;

/**
 * Constants file for Application
 * @author AgreeYa Solutions
 *
 */
public class Constants {
	/* check for the uniqueness in DB */
	public static final String EXIST = "exist";
	public static final String NOT_EXIST = "notexist";

	// Authentication Constants
	public static final int SUBSCRIBER_SESSIONID_LENGTH = 25;

	/**
	 *  error type from web service
	 *
	 */
	public enum ErrorType {
		ERROR, EXCEPTION, WARNING
	};

	public static final String CHHS_LOGGER_QUEUE = "CHHS_LOGGER_QUEUE";

	/* Universal */
	public static final String TRUE = "true";
	
	public static final String CASEID = "CASE-";

	// Application Logging Flag
	public static final String CHHS_APPLICATION_LOG = "application.logging";

	// HHS API url
	public static final String HHS_API_URL = "hhs.api.url";
	public static final String HHS_API_NODATA = "No Facilities found";

	public static final String FALSE = "false";

	// 
	/**
	 * User status in user 
	 *
	 */
	public enum UserStatusType {
		ACTIVE, INACTIVE, INCOMPLETE
	}
}
