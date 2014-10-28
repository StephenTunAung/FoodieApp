package com.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import biz.source_code.base64Coder.Base64Coder;

import com.webservice.domain.Restaurant;
import com.webservice.domain.RestaurantDetail;
import com.webservice.utility.DBUtility;

public class RestaurantService {

	private Connection connection;

	public RestaurantService() {
		connection = DBUtility.getConnection();
	}

	public int getRestaurantCount() {
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
		}
		return count;
	}

	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("select RestaurantID, RestaurantName, Address, Thumbnail from tbl_restaurant limit 15");
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantID(rs.getInt("RestaurantID"));
				restaurant.setRestaurantName(rs.getString("RestaurantName"));
				restaurant.setAddress(rs.getString("Address"));
				restaurant.setImage(encodeBase64String(rs.getBytes("Thumbnail")));
				restaurants.add(restaurant);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurants;
	}

	public RestaurantDetail getRestaurantById(int restaurantID) {
		RestaurantDetail restaurantDetail = new RestaurantDetail();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select restaurantID, restaurantname, Image from tbl_restaurant where restaurantID=?");
			preparedStatement.setInt(1, restaurantID);
			ResultSet rs = preparedStatement.executeQuery();
			
			restaurantDetail.setCuisineList(getCuisineTypeList(restaurantID));

			while (rs.next()) {
				restaurantDetail.setRestaurantID(rs.getInt("restaurantID"));
				restaurantDetail.setRestaurantName(rs
						.getString("restaurantname"));

				String encoded = encodeBase64String(rs.getBytes("image"));
				restaurantDetail.setImage(encoded);
//				String original = new String(rs.getBytes("image"));
//				System.out.println("Original : " + original);
//				System.out.println("-----****-------");
//				System.out.println("Encoded : " + encoded + "\n ------ \n");
//
//				System.out.println("-----****-------");
//
//				byte[] raw = Base64Coder.decode(encoded);
//
//				String rawString = new String(raw);
//
//				System.out.println(rawString);
//
//				System.out.println("Is original and decoded equal ?="
//						+ original.equals(rawString));

			}

			

			System.out.println("Restaurant ID" + restaurantID + ":::"
					+ restaurantDetail);

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantDetail;
	}

	private String getCuisineTypeList(int restaurantID) {
		// Get Cuisine information of Restaurant
		StringBuffer cuisineList = new StringBuffer();
		try {
			PreparedStatement psCuisine = connection
					.prepareStatement("select CuisineTypeID from tbl_restaurantcuisine where restaurantID=? order by CuisineTypeID");
			psCuisine.setInt(1, restaurantID);
			ResultSet rsCuisine = psCuisine.executeQuery();		

			while (rsCuisine.next()) {

				PreparedStatement psCuisineType = connection
						.prepareStatement("select CuisineName from tbl_cuisinetype where CuisineTypeID=?");
				
				psCuisineType.setInt(1, rsCuisine.getInt("CuisineTypeID"));

				ResultSet rsCuisineType = psCuisineType.executeQuery();

				while (rsCuisineType.next()) {


					cuisineList.append(rsCuisineType
							.getString("CuisineName"));
					cuisineList.append(",");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cuisineList.deleteCharAt(cuisineList.length()-1);
		return cuisineList.toString();

	}

	private String encodeBase64String(byte[] param) {
		String encoded = new String(Base64Coder.encode(param));
		return encoded;

	}

}
