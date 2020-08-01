package com.uiautomation.testcases;

import com.aventstack.extentreports.Status;
import com.uiautomation.pages.HomePage;
import com.uiautomation.pages.MyAccountPage;
import com.uiautomation.testcases.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

public class LoginTest extends TestBase {
    HomePage homepage;
    MyAccountPage myAccountPage;

    @Test
    public void loginTest()
    {
    homepage = new HomePage(driver);

    log(Status.INFO, "Test started");
        myAccountPage =homepage.clickMyAccount();
        log(Status.INFO, "Clicked on My Account link");
        myAccountPage.enterUsername(prop.getProperty("email"));
        log(Status.INFO, "email entered : " +prop.getProperty("email"));
        myAccountPage.enterPassword(prop.getProperty("pass"));
        log(Status.INFO, "password entered : " +prop.getProperty("pass"));
        myAccountPage.clickLoginButton();
        log(Status.INFO,"Clicked on Login Button");
        String actualUserName = myAccountPage.getUserName();
        log(Status.INFO,"redirected to My Profile page.");
        String[] expectedUsername = prop.getProperty("email").split("@");

    }
}
