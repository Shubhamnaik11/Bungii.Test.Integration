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
    public class Menu_PaymentSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;

        LoginPage Page_CustLogin = new LoginPage(AndroidManager.androiddriver);
        PaymentPage Page_Payment = new PaymentPage(AndroidManager.androiddriver);

        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();

        UtilityFunctions UtilFunctions = new UtilityFunctions();
        
        [When(@"I tap on ""(.*)"" on Payment page")]
        public void WhenITapOnOnPaymentPage(string p0)
        {
            switch (p0)
            {
                case "the 2nd payment method":
                    DriverAction.AddValueToScenarioContextVariable("CardNumber", Page_Payment.PaymentCard2.Text);
                    AssertionManager.ElementDisabled(Page_Payment.Checkbox_Default);
                    AssertionManager.ElementDisabled(Page_Payment.Button_Save);
                    DriverAction.Click(Page_Payment.PaymentCard2);
                    AssertionManager.ElementDisplayed(Page_Payment.DefaultTick_payment2);
                    break;

                case "Set as default payment mode":
                    AssertionManager.ElementEnabled(Page_Payment.Checkbox_Default);
                    DriverAction.Click(Page_Payment.Checkbox_Default);
                    break;

                case "Save":
                    AssertionManager.ElementEnabled(Page_Payment.Button_Save);
                    DriverAction.Click(Page_Payment.Button_Save);
                    break;

                case "Delete":
                    DriverAction.Click(Page_Payment.Button_Delete);
                    DriverAction.Click(Page_Payment.Button_Delete_Yes);
                    break;

                default: break;
            }
        }

        [Then(@"I should see ""(.*)"" on Payment page")]
        public void ThenIShouldSeeOnPaymentPage(string p0)
        {
            switch (p0)
            {
                case "message when no payment methods exist":
                    string ActualNoPaymentText = UtilFunctions.TrimString(Page_Payment.Text_NoPaymentExists.Text);
                    AssertionManager.CompareStrings(Data_Valid_Customer.Payment_NoPaymentText, ActualNoPaymentText);
                    AssertionManager.ElementEnabled(Page_Payment.Button_Add);
                    break;

                case "default payment set":
                    AssertionManager.ElementDisplayed(Page_Payment.DefaultTick_payment1);
                    string CardNumber = DriverAction.GetValueFromScenarioContextVariable("CardNumber");
                    AssertionManager.ElementTextEqual(Page_Payment.PaymentCard1, CardNumber);
                    AssertionManager.ElementDisabled(Page_Payment.Checkbox_Default);
                    AssertionManager.ElementDisabled(Page_Payment.Button_Save);
                    break;

                case "the card has been deleted":
                    AssertionManager.ElementNotDisplayed(Page_Payment.PaymentCard2);
                    break;
                default: break;
            }
        }

        [When(@"I swipe ""(.*)"" card on the payment page")]
        public void WhenISwipeCardOnThePaymentPage(string p0)
        {
            UtilFunctions.SwipeLeft(Page_Payment.PaymentCard2);
        }

    }
}
