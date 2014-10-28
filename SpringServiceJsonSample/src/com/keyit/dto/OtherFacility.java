package com.keyit.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_otherfacilities")
public class OtherFacility implements Serializable {

	private static final long serialVersionUID = 564740712704155537L;

	@Id
	@Column(name = "OtherFacilitiesID")
	@GeneratedValue
	private Integer otherFacilityId;

	@Column(name = "OtherFacilitiesName")
	private String otherFacilityName;

	public Integer getOtherFacilityId() {
		return otherFacilityId;
	}

	public void setOtherFacilityId(Integer otherFacilityId) {
		this.otherFacilityId = otherFacilityId;
	}

	public String getOtherFacilityName() {
		return otherFacilityName;
	}

	public void setOtherFacilityName(String otherFacilityName) {
		this.otherFacilityName = otherFacilityName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((otherFacilityId == null) ? 0 : otherFacilityId.hashCode());
		result = prime
				* result
				+ ((otherFacilityName == null) ? 0 : otherFacilityName
						.hashCode());
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
		OtherFacility other = (OtherFacility) obj;
		if (otherFacilityId == null) {
			if (other.otherFacilityId != null)
				return false;
		} else if (!otherFacilityId.equals(other.otherFacilityId))
			return false;
		if (otherFacilityName == null) {
			if (other.otherFacilityName != null)
				return false;
		} else if (!otherFacilityName.equals(other.otherFacilityName))
			return false;
		return true;
	}

}
