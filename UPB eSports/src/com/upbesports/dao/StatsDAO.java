package com.upbesports.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upbesports.model.db.Stats;

@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class StatsDAO extends DAOTemplate<Stats> 
{
	public StatsDAO() {setClazz(Stats.class);}
}
