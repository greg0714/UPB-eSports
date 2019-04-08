package com.upbesports.dao;

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
}
