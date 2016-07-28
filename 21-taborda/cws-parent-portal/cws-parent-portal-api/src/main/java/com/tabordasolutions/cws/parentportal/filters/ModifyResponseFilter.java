package com.tabordasolutions.cws.parentportal.filters;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModifyResponseFilter implements Filter {
	public static final Logger LOGGER = LoggerFactory.getLogger(ModifyResponseFilter.class);
	
	private static final String HEADER_KEY_REQUEST_ID = "Parent_Portal_API_Request_Id";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String originalThreadName = Thread.currentThread().getName();
		
		//set a unique threadname for logging/tracking
		String newThreadName = UUID.randomUUID().toString();
		Thread.currentThread().setName(newThreadName);
		
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		try {
			httpServletResponse.setHeader(HEADER_KEY_REQUEST_ID, newThreadName);
			chain.doFilter(request, httpServletResponse);
		} finally {
			//give the thread back it's original name
			Thread.currentThread().setName(originalThreadName);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}