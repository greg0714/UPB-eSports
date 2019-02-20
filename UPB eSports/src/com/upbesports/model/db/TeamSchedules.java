package com.upbesports.model.db;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TeamSchedules")
public class TeamSchedules implements Serializable 
{
	private static final long serialVersionUID = 194829871283102193L;
	
	private Calendar
		eventTime = null,
		updated = null;
	
	private Long 
		id = null,
		teamId = null;
	
	private String
		comments = null,
		eventType = null,
		location = null,
		updatedBy = null;

	@Id
	@Column(name = "id")
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	@Column(name = "comments")
	public String getComments() {return comments;}
	public void setComments(String comments) {this.comments = comments;}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "event_time")
	public Calendar getEventTime() {return eventTime;}
	public void setEventTime(Calendar eventTime) {this.eventTime = eventTime;}

	@Column(name = "event_type")
	public String getEventType() {return eventType;}
	public void setEventType(String eventType) {this.eventType = eventType;}

	@Column(name = "location")
	public String getLocation() {return location;}
	public void setLocation(String location) {this.location = location;}

	@Column(name = "team_id")
	public Long getTeamId() {return teamId;}
	public void setTeamId(Long teamId) {this.teamId = teamId;}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	public Calendar getUpdated() {return updated;}
	public void setUpdated(Calendar updated) {this.updated = updated;}

	@Column(name = "updated_by")
	public String getUpdatedBy() {return updatedBy;}
	public void setUpdatedBy(String updatedBy) {this.updatedBy = updatedBy;}
	
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
		TeamSchedules other = (TeamSchedules) obj;
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
		return "TeamSchedules [id=" + id +
			", comments=" + comments + 
			", eventTime=" + eventTime + 
			", eventType=" + eventType + 
			", location=" + location + 
			", teamId=" + teamId + 
			", updated=" + updated + 
			", updatedBy=" + updatedBy + 
		"]";
	}
}
