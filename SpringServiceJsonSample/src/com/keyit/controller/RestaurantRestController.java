package com.keyit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.dao.RestaurantService;
import com.webservice.domain.Cuisine;
import com.webservice.domain.Restaurant;
import com.webservice.domain.RestaurantDetail;
import com.webservice.domain.RestaurantType;
import com.webservice.domain.Township;

@RestController
@RequestMapping("/webservice/restaurant/")
public class RestaurantRestController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public RestaurantDetail getRestaurantByID(@PathVariable int id) {

		RestaurantService restaurantService = new RestaurantService();
		RestaurantDetail restaurantDetail = restaurantService
				.getRestaurantById(id);

		return restaurantDetail;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Restaurant> getAllRestaurants() {

		RestaurantService restaurantService = new RestaurantService();
		List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		return restaurants;
	}

	@RequestMapping(value = "/restaurantlist/{type}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Restaurant> getAllRestaurantListByID(@PathVariable String type) {

		RestaurantService restaurantService = new RestaurantService();
		List<Restaurant> restaurants = new ArrayList<Restaurant>();

		String splitStrings[] = type.split(",");
		int searchtype = Integer.parseInt(splitStrings[0]);
		int searchByIds = Integer.parseInt(splitStrings[1]);

		switch (searchtype) {
		case 1:
			restaurants = restaurantService
					.getRestaurantByTownshipID(searchByIds);
			break;
		case 2:
			restaurants = restaurantService
					.getRestaurantByCuisineID(searchByIds);
			break;
		case 3:
			restaurants = restaurantService
					.getRestaurantByRestaurantTypeID(searchByIds);
			break;
		}

		return restaurants;
	}

	@RequestMapping(value = "/townships", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Township> getAllTownships() {

		RestaurantService restaurantService = new RestaurantService();
		List<Township> townships = restaurantService.getAllTownships();
		return townships;
	}

	@RequestMapping(value = "/cuisines", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Cuisine> getAllCuisines() {
		RestaurantService restaurantService = new RestaurantService();
		List<Cuisine> cuisines = restaurantService.getAllCuinsines();
		return cuisines;
	}

	@RequestMapping(value = "/restauranttypes", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<RestaurantType> getAllRestaurantTypes() {
		RestaurantService restaurantService = new RestaurantService();
		List<RestaurantType> restaurantTypes = restaurantService
				.getAllRestaurantTypes();
		return restaurantTypes;
	}

}