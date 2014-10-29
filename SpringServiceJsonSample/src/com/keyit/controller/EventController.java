package com.keyit.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.keyit.bean.AdminUser;
import com.keyit.dto.DressCode;
import com.keyit.dto.Event;
import com.keyit.dto.Restaurant;
import com.keyit.service.DressCodeService;
import com.keyit.service.EventService;
import com.keyit.service.RestaurantService;

@Controller
public class EventController {

	private RestaurantService restaurantService;

	private EventService eventService;

	private DressCodeService dressCodeService;

	@RequestMapping(value = "/event/showEventDetail/{id}", method = RequestMethod.GET)
	public ModelAndView gotoEventDetail(@PathVariable("id") int id,
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("eventDetail");

		Event event = this.eventService.getEventByRestaurantId(id);

		List<DressCode> dressCodes = this.dressCodeService.getAllDressCodes();

		if (event != null) {
			modelAndView.addObject("event", event);
			for (DressCode dressCode : event.getDressCodes()) {
				dressCodes.remove(dressCode);
			}
		} else {
			modelAndView.addObject("event", new Event());
		}

		Restaurant restaurant = this.restaurantService.getRestaurantById(id);

		modelAndView.addObject("restaurant", restaurant);

		modelAndView.addObject("dressCodes", dressCodes);

		return modelAndView;

	}

	@RequestMapping(value = "/event/add", method = RequestMethod.POST)
	public ModelAndView addingEvent(@ModelAttribute("restaurant") Event event,
			HttpServletRequest request) {
		// Authentication check starts
		boolean isLogin = this.isLogin(request);
		if (!isLogin) {
			ModelAndView loginFail = new ModelAndView(
					"redirect:/restaurant/showLoginPage");
			return loginFail;
		}
		// Authentication check ends

		ModelAndView modelAndView = new ModelAndView(
				"redirect:/restaurant/restaurants");

		Set<DressCode> selectedDressCodes = this.getSelectedDressCode(event);
		Restaurant restaurant = this.restaurantService
				.getRestaurantById(Integer.parseInt(request
						.getParameter("restId")));
		event.setRestaurant(restaurant);
		event.setDressCodes(selectedDressCodes);

		eventService.addOrUpdateEvent(event);

		String message = "Restaurant was successfully added.";

		modelAndView.addObject("message", message);
		return modelAndView;
	}

	private Set<DressCode> getSelectedDressCode(Event event) {
		List<String> selectedDressCodeIDs = event.getSelectedDressCodeId();
		Set<DressCode> selectedDressCodes = new HashSet<DressCode>();

		if (null != selectedDressCodeIDs) {

			for (String selectedDressCodeID : selectedDressCodeIDs) {
				DressCode dressCode = this.dressCodeService
						.getDressCodeById(Integer.parseInt(selectedDressCodeID));
				selectedDressCodes.add(dressCode);
			}
		}

		return selectedDressCodes;
	}

	private boolean isLogin(HttpServletRequest request) {
		AdminUser adminUser = (AdminUser) request.getSession().getAttribute(
				"adminUser");

		if (adminUser == null) {
			return false;
		}

		return true;
	}

	@Autowired(required = true)
	@Qualifier(value = "restaurantService")
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@Autowired(required = true)
	@Qualifier(value = "eventService")
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Autowired(required = true)
	@Qualifier(value = "dressCodeService")
	public void setDressCodeService(DressCodeService dressCodeService) {
		this.dressCodeService = dressCodeService;
	}

}
