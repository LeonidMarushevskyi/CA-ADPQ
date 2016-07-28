package com.portlandwebworks.chhs.authentication.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
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
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Intialize a new hystrix context for each request in order to be able to
 * store and retrieve the Spring Security context holder.
 * 
 * @author nick
 */
@Component
@Order(-1000) //Super low order to get placed ahead of the SpringSecurityChainFilter so this is initialized in time and doesn't cause errors
@WebFilter(urlPatterns = "/*")
public class HystrixRequestContextEnablerFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(HystrixRequestContextEnablerFilter.class);
	
	@Override
	public void init(FilterConfig fc) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			SecurityContextHystrixRequestVariable.getInstance().set(SecurityContextHolder.getContext());
			fc.doFilter(sr, sr1);
		} finally {
			context.shutdown();
		}
	}

	@Override
	public void destroy() {
	}

}
