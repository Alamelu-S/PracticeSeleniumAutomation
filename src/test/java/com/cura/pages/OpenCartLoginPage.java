package com.cura.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenCartLoginPage {

	WebDriver driver;

	private By usernameField = By.id("user-name");
	private By passwordField = By.id("password");
	private By loginBtnClick = By.id("login-button");

	public OpenCartLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) 
	{
		System.out.println("Username ::: "+username);
				
		driver.findElement(By.id("user-name")).sendKeys(username);
		//driver.findElement(usernameField).sendKeys(username);
	}

	public void enterPassword(String password) {

		driver.findElement(By.id("password")).sendKeys(password);
		// driver.findElement(passwordField).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(By.id("login-button")).click();
		// driver.findElement(loginBtnClick).click();
	}

	public void loginAs(String username, String password) 
	{
		System.out.println("Login As ::: "+username+"Pwd::"+password);
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

}
