package com.tabordasolutions.cws.parentportal.filters;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class ModifyResponseFilterTest {
	private static final String HEADER_KEY_REQUEST_ID = "Parent_Portal_API_Request_Id";

	private ModifyResponseFilter modifyResponseFilter;
	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	private FilterChain filterChain;
	
    @Before
    public void setup() {
    	httpServletRequest = Mockito.mock(HttpServletRequest.class);
        httpServletResponse = Mockito.mock(HttpServletResponse.class);
        filterChain = Mockito.mock(FilterChain.class);
        
        Mockito.when(httpServletRequest.getRequestURI()).thenReturn("/someendpoint");

    	modifyResponseFilter = new ModifyResponseFilter();
     }
    
    @Test
    public void testHeaderModified() throws Exception {
    	modifyResponseFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);    	
    	Mockito.verify(httpServletResponse).setHeader(Mockito.contains(HEADER_KEY_REQUEST_ID), Mockito.anyString());
    }
}