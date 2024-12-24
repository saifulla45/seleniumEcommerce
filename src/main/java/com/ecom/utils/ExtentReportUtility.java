package com.ecom.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ecom.constants.FrameworkConstants;

public class ExtentReportUtility {

    public static ExtentReports getReportObject() {
        String filePath = FrameworkConstants.REPORTPATH;
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(filePath);
        extentSparkReporter.config().setReportName("Test Automation Execution Report");
        extentSparkReporter.config().setDocumentTitle("Automation Report");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setTimeStampFormat("HH-mm-ss");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester","Saifulla");
        return extentReports;

    }
}
