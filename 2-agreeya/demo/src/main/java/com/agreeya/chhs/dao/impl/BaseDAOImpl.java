package com.agreeya.chhs.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;

import com.agreeya.chhs.dao.BaseDAO;
import com.agreeya.chhs.util.TypeCast;

/**
 * Implementation class for BaseDAO 
 * @author AgreeYa Solutions
 *
 */
public class BaseDAOImpl implements BaseDAO {

	//private static Logger log = Logger.getLogger(BaseDAOImpl.class);

	protected SessionFactory sessionFactory;

	/**
	 * Persists the object as a new row in the DB.
	 * 
	 * @param obj
	 *            object to save.
	 * @return the generated identifier.
	 */
	public Object save(Object obj) {
		return sessionFactory.getCurrentSession().save(obj);
	}

	/**
	 * Persists the object as a new row or updates an existing record in the DB.
	 * 
	 * @param obj
	 *            object to save.
	 * @return the generated identifier.
	 */
	public void saveOrUpdate(Object obj) {
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
	}

	/**
	 * Update the given persistent instance
	 * 
	 * @param obj
	 *            - the persistent instance to update
	 */
	public void update(Object obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	/*public <T> List<T> get(Class<T> entityClazz, int firstResult, int maxResults) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClazz);
		return getHibernateTemplate().findByCriteria(criteria, firstResult,
				maxResults);
	}*/

	/**
	 * Fetches an instance from the DB by its id.
	 * 
	 * @param entityClazz
	 *            Class<T>
	 * @param id
	 *            Serializable
	 * @return <T> T - a generic type
	 */
	public <T> T getById(Class<T> entityClazz, Serializable id) {
		return TypeCast.<T>cast(sessionFactory.getCurrentSession().get(entityClazz, id));
	}

	/**
	 * Persists the list of object as a new row for each object in list in the
	 * DB.
	 * 
	 * @param entities
	 *            Collection<T> - collection of object(s) to save.
	 */
	public <T> void saveOrUpdateAll(Collection<T> entities) {
		for (Iterator<T> it = entities.iterator(); it.hasNext();) {
			sessionFactory.getCurrentSession().saveOrUpdate(it.next());
		}
	}

	/**
	 * Delete the list of object in list in the DB.
	 * 
	 * @param List
	 *            of obj object to delete.
	 * @return the generated identifier.
	 */
	public void deleteAll(List entities) {
		sessionFactory.getCurrentSession().delete(entities);
	}


	/**
	 * Load an instance from the DB by its id.
	 * 
	 * @param entityClazz
	 *            Class<T>
	 * @param id
	 *            Serializable
	 * @return <T> T - a generic type
	 */
	public <T> T loadById(Class<T> entityClazz, Serializable id) {
		return TypeCast.<T>cast(sessionFactory.getCurrentSession().load(entityClazz, id));
	}
	

	@Override
	public void delete(Object entity) {
		sessionFactory.getCurrentSession().delete(entity);

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	// Spring hooks.
	
}
