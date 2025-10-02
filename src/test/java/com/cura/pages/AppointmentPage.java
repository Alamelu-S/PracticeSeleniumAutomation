package com.cura.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AppointmentPage
{
    WebDriver driver;

    private By facilityDropdown = By.id("combo_facility");
    private By readmissonChkbox = By.id("chk_hospotal_readmission");
    private By healthRadioBtn = By.id("radio_program_medicare");
    private By visitDateField = By.id("txt_visit_date");
    private By commentField = By.id("txt_comment");
    private By bookAppointment = By.id("btn-book-appointment");

    // Use exact match for confirmation header
    private By confirmationHeader = By.xpath("//h2[normalize-space()='Appointment Confirmation']");

    public AppointmentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void bookAppointment(String facility, String date, String comment) {
        System.out.println(">>> Selecting facility: " + facility);
        Select facilityOption = new Select(driver.findElement(facilityDropdown));
        facilityOption.selectByVisibleText(facility);

        System.out.println(">>> Clicking readmission checkbox");
        driver.findElement(readmissonChkbox).click();

        System.out.println(">>> Selecting Medicare radio button");
        driver.findElement(healthRadioBtn).click();

        System.out.println(">>> Entering visit date: " + date);
        WebElement dateInput = driver.findElement(visitDateField);
        dateInput.clear();
        dateInput.sendKeys(date);

        System.out.println(">>> Entering comment: " + comment);
        WebElement commentInput = driver.findElement(commentField);
        commentInput.clear();
        commentInput.sendKeys(comment);

        System.out.println(">>> Clicking Book Appointment button...");
        WebElement bookBtn = driver.findElement(bookAppointment);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bookBtn); // Use JS click
        System.out.println(">>> Clicked Book Appointment button!");

        // TEMP: wait for the confirmation page to load (can be removed once stable)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getConfirmationHeader() {
        System.out.println(">>> Current URL in confirm: " + driver.getCurrentUrl());

        WebElement header = driver.findElement(confirmationHeader);
        String text = header.getText();
        System.out.println(">>> Confirmation Header Text: " + text);
        return text;
    }
}
