package com.upbesports.model.form;

public class TeamsForm 
{
	private Long 
		teamId = null,
		teamGameId = null,
		teamManagerId = null,
		teamWins = null,
		teamLosses = null,
		teamDraws = null;
	
	private String teamName = null;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getTeamGameId() {
		return teamGameId;
	}

	public void setTeamGameId(Long teamGameId) {
		this.teamGameId = teamGameId;
	}

	public Long getTeamManagerId() {
		return teamManagerId;
	}

	public void setTeamManagerId(Long teamManagerId) {
		this.teamManagerId = teamManagerId;
	}

	public Long getTeamWins() {
		return teamWins;
	}

	public void setTeamWins(Long teamWins) {
		this.teamWins = teamWins;
	}

	public Long getTeamLosses() {
		return teamLosses;
	}

	public void setTeamLosses(Long teamLosses) {
		this.teamLosses = teamLosses;
	}

	public Long getTeamDraws() {
		return teamDraws;
	}

	public void setTeamDraws(Long teamDraws) {
		this.teamDraws = teamDraws;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "TeamsForm [teamId=" + teamId + ", teamGameId=" + teamGameId + ", teamManagerId=" + teamManagerId
				+ ", teamWins=" + teamWins + ", teamLosses=" + teamLosses + ", teamDraws=" + teamDraws + ", teamName="
				+ teamName + "]";
	}
	
	
		
}
