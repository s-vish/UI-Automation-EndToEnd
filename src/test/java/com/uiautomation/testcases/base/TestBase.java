package com.uiautomation.testcases.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.uiautomation.report.ReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase implements ITestListener {

    public static WebDriver driver;
    public Properties prop;
    public ExtentReports report;
    public ExtentTest test;
    public String path = System.getProperty("user.dir");
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(path+"/src/main/java/com/uiautomation/configuration/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



@BeforeMethod
public void onTestStart(ITestResult result) {

    report = ReportManager.generateReport();
    test = report.createTest(result.getMethod().getMethodName());
    result.setAttribute("ExtentTestObject", test);
}

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS,"Test case "+result.getMethod().getMethodName()+" is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL,"Test case "+result.getMethod().getMethodName()+" is Failed");
        test.log(Status.FAIL,result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }


    @Override
    public void onFinish(ITestContext context) {
        report.flush();
    }




    @BeforeClass
    public void init() {
        String browser_name = prop.getProperty("browser");
        if (browser_name.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("disable-notification");
            driver = new ChromeDriver(options);

        }
        else if (browser_name.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        else{
                System.out.println(("browser : " + browser_name + " is invalid, Launching chrome as browser of choice.."));
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("disable-notification");
                driver = new ChromeDriver();
            }
        //open url
        driver.get(prop.getProperty("url"));
        }

        @AfterClass
        public void tearDown()
        {
           driver.quit();
        }






    }
