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
import com.cura.utils.ReportManager;

public class CartTest extends BaseTest {
	WebDriver driver;
	OpenCartLoginPage cartLoginPage;
	ProductPage productPage;
	CartPage cartPage;

	@BeforeClass
	public void setUp() {
		System.out.println("Inside Setup  :::");
		driver = DriverFactory.initDriver();
		driver.get("https://www.saucedemo.com/");

	}

	@Test
	public void goToCartTest() {
		System.out.println("Inside goToCart Test method  :::");

		ReportManager.getTest().info("Logging with standard-user");

		// Step 1: Login
		cartLoginPage = new OpenCartLoginPage(driver);
		cartLoginPage.loginAs("standard_user", "secret_sauce");

		// Step 2: Add Product into Cart
		String productName = "Sauce Labs Backpack";
		productPage = new ProductPage(driver);
		ReportManager.getTest().info("Adding product: " + productName);
		productPage.addProducttoCartByName(productName);

		// Step 3: Check If the Product/item is available in Cart
		cartPage = new CartPage(driver);
		cartPage.goToCart();
		ReportManager.getTest().info("Navigated to Cart");
		boolean isAvailableInCart = cartPage.isProductInCart(productName);
		ReportManager.getTest().info("Checking if product is in cart: " + productName);

		// Step 4: Assertion
		Assert.assertTrue(isAvailableInCart, "Product '" + productName + "' was not found in the cart!");
		ReportManager.getTest().pass("Cart test passed: Product found in cart");
	}

	@AfterClass
	public void tearDown() {

		DriverFactory.quitDriver();
		ReportManager.getTest().info("Browser closed after cart test");

	}

}
