package com.ecom.testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecom.utils.ExtentReportUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends TestBase implements ITestListener {

    ExtentReports extentReport;
    ExtentTest test;
    ThreadLocal<ExtentTest> reports = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        test = extentReport.createTest(result.getMethod().getMethodName());
        reports.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        reports.get().log(Status.PASS,"Test is Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        reports.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        String path = takeScreenshot(result.getMethod().getMethodName(),driver);
        reports.get().addScreenCaptureFromPath(path , result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        reports.get().skip(result.getThrowable());
    }

    @Override
    public void onStart(ITestContext context) {
        extentReport= ExtentReportUtility.getReportObject();
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
    }

}
