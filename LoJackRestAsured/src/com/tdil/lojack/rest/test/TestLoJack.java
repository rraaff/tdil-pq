package com.tdil.lojack.rest.test;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import com.jayway.restassured.response.Response;

public class TestLoJack {

	public static void main(String[] args) {
		// login
		Response response = expect().body("ok", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/users/login?documentType=1&documentNumber=12&password=2");
		String jsessionId = response.getCookie("JSESSIONID");
		// list
		given().cookie("JSESSIONID=" + jsessionId).expect().body("alarms.idEntidad", hasItems(1, 2)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/list");
		
		// Rename
		String newName = String.valueOf(System.nanoTime());
		given().cookie("JSESSIONID=" + jsessionId).param("description", newName).expect().body("ok", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/{idEntidad}/rename",1);
		given().cookie("JSESSIONID=" + jsessionId).expect().body("alarms.description", hasItems(newName)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/list");
		
		// send panic
		given().cookie("JSESSIONID=" + jsessionId).expect().body("accepted", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/{idEntidad}/sendPanic",1);
		// activate
		given().cookie("JSESSIONID=" + jsessionId).expect().body("accepted", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/{idEntidad}/activate",1);
		// deactivate
		given().cookie("JSESSIONID=" + jsessionId).expect().body("accepted", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/{idEntidad}/deactivate",1);
		
		// logout
		given().cookie("JSESSIONID=" + jsessionId).expect().statusCode(201).when().get("http://localhost:8180/LoJackWeb/rest/users/logout");
		
		// unauthorized
		given().cookie("JSESSIONID=" + jsessionId).expect().statusCode(401).when().get("http://localhost:8180/LoJackWeb/rest/alarms/list");
	}
}
