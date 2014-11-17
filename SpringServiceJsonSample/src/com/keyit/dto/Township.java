package com.keyit.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_township")
public class Township implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1700171940959832414L;

	@Id
	@Column(name = "TownshipID")
	@GeneratedValue
	private Integer townshipId;

	@Column(name = "TownshipName")
	private String townshipName;

	public Integer getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(Integer townshipId) {
		this.townshipId = townshipId;
	}

	public String getTownshipName() {
		return townshipName;
	}

	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}

}
