package com.keyit.dto;

import java.io.Serializable;
import java.sql.Blob;
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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tbl_event")
public class Event implements Serializable {

	private static final long serialVersionUID = -3737837345282400260L;

	@Id
	@Column(name = "EventID")
	@GeneratedValue
	private Integer eventId;

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
	private float coverCharges;

	@Column(name = "EventImage")
	@Lob
	private Blob eventImage;

	@Transient
	private MultipartFile eventImagePart;

	@Column(name = "EventThumb")
	@Lob
	private Blob eventThumb;

	@Transient
	private MultipartFile eventThumbPart;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_eventdresscode", joinColumns = @JoinColumn(name = "EventID"), inverseJoinColumns = @JoinColumn(name = "DressCodeID"))
	private Set<DressCode> dressCodes = new HashSet<DressCode>();

	@Transient
	private List<String> selectedDressCodeId;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
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

	public float getCoverCharges() {
		return coverCharges;
	}

	public void setCoverCharges(float coverCharges) {
		this.coverCharges = coverCharges;
	}

	public Blob getEventImage() {
		return eventImage;
	}

	public void setEventImage(Blob eventImage) {
		this.eventImage = eventImage;
	}

	public MultipartFile getEventImagePart() {
		return eventImagePart;
	}

	public void setEventImagePart(MultipartFile eventImagePart) {
		this.eventImagePart = eventImagePart;
	}

	public Blob getEventThumb() {
		return eventThumb;
	}

	public void setEventThumb(Blob eventThumb) {
		this.eventThumb = eventThumb;
	}

	public MultipartFile getEventThumbPart() {
		return eventThumbPart;
	}

	public void setEventThumbPart(MultipartFile eventThumbPart) {
		this.eventThumbPart = eventThumbPart;
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
