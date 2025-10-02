package com.cura.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cura.pages.LoginPage;
import com.cura.utils.DriverFactory;

public class LoginTest 
{
	WebDriver driver;
	LoginPage loginPage;
	
	@BeforeClass
	public void setUp() {
        driver = DriverFactory.initDriver();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
    }
	@Test
    public void testValidLogin() 
	{
        driver.findElement(By.id("btn-make-appointment")).click();
        loginPage = new LoginPage(driver);
        loginPage.loginAs("John Doe", "ThisIsNotAPassword");

        // Assert that the next page has the title 'Make Appointment'
        String headerText = driver.findElement(By.xpath("//h2")).getText();
        System.out.println("Header ::: "+headerText);
        Assert.assertEquals(headerText, "Make Appointment");
    }

	@AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }


	

}
