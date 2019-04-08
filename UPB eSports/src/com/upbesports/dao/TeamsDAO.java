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
		List<TeamModel> teams = this.getEntityManager().createNativeQuery("select t.*, g.name game, p.display_name manager " +
			"from Teams t left join Games g on t.game_id = g.id " + 
			"left join Players p on t.manager_id = p.id", TeamModel.class)
			.getResultList();
		teams.forEach(team -> team.setPlayers(playersDAO.findFilteredByTeam(team.getId())));
		return teams;
	}
}
