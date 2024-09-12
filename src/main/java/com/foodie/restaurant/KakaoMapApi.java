package com.foodie.restaurant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.DBUtil;

public class KakaoMapApi {

	public static void main(String[] args) {

		KakaoMapApi.test();

	}

	public static void test() {
		String apiKey = "f93c1ca6a6943cbac37dcc5a4b9a2f2a";
		String query = "마포 맛집";
		RestaurantService rService = new RestaurantService();

		try {
			query = URLEncoder.encode(query, "UTF-8");

			for (int i = 1; i < 4; i++) {

				String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + query;
				URL url = new URL(apiUrl);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Authorization", "KakaoAK " + apiKey);
				int responseCode = con.getResponseCode();

				BufferedReader br;
				if (responseCode == 200) { // 정상 호출
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else { // 에러 발생
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}

				StringBuilder response = new StringBuilder();

				String inputLine;
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();

				JSONObject jsonResponse = new JSONObject(response.toString());
				JSONArray documents = jsonResponse.getJSONArray("documents");
				

				// 결과 출력
				
				 for (int j = 0; j < documents.length(); j++) { System.out.println(j);
				 JSONObject place = documents.getJSONObject(j); String name =
				 place.getString("place_name"); String category =
				 place.getString("category_name"); String detailCategory =
				 extractCategory(category); String id = place.getString("id");
				  
				 String address = place.getString("road_address_name"); System.out.println(j);
				 rService.restaurantInsert(id, name, detailCategory,address);
				 
				 }
				

			}

		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}

	}

	public static String extractCategory(String json) {
		// JSON 문자열에서 "category_name" 값만 추출
		int firstIndex = json.indexOf(">");
		int secondIndex = json.indexOf(">", firstIndex + 1);

		if (firstIndex != -1 && secondIndex != -1) {
			// 첫 번째 ">" 이후의 문자열 추출
			String category = json.substring(firstIndex + 1, secondIndex).trim();

			// 추출된 문자열에 공백이 포함되지 않도록 처리
			category = category.replaceAll("\\s", ""); // 공백 제거

			return category;
		} else if (firstIndex != -1) {
			// ">"가 하나만 있는 경우 그 이후의 문자열 추출
			String category = json.substring(firstIndex + 1).trim();

			// 추출된 문자열에 공백이 포함되지 않도록 처리
			category = category.replaceAll("\\s", ""); // 공백 제거

			return category;
		} else {
			return "카테고리를 찾을 수 없습니다.";
		}
	}

}
