package com.upbesports.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upbesports.model.db.TeamSchedules;
import com.upbesports.model.report.ScheduleModel;

@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TeamSchedulesDAO extends DAOTemplate<TeamSchedules> 
{
	public TeamSchedulesDAO() {setClazz(TeamSchedules.class);}

	@SuppressWarnings("unchecked")
	public List<TeamSchedules> findAllWithTeamName() 
	{
		return this.getEntityManager()
			.createNativeQuery("select ts.*, t.name team_name from TeamSchedules ts left join Teams t on ts.team_id = t.id", ScheduleModel.class)
			.getResultList();
	}
}
