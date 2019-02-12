package main.java.hello;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import main.java.utils.ValidationUtils;

@Transactional
@Repository
public class GreetingDAO extends DAOTemplate<Greeting>
{
	@SuppressWarnings("unchecked")
	public Greeting retrieveByName(String name)
	{
		if(!ValidationUtils.validateString(name)) return new Greeting("", "Hello, World!");
		
		List<Greeting> resultList = this.entityManager
			.createNativeQuery("select * from Greeting where upper(name) = :name", Greeting.class)
			.setParameter("name", name.toUpperCase())
			.getResultList();
		
		if(resultList.size() > 0) return resultList.get(0);
		return new Greeting(name, "Hello, " + name + "!");
	}
}
