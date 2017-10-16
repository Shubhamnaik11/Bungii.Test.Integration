using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using System;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class Menu_SaveMoneySteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;

        SaveMoneyPage Page_SaveMoney = new SaveMoneyPage(AndroidManager.androiddriver);
        LoginPage Page_Login = new LoginPage(AndroidManager.androiddriver);
        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();
        UtilityFunctions UtilFunctions = new UtilityFunctions();

        [When(@"I add ""(.*)"" PromoCode")]
        public void WhenIAddPromoCode(string p0)
        {
            switch (p0)
            {
                case "valid":
                    DriverAction.SendKeys(Page_SaveMoney.Textfield_PromoCode, Data_Customer.ValidPromo);
                    break;
                case "fixed valid":
                    DriverAction.SendKeys(Page_SaveMoney.Textfield_PromoCode, Data_Customer.FixedValid);
                    break;
                case "invalid":
                    DriverAction.SendKeys(Page_SaveMoney.Textfield_PromoCode, Data_Customer.InvalidCode);
                    break;
                case "expired":
                    DriverAction.SendKeys(Page_SaveMoney.Textfield_PromoCode, Data_Customer.ExpiredPromo);
                    break;
                case "referral":
                    DriverAction.SendKeys(Page_SaveMoney.Textfield_PromoCode, Data_Customer.ReferralCode);
                    break;
                case "first time":
                    DriverAction.SendKeys(Page_SaveMoney.Textfield_PromoCode, Data_Customer.FirstTimePromo);
                    break;
                case "used one off":
                    DriverAction.SendKeys(Page_SaveMoney.Textfield_PromoCode, Data_Customer.UsedOneOffCode);
                    break;
                default: break;
            }
        }

        [When(@"I tap ""(.*)"" on Save Money page")]
        public void WhenITapOnSaveMoneyPage(string p0)
        {
            switch (p0)
            {
                case "Add":
                    DriverAction.Click(Page_SaveMoney.Button_Add);
                    break;
                case "Get More Money":
                    DriverAction.Click(Page_SaveMoney.Button_GetMoreMoney);
                    break;
                default: break;
            }
            
        }

        [Then(@"I should see ""(.*)"" on Save Money page")]
        public void ThenIShouldSeeOnSaveMoneyPage(string p0)
        {
            switch (p0)
            {
                case "promocode added":
                    AssertionManager.ElementTextEqual(Page_SaveMoney.SaveMoney_PromoCode1, Data_Customer.ValidPromo);
                    break;
                case "snackbar message for invalid code":
                    AssertionManager.SnackbarTextEqual(Page_Login.Snackbar, Data_Valid_Customer.SaveMoney_Snackbar_Invalid);
                    break;
                case "snackbar message for expired code":
                    AssertionManager.SnackbarTextEqual(Page_Login.Snackbar, Data_Valid_Customer.SaveMoney_Snackbar_Expired);
                    break;
                case "snackbar message for already added code":
                    AssertionManager.SnackbarTextEqual(Page_Login.Snackbar, Data_Valid_Customer.SaveMoney_Snackbar_CodeAlreadyAdded);
                    break;
                case "snackbar stating referrals are only for new users":
                    AssertionManager.SnackbarTextEqual(Page_Login.Snackbar, Data_Valid_Customer.SaveMoney_Snackbar_ReferralFromSaveMoney);
                    break;
                case "snackbar stating first time code is for new users":
                    AssertionManager.SnackbarTextEqual(Page_Login.Snackbar, Data_Valid_Customer.SaveMoney_Snackbar_FirsttimeOldUser);
                    break;
                case "snackbar message for used one off code":
                    AssertionManager.SnackbarTextEqual(Page_Login.Snackbar, Data_Valid_Customer.SaveMoney_Snackbar_Invalid);
                    break;
                case "snackbar message stating referral already exists":
                    AssertionManager.SnackbarTextEqual(Page_Login.Snackbar, Data_Valid_Customer.SaveMoney_Snackbar_ReferralFromSaveMoney);
                    break;
                default: break;
            }
        }
    }
}
