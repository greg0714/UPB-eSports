package com.upbesports.model.form;

public class PlayersForm 
{
	private Long playerId = null;
	
	private Boolean eligible = null;
	
	private String
		firstName = null,
		middleName = null,
		lastName = null,
		displayName = null;

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Boolean getEligible() {
		return eligible;
	}

	public void setEligible(Boolean eligible) {
		this.eligible = eligible;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return "PlayersForm [playerId=" + playerId + ", displayName=" + displayName + ", eligible=" + eligible
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + "]";
	}
	
	
		
}
