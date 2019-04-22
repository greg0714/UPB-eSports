package com.upbesports.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upbesports.model.db.Players;

@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PlayersDAO extends DAOTemplate<Players> 
{
	public PlayersDAO() {setClazz(Players.class);}

	@SuppressWarnings("unchecked")
	public List<Players> findFilteredByTeam(Long teamId) 
	{
		return this.getEntityManager().createNativeQuery("select p.* from Players p " +
			"left join TeamPlayers tp on p.id = tp.player_id " + 
			"left join Teams t on t.id = tp.team_id where t.id = :teamId", Players.class)
			.setParameter("teamId", teamId)
			.getResultList();
	}

	@Transactional(readOnly = false)
	public int deleteFromAllTeams(Long playerId) 
	{
		return this.getEntityManager()
			.createNativeQuery("delete from TeamPlayers where player_id = :playerId")
			.setParameter("playerId", playerId)
			.executeUpdate();
	}

	@Transactional(readOnly = false)
	public int addToTeam(Long playerId, Long teamId) 
	{
		return this.getEntityManager()
			.createNativeQuery("insert into TeamPlayers (player_id, team_id) values (:playerId, :teamId)")
			.setParameter("playerId", playerId)
			.setParameter("teamId", teamId)
			.executeUpdate();
	}
	
	@Transactional(readOnly = false)
	public int deleteFromTeam(Long playerId, Long teamId) 
	{
		return this.getEntityManager()
			.createNativeQuery("delete from TeamPlayers where player_id = :playerId and team_id = :teamId")
			.setParameter("playerId", playerId)
			.setParameter("teamId", teamId)
			.executeUpdate();
	}
	
	public int playerOnTeam(Long playerId, Long teamId) 
	{
		BigInteger result = (BigInteger) this.getEntityManager()
			.createNativeQuery("select count(*) from TeamPlayers where player_id = :playerId and team_id = :teamId")
			.setParameter("playerId", playerId)
			.setParameter("teamId", teamId)
			.getSingleResult();
		return result.intValue();
	}
}
