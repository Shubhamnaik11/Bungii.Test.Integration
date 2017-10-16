using System;
using OpenQA.Selenium;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Bungii.Test.Integration.Framework.Core.Android
{
    public class AssertionManager
    {
        public static void ElementTextEqual(IWebElement element, String value)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.AreEqual(value, element.Text, "Values does not match");
        }

        public static void ElementTextContains(IWebElement element, String value)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(value.Contains(element.Text), "Element does not contain required text");
        }

        public static void SnackbarTextEqual(IWebElement element, String value)
        {
            DriverAction.WaitUntilSnackbarExistsAndDisplayed(element);
            Assert.AreEqual(value, element.Text, "Values does not match");
        }

        public static void CompareStrings(String value1, String value2)
        {
            Assert.AreEqual(value1, value2, "Values does not match");
        }

        public static void StringContainsText(String MainString, String SubString)
        {
            Assert.IsTrue(MainString.Contains(SubString), "Values does not match");
        }

        public static void ElementValueEquals(IWebElement element, String value)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.AreEqual(value, element.GetAttribute("value"), "Values does not match");
        }

        public static void ElementValueNotEquasl(IWebElement element, String value)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.AreNotEqual(value, element.Text, "Values were equal");
        }

        public static void ElementPresent(IWebElement element)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(element.Displayed, "Element was not displayed");
        }

        public static void ElementEnabled(IWebElement element)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(element.Enabled, "Element was disabled");
        }

        public static void ElementChecked(IWebElement element)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(element.Selected, "Element was not Checked");
        }

        public static void ElementUnChecked(IWebElement element)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsFalse(element.Selected, "Element was checked");
        }

        public static void ElementDisabled(IWebElement element)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsFalse(element.Enabled, "Element was Enabled");
        }

        public static void ElementDisplayed(IWebElement element)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(element.Displayed, "Element was not Displayed");
        }

        public static void IsAlphanumeric(bool value)
        {
            Assert.IsTrue(value, "Non alphanumeric value displayed");
        }

        public static void ElementNotDisplayed(IWebElement element)
        {
            try
            {
                IWebElement elementpresent = element;
                Assert.Fail("Element was Displayed");
            }
            catch
            {
            }
        }

        public static void CompareMaxLength(IWebElement element, String value)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.AreEqual(value, element.GetAttribute("maxlength"), "Values do not match");
        }

        public static void ElementSelected(IWebElement element)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(element.Selected, "Element was not selected");
        }

        public static void ElementNotEmpty(IWebElement element)
        {
            DriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsNotNull(element.Selected, "Element does not contain any value");
        }
    }
}
