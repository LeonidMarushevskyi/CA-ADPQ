package com.portlandwebworks.chhs.messages;

import com.portlandwebworks.chhs.authentication.feign.FeignAuthConfiguration;
import com.portlandwebworks.chhs.messages.model.Message;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author nick
 */
@FeignClient(name = "backend", url = "${backend.url}", configuration = {FeignAuthConfiguration.class})
public interface MessageClient {
	
	@RequestMapping(method = RequestMethod.GET, path = "/api/messages", produces = "application/json", consumes = "application/json")
	List<Message> getMessages();
	
	@RequestMapping(method = RequestMethod.POST, path = "/api/messages", produces = "application/json", consumes = "application/json")
	void postMessage(Message message);
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/api/messages/{messageId}", produces = "application/json", consumes = "application/json")
	void delete(@PathVariable("messageId") Integer messageId);
	
}
