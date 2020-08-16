package com.uiautomation.dataprovider;

import com.uiautomation.base.TestBase;
import org.testng.annotations.DataProvider;

public class LoginDP extends TestBase {

    @DataProvider(name = "Auth")
    public static Object[][] EmailLoginData() {

        return new Object[][]{
                {"testautomationui@gmail.comm", "&*test@*&123","Error: A user could not be found with this email address."},
                {"", "&*test@*&123","Error: Username is required."},
                {"testautomationui@gmail.com", "","Error: Password is required."}};

    }
    }


