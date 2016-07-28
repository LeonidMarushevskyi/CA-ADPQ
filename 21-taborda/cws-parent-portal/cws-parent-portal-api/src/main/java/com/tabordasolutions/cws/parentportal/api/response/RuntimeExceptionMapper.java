package com.tabordasolutions.cws.parentportal.api.response;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeExceptionMapper.class);

  @Override
  public Response toResponse(RuntimeException runtime) {

	LOGGER.error("Unexpected Error", runtime);
	ErrorResponse error = new ErrorResponse("Unexpected Error");
    // Build default response
    Response defaultResponse = Response.status(HttpStatus.SC_OK)
      .entity(error)
      .build();
    return defaultResponse;

  }
}