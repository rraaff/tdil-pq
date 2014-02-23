package com.tdil.lojack.rest.test;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

public class TestPeugeot {

	public static void main(String[] args) {
		// login
		Response response = expect().log().body().body("homeUser", equalTo(true)).when().get("http://localhost:8180/PeugeotServiceWeb/rest/users/login?documentType=1&documentNumber=12&password=2");
//		String jsessionId = response.getBody().
		String apkToken = response.path("apkToken");
//		String cookie = "JSESSIONID=" + jsessionId;

		// vehicles
		response = given().header(new Header("apkToken", apkToken)).expect().log().body().body("list.description", hasItems("CZP075")).when().get("http://localhost:8180/PeugeotServiceWeb/rest/prevent/vehicles");
		String idVehicle = response.path("list[0].id");
		response = given().header(new Header("apkToken", apkToken)).expect().log().body().body("list.description", hasItems("Casa Materna ACTIVA")).when().get("http://localhost:8180/PeugeotServiceWeb/rest/prevent/" + idVehicle + "/secureZones");
		response = given().header(new Header("apkToken", apkToken)).expect().log().body().body("list.description", hasItems("100 Km/h ACTIVA")).when().get("http://localhost:8180/PeugeotServiceWeb/rest/prevent/" + idVehicle + "/speedLimits");
		response = given().header(new Header("apkToken", apkToken)).expect().log().body().body("alert", equalTo("54-11558710405")).when().get("http://localhost:8180/PeugeotServiceWeb/rest/prevent/" + idVehicle + "/phoneNumbers");
		
		Map<String, Object> secZone = new HashMap<String, Object>();
		secZone.put("id", "1");
		secZone.put("description", "1");
		secZone.put("status", "true");
		Gson gson = new Gson(); 
		String json = gson.toJson(secZone); 
		response = given().header(new Header("apkToken", apkToken)).body(json).expect().log().body().body("ok", equalTo(true)).when().post("http://localhost:8180/PeugeotServiceWeb/rest/prevent/" + idVehicle + "/secureZone");
		
		Map<String, Object> speedLimit = new HashMap<String, Object>();
		speedLimit.put("id", "1");
		speedLimit.put("description", "1");
		gson = new Gson(); 
		json = gson.toJson(speedLimit); 
		response = given().header(new Header("apkToken", apkToken)).body(json).expect().log().body().body("ok", equalTo(true)).when().post("http://localhost:8180/PeugeotServiceWeb/rest/prevent/" + idVehicle + "/speedLimit");

		Map<String, Object> updatePhone = new HashMap<String, Object>();
		updatePhone.put("userToken", apkToken);
		updatePhone.put("vehicleID", "1");
		updatePhone.put("alert", "1");
		updatePhone.put("crash", "1");
		updatePhone.put("other", "1");
		
		gson = new Gson(); 
		json = gson.toJson(updatePhone); 
		response = given().header(new Header("apkToken", apkToken)).body(json).expect().log().body().body("ok", equalTo(true)).when().post("http://localhost:8180/PeugeotServiceWeb/rest/prevent/" + idVehicle + "/phoneNumber");


		// logout
		given().header(new Header("apkToken", apkToken)).expect().log().body().statusCode(201).when().get("http://localhost:8180/PeugeotServiceWeb/rest/users/logout");
		
		// unauthorized
		given().header(new Header("apkToken", apkToken)).expect().log().body().statusCode(401).when().get("http://localhost:8180/PeugeotServiceWeb/rest/prevent/vehicles");
	}
}
