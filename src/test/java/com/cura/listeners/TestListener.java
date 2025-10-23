package com.cura.listeners;

import com.aventstack.extentreports.Status;
import com.cura.utils.DriverFactory;
import com.cura.utils.ReportManager;
import com.cura.utils.ScreenshotUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	

	// Optional: log test start, success, etc.
	@Override
	public void onTestStart(ITestResult result) {
		ReportManager.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ReportManager.getTest().pass("Test passed");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String screenshotPath = ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), testName);

		ReportManager.getTest().fail("Test failed: " + result.getThrowable());
		ReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
	}
	
	// Called when test is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        ReportManager.getTest().skip("Test skipped");
    }

	@Override
	public void onFinish(ITestContext context) {
		ReportManager.flushReport();
	}
}
