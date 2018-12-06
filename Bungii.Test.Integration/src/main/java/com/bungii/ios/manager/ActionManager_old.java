package com.bungii.ios.manager;


import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.ParseUtility;
import com.bungii.common.utilities.PropertyUtility;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;








//This is Old Action com.bungii.android.manager for reference , Will remove this once relevant functions are moved to actual actionmanager

















/**
 * @author vishal.bagi
 * 
 *         All function related to appium will go over here Abtract class
 */
public abstract class ActionManager_old {
	private static long DRIVER_WAIT_TIME;
	private static LogUtility logger = new LogUtility(ActionManager.class);

	protected WebDriverWait wait;
	protected WebDriver appiumDriver;

	/**
	 * Constructor that will assign driver get driver instance and store it. IOS
	 * helper will create instance if it is first time class is called. Else it
	 * will return store instance.
	 */
	public ActionManager_old() {
		this.appiumDriver = DriverManager.getObject().getDriver();
		DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));
		this.wait = new WebDriverWait(appiumDriver, DRIVER_WAIT_TIME);
	//	PageFactory.initElements(DriverHelper.getAppiumDriver(), this);


	}

	/**
	 * @return driver instance
	 */
	public WebDriver getDriver() {
		// return this.appiumDriver;
		return  DriverManager.getObject().getDriver();
	}

	/**
	 * @param driver
	 *            instance that is to be set
	 */
	public void setDriver(WebDriver driver) {
		DriverManager.getObject().setDriver(driver);
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
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
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
	 * Show notification
	 */
	protected void showNotifications() {
		manageNotifications(true);
	}

	/**
	 * Hide notification
	 */
	protected void hideNotifications() {
		manageNotifications(false);
	}

	/**
	 * Manage notification
	 * @param show
	 */
	private void manageNotifications(Boolean show) {

		Dimension screenSize = getDriver().manage().window().getSize();
		int yMargin = 5;
		int xMid = screenSize.width / 2;
		PointOption top = PointOption.point(xMid, yMargin);
		PointOption bottom = PointOption.point(xMid, screenSize.height - yMargin);

		TouchAction action = new TouchAction((AppiumDriver) getDriver());
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
	 * @param locator
	 *            , locator of field
	 * @param text
	 *            , Text value that is to be sent
	 */
	public void sendKeys(By locator, String text) {
		getDriver().findElement(locator).sendKeys(text);
		logger.detail("Send  " + text + " in locator" + locator.toString());
	}

	/**
	 * @return boolean value according to alert existence
	 */
	public boolean isAlertPresent() {
		try {
			Thread.sleep(1000);
			getDriver().switchTo().alert();
			logger.detail("Alert is present");
			return true;
		} catch (NoAlertPresentException | InterruptedException Ex) {
			logger.detail("Alert is not present");
			return false;
		}
	}

	/**
	 * Make Sure driver is set to ios/Andriod specific
	 * 
	 * @param key
	 * @return
	 */
	public boolean verifyImageIsPresent(String key) {
		
		WebDriverWait wait = new WebDriverWait(appiumDriver, 4);

		String imagePath = FileUtility.getSuiteResource(PropertyUtility.getProp("image.folder"),
				PropertyUtility.getImageLocations(key));
		IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) getDriver();
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

	/**
	 * @param screenshotPath
	 *            Folder Path in which screenshot will be captured
	 * @return absolute path of screenshot
	 * @throws IOException
	 */
	public String screenshot(String screenshotPath) throws IOException {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String filename = UUID.randomUUID().toString();
		File targetFile = new File(screenshotPath + filename + ".jpg");

		FileUtils.copyFile(srcFile, targetFile);
		return targetFile.getPath();
	}

	/**
	 * @return value of alert message
	 */
	public String getAlertMessage() {
		if (isAlertPresent()) {
			return getDriver().switchTo().alert().getText();
		} else {
			return "";
		}
	}

	/**
	 * @param locator
	 *            ,locator that is to be clicked
	 */
	public void 	click(By locator) {
		waitForExpectedElement(locator).click();
		logger.detail("Click on locator by locator" + locator.toString());
	}

	/**
	 * @param locator
	 *            ,locator whose text is to extracted
	 */
	public String getText(By locator) {
		String elementText = waitForExpectedElement(locator).getText();
		logger.detail("text of locator " + locator.toString() + " is " + elementText);
		return elementText;
	}

	public void click(WebElement element) {
		element.click();
		logger.detail("Click on locator by element" + element.toString());
	}

	/**
	 * @param locator
	 *            ,locator that is to be double tab
	 */
	public void doubleTab(By locator) {
		iosDoubleTab(waitForExpectedElement(locator));
		logger.detail("double tab on locator " + locator.toString());
	}

	/**
	 * IOS SPECIFIC
	 * 
	 * @param element
	 *            ,element that is to be double tab
	 */
	private void iosDoubleTab(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		Map<String, Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: doubleTap", params);
	}

	/**
	 * Swipe up on current mobile screen IOS SPECIFIC
	 */
	public void swipeUP() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "up");
		js.executeScript("mobile: swipe", params);

	}

	/**
	 * Swipe up on current mobile screen IOS SPECIFIC
	 */
	public void swipeDown() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "down");
		js.executeScript("mobile: swipe", params);
	}

	/**
	 * Swipe left on current mobile screen IOS SPECIFIC
	 */
	public void swipeLeft(By by) {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "left");
		params.put("element", ((RemoteWebElement) element(by)).getId());
		js.executeScript("mobile: swipe", params);
	}
	
	/**
	 * Swipe left on current mobile screen IOS SPECIFIC
	 */
	public void swipeLeft(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "left");
		params.put("element", ((RemoteWebElement)element).getId());
		js.executeScript("mobile: swipe", params);
	}
	
	
	/**
	 * Swipe up on current mobile screen IOS SPECIFIC
	 */
	public void swipeRight(By by) {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "right");
		params.put("element", ((RemoteWebElement) element(by)).getId());
		js.executeScript("mobile: swipe", params);
	}
	
	/**
	 * Swipe up on current mobile screen IOS SPECIFIC
	 */
	public void swipeRight(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "right");
		params.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: swipe", params);
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
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
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
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		Map<String, Object> params = new HashMap<>();
		params.put("duration", duration);
		params.put("fromX", startx);
		params.put("fromY", starty);
		params.put("toX", endx);
		params.put("toY", endy);
		js.executeScript("mobile: dragFromToForDuration", params);

	}

	/**
	 * Get upper left coordinate of locator
	 * 
	 * @param by
	 *            Locator of which coordinate are required
	 * @return point of upper left corner
	 */
	public Point getElementInitialPoint(By by) {
		/*
		 * int leftX = element.getLocation().getX(); int rightX = leftX +
		 * element.getSize().getWidth(); int middleX = (rightX + leftX) / 2; int
		 * upperY = element.getLocation().getY(); int lowerY = upperY +
		 * element.getSize().getHeight(); int middleY = (upperY + lowerY) / 2;
		 */
		return waitForExpectedElement(by).getLocation();
	}

	public Rectangle getLocatorRectangle(By by) {

		MobileElement element = (MobileElement) waitForExpectedElement(by);
		int leftX = element.getLocation().getX();
		int width = leftX + element.getSize().getWidth();
		int upperY = element.getLocation().getY();
		int hight = upperY + element.getSize().getHeight();
		Rectangle area = new Rectangle(leftX, upperY, width, hight);
		return area;

	}

	/**
	 * method to get all the context handles at particular screen,native,webview
	 * or hybrid
	 * 
	 * @return return all the context on current screen
	 * 
	 */
	public Set<String> getContext() {
		return ((AppiumDriver<MobileElement>) getDriver()).getContextHandles();
	}

	/**
	 * method to set the context to required view.
	 * 
	 * @param context
	 *            view to be set
	 */
	public void setContext(String context) {

		Set<String> contextNames = ((AppiumDriver<MobileElement>) getDriver()).getContextHandles();

		if (contextNames.contains(context)) {
			((AppiumDriver<MobileElement>) getDriver()).context(context);
			logger.detail("Context changed successfully");
		} else {
			logger.detail(context + "not found on this page");
		}

		logger.detail("Current context" + ((AppiumDriver<MobileElement>) getDriver()).getContext());
	}

	/**
	 * IOS SPECIFIC
	 * 
	 * @param bundleId
	 *            of application that needs to be activated
	 */
	public void switchApplication(String bundleId) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		HashMap<String, Object> args = new HashMap<>();
		args.put("bundleId", bundleId);
		js.executeScript("mobile: launchApp", args);
	}

	/**
	 * Find the dynamic element wait until its visible
	 *
	 * @param by
	 *            Element location found xpath, etc...
	 **/
	protected WebElement waitForExpectedElement(final By by) {
		WebElement element = wait.until(visibilityOfElementLocated(by));
		// ScrollToElement(element);
		return element;
	}

	/**
	 * @param by
	 *            element locator
	 * @return value of name attribute
	 */
	protected String getNameAttribute(By by) {
		String name = waitForExpectedElement(by).getAttribute("name");
		logger.detail("'Name' attribute for  " + by.toString() + " is " + name);
		return name;
	}

	/**
	 * @param element
	 *            element locator
	 * @return value of name attribute
	 */
	protected String getNameAttribute(WebElement element) {
		String name = element.getAttribute("name");
		logger.detail("'Name' attribute for  " + element.toString() + " is " + name);
		return name;
	}
	/**
	 * Find value attribute of webelement
	 * 
	 * @param by
	 *            element locator
	 * @return value of name attribute
	 */
	protected String getValueAttribute(By by) {
		String name = waitForExpectedElement(by).getAttribute("value");
		logger.detail("'value' attribute for " + by.toString() + " is " + name);
		return name;
	}

	protected String getValueAttribute(WebElement element) {
		String value = element.getAttribute("value");
		logger.detail("'value' attribute for " + element.toString() + " is " + value);
		return value;
	}

	/**
	 * Hide keyboard from screen
	 */
	public void hideKeyboard() {
		/*
		 * // TODO: check why it is not working //
		 * ((AppiumDriver<MobileElement>) getDriver()).hideKeyboard(); //
		 * ((AppiumDriver<MobileElement>) //
		 * getDriver()).hideKeyboard(HideKeyboardStrategy.TAP_OUTSIDE); //
		 * ((AppiumDriver<MobileElement>) //
		 * getDriver()).findElementByAccessibilityId("Hide keyboard").click();
		 */ IOSElement element = (IOSElement) ((AppiumDriver) getDriver())
				.findElementByClassName("XCUIElementTypeKeyboard");
		Point keyboardPoint = element.getLocation();
		TouchAction touchAction = new TouchAction((AppiumDriver) getDriver());
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
	 * Scroll to element ,IOS SPECIFIC
	 * 
	 * @param element
	 *            Element locator to which is should be scrolled
	 */
	private void ScrollToWebElement(WebElement element) {
		// TODO: Sometime it clicks element , need to check this
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			Map<String, Object> params = new HashMap<>();
			params.put("direction", "up");
			params.put("toVisible", "true");
			params.put("element", ((RemoteWebElement) element).getId());
			js.executeScript("mobile:scroll", params);
		} catch (Exception e) {
			logger.error("Not able to scroll to element " + e.getMessage());
		}
	}

	/**
	 * Find the dynamic element wait until its visible for a specified time
	 *
	 * @param by
	 *            Element location found by xpath etc...
	 * @param waitTimeInSeconds
	 *            max time to wait until element is visible
	 **/

	public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), waitTimeInSeconds);
			return wait.until(visibilityOfElementLocated(by));
		} catch (NoSuchElementException e) {
			logger.error(e.getMessage());
			return null;
		} catch (TimeoutException e) {
			logger.error(e.getMessage());
			return null;
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
			WebElement element = getDriver().findElement(by);
			return element.isEnabled() ? element : null;
		};
	}

	/**
	 * An expectation for checking if the given text is present in the element
	 * that matches the given locator.
	 */
	public boolean textToBePresentInElementLocated(final By by, final String text) {
		return new WebDriverWait(getDriver(), DRIVER_WAIT_TIME)
				.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
	}

	/**
	 * An expectation for checking if the given text is present in the specified
	 * elements value attribute.
	 */
	public boolean textToBePresentInElementValue(final WebElement element, final String text) {
		return new WebDriverWait(getDriver(), DRIVER_WAIT_TIME)
				.until(ExpectedConditions.textToBePresentInElementValue(element, text));
	}

	/**
	 * An expectation for checking if the given text is present in the specified
	 * element.
	 *
	 */
	public boolean textToBePresentInElement(WebElement element, String text) {
		return new WebDriverWait(getDriver(), DRIVER_WAIT_TIME)
				.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	/**
	 * An expectation for checking if the given text is present in the specified
	 * elements value attribute.
	 *
	 */
	public boolean textToBePresentInElementValue(final By by, final String text) {
		return new WebDriverWait(getDriver(), DRIVER_WAIT_TIME)
				.until(ExpectedConditions.textToBePresentInElementValue(by, text));
	}

	/**
	 * An expectation for checking if the given text is present in the specified
	 * elements value attribute.
	 *
	 */
	public void textToBePresentInElementName(final By by, final String text) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(DRIVER_WAIT_TIME, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		try {
			wait.until(ExpectedConditions.attributeToBe(by, "name", text));
		} catch (TimeoutException e) {
			logger.detail("Wait failed");
		}
		// return new WebDriverWait(getDriver(),
		// DRIVER_WAIT_TIME).until(ExpectedConditions.attributeToBe(by, "name",
		// text));
	}

	/**
	 * An expectation for checking that an element is either invisible or not
	 * present on the DOM.
	 *
	 * @param by
	 *            used to find the element
	 */
	public boolean invisibilityOfElementLocated(By by) {
		try {
			return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME))
					.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * An expectation for checking that an element with text is either invisible
	 * or not present on the DOM.
	 *
	 */
	public boolean invisibilityOfElementWithText(final By by, final String text) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME))
				.until(ExpectedConditions.invisibilityOfElementWithText(by, text));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that
	 * you can click it.
	 *
	 */
	public WebElement elementToBeClickable(By by) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that
	 * you can click it.
	 */

	public WebElement elementToBeClickable(final WebElement element) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME))
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait until an element is no longer attached to the DOM.
	 *
	 * @param element
	 *            The element to wait for.
	 * @return false is the element is still attached to the DOM, true
	 *         otherwise.
	 */
	public boolean stalenessOf(final WebElement element) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.stalenessOf(element));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public boolean elementToBeSelected(final By by) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.elementToBeSelected(by));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public boolean elementToBeSelected(final WebElement element) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME))
				.until(ExpectedConditions.elementToBeSelected(element));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public boolean elementSelectionStateToBe(final WebElement element, final boolean selected) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME))
				.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 */
	public boolean elementSelectionStateToBe(final By by, final boolean selected) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME))
				.until(ExpectedConditions.elementSelectionStateToBe(by, selected));
	}

	public void waitForAlert() {
		(new WebDriverWait(getDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.alertIsPresent());
	}

	public void waitForPageLoad() {
		new WebDriverWait(getDriver(), DRIVER_WAIT_TIME).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * An expectation for checking that all elements present on the com.bungii.web.web page
	 * that match the locator are visible. Visibility means that the elements
	 * are not only displayed but also have a height and width that is greater
	 * than 0.
	 *
	 */
	public List<WebElement> visibilityOfAllElementsLocatedBy(final By by) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	/**
	 * An expectation for checking that all elements present on the com.bungii.web.web page
	 * that match the locator are visible. Visibility means that the elements
	 * are not only displayed but also have a height and width that is greater
	 * than 0.
	 *
	 * @param elements
	 *            list of WebElements
	 * @return the list of WebElements once they are located
	 */
	public List<WebElement> visibilityOfAllElements(final List<WebElement> elements) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME))
				.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	/**
	 * An expectation for checking that there is at least one element present on
	 * a com.bungii.web.web page.
	 *
	 * @param by
	 *            used to find the element
	 * @return the list of WebElements once they are located
	 */
	public List<WebElement> presenceOfAllElementsLocatedBy(final By by) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}

	/**
	 * An expectation for checking that an element, known to be present on the
	 * DOM of a page, is visible. Visibility means that the element is not only
	 * displayed but also has a height and width that is greater than 0.
	 */

	public WebElement visibilityOf(final WebElement element) {
		return (new WebDriverWait(getDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a
	 * page. This does not necessarily mean that the element is visible.
	 *
	 */
	public boolean isElementPresent(final By by) {
		try {

			new WebDriverWait(getDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException exception) {
			// LOG.info(exception.getMessage());
			return false;
		}
		return true;
	}

	public boolean isElementEnabled(final By by) {
		boolean isPresent = isElementPresent(by);

		if (!isPresent)
			return isPresent;

		try {
			if (waitForExpectedElement(by).isEnabled())// .getAttribute("enabled").equals("true"))
				return true;
			else
				return false;

		} catch (TimeoutException exception) {
			return false;
		}

	}

	public boolean isElementEnabled(final WebElement element) {
		boolean isPresent ;

		try {

		return element.isEnabled();
		} catch (TimeoutException exception) {
			return false;
		}

	}
	/**
	 * Wrapper for driver.findElement
	 *
	 **/
	protected WebElement element(final By by) {
		return getDriver().findElement(by);
	}

	/**
	 * Wrapper for driver.findElements
	 *
	 **/
	protected List<WebElement> elements(final By by) {
		return getDriver().findElements(by);
	}

	/**
	 * Wrapper for clear data and sendKeys in Input Text box
	 *
	 **/

	protected void clearEnterText(By by, String inputText) {
		element(by).clear();
		element(by).sendKeys(inputText);
		logger.detail("Entered Text " + inputText + " in " + by.toString() + "after clearing the field");

	}

	/**
	 * Wrapper for wait, clear data and sendKeys in Input Text box
	 **/
	protected void waitClearEnterText(By by, String inputText) {

		WebElement element = waitForExpectedElement(by);
		element.clear();
		element.sendKeys(inputText);
		logger.detail("Entered Text " + inputText + " in " + by.toString() + "after clearing the field");

	}

	protected void hardWaitWithSwipeUp(int minutes) throws InterruptedException {
		for (int i = minutes; i > 0; i--) {
			logger.detail("Inside Hard wait , wait for " + i + " minutes");
			Thread.sleep(30000);
			swipeDown();
			Thread.sleep(30000);

		}
	}

	protected List<String> getListOfAlertButton() {

		wait.until(ExpectedConditions.alertIsPresent());
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		HashMap<String, String> params = new HashMap<>();
		params.put("action", "getButtons");
		List<String> buttons = (List<String>) js.executeScript("mobile: alert", params);
		return buttons;
	}

	protected boolean clickAlertButton(String label) {
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
			JavascriptExecutor js = (JavascriptExecutor) getDriver();

			params.put("action", "accept");
			params.put("buttonLabel", buttonLabel);
			js.executeScript("mobile: alert", params);

			return true;
		}
	}

}
