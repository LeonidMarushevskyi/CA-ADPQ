package com.agreeya.chhs.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONValue;

import com.agreeya.chhs.bd.MemberServiceBD;
import com.agreeya.chhs.exception.CHHSException;
import com.agreeya.chhs.exception.ErrorDetail;
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.model.ApplicationLog;
import com.agreeya.chhs.request.AbstractRequiresUserContext;
import com.agreeya.chhs.request.ValidationResult;
import com.agreeya.chhs.request.WSRequest;
import com.agreeya.chhs.response.WSErrorResponse;
import com.agreeya.chhs.service.ApplicationLoggerService;
import com.agreeya.chhs.to.UserContextTO;
import com.agreeya.chhs.util.CHHSErrorCodes;
import com.agreeya.chhs.util.Constants;
import com.agreeya.chhs.util.JsonConverter;
import com.agreeya.chhs.util.ResourceBundleUtil;
import com.agreeya.chhs.util.WSConstants;
import com.agreeya.chhs.util.XMLBinder;

/**
 * Intercepting filter for the REST services.
 * 
 * This filter validates the input data and the REST URL syntax, converts the
 * incoming requests into the corresponding Java objects, catches and translates
 * exceptions from the lower stack and transmits them to the client. It also
 * logs the API calls for auditing purposes.
 * 
 * Tasks Done: intercept of request Api request object creation Application
 * logging Role identification and permission check
 * 
 * @author AgreeYa Solutions
 */
public class WSRequestFilter implements Filter {

	private static Logger log = Logger.getLogger(WSRequestFilter.class);

	private MemberServiceBD memberServiceBD;

	private Map<String, String> requestClassMap;


	String requestMethod = "";

	// private MemberServiceBD memberServiceBD;
	private ApplicationLoggerService applicationLoggerService;

	private Validator validator;

	// TODO check whether to put this flag in system config DB
	private boolean loggingFlag = Boolean.valueOf(ResourceBundleUtil.getProperty(Constants.CHHS_APPLICATION_LOG));

