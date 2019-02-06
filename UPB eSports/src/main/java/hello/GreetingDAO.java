package main.java.hello;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class GreetingDAO extends DAOTemplate<Greeting> 
{
	@SuppressWarnings("unchecked")
	public List<Greeting> retrieveByName(String name)
	{
		return this.getCurrentSession()
			.createNativeQuery("select * from greeting where name = :name")
			.addEntity(Greeting.class)
			.setParameter("name", name)
			.list();
	}
}
