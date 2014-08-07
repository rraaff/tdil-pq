package com.tdil.lojack.rest.test;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import com.jayway.restassured.response.Response;

public class TestLoJack {

	public static void main(String[] args) {
		// login
		Response response = expect().log().body().body("homeUser", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/users/login?documentType=1&documentNumber=12&password=2");
		String jsessionId = response.getCookie("JSESSIONID");
		String cookie = "JSESSIONID=" + jsessionId;

		// ALARMS
		// list
		given().cookie(cookie).expect().log().body().body("alarms.idEntidad", hasItems(1, 2)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/list");
		// Rename
		String newName = String.valueOf(System.nanoTime());
		given().cookie(cookie).param("description", newName).expect().log().body().body("ok", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/{idEntidad}/rename",1);
		given().cookie(cookie).expect().log().body().body("alarms.description", hasItems(newName)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/list");
		// get log
		given().cookie(cookie).expect().log().body().body("logs.action", hasItems("ACTIVAR")).when().get("http://localhost:8180/LoJackWeb/rest/alarms/{idEntidad}/log",1);
		// send panic
		given().cookie(cookie).expect().log().body().body("accepted", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/{idEntidad}/sendPanic",1);
		// activate
		given().cookie(cookie).expect().log().body().body("accepted", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/{idEntidad}/activate",1);
		// deactivate
		given().cookie(cookie).expect().log().body().body("accepted", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/alarms/{idEntidad}/deactivate",1);
		
		// LIGHTS
		// list
		given().cookie(cookie).expect().log().body().body("lights.idLuz", hasItems(1, 2)).when().get("http://localhost:8180/LoJackWeb/rest/lights/list");
		// Rename
		given().cookie(cookie).param("description", newName).expect().log().body().body("ok", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/lights/{idEntidad}/{idLuz}/rename",70843, 1);
		given().cookie(cookie).expect().log().body().body("lights.description", hasItems(newName)).when().get("http://localhost:8180/LoJackWeb/rest/lights/list");
		// get log
		given().cookie(cookie).expect().log().body().body("logs.action", hasItems("ACTIVAR")).when().get("http://localhost:8180/LoJackWeb/rest/lights/{idEntidad}/{idLuz}/log",70843,1);
		
		// activate
		given().cookie(cookie).expect().log().body().body("accepted", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/lights/{idEntidad}/{idLuz}/activate",70843,1);
		// deactivate
		given().cookie(cookie).expect().log().body().body("accepted", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/lights/{idEntidad}/{idLuz}/deactivate",70843,1);
		// randomOn
		given().cookie(cookie).expect().log().body().body("accepted", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/lights/{idEntidad}/{idLuz}/randomOn",70843,1);
		// randomOff
		given().cookie(cookie).expect().log().body().body("accepted", equalTo(true)).when().get("http://localhost:8180/LoJackWeb/rest/lights/{idEntidad}/{idLuz}/randomOff",70843,1);
		
		// logout
		given().cookie(cookie).expect().log().body().statusCode(201).when().get("http://localhost:8180/LoJackWeb/rest/users/logout");
		
		// unauthorized
		given().cookie(cookie).expect().log().body().statusCode(401).when().get("http://localhost:8180/LoJackWeb/rest/alarms/list");
	}
}