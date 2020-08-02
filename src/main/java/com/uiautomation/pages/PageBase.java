package com.uiautomation.pages;

import com.uiautomation.utilities.TestUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

    public WebDriver driver;
    public WebDriverWait wait;
    public String readText;
    public PageBase(WebDriver driver)
    {
        this.driver = driver;

        PageFactory.initElements(driver, this);
        wait =  new WebDriverWait(driver, TestUtil.EXPLICIT_WAIT);
    }

    public void waitForElementVisibility(WebElement element)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element)
    {
        waitForElementVisibility(element);
        try {
            if (element.isDisplayed() || element.isEnabled())
                element.click();
        }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Element not found!!! " + element);

    }

    }

    public void writeText(WebElement element, String str)
    {
        waitForElementVisibility(element);
        try
        {
            if(element.isEnabled()||element.isDisplayed()) {
                element.clear();
                element.sendKeys(str);

            }
        }
        catch (Exception e)
        {
           // e.printStackTrace();
            throw new RuntimeException("Searched element is not found!!! "+element);
        }
    }

    public String readText(WebElement element) {

        waitForElementVisibility(element);
        try {
            if (element.isDisplayed() || element.isEnabled())
            {
                readText = element.getText();
            }

        } catch (Exception e){
        e.printStackTrace();
        throw new RuntimeException("Text not readable !!! "+element);
    }
        return readText;

    }


}
