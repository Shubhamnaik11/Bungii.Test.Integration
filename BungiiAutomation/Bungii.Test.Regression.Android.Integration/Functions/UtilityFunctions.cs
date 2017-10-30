using System;
using System.Data.SqlClient;
using System.Threading;
using Bungii.Test.Regression.Android.Integration.Pages;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium.MultiTouch;
using System.Configuration;
using Bungii.Test.Integration.Framework.Core.Android;
using OpenQA.Selenium.Appium.Interfaces;
using System.Text.RegularExpressions;
using Bungii.Test.Integration.Framework.Core.Web;

namespace Bungii.Test.Regression.Android.Integration.Functions
{
    public class UtilityFunctions
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        LoginPage Page_Login = new LoginPage(AndroidManager.androiddriver);
        SignupPage Page_Signup = new SignupPage(AndroidManager.androiddriver);
        TermsPage Page_CustTerms = new TermsPage(AndroidManager.androiddriver);
        CustomerHomePage Page_CustHome = new CustomerHomePage(AndroidManager.androiddriver);
        MenuPage Page_Menu = new MenuPage(AndroidManager.androiddriver);
        private static string connection  = ConfigurationManager.AppSettings["QA.Database.ConnectionUri"];

        public void LoginToCustomerApp(string phone, string password)
        {
            DriverAction.Click(Page_Signup.Link_Login);
            DriverAction.SendKeys(Page_Login.TextField_PhoneNumber, phone);
            DriverAction.SendKeys(Page_Login.TextField_Password, password);
            DriverAction.Click(Page_Login.Button_Login);
            if (DriverAction.isElementPresent(Page_CustTerms.Checkbox_Agree))
            {
                DriverAction.Click(Page_CustTerms.Checkbox_Agree);
                DriverAction.Click(Page_CustTerms.Button_Continue);
                if (DriverAction.isElementPresent(Page_CustTerms.Popup_PermissionsMessage))
                {
                    DriverAction.Click(Page_CustTerms.Button_PermissionsAllow);
                }
            }
            AssertionManager.ElementDisplayed(Page_CustHome.Title_HomePage);
            AssertionManager.ElementDisplayed(Page_CustHome.Link_Invite);
        }

        public void LogoutCustomerApp()
        {
            if(DriverAction.isElementPresent(Page_CustHome.Link_Menu))
            {
                DriverAction.Click(Page_CustHome.Link_Menu);
                DriverAction.Click(Page_Menu.Menu_Logout);
            }
        }

        public string GetVerificationCode(string PhoneNumber)
        {
            string SMSCode = string.Empty;
            try
            {
                using (SqlConnection conn = new SqlConnection())
                {
                    conn.ConnectionString = connection;
                    conn.Open();
                    // Creating the command
                    SqlCommand command = new SqlCommand("SELECT SmsVerificationCode FROM Customer WHERE Phone = @Phone", conn);
                    // Adding the parameters.
                    command.Parameters.Add(new SqlParameter("Phone", PhoneNumber));
                    // Creating new SqlDataReader object and read data from the command.
                    using (SqlDataReader reader = command.ExecuteReader())
                    {
                        // while there is another record present
                        while (reader.Read())
                        {
                            SMSCode = reader["SmsVerificationCode"].ToString();
                        }
                    }
                    conn.Close();
                    return SMSCode;
                }
            }
            catch(Exception)
            {
                Assert.Fail("Could not retrive Verification code");
                return null;
            }
        }

        public void ScrollToBottom()
        {
            var dimensions = driver.Manage().Window.Size;
            Double screenHeightStart = dimensions.Height * 0.5;
            int scrollstart = Convert.ToInt32(screenHeightStart);
            Double screenHeightEnd = dimensions.Height * 0.2;
            int scrollend = Convert.ToInt32(screenHeightEnd);
            driver.Swipe(0, scrollstart, 0, scrollend, 1000);
        }

        public void ScrollUntilElementDisplayed(IWebElement element)
        {
            do ScrollToBottom();
            while(IsElementDisplayed(element)==false);
        }

        public void ScrollToTop()
        {
            var dimensions = driver.Manage().Window.Size;
            Double screenHeightStart = dimensions.Height * 0.2;
            int scrollstart = Convert.ToInt32(screenHeightStart);
            Double screenHeightEnd = dimensions.Height * 0.5;
            int scrollend = Convert.ToInt32(screenHeightEnd);
            driver.Swipe(0, scrollstart, 0, scrollend, 1000);
        }

        public void SwipeLeft(IWebElement row)
        {
            int xShift = Convert.ToInt32(row.Size.Width * 0.20);
            int xStart = (row.Size.Width) - xShift;
            int xEnd = xShift;

            ITouchAction action = new TouchAction(driver)
            .Press(row, xStart, (row.Size.Height / 2))
            .Wait(1000)
            .MoveTo(row, xEnd, (row.Size.Height / 2))
            .Release();

            action.Perform();
            Thread.Sleep(2000);
        }

        public bool IsAlphanumeric(string stringtext)
        {
            Regex reg = new Regex("^[a-zA-Z0-9]*$");
            if (reg.IsMatch(stringtext))
                return true;
            else return false;            
        }

        public string TrimString(string stringtext)
        {
            stringtext = stringtext.Trim().Replace("\t", "").Replace("\n", "").Replace("\r", "");
            return stringtext;
        }

        public string ConvertPhoneToString(IWebElement actualphone)
        {
            string phone = actualphone.Text.Replace(" ", "").Replace("(", "").Replace(")", "").Replace("-", "");
            return phone;
        }

        public string GetStringLength(IWebElement textstring)
        {
            string length = Convert.ToString(textstring.Text.Length);
            return length;
        }

        public void SelectAddress(IWebElement element, string searchstring)
        {
            DriverAction.Clear(element);
            element.Click();
            element.SendKeys(searchstring);
            int x = element.Location.X;
            int y = element.Location.Y;

            new TouchAction(driver).Tap(x + 32, y + 176).Release().Perform();
        }

        public bool IsElementDisplayed(IWebElement element)
        {
            try
            {
                IWebElement elementpresent = element;
                return true;
            }
            catch
            {
                return false;
            }
        }

        public void initialiseweb()
        {
            WebManager.InitializeDriver();
        }
    }
}
