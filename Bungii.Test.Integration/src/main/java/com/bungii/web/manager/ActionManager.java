package com.bungii.web.manager;

import com.bungii.SetupManager;
import com.bungii.common.utilities.LogUtility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
        element.click();
        logger.detail("Click on locator by locator" + element.toString());
    }

    public void navigateToPreviousPageUsingBrowserBackButton() {
        SetupManager.getDriver().navigate().back();
    }


    public void navigateTo(String url) {
        SetupManager.getDriver().navigate().to(url);
    }

    public static void selectElementByText(WebElement element, String text)
    {
        new Select(element).selectByVisibleText(text);
    }
}
