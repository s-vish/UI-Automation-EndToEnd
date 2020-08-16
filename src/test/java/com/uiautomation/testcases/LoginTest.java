package com.uiautomation.testcases;

import com.aventstack.extentreports.Status;
import com.uiautomation.pages.HomePage;
import com.uiautomation.pages.MyAccountPage;
import com.uiautomation.base.TestBase;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class LoginTest extends TestBase {
    HomePage homepage;
    MyAccountPage myAccountPage;
    InputStream inputStream;
    JSONTokener jsonTokener;
    JSONObject jsonObject;

    //   @Test
    public void loginTest() {
        homepage = new HomePage(driver);
        try {
            String filePath = "TestData/LoginData.json";

            inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            jsonTokener = new JSONTokener(inputStream);
            jsonObject = new JSONObject(jsonTokener);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        test.log(Status.INFO, "Test started");
        myAccountPage = homepage.clickMyAccount();
        test.log(Status.INFO, "Clicked on My Account link");
        myAccountPage.enterUsername(jsonObject.getJSONObject("valid").getString("email"));
        test.log(Status.INFO, "email entered : " + jsonObject.getJSONObject("valid").getString("email"));
        myAccountPage.enterPassword(jsonObject.getJSONObject("valid").getString("pass"));
        test.log(Status.INFO, "password entered : " + jsonObject.getJSONObject("valid").getString("pass"));
        myAccountPage.clickLoginButton();
        test.log(Status.INFO, "Clicked on Login Button");
        String actualUserName = myAccountPage.getUserName();
        test.log(Status.INFO, "redirected to My Profile page.");
        String[] expectedUsername = jsonObject.getJSONObject("valid").getString("email").split("@");

        Assert.assertEquals(expectedUsername[1], actualUserName, "ERROR!!! username does not match");
    }

    //   @Test(dependsOnMethods = {"loginTest"})
    public void depentMethod() {
        test.log(Status.INFO, "dependent Test started ");
    }

    /**
     * To be used in positive login case
     * String actualUserName = myAccountPage.getUserName();
     * test.log(Status.INFO, "redirected to My Profile page.");
     * String[] expectedUsername = uname.split("@");
     * <p>
     * Assert.assertEquals(expectedUsername[0], actualUserName, "ERROR!!! username does not match");
     */


    @Test
    public void loginTestWithValidData() {
        homepage = new HomePage(driver);
        test.log(Status.INFO, "Test started");
        myAccountPage = homepage.clickMyAccount();
        test.log(Status.INFO, "Clicked on My Account link");
        myAccountPage.enterUsername(prop.getProperty("email"));
        test.log(Status.INFO, "email entered : " + prop.getProperty("email"));
        myAccountPage.enterPassword(prop.getProperty("pass"));
        test.log(Status.INFO, "password entered : " + prop.getProperty("pass"));
        myAccountPage.clickLoginButton();
        test.log(Status.INFO, "Clicked on Login Button");
        String actualUserName = myAccountPage.getUserName();
        String[] expectedUsername = prop.getProperty("email").toString().split("@");
        Assert.assertEquals(expectedUsername[1], actualUserName, "ERROR!!! username does not match");
    }

//    @Test(dataProvider = "Auth", dataProviderClass = LoginDP.class)
    public void loginTestForNegativeScnrios(String uname, String pass, String value) {
        homepage = new HomePage(driver);
        test.log(Status.INFO, "Test started");
        myAccountPage = homepage.clickMyAccount();
        test.log(Status.INFO, "Clicked on My Account link");
        myAccountPage.enterUsername(uname);
        test.log(Status.INFO, "email entered : " + uname);
        myAccountPage.enterPassword(pass);
        test.log(Status.INFO, "password entered : " + pass);
        myAccountPage.clickLoginButton();
        test.log(Status.INFO, "Clicked on Login Button");
        String actualErrorText = myAccountPage.ErroInfoReadText();
        Assert.assertEquals(value, actualErrorText, "Assertion Failed");
    }


}
