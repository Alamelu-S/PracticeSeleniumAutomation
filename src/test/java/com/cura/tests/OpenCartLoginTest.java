package com.cura.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cura.pages.OpenCartLoginPage;
import com.cura.utils.DriverFactory;
import com.cura.utils.ReportManager;

//@Listeners(com.cura.listeners.TestListener.class)

public class OpenCartLoginTest extends BaseTest 
{
	WebDriver driver;
	OpenCartLoginPage openCartLogin;

	@BeforeClass
	public void setUp() {
		System.out.println("Setup  :::");
		driver = DriverFactory.initDriver();
		driver.get("https://www.saucedemo.com/");
		ReportManager.initReport();

	}

	@Test
	public void testValidateLogin() 
	{
		System.out.println("Login Test ::: ");
		//ReportManager.createTest("testValidateLogin");
		ReportManager.getTest().info("Browser launched & Starting login test for SauceDemo");
		openCartLogin = new OpenCartLoginPage(driver);
		openCartLogin.loginAs("standard_user", "secret_sauce");

		// Assert that the next page has the text or logo 'Swag Labs'
		String titleText = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
		ReportManager.getTest().info("Page header text: " + titleText);

		System.out.println("Header ::: " + titleText);
		Assert.assertEquals(titleText, "Swag Labs");
		ReportManager.getTest().pass("Login test passed successfully!");
	}

	/*
	 * public void testInvalidLogin() {
	 * ReportManager.createTest("testInvalidLogin"); openCartLogin = new
	 * OpenCartLoginPage(driver);
	 * 
	 * ReportManager.getTest().info("Attempting invalid login");
	 * 
	 * openCartLogin.loginAs("invalid_user", "wrong_password");
	 * 
	 * String actualError = openCartLogin.getErrorMessage();
	 * 
	 * String expectedError =
	 * "Epic sadface: Username and password do not match any user in this service";
	 * 
	 * ReportManager.getTest().info("Error message displayed: " + actualError);
	 * 
	 * // ❌ Intentionally fail this assertion Assert.assertEquals(actualError,
	 * expectedError, "Error message mismatch!"); }
	 */

	@AfterClass
	public void tearDown() 
	{
		DriverFactory.quitDriver();
		ReportManager.getTest().info("Browser closed");
		ReportManager.flushReport();
	}

}
