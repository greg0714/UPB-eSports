package com.upbesports.model.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PlayerStats")
public class PlayerStats implements Serializable 
{
	private static final long serialVersionUID = -6151784561935045660L;
	
	private BigDecimal valueNum = null;
	
	private Calendar updated = null;
	
	private Long
		id = null,
		playerId = null,
		statId = null;
	
	private String 
		updatedBy = null,
		value = null;

	@Id
	@Column(name = "id")
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	@Column(name = "player_id")
	public Long getPlayerId() {return playerId;}
	public void setPlayerId(Long playerId) {this.playerId = playerId;}

	@Column(name = "stat_id")
	public Long getStatId() {return statId;}
	public void setStatId(Long statId) {this.statId = statId;}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	public Calendar getUpdated() {return updated;}
	public void setUpdated(Calendar updated) {this.updated = updated;}

	@Column(name = "updated_by")
	public String getUpdatedBy() {return updatedBy;}
	public void setUpdatedBy(String updatedBy) {this.updatedBy = updatedBy;}
	
	@Column(name = "value")
	public String getValue() {return value;}
	public void setValue(String value) {this.value = value;}
	
	@Column(name = "value_num")
	public BigDecimal getValueNum() {return valueNum;}
	public void setValueNum(BigDecimal valueNum) {this.valueNum = valueNum;}
	
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
		PlayerStats other = (PlayerStats) obj;
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
		return "PlayerStats [id=" + id + 
			", playerId=" + playerId + 
			", statId=" + statId + 
			", updated=" + updated + 
			", updatedBy=" + updatedBy + 
			", value=" + value + 
			", valueNum=" + valueNum + 
		"]";
	}
}
