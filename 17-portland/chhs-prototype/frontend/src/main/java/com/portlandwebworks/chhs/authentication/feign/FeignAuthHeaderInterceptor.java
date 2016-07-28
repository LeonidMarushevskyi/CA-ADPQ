package com.portlandwebworks.chhs.authentication.feign;

import com.portlandwebworks.chhs.authentication.AuthenticatedUser;
import com.portlandwebworks.chhs.authentication.hystrix.SecurityContextHystrixRequestVariable;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

/**
 * Simple feign interceptor that automatically adds the Token header to any
 * requests if an Authentication principle is available in the SecurityContext.
 *
 * @author nick
 */
public class FeignAuthHeaderInterceptor implements RequestInterceptor {

	private static Logger log = LoggerFactory.getLogger(FeignAuthHeaderInterceptor.class);

	@Override
	public void apply(RequestTemplate rt) {
		final SecurityContext ctx = SecurityContextHystrixRequestVariable.getInstance().get();
		final Authentication auth = ctx.getAuthentication();
		log.trace("Seeing if we have auth items to add to the feign request. auth != null == {}", auth != null);
		if (auth != null) {
			log.trace("Checking for auth details to add token to header. details != null = {}", auth.getDetails() != null);
			if (auth.getDetails() != null
					&& auth.getDetails() instanceof AuthenticatedUser) {
				log.trace("User authenticated, adding token header: {}", auth.getCredentials());
				rt.header("Token", (String) auth.getCredentials());
			} else {
				log.trace("No authentication details, not adding header.");
			}
		} else {
			log.trace("No security context, probably before login.");
		}
	}

}
