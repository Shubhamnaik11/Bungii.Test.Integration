package com.bungii.android.manager;

import com.bungii.SetupManager;
import com.bungii.common.utilities.LogUtility;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

public class ActionManager {
    private static LogUtility logger = new LogUtility(ActionManager.class);

    /**
     * @param element
     *            , locator of field
     * @param text
     *            , Text value that is to be sent
     */
    public void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
        logger.detail("Send  " + text + " in element" + element.toString());
    }

    /**
     * @return boolean value according to alert existence
     */
    public boolean isAlertPresent() {
        try {
            Thread.sleep(1000);
            SetupManager.getDriver().switchTo().alert();
            logger.detail("Alert is present");
            return true;
        } catch (NoAlertPresentException | InterruptedException Ex) {
            logger.detail("Alert is not present");
            return false;
        }
    }

    /**
     * @param element
     *            ,locator that is to be clicked
     */
    public void click(WebElement element) {
        element.click();
        logger.detail("Click on locator by locator" + element.toString());
    }
}
