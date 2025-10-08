package com.cura.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;

	}

	// Dynamic method to add a product to cart by name

	public boolean addProducttoCartByName(String productName) {
		List<WebElement> products = driver
				.findElements(By.xpath("//div[@class='inventory_list']/div[@class='inventory_item']"));

		System.out.println("List Size:" + products.size());

		for (WebElement webElement : products) {
			// ✅ FIX: Get product name inside the product card
			String proName = webElement
					.findElement(By.xpath("//div[@class='inventory_list']/div[@class='inventory_item']//a//div"))
					.getText();
			System.out.println("Name inside addproduct :: " + proName);

			if (proName.equalsIgnoreCase(productName)) {
				WebElement button = webElement.findElement(By.xpath(".//button[contains(text(), 'Add to cart')]"));
				button.click();
				System.out.println("Added to cart :: " + proName);
				return true;
			}

		}

		return false;
	}

	// Add Multiple Product in cart

	public boolean addMultipleProducttoCartByName(List<String> productNames) {
		List<WebElement> products = driver
				.findElements(By.xpath("//div[@class='inventory_list']/div[@class='inventory_item']"));

		System.out.println("List Size in Multiple Add item method:" + products.size());

		int addedCount = 0;

		for (WebElement webElement : products) {
			// ✅ FIX: Get product name inside the product card
			String proName = webElement.findElement(By.className("inventory_item_name")).getText();
			System.out.println("Name inside multi addproduct :: " + proName);

			if (productNames.contains(proName)) {
				System.out.println("Added to cart :: Count::" + addedCount + "Name of item::" + proName);

				WebElement button = webElement.findElement(By.xpath(".//button[contains(text(), 'Add to cart')]"));
				button.click();
				addedCount++;

				// Stop if all products are added
				if (addedCount == productNames.size()) {
					break;

				}

			}

		}
		System.out.println("Added Count ::" + productNames.size());
		return addedCount == productNames.size();
	}

	// CartItem Count validation
	public int getCartItemCount() {
		String cartCountText = driver.findElement(By.className("shopping_cart_badge")).getText();
		System.out.println("get Cart Item Count method ::" + Integer.parseInt(cartCountText));
		return Integer.parseInt(cartCountText);
	}

}

/*
 * Not used this method.
 * 
 * public double calculatedItemTotal(List<String> productNames) {
 * List<WebElement> products = driver .findElements(By.xpath(
 * "//div[@class='inventory_list']/div[@class='inventory_item']"));
 * 
 * System.out.println("List Size in calculatedItemTotal method:" +
 * products.size());
 * 
 * double calculatedTotal = 0.0;
 * 
 * for (WebElement webElement : products) { String proName =
 * webElement.findElement(By.className("inventory_item_name")).getText(); if
 * (productNames.contains(proName)) { String totalPriceText =
 * webElement.findElement(By.className("inventory_item_price")).getText();
 * double totalPrice =
 * Double.parseDouble(totalPriceText.replace("$","").trim()); calculatedTotal+=
 * totalPrice;
 * 
 * System.out.println("calculatedTotal::;" + calculatedTotal); }
 * 
 * }
 * 
 * return calculatedTotal; }
 */
