package com.keyit.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

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
import com.keyit.utils.WebUIHandler;

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
			this.handleImage(event, request);
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

		// Is new Event

		Event savedEvent = this.eventService.getEventByRestaurantId(Integer
				.parseInt(request.getParameter("restId")));

		// Image part
		Blob blob = null; // is our blob object
		byte[] thumbnailBuffer;
		Blob thumbnailBlob = null;
		byte[] buffer;

		// if edit Event
		if (savedEvent != null) {

			if (event.getEventImagePart().isEmpty()) {

				event.setEventImage(savedEvent.getEventImage());

			} else {
				try {
					buffer = event.getEventImagePart().getBytes();
					blob = new SerialBlob(buffer);
					event.setEventImage(blob);

				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}
			}
			if (event.getEventThumbPart().isEmpty()) {
				// Thumb

				event.setEventThumb(savedEvent.getEventThumb());

			} else {
				try {

					// Thumb
					thumbnailBuffer = event.getEventThumbPart().getBytes();
					thumbnailBlob = new SerialBlob(thumbnailBuffer);
					event.setEventThumb(thumbnailBlob);

				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}

			}

		}

		// new Event ends

		// if new Event
		else {
			if (null != event.getEventImagePart()
					&& null != event.getEventThumbPart()) {
				try {
					buffer = event.getEventImagePart().getBytes();
					blob = new SerialBlob(buffer);
					event.setEventImage(blob);

					// Thumb
					thumbnailBuffer = event.getEventThumbPart().getBytes();
					thumbnailBlob = new SerialBlob(thumbnailBuffer);
					event.setEventThumb(thumbnailBlob);

				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// Image part end

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

	private void handleImage(Event event, HttpServletRequest request) {

		if (event.getEventThumb() != null) {
			Blob thumb = event.getEventThumb();

			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/resources/temp/eventThumb" + event.getEventId();
			WebUIHandler.createTempImage(fileName, thumb);
			// this.createTempImage(fileName, thumb);
		}

		if (event.getEventImage() != null) {
			Blob image = event.getEventImage();
			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/resources/temp/eventImage" + event.getEventId();
			WebUIHandler.createTempImage(fileName, image);
			// this.createTempImage(fileName, image);

		}

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
