package com.uiautomation.testcases.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;
    public Properties prop;

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("src/main/java/com/uiautomation/configuration/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
  //          driver.quit();
        }

    }
