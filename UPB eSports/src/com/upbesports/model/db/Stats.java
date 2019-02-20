package com.upbesports.model.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Stats")
public class Stats implements Serializable 
{
	private static final long serialVersionUID = -2938135370795845110L;

	private Long
		gameId = null,
		id = null;
	
	private String stat = null;

	@Id
	@Column(name = "id")
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	@Column(name = "game_id")
	public Long getGameId() {return gameId;}
	public void setGameId(Long gameId) {this.gameId = gameId;}

	@Column(name = "stat")
	public String getStat() {return stat;}
	public void setStat(String stat) {this.stat = stat;}
	
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
		Stats other = (Stats) obj;
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
		return "Stats [id=" + id + 
			", gameId=" + gameId + 
			", stat=" + stat + 
		"]";
	}
}
