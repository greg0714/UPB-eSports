package com.upbesports.model.form;

public class ScheduleForm 
{
	private Long
		scheduleId = null,
		teamId = null;
	
	private String
		date = null,
		eventType = null,
		location = null,
		comments = null;

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "ScheduleForm [scheduleId=" + scheduleId + ", teamId=" + teamId + ", date=" + date + ", eventType="
				+ eventType + ", location=" + location + ", comments=" + comments + "]";
	}
	
	
}
