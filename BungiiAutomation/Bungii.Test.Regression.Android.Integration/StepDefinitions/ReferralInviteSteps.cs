using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages;
using Bungii.Test.Regression.Android.Integration.Pages.MenuPages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using TechTalk.SpecFlow;
using System;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class ReferralInviteSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;

        InvitePage Page_Invite = new InvitePage(AndroidManager.androiddriver);
        SaveMoneyPage Page_SaveMoney = new SaveMoneyPage(AndroidManager.androiddriver);
        CustomerHomePage Page_Home = new CustomerHomePage(AndroidManager.androiddriver);
        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();
        UtilityFunctions UtilFunctions = new UtilityFunctions();

        [Then(@"I should see ""(.*)"" on Invite Page")]
        public void ThenIShouldSeeOnInvitePage(string p0)
        {
            switch (p0)
            {
                case "all elements":
                    AssertionManager.ElementDisplayed(Page_Invite.Header_Invite);
                    AssertionManager.ElementTextEqual(Page_Invite.Invite_Title, Data_Valid_Customer.Invite_Title);
                    AssertionManager.ElementTextEqual(Page_Invite.Invite_Text, Data_Valid_Customer.Invite_Text);

                    AssertionManager.IsAlphanumeric(UtilFunctions.IsAlphanumeric(Page_Invite.Invite_Code.Text));
                    AssertionManager.CompareStrings("5", UtilFunctions.GetStringLength(Page_Invite.Invite_Code));

                    AssertionManager.ElementEnabled(Page_Invite.Button_Share);
                    break;
                default: break;
            }
        }

        [When(@"I tap ""(.*)"" on Invite page")]
        public void WhenITapOnInvitePage(string p0)
        {
            switch (p0)
            {
                case "Referral Invite link":
                    DriverAction.Click(Page_Home.Link_Invite);
                    break;
                case "Share":
                    DriverAction.Click(Page_Invite.Button_Share);
                    break;
                case "Share on Facebook":
                    DriverAction.Click(Page_Invite.Share_Facebook);
                    break;
                case "Share on Twitter":
                    DriverAction.Click(Page_Invite.Share_Twitter);
                    break;
                case "Share by Email":
                    DriverAction.Click(Page_Invite.Share_Email);
                    break;
                case "Share by Text Message":
                    DriverAction.Click(Page_Invite.Share_TextMessage);
                    break;
                default: break;
            }
        }

        [When(@"I share on ""(.*)""")]
        public void WhenIShareOn(string p0)
        {
            switch (p0)
            {
                case "Facebook with app installed":
                    AssertionManager.ElementTextEqual(Page_Invite.FBApp_Title, Data_Valid_Customer.FBApp_Title);
                    DriverAction.SendKeys(Page_Invite.FBApp_StatusText, Data_Customer.Support_Text);
                    AssertionManager.ElementTextEqual(Page_Invite.FBApp_PreviewText, Data_Valid_Customer.FBApp_PreviewText);
                    DriverAction.Click(Page_Invite.FBApp_PostLink);
                    break;
                default: break;
            }
        }

        [Then(@"I should see post ""(.*)""")]
        public void ThenIShouldSeePost(string p0)
        {
            ScenarioContext.Current.Pending();
        }

    }
}
