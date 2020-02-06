package com.bungii.web.manager;

import com.bungii.SetupManager;
import com.bungii.common.enums.ResultType;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.manager.ResultManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Random;

import static com.bungii.common.manager.ResultManager.error;

public class ActionManager {
    private static LogUtility logger = new LogUtility(ActionManager.class);
    private long DRIVER_WAIT_TIME;


    /**
     * @param element , locator of field
     * @param text    , Text value that is to be sent
     */
    public void clearSendKeys(WebElement element, String text) {
        try {
            new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOf(element));
            Thread.sleep(2000);
            element.clear();
            element.sendKeys(text);
            logger.detail("Send  " + text + " in element" + element.toString());
        }
        catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step, Please check logs for more details",
                    true);
        }
    }
    /**
     * @param element , locator of field
     * @param text    , Text value that is to be sent
     */
    public void sendKeys(WebElement element, String text) {
        try {
            //new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.(element));
            element.sendKeys(text);
            logger.detail("Send  " + text + " in element" + element.toString());
        }
          catch(Exception ex)
            {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
                error("Step should be successful", "Error performing step, Please check logs for more details",
                        true);
            }
    }
    public void clear(WebElement element) {
        try {
            logger.detail("Clear  element" + element.toString());
            new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
    }  catch(Exception ex)
    {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step should be successful", "Error performing step, Please check logs for more details",
                true);
    }
    }

    public String getText(WebElement element) {
        try {
         Long  DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));
         new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        logger.detail("text Value is  " + text + " for element" + element.toString());

        return text;
        }  catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step, Please check logs for more details",
                    true);
            return null;
        }
    }
    /**
     * @param element ,locator that is to be clicked
     */
    public void click(WebElement element) {
        DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));
        try {
        new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch (StaleElementReferenceException ex){
            //Retry
            try {
            Thread.sleep(2000);
                   element.click();
            }
            catch (Exception ex1) {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(ex1));
                error("Step should be successful", "Error performing step, Please check logs for more details",
                        true);

            }
            }

         catch(WebDriverException ex) {
                        //Chrome Retry if unable to click because of overlapping (Chrome NativeEvents is always on (Clicks via Co-ordinates))
                        JavaScriptClick(element);
            }
        logger.detail("Click on locator by locator" + element.toString());
    }
    public void JavaScriptClick(WebElement element) {
        try{
            JavascriptExecutor executor = (JavascriptExecutor) SetupManager.getDriver();
            executor.executeScript("arguments[0].click();", element);
        logger.detail(" JS Click on locator by locator" + element.toString());

    }  catch(Exception ex)
    {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step should be successful", "Error performing step, Please check logs for more details",
                true);
    }
    }
    public void navigateToPreviousPageUsingBrowserBackButton() {
        SetupManager.getDriver().navigate().back();
    }
    //Select Random Dropdown value
    Random random = new Random();
    public void selectRandomDropdown(WebElement DropdownField)
    {
        try{
        Select  s = new Select(DropdownField);
        int itemCount = s.getOptions().size(); // get the count of elements in ddlWebElement
         int randomnumber= random.nextInt( itemCount-1);
        s.selectByIndex(randomnumber==0 ? (randomnumber+1 <= itemCount ? randomnumber+1 : randomnumber ) : randomnumber );
        logger.detail(" Selected Random "+randomnumber+" from Dropdown: " + DropdownField.toString());
    }  catch(Exception ex)
    {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step should be successful", "Error performing step, Please check logs for more details",
                true);
    }
    }

    public void navigateTo(String url) {
        SetupManager.getDriver().navigate().to(url);
    }
    public void refreshPage() {
        SetupManager.getDriver().navigate().refresh();
    }
    public static void selectElementByText(WebElement element, String text)
    { try{
        Long DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));
        new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.elementToBeSelected(element));
        new Select(element).selectByVisibleText(text);
    }  catch(Exception ex)
    {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step should be successful", "Error performing step, Please check logs for more details",
                true);
    }
    }
    public static String getFirstSelectedOption(WebElement element)
    {
      return new Select(element).getFirstSelectedOption().getText();
    }
    public  void deleteAllCookies()
    {
        SetupManager.getDriver().manage().deleteAllCookies();
    }

    public boolean invisibilityOfElementLocated(WebElement element){
try {
    return new WebDriverWait(DriverManager.getObject().getDriver(), Long.parseLong(PropertyUtility.getProp("WaitTime"))).until(ExpectedConditions.invisibilityOf(element));
}
catch(Exception ex)
{
    return true;
}

    }

    public void waitUntilIsElementExistsAndDisplayed(WebElement element, Long waitTime) {
        try {
            WebDriver driver = SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until((ExpectedConditions.visibilityOf(element)));
        } catch (Exception ex) {
            Assert.fail("Following element is not displayed : " + element);
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step, Please check logs for more details",
                    true);
        }
    }
}
