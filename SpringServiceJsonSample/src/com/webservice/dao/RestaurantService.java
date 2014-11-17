package com.webservice.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import biz.source_code.base64Coder.Base64Coder;

import com.webservice.domain.Cuisine;
import com.webservice.domain.Restaurant;
import com.webservice.domain.RestaurantDetail;
import com.webservice.domain.RestaurantType;
import com.webservice.domain.Township;

public class RestaurantService {

	private Connection connection = null;

	public int getRestaurantCount() {
		connection = this.getConnection();
		int count = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select count(*) as count from tbl_restaurant");
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		connection = this.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select RestaurantID, RestaurantName,Thumbnail from tbl_restaurant limit 15");
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantID(rs.getInt("RestaurantID"));
				restaurant.setRestaurantName(rs.getString("RestaurantName"));
				restaurant
						.setImage(encodeBase64String(rs.getBytes("Thumbnail")));

				restaurants.add(restaurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return restaurants;
	}

	public List<Restaurant> getRestaurantByTownshipID(int townshipID) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		connection = this.getConnection();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select RestaurantID,RestaurantName,Address,Thumbnail from tbl_restaurant where TownshipID=?");
			preparedStatement.setInt(1, townshipID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantID(rs.getInt("RestaurantID"));
				restaurant.setRestaurantName(rs.getString("RestaurantName"));
				restaurant.setAddress(rs.getString("Address"));
				restaurant
						.setImage(encodeBase64String(rs.getBytes("Thumbnail")));

				restaurants.add(restaurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return restaurants;

	}

	public List<Restaurant> getRestaurantByCuisineID(int cuisineTypeID) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		connection = this.getConnection();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT r.RestaurantID,RestaurantName,Address,Thumbnail FROM tbl_restaurant r inner JOIN tbl_restaurantcuisine rc on r.RestaurantID=rc.RestaurantID where CuisineTypeID=?");
			preparedStatement.setInt(1, cuisineTypeID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantID(rs.getInt("RestaurantID"));
				restaurant.setRestaurantName(rs.getString("RestaurantName"));
				restaurant.setAddress(rs.getString("Address"));
				restaurant
						.setImage(encodeBase64String(rs.getBytes("Thumbnail")));

				restaurants.add(restaurant);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return restaurants;
	}

	public List<Restaurant> getRestaurantByRestaurantTypeID(int restaurantTypeID) {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		connection = this.getConnection();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT r.RestaurantID,RestaurantName,Address,Thumbnail FROM tbl_restaurant r inner JOIN tbl_restaurantresttype rtype on r.RestaurantID=rtype.RestaurantID where RestaurantTypeID=?");
			preparedStatement.setInt(1, restaurantTypeID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantID(rs.getInt("RestaurantID"));
				restaurant.setRestaurantName(rs.getString("RestaurantName"));
				restaurant.setAddress(rs.getString("Address"));
				restaurant
						.setImage(encodeBase64String(rs.getBytes("Thumbnail")));

				restaurants.add(restaurant);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return restaurants;
	}

	public RestaurantDetail getRestaurantById(int restaurantID) {
		RestaurantDetail restaurantDetail = new RestaurantDetail();
		connection = this.getConnection();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select RestaurantID, RestaurantName,Address,EmailAddress,ContactNo,"
							+ "WebAddress,FacebookAddress,PaymentMethod,FromPriceRange,ToPriceRange,AlcoholicAvailable,"
							+ "NoOfSeats,Image from tbl_restaurant where RestaurantID=?");
			preparedStatement.setInt(1, restaurantID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				restaurantDetail.setRestaurantID(rs.getInt("RestaurantID"));
				restaurantDetail.setRestaurantName(rs
						.getString("RestaurantName"));
				restaurantDetail.setAddress(rs.getString("Address"));
				restaurantDetail.setContactNo(rs.getString("ContactNo"));
				restaurantDetail.setEmailAddress(rs.getString("EmailAddress"));
				restaurantDetail.setWebAddress(rs.getString("WebAddress"));
				restaurantDetail.setFacebookAddress(rs
						.getString("FacebookAddress"));
				restaurantDetail
						.setPaymentMethod(rs.getString("PaymentMethod"));
				restaurantDetail.setFromPriceRange(rs
						.getFloat("FromPriceRange"));
				restaurantDetail.setToPriceRange(rs.getFloat("ToPriceRange"));
				restaurantDetail.setAlcoholicAvailable(rs
						.getBoolean("AlcoholicAvailable"));
				restaurantDetail.setNoOfSeats(rs.getInt("NoOfSeats"));

				String encoded = encodeBase64String(rs.getBytes("Image"));
				restaurantDetail.setImage(encoded);
			}

			restaurantDetail.setCuisineList(getCuisineList(restaurantID));
			restaurantDetail
					.setRestaurantTypeList(getRestaurantTypeList(restaurantID));
			restaurantDetail
					.setOtherFacilitiesList(getOtherFacilitiesList(restaurantID));
			restaurantDetail.setSuitableList(getSuitableList(restaurantID));
			restaurantDetail
					.setRecommendedDishList(getRecommendedDishList(restaurantID));
			restaurantDetail
					.setOperationHourList(getOperationHourList(restaurantID));
		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return restaurantDetail;
	}

	private String getCuisineList(int restaurantID) {
		// Get Cuisine information of Restaurant
		String cuisineList = "";
		connection = this.getConnection();
		try {
			PreparedStatement psCuisine = connection
					.prepareStatement("select GROUP_CONCAT(CuisineName) as CuisineList from tbl_cuisinetype c inner join tbl_restaurantcuisine rc on c.CuisineTypeID = rc.CuisineTypeID and rc.RestaurantID=?");
			psCuisine.setInt(1, restaurantID);
			ResultSet rsCuisine = psCuisine.executeQuery();

			if (rsCuisine.next()) {
				cuisineList = rsCuisine.getString("CuisineList");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cuisineList;

	}

	private String getRestaurantTypeList(int restaurantID) {
		// Get Restaurant Type inforamtion of Restaurant
		String restaurantTypeList = "";
		connection = this.getConnection();
		try {
			PreparedStatement psRestaurantType = connection
					.prepareStatement("select GROUP_CONCAT(RestaurantTypeName) as RestaurantTypeList from  tbl_restauranttype rt inner join tbl_restaurantresttype rrt on rt.RestaurantTypeID=rrt.RestaurantTypeID and rrt.RestaurantID=?");
			psRestaurantType.setInt(1, restaurantID);
			ResultSet rsRestaurantType = psRestaurantType.executeQuery();

			if (rsRestaurantType.next()) {
				restaurantTypeList = rsRestaurantType
						.getString("RestaurantTypeList");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return restaurantTypeList;
	}

	private String getOtherFacilitiesList(int restaurantID) {
		// Get Other Facilities information of Restaurant
		String otherFacilitiesList = "";
		connection = this.getConnection();
		try {
			PreparedStatement psOtherFacilities = connection
					.prepareStatement("select GROUP_CONCAT(OtherFacilitiesName)as OtherFacilitiesList from tbl_otherfacilities of inner join tbl_restaurantfacilities rf on of.OtherFacilitiesID=rf.OtherFacilitiesID and RestaurantID=?");
			psOtherFacilities.setInt(1, restaurantID);
			ResultSet rsOtherFacilities = psOtherFacilities.executeQuery();

			if (rsOtherFacilities.next()) {
				otherFacilitiesList = rsOtherFacilities
						.getString("OtherFacilitiesList");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return otherFacilitiesList;
	}

	private String getSuitableList(int restaurantID) {
		// Get Suitable information of restaurant
		String suitableList = "";
		connection = this.getConnection();
		try {
			PreparedStatement psSuitable = connection
					.prepareStatement("select GROUP_CONCAT(SuitableName) as SuitableList from tbl_suitable s INNER JOIN tbl_restaurantsuitable rs on s.SuitableID=rs.SuitableID and RestaurantID=?");
			psSuitable.setInt(1, restaurantID);
			ResultSet rsSuitable = psSuitable.executeQuery();

			if (rsSuitable.next()) {
				suitableList = rsSuitable.getString("SuitableList");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return suitableList;
	}

	private String getOperationHourList(int restaurantID) {
		// Get Suitable information of restaurant
		String operationHourList = "";
		connection = this.getConnection();
		try {
			PreparedStatement psoperationHour = connection
					.prepareStatement("select GROUP_CONCAT(RestOperationHours) as OperationHourList from tbl_restaurantoperationhours WHERE RestaurantID=?");
			psoperationHour.setInt(1, restaurantID);
			ResultSet rsoperationHour = psoperationHour.executeQuery();

			if (rsoperationHour.next()) {
				operationHourList = rsoperationHour
						.getString("OperationHourList");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return operationHourList;
	}

	private String getRecommendedDishList(int restaurantID) {
		// Get Suitable information of restaurant
		String recommendedDishList = "";
		connection = this.getConnection();
		try {
			PreparedStatement psrecommendedDish = connection
					.prepareStatement("select GROUP_CONCAT(RecommendDishName) as RecommendedDishList from tbl_recommenddish WHERE RestaurantID=?");
			psrecommendedDish.setInt(1, restaurantID);
			ResultSet rsrecommendedDish = psrecommendedDish.executeQuery();

			if (rsrecommendedDish.next()) {
				recommendedDishList = rsrecommendedDish
						.getString("RecommendedDishList");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return recommendedDishList;
	}

	public List<Township> getAllTownships() {
		List<Township> townships = new ArrayList<Township>();
		connection = this.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select TownshipID,TownshipName from tbl_township limit 15");
			while (rs.next()) {
				Township township = new Township();
				township.setTownshipID(rs.getInt("TownshipID"));
				township.setTownshipName(rs.getString("TownshipName"));
				townships.add(township);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return townships;
	}

	public List<Cuisine> getAllCuinsines() {

		List<Cuisine> cuisines = new ArrayList<Cuisine>();
		connection = this.getConnection();
		try {
			Statement statment = connection.createStatement();
			ResultSet rs = statment
					.executeQuery("select CuisineTypeID,CuisineName from tbl_cuisinetype limit 15");
			while (rs.next()) {
				Cuisine cuisine = new Cuisine();
				cuisine.setCuisineId(rs.getInt("CuisineTypeID"));
				cuisine.setCuisineName(rs.getString("CuisineName"));
				cuisines.add(cuisine);

			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cuisines;
	}

	public List<RestaurantType> getAllRestaurantTypes() {
		List<RestaurantType> restaurantTypes = new ArrayList<RestaurantType>();
		connection = this.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select RestaurantTypeID,RestaurantTypeName from tbl_restauranttype limit 15");
			while (rs.next()) {
				RestaurantType restaurantType = new RestaurantType();
				restaurantType.setRestaurantTypeId(rs
						.getInt("RestaurantTypeID"));
				restaurantType.setRestaurantTypeName(rs
						.getString("RestaurantTypeName"));
				restaurantTypes.add(restaurantType);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return restaurantTypes;
	}

	private Connection getConnection() {

		try {
			Properties prop = new Properties();
			InputStream inputStream = RestaurantService.class.getClassLoader()
					.getResourceAsStream("/resources/database.properties");

			prop.load(inputStream);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private String encodeBase64String(byte[] param) {
		String encoded = new String(Base64Coder.encode(param));
		return encoded;

	}

}
