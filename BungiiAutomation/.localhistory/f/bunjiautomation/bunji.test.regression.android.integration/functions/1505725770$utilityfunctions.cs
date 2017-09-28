using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Bungii.Test.Integration.Framework.Core;
using Bungii.Test.Regression.Android.Integration.Pages;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.Functions
{
    public class UtilityFunctions
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        LoginPage _LoginPage = new LoginPage(AndroidManager.androiddriver);
        SignupPage _SignupPage = new SignupPage(AndroidManager.androiddriver);
       // ActionManager _ActionManager = new ActionManager();
        public void LoginToCustomerApp(string username, string password)
        {
            DriverAction.Click(_SignupPage.LoginLink);           
            DriverAction.SendKeys(_LoginPage.PhoneNumber,username);
            DriverAction.SendKeys(_LoginPage.PasswordField, password);
            DriverAction.Click(_LoginPage.Button_Login);
        }
        public string GetVerificationCode(string PhoneNumber)
        {
            string SMSCode = string.Empty;
            try
            {
                using (SqlConnection conn = new SqlConnection())
                {
                    conn.ConnectionString = "Data Source=MANDOVI\\MSSQLSERVER2K14;Initial Catalog=BungiiQA;Persist Security Info=True;User ID=for_bungii;Password=forbungii290416";
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
            catch(Exception ex)
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
        public void ScrollToTop()
        {
            var dimensions = driver.Manage().Window.Size;
            Double screenHeightStart = dimensions.Height * 0.2;
            int scrollstart = Convert.ToInt32(screenHeightStart);
            Double screenHeightEnd = dimensions.Height * 0.5;
            int scrollend = Convert.ToInt32(screenHeightEnd);
            driver.Swipe(0, scrollstart, 0, scrollend, 1000);
        }

        public void HideKeyboard()
        {
            try
            {
                driver.HideKeyboard();
            }
            catch(Exception ex)
            {

            }
        }

       
    }
}