	/**
	 * Servlet filter's lifecycle method. Catches any exceptions that might bubble up from the layers below, and translates them into
	 * appropriate format for sending them to the client.
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		ApplicationLog applicationLog = null;
		long startTime = 0;

		try {
			// For Application Logging
			applicationLog = new ApplicationLog();
			startTime = Calendar.getInstance().getTimeInMillis();

			request = new HttpServletRequestCopier((HttpServletRequest) request);
			// calling create application log object
			createApplicationLogObject((HttpServletRequest) request, applicationLog);

			// sanity check of the API call
			// API URL, payload structure, contenttype, input data validation,
			// session validity, authentication/authorization
			preProcess(request);

		} catch (WSException ex) {
			log.error("Error in preprocessing ", ex);

			WSErrorResponse wsError = new WSErrorResponse(ex.getErrorCode(), ex.getExceptionMessage(), ex.getErrorDetails());
			String errorResponseText = JsonConverter.convertJavaObjectToJsonString(wsError);
			request.setAttribute(WSConstants.WS_RESPONSE, errorResponseText);

			PrintWriter out = response.getWriter();
			try {
				out.println(errorResponseText);
			} catch (Exception e) {
				log.error("Error in writing to response writer ", e);
			}
			// For Application Log
			if (loggingFlag) {
				long endTime = Calendar.getInstance().getTimeInMillis();
				applicationLog.setResponseJSON(errorResponseText);
				applicationLog.setErrorCode(ex.getErrorCode());
				applicationLog.setErrorText(ex.getExceptionMessage());
				applicationLog.setStatus(wsError.getStatus());
				applicationLog.setRequestJSON((String) request.getAttribute(WSConstants.REQUEST_PARAM));
				applicationLog.setExecutionTime(BigInteger.valueOf(endTime - startTime));
				// Appilcation Logging
				applicationLoggerService.log(applicationLog);
			}

			return;
		} catch (Exception e) {
			log.error("Error in preprocessing request..", e);

			// respond to the user.
			Object[] errorDetails = getErrorStatusAndMessage(e);
			WSErrorResponse wsError = new WSErrorResponse((String) errorDetails[0], (String) errorDetails[1],
					(List<ErrorDetail>) errorDetails[2]);
			PrintWriter out = response.getWriter();
			String errorResponseText = "";
			try {
				errorResponseText = JsonConverter.convertJavaObjectToJsonString(wsError);
				request.setAttribute(WSConstants.WS_RESPONSE, errorResponseText);
				out.println(errorResponseText);
			} catch (Exception je) {
				log.error("Error in writing error response", je);
			}

			// For Application Log
			if (loggingFlag) {
				long endTime = Calendar.getInstance().getTimeInMillis();
				applicationLog.setResponseJSON(errorResponseText);
				applicationLog.setErrorCode((String) errorDetails[0]);
				applicationLog.setErrorText((String) errorDetails[1]);
				applicationLog.setStatus(wsError.getStatus());
				applicationLog.setRequestJSON((String) request.getAttribute(WSConstants.REQUEST_PARAM));
				applicationLog.setExecutionTime(BigInteger.valueOf(endTime - startTime));
				applicationLoggerService.log(applicationLog);
			}
			return;
		}

		try {

			// pass the call down the stack to the REST controllers
			chain.doFilter(request, response);
			// For Application Log- for success case
			if (loggingFlag) {
				long endTime = Calendar.getInstance().getTimeInMillis();
				applicationLog.setStatus("1");
				applicationLog.setRequestJSON((String) request.getAttribute(WSConstants.REQUEST_PARAM));
				applicationLog.setResponseJSON((String) request.getAttribute(WSConstants.WS_RESPONSE));
				applicationLog.setExecutionTime(BigInteger.valueOf(endTime - startTime));
				applicationLoggerService.log(applicationLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in filter chain", e);
			String errorResponseText = "";
			// respond to the user.
			Object[] errorDetails = getErrorStatusAndMessage(e);
			WSErrorResponse wsError = new WSErrorResponse((String) errorDetails[0], (String) errorDetails[1],
					(List<ErrorDetail>) errorDetails[2]);
			PrintWriter out = response.getWriter();
			try {
				errorResponseText = JsonConverter.convertJavaObjectToJsonString(wsError);
				request.setAttribute(WSConstants.WS_RESPONSE, errorResponseText);
				out.println(errorResponseText);
			} catch (Exception je) {
				log.error("Error in writing error response", je);
			}

			// For Application Log
			if (loggingFlag) {
				long endTime = Calendar.getInstance().getTimeInMillis();
				applicationLog.setResponseJSON(errorResponseText);
				applicationLog.setErrorCode((String) errorDetails[0]);
				applicationLog.setErrorText((String) errorDetails[1]);
				applicationLog.setStatus(wsError.getStatus());
				applicationLog.setRequestJSON((String) request.getAttribute(WSConstants.REQUEST_PARAM));
				applicationLog.setExecutionTime(BigInteger.valueOf(endTime - startTime));
				applicationLoggerService.log(applicationLog);
			}
		}

	}

	/**
	 * Validates the REST URL syntax, converts the incoming requests into the corresponding Java objects and validates the input data.
	 * 
	 * @param request
	 * @param response
	 * @throws WSException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void preProcess(ServletRequest request) throws WSException, IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		log.info("Inside preProcess method");
		populateContextFromHeader(request);
		//String supportedContentType = httpRequest.getContentType();
		
		String supportedContentType = "application/json";
		
		String requestUri = httpRequest.getRequestURI();
		Object reqObj = null;
		log.info("requestUri : " + requestUri);
		String apiName = getAPIName(requestUri);
		if (apiName == null) {
			throw new WSException("Gen-003", "Invalid service request");
		}
		String apiClassName = "";
		boolean requestValidation = false;
		boolean isRequestBodyRequired = true;
		if ("GET".equals(requestMethod) || "DELETE".equals(requestMethod)) {
			isRequestBodyRequired = false;
			if (getRequestClass(apiName) == null) {
				apiName = stripPathVariables(apiName);
				requestValidation = false;
			}
		}
		String requestXML = "";
		if (isRequestBodyRequired) {
			requestValidation = true;
			// Content-Type check
			if (!((supportedContentType != null) && supportedContentType.equals(WSConstants.REQ_CONTENT_TYPE_JSON)
					|| supportedContentType.equals(WSConstants.REQ_CONTENT_TYPE_XML)
					|| supportedContentType.equals(WSConstants.REQ_CONTENT_TYPE_JSON_UTF))) {
				log.error(CHHSErrorCodes.UNSUPPORTED_REQUEST_CONTENT_TYPE_MESSAGE);
				throw new WSException(CHHSErrorCodes.INVALID_SERVICE_REQUEST, 
						CHHSErrorCodes.UNSUPPORTED_REQUEST_CONTENT_TYPE_MESSAGE);
			}

			requestXML = httpRequest.getParameter(WSConstants.REQUEST_PARAM);
			// TODO provide a check for sensitive requests
			log.info("httpRequest.getContentType() : " + httpRequest.getContentType());
			// if No Wrapper present
			if (requestXML == null) {
				requestXML = getRequestPayload(httpRequest);
			}
			// log.info("requestXML : " + requestXML);
			httpRequest.setAttribute(WSConstants.REQUEST_PARAM, requestXML);
			log.info("requestUri : " + requestUri);

			if (StringUtils.isEmpty(requestXML)) {
				throw new WSException(CHHSErrorCodes.REQUEST_PAYLOAD_NOT_FOUND, CHHSErrorCodes.REQUEST_PAYLOAD_NOT_FOUND_MESSAGE);
			}

			Class apiClass = null;
			apiClassName = getRequestClass(apiName);
			try {
				apiClass = Class.forName(apiClassName);
			} catch (ClassNotFoundException e) {
				log.error("Error in loading API request class", e);
				throw new WSException(CHHSErrorCodes.INVALID_SERVICE_REQUEST, e.getMessage(),
						CHHSErrorCodes.INVALID_SERVICE_REQUEST_MESSAGE);
			}

			try {
				if (supportedContentType.equals(WSConstants.REQ_CONTENT_TYPE_JSON)
						|| supportedContentType.equals(WSConstants.REQ_CONTENT_TYPE_JSON_UTF)) {
					reqObj = (WSRequest) JsonConverter.convertJsonToJavaObject(requestXML, apiClass);
				} else if (supportedContentType.equals(WSConstants.REQ_CONTENT_TYPE_XML)) {
					reqObj = (WSRequest) XMLBinder.unmarshal(apiClass, requestXML);
				}

				/*
				 * log.info("*******" ); String xml = XMLBinder.marshal(reqObj); log.info(xml);
				 */

