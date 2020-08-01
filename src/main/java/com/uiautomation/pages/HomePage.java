package com.uiautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase{


    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='My Account']")
    private WebElement MyAccount;

    public MyAccountPage clickMyAccount()
    {

        clickElement(MyAccount);
        return new MyAccountPage(driver);
    }

}
