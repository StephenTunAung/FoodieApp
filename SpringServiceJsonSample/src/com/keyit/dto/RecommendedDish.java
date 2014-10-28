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
@Table(name = "tbl_recommenddish")
public class RecommendedDish implements Serializable {

	private static final long serialVersionUID = -7626163297590550459L;

	@Id
	@Column(name = "RecommendDishID")
	@GeneratedValue
	private Integer recommendDishID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RestaurantID")
	private Restaurant restaurant;

	@Column(name = "RecommendDishName")
	private String recommendDishName;

	public Integer getRecommendDishID() {
		return recommendDishID;
	}

	public void setRecommendDishID(Integer recommendDishID) {
		this.recommendDishID = recommendDishID;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getRecommendDishName() {
		return recommendDishName;
	}

	public void setRecommendDishName(String recommendDishName) {
		this.recommendDishName = recommendDishName;
	}

}
