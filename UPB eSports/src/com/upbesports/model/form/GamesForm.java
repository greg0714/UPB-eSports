package com.upbesports.model.form;

import java.math.BigDecimal;

public class GamesForm 
{
	private BigDecimal gameCost = null;
	
	private Boolean
		gameMicrotransactions = null,
		gameSubscription = null;
	
	private Long 
		gameId = null;
	
	private String
		gameName = null,
		gamePlatforms = null;

	public BigDecimal getGameCost() {
		return gameCost;
	}

	public void setGameCost(BigDecimal gameCost) {
		this.gameCost = gameCost;
	}

	public Boolean getGameMicrotransactions() {
		return gameMicrotransactions;
	}

	public void setGameMicrotransactions(Boolean gameMicrotransactions) {
		this.gameMicrotransactions = gameMicrotransactions;
	}

	public Boolean getGameSubscription() {
		return gameSubscription;
	}

	public void setGameSubscription(Boolean gameSubscription) {
		this.gameSubscription = gameSubscription;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGamePlatforms() {
		return gamePlatforms;
	}

	public void setGamePlatforms(String gamePlatforms) {
		this.gamePlatforms = gamePlatforms;
	}

	@Override
	public String toString() {
		return "GamesForm [gameCost=" + gameCost + ", gameMicrotransactions=" + gameMicrotransactions + ", gameSubscription="
				+ gameSubscription + ", gameId=" + gameId + ", gameName=" + gameName + ", gamePlatforms="
				+ gamePlatforms + "]";
	}

	
}
