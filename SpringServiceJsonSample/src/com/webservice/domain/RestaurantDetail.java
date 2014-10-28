package com.webservice.domain;


public class RestaurantDetail {

	private int restaurantID;
	private String restaurantName;
	private String image;
	private String cuisineList;

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
