package com.uiautomation.testcases;

import com.aventstack.extentreports.Status;
import com.uiautomation.pages.HomePage;
import com.uiautomation.pages.MyAccountPage;
import com.uiautomation.report.Listener;
import com.uiautomation.testcases.base.TestBase;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LoginTest extends TestBase {
    HomePage homepage;
    MyAccountPage myAccountPage;
    InputStream inputStream;
    JSONTokener jsonTokener;
    JSONObject jsonObject;

    @Test
    public void loginTest()
    {
        homepage = new HomePage(driver);
    try {
        String filePath = "TestData/LoginData.json";

        inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        jsonTokener = new JSONTokener(inputStream);
        jsonObject = new JSONObject(jsonTokener);
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    finally {
        if(inputStream!=null)
        {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        test.log(Status.INFO, "Test started");
        myAccountPage =homepage.clickMyAccount();
        test.log(Status.INFO, "Clicked on My Account link");
        myAccountPage.enterUsername(jsonObject.getJSONObject("valid").getString("email"));
        test.log(Status.INFO, "email entered : " +jsonObject.getJSONObject("valid").getString("email"));
        myAccountPage.enterPassword(jsonObject.getJSONObject("valid").getString("pass"));
        test.log(Status.INFO, "password entered : " +jsonObject.getJSONObject("valid").getString("pass"));
        myAccountPage.clickLoginButton();
        test.log(Status.INFO,"Clicked on Login Button");
        String actualUserName = myAccountPage.getUserName();
        test.log(Status.INFO,"redirected to My Profile page.");
        String[] expectedUsername = jsonObject.getJSONObject("valid").getString("email").split("@");

        Assert.assertEquals(expectedUsername[0], actualUserName ,"ERROR!!! username does not match");
    }
}
