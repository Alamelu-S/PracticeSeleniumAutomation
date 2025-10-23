package com.cura.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cura.pages.CartPage;
import com.cura.pages.CheckoutPage;
import com.cura.pages.OpenCartLoginPage;
import com.cura.pages.ProductPage;
import com.cura.utils.DriverFactory;
import com.cura.utils.ExcelReader;

public class CheckoutDDTest {

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

	@DataProvider(name = "checkoutData")
	public Object[][] getCheckoutData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/test/java/resources/testData/CheckoutTestData.xlsx";

		// "C:\Users\alame\eclipse-workspace\seleniumautomation\src\test\java\resources\testData\CheckoutTestData.xlsx"
		return ExcelReader.getData(filePath, "Sheet1");
	}

	@Test(dataProvider = "checkoutData")
	public void testCheckoutFlow(String firstName, String lastName, String postalCode, String productNames) 
	{
		System.out.println("=== Starting Checkout Test for " + firstName + " " + lastName + " ===");

		// Step 1: Login
		loginPage = new OpenCartLoginPage(driver);
		loginPage.loginAs("standard_user", "secret_sauce");

		// Step 2: Add products (comma-separated from Excel)
		List<String> productsToAdd = Arrays.asList(productNames.split(","));
		productPage = new ProductPage(driver);
		productPage.addMultipleProducttoCartByName(productsToAdd);

		// Step 3: Verify products in cart
		cartPage = new CartPage(driver);
		cartPage.goToCart();
		for (String productName : productsToAdd) {
			Assert.assertTrue(cartPage.isProductInCart(productName), "Product should be in cart: " + productName);
		}

		// Step 4: Checkout
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.clickCheckOutButton();
		checkoutPage.enterCheckoutDetails(firstName, lastName, postalCode);
		checkoutPage.clickContinue();

		// Step 5: Verify totals
		double expectedTotal = checkoutPage.calculateExpectedItemTotalOnCheckout(productsToAdd);
		double actualTotal = checkoutPage.getItemTotal();
		Assert.assertEquals(actualTotal, expectedTotal, "Item total mismatch on checkout page");

		// Step 6: Finish checkout
		checkoutPage.clickFinish();
		Assert.assertTrue(checkoutPage.isCheckoutComplete(), "Checkout should be completed successfully.");

		System.out.println("=== Checkout Completed for " + firstName + " " + lastName + " ===");

		// Step 7: Logout
		checkoutPage.logout();
		Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"), "User should be redirected to login page");
	}

	@AfterClass
	public void tearDown() {

		DriverFactory.quitDriver();
	}
}
