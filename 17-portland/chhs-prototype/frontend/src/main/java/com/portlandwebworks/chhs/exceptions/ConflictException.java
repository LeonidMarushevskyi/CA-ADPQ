package com.portlandwebworks.chhs.exceptions;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 *
 * @author nick
 */
public class ConflictException extends HystrixBadRequestException {
	
	public ConflictException(String message) {
		super(message);
	}

	public ConflictException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
