package com.uiautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends PageBase {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id="username")
    private WebElement username;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(xpath="//input[@name='login']")
    private WebElement loginButton;

    @FindBy(xpath  ="//label[@for='rememberme']")
    private WebElement rememberMe;

    @FindBy(xpath="//a[contains(text(),'Lost your password?')] ")
    private  WebElement forgotPassword;

    @FindBy(xpath ="//p/strong")
    private WebElement usernameText;

//    public String login(String emailID, String pass)
//    {
//
//
//        username.clear();
//        username.sendKeys(emailID);
//        password.clear();
//        password.sendKeys(pass);
//        loginButton.click();
//        waitForElementVisibility(usernameText);
//       return usernameText.getText();
//    }
    public void enterUsername(String email)
    {

        writeText(username, email);
    }

    public void enterPassword(String pass)
    {
        writeText(password, pass);
    }

    public void clickLoginButton()
    {
        clickElement(loginButton);
    }

    public String getUserName()
    {
       return readText(usernameText);
    }

}
