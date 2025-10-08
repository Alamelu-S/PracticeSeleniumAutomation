package com.cura.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cura.pages.CartPage;
import com.cura.pages.OpenCartLoginPage;
import com.cura.pages.ProductPage;
import com.cura.utils.DriverFactory;

public class CartTest {
	WebDriver driver;
	OpenCartLoginPage cartLoginPage;
	ProductPage productPage;
	CartPage cartPage;

	@BeforeClass
	public void setUp() 
	{
		System.out.println("Inside Setup  :::");
		driver = DriverFactory.initDriver();
		driver.get("https://www.saucedemo.com/");

	}

	@Test
	public void goToCartTest() {
		System.out.println("Inside goToCart Test method  :::");

		// Step 1: Login
		cartLoginPage = new OpenCartLoginPage(driver);
		cartLoginPage.loginAs("standard_user", "secret_sauce");

		// Step 2: Add Product into Cart
		String productName = "Sauce Labs Backpack";
		productPage = new ProductPage(driver);
		productPage.addProducttoCartByName(productName);

		// Step 3: Check If the Product/item is available in Cart
		cartPage = new CartPage(driver);
		cartPage.goToCart();
		boolean isAvailableInCart = cartPage.isProductInCart(productName);

		// Step 4: Assertion
		Assert.assertTrue(isAvailableInCart, "Product '" + productName +"' was not found in the cart!");

	}

	@AfterClass
	public void tearDown() {

		DriverFactory.quitDriver();

	}

}
