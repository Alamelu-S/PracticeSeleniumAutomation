package com.cura.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReqreAPITest {

	@Test
	public void testCreateUser() 
	{
		// Prepare the payload
		String payload = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

		// Send the POST request
		Response response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("x-api-key","reqres-free-v1")
				.body(payload)
				.when()
				.post("https://reqres.in/api/users");

		// Print the response
		System.out.println("Response in POST: " + response.asString());

		// Validate the response
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertNotNull(response.jsonPath().getString("id"));
		Assert.assertNotNull(response.jsonPath().getString("createdAt"));
	}
	
	@Test
	public void testGetUser()
	{
		//Prepare Get request
		Response response = RestAssured
		.given()
		.header("x-api-key","reqres-free-v1")
		.when()
		.get("https://reqres.in/api/users/2");
		
		System.out.println("Response status in GET::"+response.getStatusCode());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		//get id and name
		//response.jsonPath()
		Assert.assertEquals(response.jsonPath().getInt("data.id"), 2);
	    Assert.assertEquals(response.jsonPath().getString("data.first_name"), "Janet");
		
	}
}
