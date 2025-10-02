//package com.cura.tests;
//
//public class AppointmentPageOld 
//{
//	package com.cura.pages;
//
//	import org.openqa.selenium.By;
//	import org.openqa.selenium.WebDriver;
//	import org.openqa.selenium.WebElement;
//	import org.openqa.selenium.support.ui.Select;
//
//	public class AppointmentPage {
//		WebDriver driver;
//
//		private By facilityDropdown = By.id("combo_facility");
//		private By readmissonChkbox = By.id("chk_hospotal_readmission");
//		private By healthRadioBtn = By.id("radio_program_medicare");
//		private By visitDateField = By.id("txt_visit_date");
//		private By commentField = By.id("txt_comment");
//		private By bookAppointment = By.id("btn-book-appointment");
//		//private By confirmationHeader = By.tagName("h2");
//		private By confirmationHeader = By.xpath("//h2[normalize-space()='Appointment Confirmation']");
//
//
//		// logout
//		// private By menuToggle = By.id("menu-toggle");
//		// private By logoutLink = By.xpath("//a[text()='Logout']");
//
//		public AppointmentPage(WebDriver driver) {
//			this.driver = driver;
//		}
//
//		public void bookAppointment(String facility, String date, String comment) {
//
//			/*
//			 * Hardcoding input-- just go on the flow
//			 * 
//			 * driver.findElement(facilityDropdown).sendKeys(facility);
//			 * driver.findElement(readmissonChkbox).click();
//			 * driver.findElement(healthRadioBtn).click();
//			 * driver.findElement(visitDateField).sendKeys(date);
//			 * driver.findElement(commentField).sendKeys(comment);
//			 * driver.findElement(bookAppointment).click();
//			 */
//
//			// Select Option dropdown
//			Select facilityOption = new Select(driver.findElement(facilityDropdown));
//			facilityOption.selectByVisibleText(facility);
//
//			// Click the readmission checkbox
//			driver.findElement(readmissonChkbox).click();
//
//			// Select Medicare radio button
//			driver.findElement(healthRadioBtn).click();
//
//			// Enter visit date
//			WebElement dateInput = driver.findElement(visitDateField);
//			dateInput.clear();
//			dateInput.sendKeys(date);
//			// Clear existing date if any dateInput.sendKeys(date);
//			// Type date (formatshould match expected by the app)
//
//			// Enter comment
//			WebElement commentInput = driver.findElement(commentField);
//			commentInput.clear();
//			commentInput.sendKeys(comment);
//
//			// Click book appointment button
//			driver.findElement(bookAppointment).click();
//
//		}
//
//		/*public String getConfirmationHeader() {
//			System.out.println("Current URl in confirm :: " + driver.getCurrentUrl());
//			System.out.println("Confirm Text :: " + driver.findElement(confirmationHeader).getText());
//			return driver.findElement(confirmationHeader).getText();
//		}*/
//		
//		
//
//		public String getConfirmationHeader() 
//		{
//		    System.out.println("Current URL in confirm :: " + driver.getCurrentUrl());
//		    String headerText = driver.findElement(confirmationHeader).getText();
//		    System.out.println("Confirm Text :: " + headerText);
//		    return headerText;
//		}
//
//		// Logout
//		/*
//		 * public void logout() { System.out.println("logout method");
//		 * driver.findElement(menuToggle).click();
//		 * driver.findElement(logoutLink).click(); }
//		 */
//
//	}
//
//
//
//}
