/**
 * 
 */
package com.agreeya.chhs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.agreeya.chhs.bd.MemberServiceBD;
import com.agreeya.chhs.exception.UserException;
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.request.GetUserInboxRequest;
import com.agreeya.chhs.request.SaveUserRequest;
import com.agreeya.chhs.request.UserRegistrationRequest;
import com.agreeya.chhs.response.SaveUserResponse;
import com.agreeya.chhs.response.UserInboxResponse;
import com.agreeya.chhs.response.UserRegistrationResponse;
import com.agreeya.chhs.to.UserContextTO;
import com.agreeya.chhs.util.CHHSErrorCodes;
import com.agreeya.chhs.util.JsonConverter;
import com.agreeya.chhs.util.WSConstants;

/**
 * REST controller for getting UserDetails, save register.
 * 
 * @author AgreeYa Solutions 
 */
@Path("/member")
public class MembershipController {
	private static Logger log = Logger.getLogger(MembershipController.class);
	MemberServiceBD memberServiceBD;

	/**
	 * This service will register new user
	 * 
	 * @param request
	 * @param rawRequestData
	 * @return
	 * @throws WSException
	 */
	@POST
	@Path("/register")
	public Response registerUser(@Context HttpServletRequest request,
			@FormParam(WSConstants.REQUEST_PARAM) String rawRequestData) throws WSException {
		try {
			log.info("enter into MembershipController in registerUser() method...................");
			UserRegistrationRequest wsRequest = (UserRegistrationRequest)
					request.getAttribute(WSConstants.WS_REQUEST_OBJECT);
			
			UserRegistrationResponse response = new UserRegistrationResponse();
			
			response = memberServiceBD.registerUser(wsRequest, response);
			UserContextTO userContextTO = null;
			if (!response.getMessage().equalsIgnoreCase("User already Exist with provided username and email")) {
				userContextTO = memberServiceBD.authenticateUser(wsRequest.getPersonalProfile().getUserName(),
						wsRequest.getPersonalProfile().getPassword());
				if (userContextTO != null) {
					response.setUserContext(userContextTO);
				} else {
					throw new WSException(CHHSErrorCodes.CREATE_USERCONTEXT_FAILED,
							CHHSErrorCodes.CREATE_USERCONTEXT_FAILED_MESSAGE);
				}
			}
			
			String result = JsonConverter.convertJavaObjectToJsonString(response);
			// for audit logging.
			try {
				request.setAttribute(WSConstants.WS_RESPONSE, result);
			} catch (RuntimeException re) {
				log.info("Runtime Exception occurred");
			}
			log.info("Exit from MembershipController from registerUser() method ...............");
			return Response.ok(result).build();
		} catch (UserException ex) {
			String errorCode = (ex.getErrorCode() != null ? ex.getErrorCode() : CHHSErrorCodes.USER_REGISTRATION_FAILED);
			String exceptionMessage = (ex.getExceptionMessage() != null ? ex.getExceptionMessage() 
					: CHHSErrorCodes.USER_REGISTRATION_FAILED);
			log.error(CHHSErrorCodes.USER_REGISTRATION_FAILED);
			log.error(CHHSErrorCodes.USER_REGISTRATION_FAILED_MESSAGE, ex);
			throw new WSException(errorCode, exceptionMessage, ex.getErrorDetails(), ex.getCause());
		} catch (Exception e) {
			log.error(CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
			throw new WSException(CHHSErrorCodes.SERVER_ERROR, CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
		}

	}
	
	
	/**
	 * This service will save user profile
	 * 
	 * @param request
	 * @param rawRequestData
	 * @return
	 * @throws WSException
	 */
	@POST
	@Path("/save")
	public Response saveUser(@Context HttpServletRequest request,
			@FormParam(WSConstants.REQUEST_PARAM) String rawRequestData) throws WSException {
		try {
			log.info("enter into MembershipController in saveUser() method...................");
			SaveUserRequest wsRequest = (SaveUserRequest)
					request.getAttribute(WSConstants.WS_REQUEST_OBJECT);
			
			SaveUserResponse response = new SaveUserResponse();
			
			response = memberServiceBD.saveUser(wsRequest, response);
			response.setUserContext(wsRequest.getUserContext());
			
			
			String result = JsonConverter.convertJavaObjectToJsonString(response);
			// for audit logging.
			try {
				request.setAttribute(WSConstants.WS_RESPONSE, result);
			} catch (RuntimeException re) {
				log.info("Runtime Exception occurred");
			}
			log.info("Exit from MembershipController from saveUser() method ...............");
			return Response.ok(result).build();
		} catch (UserException ex) {
			String errorCode = (ex.getErrorCode() != null ? ex.getErrorCode() : CHHSErrorCodes.USER_SAVE_FAILED);
			String exceptionMessage = (ex.getExceptionMessage() != null ? ex.getExceptionMessage() 
					: CHHSErrorCodes.USER_SAVE_FAILED);
			log.error(CHHSErrorCodes.USER_SAVE_FAILED);
			log.error(CHHSErrorCodes.USER_SAVE_FAILED_MESSAGE, ex);
			throw new WSException(errorCode, exceptionMessage, ex.getErrorDetails(), ex.getCause());
		} catch (Exception e) {
			log.error(CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
			throw new WSException(CHHSErrorCodes.SERVER_ERROR, CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
		}

	}
	
	
	/**
	 * This service will save user profile
	 * 
	 * @param request
	 * @param rawRequestData
	 * @return
	 * @throws WSException
	 */
	@POST
	@Path("/inbox")
	public Response getUserInbox(@Context HttpServletRequest request,
			@FormParam(WSConstants.REQUEST_PARAM) String rawRequestData) throws WSException {
		try {
			log.info("enter into MembershipController in getUserInbox() method...................");
			GetUserInboxRequest wsRequest = (GetUserInboxRequest)
					request.getAttribute(WSConstants.WS_REQUEST_OBJECT);
			
			UserInboxResponse response = new UserInboxResponse();
			
			response = memberServiceBD.getUserInbox(wsRequest, response);
			
			String result = JsonConverter.convertJavaObjectToJsonString(response);
			// for audit logging.
			try {
				request.setAttribute(WSConstants.WS_RESPONSE, result);
			} catch (RuntimeException re) {
				log.info("Runtime Exception occurred");
			}
			log.info("Exit from MembershipController from getUserInbox() method ...............");
			return Response.ok(result).build();
		} catch (UserException ex) {
			String errorCode = (ex.getErrorCode() != null ? ex.getErrorCode() : CHHSErrorCodes.USER_INBOX_GET_FAILED);
			String exceptionMessage = (ex.getExceptionMessage() != null ? ex.getExceptionMessage() 
					: CHHSErrorCodes.USER_INBOX_GET_FAILED);
			log.error(CHHSErrorCodes.USER_INBOX_GET_FAILED);
			log.error(CHHSErrorCodes.USER_INBOX_GET_FAILED_MESSAGE, ex);
			throw new WSException(errorCode, exceptionMessage, ex.getErrorDetails(), ex.getCause());
		} catch (Exception e) {
			log.error(CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
			throw new WSException(CHHSErrorCodes.SERVER_ERROR, CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
		}

	}

	/************************* Getter and Setters ***************************/
	public MemberServiceBD getMemberServiceBD() {
		return memberServiceBD;
	}

	public void setMemberServiceBD(MemberServiceBD memberServiceBD) {
		this.memberServiceBD = memberServiceBD;
	}
}
