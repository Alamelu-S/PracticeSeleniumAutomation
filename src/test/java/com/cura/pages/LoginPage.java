package com.cura.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage 
{
	WebDriver driver;
	
	private By usernameField = By.id("txt-username");
	private By passwordField = By.id("txt-password");
	private By loginBtnClick = By.id("btn-login");
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void enterUsername(String username) 
	{
		driver.findElement(By.id("txt-username")).sendKeys(username);
        //driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        
    	driver.findElement(By.id("txt-password")).sendKeys(password);
    	//driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() 
    {
        driver.findElement(By.id("btn-login")).click();
    	//driver.findElement(loginBtnClick).click();
    }

	
    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
			
	

}
