package com.keyit.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.keyit.dto.CuisineType;
import com.keyit.dto.OperationHour;
import com.keyit.dto.OtherFacility;
import com.keyit.dto.RecommendedDish;
import com.keyit.dto.Restaurant;
import com.keyit.dto.RestaurantType;
import com.keyit.dto.Suitable;
import com.keyit.service.CuisineTypeService;
import com.keyit.service.FacilityService;
import com.keyit.service.OperationHourService;
import com.keyit.service.RecommendedDishService;
import com.keyit.service.RestaurantService;
import com.keyit.service.RestaurantTypeService;
import com.keyit.service.SuitableService;
import com.keyit.service.TownshipService;
import com.keyit.utils.WebUIHandler;

@Controller
public class RestaurantController {

	private RestaurantService restaurantService;

	private TownshipService townshipService;

	private FacilityService facilityService;

	private RestaurantTypeService restaurantTypeService;

	private CuisineTypeService cuisineTypeService;

	private OperationHourService operationHourService;

	private RecommendedDishService recommendedDishService;

	private SuitableService suitableService;

	@RequestMapping(value = "/restaurant/showAddRestaurant", method = RequestMethod.GET)
	public ModelAndView gotoAddRestaurant(HttpServletRequest request) {
		// Authentication check starts
		boolean isLogin = this.isLogin(request);
		if (!isLogin) {
			ModelAndView loginFail = new ModelAndView(
					"redirect:/restaurant/showLoginPage");
			return loginFail;
		}
		// Authentication check ends
		ModelAndView modelAndView = new ModelAndView("addRestaurant");

		modelAndView.addObject("townships",
				this.townshipService.getAllTownships());

		modelAndView.addObject("facilities",
				this.facilityService.getAllFacilities());

		modelAndView.addObject("restaurantTypes",
				this.restaurantTypeService.getAllRestaurantTypes());

		modelAndView.addObject("cuisineTypes",
				this.cuisineTypeService.getAllCuisineTypes());

		modelAndView.addObject("suitables",
				this.suitableService.getAllSuitables());

		List<OperationHour> threeOperationHours = createThreeOperationHours();

		modelAndView.addObject("threeOperationHours", threeOperationHours);

		List<RecommendedDish> threeRecommendedDishes = createThreeRecommendedDishes();

		modelAndView.addObject("threeRecommendedDishs", threeRecommendedDishes);

		Restaurant restaurant = new Restaurant();

		modelAndView.addObject("restaurant", restaurant);

		return modelAndView;
	}

