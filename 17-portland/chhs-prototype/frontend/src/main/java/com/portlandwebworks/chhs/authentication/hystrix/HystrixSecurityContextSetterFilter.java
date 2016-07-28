package com.portlandwebworks.chhs.authentication.hystrix;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Sets the SecurityContext request variable for later access through Hystrix. 
 * Needs to run after Spring Security filter chain items.
 * 
 * @author nick
 */
@Component
@WebFilter(urlPatterns = "/*")
public class HystrixSecurityContextSetterFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(HystrixSecurityContextSetterFilter.class);

	@Override
	public void init(FilterConfig fc) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
		SecurityContextHystrixRequestVariable.getInstance().set(SecurityContextHolder.getContext());
		fc.doFilter(sr, sr1);
	}

	@Override
	public void destroy() {
	}

}
