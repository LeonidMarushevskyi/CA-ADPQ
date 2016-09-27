package com.cgi.chhs.adpq.service.profile.service.micro;

import com.cgi.chhs.adpq.service.profile.entity.Message;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by michael on 6/5/16.
 */
@EnableJpaRepositories
public interface MessageRepository extends CrudRepository <Message, Long> {
}
