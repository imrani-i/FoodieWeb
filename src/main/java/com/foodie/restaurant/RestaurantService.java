package com.foodie.restaurant;

import java.sql.Connection;
import java.util.List;


public class RestaurantService {
	RestaurantDAO restaurantDAO = new RestaurantDAO();
	
	public List<RestaurantDTO> selectRestaurantAll() {
		return restaurantDAO.selectRestaurantAll();
		
	}
	public List<RestaurantDTO> selectByAll (String standard){
		return restaurantDAO.selectByAll(standard);
	}

	public List<RestaurantDTO> selectByCategory(String category,String standard){
		return restaurantDAO.selectByCategory(category, standard);
	}
	
	public int restaurantInsert(String id, String name,String detailCategory,String address) {
		return restaurantDAO.restaurantInsert(id, name, detailCategory, address);
	}
	public int restaurantInsert2(Connection conn, String id, String name,String detailCategory,String address) {
		return restaurantDAO.restaurantInsert2(conn, id, name, detailCategory, address);
	}
}
