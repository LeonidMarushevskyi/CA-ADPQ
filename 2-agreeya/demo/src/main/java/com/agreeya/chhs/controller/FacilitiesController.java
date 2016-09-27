package com.agreeya.chhs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.agreeya.chhs.exception.UserException;
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.request.FindAgenciesBytLocationRequest;
import com.agreeya.chhs.response.FacilityFinderResponse;
import com.agreeya.chhs.response.hhsapi.FacilityList;
import com.agreeya.chhs.util.CHHSErrorCodes;
import com.agreeya.chhs.util.Constants;
import com.agreeya.chhs.util.HHSApiCaller;
import com.agreeya.chhs.util.JsonConverter;
import com.agreeya.chhs.util.WSConstants;

/**
 * Controller class for all APIs related to facility search
 * @author AgreeYa Solutions
 *
 */
@Path("/facilities")
public class FacilitiesController {
	
	
	private static Logger log = Logger.getLogger(FacilitiesController.class);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/zipcode/{zip}")
	public Response getFacilitiesByZip(@PathParam("zip")String zip) throws WSException {
		try {
			log.info("enter into FacilitiesController in getFacilitiesByZip() method...................");
			

			FacilityFinderResponse response = new FacilityFinderResponse();
			FacilityList facilities = HHSApiCaller.getFacilitiesByZipCode(zip);
			
			if (null == facilities.getPojoList() || facilities.getPojoList().size() <= 0) {
				response.setMessage(Constants.HHS_API_NODATA);
			} else {
				response.setFacilities(facilities);
			}
			
			String result = JsonConverter.convertJavaObjectToJsonString(response);
			log.info("Exit from FacilitiesController from getFacilitiesByZip() method ...............");
			return Response.ok(result).build();
		} catch (UserException ex) {
			String errorCode = (ex.getErrorCode() != null ? ex.getErrorCode() : CHHSErrorCodes.GET_FACILITIES_FAILED);
			String exceptionMessage = (ex.getExceptionMessage() != null ? ex.getExceptionMessage() 
					: CHHSErrorCodes.GET_FACILITIES_FAILED_MESSAGE);
			log.error(CHHSErrorCodes.GET_FACILITIES_FAILED);
			log.error(CHHSErrorCodes.GET_FACILITIES_FAILED_MESSAGE, ex);
			throw new WSException(errorCode, exceptionMessage, ex.getErrorDetails(), ex.getCause());
		} catch (Exception e) {
			log.error(CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
			throw new WSException(CHHSErrorCodes.SERVER_ERROR, CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
		}
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/agencynearby")
	public Response getAgenciesByLocation(@Context HttpServletRequest request,
			@FormParam(WSConstants.REQUEST_PARAM) String rawRequestData) throws WSException {
		try {
			log.info("enter into FacilitiesController in getAgenciesByLocation() method...................");
			
			FindAgenciesBytLocationRequest wsRequest = (FindAgenciesBytLocationRequest)
					request.getAttribute(WSConstants.WS_REQUEST_OBJECT);
			String latt = wsRequest.getLattitude();
			String lon = wsRequest.getLongitude();
			String radius = wsRequest.getRadius();
			

			FacilityFinderResponse response = new FacilityFinderResponse();
			FacilityList facilities = HHSApiCaller.getFacilitiesByLocation(latt, lon, radius);
			
			if (null == facilities.getPojoList() || facilities.getPojoList().size() <= 0) {
				response.setMessage(Constants.HHS_API_NODATA);
			} else {
				response.setFacilities(facilities);
			}
			
			String result = JsonConverter.convertJavaObjectToJsonString(response);
			log.info("Exit from FacilitiesController from getAgenciesByLocation() method ...............");
			return Response.ok(result).build();
		}	catch (UserException ex) {
			String errorCode = (ex.getErrorCode() != null ? ex.getErrorCode() : CHHSErrorCodes.GET_FACILITIES_FAILED);
			String exceptionMessage = (ex.getExceptionMessage() != null ? ex.getExceptionMessage()
					: CHHSErrorCodes.GET_FACILITIES_FAILED_MESSAGE);
			log.error(CHHSErrorCodes.GET_FACILITIES_FAILED);
			log.error(CHHSErrorCodes.GET_FACILITIES_FAILED_MESSAGE, ex);
			throw new WSException(errorCode, exceptionMessage, ex.getErrorDetails(), ex.getCause());
		} catch (Exception e) {
			log.error(CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
			throw new WSException(CHHSErrorCodes.SERVER_ERROR, CHHSErrorCodes.SERVER_ERROR_MESSAGE, e);
		}
	}
}
