package com.cura.utils;

import java.util.*;
import java.text.SimpleDateFormat;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

	private static ExtentReports extent;
	private static ExtentTest test;

	// Initialize report - call this once before tests start
	public static void initReport() 
	{
	    if (extent == null) {
	        // ✅ FIX #1: Added timestamp format for cleaner file names (more readable)
	        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        String reportPath = "test-output/ExtentReport_" + timestamp + ".html";

	        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	        extent = new ExtentReports();
	        extent.attachReporter(spark);

	        extent.setSystemInfo("Tester", "Alamelu");
	        extent.setSystemInfo("Environment", "QA");

	        // ✅ FIX #2: Added log to confirm new report file creation
	        System.out.println("✅ New report created: " + reportPath);
	    }
	}


	// Start a new test in the report with the test name
	public static void createTest(String testName) {
		test = extent.createTest(testName);
	}

	// Get the current test object to add logs
	public static ExtentTest getTest() {
		return test;
	}

	// Save the report to the file, call once after all tests finish
	public static void flushReport() {
	    if (extent != null) {
	        extent.flush();
	        extent = null; // ✅ FIX #3: Important!
	                       // Forces ExtentReports to reset on next run,
	                       // preventing old results from showing up
	    }
	}

}
