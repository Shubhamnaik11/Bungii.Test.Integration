using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using OpenQA.Selenium;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Bungii.Test.Integration.Framework.Core
{
    class AssertionManager
    {
        public static void Equal(IWebElement element, String value)
        {
            Assert.AreEqual(value, element.Text, "Values does not match");

        }


        public static void CompareString(String value1, String value2)
        {
            Assert.AreEqual(value1, value2, "Values does not match");

        }

        public static void EqualByAttributeValue(IWebElement element, String value)
        {
            Assert.AreEqual(value, element.GetAttribute("value"), "Values does not match");

        }


        public static void NotEqual(IWebElement element, String value)
        {
            Assert.AreNotEqual(value, element.Text, "Values were equal");

        }

        public static void Present(IWebElement element)
        {
            Assert.IsTrue(element.Displayed, "Element was not displayed");
        }

        public static void Enabled(IWebElement element)
        {
            Assert.IsTrue(element.Enabled, "Element was disabled");
        }

        public static void Checked(IWebElement element)
        {
            Assert.IsTrue(element.Selected, "Element was not Checked");
        }


        public static void UnChecked(IWebElement element)
        {
            Assert.IsFalse(element.Selected, "Element was checked");
        }


        public static void Disabled(IWebElement element)
        {
            Assert.IsFalse(element.Enabled, "Element was Enabled");
        }

        public static void Displayed(IWebElement element)
        {
            Assert.IsTrue(element.Displayed, "Element was not Displayed");
        }

        public static void NotDisplayed(IWebElement element)
        {
            Assert.IsFalse(element.Displayed, "Element was Displayed");
        }


        public static void CompareMaxLength(IWebElement element, String value)
        {
            Assert.AreEqual(value, element.GetAttribute("maxlength"), "Values do not match");
        }

        public static void Selected(IWebElement element)
        {
            Assert.IsTrue(element.Selected, "Element was not selected");
        }

    }
}
