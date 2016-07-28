package com.portlandwebworks.chhs.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

/**
 * Instead of returning the normal redirect response that Spring uses this class
 * returns a JSON response instead that can be handled by AJAX login attempts.
 *
 * @author nick
 */
public class JsonAuthFilter extends AbstractAuthenticationProcessingFilter {

	public static final String DEFAULT_PROCESSING_URL = "/login";
	private final Logger log = LoggerFactory.getLogger(JsonAuthFilter.class);
	private final ObjectMapper mapper = new ObjectMapper();

	public JsonAuthFilter(AuthenticationProvider authenticationProvider) {
		super(DEFAULT_PROCESSING_URL);
		setAuthenticationManager(new ProviderManager(Arrays.asList(authenticationProvider)));
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if (((HttpServletRequest) req).getMethod().equals("POST")) {
			super.doFilter(req, res, chain);
		} else {
			HttpServletResponse resp = (HttpServletResponse) res;
			resp.setHeader("Access-Control-Allow-Origin", "*");
			chain.doFilter(req, res);
		}
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest hsr, HttpServletResponse hsr1) throws AuthenticationException, IOException, ServletException {
		try {
			log.debug("Attempting to get email/password from request body.");

			AuthRequest req = mapper.readValue(hsr.getInputStream(), AuthRequest.class);
			String email = req.getEmail();
			String password = req.getPassword();
			if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
				throw new BadCredentialsException("Username and/or password is empty.");
			}
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
			return getAuthenticationManager().authenticate(token);
		} catch (BadCredentialsException ex) {
			throw ex;
		}
	}
}
