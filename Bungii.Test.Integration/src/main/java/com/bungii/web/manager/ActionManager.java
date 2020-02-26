package com.bungii.web.manager;

import com.bungii.SetupManager;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Random;
import java.util.function.Predicate;

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
            error("Step should be successful", "Unable to send " + text + " in element " + element.toString(),
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
                error("Step should be successful", "Unable to send " + text + " in element " + element.toString(),
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
        error("Step should be successful", "Unable to clear element " + element.toString(),
                true);
    }
    }
    public boolean waitForJStoLoad() {

        WebDriverWait wait = new WebDriverWait(DriverManager.getObject().getDriver(), 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
    public String getText(WebElement element) {
        try {
         Long  DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));
         Thread.sleep(3000);
       //  new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME).until((JavascriptExecutor)DriverManager.getObject().getDriver()).executeScript("return document.readyState").equals("complete") }
            waitForJStoLoad();
        // new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        logger.detail("Text Value is  " + text + " for element " + element.toString());

        return text;
        }
        catch(StaleElementReferenceException ex)
        {
            String text = element.getText();
            logger.detail("Text Value is  " + text + " for element " + element.toString());
            return text;
        }
        catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to get text from element " + element.toString() ,
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
                error("Step should be successful", "Unable to click on element " + element.toString() ,
                        true);

            }
            }

         catch(WebDriverException ex) {
                        //Chrome Retry if unable to click because of overlapping (Chrome NativeEvents is always on (Clicks via Co-ordinates))
                        JavaScriptClick(element);
            }
        logger.detail("Click on element by locator" + element.toString());
    }
    public void JavaScriptClick(WebElement element) {
        try{
            JavascriptExecutor executor = (JavascriptExecutor) SetupManager.getDriver();
            executor.executeScript("arguments[0].click();", element);
        logger.detail(" JS Click on element by locator" + element.toString());

    }  catch(Exception ex)
    {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step should be successful", "Unable to click on element " + element.toString() ,
                true);
    }
    }

    public void JavaScriptClear(WebElement element) {
        try{
            JavascriptExecutor executor = (JavascriptExecutor) SetupManager.getDriver();
            executor.executeScript("arguments[0].value = '';", element);
            logger.detail(" JS Clear on locator" + element.toString());

        }  catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to clear element " + element.toString() ,
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
        error("Step should be successful", "Unable to select Random value from dropdown element " + DropdownField.toString() ,
                true);
    }
    }

    public void navigateTo(String url) {
        SetupManager.getDriver().navigate().to(url);
    }
    public void refreshPage() {
        SetupManager.getDriver().navigate().refresh();
    }

    public String getCurrentURL() {
        String s = SetupManager.getDriver().getCurrentUrl();
        return s;
    }

    public static void selectElementByText(WebElement element, String text)
    { try{
        Long DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));
        new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.elementToBeClickable(element));
        new Select(element).selectByVisibleText(text);
    }  catch(Exception ex)
    {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step should be successful", "Unable to select element by text of " + element.toString() +" | Text: "+ text ,
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
            error("Step should be successful", "Following element is not displayed : " + element.toString(),
                    true);
        }
    }

    public Boolean waitForElement(String xpath)
    {
        int retrycount =10;
        boolean retry = true;
        boolean isElementPresent = false;
        while (retry == true && retrycount >0) {
            try {
                WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                retry = false;
                isElementPresent = true;
            } catch (Exception ex) {
                SetupManager.getDriver().navigate().refresh();
                retrycount--;
                retry = true;
            }
        }
        return isElementPresent;
    }

}
