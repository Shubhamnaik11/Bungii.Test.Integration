package com.bungii.android.manager;

import com.bungii.SetupManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.collections.Lists;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

public class ActionManager {
    private static LogUtility logger = new LogUtility(ActionManager.class);
    private final long DRIVER_WAIT_TIME;

    public ActionManager() {
        DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));

    }
    public static void keyBoardEvent(int eventNumber) {
        try {
            String strCmdText;
            strCmdText = "cmd /C adb shell input keyevent " + eventNumber;
            Process myProcess = new ProcessBuilder("CMD.exe", strCmdText).start();
            logger.detail("Performed Keyboard Event : " + eventNumber);


        } catch (Exception ex) {
        }
    }

    public static void clear(WebElement element) {
        element.clear();
        logger.detail("Clear locator by locator" + element.toString());
    }

    public static void NavigateBack() {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
        driver.navigate().back();
        logger.detail("Navigate Back");

    }

/*    public static void HideKeyboard() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.hideKeyboard();
            logger.detail("Hide Keyboard");
        } catch (Exception ex) {
        }
    }*/

    public boolean isElementPresent(WebElement element) {
        //Set the timeout to something low
    //    AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
     //   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            boolean isdisplayed = element.isDisplayed();
            return isdisplayed;
        } catch (Exception Ex) {
            return false;
        }
    }

    public boolean isElementEnabled(WebElement element) {
        //Set the timeout to something low
        //    AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
        //   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            boolean isdisplayed = element.isEnabled();
            return isdisplayed;
        } catch (Exception Ex) {
            return false;
        }
    }
    public static void waitUntilIsElementExistsAndDisplayed(WebElement element) {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until((ExpectedConditions.visibilityOf(element)));
        } catch (Exception Ex) {
            logger.detail("Page source "+ SetupManager.getDriver().getPageSource());
            logger.error("Error performing step", ExceptionUtils.getStackTrace(Ex));
         //   Assert.fail("Following element is not displayed : " + element);
        }
    }

    public static void waitUntilIsElementExistsAndDisplayed(WebElement element, Long waitTime) {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until((ExpectedConditions.visibilityOf(element)));
        } catch (Exception Ex) {
            Assert.fail("Following element is not displayed : " + element);
        }
    }

/*    public static void HideKeyboard() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.hideKeyboard();
            logger.detail("Hide Keyboard");
        } catch (Exception ex) {
        }
    }*/

    public static void waitUntilAlertDisplayed(WebElement element) {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until((ExpectedConditions.visibilityOfAllElements(element)));
        } catch (Exception Ex) {
            Assert.fail("Alert not received : " + element);
        }
    }

    public static boolean waitUntilAlertDisplayed(Long delay) {
        boolean isDisplayed = false;
        try { //
            new WebDriverWait(SetupManager.getDriver(), delay)
                    .ignoring(NoAlertPresentException.class)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@resource-id,'notification_alert_message')]")));

            //      .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@resource-id='com.bungii.driver:id/notification_alert_message']")));
            isDisplayed = true;
        } catch (Exception Ex) {
            isDisplayed = false;
            //   Assert.fail("Alert not received : " );
        }
        return isDisplayed;
    }

    public String getValueAttribute(WebElement element) {
        String value = element.getAttribute("value");
        logger.detail("'value' attribute for " + element.toString() + " is " + value);
        return value;
    }

    public String getAttribute(WebElement element, String attribute) {
        String value = element.getAttribute(attribute);
        logger.detail(attribute + " attribute for " + element.toString() + " is " + value);
        return value;
    }


    /**
     * @param element , locator of field
     * @param text    , Text value that is to be sent
     */
    public void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
        hideKeyboard();
        logger.detail("Send  " + text + " in element" + element.toString());
    }

    public void hideKeyboard() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.hideKeyboard();
        } catch (Exception e) {

        }

    }
