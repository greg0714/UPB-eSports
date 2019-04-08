package com.upbesports.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upbesports.dao.GamesDAO;
import com.upbesports.dao.PlayersDAO;
import com.upbesports.dao.TeamsDAO;
import com.upbesports.model.db.Games;
import com.upbesports.model.db.Players;
import com.upbesports.model.form.GamesForm;
import com.upbesports.model.report.TeamModel;

@RestController
public class TeamTracker 
{
	@Autowired TeamsDAO teamsDAO;
	@Autowired GamesDAO gamesDAO;
	@Autowired PlayersDAO playersDAO;
	
	@RequestMapping(value = "/teamTracker/getGames", method = RequestMethod.GET)
	public List<Games> getGames(HttpServletRequest request, HttpServletResponse response)
	{
    	return gamesDAO.findAll();
	}

	@RequestMapping(value = "/teamTracker/getTeams", method = RequestMethod.GET)
	public List<TeamModel> getTeams(HttpServletRequest request, HttpServletResponse response)
	{
    	return teamsDAO.findWithGameAndManager();
	}
    
	@RequestMapping(value = "/teamTracker/getPlayers", method = RequestMethod.GET)
	public List<Players> getPlayers(HttpServletRequest request, HttpServletResponse response)
	{
    	return playersDAO.findAll();
	}
	
	@RequestMapping(value = "/teamTracker/addGame", method = RequestMethod.POST)
	public void addGame(HttpServletRequest request, HttpServletResponse response, GamesForm form) throws IOException
	{
		if(!gamesDAO.exists(form.getName()))
		{
			Games game = new Games();
			game.setCost(form.getCost());
			game.setId(form.getId());
			game.setMicrotransactions(form.getMicrotransactions());
			game.setName(form.getName());
			game.setPlatforms(form.getPlatforms());
			game.setPlayerBase(form.getPlayerBase());
			game.setSubscription(form.getSubscription());
			game.setUpdated(Calendar.getInstance());
			game.setUpdatedBy(request.getRemoteUser());
			
			gamesDAO.create(game);
		}
		else response.sendError(HttpServletResponse.SC_CONFLICT, "A game with that name already exists.");
	}
	
}
