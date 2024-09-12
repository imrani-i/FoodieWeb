package com.foodie.restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import util.DateUtil;

public class RestaurantDAO {

	PreparedStatement pst; // Statement 를 상속 받음, (?)바인딩 변수 지원
	Connection conn; // DB연결
	Statement st; // sql 코드 전송
	ResultSet rs; // 결과 반환

	public int restaurantInsert(String id, String name,String detailCategory,String address) {
		int result = 0;
		String sql = "insert into restaurant (RESTAURANT_ID, RESTAURANT_NAME, MENU_CATEGORY, RESTAURANT_ADDRESS) VALUES (?, ?, ?, ?)";
		conn = DBUtil.dbConnection();
		
		try {
		
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, name);
			pst.setString(3, detailCategory);
			pst.setString(4, address);

			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return result;	
		
	}

	public int restaurantInsert2(Connection conn, String id, String name, String detailCategory, String address) {
		int result = 0;
		String sql = "insert into restaurant (RESTAURANT_ID, RESTAURANT_NAME, MENU_CATEGORY, RESTAURANT_ADDRESS) VALUES (?, ?, ?, ?)";
		// conn = DBUtil.dbConnection();

		try {

			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, name);
			pst.setString(3, detailCategory);
			pst.setString(4, address);

			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// PreparedStatement를 닫습니다.
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	// 특정 카테고리에 따른 조회
	public List<RestaurantDTO> selectByCategory(String category, String standard) {

		List<RestaurantDTO> restaurantList = new ArrayList<RestaurantDTO>();
		String sql = "select *" + " from restaurant" + " where menu_category = ? and avg_grade is not null"
				+ " order by " + standard + " desc";

		conn = DBUtil.dbConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, category);
			rs = pst.executeQuery();
			while (rs.next()) {
				RestaurantDTO restaurant = makeRestaurant(rs);
				restaurantList.add(restaurant);

			}

		} catch (SQLException e) {
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}

		return restaurantList;

	}

	// 전체 카테고리에서 조회

	public List<RestaurantDTO> selectByAll(String standard) {

		List<RestaurantDTO> restaurantList = new ArrayList<RestaurantDTO>();

		String sql = "select menu_category, restaurant_name, avg_grade, post_count" + " from restaurant"
				+ " where avg_grade is not null" + " order by " + standard + " desc";

		conn = DBUtil.dbConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				RestaurantDTO restaurant = makeRestaurant(rs);
				restaurantList.add(restaurant);
			}

		} catch (SQLException e) {
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return restaurantList;

	}

	// 모든 조회하기

	public List<RestaurantDTO> selectRestaurantAll() {
		
		List<RestaurantDTO> restaurantList = new ArrayList<RestaurantDTO>();

		String sql = "select RESTAURANT_NAME, MENU_CATEGORY, RESTAURANT_ID, RESTAURANT_ADDRESS from restaurant";

		conn = DBUtil.dbConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				RestaurantDTO restaurant = makeRestaurant(rs);
				restaurantList.add(restaurant);
			}

		} catch (SQLException e) {
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return restaurantList;
		

	}

	private RestaurantDTO makeRestaurant(ResultSet rs) throws SQLException {

		RestaurantDTO restaurant = new RestaurantDTO();

		restaurant.setRestaurantName(rs.getString("restaurant_name"));
		restaurant.setCategory(rs.getString("menu_category"));
		restaurant.setAvgGrade(rs.getDouble("avg_grade"));
		restaurant.setPost_count(rs.getInt("post_count"));

		return restaurant;
	}

}