/*
    public void scrollToBottom()
    {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = ((Dimension) dimensions).getHeight() * 0.5;
        int scrollstart = screenHeightStart.intValue();

        Double screenHeightEnd = ((Dimension) dimensions).getHeight()  * 0.2;

        int scrollend = screenHeightEnd.intValue();
//
  }*/

    public String getText(WebElement element) {
        String text = element.getText();
        logger.detail("text Value is  " + text + " for element" + element.toString());

        return text;
    }
    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     *
     */
    public void eitherTextToBePresentInElementText(final WebElement element, final String text1,final String text2) {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(SetupManager.getDriver()).withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        try {
            //  wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            wait.until(
                    ExpectedConditions.or(
                            ExpectedConditions.textToBePresentInElement(element,text1),
                            ExpectedConditions.textToBePresentInElement(element,text2)
                    )
            );        } catch (Exception e) {
            logger.detail("Wait failed");
        }
    }
    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     *
     */
    public void textToBePresentInElementText(final WebElement element, final String text1) {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(SetupManager.getDriver()).withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        try {
            //  wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            wait.until(
                    ExpectedConditions.or(
                            ExpectedConditions.textToBePresentInElement(element,text1)
                    )
            );        } catch (Exception e) {
            logger.detail("Wait failed");
        }
    }
    public void clearSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        hideKeyboard();
        logger.detail("Send  " + text + " in element" + element.toString());
    }

    /**
     * SendKeys using adb shell
     *
     * @param input
     */
    public void sendKeys(String input) {
        AndroidDriver driver = (AndroidDriver) SetupManager.getDriver();
        Map<String, Object> args = new HashMap<>();
        args.put("command", "input");
        args.put("args", Lists.newArrayList("text", input));
        driver.executeScript("mobile: shell", args);
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

    public boolean isNotificationAlertDisplayed() {
        boolean isDisplayed = false;
        try {
            new WebDriverWait(SetupManager.getDriver(), 30)
                    .ignoring(NoAlertPresentException.class)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@resource-id,'notification_alert_message')]")));
            isDisplayed = true;
        } catch (Exception Ex) {
            isDisplayed = false;
            //   Assert.fail("Alert not received : " );
        }
        return isDisplayed;
    }

    /**
     * @param element ,locator that is to be clicked
     */
    public void click(WebElement element) {
        element.click();
        logger.detail("Click on locator by locator" + element.toString());
    }
    /**
     * @param element ,locator that is to be clicked
     */
    public void longPress(WebElement element) {

        TouchAction action = new TouchAction((AndroidDriver)SetupManager.getDriver()).longPress(longPressOptions().withElement(element(element)).withDuration(Duration.ofMillis(10000))).release().perform();
      //  Thread.sleep(5000);


    }
    public void click(Point p) {
        TouchAction touchAction = new TouchAction((AndroidDriver<MobileElement>) SetupManager.getDriver());
        PointOption top = PointOption.point(p.getX(), p.getY());
        touchAction.tap(top).perform();
        logger.detail("Clicked point at , (" + p.getX() + "," + p.getY() + ")");
    }

    public void scrollToBottom() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

            //if pressX was zero it didn't work for me
            int pressX = driver.manage().window().getSize().width / 2;
            // 4/5 of the screen as the bottom finger-press point
            int bottomY = driver.manage().window().getSize().height * 4 / 5;
            // just non zero point, as it didn't scroll to zero normally
            int topY = driver.manage().window().getSize().height / 8;
            //scroll with TouchAction by itself
            scroll(pressX, bottomY, pressX, topY);
        } catch (Exception e) {
            logger.error("Not able to scroll");
        }
    }

    /**
     * An expectation for checking that an element is either invisible or not
     * present on the DOM.
     *
     * @param element used to find the element
     */
    public boolean invisibilityOfElementLocated(WebElement element) {

        try {
            return (new WebDriverWait(SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("WaitTime"))))
                    .until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            return true;
        }
    }

    private void scroll(int fromX, int fromY, int toX, int toY) {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();

    }

    public void scrollUntilElementDisplayed(WebElement element) {
        do scrollToBottom();
        while (isElementPresent(element) == false);
    }

    public void scrollToTop() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

            //if pressX was zero it didn't work for me
            int pressX = driver.manage().window().getSize().width / 2;
            // 4/5 of the screen as the bottom finger-press point
            int bottomY = driver.manage().window().getSize().height * 4 / 5;
            // just non zero point, as it didn't scroll to zero normally
            int topY = driver.manage().window().getSize().height / 6;
            //scroll with TouchAction by itself
            scroll(pressX, topY+180, pressX, bottomY);
        } catch (Exception e) {
            logger.detail("Failed to drap to top");
        }
    }

