package com.portlandwebworks.chhs.exceptions;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 *
 * @author nick
 */
public class BadRequestException extends HystrixBadRequestException{
	
	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