				// log.info("**************************************************");
				// log.info(JsonConverter.convertJavaObjectToJsonString(reqObj));
				// log.info("**************************************************");

			} catch (Exception e) {
				log.error("Error while parsing request string " + requestXML, e);
				throw new WSException(CHHSErrorCodes.INVALID_REQUEST_PAYLOAD_STRUCTURE, e.getMessage(),
						CHHSErrorCodes.INVALID_REQUEST_PAYLOAD_STRUCTURE_MESSAGE);
			}
		}

		if (apiName == null) {
			throw new WSException(CHHSErrorCodes.INVALID_SERVICE_REQUEST, CHHSErrorCodes.INVALID_SERVICE_REQUEST_MESSAGE);
		}

		if (apiClassName == null) {
			throw new WSException(CHHSErrorCodes.INVALID_SERVICE_REQUEST, CHHSErrorCodes.INVALID_SERVICE_REQUEST_MESSAGE);
		}

		WSRequest wsRequest = (WSRequest) reqObj;
		
		// Hibernate validation code start
		String errorMessage = "";
		Map<String, String> errorMessageMap = new HashMap<String, String>();
		if (isRequestBodyRequired && requestValidation) {
			Set<ConstraintViolation<WSRequest>> constraintViolations = validator.validate(wsRequest);
			for (ConstraintViolation<WSRequest> constraintViolation : constraintViolations) {
				errorMessage = errorMessage + constraintViolation.getPropertyPath() + " :: " + constraintViolation.getMessage() + " ";
				errorMessageMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
			}
			// Hibernate validation code end
			ValidationResult vr = wsRequest.validate();
			if (!vr.isValid()) {
				errorMessage += vr.getMessage();
				errorMessageMap.put(CHHSErrorCodes.INVALID_REQUEST_MESSAGE, vr.getMessage());

			}
			/**
			 * this is temporary arrangement so that exception is only thrown when oldErrorMessage is not empty.
			 */
			if (errorMessageMap != null && !errorMessageMap.isEmpty()) {
				StringWriter jsonObj = new StringWriter();
				JSONValue.writeJSONString(errorMessageMap, jsonObj);
				throw new WSException(CHHSErrorCodes.INVALID_REQUEST_PARAMETERS, jsonObj.toString());
			}
		}
		request.setAttribute(WSConstants.WS_REQUEST_OBJECT, reqObj);

		authenticateAndAuthorizeAPICall(requestXML, reqObj);
	}

	/**
	 * Authenticate and authorize the API request.
	 * 
	 * @param httpRequest
	 *            {@link HttpServletRequest}
	 * @param requestXML
	 *            {@link String}
	 * @param reqObj
	 *            {@link Object}
	 * @throws WSException
	 */
	private void authenticateAndAuthorizeAPICall(String requestXML, Object reqObj) throws WSException {
		try {
			/*
			 * if (reqObj instanceof AbstractDoesNotRequireAnyContext) {
			 * 
			 * // let it pass through. Credentials will be validated inside the // controller stack. } else
			 */
			if (reqObj instanceof AbstractRequiresUserContext && !isUserSessionValid((AbstractRequiresUserContext) reqObj)) {
				log.error("Error while parsing session Id : " + requestXML);
				throw new WSException(CHHSErrorCodes.UNAUTHORIZED, CHHSErrorCodes.UNAUTHORIZED_MESSAGE);
			}
		} catch (RuntimeException e) {
			log.error("Error in authenticating caller", e);
			throw new WSException(CHHSErrorCodes.SERVER_ERROR, CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
		}
	}

	private boolean isUserSessionValid(AbstractRequiresUserContext reqObj) {
		boolean validSession = false;
		try {
			UserContextTO contextTO = reqObj.getUserContext();
			if (contextTO != null && contextTO.getSessionId() != null && !"".equals(contextTO.getSessionId())) {
				validSession = memberServiceBD.isUserSessionValid(contextTO.getSessionId(), contextTO.getUserId());
			}
		} catch (Exception e) {
			log.error("Error in validating session", e);
		}
		return validSession;
	}

	private String getRequestPayload(HttpServletRequest request) throws WSException {
		StringBuffer contentBuff = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				contentBuff.append(line);
			}
		} catch (Exception e) {

			log.error("Error while copying request string ", e);
			throw new WSException(CHHSErrorCodes.INVALID_REQUEST_PAYLOAD_STRUCTURE,
					CHHSErrorCodes.INVALID_REQUEST_PAYLOAD_STRUCTURE_MESSAGE, e.getMessage());
		}

		return contentBuff.toString();
	}

	private String getAPIName(String requestUri) {
		log.info(requestUri);
		String appName = null;
		int appIndex = requestUri.indexOf("/rest/");
		if (appIndex != -1) {
			appName = requestUri.substring(appIndex);
		}
		return appName;
	}

	/**
	 * Determines the actual error codes returned from the controllers, if any. Otherwise the default SERVER_ERROR is returned.
	 * 
	 * @param e
	 * @return
	 */
	private Object[] getErrorStatusAndMessage(Throwable aThrowable) {
		Object[] errorDetail = new Object[] {CHHSErrorCodes.SERVER_ERROR, CHHSErrorCodes.SERVER_ERROR_MESSAGE, null};
		if (aThrowable instanceof CHHSException) {
			CHHSException exception = (CHHSException) aThrowable;
			errorDetail[0] = exception.getErrorCode();
			errorDetail[1] = (exception.getExceptionMessage() != null ? exception.getExceptionMessage() : exception.getMessage());
			errorDetail[2] = (exception.getErrorDetails() != null ? exception.getErrorDetails() : null);

			return errorDetail;
		}

		// exception has been thrown from one of the utility methods of the
		// filter.
		if (aThrowable instanceof WSException) {
			WSException exception = (WSException) aThrowable;
			errorDetail[0] = exception.getErrorCode();
			errorDetail[1] = (exception.getExceptionMessage() != null ? exception.getExceptionMessage() : exception.getMessage());
			errorDetail[2] = (exception.getErrorDetails() != null ? exception.getErrorDetails() : null);

			return errorDetail;
		}

		// exception has come from the controller layer
		if (aThrowable instanceof ServletException && aThrowable.getCause() instanceof WSException) {
			WSException exception = (WSException) aThrowable.getCause();
			errorDetail[0] = exception.getErrorCode();
			errorDetail[1] = (exception.getExceptionMessage() != null ? exception.getExceptionMessage() : exception.getMessage());
			errorDetail[2] = (exception.getErrorDetails() != null ? exception.getErrorDetails() : null);
		}

		return errorDetail;
	}

	/**
	 * This method is used to fill the values in ApplicationLog Entity Object
	 * 
	 * @param request
	 * @param applicationLog
	 * @throws WSException
	 */
	private void createApplicationLogObject(HttpServletRequest request, ApplicationLog applicationLog) throws WSException {

		applicationLog.setApiName(request.getRequestURI());
		applicationLog.setCreatedOn(new Date());
		applicationLog.setIpAddress(request.getRemoteAddr());
	}

	private void populateContextFromHeader(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		requestMethod = req.getMethod();

	}

	/**
	 * Returns the fully qualified class name of the class that can handle the request sent on the given URL.
	 * 
	 * @param apiName
	 * @return
	 */
	private String getRequestClass(String apiName) {
		return requestClassMap.get(apiName);
	}

	/**
	 * Function to generate a unique request ID for each API request made. This is useful for tracing the path of a request across the api
	 * audit logs.
	 * 
	 * @return the unique request Id.
	 */
	public String generateRequestId() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	// Spring hooks

	public Map<String, String> getRequestClassMap() {
		return requestClassMap;
	}

	public void setRequestClassMap(Map<String, String> requestClassMap) {
		this.requestClassMap = requestClassMap;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public ApplicationLoggerService getApplicationLoggerService() {
		return applicationLoggerService;
	}

	public void setApplicationLoggerService(ApplicationLoggerService applicationLoggerService) {
		this.applicationLoggerService = applicationLoggerService;
	}

	private String stripPathVariables(String apiName) {
		int i = apiName.lastIndexOf("/");
		String apiNameStripped = apiName.substring(0, i);
		return apiNameStripped;
	}

	public MemberServiceBD getMemberServiceBD() {
		return memberServiceBD;
	}

	public void setMemberServiceBD(MemberServiceBD memberServiceBD) {
		this.memberServiceBD = memberServiceBD;
	}

}
