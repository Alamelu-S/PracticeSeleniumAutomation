package com.cura.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReqAPI {

	@Test
	public void testSuccessfulLogin() {
		String payload = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

		Response response = RestAssured
				.given().header("Content-Type", "application/json").body(payload)
				.when()
				.post("https://reqres.in/api/login");

		System.out.println("Response: " + response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertNotNull(response.jsonPath().getString("token"));
	}

	@Test
	public void testFailedLogin() {
		String payload = "{ \"email\": \"peter@klaven\" }"; // Missing password

		Response response = RestAssured.given().header("Content-Type", "application/json").body(payload).when()
				.post("https://reqres.in/api/login");

		System.out.println("Response: " + response.asString());
		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.jsonPath().getString("error"), "Missing password");
	}
}
