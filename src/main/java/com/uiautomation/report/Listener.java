package com.uiautomation.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiautomation.base.TestBase;
import com.uiautomation.pages.PageBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener extends TestBase implements ITestListener {
    public ExtentReports report;
    public ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = (ExtentTest) result.getAttribute("ExtentTestObject");
        test.log(Status.PASS, "Test case " + result.getMethod().getMethodName() + " is passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = (ExtentTest)result.getAttribute("ExtentTestObject");
        test.log(Status.FAIL, "Test case " + result.getMethod().getMethodName() + " is Failed " + result.getThrowable().getMessage() );

        File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss z");
        Date date = new Date();
        String actualDate = format.format(date);
        String screenshotPath=System.getProperty("user.dir")+"/extentReport/"+actualDate+"/screenshots/"+"abc.jpeg";
        File dest = new File(screenshotPath);
        try
        {
            FileUtils.copyFile(scr,dest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try{
            test.addScreenCaptureFromPath(screenshotPath, "Test case Fialure" +
                    "screenshot");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test =(ExtentTest)result.getAttribute("ExtentTestObject");
		test.log(Status.SKIP, result.getName() + " is Skipped");
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
    }
}



