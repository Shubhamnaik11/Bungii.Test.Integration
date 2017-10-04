using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
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

                case "Add":
                    DriverAction.Click(Page_Payment.Button_Add);
                    break;

                case "Credit or Debit Card":
                    DriverAction.Click(Page_Payment.Select_Method_Card);
                    AssertionManager.ElementDisplayed(Page_Payment.Header_CardDetailsPage);
                    break;

                case "Add Card":                    
                    DriverAction.Click(Page_Payment.Button_AddCard);
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

                case "the card has been added":
                    AssertionManager.ElementDisplayed(Page_Payment.PaymentCard1);
                    AssertionManager.ElementDisplayed(Page_Payment.DefaultTick);
                    string ExpectedLast4Digits = DriverAction.GetValueFromScenarioContextVariable("Last4Digits");
                    string ActualLast4Digits = Page_Payment.PaymentCard1.Text.Replace("*", "").Replace(" ","");
                    AssertionManager.CompareStrings(ExpectedLast4Digits, ActualLast4Digits);
                    break;

                case "invalid card error":
                    AssertionManager.ElementTextEqual(Page_Payment.Error_CardNumber, Data_Valid_Customer.Error_InvalidCard);
                    break;

                default: break;
            }
        }

        [When(@"I swipe ""(.*)"" card on the payment page")]
        public void WhenISwipeCardOnThePaymentPage(string p0)
        {
            switch (p0)
            {
                case "2nd":
                    UtilFunctions.SwipeLeft(Page_Payment.PaymentCard2);
                    break;
                default: break;
            }
        }

        [When(@"I enter ""(.*)"" on Card Details page")]
        public void WhenIEnterOnCardDetailsPage(string p0)
        {
            switch (p0)
            {
                case "valid card number":
                    Page_Payment.Textfield_CardNumber.SendKeys(Data_Customer.ValidCard_JCB);
                    DriverAction.AddValueToScenarioContextVariable("Last4Digits", Data_Customer.ValidCard_JCB.Substring(Data_Customer.ValidCard_JCB.Length - 4));
                    break;

                case "invalid card number":
                    Page_Payment.Textfield_CardNumber.SendKeys(Data_Customer.InvalidCard);
                    break;

                case "valid expiry date":
                    DriverAction.Click(Page_Payment.Month_12);
                    DriverAction.Click(Page_Payment.Year_2020);
                    DriverAction.keyBoardEvent(4);
                    break;

                default: break;
            }
        }

    }
}
