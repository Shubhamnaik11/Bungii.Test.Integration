package com.bungii.common.core;

import com.bungii.SetupManager;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.PropertyUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class PageBase {
    private   long DRIVER_WAIT_TIME;

    public enum LocatorType {
        Id,
        Name,
        ClassName,
        LinkText,
        PartialLinkText,
        CssSelector,
        TagName,
        XPath
    }
    public  PageBase(){
        DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));

    }

    public  void WaitUntilElementIsDisplayed(By locator) {
        try {
            WebDriver driver = DriverManager.getObject().getDriver();
            WebDriverWait wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        } catch (Exception ex) {
            // Assert.Fail("Following element is not displayed : " + locator);
        }
    }

    public void waitForPageLoad() {
        new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME).until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
    public List<WebElement> findElements(String identifier, LocatorType locatorType) {
        WebDriver driver = DriverManager.getObject().getDriver();

        List<WebElement> elements = null;

        switch (locatorType) {
            case Id: {
                WaitUntilElementIsDisplayed(By.id(identifier));
                elements = driver.findElements(By.id(identifier));
                break;
            }
            case Name: {
                WaitUntilElementIsDisplayed(By.name(identifier));
                elements = driver.findElements(By.name(identifier));
                break;
            }
            case ClassName: {
                WaitUntilElementIsDisplayed(By.className(identifier));
                elements = driver.findElements(By.className(identifier));
                break;
            }
            case XPath: {
                WaitUntilElementIsDisplayed(By.xpath(identifier));
                elements = driver.findElements(By.xpath(identifier));
                break;
            }
            case LinkText: {
                WaitUntilElementIsDisplayed(By.linkText(identifier));
                elements = driver.findElements(By.linkText(identifier));
                break;
            }
            case PartialLinkText: {
                WaitUntilElementIsDisplayed(By.partialLinkText(identifier));
                elements = driver.findElements(By.partialLinkText(identifier));
                break;
            }
            case CssSelector: {
                WaitUntilElementIsDisplayed(By.cssSelector(identifier));
                elements = driver.findElements(By.cssSelector(identifier));
                break;
            }
            case TagName: {
                WaitUntilElementIsDisplayed(By.tagName(identifier));
                elements = driver.findElements(By.tagName(identifier));
                break;
            }

        }
        return elements;
    }
    private void updateWaitTime( boolean ...ignoreException){
        if(ignoreException.length>0)
            if(ignoreException[0]==true)
                DRIVER_WAIT_TIME = 7L;

    }
    public void  updateWaitTime(Long waitTime){
        DRIVER_WAIT_TIME=waitTime;
    }

    public WebElement findElement(String identifier, LocatorType locatorType , boolean ...ignoreException) {
        WebDriver driver = DriverManager.getObject().getDriver();
        updateWaitTime(ignoreException);
        WebElement element = null;
        try{
        switch (locatorType) {
            case Id: {
                WaitUntilElementIsDisplayed(By.id(identifier));
                element = driver.findElement(By.id(identifier));
                break;
            }
            case Name: {
                WaitUntilElementIsDisplayed(By.name(identifier));
                element = driver.findElement(By.name(identifier));
                break;
            }
            case ClassName: {
                WaitUntilElementIsDisplayed(By.className(identifier));
                element = driver.findElement(By.className(identifier));
                break;
            }
            case XPath: {
                WaitUntilElementIsDisplayed(By.xpath(identifier));
                element = driver.findElement(By.xpath(identifier));
                break;
            }
            case LinkText: {
                WaitUntilElementIsDisplayed(By.linkText(identifier));
                element = driver.findElement(By.linkText(identifier));
                break;
            }
            case PartialLinkText: {
                WaitUntilElementIsDisplayed(By.partialLinkText(identifier));
                element = driver.findElement(By.partialLinkText(identifier));
                break;
            }
            case CssSelector: {
                WaitUntilElementIsDisplayed(By.cssSelector(identifier));
                element = driver.findElement(By.cssSelector(identifier));
                break;
            }
            case TagName: {
                WaitUntilElementIsDisplayed(By.tagName(identifier));
                element = driver.findElement(By.tagName(identifier));
                break;
            }
        }
        }catch (NoSuchElementException e){
            if(ignoreException.length>0){
                if(ignoreException[0]==true)
                {
                    //ignore exception
                    //or to do action
                }else{
                    throw new java.util.NoSuchElementException();
                }
            }else {
                throw new java.util.NoSuchElementException();
            }
        }finally{
            updateWaitTime(Long.parseLong(PropertyUtility.getProp("WaitTime")));

        }

        return element;
    }
    public void open(String PageUrl)
    {
        DriverManager.getObject().getDriver().navigate().to(PageUrl);
    }

    public String get(){return DriverManager.getObject().getDriver().getCurrentUrl();}

    /**
     * An expectation for checking that an element, known to be present on the
     * DOM of a page, is visible. Visibility means that the element is not only
     * displayed but also has a height and width that is greater than 0.
     */

    public WebElement visibilityOf(final WebElement element) {
        return (new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementEnabled(final WebElement element) {
        try {
            return element.isEnabled();
        } catch (TimeoutException exception) {
            return false;
        }

    }
}
