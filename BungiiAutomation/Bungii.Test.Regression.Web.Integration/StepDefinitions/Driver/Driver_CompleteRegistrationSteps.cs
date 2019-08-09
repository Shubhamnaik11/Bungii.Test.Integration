using Bungii.Android.Regression.Test.Integration.Data;
using Bungii.Android.Regression.Test.Integration.Functions;
using Bungii.Android.Regression.Test.Integration.Pages.Driver;
using Bungii.Test.Integration.Framework;
using Bungii.Test.Integration.Framework.Core.Web;
using OpenQA.Selenium;
using System;
using System.Configuration;
using TechTalk.SpecFlow;

namespace Bungii.Android.Regression.Test.Integration.StepDefinitions
{
    [Binding]
    public class Driver_PickupInfoSteps
    {
        public IWebDriver webdriver = WebManager.webdriver;

        Driver_PickUpInfoPage Page_Driver_PickupInfo = new Driver_PickUpInfoPage(WebManager.webdriver);
        Driver_DocumentationPage Page_Driver_Doc = new Driver_DocumentationPage(WebManager.webdriver);
        Driver_BankDetailsPage Page_Driver_Bank = new Driver_BankDetailsPage(WebManager.webdriver);

        Data_Reusable_Driver Data_Driver = new Data_Reusable_Driver();
        Data_Validation_Driver Data_valid_Driver = new Data_Validation_Driver();

        WebUtilityFunctions WebUtils = new WebUtilityFunctions();
        GeneralUtilityFunctions Functions = new GeneralUtilityFunctions();

        [When(@"I enter ""(.*)"" data on Pickup Information page")]
        public void WhenIEnterDataOnPickupInformationPage(string p0)
        {
            switch (p0)
            {
                case "valid":
                    WebDriverAction.SendKeys(Page_Driver_PickupInfo.TextBox_PickupMake, Data_Driver.PickupMake);
                    WebDriverAction.SendKeys(Page_Driver_PickupInfo.TextBox_PickupModel, Data_Driver.PickupModel);
                    WebDriverAction.SendKeys(Page_Driver_PickupInfo.DropDown_PickupYear, Data_Driver.PickupYear);
                    WebDriverAction.SendKeys(Page_Driver_PickupInfo.TextBox_LicenseNo, Data_Driver.PickupLicenseNo);

                    WebDriverAction.Click(Page_Driver_PickupInfo.DropZone2_TruckImages);
                    WebUtils.ImageUpload(ConfigurationManager.AppSettings["ImagePath"], Data_Driver.All3PickupImages);

                    WebAssertionManager.ElementDisplayed(Page_Driver_PickupInfo.Image_Truck1);
                    WebAssertionManager.ElementDisplayed(Page_Driver_PickupInfo.Image_Truck2);
                    WebAssertionManager.ElementDisplayed(Page_Driver_PickupInfo.Image_Truck3);

                    break;

                case "less truck image - i":
                    WebDriverAction.Click(Page_Driver_PickupInfo.DropZone2_TruckImages);
                    WebUtils.ImageUpload(ConfigurationManager.AppSettings["ImagePath"], Data_Driver.All3PickupImages);
                    WebDriverAction.Click(Page_Driver_PickupInfo.Link_Truck1Image_Remove);
                    break;

                case "less truck image - ii":
                    WebDriverAction.Click(Page_Driver_PickupInfo.Link_Truck1Image_Remove);
                    break;

                default: break;
            }
        }

        [When(@"I enter ""(.*)"" data on Documentation page")]
        public void WhenIEnterDataOnDocumentationPage(string p0)
        {
            switch (p0)
            {
                case "valid":
                    WebDriverAction.Click(Page_Driver_Doc.DropZone3_LicenseImage);
                    WebUtils.ImageUpload(ConfigurationManager.AppSettings["ImagePath"], Data_Driver.DriverLicenseImage);
                    WebAssertionManager.ElementDisplayed(Page_Driver_Doc.Link_LicenseRemoveFile);

                    WebDriverAction.SendKeys(Page_Driver_Doc.TextBox_LicenseNumber, Data_Driver.DriverLicenseNumber);
                    WebDriverAction.SendKeys(Page_Driver_Doc.TextBox_LicenseExpiry, Data_Driver.ExpirationDate);

                    WebDriverAction.Click(Page_Driver_Doc.DropZone4_InsuranceImage);
                    WebUtils.ImageUpload(ConfigurationManager.AppSettings["ImagePath"], Data_Driver.DriverInsuranceImage);
                    WebAssertionManager.ElementDisplayed(Page_Driver_Doc.Link_InsuranceRemoveFile);

                    WebDriverAction.SendKeys(Page_Driver_Doc.TextBox_InsuranceExpiry, Data_Driver.ExpirationDate);

                    break;

                case "invalid date":
                    WebDriverAction.SendKeys(Page_Driver_Doc.TextBox_LicenseExpiry, Data_Driver.Date_Invalid);
                    WebDriverAction.SendKeys(Page_Driver_Doc.TextBox_InsuranceExpiry, Data_Driver.Date_Invalid);
                    break;

                case "invalid":
                    WebDriverAction.SendKeys(Page_Driver_Doc.TextBox_LicenseNumber, Data_Driver.DriverLicenseNumber_Existing);
                    WebDriverAction.SendKeys(Page_Driver_Doc.TextBox_LicenseExpiry, Data_Driver.Date_2015);
                    WebDriverAction.SendKeys(Page_Driver_Doc.TextBox_InsuranceExpiry, Data_Driver.Date_2015);
                    break;
                default: break;
            }
        }

        [When(@"I enter ""(.*)"" data on Bank Details page")]
        public void WhenIEnterDataOnBankDetailsPage(string p0)
        {
            switch (p0)
            {
                case "valid":
                    WebDriverAction.SendKeys(Page_Driver_Bank.TextBox_RoutingNumber, Data_Driver.DriverRoutingNumber);
                    WebDriverAction.SendKeys(Page_Driver_Bank.TextBox_BankAccNumber, Data_Driver.DriverBankAccNumber);
                    break;
                case "short bank account":
                    WebDriverAction.SendKeys(Page_Driver_Bank.TextBox_BankAccNumber, Data_Driver.InvalidValue);
                    break;
                case "invalid":
                    WebDriverAction.SendKeys(Page_Driver_Bank.TextBox_RoutingNumber, Data_Driver.InvalidValue);
                    WebDriverAction.SendKeys(Page_Driver_Bank.TextBox_BankAccNumber, Data_Driver.DriverBankAccNumber_Invalid);
                    break;
                default: break;
            }
        }
    }
}