/*    public void swipeLeft(WebElement row) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();


        int xShift = Math.toIntExact(Math.round(row.getSize().width * 0.20));
        int xStart = (row.getSize().width) - xShift;
        int xEnd = xShift;

        TouchAction action = new TouchAction(driver)
                .press(point(xStart, (row.getSize().height / 2)))
                .waitAction(waitOptions(Duration.ofMillis(1000)))
                .moveTo(point(xEnd, (row.getSize().height / 2)))
                .release().perform();
        Thread.sleep(2000);


    }*/

    public void swipeRight(WebElement row) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();


        int xAxisStartPoint = row.getLocation().getX() + row.getSize().getWidth() / 20;
        int xAxisEndPoint = row.getLocation().getX() + row.getSize().getWidth();
        int yAxis = row.getLocation().getY() + row.getRect().getHeight() / 2;
        TouchAction act = new TouchAction(driver);
//pressed x axis & y axis of seekbar and move seekbar till the end
        act.longPress(PointOption.point(xAxisStartPoint, yAxis)).moveTo(PointOption.point(xAxisEndPoint - 1, yAxis)).release().perform();
    }

    public void swipeLeft(WebElement row) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();


        int xAxisStartPoint = row.getLocation().getX() + row.getSize().getWidth() / 20;
        int xAxisEndPoint = row.getLocation().getX() + row.getSize().getWidth();
        int yAxis = row.getLocation().getY() + row.getRect().getHeight() / 2;
        TouchAction act = new TouchAction(driver);
//pressed x axis & y axis of seekbar and move seekbar till the end
        act.longPress(PointOption.point(xAxisEndPoint - 1, yAxis)).moveTo(PointOption.point(xAxisStartPoint, yAxis)).release().perform();
    }

    public void scrollUp(WebElement row) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        int xShift = Math.toIntExact(Math.round(row.getSize().width * 0.20));
        int xStart = (row.getSize().height) - xShift;
        int xEnd = xShift;

        TouchAction action = new TouchAction(driver)
                .press(point(xStart, (row.getSize().width / 2)))
                .waitAction(waitOptions(Duration.ofMillis(1000)))
                .moveTo(point(xEnd, (row.getSize().width / 2)))
                .release().perform();


        Thread.sleep(2000);
    }

    /**
     * Show notification
     */
    public void showNotifications() {
        manageNotifications(true);
    }

    /**
     * Hide notification
     */
    public void hideNotifications() {
        manageNotifications(false);
    }

    /**
     * Manage notification
     *
     * @param show
     */
    private void manageNotifications(Boolean show) {

        Dimension screenSize = SetupManager.getDriver().manage().window().getSize();
        int yMargin = 5;
        int xMid = screenSize.width / 2;
        PointOption top = PointOption.point(xMid, yMargin);
        PointOption bottom = PointOption.point(xMid, screenSize.height - yMargin);

        TouchAction action = new TouchAction((AppiumDriver) SetupManager.getDriver());
        if (show) {
            action.press(top);
        } else {
            action.press(bottom);
        }
        action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        if (show) {
            action.moveTo(bottom);
        } else {
            action.moveTo(top);
        }
        action.perform();
    }

    public void hardWaitWithSwipeUp(int minutes) throws InterruptedException {
        for (int i = minutes; i > 0; i--) {
            logger.detail("Inside Hard wait , wait for " + i + " minutes");
            Thread.sleep(30000);
            scrollToTop();
            Thread.sleep(30000);
            scrollToTop();
        }
    }

    public void waitForAlert() {
        (new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.alertIsPresent());
    }

    public void hardWait(int minutes) throws InterruptedException {
        for (int i = minutes; i > 0; i--) {
            logger.detail("Inside Hard wait , wait for " + i + " minutes");
            Thread.sleep(30000);
            //Send some command after 30 sec so that connection wont die
            ((AndroidDriver)SetupManager.getDriver()).getDeviceTime();
            Thread.sleep(30000);
            //Send some command after 30 sec so that connection wont die
            ((AndroidDriver)SetupManager.getDriver()).getDeviceTime();
        }
    }

}
