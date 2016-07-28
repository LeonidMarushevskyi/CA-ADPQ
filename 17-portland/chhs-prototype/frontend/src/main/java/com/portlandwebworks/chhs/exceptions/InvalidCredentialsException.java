package com.portlandwebworks.chhs.exceptions;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 *
 * @author nick
 */
public class InvalidCredentialsException extends HystrixBadRequestException{

	public InvalidCredentialsException(String message){
		super(message);
	}
	
}
