package com.uiautomation.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    public ExtentReports report;
    public ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

         
        test = report.createTest(result.getMethod().getMethodName());
        result.setAttribute("ExtentTestObject", test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

        report = ReportManager.generateReport();
    }

    @Override
    public void onFinish(ITestContext context) {
report.flush();
    }
}
