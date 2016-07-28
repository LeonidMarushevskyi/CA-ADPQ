package com.portlandwebworks.chhs.authentication;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.portlandwebworks.chhs.exceptions.InvalidCredentialsException;
import java.util.stream.Collectors;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author nick
 */
@Component
public class RemoteAuthenticationProvider implements AuthenticationProvider {

	private static final Logger log = LoggerFactory.getLogger(RemoteAuthenticationProvider.class);

	private final TokenClient tokenClient;

	@Autowired
	public RemoteAuthenticationProvider(TokenClient tokenClient) {
		this.tokenClient = tokenClient;
	}

	@Override
	public Authentication authenticate(Authentication a) throws AuthenticationException {
		log.info("Attempting remote authentication.");
		AuthRequest req = new AuthRequest();
		req.setEmail((String) a.getPrincipal());
		req.setPassword((String) a.getCredentials());

		try {
			AuthenticationResponse authResp = tokenClient.generateToken(req);
			log.info("User authenticated {}", ToStringBuilder.reflectionToString(authResp, ToStringStyle.MULTI_LINE_STYLE));
			final UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(authResp.getUsername(), null, authResp.getRoles().stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList()));
			userToken.setDetails(authResp);
			return userToken;
		} catch (HystrixRuntimeException ex) {
			if (ex.getCause() instanceof InvalidCredentialsException) {
				throw new BadCredentialsException("Bad username/password given.");
			} else {
				log.error("Error getting remote authentication.", ex);
				throw new InternalAuthenticationServiceException("Error getting remote authentication credentials.");
			}
		}
	}

	@Override
	public boolean supports(Class<?> type) {
		return type.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}
