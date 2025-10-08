package com.cura.tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cura.pages.OpenCartLoginPage;
import com.cura.pages.ProductPage;
import com.cura.utils.DriverFactory;

public class AddtoCartProductTest {

	WebDriver driver;
	OpenCartLoginPage openCartLoginPage;
	ProductPage productPage;

	@BeforeClass
	public void setUp() {
		// chrome initilize
		driver = DriverFactory.initDriver();

		// open site
		driver.get("https://www.saucedemo.com/");

		// Initilize page class
		openCartLoginPage = new OpenCartLoginPage(driver);
		productPage = new ProductPage(driver);

	}

	
	public void testAddtoCart() {
		// Step : 1 :login
		openCartLoginPage.loginAs("standard_user", "secret_sauce");

		// Step 2: Add product to cart
		String productName = "Sauce Labs Backpack";
		boolean isAdded = productPage.addProducttoCartByName(productName);

		// Step 3: Assert it was added successfully
		System.out.println("Before Assertion");
		Assert.assertTrue(isAdded, "The product '" + productName + "' should be added to the cart.");
		System.out.println("After Assertion");
	}

	@Test
	// Check multiple product added into cart
	public void testMultipleProductAddtoCart() {

		// Step : 1 :login
		openCartLoginPage.loginAs("standard_user", "secret_sauce");

		// Step 2: Add product to cart
		List<String> productsToAdd = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light",
				"Sauce Labs Bolt T-Shirt");

		boolean isAddedAll = productPage.addMultipleProducttoCartByName(productsToAdd);

		// Step 3: Assert it was added successfully
		System.out.println("Before Assertion");
		Assert.assertTrue(isAddedAll, "Not all products were added successfully.");
		System.out.println("After Assertion");
		
		//Step: 4: Item Count Checck
		int expectedCount = productsToAdd.size();
		int actualCount = productPage.getCartItemCount();
		System.out.println("Exp Count :"+expectedCount);
		System.out.println("Actual Count :"+actualCount);
		Assert.assertEquals(actualCount, expectedCount, "Cart item count mismatch.");

	}

	@AfterClass
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
