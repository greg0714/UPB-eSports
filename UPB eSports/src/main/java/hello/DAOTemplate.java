package main.java.hello;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class DAOTemplate< T extends Serializable >{
	   private Class< T > clazz;
	 
	   @Autowired private SessionFactory sessionFactory;
	 
	   public void setClazz( final Class< T > clazzToSet ){
	      clazz = clazzToSet;
	   }
	 
	   public T findOne( final long id ){
	      return getCurrentSession().get( clazz, id );
	   }
	   @SuppressWarnings("unchecked")
	public List< T > findAll(){
	      return getCurrentSession()
	       .createQuery( "from " + clazz.getName() ).list();
	   }
	 
	   public void save( final T entity ){
	      getCurrentSession().persist( entity );
	   }
	 
	   @SuppressWarnings("unchecked")
	public T update( final T entity ){
	      return (T) getCurrentSession().merge( entity );
	   }
	 
	   public void delete( final T entity ){
	      getCurrentSession().delete( entity );
	   }
	   public void deleteById( final long id ){
	      final T entity = findOne( id);
	      delete( entity );
	   }
	 
	   protected final Session getCurrentSession(){
	      return sessionFactory.getCurrentSession();
	   }
	}