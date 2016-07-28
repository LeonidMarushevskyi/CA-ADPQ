package com.portlandwebworks.chhs.account;

import com.portlandwebworks.chhs.authentication.feign.FeignAuthConfiguration;
import com.portlandwebworks.chhs.account.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author nick
 */
@FeignClient(name = "backend", url = "${backend.url}", configuration = {FeignAuthConfiguration.class})
public interface  AccountClient {
	
	@RequestMapping(method = RequestMethod.GET, path = "/api/account", produces = "application/json", consumes = "application/json")
	User getCurrent();
	
	@RequestMapping(method = RequestMethod.GET, path = "/api/account/caseworker", produces = "application/json", consumes = "application/json")
	User getCaseWorker();
	
	@RequestMapping(method = RequestMethod.POST, path = "/api/account", produces = "application/json", consumes = "application/json")
	void register(User account);
	
	@RequestMapping(method = RequestMethod.PUT, path = "/api/account", produces = "application/json", consumes = "application/json")
	void update(User account);
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/api/account/available", produces = "application/json", consumes = "application/json")
	ResponseEntity accountAvailable(@RequestParam("email") String email);
	
	
}