	@RequestMapping(value = "/restaurant/add", method = RequestMethod.POST)
	public ModelAndView addingRestaurant(
			@ModelAttribute("restaurant") Restaurant restaurant,
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

		// Image part
		Blob blob = null; // is our blob object

		byte[] buffer;
		if (null != restaurant.getRestaurantImage()) {
			try {
				buffer = restaurant.getRestaurantImage().getBytes();
				blob = new SerialBlob(buffer);
				restaurant.setImage(blob);

			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != restaurant.getRestaurantThumbnail()) {
			byte[] thumbnailBuffer;
			Blob thumbnailBlob = null;
			try {
				thumbnailBuffer = restaurant.getRestaurantThumbnail()
						.getBytes();
				thumbnailBlob = new SerialBlob(thumbnailBuffer);
				restaurant.setThumbnailImage(thumbnailBlob);

			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}

		}
		// Image part end

		Set<OtherFacility> selectedFacilities = this
				.getSelectedFacility(restaurant);
		restaurant.setFacilities(selectedFacilities);

		Set<RestaurantType> selectedRestaurantTypes = this
				.getSelectedRestaurantType(restaurant);
		restaurant.setRestaurantTypes(selectedRestaurantTypes);

		Set<CuisineType> selectedCuisineTypes = this
				.getSelectedCuisineType(restaurant);
		restaurant.setCuisineTypes(selectedCuisineTypes);

		Set<Suitable> selectedSuitables = this.getSelectedSuitable(restaurant);
		restaurant.setSuitables(selectedSuitables);

		List<OperationHour> operationHours = this.getOperationHour(request);
		List<RecommendedDish> recommendedDishes = this
				.getRecommendedDish(request);

		restaurantService.addRestaurant(restaurant);

		this.saveOperationHour(restaurant, operationHours);

		this.saveRecommendedDish(restaurant, recommendedDishes);

		String message = "Restaurant was successfully added.";

		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping(value = "/restaurant/restaurants", method = RequestMethod.GET)
	public ModelAndView listRestaurants(HttpServletRequest request) {

		// Authentication check starts
		boolean isLogin = this.isLogin(request);
		if (!isLogin) {
			ModelAndView loginFail = new ModelAndView(
					"redirect:/restaurant/showLoginPage");
			return loginFail;
		}
		// Authentication check ends

		ModelAndView modelAndView = new ModelAndView("restaurantList");

		modelAndView.addObject("listRestaurants",
				this.restaurantService.getAllRestaurants());
		return modelAndView;
	}

	@RequestMapping("/restaurant/remove/{id}")
	public ModelAndView removeRestaurant(@PathVariable("id") int id,
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
		this.restaurantService.deleteRestaurant(id);
		String message = "Restaurant was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping("/restaurant/edit/{id}")
	public ModelAndView editRestaurant(@PathVariable("id") int id,
			HttpServletRequest request) {
		// Authentication check starts
		boolean isLogin = this.isLogin(request);
		if (!isLogin) {
			ModelAndView loginFail = new ModelAndView(
					"redirect:/restaurant/showLoginPage");
			return loginFail;
		}
		// Authentication check ends
		ModelAndView modelAndView = new ModelAndView("editRestaurant");

		modelAndView.addObject("townships",
				this.townshipService.getAllTownships());

		List<OtherFacility> allFacilities = this.facilityService
				.getAllFacilities();

		List<RestaurantType> allRestaurantTypes = this.restaurantTypeService
				.getAllRestaurantTypes();

		List<CuisineType> allCuisineTypes = this.cuisineTypeService
				.getAllCuisineTypes();

		List<Suitable> allSuitables = this.suitableService.getAllSuitables();

		Restaurant restaurant = this.restaurantService.getRestaurantById(id);

		this.handleImage(restaurant, request);

		for (OtherFacility other : restaurant.getFacilities()) {
			allFacilities.remove(other);
		}

		for (RestaurantType restaurantType : restaurant.getRestaurantTypes()) {
			allRestaurantTypes.remove(restaurantType);

		}

		for (CuisineType cuisineType : restaurant.getCuisineTypes()) {
			allCuisineTypes.remove(cuisineType);
		}

		for (Suitable suitable : restaurant.getSuitables()) {
			allSuitables.remove(suitable);
		}

		modelAndView.addObject("facilities", allFacilities);

		modelAndView.addObject("restaurantTypes", allRestaurantTypes);

		modelAndView.addObject("cuisineTypes", allCuisineTypes);

		modelAndView.addObject("suitables", allSuitables);

		List<OperationHour> operationHours = this.operationHourService
				.getOperationHourByResID(id);

		int i = 1;
		for (OperationHour operationHour : operationHours) {
			modelAndView.addObject("operationHour" + i, operationHour);
			i++;
		}

		List<RecommendedDish> recommendedDishes = this.recommendedDishService
				.getRecommendedDishByResID(id);
		i = 1;
		for (RecommendedDish recommendedDish : recommendedDishes) {

			modelAndView.addObject("recommendedDish" + i, recommendedDish);
			i++;
		}

		modelAndView.addObject("restaurant",
				this.restaurantService.getRestaurantById(id));
		return modelAndView;
	}

	@RequestMapping(value = "/restaurant/editing", method = RequestMethod.POST)
	public ModelAndView editingRestaurant(
			@ModelAttribute("restaurant") Restaurant restaurant,
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

		// Image part
		Blob blob = null; // is our blob object

		byte[] buffer;
		if (restaurant.getRestaurantImage().isEmpty()) {

			Restaurant imageRestaurant = restaurantService
					.getRestaurantById(restaurant.getId());
			restaurant.setImage(imageRestaurant.getImage());

		} else {
			try {
				buffer = restaurant.getRestaurantImage().getBytes();
				blob = new SerialBlob(buffer);
				restaurant.setImage(blob);

			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		}

		if (restaurant.getRestaurantThumbnail().isEmpty()) {
			Restaurant imageRestaurant = restaurantService
					.getRestaurantById(restaurant.getId());
			restaurant.setThumbnailImage(imageRestaurant.getThumbnailImage());

		} else {
			byte[] thumbnailBuffer;
			Blob thumbnailBlob = null;
			try {
				thumbnailBuffer = restaurant.getRestaurantThumbnail()
						.getBytes();
				thumbnailBlob = new SerialBlob(thumbnailBuffer);
				restaurant.setThumbnailImage(thumbnailBlob);

			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		}
		// Image part end

		Set<OtherFacility> selectedFacilities = getSelectedFacility(restaurant);

		restaurant.setFacilities(selectedFacilities);

		Set<RestaurantType> selectedRestaurantTypes = getSelectedRestaurantType(restaurant);
		restaurant.setRestaurantTypes(selectedRestaurantTypes);

		Set<CuisineType> selectedCuisineTypes = this
				.getSelectedCuisineType(restaurant);
		restaurant.setCuisineTypes(selectedCuisineTypes);

		Set<Suitable> selectedSuitables = this.getSelectedSuitable(restaurant);
		restaurant.setSuitables(selectedSuitables);

		this.restaurantService.updateRestaurant(restaurant);
		this.operationHourService
				.deleteOperationHourByResID(restaurant.getId());

		OperationHour operationHour1 = getUpdatedOperationHour1(request);
		if (operationHour1 != null) {
			operationHour1.setRestaurant(restaurant);
			this.operationHourService.saveOperationHour(operationHour1);
		}

		OperationHour operationHour2 = getUpdatedOperationHour2(request);
		if (operationHour2 != null) {
			operationHour2.setRestaurant(restaurant);
			this.operationHourService.saveOperationHour(operationHour2);
		}

		OperationHour operationHour3 = getUpdatedOperationHour3(request);
		if (operationHour3 != null) {
			operationHour3.setRestaurant(restaurant);
			this.operationHourService.saveOperationHour(operationHour3);
		}

		List<RecommendedDish> recommendedDishes = this
				.getRecommendedDish(request);
		this.recommendedDishService.deleteRecommendedDishByResID(restaurant
				.getId());
		if (recommendedDishes.size() > 0) {
			for (RecommendedDish recommendedDish : recommendedDishes) {
				recommendedDish.setRestaurant(restaurant);
				this.recommendedDishService
						.saveRecommendedDish(recommendedDish);
			}
		}

		String message = "Restaurant was successfully updated.";
		modelAndView.addObject("message", message);
		this.deleteTempImage(restaurant, request);
		return modelAndView;
	}

	private OperationHour getUpdatedOperationHour3(HttpServletRequest request) {
		String fromOpHour3 = request.getParameter("fromOpHour3");
		String toOpHour3 = request.getParameter("toOpHour3");

		if (fromOpHour3.isEmpty() && toOpHour3.isEmpty()) {
			return null;
		}

		String sunday3 = request.getParameter("sunday3");
		String monday3 = request.getParameter("monday3");
		String tuesday3 = request.getParameter("tuesday3");
		String wednesday3 = request.getParameter("wednesday3");
		String thursday3 = request.getParameter("thursday3");
		String friday3 = request.getParameter("friday3");
		String satursday3 = request.getParameter("satursday3");
		OperationHour operationHour3 = new OperationHour();
		operationHour3.setFromOpHour(fromOpHour3);
		operationHour3.setToOpHour(toOpHour3);

		operationHour3.setSunday(stringToBoolean(sunday3));
		operationHour3.setMonday(stringToBoolean(monday3));
		operationHour3.setTuesday(stringToBoolean(tuesday3));
		operationHour3.setWednesday(stringToBoolean(wednesday3));
		operationHour3.setThursday(stringToBoolean(thursday3));
		operationHour3.setFriday(stringToBoolean(friday3));
		operationHour3.setSatursday(stringToBoolean(satursday3));

		return operationHour3;
	}

	private OperationHour getUpdatedOperationHour2(HttpServletRequest request) {
		String fromOpHour2 = request.getParameter("fromOpHour2");
		String toOpHour2 = request.getParameter("toOpHour2");

		if (fromOpHour2.isEmpty() && toOpHour2.isEmpty()) {
			return null;
		}

		String sunday2 = request.getParameter("sunday2");
		String monday2 = request.getParameter("monday2");
		String tuesday2 = request.getParameter("tuesday2");
		String wednesday2 = request.getParameter("wednesday2");
		String thursday2 = request.getParameter("thursday2");
		String friday2 = request.getParameter("friday2");
		String satursday2 = request.getParameter("satursday2");

		OperationHour operationHour2 = new OperationHour();
		operationHour2.setFromOpHour(fromOpHour2);
		operationHour2.setToOpHour(toOpHour2);

		operationHour2.setSunday(stringToBoolean(sunday2));
		operationHour2.setMonday(stringToBoolean(monday2));
		operationHour2.setTuesday(stringToBoolean(tuesday2));
		operationHour2.setWednesday(stringToBoolean(wednesday2));
		operationHour2.setThursday(stringToBoolean(thursday2));
		operationHour2.setFriday(stringToBoolean(friday2));
		operationHour2.setSatursday(stringToBoolean(satursday2));
		return operationHour2;
	}

	private OperationHour getUpdatedOperationHour1(HttpServletRequest request) {
		OperationHour operationHour1 = new OperationHour();

		String fromOpHour1 = request.getParameter("fromOpHour1");
		String toOpHour1 = request.getParameter("toOpHour1");

		if (fromOpHour1.isEmpty() && toOpHour1.isEmpty()) {
			return null;
		}

		String sunday1 = request.getParameter("sunday1");
		String monday1 = request.getParameter("monday1");
		String tuesday1 = request.getParameter("tuesday1");
		String wednesday1 = request.getParameter("wednesday1");
		String thursday1 = request.getParameter("thursday1");
		String friday1 = request.getParameter("friday1");
		String satursday1 = request.getParameter("satursday1");

		operationHour1.setFromOpHour(fromOpHour1);
		operationHour1.setToOpHour(toOpHour1);

		operationHour1.setSunday(stringToBoolean(sunday1));
		operationHour1.setMonday(stringToBoolean(monday1));
		operationHour1.setTuesday(stringToBoolean(tuesday1));
		operationHour1.setWednesday(stringToBoolean(wednesday1));
		operationHour1.setThursday(stringToBoolean(thursday1));
		operationHour1.setFriday(stringToBoolean(friday1));
		operationHour1.setSatursday(stringToBoolean(satursday1));

		return operationHour1;
	}

	private Set<OtherFacility> getSelectedFacility(Restaurant restaurant) {
		List<String> selectedFacilitiesID = restaurant
				.getSelectedFacilitiesId();
		Set<OtherFacility> selectedFacilities = new HashSet<OtherFacility>();

		if (null != selectedFacilitiesID) {

			for (String selectedFacilityID : selectedFacilitiesID) {
				OtherFacility facility = this.facilityService
						.getFacilityByID(Integer.parseInt(selectedFacilityID));
				selectedFacilities.add(facility);
			}
		}

		return selectedFacilities;
	}

	private Set<RestaurantType> getSelectedRestaurantType(Restaurant restaurant) {
		List<String> selectedRestaurantTypeIds = restaurant
				.getSelectedRestaurantTypeId();
		Set<RestaurantType> selectedRestaurantTypes = new HashSet<RestaurantType>();

		if (null != selectedRestaurantTypeIds) {

			for (String selectedRestaurantTypeId : selectedRestaurantTypeIds) {
				RestaurantType restaurantType = this.restaurantTypeService
						.getRestaurantTypeById(Integer
								.parseInt(selectedRestaurantTypeId));
				selectedRestaurantTypes.add(restaurantType);
			}
		}

		return selectedRestaurantTypes;

	}

	private Set<CuisineType> getSelectedCuisineType(Restaurant restaurant) {
		List<String> selectedCuisineTypeIds = restaurant
				.getSelectedCuisineTypeId();
		Set<CuisineType> selectedCuisineTypes = new HashSet<CuisineType>();
		if (null != selectedCuisineTypeIds) {
			for (String selectedCuisineTypeId : selectedCuisineTypeIds) {
				CuisineType cuisineType = this.cuisineTypeService
						.getCuisineTypeById(Integer
								.parseInt(selectedCuisineTypeId));
				selectedCuisineTypes.add(cuisineType);
			}
		}

		return selectedCuisineTypes;
	}

	private Set<Suitable> getSelectedSuitable(Restaurant restaurant) {
		List<String> selectedSuitableIds = restaurant.getSelectedSuitableId();

		Set<Suitable> selectedSuitables = new HashSet<Suitable>();
		if (null != selectedSuitableIds) {
			for (String selectedSuitableId : selectedSuitableIds) {
				Suitable suitable = this.suitableService
						.getSuitableById(Integer.parseInt(selectedSuitableId));
				selectedSuitables.add(suitable);
			}
		}
		return selectedSuitables;
	}

	private List<OperationHour> createThreeOperationHours() {
		List<OperationHour> operationHours = new ArrayList<OperationHour>();

		for (int i = 0; i < 3; i++) {
			OperationHour operationHour = new OperationHour();
			operationHour.setFromOpHour("");
			operationHour.setToOpHour("");
			operationHour.setSunday(true);
			operationHours.add(operationHour);

		}

		return operationHours;
	}

	private List<RecommendedDish> createThreeRecommendedDishes() {
		List<RecommendedDish> recommendedDishs = new ArrayList<RecommendedDish>();
		for (int i = 0; i < 3; i++) {
			RecommendedDish recommendedDish = new RecommendedDish();
			recommendedDish.setRecommendDishName("");
			recommendedDishs.add(recommendedDish);
		}
		return recommendedDishs;
	}

	private List<OperationHour> getOperationHour(HttpServletRequest request) {

		List<OperationHour> operationHours = new ArrayList<OperationHour>();

		OperationHour operationHour1 = getUpdatedOperationHour1(request);

		if (null != operationHour1) {
			operationHours.add(operationHour1);
		}

		OperationHour operationHour2 = getUpdatedOperationHour2(request);

		if (operationHour2 != null) {
			operationHours.add(operationHour2);
		}

		OperationHour operationHour3 = getUpdatedOperationHour3(request);
		if (operationHour3 != null) {

			operationHours.add(operationHour3);
		}

		return operationHours;
	}

	private List<RecommendedDish> getRecommendedDish(HttpServletRequest request) {
		List<RecommendedDish> recommendedDishs = new ArrayList<RecommendedDish>();

		RecommendedDish recommendedDish1 = new RecommendedDish();
		String recommendedDishName1 = request
				.getParameter("recommendedDishName1");

		if (null != recommendedDishName1 && !("".equals(recommendedDishName1))) {
			recommendedDish1.setRecommendDishName(recommendedDishName1);
			recommendedDishs.add(recommendedDish1);
		}

		RecommendedDish recommendedDish2 = new RecommendedDish();
		String recommendedDishName2 = request
				.getParameter("recommendedDishName2");
		if (null != recommendedDishName2 && !("".equals(recommendedDishName2))) {
			recommendedDish2.setRecommendDishName(recommendedDishName2);
			recommendedDishs.add(recommendedDish2);
		}

		RecommendedDish recommendedDish3 = new RecommendedDish();
		String recommendedDishName3 = request
				.getParameter("recommendedDishName3");

		if (null != recommendedDishName3 && !("".equals(recommendedDishName3))) {
			recommendedDish3.setRecommendDishName(recommendedDishName3);
			recommendedDishs.add(recommendedDish3);
		}

		return recommendedDishs;

	}

	private boolean stringToBoolean(String str) {

		if ("on".equalsIgnoreCase(str)) {
			return true;
		}

		return false;
	}

	private void saveOperationHour(Restaurant restaurant,
			List<OperationHour> operationHours) {
		if (operationHours.size() > 0) {
			for (OperationHour op : operationHours) {
				op.setRestaurant(restaurant);
				operationHourService.saveOperationHour(op);
			}
		}

	}

	private void saveRecommendedDish(Restaurant restaurant,
			List<RecommendedDish> recommendedDishes) {
		if (recommendedDishes.size() > 0) {
			for (RecommendedDish recommendedDish : recommendedDishes) {
				recommendedDish.setRestaurant(restaurant);
				recommendedDishService.saveRecommendedDish(recommendedDish);
			}
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

	private void deleteTempImage(Restaurant restaurant,
			HttpServletRequest request) {
		String fileName = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/resources/temp/thumb" + restaurant.getId();
		File thumbToDelete = new File(fileName);
		if (thumbToDelete.exists()) {
			thumbToDelete.delete();
		}

		String image = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/resources/temp/image" + restaurant.getId();
		File imageToDelete = new File(image);
		if (imageToDelete.exists()) {
			imageToDelete.delete();
		}

	}

	private void handleImage(Restaurant restaurant, HttpServletRequest request) {

		if (restaurant.getThumbnailImage() != null) {
			Blob thumb = restaurant.getThumbnailImage();

			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/resources/temp/thumb" + restaurant.getId();

			WebUIHandler.createTempImage(fileName, thumb);
			// this.createTempImage(fileName, thumb);
		}

		if (restaurant.getImage() != null) {
			Blob image = restaurant.getImage();
			String fileName = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/resources/temp/image" + restaurant.getId();
			WebUIHandler.createTempImage(fileName, image);
			// this.createTempImage(fileName, image);

		}

	}

	@Autowired(required = true)
	@Qualifier(value = "restaurantService")
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@Autowired(required = true)
	@Qualifier(value = "townshipService")
	public void setTownshipService(TownshipService townshipService) {
		this.townshipService = townshipService;
	}

	@Autowired(required = true)
	@Qualifier(value = "facilityService")
	public void setFacilityService(FacilityService facilityService) {
		this.facilityService = facilityService;
	}

	@Autowired(required = true)
	@Qualifier(value = "restaurantTypeService")
	public void setRestaurantTypeService(
			RestaurantTypeService restaurantTypeService) {
		this.restaurantTypeService = restaurantTypeService;
	}

	@Autowired(required = true)
	@Qualifier(value = "cuisineTypeService")
	public void setCuisineTypeService(CuisineTypeService cuisineTypeService) {
		this.cuisineTypeService = cuisineTypeService;
	}

	@Autowired(required = true)
	@Qualifier(value = "suitableService")
	public void setSuitableService(SuitableService suitableService) {
		this.suitableService = suitableService;
	}

	@Autowired(required = true)
	@Qualifier(value = "operationHourService")
	public void setOperationHourService(
			OperationHourService operationHourService) {
		this.operationHourService = operationHourService;
	}

	@Autowired(required = true)
	@Qualifier(value = "recommendedDishService")
	public void setRecommendedDishService(
			RecommendedDishService recommendedDishService) {
		this.recommendedDishService = recommendedDishService;
	}

}
