package com.upbesports.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upbesports.dao.GamesDAO;
import com.upbesports.dao.PlayersDAO;
import com.upbesports.dao.TeamsDAO;
import com.upbesports.model.db.Games;
import com.upbesports.model.db.Players;
import com.upbesports.model.db.Teams;
import com.upbesports.model.form.GamesForm;
import com.upbesports.model.form.PlayersForm;
import com.upbesports.model.form.TeamPlayersForm;
import com.upbesports.model.form.TeamsForm;
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
	
	@Transactional
	@RequestMapping(value = "/teamTracker/addOrUpdateGame", method = RequestMethod.POST)
	public void addOrUpdateGame(HttpServletRequest request, HttpServletResponse response, GamesForm form) throws IOException
	{
		if(form.getGameId() == null) 
		{
			if(!gamesDAO.exists(form.getGameName()))
			{
				Games game = new Games();
				game.setName(form.getGameName());
				setGameValuesFromForm(game, form);
				
				gamesDAO.create(game);
			}
			else response.sendError(HttpServletResponse.SC_CONFLICT, "A game with that name already exists.");
		}
		else
		{
			Games game = gamesDAO.findOne(form.getGameId());
			setGameValuesFromForm(game, form);
			
			gamesDAO.update(game);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@RequestMapping(value = "/teamTracker/removeGame", method = RequestMethod.POST)
	public void removeGame(HttpServletRequest request, HttpServletResponse response, GamesForm form) throws IOException
	{
		teamsDAO.deleteByGame(form.getGameId());
		gamesDAO.deleteById(form.getGameId());
	}
	
	@Transactional
	@RequestMapping(value = "/teamTracker/addOrUpdateTeam", method = RequestMethod.POST)
	public void addOrUpdateTeam(HttpServletRequest request, HttpServletResponse response, TeamsForm form) throws IOException
	{
		if(form.getTeamId() == null) 
		{
			if(!teamsDAO.exists(form.getTeamName()))
			{
				Teams team = new Teams();
				team.setGameId(form.getTeamGameId());
				setTeamValuesFromForm(team, form);
				
				teamsDAO.create(team);
			}
			else response.sendError(HttpServletResponse.SC_CONFLICT, "A team with that name already exists.");
		}
		else
		{
			Teams team = teamsDAO.findOne(form.getTeamId());
			setTeamValuesFromForm(team, form);
			
			teamsDAO.update(team);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@RequestMapping(value = "/teamTracker/removeTeam", method = RequestMethod.POST)
	public void removeTeam(HttpServletRequest request, HttpServletResponse response, TeamsForm form) throws IOException
	{
		teamsDAO.deleteById(form.getTeamId());
	}

	@Transactional
	@RequestMapping(value = "/teamTracker/addOrUpdatePlayer", method = RequestMethod.POST)
	public void addOrUpdatePlayer(HttpServletRequest request, HttpServletResponse response, PlayersForm form) throws IOException
	{
		if(form.getPlayerId() == null) 
		{
			Players player = new Players();
			player.setFirstName(form.getFirstName());
			player.setMiddleName(form.getMiddleName());
			player.setLastName(form.getLastName());
			player.setJoined(Calendar.getInstance());
			setPlayerValuesFromForm(player, form);
			
			playersDAO.create(player);
		}
		else
		{
			Players player = playersDAO.findOne(form.getPlayerId());
			setPlayerValuesFromForm(player, form);
			
			playersDAO.update(player);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@RequestMapping(value = "/teamTracker/removePlayer", method = RequestMethod.POST)
	public void removePlayer(HttpServletRequest request, HttpServletResponse response, PlayersForm form) throws IOException
	{
		playersDAO.deleteFromAllTeams(form.getPlayerId());
		playersDAO.deleteById(form.getPlayerId());
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@RequestMapping(value = "/teamTracker/addPlayerToTeam", method = RequestMethod.POST)
	public boolean addPlayerToTeam(HttpServletRequest request, HttpServletResponse response, TeamPlayersForm form) throws IOException
	{
		int exists = playersDAO.playerOnTeam(form.getPlayerId(), form.getTeamId());
		if(exists <= 0) 
		{
			playersDAO.addToTeam(form.getPlayerId(), form.getTeamId());
			return true;
		}
		else return false;
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@RequestMapping(value = "/teamTracker/removePlayerFromTeam", method = RequestMethod.POST)
	public boolean removePlayerFromTeam(HttpServletRequest request, HttpServletResponse response, TeamPlayersForm form) throws IOException
	{
		int deleted = playersDAO.deleteFromTeam(form.getPlayerId(), form.getTeamId());
		if(deleted > 0) return true;
		else return false;
	}
	
	private void setGameValuesFromForm(Games game, GamesForm form)
	{
		game.setCost(form.getGameCost());
		game.setMicrotransactions(form.getGameMicrotransactions());
		game.setPlatforms(form.getGamePlatforms());
		game.setSubscription(form.getGameSubscription());
		game.setUpdated(Calendar.getInstance());
		game.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
	private void setTeamValuesFromForm(Teams team, TeamsForm form)
	{
		team.setDraws(form.getTeamDraws());
		team.setLosses(form.getTeamLosses());
		team.setManagerId(form.getTeamManagerId());
		team.setName(form.getTeamName());
		team.setWins(form.getTeamWins());
		team.setUpdated(Calendar.getInstance());
		team.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
	private void setPlayerValuesFromForm(Players player, PlayersForm form)
	{
		player.setDisplayName(form.getDisplayName());
		player.setEligible(form.getEligible());
		player.setUpdated(Calendar.getInstance());
		player.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
