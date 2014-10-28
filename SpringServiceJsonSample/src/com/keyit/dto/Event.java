package com.keyit.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_event")
public class Event implements Serializable {

	private static final long serialVersionUID = -3737837345282400260L;

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

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_dresscode", joinColumns = @JoinColumn(name = "RestaurantID"), inverseJoinColumns = @JoinColumn(name = "DressCodeID"))
	private Set<DressCode> dressCodes = new HashSet<DressCode>();

	@Transient
	private List<String> selectedDressCodeId;

	public Integer getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getCoverCharges() {
		return coverCharges;
	}

	public void setCoverCharges(String coverCharges) {
		this.coverCharges = coverCharges;
	}

	public Set<DressCode> getDressCodes() {
		return dressCodes;
	}

	public void setDressCodes(Set<DressCode> dressCodes) {
		this.dressCodes = dressCodes;
	}

	public List<String> getSelectedDressCodeId() {
		return selectedDressCodeId;
	}

	public void setSelectedDressCodeId(List<String> selectedDressCodeId) {
		this.selectedDressCodeId = selectedDressCodeId;
	}

}
