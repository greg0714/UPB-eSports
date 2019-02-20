package com.upbesports.model.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TableCodes")
public class TableCodes implements Serializable 
{
	private static final long serialVersionUID = -430298479502356147L;
	
	private Long id = null;
	
	private String
		columnName = null,
		description = null,
		tableName = null,
		value = null;

	@Id
	@Column(name = "id")
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	@Column(name = "column_name")
	public String getColumnName() {return columnName;}
	public void setColumnName(String columnName) {this.columnName = columnName;}

	@Column(name = "description")
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	@Column(name = "table_name")
	public String getTableName() {return tableName;}
	public void setTableName(String tableName) {this.tableName = tableName;}

	@Column(name = "value")
	public String getValue() {return value;}
	public void setValue(String value) {this.value = value;}
	
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
		TableCodes other = (TableCodes) obj;
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
		return "TableCodes [id=" + id + 
			", columnName=" + columnName + 
			", description=" + description + 
			", tableName=" + tableName + 
			", value=" + value + 
		"]";
	}
}
