package com.agreeya.chhs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.agreeya.chhs.bd.AESSecurityBD;
import com.agreeya.chhs.bd.MemberServiceBD;
import com.agreeya.chhs.exception.MembershipException;
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.request.CreateUserContextRequest;
import com.agreeya.chhs.request.ForgotPasswordRequest;
import com.agreeya.chhs.request.UserLogoutRequest;
import com.agreeya.chhs.response.CreateUserContextResponse;
import com.agreeya.chhs.response.UserLogoutResponse;
import com.agreeya.chhs.response.user.UserResponse;
import com.agreeya.chhs.to.UserContextTO;
import com.agreeya.chhs.to.UserTO;
import com.agreeya.chhs.util.CHHSErrorCodes;
import com.agreeya.chhs.util.JsonConverter;
import com.agreeya.chhs.util.WSConstants;

/**
 * REST controller for getting UserContext.
 * 
 */
@Path("/contextinit")
public class ContextInitiationController {

	private static Logger log = Logger.getLogger(ContextInitiationController.class);

	private MemberServiceBD memberServiceBD;
	private AESSecurityBD aESSecurityBD;

	/**
	 * creating the UserContext.
	 */
	@POST
	@Path("/createusercontext")
	public Response createUserContext(@Context HttpServletRequest request, @FormParam(WSConstants.REQUEST_PARAM) String rawRequestData)
			throws WSException {
		try {
			log.info("enter into ContextInitiationController createUserContext() method ....................");
			CreateUserContextRequest wsRequest = (CreateUserContextRequest) request.getAttribute(WSConstants.WS_REQUEST_OBJECT);
			wsRequest.validate();
			
			// before decryption first excute encryption method to generate key
			aESSecurityBD.encryptAES("FIX for now");
			// check whether there is a valid user available in subscriber table
			// then get account status, else create new subscriber if doesnt
			// exist
			// if exists then get account status as well.
			// also create a new user context/session for this new subscriber
			// and return it.
			// if a valid session already exists for this use return it or else
			// create new and return
			// assuming that only one valid session exists per user at any
			// point.

			UserContextTO userContextTO = memberServiceBD.authenticateUser(wsRequest.getUserName(), wsRequest.getPassword());
			CreateUserContextResponse response = new CreateUserContextResponse();
			if (userContextTO != null) {
				response.setUserContext(userContextTO);
			} else {
				throw new WSException(CHHSErrorCodes.CREATE_USERCONTEXT_FAILED, CHHSErrorCodes.CREATE_USERCONTEXT_FAILED_MESSAGE);
			}
			String result = JsonConverter.convertJavaObjectToJsonString(response);
			log.info("exit from ContextInitiationController createUserContext() method ....................");
			// for audit logging.
			try {
				request.setAttribute(WSConstants.WS_RESPONSE, result);
			} catch (RuntimeException re) {
				log.info("");
			}
			return Response.ok(result).build();
		} catch (MembershipException aae) {
			String errorCode = (aae.getErrorCode() != null ? aae.getErrorCode() : CHHSErrorCodes.CREATE_USERCONTEXT_FAILED);
			String exceptionMessage = (aae.getExceptionMessage() != null ? aae.getExceptionMessage()
					: CHHSErrorCodes.CREATE_USERCONTEXT_FAILED_MESSAGE);
			log.error(CHHSErrorCodes.CREATE_USERCONTEXT_FAILED_MESSAGE, aae);
			throw new WSException(errorCode, exceptionMessage, aae.getErrorDetails(), aae.getCause());
		} catch (Exception e) {
			log.error(CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
			throw new WSException(CHHSErrorCodes.SERVER_ERROR, CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
		}
	}

	@POST
	@Path("/userlogout")
	public Response logoutUser(@Context HttpServletRequest request, @FormParam(WSConstants.REQUEST_PARAM) String rawRequestData)
			throws WSException {
		try {
			log.info("enter into ContextInitiationController logoutUser() method ....................");
			UserLogoutRequest wsRequest = (UserLogoutRequest) request.getAttribute(WSConstants.WS_REQUEST_OBJECT);
			UserLogoutResponse response = new UserLogoutResponse();

			this.memberServiceBD.logoutUser(wsRequest, response);

			String result = JsonConverter.convertJavaObjectToJsonString(response);
			// for audit logging.
			try {
				request.setAttribute(WSConstants.WS_RESPONSE, result);
			} catch (RuntimeException re) {
				log.info("");
			}
			log.info("exit from ContextInitiationController logoutUser() method ....................");
			return Response.ok(result).build();
		} catch (MembershipException aae) {
			String errorCode = (aae.getErrorCode() != null ? aae.getErrorCode() : CHHSErrorCodes.LOGOUT_USER_FAILED);
			String exceptionMessage = (aae.getExceptionMessage() != null ? aae.getExceptionMessage()
					: CHHSErrorCodes.LOGOUT_USER_FAILED_MESSAGE);
			log.error(CHHSErrorCodes.LOGOUT_USER_FAILED_MESSAGE, aae);
			throw new WSException(errorCode, exceptionMessage, aae.getErrorDetails(), aae.getCause());
		} catch (Exception e) {
			log.error(CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
			throw new WSException(CHHSErrorCodes.SERVER_ERROR, CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
		}
	}

	/**
	 * This service used to check the user name with user mail is exist.
	 * 
	 * @param request
	 * @param rawRequestData
	 * @return
	 * @throws WSException
	 */
	@POST
	@Path("/checkuserdetailexist")
	public Response checkUserDetailExist(@Context HttpServletRequest request, 
			@FormParam(WSConstants.REQUEST_PARAM) String rawRequestData)
			throws WSException {
		try {
			log.info("enter into ContextInitiationController for checkUserDetailExist() method ...................");
			ForgotPasswordRequest wsRequest = (ForgotPasswordRequest) request.getAttribute(WSConstants.WS_REQUEST_OBJECT);
			String userName = wsRequest.getUserName();
			UserTO usr = this.memberServiceBD.checkUserDetailExist(userName);
			UserResponse response = new UserResponse();
			if (null != usr) {
				response.setStatus(WSConstants.RESPONSE_OK);
			} else {
				response.setStatus(WSConstants.RESPONSE_ERROR);
			}
		    response.setUser(usr);
		    ObjectMapper mapper = new ObjectMapper();
		    
			String result = mapper.writeValueAsString(response);
			// for audit logging.
			try {
				request.setAttribute(WSConstants.WS_RESPONSE, result);
			} catch (RuntimeException re) {
				log.info("");
			}
			log.info("Exit from ContextInitiationController from checkUserDetailExist() method ...............");
			return Response.ok(result).build();

		} catch (MembershipException ex) {
			String errorCode = (ex.getErrorCode() != null ? ex.getErrorCode() : CHHSErrorCodes.USER_DETAILS_CHECK_FAILED);
			String exceptionMessage = (ex.getExceptionMessage() != null ? ex.getExceptionMessage()
					: CHHSErrorCodes.USER_DETAILS_CHECK_FAILED_MESSAGE);
			log.error(CHHSErrorCodes.USER_DETAILS_CHECK_FAILED);
			log.error(CHHSErrorCodes.USER_DETAILS_CHECK_FAILED_MESSAGE, ex);
			throw new WSException(errorCode, exceptionMessage, ex.getErrorDetails(), ex.getCause());
		} catch (Exception e) {
			log.error(CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
			throw new WSException(CHHSErrorCodes.SERVER_ERROR, CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
		}
	}

	/************************* Spring DI hooks **********************************/

	public MemberServiceBD getMemberServiceBD() {
		return memberServiceBD;
	}

	public void setMemberServiceBD(MemberServiceBD memberServiceBD) {
		this.memberServiceBD = memberServiceBD;
	}

	public AESSecurityBD getaESSecurityBD() {
		return aESSecurityBD;
	}

	public void setaESSecurityBD(AESSecurityBD aESSecurityBD) {
		this.aESSecurityBD = aESSecurityBD;
	}

}
