package com.webservice.domain;

public class RestaurantDetail {

	private int restaurantID;
	private String restaurantName;
	private String image;
	private String cuisineList;
	private String address;
	private String contactNo;
	private String emailAddress;
	private String webAddress;
	private String facebookAddress;
	private String paymentMethod;
	private float fromPriceRange;
	private float toPriceRange;
	private String alcoholicAvailable;
	private int noOfSeats;
	private String restaurantTypeList;
	private String otherFacilitiesList;
	private String suitableList;

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

	public String getAlcoholicAvailable() {
		return alcoholicAvailable;
	}

	public void setAlcoholicAvailable(String alcoholicAvailable) {
		this.alcoholicAvailable = alcoholicAvailable;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getRestaurantTypeList() {
		return restaurantTypeList;
	}

	public void setRestaurantTypeList(String restaurantTypeList) {
		this.restaurantTypeList = restaurantTypeList;
	}

	public String getOtherFacilitiesList() {
		return otherFacilitiesList;
	}

	public void setOtherFacilitiesList(String otherFacilitiesList) {
		this.otherFacilitiesList = otherFacilitiesList;
	}

	public String getSuitableList() {
		return suitableList;
	}

	public void setSuitableList(String suitableList) {
		this.suitableList = suitableList;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCuisineList() {
		return cuisineList;
	}

	public void setCuisineList(String cuisineList) {
		this.cuisineList = cuisineList;
	}

}
