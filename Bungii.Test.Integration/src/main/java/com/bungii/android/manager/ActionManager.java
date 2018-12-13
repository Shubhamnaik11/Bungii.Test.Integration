package com.bungii.android.manager;

import com.bungii.SetupManager;
import com.bungii.common.utilities.LogUtility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
        driver.hideKeyboard();
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

    public static void keyBoardEvent(int eventNumber)
    {
        try
        {
            String strCmdText;
            strCmdText = "cmd /C adb shell input keyevent " + eventNumber;
            Process myProcess = new ProcessBuilder("CMD.exe", strCmdText).start();
            logger.detail("Performed Keyboard Event : " + eventNumber);


        }
        catch (Exception ex)
        {
        }
    }

    public static void clear(WebElement element)
    {
        element.clear();
        logger.detail("Clear locator by locator" + element.toString());
    }


    public static void NavigateBack()
    {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
        driver.navigate().back();
        logger.detail("Navigate Back");

    }

    public static void HideKeyboard()
    {
        try
        {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.hideKeyboard();
            logger.detail("Hide Keyboard");
        }
        catch (Exception ex)
        {
        }
    }
    public static boolean isElementPresent(WebElement element)
    {
        //Set the timeout to something low
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        try
        {
            boolean isdisplayed = element.isDisplayed();
            return isdisplayed;
        }
        catch (Exception Ex)
        {
            return false;
        }
    }

    public void scrollToBottom()
    {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = ((Dimension) dimensions).getHeight() * 0.5;
        int scrollstart = screenHeightStart.intValue();
        Double screenHeightEnd = ((Dimension) dimensions).getHeight()  * 0.2;
        int scrollend = screenHeightEnd.intValue();
//
  }

    public void scrollUntilElementDisplayed(WebElement element)
    {
        do scrollToBottom();
        while(isElementPresent(element)==false);
    }

    public void scrollToTop()
    {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = ((Dimension) dimensions).getHeight() * 0.2;
        int scrollstart = (screenHeightStart).intValue();;
        Double screenHeightEnd = ((Dimension) dimensions).getHeight()  * 0.2;
        int scrollend = (screenHeightEnd).intValue();;
        //driver.swipe(0, scrollstart, 0, scrollend, 1000);
    }

    public void swipeLeft(WebElement row) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        int xShift = Math.toIntExact(Math.round(row.getSize().width * 0.20));
        int xStart = (row.getSize().width) - xShift;
        int xEnd = xShift;

        TouchAction action = new TouchAction(driver)
                .press(PointOption.point(xStart, (row.getSize().height / 2)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(xEnd, (row.getSize().height / 2)))
                .release().perform();

        Thread.sleep(2000);
    }

    public void scrollUp(WebElement row) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        int xShift = Math.toIntExact(Math.round(row.getSize().width * 0.20));
        int xStart = (row.getSize().height) - xShift;
        int xEnd = xShift;

        TouchAction action = new TouchAction(driver)
                .press(PointOption.point(xStart, (row.getSize().width / 2)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(xEnd, (row.getSize().width / 2)))
                .release().perform();


        Thread.sleep(2000);
    }
    public static void waitUntilIsElementExistsAndDisplayed(WebElement element)
    {
        try
        {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until((ExpectedConditions.visibilityOf(element)));
        }
        catch (Exception Ex)
        {
            Assert.fail("Following element is not displayed : " + element);
        }
    }

    public static void waitUntilAlertDisplayed(WebElement element)
    {
        try
        {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver,  10);
            wait.until((ExpectedConditions.visibilityOfAllElements(element)));
        }
        catch (Exception Ex)
        {
            Assert.fail("Alert not received : " + element);
        }
    }

}
