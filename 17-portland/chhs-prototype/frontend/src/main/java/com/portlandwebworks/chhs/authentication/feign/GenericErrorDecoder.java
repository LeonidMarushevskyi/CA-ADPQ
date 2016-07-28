package com.portlandwebworks.chhs.authentication.feign;

import com.portlandwebworks.chhs.exceptions.ConflictException;
import com.portlandwebworks.chhs.exceptions.InvalidCredentialsException;
import com.portlandwebworks.chhs.exceptions.RemoteClientException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.BufferedReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nick
 */
public class GenericErrorDecoder implements ErrorDecoder {

	private Logger log = LoggerFactory.getLogger(GenericErrorDecoder.class);

	@Override
	public Exception decode(String method, Response rspns) {
		final String body = responseBody(rspns);
		log.debug("Error happened. Seeing if it was bad credentials. \n{}", body);
		switch (rspns.status()) {
			case 403:
				log.debug("Access denied exception, returning generic credentials exception.", body);
				throw new InvalidCredentialsException(body);
			case 409:
				log.warn("Conflict in request.");
				throw new ConflictException("Resource conflict for request.");
			default:
				throw new RemoteClientException("Error during remote call.", method, rspns.status());
		}
	}

	private String responseBody(Response rsp) {
		try (BufferedReader reader = new BufferedReader(rsp.body().asReader())) {
			StringBuilder bld = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				bld.append(line);
			}
			return bld.toString();
		} catch (Exception ex) {
			log.error("Error reading response body.", ex);
			return null;
		}
	}

}
