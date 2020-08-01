package com.uiautomation.testcases;

import com.uiautomation.pages.HomePage;
import com.uiautomation.pages.MyAccountPage;
import com.uiautomation.testcases.base.TestBase;
import org.testng.annotations.Test;

public class TestClass extends TestBase {
    HomePage homepage;
    MyAccountPage myAccountPage;

    @Test
    public void loginTest()
    {
    homepage = new HomePage(driver);
        myAccountPage =homepage.clickMyAccount();
        myAccountPage.enterUsername(prop.getProperty("email"));
        myAccountPage.enterPassword(prop.getProperty("pass"));
        myAccountPage.clickLoginButton();
        String userName = myAccountPage.getUserName();
        System.out.println(userName);
    }
}
