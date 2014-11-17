package com.keyit.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_restaurantoperationhours")
public class OperationHour implements Serializable {

	private static final long serialVersionUID = -5785451409366833352L;

	@Id
	@Column(name = "RestaurantOpHourID")
	@GeneratedValue
	private Integer operationHourID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RestaurantID")
	private Restaurant restaurant;

	@Column(name = "RestOperationHours")
	private String restOperationHours;

	public Integer getOperationHourID() {
		return operationHourID;
	}

	public void setOperationHourID(Integer operationHourID) {
		this.operationHourID = operationHourID;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getRestOperationHours() {
		return restOperationHours;
	}

	public void setRestOperationHours(String restOperationHours) {
		this.restOperationHours = restOperationHours;
	}

}
