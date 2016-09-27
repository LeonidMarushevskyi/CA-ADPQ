package com.cgi.chhs.adpq.service.mirco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

@SpringBootApplication
@EntityScan("com.cgi.chhs.adpq.service.mirco")
public class HouseholdApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseholdApplication.class, args);
	}
}
