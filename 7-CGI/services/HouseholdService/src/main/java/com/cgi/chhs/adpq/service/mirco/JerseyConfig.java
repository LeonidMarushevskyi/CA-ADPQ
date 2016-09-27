package com.cgi.chhs.adpq.service.mirco;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;


/**
 * Created by michael on 6/7/16.
 */
@Configuration
@CrossOrigin
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(HouseholdService.class);
        register(SimpleCORSFilter.class);
    }
}
