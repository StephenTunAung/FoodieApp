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
@Table(name = "tbl_restaurant")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = -7947190256127387293L;

	@Id
	@Column(name = "RestaurantID")
	@GeneratedValue
	private Integer id;

	@Column(name = "RestaurantName")
	private String restaurantName;

	@Column(name = "Address")
	private String address;

	@Column(name = "ContactNo")
	private String contactNo;

	@Column(name = "EmailAddress")
	private String emailAddress;

	@Column(name = "WebAddress")
	private String webAddress;

	@Column(name = "FacebookAddress")
	private String facebookAddress;

	@Column(name = "InCharge")
	private String inCharge;

	@Column(name = "InChargeContactNo")
	private String inChargeContactNo;

	@Column(name = "Designation")
	private String designation;

	@Column(name = "PaymentMethod")
	private String paymentMethod;

	@Column(name = "FromPriceRange")
	private float fromPriceRange;

	@Column(name = "ToPriceRange")
	private float toPriceRange;

	@Column(name = "AlcoholicAvailable")
	private boolean alcoholicAvailable;

	@Column(name = "NoOfSeats")
	private Integer noOfSeats;

	@Column(name = "Longitude")
	private float longitude;

	@Column(name = "Lattitude")
	private float lattitude;

	@Column(name = "Overview")
	private String overview;

	@Column(name = "ClosedOnModay")
	private boolean closedOnModay;

	@Column(name = "ClosedOnTuesday")
	private boolean closedOnTuesday;

	@Column(name = "ClosedOnWednesday")
	private boolean closedOnWednesday;

	@Column(name = "ClosedOnThursday")
	private boolean closedOnThursday;

	@Column(name = "ClosedOnFriday")
	private boolean closedOnFriday;

	@Column(name = "ClosedOnSatursday")
	private boolean closedOnSatursday;

	@Column(name = "ClosedOnSunday")
	private boolean closedOnSunday;

	@Column(name = "ClosedOnPH")
	private boolean closedOnPH;

	@Column(name = "image")
	@Lob
	private Blob image;

	@Transient
	private MultipartFile restaurantImage;

	@Column(name = "Thumbnail")
	@Lob
	private Blob thumbnailImage;

	@Transient
	private MultipartFile restaurantThumbnail;

	@OneToOne
	@JoinColumn(name = "TownshipID", referencedColumnName = "TownshipID")
	private Township township;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_restaurantfacilities", joinColumns = @JoinColumn(name = "RestaurantID"), inverseJoinColumns = @JoinColumn(name = "OtherFacilitiesID"))
	private Set<OtherFacility> facilities = new HashSet<OtherFacility>();

	@Transient
	private List<String> selectedFacilitiesId;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_restaurantresttype", joinColumns = @JoinColumn(name = "RestaurantID"), inverseJoinColumns = @JoinColumn(name = "RestaurantTypeID"))
	private Set<RestaurantType> restaurantTypes = new HashSet<RestaurantType>();

	@Transient
	private List<String> selectedRestaurantTypeId;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_restaurantcuisine", joinColumns = @JoinColumn(name = "RestaurantID"), inverseJoinColumns = @JoinColumn(name = "CuisineTypeID"))
	private Set<CuisineType> cuisineTypes = new HashSet<CuisineType>();

	@Transient
	private List<String> selectedCuisineTypeId;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_restaurantsuitable", joinColumns = @JoinColumn(name = "RestaurantID"), inverseJoinColumns = @JoinColumn(name = "SuitableID"))
	private Set<Suitable> suitables = new HashSet<Suitable>();

	@Transient
	private List<String> selectedSuitableId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public String getFacebookAddress() {
		return facebookAddress;
	}

	public void setFacebookAddress(String facebookAddress) {
		this.facebookAddress = facebookAddress;
	}

	public String getInCharge() {
		return inCharge;
	}

	public void setInCharge(String inCharge) {
		this.inCharge = inCharge;
	}

	public String getInChargeContactNo() {
		return inChargeContactNo;
	}

	public void setInChargeContactNo(String inChargeContactNo) {
		this.inChargeContactNo = inChargeContactNo;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public float getFromPriceRange() {
		return fromPriceRange;
	}

	public void setFromPriceRange(float fromPriceRange) {
		this.fromPriceRange = fromPriceRange;
	}

	public float getToPriceRange() {
		return toPriceRange;
	}

	public void setToPriceRange(float toPriceRange) {
		this.toPriceRange = toPriceRange;
	}

	public boolean isAlcoholicAvailable() {
		return alcoholicAvailable;
	}

	public void setAlcoholicAvailable(boolean alcoholicAvailable) {
		this.alcoholicAvailable = alcoholicAvailable;
	}

	public Integer getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLattitude() {
		return lattitude;
	}

	public void setLattitude(float lattitude) {
		this.lattitude = lattitude;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public boolean isClosedOnModay() {
		return closedOnModay;
	}

	public void setClosedOnModay(boolean closedOnModay) {
		this.closedOnModay = closedOnModay;
	}

	public boolean isClosedOnTuesday() {
		return closedOnTuesday;
	}

	public void setClosedOnTuesday(boolean closedOnTuesday) {
		this.closedOnTuesday = closedOnTuesday;
	}

	public boolean isClosedOnWednesday() {
		return closedOnWednesday;
	}

	public void setClosedOnWednesday(boolean closedOnWednesday) {
		this.closedOnWednesday = closedOnWednesday;
	}

	public boolean isClosedOnThursday() {
		return closedOnThursday;
	}

	public void setClosedOnThursday(boolean closedOnThursday) {
		this.closedOnThursday = closedOnThursday;
	}

	public boolean isClosedOnFriday() {
		return closedOnFriday;
	}

	public void setClosedOnFriday(boolean closedOnFriday) {
		this.closedOnFriday = closedOnFriday;
	}

	public boolean isClosedOnSatursday() {
		return closedOnSatursday;
	}

	public void setClosedOnSatursday(boolean closedOnSatursday) {
		this.closedOnSatursday = closedOnSatursday;
	}

	public boolean isClosedOnSunday() {
		return closedOnSunday;
	}

	public void setClosedOnSunday(boolean closedOnSunday) {
		this.closedOnSunday = closedOnSunday;
	}

	public boolean isClosedOnPH() {
		return closedOnPH;
	}

	public void setClosedOnPH(boolean closedOnPH) {
		this.closedOnPH = closedOnPH;
	}

	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public MultipartFile getRestaurantImage() {
		return restaurantImage;
	}

	public void setRestaurantImage(MultipartFile restaurantImage) {
		this.restaurantImage = restaurantImage;
	}

	public Blob getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(Blob thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public MultipartFile getRestaurantThumbnail() {
		return restaurantThumbnail;
	}

	public void setRestaurantThumbnail(MultipartFile restaurantThumbnail) {
		this.restaurantThumbnail = restaurantThumbnail;
	}

	public Set<OtherFacility> getFacilities() {
		return facilities;
	}

	public void setFacilities(Set<OtherFacility> facilities) {
		this.facilities = facilities;
	}

	public List<String> getSelectedFacilitiesId() {
		return selectedFacilitiesId;
	}

	public void setSelectedFacilitiesId(List<String> selectedFacilitiesId) {
		this.selectedFacilitiesId = selectedFacilitiesId;
	}

	public Set<RestaurantType> getRestaurantTypes() {
		return restaurantTypes;
	}

	public void setRestaurantTypes(Set<RestaurantType> restaurantTypes) {
		this.restaurantTypes = restaurantTypes;
	}

	public List<String> getSelectedRestaurantTypeId() {
		return selectedRestaurantTypeId;
	}

	public void setSelectedRestaurantTypeId(
			List<String> selectedRestaurantTypeId) {
		this.selectedRestaurantTypeId = selectedRestaurantTypeId;
	}

	public Set<CuisineType> getCuisineTypes() {
		return cuisineTypes;
	}

	public void setCuisineTypes(Set<CuisineType> cuisineTypes) {
		this.cuisineTypes = cuisineTypes;
	}

	public List<String> getSelectedCuisineTypeId() {
		return selectedCuisineTypeId;
	}

	public void setSelectedCuisineTypeId(List<String> selectedCuisineTypeId) {
		this.selectedCuisineTypeId = selectedCuisineTypeId;
	}

	public Set<Suitable> getSuitables() {
		return suitables;
	}

	public void setSuitables(Set<Suitable> suitables) {
		this.suitables = suitables;
	}

	public List<String> getSelectedSuitableId() {
		return selectedSuitableId;
	}

	public void setSelectedSuitableId(List<String> selectedSuitableId) {
		this.selectedSuitableId = selectedSuitableId;
	}

}
