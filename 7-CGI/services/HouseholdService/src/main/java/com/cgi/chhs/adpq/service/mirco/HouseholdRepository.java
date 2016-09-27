package com.cgi.chhs.adpq.service.mirco;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by michael on 6/4/16.
 */
@EnableJpaRepositories
public interface HouseholdRepository extends CrudRepository<Household, Long> {

}

