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
 
	public T findOne( long id )
	{
		return entityManager.find( clazz, id );
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll()
	{
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}
 
	public void create(T entity)
	{
		entityManager.persist(entity);
	}
 
	public T update(T entity)
	{
	   return entityManager.merge(entity);
	}
 
	public void delete(T entity)
	{
		entityManager.remove(entity);
	}
	
	public void deleteById(long entityId)
	{
		T entity = findOne(entityId);
		delete(entity);
	}
	
	public EntityManager getEntityManager() 
	{
		return this.entityManager;
	}
}