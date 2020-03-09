package com.bungii.ios.manager;

import com.bungii.SetupManager;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.error;
import static io.appium.java_client.touch.offset.PointOption.point;

public class ActionManager {
    private static LogUtility logger = new LogUtility(ActionManager.class);
    private final long DRIVER_WAIT_TIME;

    public ActionManager() {
        DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));

    }
    private String getElementDetails(WebElement element)
    {
        return element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
    }
    public static void waitUntilIsElementExistsAndDisplayed(WebElement element) {
        try {
            IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until((ExpectedConditions.visibilityOf(element)));
        } catch (Exception Ex) {
            //  Assert.fail("Following element is not displayed : " + element);
        }
    }

    /**
     * @param element , locator of field
     * @param text    , Text value that is to be sent
     */
    public void sendKeys(WebElement element, String text) {
        try {
            element.sendKeys(text);
            logger.detail("Send  " + text + " in element -> " + getElementDetails(element));
        }
         catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to send " + text + " in element -> " + getElementDetails(element),
                    true);
        }
    }

    /**
     * @return boolean value according to alert existence
     */
    public boolean isAlertPresent() {
        try {
            Thread.sleep(1000);
            SetupManager.getDriver().switchTo().alert();
            String alertMessage = SetupManager.getDriver().switchTo().alert().getText();
            logger.detail("Alert is present :" + alertMessage);

            if (alertMessage.contains("no such alert"))
                return false;
            else
                return true;
        } catch (NoAlertPresentException | InterruptedException Ex) {
            logger.detail("Alert is not present");
            return false;
        } catch (Exception ex) {
            logger.error("Error occured " + ex);
            return false;
        }
    }

    public String getValueAttribute(WebElement element) {
        String value = element.getAttribute("value");
        logger.detail("'value' attribute for element -> " + getElementDetails(element)+ " is " + value);
        return value;
    }

    public String getNameAttribute(WebElement element) {
        String value = element.getAttribute("name");
        logger.detail("'name' attribute for element -> " + getElementDetails(element) + " is " + value);
        return value;
    }

    public void click(WebElement element) {
        try{
        element.click();
        logger.detail("Click on locator by element -> " + getElementDetails(element));

    }
         catch(Exception ex)
    {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step should be successful", "Unable to click on element -> " + getElementDetails(element),
                true);
    }
    }

    public void tapByElement(WebElement element) {
        AppiumDriver<WebElement> driver = (AppiumDriver<WebElement>) SetupManager.getDriver();
        int startX = element.getLocation().getX();
        int addition = (int) (element.getSize().height * 0.5);
        int endX = startX + addition;
        int startY = element.getLocation().getY();
        new TouchAction(driver).tap(point(endX, startY)).perform();
    }

    public void clickMiddlePoint(WebElement element) {
        Point elementLocation = element.getLocation();
        Dimension elementSize = element.getSize();

        int leftX = elementLocation.getX();
        int width = elementSize.getWidth();
        int upperY = elementLocation.getY();
        int hight = elementSize.getHeight();
        Point p = new Point(leftX + (width / 2), upperY + (hight / 2));
        click(p);
        logger.detail("Click on locator by element -> " + getElementDetails(element) + p);
    }

    public void waitForAlert() {
        (new WebDriverWait(SetupManager.getDriver(), 60)).until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Swipe up on current mobile screen IOS SPECIFIC
     */
    public void swipeUP() {
        try {

            JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
            Map<String, Object> params = new HashMap<>();
            params.put("direction", "up");
            js.executeScript("mobile: swipe", params);

        } catch (Exception ex) {
            logger.error("Error occured " + ex.getMessage());

        }
    }

    /**
     * Swipe up on current mobile screen IOS SPECIFIC
     */
    public void swipeDown() {
        try {


            JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
            Map<String, Object> params = new HashMap<>();
            params.put("direction", "down");
            js.executeScript("mobile: swipe", params);
        } catch (Exception ex) {
            logger.error("Error occured " + ex.getMessage());

        }
    }

    /**
     * Swipe up on current mobile screen IOS SPECIFIC
     */
    public void swipeRight(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "right");
        params.put("element", ((RemoteWebElement) element).getId());
        //     params.put("element", ((IOSElement) element).getId());
        js.executeScript("mobile: swipe", params);
    }

    public String getDeviceTimeZone() {
        String time2 = ((AppiumDriver) SetupManager.getDriver()).getDeviceTime();
        return time2;
    }

    /**
     * Swipe left on current mobile screen IOS SPECIFIC
     */
    public void swipeLeft(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "left");
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: swipe", params);
    }

    /**
     * IOS SPECIFIC
     *
     * @param picker      locator for picker element
     * @param wheel       locator for wheel inside picker
     * @param forwordDate No of days from today
     * @param hour        value for hour
     * @param minutes     value for minute
     * @param meridiem    value for meridiem , AM/PM
     */
    public void dateTimePicker(By picker, By wheel, int forwordDate, String hour, String minutes, String meridiem) {
        List<WebElement> Columns = waitForExpectedElement(picker).findElements(wheel);
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> hp = new HashMap<String, Object>();
        hp.put("order", "next");
        hp.put("offset", 0.15);
        hp.put("element", Columns.get(0));
        try{
        for (int row = 0; row < forwordDate; row++)
            js.executeScript("mobile: selectPickerWheelValue", hp);}catch (Exception e){}
        if(!meridiem.equals(""))
            Columns.get(3).sendKeys(meridiem);

        if(!minutes.equals(""))
            Columns.get(2).sendKeys(minutes);

        if(!hour.equals(""))
            Columns.get(1).sendKeys(hour);
        if(!meridiem.equals("")) {
            if (!Columns.get(3).getAttribute("value").equals(meridiem))
                Columns.get(3).sendKeys(meridiem);
        }
        logger.detail("Scheduled time  " + Columns.get(0).getAttribute("value") + " , "
                + Columns.get(1).getAttribute("value") + ":" + Columns.get(2).getAttribute("value") + " "
                + Columns.get(3).getAttribute("value"));

    }

    /**
     * Drag from one point to andother IOS SPECIFIC
     *
     * @param startx   X coordinate for initial location of swipe
     * @param starty   Y coordinate for initial location of swipe
     * @param endx     X coordinate for end location of swipe
     * @param endy     Y coordinate for end location of swipe
     * @param duration time duration in which swipe should be performed
     * @param element  Reference element for all the coordinate
     */
    public void dragFromToForDuration(int startx, int starty, int endx, int endy, int duration, WebElement element) {
        logger.detail("Slide started");

        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("duration", duration);
        params.put("fromX", startx);
        params.put("fromY", starty);
        params.put("toX", endx);
        params.put("toY", endy);
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: dragFromToForDuration", params);
        logger.detail("Slide ended");
    }

    /**
     * Drag from one point to andother IOS SPECIFIC
     *
     * @param startx   X coordinate for initial location of swipe
     * @param starty   Y coordinate for initial location of swipe
     * @param endx     X coordinate for end location of swipe
     * @param endy     Y coordinate for end location of swipe
     * @param duration time duration in which swipe should be performed
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
        try {
            //new method is updated appium versions
            ((AppiumDriver) SetupManager.getDriver()).hideKeyboard();
/*            IOSElement element = (IOSElement) ((AppiumDriver) SetupManager.getDriver())
                    .findElementByClassName("XCUIElementTypeKeyboard");
            Point keyboardPoint = element.getLocation();
            TouchAction touchAction = new TouchAction((AppiumDriver) SetupManager.getDriver());
            PointOption top = point(keyboardPoint.getX() + 2, keyboardPoint.getY() - 2);

            touchAction.tap(top).perform();

            Thread.sleep(200);*/

            logger.detail(" Hidded Key board");

        } catch (Exception e) {
            //  e.printStackTrace();
            logger.error(e.getStackTrace());
        }
    }

    /**
     * Hide keyboard from screen
     */
    public void nextFieldKeyboard() {
        try {
            IOSElement element = (IOSElement) ((AppiumDriver) SetupManager.getDriver())
                    .findElementByName("Next:");
            click(element);
            logger.detail(" Next Field Key board");

        } catch (Exception e) {
            //  e.printStackTrace();
            logger.error(e.getStackTrace());
        }
    }

    /**
     * Press button from keyboard from screen
     */
    public void pressFieldKeyboard(String keyName) {
        try {
            IOSElement element = (IOSElement) ((AppiumDriver) SetupManager.getDriver())
                    .findElementByName(keyName);
            click(element);
            logger.detail(keyName + " Field Key board");

        } catch (Exception e) {
            //  e.printStackTrace();
            logger.error(e.getStackTrace());
        }
    }

    public void click(Point p) {
        TouchAction touchAction = new TouchAction((AppiumDriver) SetupManager.getDriver());
        PointOption top = point(p.getX(), p.getY());

        touchAction.tap(top).perform();
    }

    /**
     * IOS SPECIFIC
     *
     * @param bundleId of application that needs to be activated
     */
    public void switchApplication(String bundleId) {
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();

        HashMap<String, Object> args = new HashMap<>();
        args.put("bundleId", bundleId);
        js.executeScript("mobile: launchApp", args);
    }

    public void terminateApp(String bundleId) {
        ((IOSDriver) SetupManager.getDriver()).terminateApp(bundleId);

    }

    /**
     * Wrapper for wait, clear data and clearSendKeys in Input Text box
     **/
    public void clearEnterText(WebElement element, String inputText) {
        try {
            element.clear();
        }catch (Exception e){}
        try {element.sendKeys(inputText);}catch (Exception e){    element.clear();element.sendKeys(inputText); }
        logger.detail("Entered Text " + inputText + " in element ->" + getElementDetails(element) + "after clearing the field");

    }

    /**
     * Find the dynamic element wait until its visible
     *
     * @param by Element location found xpath, etc...
     **/
    public WebElement waitForExpectedElement(final By by) {
        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME);
        WebElement element = wait.until(visibilityOfElementLocated(by));
        return element;
    }

    public void hardWaitWithSwipeUp(int minutes) throws InterruptedException {
        for (int i = minutes; i > 0; i--) {
            logger.detail("Inside Hard wait , wait for " + i + " minutes");
            Thread.sleep(30000);
            swipeDown();
            Thread.sleep(30000);

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
            return (new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME))
                    .until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * @param by Element location found by xpath etc...
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
     */
    public void textToBePresentInElementName(final WebElement element, final String text) {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(SetupManager.getDriver()).withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.attributeToBe(element, "name", text));
        } catch (Exception e) {
            logger.detail("Wait failed");
        }
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
        PointOption top = point(xMid, yMargin);
        PointOption bottom = point(xMid, screenSize.height - yMargin);

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

    /**
     * Make Sure driver is set to ios/Andriod specific
     *
     * @param key
     * @return
     */
    public boolean verifyImageIsPresent(String key) {
        WebDriverWait driverWait = new WebDriverWait(SetupManager.getDriver(), 4);

        String imagePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),
                PropertyUtility.getImageLocations(key));
        IOSDriver<MobileElement> driver;
        driver = (IOSDriver<MobileElement>) SetupManager.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, 0.26);
        driver.setSetting(Setting.FIX_IMAGE_FIND_SCREENSHOT_DIMENSIONS, false);
        try {
            File enroute = new File(imagePath);
            File refImgFile = Paths.get(enroute.toURI()).toFile();

            String base64Enroute = Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
            By enrouteBase64 = MobileBy.image(base64Enroute);
            driverWait.until(ExpectedConditions.presenceOfElementLocated(enrouteBase64));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getListOfAlertButton() {
        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.alertIsPresent());
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();

        HashMap<String, String> params = new HashMap<>();
        params.put("action", "getButtons");
        List<String> buttons = (List<String>) js.executeScript("mobile: alert", params);
        logger.detail("List of alert button:" + buttons.toString());
        return buttons;
    }

    public boolean clickAlertButton(String label) {
        List<String> buttons = getListOfAlertButton();

        String buttonLabel = "";
        for (String button : buttons) {
            if (button.equalsIgnoreCase(label)) {
                buttonLabel = button;
                break;
            }
        }
        if (buttonLabel.equals(""))
            return false;
        else {
            HashMap<String, String> params = new HashMap<>();
            JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();

            params.put("action", "accept");
            params.put("buttonLabel", buttonLabel);
            js.executeScript("mobile: alert", params);

            return true;
        }
    }

    /**
     * @return value of alert message
     */
    public String getAlertMessage() {
        if (isAlertPresent()) {
            return SetupManager.getDriver().switchTo().alert().getText();
        } else {
            return "";
        }
    }

    public Rectangle getLocatorRectangle(WebElement element) {

        // MobileElement element = (MobileElement) waitForExpectedElement(by);
        Point elementLocation = element.getLocation();
        Dimension elementSize = element.getSize();
        int leftX = elementLocation.getX();
        int width = leftX + elementSize.getWidth();
        int upperY = elementLocation.getY();
        int hight = upperY + elementSize.getHeight();
        Rectangle area = new Rectangle(leftX, upperY, width, hight);
        return area;

    }

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

    public void selectElementByText(WebElement element, String text)
    {
        try{
            new Select(element).selectByVisibleText(text);
            logger.detail("Select "+text+" in element -> " + getElementDetails(element));

        }
        catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to Select "+text +" in element -> " + getElementDetails(element),
                    true);
        }
    }

}
