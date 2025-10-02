package com.cura.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cura.pages.AppointmentPage;
import com.cura.pages.LoginPage;
import com.cura.utils.DriverFactory;

public class AppointmentTest {
	WebDriver driver;
	LoginPage loginPage;
	AppointmentPage appointmentPage;

	@BeforeClass
	public void setUp() {
		driver = DriverFactory.initDriver();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
	}

	@Test
	public void testBookAppointment() {
		// Login
		driver.findElement(By.id("btn-make-appointment")).click();
		loginPage = new LoginPage(driver);
		loginPage.loginAs("John Doe", "ThisIsNotAPassword");

		System.out.println("Current URL After Login :" + driver.getCurrentUrl());

		// Wait for appointment page to load
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("combo_facility")));

		// Book appointment
		appointmentPage = new AppointmentPage(driver);
		System.out.println("Before Click to book appointment");
		appointmentPage.bookAppointment("Tokyo CURA Healthcare Center", "25/09/2025",
				"My First Booking");
		
		System.out.println(">>> CURRENT URL: " + driver.getCurrentUrl());
		System.out.println(">>> CURRENT H2 TEXT: " + driver.findElement(By.tagName("h2")).getText());


		// Wait for confirmation page
		// wait.until(ExpectedConditions.urlContains("#summary"));-- This gives Timesout
		// Exception.So usedbelow line
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
		wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//h2[normalize-space()='Appointment Confirmation']")));

		System.out.println("Current URL Summary :" + driver.getCurrentUrl());

		// Assert confirmation text
		String confirmation = appointmentPage.getConfirmationHeader();
		System.out.println("Confirmation Header Text: " + confirmation);
		Assert.assertEquals(confirmation, "Appointment Confirmation"); 
				
		// Logout & Assertion
			
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
		//System.out.println("Current URL Summary after booking and before logout :" 
		//+ driver.getCurrentUrl());
		
		//appointmentPage.logout();
		//System.out.println("Logout Success and current url"+driver.getCurrentUrl());
		//Assert.assertEquals(false, null)
		
		//Assert.assertTrue(driver.getCurrentUrl().contains("profile.php#login"));
		//wait.until(ExpectedConditions.urlContains("profile.php#login"));
        //Assert.assertTrue(driver.getCurrentUrl().contains("profile.php#login"), "Logout failed.");


	}

	@AfterClass
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
