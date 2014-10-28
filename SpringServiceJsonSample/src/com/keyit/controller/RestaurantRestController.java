package com.keyit.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.dao.RestaurantService;
import com.webservice.domain.Restaurant;
import com.webservice.domain.RestaurantDetail;

@RestController
@RequestMapping("/webservice/restaurant/")
public class RestaurantRestController {

	RestaurantService restaurantService = new RestaurantService();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public RestaurantDetail getRestaurantByID(@PathVariable int id) {

		RestaurantDetail restaurantDetail = restaurantService
				.getRestaurantById(id);
		return restaurantDetail;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		return restaurants;
	}

}