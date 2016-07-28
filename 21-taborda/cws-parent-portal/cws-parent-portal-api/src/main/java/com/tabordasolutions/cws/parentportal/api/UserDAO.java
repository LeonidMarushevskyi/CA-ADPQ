package com.tabordasolutions.cws.parentportal.api;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {
	public UserDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public User findByUsername(String email) {
		Query query = currentSession().createQuery(
				"from User U where U.email = :email");
		query.setString("email", email);
		return uniqueResult(query);
	}

	@SuppressWarnings("unchecked")
	public List<User> findCaseworkers(String emailMatcher) {
		Query query = currentSession().createQuery(
				"from User U where U.email like :email");
		query.setString("email", emailMatcher);
		return (List<User>)query.list();
	}

	public User find(long id) {
		return get(id);
	}

	public User update(User user) {
		return persist(user);
	}

	public long create(User user) {
		return persist(user).getId();
	}

}
