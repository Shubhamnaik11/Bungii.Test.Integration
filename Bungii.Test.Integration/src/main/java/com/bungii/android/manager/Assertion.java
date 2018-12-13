package com.bungii.android.manager;

import com.bungii.common.manager.AssertManager;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.DriverAction;

public class Assertion extends AssertManager {

//    public static void ElementTextEqual(WebElement element, String value)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        isEquals(value,  element.getText(), "Stepname","Values do not match");
//    }
//
//    public static void PhoneNumbersEqual(WebElement element, String value)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.AreEqual(value, element.getText().Replace(" ","").Replace("-","").Replace(",",""), "Values do not match");
//    }
//
//    public static void ElementTextContains(WebElement element, String value)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.IsTrue(value.contains(element.getText()), "Element does not contain required text");
//    }
//
//    public static void SnackbarTextEqual(WebElement element, String value)
//    {
//        ActionManager.waitUntilSnackbarExistsAndDisplayed(element);
//        Assert.AreEqual(value, element.getText(), "Values do not match");
//    }
//
//    public static void CompareStrings(String value1, String value2)
//    {
//        Assert.AreEqual(value1, value2, "Values do not match");
//    }
//
//    public static void StringContainsText(String MainString, String SubString)
//    {
//        Assert.IsTrue(MainString.contains(SubString), "Values do not match");
//    }
//
//    public static void ElementValueEquals(WebElement element, String value)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.AreEqual(value, element.getAttribute("value"), "Values do not match");
//    }
//
//    public static void ElementValueNotEquasl(IWebElement element, String value)
//    {
//        ActionManager.WaitUntilIsElementExistsAndDisplayed(element);
//        Assert.AreNotEqual(value, element.getText(), "Values were equal");
//    }
//
//    public static void ElementPresent(WebElement element)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.IsTrue(element.isDisplayed(), "Element was not displayed");
//    }
//
//    public static void ElementEnabled(WebElement element)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.IsTrue(element.isEnabled(), "Element was disabled");
//    }
//
//    public static void ElementChecked(WebElement element)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.IsTrue(element.isSelected(), "Element was not checked");
//    }
//
//    public static void ElementUnChecked(WebElement element)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.IsFalse(element.isSelected(), "Element was checked");
//    }
//
//    public static void ElementDisabled(WebElement element)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.IsFalse(element.isEnabled(), "Element was enabled");
//    }
//
//    public static void ElementDisplayed(WebElement element)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.IsTrue(element.isDisplayed(), "Element was not displayed");
//    }
//
//    public static void IsAlphanumeric(bool value)
//    {
//        Assert.IsTrue(value, "Non alphanumeric value displayed");
//    }
//
//    public static void ElementNotDisplayed(WebElement element)
//    {
//        try
//        {
//            WebElement elementpresent = element;
//            Assert.Fail("Element was displayed");
//        }
//        catch
//        {
//        }
//    }
//
//    public static void CompareMaxLength(WebElement element, String value)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.AreEqual(value, element.getAttribute("maxlength"), "Values do not match");
//    }
//
//    public static void ElementSelected(WebElement element)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.IsTrue(element.isSelected(), "Element was not selected");
//    }
//
//    public static void ElementNotSelected(WebElement element)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.IsFalse(element.isSelected(), "Element was selected");
//    }
//
//    public static void ElementNotEmpty(WebElement element)
//    {
//        ActionManager.waitUntilIsElementExistsAndDisplayed(element);
//        Assert.IsNotNull(element.isSelected(), "Element does not contain any value");
//    }
//



}
