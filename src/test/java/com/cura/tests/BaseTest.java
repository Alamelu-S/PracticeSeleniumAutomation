package com.cura.tests;

import com.cura.utils.ReportManager;
import org.testng.annotations.*;
import java.lang.reflect.Method;

	public class BaseTest {

	    @BeforeSuite
	    public void initReport() {
	        ReportManager.initReport();  // Start report once before all tests
	    }

	    @BeforeMethod
	    public void startTest(Method method) {
	        ReportManager.createTest(method.getName());  // Start logging for each test method
	    }

	    @AfterSuite
	    public void flushReport() {
	        ReportManager.flushReport();  // Save the report after all tests finish
	    }
	}


