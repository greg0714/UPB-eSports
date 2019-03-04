package com.upbesports.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upbesports.model.db.PlayerStats;

@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PlayerStatsDAO extends DAOTemplate<PlayerStats> 
{
	public PlayerStatsDAO() {setClazz(PlayerStats.class);}
}
