package com.cgi.chhs.adpq.service.profile.service.micro;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.cgi.chhs.adpq.service.profile.entity.Profile;

@EnableJpaRepositories
public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
