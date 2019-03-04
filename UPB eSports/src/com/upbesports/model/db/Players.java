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
@Table(name = "Players")
public class Players implements Serializable 
{
	private static final long serialVersionUID = -1362550444317550230L;

	private Boolean eligible = null;
	
	private Calendar
		joined = null,
		updated = null;
	
	private Long id = null;
	
	private String
		displayName = null,
		firstName = null,
		lastName = null,
		middleName = null,
		updatedBy = null;

	@Id
	@Column(name = "id")
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	@Column(name = "display_name")
	public String getDisplayName() {return displayName;}
	public void setDisplayName(String displayName) {this.displayName = displayName;}

	@Column(name = "eligible")
	public Boolean getEligible() {return eligible;}
	public void setEligible(Boolean eligible) {this.eligible = eligible;}

	@Column(name = "first_name")
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "joined")
	public Calendar getJoined() {return joined;}
	public void setJoined(Calendar joined) {this.joined = joined;}

	@Column(name = "last_name")
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	@Column(name = "middle_name")
	public String getMiddleName() {return middleName;}
	public void setMiddleName(String middleName) {this.middleName = middleName;}

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
		Players other = (Players) obj;
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
		return "Players [id=" + id + 
			", displayName=" + displayName + 
			", eligible=" + eligible + 
			", firstName=" + firstName + 
			", joined=" + joined + 
			", lastName=" + lastName + 
			", middleName=" + middleName + 
			", updated=" + updated + 
			", updatedBy=" + updatedBy + 
		"]";
	}
}
