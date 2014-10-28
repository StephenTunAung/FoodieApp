package com.keyit.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cuisinetype")
public class CuisineType implements Serializable {

	private static final long serialVersionUID = -5143152942573763157L;

	@Id
	@Column(name = "CuisineTypeID")
	@GeneratedValue
	private Integer cuisineTypeId;

	@Column(name = "CuisineName")
	private String cuisineName;

	public Integer getCuisineTypeId() {
		return cuisineTypeId;
	}

	public void setCuisineTypeId(Integer cuisineTypeId) {
		this.cuisineTypeId = cuisineTypeId;
	}

	public String getCuisineName() {
		return cuisineName;
	}

	public void setCuisineName(String cuisineName) {
		this.cuisineName = cuisineName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cuisineName == null) ? 0 : cuisineName.hashCode());
		result = prime * result
				+ ((cuisineTypeId == null) ? 0 : cuisineTypeId.hashCode());
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
		CuisineType other = (CuisineType) obj;
		if (cuisineName == null) {
			if (other.cuisineName != null)
				return false;
		} else if (!cuisineName.equals(other.cuisineName))
			return false;
		if (cuisineTypeId == null) {
			if (other.cuisineTypeId != null)
				return false;
		} else if (!cuisineTypeId.equals(other.cuisineTypeId))
			return false;
		return true;
	}

}
