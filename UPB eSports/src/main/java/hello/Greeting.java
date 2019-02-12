package main.java.hello;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Greeting")
public class Greeting implements Serializable
{
	private static final long serialVersionUID = -3555416812134336808L;
	
	private String
		name = null,
		message = null;

	public Greeting() {}
	public Greeting(String name, String message) 
	{
		this.message = message;
		this.name = name;
	}
	
	@Id
	@Column(name = "name")
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	@Column(name = "message")
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Greeting other = (Greeting) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Greeting [name=" + name + ", message=" + message + "]";
	}
}