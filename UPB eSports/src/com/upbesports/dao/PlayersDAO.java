package com.upbesports.dao;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upbesports.model.db.Players;

@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PlayersDAO extends DAOTemplate<Players> 
{
	public PlayersDAO() {setClazz(Players.class);}
	
	public Players findByEmail(String email)
	{
		Players player = null;
		try 
		{
			return (Players) this.getEntityManager()
				.createNativeQuery("select * from Players where email = :email", Players.class)
				.setParameter("email", email)
				.getSingleResult();
		} 
		catch(NoResultException e) 
		{
			return player;
		}
		catch(NonUniqueResultException e)
		{
			return player;
		}
	}
}
