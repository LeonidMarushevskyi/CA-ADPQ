package com.agreeya.chhs.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Superclass of all DAOs. Contains common convenience methods and Spring DI
 * hooks.
 * @author AgreeYa Solutions
 *
 * 
 */
public interface BaseDAO {

	/**
	 * Persists the object as a new row in the DB.
	 * 
	 * @param obj
	 *            object to save.
	 * @return the generated identifier.
	 */
	@SuppressWarnings("unused")
	Object save(Object obj);

	/**
	 * Persists the object as a new row or updates an existing record in the DB.
	 * 
	 * @param obj
	 *            object to save.
	 * @return the generated identifier.
	 */
	void saveOrUpdate(Object obj);

	/**
	 * Updates the row.
	 * 
	 * @param obj
	 */
	void update(Object obj);





	/**
	 * Load records in batches from database  
	 * @param entityClazz class of objects to be fetched
	 * @param firstResult first result 
	 * @param maxResults maximum results
	 * @return list of objects
	 */
	//<T> List<T> get(Class<T> entityClazz, int firstResult, int maxResults);
	
	/**
	 * Fetches an instance from the DB by its id.
	 * 
	 * @param entityClazz
	 * @param id
	 * @return
	 */
	<T> T getById(Class<T> entityClazz, Serializable id);

	/*
	 * Common functions used across multiple DAOs and services. In future these
	 * may get moved to a separate DAO if the number of such functions become
	 * too many.
	 */

	/**
	 * Persists the list of object as a new row for each object in list in the
	 * DB.
	 * 
	 * @param entities
	 *            Collection<T> - collection of object(s) to save.
	 */
	<T> void saveOrUpdateAll(Collection<T> entities);

	/**
	 * Delete the list of object in list in the DB.
	 * 
	 * @param List
	 *            of obj object to delete.
	 */

	void deleteAll(List entities);

	/**
	 * Load an instance from the DB by its id.
	 * 
	 * @param entityClazz
	 * @param id
	 * @return
	 */
	<T> T loadById(Class<T> entityClazz, Serializable id);


	
	/**
	 * Deletes the object in the DB.
	 * 
	 * @param entities
	 *           Object- object to delete.
	 */
	void delete(Object entity);
}
