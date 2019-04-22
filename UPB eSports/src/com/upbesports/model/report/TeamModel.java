package com.upbesports.model.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.upbesports.model.db.Players;

@Entity
public class TeamModel implements Serializable
{
	private static final long serialVersionUID = -4195436043458973656L;

	private List<Players> players = new ArrayList<>();
	
	private Calendar updated = null;
	
	private Long
		draws = null,
		id = null,
		game = null,
		manager = null,
		losses = null,
		wins = null;
	
	private String 
		managerName = null,
		name = null,
		updatedBy = null;

	@Id
	@Column(name = "id")
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	@Column(name = "draws")
	public Long getDraws() {return draws;}
	public void setDraws(Long draws) {this.draws = draws;}

	@Column(name = "game")
	public Long getGame() { return game; }
	public void setGame(Long game) { this.game = game; }

	@Column(name = "losses")
	public Long getLosses() {return losses;}
	public void setLosses(Long losses) {this.losses = losses;}

	@Column(name = "manager")
	public Long getManager() { return manager; }
	public void setManager(Long manager) { this.manager = manager; }

	@Column(name = "manager_name")
	public String getManagerName() { return managerName; }
	public void setManagerName(String managerName) { this.managerName = managerName; }

	@Column(name = "name")
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@Transient
	public List<Players> getPlayers() {return players;}
	public void setPlayers(List<Players> players) {this.players = players;}
	
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
		TeamModel other = (TeamModel) obj;
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
		return "TeamModel [id=" + id + ", draws=" + draws + ", game=" + game + ", losses=" + losses + ", manager="
				+ manager + ", managerName=" + managerName + ", name=" + name + ", players=" + players + ", updated=" + updated + ", updatedBy="
				+ updatedBy + ", wins=" + wins + "]";
	}
}
