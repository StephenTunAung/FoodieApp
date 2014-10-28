package com.keyit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.keyit.dto.Restaurant;
import com.keyit.service.RestaurantService;

//@Controller
public class EventController {
	
	private RestaurantService restaurantService;

	//@RequestMapping(value = "/restaurant/showAddEvent/{id}", method = RequestMethod.GET)
	public ModelAndView gotoAddEvent(@PathVariable("id") int id,
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("addEvent");
		
		Restaurant restaurant = restaurantService.getRestaurantById(id);
		
		modelAndView.addObject("restaurant", restaurant);

		return modelAndView;

	}
	
	//@Autowired(required = true)
	//@Qualifier(value = "restaurantService")
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

}
