package com.cura.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cura.pages.OpenCartLoginPage;
import com.cura.utils.DriverFactory;

public class OpenCartLoginTest 
{
	WebDriver driver;
	OpenCartLoginPage openCartLogin;
	
	@BeforeClass
	public void setUp()
	{
		System.out.println("Setup  :::");
		driver= DriverFactory.initDriver();
		driver.get("https://www.saucedemo.com/");
		//driver.get("https://demo.opencart.com/TlbeVW/");
		//driver.get("https://demo.opencart.com/TlbeVW/index.php?route=common/login");
	}
	
	@Test
	public void testValidateLogin()
	{
		System.out.println("Login Test ::: ");
		openCartLogin = new OpenCartLoginPage(driver);
		openCartLogin.loginAs("standard_user","secret_sauce");
		
		// Assert that the next page has the text or logo 'Swag Labs'
        String titleText = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        System.out.println("Header ::: "+titleText);
        Assert.assertEquals(titleText, "Swag Labs");
	}
	
	@AfterClass
	public void tearDown() {
        DriverFactory.quitDriver();
    }

}
