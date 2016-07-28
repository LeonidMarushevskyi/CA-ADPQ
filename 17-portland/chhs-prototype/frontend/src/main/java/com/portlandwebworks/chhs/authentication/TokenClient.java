package com.portlandwebworks.chhs.authentication;

import com.portlandwebworks.chhs.authentication.feign.FeignAuthConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author nick
 */
@FeignClient(name = "backend", url = "${backend.url}", configuration = {FeignAuthConfiguration.class})
public interface TokenClient {

	@RequestMapping(method = RequestMethod.POST, path = "/api/token", produces = "application/json", consumes = "application/json")
	AuthenticationResponse generateToken(AuthRequest request);
	
	@RequestMapping(method = RequestMethod.GET, path = "/api/token", produces = "application/json", consumes = "application/json")
	AuthenticationResponse currentAuth(@RequestHeader("Token") String token);

}
