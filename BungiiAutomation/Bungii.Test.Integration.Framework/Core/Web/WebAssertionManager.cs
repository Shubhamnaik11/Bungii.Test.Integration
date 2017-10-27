using System;
using OpenQA.Selenium;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Bungii.Test.Integration.Framework.Core.Web
{
    public class WebAssertionManager
    {
        public static void ElementTextEqual(IWebElement element, String value)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.AreEqual(value, element.Text, "Values does not match");
        }

        public static void CompareStrings(String value1, String value2)
        {
            Assert.AreEqual(value1, value2, "Values does not match");
        }

        public static void StringsNotEqual(String value1, String value2)
        {
            Assert.AreNotEqual(value1, value2, "Values were equal");
        }

        public static void ElementValueEquals(IWebElement element, String value)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.AreEqual(value, element.GetAttribute("value"), "Values does not match");
        }

        public static void ElementValueNotEqual(IWebElement element, String value)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.AreNotEqual(value, element.Text, "Values were equal");
        }

        public static void ElementPresent(IWebElement element)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(element.Displayed, "Element was not displayed");
        }

        public static void ElementEnabled(IWebElement element)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(element.Enabled, "Element was disabled");
        }

        public static void ElementChecked(IWebElement element)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(element.Selected, "Element was not Checked");
        }

        public static void ElementUnChecked(IWebElement element)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsFalse(element.Selected, "Element was checked");
        }

        public static void ElementDisabled(IWebElement element)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsFalse(element.Enabled, "Element was Enabled");
        }

        public static void ElementDisplayed(IWebElement element)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(element.Displayed, "Element was not Displayed");
        }

        public static void ElementNotDisplayed(IWebElement element)
        {
            Assert.IsFalse(element.Displayed, "Element was Displayed");
        }

        public static void CompareMaxLength(IWebElement element, String value)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.AreEqual(value, element.GetAttribute("maxlength"), "Values do not match");
        }

        public static void ElementSelected(IWebElement element)
        {
            WebDriverAction.WaitUntilIsElementExistsAndDisplayed(element);
            Assert.IsTrue(element.Selected, "Element was not selected");
        }
    }
}
