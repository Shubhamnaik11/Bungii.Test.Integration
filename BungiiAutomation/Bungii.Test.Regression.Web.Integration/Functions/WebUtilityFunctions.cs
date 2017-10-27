using AutoItX3Lib;
using Bungii.Android.Regression.Test.Integration.Data;
using Bungii.Android.Regression.Test.Integration.Pages.Driver;
using Bungii.Test.Integration.Framework.Core.Web;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using System;
using System.Configuration;
using System.Data.SqlClient;
using System.Threading;

namespace Bungii.Android.Regression.Test.Integration.Functions
{
    public class WebUtilityFunctions
    {
        Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage(WebManager.webdriver);

        public void DriverLogin(string Phone, string Password)
        {
            WebDriverAction.NavigateToUrl(ConfigurationManager.AppSettings["Driver_URL_QA"]);
            WebDriverAction.Click(Page_Driver_Reg.Tab_LogIn);
            WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_Phone, Phone);
            WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_Password, Password);
            WebDriverAction.Click(Page_Driver_Reg.Button_Login);
        }

        private static string connection = ConfigurationManager.AppSettings["QA.Database.ConnectionUri"];
        public string GetVerificationCode_Driver(string PhoneNumber)
        {
            string SMSCode = string.Empty;
            try
            {
                using (SqlConnection conn = new SqlConnection())
                {
                    conn.ConnectionString = connection;
                    conn.Open();
                    // Creating the command
                    SqlCommand command = new SqlCommand("SELECT SmsVerificationCode FROM Driver WHERE Phone = @Phone", conn);
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
            catch (Exception)
            {
                Assert.Fail("Could not retrive Verification code");
                return null;
            }
        }

        //Auto IT - Upload Image
        AutoItX3 autoit = new AutoItX3();
        public void ImageUpload(string ImagePath, string Images)
        {
            Thread.Sleep(5000);
            autoit.ControlFocus("Open", "", "Edit1");
            Thread.Sleep(5000);
            autoit.ControlSetText("Open", "", "Edit1", ImagePath);
            autoit.ControlClick("Open", "", "Button1");
            Thread.Sleep(1000);
            autoit.ControlSetText("Open", "", "Edit1", Images);
            autoit.ControlClick("Open", "", "Button1");
            Thread.Sleep(5000);
        }

        //Select Random Dropdown value
        Random random = new Random();
        public void SelectRandomDropdown(IWebElement DropdownField)
        {
            SelectElement s = new SelectElement(DropdownField);
            int itemCount = s.Options.Count; // get the count of elements in ddlWebElement
            s.SelectByIndex(random.Next(0, itemCount));
        }
    }
}
