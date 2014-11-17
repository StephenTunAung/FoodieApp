package com.keyit.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.keyit.dto.Promotion;
import com.keyit.dto.Restaurant;
import com.keyit.service.PromotionService;
import com.keyit.service.RestaurantService;
import com.keyit.utils.WebUIHandler;

@Controller
public class PromotionController {

	private RestaurantService restaurantService;

	private PromotionService promotionService;

	@RequestMapping(value = "/promotion/showPromotionDetail/{id}", method = RequestMethod.GET)
	public ModelAndView gotoPromotionDetail(@PathVariable("id") int id,
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("promotionDetail");

		Promotion promotion = this.promotionService
				.getPromotionIdByRestaurantId(id);

		if (promotion != null) {
			modelAndView.addObject("promotion", promotion);
			this.handleImage(promotion, request);

		} else {
			modelAndView.addObject("promotion", new Promotion());
		}

		Restaurant restaurant = this.restaurantService.getRestaurantById(id);

		modelAndView.addObject("restaurant", restaurant);

		return modelAndView;

	}

	@RequestMapping(value = "/promotion/add", method = RequestMethod.POST)
	public ModelAndView addingPromotion(
			@ModelAttribute("restaurant") Promotion promotion,
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

		Promotion savedPromotion = this.promotionService
				.getPromotionIdByRestaurantId(Integer.parseInt(request
						.getParameter("restId")));

		Date date;
		String formattedDate = null;
		try {
			date = new SimpleDateFormat("yyyy-MMM-dd").parse(promotion
					.getValidDate());
			formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		if (formattedDate != null) {
			promotion.setValidDate(formattedDate);
		} else {
			promotion.setValidDate(savedPromotion.getValidDate());
		}

		// Image part
		Blob blob = null; // is our blob object
		byte[] thumbnailBuffer;
		Blob thumbnailBlob = null;
		byte[] buffer;

		// if edit Promotion
		if (savedPromotion != null) {

			if (promotion.getPromoImagePart().isEmpty()) {

				promotion.setPromoImage(savedPromotion.getPromoImage());

			} else {
				try {
					buffer = promotion.getPromoImagePart().getBytes();
					blob = new SerialBlob(buffer);
					promotion.setPromoImage(blob);

				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}
			}
			if (promotion.getPromoThumbPart().isEmpty()) {
				// Thumb

				promotion.setPromoThumb(savedPromotion.getPromoThumb());

			} else {
				try {

					// Thumb
					thumbnailBuffer = promotion.getPromoThumbPart().getBytes();
					thumbnailBlob = new SerialBlob(thumbnailBuffer);
					promotion.setPromoThumb(thumbnailBlob);

				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}
			}

		}

		// new Promotion ends

		// if new Promotion
		else {
			if (null != promotion.getPromoImagePart()
					&& null != promotion.getPromoThumbPart()) {
				try {
					buffer = promotion.getPromoImagePart().getBytes();
					blob = new SerialBlob(buffer);
					promotion.setPromoImage(blob);

					// Thumb
					thumbnailBuffer = promotion.getPromoThumbPart().getBytes();
					thumbnailBlob = new SerialBlob(thumbnailBuffer);
					promotion.setPromoThumb(thumbnailBlob);

				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// Image part end

		Restaurant restaurant = this.restaurantService
				.getRestaurantById(Integer.parseInt(request
						.getParameter("restId")));
		promotion.setRestaurant(restaurant);
		// Set checkboxes value

		promotion = this.getUpdatedCheckbox(request, promotion);

		promotionService.addOrUpdateEvent(promotion);

		String message = "Promotion was successfully added.";

		modelAndView.addObject("message", message);
		return modelAndView;
	}

	private void handleImage(Promotion promotion, HttpServletRequest request) {

		if (promotion.getPromoThumb() != null) {
			Blob thumb = promotion.getPromoThumb();

			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/resources/temp/proThumb" + promotion.getPromotionId();
			WebUIHandler.createTempImage(fileName, thumb);
		}

		if (promotion.getPromoImage() != null) {
			Blob image = promotion.getPromoImage();
			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/resources/temp/proImage" + promotion.getPromotionId();
			WebUIHandler.createTempImage(fileName, image);

		}

	}

	private Promotion getUpdatedCheckbox(HttpServletRequest request,
			Promotion promotion) {

		String validOnMonday = request.getParameter("validOnMonday");
		String validOnTuesday = request.getParameter("validOnTuesday");
		String validOnWednesday = request.getParameter("validOnWednesday");
		String validOnThursday = request.getParameter("validOnThursday");
		String validOnFriday = request.getParameter("validOnFriday");
		String validOnSatursday = request.getParameter("validOnSatursday");
		String validOnSunday = request.getParameter("validOnSunday");
		String validOnPH = request.getParameter("validOnPH");
		String validNA = request.getParameter("validNA");

		promotion.setValidOnMonday(WebUIHandler.stringToBoolean(validOnMonday));
		promotion.setValidOnTuesday(WebUIHandler
				.stringToBoolean(validOnTuesday));
		promotion.setValidOnWednesday(WebUIHandler
				.stringToBoolean(validOnWednesday));
		promotion.setValidOnThursday(WebUIHandler
				.stringToBoolean(validOnThursday));
		promotion.setValidOnFriday(WebUIHandler.stringToBoolean(validOnFriday));
		promotion.setValidOnSatursday(WebUIHandler
				.stringToBoolean(validOnSatursday));
		promotion.setValidOnSunday(WebUIHandler.stringToBoolean(validOnSunday));
		promotion.setValidOnPH(WebUIHandler.stringToBoolean(validOnPH));
		promotion.setValidNA(WebUIHandler.stringToBoolean(validNA));

		return promotion;
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
	@Qualifier(value = "promotionService")
	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

}
