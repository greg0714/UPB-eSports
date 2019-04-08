package com.upbesports.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//Found on https://www.baeldung.com/spring-dao-jpa
public abstract class DAOTemplate<T extends Serializable> {
	 
	private Class<T> clazz;
 
	@PersistenceContext private EntityManager entityManager;
 
	public final void setClazz(Class<T> clazzToSet)
	{
		this.clazz = clazzToSet;
	}
	
	/**
	 * Pulls an object from the database table based on its id field
	 * @param id The object's id number in the database
	 * @return the object with the given id
	 */
	public T findOne( long id )
	{
		return entityManager.find( clazz, id );
	}
	
	/**
	 * Pulls all objects from the database table
	 * @return A List of all the objects
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll()
	{
		return entityManager.createQuery("from " + clazz.getSimpleName()).getResultList();
	}
 
	/**
	 * Adds a new object to the database table
	 * @param entity The object to add
	 */
	public void create(T entity)
	{
		entityManager.persist(entity);
	}
 
	/**
	 * Updates an object in the database table
	 * @param entity The object to update
	 * @return the object that was updated
	 */
	public T update(T entity)
	{
	   return entityManager.merge(entity);
	}
 
	/**
	 * Deletes an object from the database table
	 * @param entity The object to delete
	 */
	public void delete(T entity)
	{
		entityManager.remove(entity);
	}
	
	/**
	 * Deletes an object from the database table based on its id field
	 * @param id The object's id number in the database
	 */
	public void deleteById(long entityId)
	{
		T entity = findOne(entityId);
		delete(entity);
	}
	
	/**
	 * Pulls objects from the database table based on filters and sorters
	 * @param filters A map of filters in the format: $column: "operator $filterText" (Ex. wins: ">= 42"). Nullable.
	 * @param sorters A list of columns to sort by in order. Nullable.
	 * @return A JSON list of teams that meet the given criteria
	 */
	
//	Commented out until a better handling of filters and sorters can be created
//	@SuppressWarnings("unchecked")
//	public List<T> findAllFiltered(String filters, String sorters) 
//	{
//		boolean hasFilters = ValidationUtils.validateString(filters), hasSorters = ValidationUtils.validateString(sorters);
//		StringBuilder sb = new StringBuilder("select * from " + clazz.getSimpleName() + " ");
//		
//		if(hasFilters)
//		{
//			sb.append("where :filters ");
//		}
//		
//		if(hasSorters) 
//		{
//			sb.append("order by :sorters ");
//		}
//		
//		Query query = this.getEntityManager().createNativeQuery(sb.toString(), clazz);
//		if(hasFilters) query.setParameter("filters", filters);
//		if(hasSorters) query.setParameter("sorters", sorters);
//		return query.getResultList();
//	}
	
	public EntityManager getEntityManager() 
	{
		return this.entityManager;
	}
}