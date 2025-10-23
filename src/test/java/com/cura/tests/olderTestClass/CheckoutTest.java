package com.cura.tests.olderTestClass;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cura.pages.CartPage;
import com.cura.pages.CheckoutPage;
import com.cura.pages.OpenCartLoginPage;
import com.cura.pages.ProductPage;
import com.cura.utils.DriverFactory;

public class CheckoutTest {

	WebDriver driver;
	OpenCartLoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;

	@BeforeClass
	public void setUp() {

		driver = DriverFactory.initDriver(); // Get driver instance
		driver.get("https://www.saucedemo.com/");
	}

	// Single Item Checkout Flow
	public void testCompleteCheckoutFlow() {
		System.out.println("=== Starting Checkout Test ===");

		// Step 1: Login
		loginPage = new OpenCartLoginPage(driver);
		loginPage.loginAs("standard_user", "secret_sauce");

		// Step 2: Add Product into Cart
		String productName = "Sauce Labs Backpack";
		productPage = new ProductPage(driver);
		productPage.addProducttoCartByName(productName);

		// Step 3: Check If the Product/item is available in Cart
		cartPage = new CartPage(driver);
		cartPage.goToCart();
		Assert.assertTrue(cartPage.isProductInCart(productName), "Product should be visible in cart.");

		// Step : 4 Proceed to Checkout
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.clickCheckOutButton();
		checkoutPage.enterCheckoutDetails("John", "Doe", "12345");
		checkoutPage.clickContinue();
		checkoutPage.clickFinish();

		// Step 5: Verify checkout complete
		Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Checkout should be completed successfully.");

		System.out.println("=== Checkout Test Passed ===");

		// Step 6 : Logout
		checkoutPage.logout();
		System.out.println("User loggedout successfully");

		// Step 7: Asserttion for back to login page saucedemo site
		Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "User should be redirected to login page");
		System.out.println("After final Assertion ");

	}

	// ✅ Multiple Item Checkout Flow
	@Test
	public void testMultipleItemCompleteCheckoutFlow() {
		System.out.println("=== Starting Checkout Test ===");

		// Step 1: Login
		loginPage = new OpenCartLoginPage(driver);
		loginPage.loginAs("standard_user", "secret_sauce");

		// Step 2: Add multiple products
		List<String> productsToAdd = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light",
				"Sauce Labs Bolt T-Shirt");
		productPage = new ProductPage(driver);
		productPage.addMultipleProducttoCartByName(productsToAdd);

		// Step 3: Go to Cart & verify all products
		cartPage = new CartPage(driver);
		cartPage.goToCart();
		for (String productName : productsToAdd) {
			Assert.assertTrue(cartPage.isProductInCart(productName), "Product should be in cart: " + productName);
		}

		// Step 4: Go to Checkout
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.clickCheckOutButton();
		checkoutPage.enterCheckoutDetails("John", "Doe", "12345");
		checkoutPage.clickContinue();

		// ✅ Step 5: Calculate expected total from checkout summary page
		double expectedTotal = checkoutPage.calculateExpectedItemTotalOnCheckout(productsToAdd);
		double actualTotal = checkoutPage.getItemTotal();

		System.out.println("Expected Item Total: " + expectedTotal);
		System.out.println("Actual Item Total from UI: " + actualTotal);

		Assert.assertEquals(actualTotal, expectedTotal, "Item total mismatch on checkout page");

		// Step 6: Finish checkout
		checkoutPage.clickFinish();
		Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Checkout should be completed.");

		System.out.println("=== Checkout Test Passed ===");

		// Step 7: Logout
		checkoutPage.logout();
		System.out.println("User logged out successfully");

		// Step 8: Verify redirect to login
		Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "User should be redirected to login page");

		System.out.println("After final Assertion");
	}

	@AfterClass
	public void tearDown() {

		DriverFactory.quitDriver();
	}
}
