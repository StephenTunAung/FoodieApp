package com.keyit.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_restauranttype")
public class RestaurantType implements Serializable {

	private static final long serialVersionUID = -3839249948363168706L;

	@Id
	@Column(name = "RestaurantTypeID")
	@GeneratedValue
	private Integer restaurantTypeId;

	@Column(name = "RestaurantTypeName")
	private String restaurantTypeName;

	public Integer getRestaurantTypeId() {
		return restaurantTypeId;
	}

	public void setRestaurantTypeId(Integer restaurantTypeID) {
		this.restaurantTypeId = restaurantTypeID;
	}

	public String getRestaurantTypeName() {
		return restaurantTypeName;
	}

	public void setRestaurantTypeName(String restaurantTypeName) {
		this.restaurantTypeName = restaurantTypeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((restaurantTypeId == null) ? 0 : restaurantTypeId.hashCode());
		result = prime
				* result
				+ ((restaurantTypeName == null) ? 0 : restaurantTypeName
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
		RestaurantType other = (RestaurantType) obj;
		if (restaurantTypeId == null) {
			if (other.restaurantTypeId != null)
				return false;
		} else if (!restaurantTypeId.equals(other.restaurantTypeId))
			return false;
		if (restaurantTypeName == null) {
			if (other.restaurantTypeName != null)
				return false;
		} else if (!restaurantTypeName.equals(other.restaurantTypeName))
			return false;
		return true;
	}

}
