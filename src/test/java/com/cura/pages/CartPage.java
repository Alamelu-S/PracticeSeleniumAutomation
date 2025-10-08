package com.cura.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;

	}

	public void goToCart() {
		System.out.println("Inside goto cart method");
		driver.findElement(By.className("shopping_cart_link")).click();
	}

	public boolean isProductInCart(String productName) {
	    System.out.println("Inside Product In Cart method: " + productName);

	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        String xpath = "//div[@class='cart_item']//div[@class='inventory_item_name' and text()='" + productName + "']";

	        return wait
	            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)))
	            .isDisplayed();
	        
	    } catch (Exception e) {
	        System.out.println("Product not found in cart: " + productName);
	        return false;
	    }
	}


}
