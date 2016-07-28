package com.portlandwebworks.chhs;

import com.portlandwebworks.chhs.authentication.feign.FeignAuthConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 *
 * @author nick
 */
@SpringBootApplication(exclude = {FeignAuthConfiguration.class})
@EnableWebSecurity
@EnableFeignClients
public class FrontEndApp {

	public static void main(String... args) {
		SpringApplication app = new SpringApplication(FrontEndApp.class);
		app.run(args);
	}
}
