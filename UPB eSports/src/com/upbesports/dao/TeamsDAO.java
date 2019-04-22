package com.upbesports.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upbesports.model.db.Teams;
import com.upbesports.model.report.TeamModel;

@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TeamsDAO extends DAOTemplate<Teams> 
{
	@Autowired PlayersDAO playersDAO;
	
	public TeamsDAO() {setClazz(Teams.class);}

	@SuppressWarnings("unchecked")
	public List<TeamModel> findWithGameAndManager() 
	{
		List<TeamModel> teams = this.getEntityManager().createNativeQuery("select t.*, g.id game, p.id manager, " +
			"case when p.display_name is null then 'No manager' else p.display_name end manager_name " +
			"from Teams t left join Games g on t.game_id = g.id " + 
			"left join Players p on t.manager_id = p.id", TeamModel.class)
			.getResultList();
		teams.forEach(team -> team.setPlayers(playersDAO.findFilteredByTeam(team.getId())));
		return teams;
	}
	
	public boolean exists(String name)
	{
		return this.getEntityManager()
			.createNativeQuery("select * from Teams where lower(name) = lower(:name)", Teams.class)
			.setParameter("name", name)
			.getResultList()
			.size() > 0;
	}

	@Transactional(readOnly = false)
	public void deleteByGame(Long gameId) 
	{
		this.getEntityManager()
			.createNativeQuery("delete from Teams where game_id = :gameId")
			.setParameter("gameId", gameId)
			.executeUpdate();
		
		this.getEntityManager()
			.createNativeQuery("delete from TeamPlayers where team_id in (select id from Teams where game_id = :gameId)")
			.setParameter("gameId", gameId)
			.executeUpdate();
	}
}
