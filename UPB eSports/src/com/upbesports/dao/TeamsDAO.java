package com.upbesports.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upbesports.model.db.Teams;

@Repository
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TeamsDAO extends DAOTemplate<Teams> 
{

}
