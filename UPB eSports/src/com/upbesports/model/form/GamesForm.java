package com.upbesports.model.form;

import java.math.BigDecimal;

public class GamesForm 
{
	private BigDecimal cost = null;
	
	private Boolean
		microtransactions = null,
		subscription = null;
	
	private Long 
		id = null,
		playerBase = null;
	
	private String
		name = null,
		platforms = null;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public BigDecimal getCost() {return cost;}
	public void setCost(BigDecimal cost) {this.cost = cost;}

	public Boolean getMicrotransactions() {return microtransactions;}
	public void setMicrotransactions(Boolean microtransactions) {this.microtransactions = microtransactions;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getPlatforms() {return platforms;}
	public void setPlatforms(String platforms) {this.platforms = platforms;}
	
	public Long getPlayerBase() {return playerBase;}
	public void setPlayerBase(Long playerBase) {this.playerBase = playerBase;}

	public Boolean getSubscription() {return subscription;}
	public void setSubscription(Boolean subscription) {this.subscription = subscription;}
	
	@Override
	public String toString() 
	{
		return "Games [id=" + id + 
			", cost=" + cost + 
			", microtransactions=" + microtransactions + 
			", name=" + name + 
			", platforms=" + platforms + 
			", playerBase=" + playerBase + 
			", subscription=" + subscription + 
		"]";
	}
}
