package com.bungii.web.manager;

import com.bungii.SetupManager;
import com.bungii.common.utilities.LogUtility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class ActionManager {
    private static LogUtility logger = new LogUtility(ActionManager.class);

    /**
     * @param element , locator of field
     * @param text    , Text value that is to be sent
     */
    public void clearSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        logger.detail("Send  " + text + " in element" + element.toString());
    }
    /**
     * @param element , locator of field
     * @param text    , Text value that is to be sent
     */
    public void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
        logger.detail("Send  " + text + " in element" + element.toString());
    }
    public void clear(WebElement element) {
        logger.detail("Clear  element" + element.toString());
    }

    public String getText(WebElement element) {
        String text = element.getText();
        logger.detail("text Value is  " + text + " for element" + element.toString());

        return text;
    }
    /**
     * @param element ,locator that is to be clicked
     */
    public void click(WebElement element) {
        try {
            element.click();
        }
         catch(StaleElementReferenceException | InvalidElementStateException ex) {
                        //Chrome Retry if unable to click because of overlapping (Chrome NativeEvents is always on (Clicks via Co-ordinates))
                        JavaScriptClick(element);
            }
        logger.detail("Click on locator by locator" + element.toString());
    }
    public void JavaScriptClick(WebElement element) {
            JavascriptExecutor executor = (JavascriptExecutor) SetupManager.getDriver();
            executor.executeScript("arguments[0].click();", element);
        logger.detail(" JS Click on locator by locator" + element.toString());


    }
    public void navigateToPreviousPageUsingBrowserBackButton() {
        SetupManager.getDriver().navigate().back();
    }
    //Select Random Dropdown value
    Random random = new Random();
    public void selectRandomDropdown(WebElement DropdownField)
    {
        Select  s = new Select(DropdownField);
        int itemCount = s.getOptions().size(); // get the count of elements in ddlWebElement
        s.selectByIndex(random.nextInt( itemCount-1));
    }

    public void navigateTo(String url) {
        SetupManager.getDriver().navigate().to(url);
    }

    public static void selectElementByText(WebElement element, String text)
    {
        new Select(element).selectByVisibleText(text);
    }
}
