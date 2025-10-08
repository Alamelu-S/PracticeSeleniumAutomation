package com.cura.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver initDriver() 
    {
        WebDriverManager.chromedriver().setup();

        // Set ChromeOptions to disable password manager and related popups
        ChromeOptions options = new ChromeOptions();

        System.out.println("Before disabled");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("disable-features=CredentialManagementAPI,PasswordLeakDetection,PasswordSavingUI");

        // Disable Chrome's built-in password manager using preferences
        Map<String, Object> prefs = new HashMap<>();
        System.out.println("Before Map Function ");
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("profile.credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Optional: remove "Chrome is being controlled..." infobar
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}





/*package com.cura.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver initDriver() 
    {
    	System.out.println("Inside Driver Factory init method");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Disable Chrome's save password bubble
        System.out.println("Before disable Inside Driver Factory init method");
        options.addArguments("--disable-save-password-bubble");
        System.out.println("After disable Inside Driver Factory init method");

        // Disable password leak detection popup
        options.addArguments("disable-features=PasswordLeakDetection");

        // Optional: Remove the "Chrome is being controlled by automated test software" info bar
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation", "password-manager"});

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
*/
/*
package com.cura.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory 
{
	private static WebDriver driver;

	public static WebDriver initDriver() 
	{
		System.out.println("Inside init driver");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}*/
