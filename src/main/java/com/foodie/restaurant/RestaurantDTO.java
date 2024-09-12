package com.foodie.restaurant;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RestaurantDTO {
	
	private String restaurantName;
	private double avgGrade;
	private String category;
	private int post_count;
	private String restaurantId;
	private String restaurantAddress;
	
	
	
}
