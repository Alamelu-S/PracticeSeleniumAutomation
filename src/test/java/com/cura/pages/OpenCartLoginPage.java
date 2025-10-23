package com.cura.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenCartLoginPage {

	WebDriver driver;

	private By usernameField = By.id("user-name");
	private By passwordField = By.id("password");
	private By loginBtnClick = By.id("login-button");
	// locator for SauceDemo error box
	//private By errorMessage = By.cssSelector("h3[data-test='error']"); 
	private By errorMessageContainer = By.cssSelector(".error-message-container");

	

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
	
	public String getErrorMessage()
	{
		System.out.println("Inside get error msg");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageContainer));
        String errorText = driver.findElement(errorMessageContainer).getText();
        System.out.println("Error Text  get error method::"+errorText);
        return errorText;
    }

	// 🧩 NEW: Method to get error message when login fails
	/*	public String getErrorMessage() {
			try {
				return driver.findElement(errorMessage).getText();
			} catch (Exception e) {
				return "No error message displayed";
			}
		}*/


}
