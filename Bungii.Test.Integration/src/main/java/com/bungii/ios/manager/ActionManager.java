package com.bungii.ios.manager;

import com.bungii.SetupManager;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ActionManager {
    private static LogUtility logger = new LogUtility(ActionManager.class);
    private  final long DRIVER_WAIT_TIME;

    public  ActionManager(){
        DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));

    }
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
    public String getValueAttribute(WebElement element) {
        String value = element.getAttribute("value");
        logger.detail("'value' attribute for " + element.toString() + " is " + value);
        return value;
    }

    public String getNameAttribute(WebElement element) {
        String value = element.getAttribute("name");
        logger.detail("'value' attribute for " + element.toString() + " is " + value);
        return value;
    }

    public void click(WebElement element) {
        element.click();
        logger.detail("Click on locator by element" + element.toString());
    }

    public void waitForAlert() {
        (new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.alertIsPresent());
    }
    /**
     * Swipe up on current mobile screen IOS SPECIFIC
     */
    public void swipeUP() {
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "up");
        js.executeScript("mobile: swipe", params);

    }
    /**
     * Swipe up on current mobile screen IOS SPECIFIC
     */
    public void swipeDown() {
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        js.executeScript("mobile: swipe", params);
    }

    /**
     * Swipe up on current mobile screen IOS SPECIFIC
     */
    public void swipeRight(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "right");
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: swipe", params);
    }

    /**
     * Swipe left on current mobile screen IOS SPECIFIC
     */
    public void swipeLeft(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "left");
        params.put("element", ((RemoteWebElement)element).getId());
        js.executeScript("mobile: swipe", params);
    }


    /**
     * IOS SPECIFIC
     *
     * @param picker
     *            locator for picker element
     * @param wheel
     *            locator for wheel inside picker
     * @param forwordDate
     *            No of days from today
     * @param hour
     *            value for hour
     * @param minutes
     *            value for minute
     * @param meridiem
     *            value for meridiem , AM/PM
     */
    public void dateTimePicker(By picker, By wheel, int forwordDate, String hour, String minutes, String meridiem) {
        List<WebElement> Columns = waitForExpectedElement(picker).findElements(wheel);
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> hp = new HashMap<String, Object>();
        hp.put("order", "next");
        hp.put("offset", 0.15);
        hp.put("element", Columns.get(0));
        for (int row = 0; row < forwordDate; row++)
            js.executeScript("mobile: selectPickerWheelValue", hp);

        Columns.get(3).sendKeys(meridiem);
        Columns.get(2).sendKeys(minutes);

        Columns.get(1).sendKeys(hour);
        logger.detail("Time picker value is  " + Columns.get(0).getAttribute("value") + " , "
                + Columns.get(1).getAttribute("value") + ":" + Columns.get(2).getAttribute("value") + " "
                + Columns.get(3).getAttribute("value"));

    }

    /**
     * Drag from one point to andother IOS SPECIFIC
     *
     * @param startx
     *            X coordinate for initial location of swipe
     * @param starty
     *            Y coordinate for initial location of swipe
     * @param endx
     *            X coordinate for end location of swipe
     * @param endy
     *            Y coordinate for end location of swipe
     * @param duration
     *            time duration in which swipe should be performed
     * @param element
     *            Reference element for all the coordinate
     */
    public void dragFromToForDuration(int startx, int starty, int endx, int endy, int duration, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("duration", duration);
        params.put("fromX", startx);
        params.put("fromY", starty);
        params.put("toX", endx);
        params.put("toY", endy);
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: dragFromToForDuration", params);

    }
    /**
     * Drag from one point to andother IOS SPECIFIC
     *
     * @param startx
     *            X coordinate for initial location of swipe
     * @param starty
     *            Y coordinate for initial location of swipe
     * @param endx
     *            X coordinate for end location of swipe
     * @param endy
     *            Y coordinate for end location of swipe
     * @param duration
     *            time duration in which swipe should be performed
     */
    public void dragFromToForDuration(int startx, int starty, int endx, int endy, int duration) {
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("duration", duration);
        params.put("fromX", startx);
        params.put("fromY", starty);
        params.put("toX", endx);
        params.put("toY", endy);
        js.executeScript("mobile: dragFromToForDuration", params);

    }
    /**
     * Hide keyboard from screen
     */
    public void hideKeyboard() {
        IOSElement element = (IOSElement) ((AppiumDriver) SetupManager.getDriver())
                .findElementByClassName("XCUIElementTypeKeyboard");
        Point keyboardPoint = element.getLocation();
        TouchAction touchAction = new TouchAction((AppiumDriver) SetupManager.getDriver());
        PointOption top = PointOption.point(keyboardPoint.getX() + 2, keyboardPoint.getY() - 2);

        touchAction.tap(top).perform();
        try {
            Thread.sleep(200);
            logger.detail(" Hidded Key board");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Wrapper for wait, clear data and sendKeys in Input Text box
     **/
    public void waitClearEnterText(WebElement element, String inputText) {

        element.clear();
        element.sendKeys(inputText);
        logger.detail("Entered Text " + inputText + " in " + element.toString() + "after clearing the field");

    }
    /**
     * Find the dynamic element wait until its visible
     *
     * @param by
     *            Element location found xpath, etc...
     **/
    protected WebElement waitForExpectedElement(final By by) {
        WebDriverWait wait =new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME);
        WebElement element = wait.until(visibilityOfElementLocated(by));
        // ScrollToElement(element);
        return element;
    }

    /**
     * An expectation for checking that an element is either invisible or not
     * present on the DOM.
     *
     * @param element
     *            used to find the element
     */
    public boolean invisibilityOfElementLocated(WebElement element) {
        try {
            return (new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME))
                    .until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * @param by
     *            Element location found by xpath etc...
     * @return
     * @throws NoSuchElementException
     */
    private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return driver -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
            WebElement element = SetupManager.getDriver().findElement(by);
            return element.isEnabled() ? element : null;
        };
    }
    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     *
     */
    public void textToBePresentInElementName(final WebElement element, final String text) {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(SetupManager.getDriver()).withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.attributeToBe(element, "name", text));
        } catch (TimeoutException e) {
            logger.detail("Wait failed");
        }
    }

    /**
     * Make Sure driver is set to ios/Andriod specific
     *
     * @param key
     * @return
     */
    public boolean verifyImageIsPresent(String key) {

        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 4);

        String imagePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),
                PropertyUtility.getImageLocations(key));
        IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) SetupManager.getDriver();
        driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, 0.8);
        driver.setSetting(Setting.FIX_IMAGE_FIND_SCREENSHOT_DIMENSIONS, false);
        try {
            File enroute = new File(imagePath);
            File refImgFile = Paths.get(enroute.toURI()).toFile();

            String base64Enroute = Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
            By enrouteBase64 = MobileBy.image(base64Enroute);
            wait.until(ExpectedConditions.presenceOfElementLocated(enrouteBase64));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
