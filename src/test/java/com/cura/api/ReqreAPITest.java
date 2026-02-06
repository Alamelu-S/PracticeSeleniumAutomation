package com.cura.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReqreAPITest extends BaseAPI {

    @Test
    public void testCreateUser() {
        String payload = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("x-api-key", getApiKey())   // API key from config
                .body(payload)
                .when()
                .post("/users");                   // endpoint relative to base URI

        System.out.println("Response in POST: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertNotNull(response.jsonPath().getString("createdAt"));
    }

    @Test
    public void testGetUser() {
        Response response = RestAssured
                .given()
                .header("x-api-key", getApiKey())   // API key from config
                .when()
                .get("/users/2");

        System.out.println("Response status in GET: " + response.getStatusCode());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("data.id"), 2);
        Assert.assertEquals(response.jsonPath().getString("data.first_name"), "Janet");
    }
}
