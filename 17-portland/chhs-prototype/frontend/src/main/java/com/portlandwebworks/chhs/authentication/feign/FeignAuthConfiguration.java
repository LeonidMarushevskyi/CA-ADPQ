package com.portlandwebworks.chhs.authentication.feign;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is excluded from component scan so it does not automatically get
 * applied to all feign clients. See http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html#spring-cloud-ribbon
 * for details.
 * 
 * @author nick
 */
@Configuration
public class FeignAuthConfiguration {

	@Bean
	public RequestInterceptor headerInterceptor() {
		return new FeignAuthHeaderInterceptor();
	}
	
	@Bean
	public ErrorDecoder decoder(){
		return new GenericErrorDecoder();
	}
	
}
