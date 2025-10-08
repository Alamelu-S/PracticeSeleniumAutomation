package com.cura.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickCheckOutButton() {
		driver.findElement(By.id("checkout")).click();
	}

	public void enterCheckoutDetails(String firstName, String lastName, String zipCode) {
		driver.findElement(By.id("first-name")).sendKeys(firstName);
		driver.findElement(By.id("last-name")).sendKeys(lastName);
		driver.findElement(By.id("postal-code")).sendKeys(zipCode);

	}

	public void clickContinue() {
		driver.findElement(By.id("continue")).click();
	}

	public void clickFinish() {
		driver.findElement(By.id("finish")).click();
	}

	public boolean isCheckoutComplete() {
		String text = driver.findElement(By.className("complete-header")).getText();
		System.out.println("Final msg ::" + text);
		return text.contains("Thank you");
	}

	public double getItemTotal() {
		String itemTotalPrice = driver.findElement(By.className("summary_subtotal_label")).getText();
		double totalPrice = Double.parseDouble(itemTotalPrice.replace("Item total: $", "").trim());
		System.out.println("Item Total in method:: " + totalPrice);
		return totalPrice;

	}

	public double calculateExpectedItemTotalOnCheckout(List<String> expectedProductNames) {
		List<WebElement> itemRows = driver.findElements(By.className("cart_item"));
		double total = 0.0;

		for (WebElement row : itemRows) {
			String name = row.findElement(By.className("inventory_item_name")).getText().trim();
			String priceText = row.findElement(By.className("inventory_item_price")).getText().trim();

			if (expectedProductNames.contains(name)) {
				double price = Double.parseDouble(priceText.replace("$", ""));
				total += price;
			}
		}

		return Math.round(total * 100.0) / 100.0; // ✅ Optional rounding
	}

	public void logout() {
		driver.findElement(By.id("react-burger-menu-btn")).click();

		// Wait briefly to allow side menu to appear
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.id("logout_sidebar_link")).click();
	}

}
