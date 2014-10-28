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

	@Column(name = "FromOpHour")
	private String fromOpHour;

	@Column(name = "ToOpHour")
	private String toOpHour;

	@Column(name = "Monday")
	private boolean monday;

	@Column(name = "Tuesday")
	private boolean tuesday;

	@Column(name = "Wednesday")
	private boolean wednesday;

	@Column(name = "Thursday")
	private boolean thursday;

	@Column(name = "Friday")
	private boolean friday;

	@Column(name = "Satursday")
	private boolean satursday;

	@Column(name = "Sunday")
	private boolean sunday;

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

	public String getFromOpHour() {
		return fromOpHour;
	}

	public void setFromOpHour(String fromOpHour) {
		this.fromOpHour = fromOpHour;
	}

	public String getToOpHour() {
		return toOpHour;
	}

	public void setToOpHour(String toOpHour) {
		this.toOpHour = toOpHour;
	}

	public boolean isMonday() {
		return monday;
	}

	public void setMonday(boolean monday) {
		this.monday = monday;
	}

	public boolean isTuesday() {
		return tuesday;
	}

	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}

	public boolean isWednesday() {
		return wednesday;
	}

	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}

	public boolean isThursday() {
		return thursday;
	}

	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}

	public boolean isFriday() {
		return friday;
	}

	public void setFriday(boolean friday) {
		this.friday = friday;
	}

	public boolean isSatursday() {
		return satursday;
	}

	public void setSatursday(boolean satursday) {
		this.satursday = satursday;
	}

	public boolean isSunday() {
		return sunday;
	}

	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}

}
