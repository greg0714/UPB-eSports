package com.upbesports.model.form;

public class TeamPlayersForm 
{
	private Long
		id = null,
		playerId = null,
		teamId = null;

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getPlayerId() {
		return playerId;
	}



	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}



	public Long getTeamId() {
		return teamId;
	}



	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}



	@Override
	public String toString() {
		return "TeamPlayersForm [id=" + id + ", playerId=" + playerId + ", teamId=" + teamId + "]";
	}
}
