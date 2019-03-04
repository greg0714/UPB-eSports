package com.upbesports.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upbesports.model.db.Games;

@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class GamesDAO extends DAOTemplate<Games> 
{
	public GamesDAO() {setClazz(Games.class);}
	
	public boolean exists(String name)
	{
		return this.getEntityManager()
			.createNativeQuery("select * from Games where lower(name) = lower(:name)", Games.class)
			.setParameter("name", name)
			.getResultList()
			.size() > 0;
	}
}
