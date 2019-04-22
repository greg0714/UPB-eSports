package com.upbesports.model.db;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Games")
public class Games implements Serializable 
{
	private static final long serialVersionUID = -4796512242907785468L;

	private BigDecimal cost = null;
	
	private Boolean
		microtransactions = null,
		subscription = null;
	
	private Calendar updated = null;
	
	private Long id = null;
	
	private String
		name = null,
		platforms = null,
		updatedBy = null;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	@Column(name = "cost")
	public BigDecimal getCost() {return cost;}
	public void setCost(BigDecimal cost) {this.cost = cost;}

	@Column(name = "microtransactions")
	public Boolean getMicrotransactions() {return microtransactions;}
	public void setMicrotransactions(Boolean microtransactions) {this.microtransactions = microtransactions;}

	@Column(name = "name")
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	@Column(name = "platforms")
	public String getPlatforms() {return platforms;}
	public void setPlatforms(String platforms) {this.platforms = platforms;}
	
	@Column(name = "subscription")
	public Boolean getSubscription() {return subscription;}
	public void setSubscription(Boolean subscription) {this.subscription = subscription;}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
		Games other = (Games) obj;
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
		return "Games [id=" + id + 
			", cost=" + cost + 
			", microtransactions=" + microtransactions + 
			", name=" + name + 
			", platforms=" + platforms + 
			", subscription=" + subscription + 
			", updated=" + updated + 
			", updatedBy=" + updatedBy + 
		"]";
	}
}
