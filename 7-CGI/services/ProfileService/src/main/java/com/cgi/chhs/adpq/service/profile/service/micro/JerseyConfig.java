package com.cgi.chhs.adpq.service.profile.service.micro;

import com.cgi.chhs.adpq.service.profile.entity.Message;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig() {
        register(ProfileService.class);
        register(MessageService.class);
        register(SimpleCORSFilter.class);
    }
}
