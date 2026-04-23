package com.cura.api;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIWorkoutExample 
{

	protected static Properties prop = new Properties();
	static int productId;   // 👈 store globally

    @BeforeClass
    public void setUp() 
    {
    	System.out.println("Inside setup");
        try {
            // Use your current absolute path
            FileInputStream fis = new FileInputStream(
                    "C:\\Users\\alame\\eclipse-workspace\\seleniumautomation"
                    + "\\src\\test\\java\\resources\\configFiles\\apiworkoutconfig.properties"
            );

            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set base URI for all tests
        RestAssured.baseURI = prop.getProperty("base.url");
        System.out.println("Base URI set to: " + RestAssured.baseURI);
    }
    
    @Test (priority = 1)
	public void testPostProduct() 
	{
		/*String payload = "{\n" + "  \"title\": \"My Test Product\",\n" + "  \"price\": 99.99,\n"
				+ "  \"description\": \"Automation test product\",\n" + "  \"image\": \"https://i.pravatar.cc\",\n"
				+ "  \"category\": \"electronics\"\n" + "}";*/
		
		File createpayload = new File("C:\\Users\\alame\\eclipse-workspace\\seleniumautomation"
				+ "\\src\\test\\java\\resources\\testData\\create_product.json");
		
		
		Response res = RestAssured
				.given()
				.header("Content-Type", "application/json")
				//.auth().oauth2("ur token here")// for authentication (Bearer token / Basic)
				//.queryParam("category", "electronics") //for URL query parameters
				//.pathParam("id", 123) //for URL path parameters
				.when()
				.body(createpayload)
				.post("/products");
		
		//res.then().log().all();

			productId = res.jsonPath().getInt("id");
			
			System.out.println("Response status in POST: " + res.getStatusCode()
			+"Product Id::"+productId);
		
			Assert.assertEquals(res.getStatusCode(), 201);

	}
    
    public void testGetProduct11() {
		Response response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.when()
				.get("/products/"+productId);
		
		response.then().log().all();  // 👈 log separately  in case if i give next tp .get ,it will give classcastexception
		System.out.println("Response status in GET: " + response.getStatusCode()+
				"Product ID :"+productId);
				
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("price"), "99.99");
		Assert.assertEquals(response.jsonPath().getString("category"), "electronics");

	}
     
    @Test (priority = 2)
    public void testGetProduct() {
		Response response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.pathParam("id", 1)
				.when()
				.get("/products/{id}");
		
		response.then().log().all();  // 👈 log separately  in case if i give next tp .get ,it will give classcastexception
		
		System.out.println("Response status in GET: " + response.getStatusCode());
				
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("price"), "109.95");
		Assert.assertEquals(response.jsonPath().getString("category"), "men's clothing");

	}

	
	@Test (priority = 3)
	public void testPutProduct() {
		/*String payload = "{\n" + "  \"id\": 1,\r\n" + "  \"title\": \"Updated Shirt\",\r\n" + "  \"price\": 999,\r\n"
				+ "  \"description\": \"Updated via API automation\",\r\n" + "  \"category\": \"men's clothing\",\r\n"
				+ "  \"image\": \"https://i.pravatar.cc\"\r\n" + "}";*/

		
		File updatepayload = new File("C:\\Users\\alame\\eclipse-workspace\\seleniumautomation"
				+ "\\src\\test\\java\\resources\\testData\\update_product.json");
		
		Response res = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.pathParam("id", productId)
				.when()
				.body(updatepayload)
				.put("/products/{id}");

		System.out.println("Response status in PUT: " + res.getStatusCode()+
				"Product ID :"+productId);

		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.jsonPath().getString("title"), "Updated Shirt");
	}

	@Test (priority = 4)
	public void testDeleteProduct() {
		Response res = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.pathParam("id", productId)
				.when()
				.delete("/products/{id}");// Can be used multiple params like /users/{userId}/orders/{orderId}
				//.delete("/products/"+productId);

		System.out.println("Response status in Delete: " + res.getStatusCode()+
				"Product ID :"+productId);

		Assert.assertEquals(res.getStatusCode(), 200);

	}
}

/*  Response res = RestAssured
        .given()
        .header("Content-Type", "application/json")
        .header("x-api-key", "<your_api_key_here>")
        .queryParam("category", "electronics")
        .pathParam("id", 123)
        .auth().oauth2("<your_token_here>")
        .body(createPayload)
        .post("/products/{id}"); 
        
        *  🔹 Quick Notes / Interview Points
.auth() → for authentication (Bearer token / Basic)
.header() → for content type, API keys, custom headers
.queryParam() → for URL query parameters
.pathParam() → for URL path parameters
RestAssured replaces {id} with productId internally.
Cleaner if you have multiple path params like /users/{userId}/orders/{orderId}.
.body() → request payload (JSON / string / map)
.post() / .get() / .put() / .delete() → HTTP method
        */
