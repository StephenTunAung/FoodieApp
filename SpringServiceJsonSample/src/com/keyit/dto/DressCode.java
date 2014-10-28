package com.keyit.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_dresscode")
public class DressCode implements Serializable {

	private static final long serialVersionUID = 5150788647250884719L;

	@Id
	@Column(name = "DressCodeID")
	@GeneratedValue
	private Integer dressCodeId;

	@Column(name = "DressCodeName")
	private String dressCodeName;

	public Integer getDressCodeId() {
		return dressCodeId;
	}

	public void setDressCodeId(Integer dressCodeId) {
		this.dressCodeId = dressCodeId;
	}

	public String getDressCodeName() {
		return dressCodeName;
	}

	public void setDressCodeName(String dressCodeName) {
		this.dressCodeName = dressCodeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dressCodeId == null) ? 0 : dressCodeId.hashCode());
		result = prime * result
				+ ((dressCodeName == null) ? 0 : dressCodeName.hashCode());
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
		DressCode other = (DressCode) obj;
		if (dressCodeId == null) {
			if (other.dressCodeId != null)
				return false;
		} else if (!dressCodeId.equals(other.dressCodeId))
			return false;
		if (dressCodeName == null) {
			if (other.dressCodeName != null)
				return false;
		} else if (!dressCodeName.equals(other.dressCodeName))
			return false;
		return true;
	}

}
