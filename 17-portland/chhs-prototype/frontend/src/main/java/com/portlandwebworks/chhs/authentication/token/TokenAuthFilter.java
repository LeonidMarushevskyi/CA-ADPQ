package com.portlandwebworks.chhs.authentication.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

/**
 *
 * @author nick
 */
@Component
public class TokenAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

	private static final String TOKEN_NAME = "Token";
	private static final Logger log = LoggerFactory.getLogger(TokenAuthFilter.class);

	@Autowired
	public TokenAuthFilter(TokenAuthManager authManager) {
		setAuthenticationManager(authManager);
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest hsr) {
		log.debug("Checking for pre-auth cookie value.");
		if (hsr.getHeader(TOKEN_NAME) != null) {
			log.trace("Header for pre-auth found.");
			return hsr.getHeader(TOKEN_NAME);
		}else if (hsr.getCookies() != null) {
			log.trace("No header for pre-auth found, falling back to cookie value.");
			for (Cookie ck : hsr.getCookies()) {
				if (ck.getName().equalsIgnoreCase(TOKEN_NAME)) {
					return ck.getValue();
				}
			}
		}
		log.trace("No preauth token foundfound.");
		return null;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest hsr) {
		return null;
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
		super.unsuccessfulAuthentication(request, response, failed); 
		log.warn("PreAuth filter fail, clearing token.");
		Cookie cookie = new Cookie(TOKEN_NAME, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);;
		
	}
	

}
