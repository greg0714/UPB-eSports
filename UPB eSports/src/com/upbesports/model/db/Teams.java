package com.upbesports.model.db;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Teams")
public class Teams implements Serializable 
{
	private static final long serialVersionUID = 3313947846832893056L;

	private Calendar updated = null;
	
	private Long
		draws = null,
		gameId = null,
		id = null,
		losses = null,
		managerId = null,
		wins = null;
	
	private String 
		name = null,
		updatedBy = null;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	@Column(name = "draws")
	public Long getDraws() {return draws;}
	public void setDraws(Long draws) {this.draws = draws;}

	@Column(name = "game_id")
	public Long getGameId() {return gameId;}
	public void setGameId(Long gameId) {this.gameId = gameId;}

	@Column(name = "losses")
	public Long getLosses() {return losses;}
	public void setLosses(Long losses) {this.losses = losses;}

	@Column(name = "manager_id")
	public Long getManagerId() {return managerId;}
	public void setManagerId(Long managerId) {this.managerId = managerId;}

	@Column(name = "name")
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	public Calendar getUpdated() {return updated;}
	public void setUpdated(Calendar updated) {this.updated = updated;}

	@Column(name = "updated_by")
	public String getUpdatedBy() {return updatedBy;}
	public void setUpdatedBy(String updatedBy) {this.updatedBy = updatedBy;}
	
	@Column(name = "wins")
	public Long getWins() {return wins;}
	public void setWins(Long wins) {this.wins = wins;}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teams other = (Teams) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() 
	{
		return "Teams [id=" + id + 
			", draws=" + draws + 
			", gameId=" + gameId + 
			", losses=" + losses + 
			", managerId=" + managerId + 
			", name=" + name + 
			", updated=" + updated + 
			", updatedBy=" + updatedBy + 
			", wins=" + wins + 
		"]";
	}
}
