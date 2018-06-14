using Bungii.Android.Regression.Test.Integration.Data;
using Bungii.Android.Regression.Test.Integration.Pages.Admin;
using Bungii.Test.Integration.Framework.Core.Web;
using OpenQA.Selenium;
using System;
using System.Configuration;
using TechTalk.SpecFlow;

namespace Bungii.Android.Regression.Test.Integration.Functions
{
    public class AndroidWebUtilityFunctions
    {
        public IWebDriver webdriver = null;
        Admin_LoginPage Page_AdminLogin = null;
        Admin_MenuLinksPage Page_AdminMenu = null;
        Admin_ReferralSourcePage Page_AdminReferralSource = null;
        Data_Reusable_Admin Data_Admin = null;
        private static string environment = ConfigurationManager.AppSettings["Environment"];

        public AndroidWebUtilityFunctions()
        {
            WebManager.InitializeDriver();
            webdriver = WebManager.webdriver;
            Page_AdminLogin = new Admin_LoginPage(WebManager.webdriver);
            Page_AdminMenu = new Admin_MenuLinksPage(WebManager.webdriver);
            Page_AdminReferralSource = new Admin_ReferralSourcePage(WebManager.webdriver);
            Data_Admin = new Data_Reusable_Admin();
        }

        public void RefreshWebPage()
        {
            webdriver.Navigate().Refresh();
        }

        public void Quit()
        {
            WebManager.Quit(ScenarioContext.Current);
        }

        public void AdminLogin()
        {
            if (environment.Equals("Dev"))
                WebDriverAction.NavigateToUrl(ConfigurationManager.AppSettings["Admin_URL_Dev"]);
            else if (environment.Equals("QA"))
                WebDriverAction.NavigateToUrl(ConfigurationManager.AppSettings["Admin_URL_QA"]);
            else if (environment.Equals("Stage"))
                WebDriverAction.NavigateToUrl(ConfigurationManager.AppSettings["Admin_URL_Stage"]);

            WebDriverAction.SendKeys(Page_AdminLogin.TextBox_Phone, Data_Admin.AdminPhonenumber);
            WebDriverAction.SendKeys(Page_AdminLogin.TextBox_Password, Data_Admin.AdminPassword);
            WebDriverAction.Click(Page_AdminLogin.Button_AdminLogin);
        }

        public Tuple<int, double> GetReferralSourceCount(string referralsource)
        {
            WebDriverAction.Click(Page_AdminMenu.Menu_Marketing);
            WebDriverAction.Click(Page_AdminMenu.Menu_Marketing_ReferralSource);
            var tuple = new Tuple<int, double>(0, 0.00);
            int AccCreated_Count = 0;
            double AccCreated_Percent = 0.0;
            switch (referralsource)
            {
                case "Other":
                    AccCreated_Count = Convert.ToInt32(Page_AdminReferralSource.Other_AccCreated.Text);
                    AccCreated_Percent = Convert.ToDouble(Page_AdminReferralSource.Other_PercentAccCreated.Text);
                    tuple = new Tuple<int, double>(AccCreated_Count, AccCreated_Percent);
                    break;

                case "Event":
                    AccCreated_Count = Convert.ToInt32(Page_AdminReferralSource.Event_AccCreated.Text);
                    AccCreated_Percent = Convert.ToDouble(Page_AdminReferralSource.Event_PercentAccCreated.Text);
                    tuple = new Tuple<int, double>(AccCreated_Count, AccCreated_Percent);
                    break;

                default:
                    tuple = new Tuple<int, double>(0, 0.00);
                    break;
            }
            return tuple;
        }

        public double CalculatePercentValue(string referralsource)
        {
            int AptComplex = Convert.ToInt32(Page_AdminReferralSource.AptComplex_AccCreated.Text);
            int Blimp = Convert.ToInt32(Page_AdminReferralSource.Blimp_AccCreated.Text);
            int Craigslist = Convert.ToInt32(Page_AdminReferralSource.Craigslist_AccCreated.Text);
            int EstateSale = Convert.ToInt32(Page_AdminReferralSource.EstateSale_AccCreated.Text);
            int Event = Convert.ToInt32(Page_AdminReferralSource.Event_AccCreated.Text);
            int Facebook = Convert.ToInt32(Page_AdminReferralSource.Facebook_AccCreated.Text);
            int Google = Convert.ToInt32(Page_AdminReferralSource.Google_AccCreated.Text);
            int NewsStory = Convert.ToInt32(Page_AdminReferralSource.NewsStory_AccCreated.Text);
            int Other = Convert.ToInt32(Page_AdminReferralSource.Other_AccCreated.Text);
            int Store = Convert.ToInt32(Page_AdminReferralSource.Store_AccCreated.Text);
            int WordOfMouth = Convert.ToInt32(Page_AdminReferralSource.WordOfMouth_AccCreated.Text);
            int Sum_Referral = AptComplex + Blimp + Craigslist + EstateSale + Event + Facebook + Google + NewsStory + Other + Store + WordOfMouth;

            double Expected_Other_PercentAccCreated;
            switch (referralsource)
            {
                case "Other":
                    Expected_Other_PercentAccCreated = 
                        Math.Round
                        (
                        Convert.ToDouble(Page_AdminReferralSource.Other_AccCreated.Text) * 100 /
                        Convert.ToDouble(Sum_Referral),
                        2);
                    break;

                default:
                    Expected_Other_PercentAccCreated = 0.00;
                    break;
            }
            return Expected_Other_PercentAccCreated;
        }
    }
}
