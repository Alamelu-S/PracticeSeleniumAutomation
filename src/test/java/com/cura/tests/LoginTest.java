//package com.cura.tests;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.cura.pages.LoginPage;
//import com.cura.utils.DriverFactory;
//import com.cura.utils.ReportManager;
//
//public class LoginTest extends BaseTest {
//	WebDriver driver;
//	LoginPage loginPage;
//
//	
//	public void setUp() {
//		driver = DriverFactory.initDriver();
//		driver.get("https://katalon-demo-cura.herokuapp.com/");
//		ReportManager.getTest().info("Browser launched and navigated to CURA demo site");
//	}
//
//	public void testValidLogin() {
//		ReportManager.getTest().info("Clicking on Make Appointment button");
//		driver.findElement(By.id("btn-make-appointment")).click();
//
//		loginPage = new LoginPage(driver);
//		ReportManager.getTest().info("Logging in as John Doe");
//		loginPage.loginAs("John Doe", "ThisIsNotAPassword");
//
//		// Assert that the next page has the title 'Make Appointment'
//		String headerText = driver.findElement(By.xpath("//h2")).getText();
//		ReportManager.getTest().info("Verifying page header: " + headerText);
//		System.out.println("Header ::: " + headerText);
//
//		Assert.assertEquals(headerText, "Make Appointment");
//		ReportManager.getTest().pass("Login test passed successfully!");
//
//	}
//
//	
//	public void tearDown() {
//		DriverFactory.quitDriver();
//		ReportManager.getTest().info("Browser closed");
//
//	}
//
//}
