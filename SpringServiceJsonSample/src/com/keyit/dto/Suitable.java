package com.keyit.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_suitable")
public class Suitable implements Serializable {
	
	private static final long serialVersionUID = -6370249259271203046L;

	@Id
	@Column(name = "SuitableID")
	@GeneratedValue
	private Integer suitableId;

	@Column(name = "SuitableName")
	private String suitableName;

	public Integer getSuitableId() {
		return suitableId;
	}

	public void setSuitableId(Integer suitableID) {
		this.suitableId = suitableID;
	}

	public String getSuitableName() {
		return suitableName;
	}

	public void setSuitableName(String suitableName) {
		this.suitableName = suitableName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((suitableId == null) ? 0 : suitableId.hashCode());
		result = prime * result
				+ ((suitableName == null) ? 0 : suitableName.hashCode());
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
		Suitable other = (Suitable) obj;
		if (suitableId == null) {
			if (other.suitableId != null)
				return false;
		} else if (!suitableId.equals(other.suitableId))
			return false;
		if (suitableName == null) {
			if (other.suitableName != null)
				return false;
		} else if (!suitableName.equals(other.suitableName))
			return false;
		return true;
	}
	
	

}
