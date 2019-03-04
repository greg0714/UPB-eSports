package com.upbesports.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upbesports.model.db.TeamSchedules;

@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TeamSchedulesDAO extends DAOTemplate<TeamSchedules> 
{
	public TeamSchedulesDAO() {setClazz(TeamSchedules.class);}
}
