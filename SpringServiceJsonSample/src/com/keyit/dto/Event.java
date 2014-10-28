package com.keyit.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Event implements Serializable {
	
	@Id
	@Column(name = "EventID")
	@GeneratedValue
	private Integer eventID;
	
	@OneToOne
	@JoinColumn(name = "RestaurantID", referencedColumnName = "RestaurantID")
	private Restaurant restaurant;
	
	@Column(name = "EventName")
	private String eventName;
	
	@Column(name = "EventDescription")
	private String eventDescription;
	
	@Column(name = "EventDate")
	private String eventDate;
	
	@Column(name = "EventTime")
	private String eventTime;
	
	@Column(name = "CoverCharges")
	private String coverCharges;
	
	

}
