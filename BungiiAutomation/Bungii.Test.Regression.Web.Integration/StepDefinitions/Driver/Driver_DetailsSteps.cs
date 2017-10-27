using Bungii.Android.Regression.Test.Integration.Data;
using Bungii.Android.Regression.Test.Integration.Functions;
using Bungii.Android.Regression.Test.Integration.Pages.Driver;
using Bungii.Test.Integration.Framework;
using Bungii.Test.Integration.Framework.Core.Web;
using OpenQA.Selenium;
using System;
using System.Configuration;
using System.Threading;
using TechTalk.SpecFlow;

namespace Bungii.Android.Regression.Test.Integration.StepDefinitions
{
    [Binding]
    public class Driver_DetailsSteps
    {
        public IWebDriver webdriver = WebManager.webdriver;
        
        Driver_DetailsPage Page_Driver_Details = new Driver_DetailsPage(WebManager.webdriver);
        Driver_PickUpInfoPage Page_Driver_PickupInfo = new Driver_PickUpInfoPage(WebManager.webdriver);
        Driver_DocumentationPage Page_Driver_Doc = new Driver_DocumentationPage(WebManager.webdriver);

        Data_Reusable_Driver Data_Driver = new Data_Reusable_Driver();
        Data_Validation_Driver Data_valid_Driver = new Data_Validation_Driver();

        WebUtilityFunctions WebUtils = new WebUtilityFunctions();
        GeneralUtilityFunctions Functions = new GeneralUtilityFunctions();

        [When(@"I enter ""(.*)"" data on Driver Details page")]
        public void WhenIEnterDataOnDriverDetailsPage(string p0)
        {
            switch (p0)
            {
                case "valid":
                    WebDriverAction.SendKeys(Page_Driver_Details.TextBox_StreetAddress, Data_Driver.DriverStreet);
                    WebDriverAction.SendKeys(Page_Driver_Details.TextBox_City, Data_Driver.DriverCity);
                    WebDriverAction.SelectElementByText(Page_Driver_Details.DropDown_State, Data_Driver.DriverState);
                    WebDriverAction.SendKeys(Page_Driver_Details.TextBox_ZipCode, Data_Driver.DriverZipCode);

                    WebDriverAction.Click(Page_Driver_Details.CheckBox_Age18);
                    WebDriverAction.Click(Page_Driver_Details.CheckBox_Lift75);
                    WebDriverAction.Click(Page_Driver_Details.CheckBox_DrivingExp);

                    WebDriverAction.SendKeys(Page_Driver_Details.TextArea_Other, Data_Driver.DriverOther);

                    WebDriverAction.SendKeys(Page_Driver_Details.TextArea_Occupation, Data_Driver.DriverOccupation);

                    WebDriverAction.Click(Page_Driver_Details.Link_DriverPicture);
                    WebUtils.ImageUpload(ConfigurationManager.AppSettings["ImagePath"], Data_Driver.DriverPic);
                    WebDriverAction.Click(Page_Driver_Details.Button_Crop);
                    Thread.Sleep(1000);
                    WebAssertionManager.ElementEnabled(Page_Driver_Details.Link_RemoveFile);

                    WebDriverAction.SendKeys(Page_Driver_Details.TextBox_SSN, Data_Driver.DriverSSN);
                    WebDriverAction.SendKeys(Page_Driver_Details.TextBox_Birthday, Data_Driver.DriverBirthday);
                    WebUtils.SelectRandomDropdown(Page_Driver_Details.DropDown_Info);

                    WebDriverAction.Click(Page_Driver_Details.CheckBox_Consent);

                    break;

                case "invalid":
                    WebDriverAction.SendKeys(Page_Driver_Details.TextBox_ZipCode, Data_Driver.DriverZipCode_Invalid);

                    WebDriverAction.Click(Page_Driver_Details.Link_ClearAll);                    
                    WebDriverAction.Click(Page_Driver_Details.Link_SelectAll);
                    WebAssertionManager.ElementChecked(Page_Driver_Details.CheckBox_SunEve);
                    WebDriverAction.Click(Page_Driver_Details.Link_ClearAll);
                    WebAssertionManager.ElementUnChecked(Page_Driver_Details.CheckBox_WedAftrn);

                    WebDriverAction.Click(Page_Driver_Details.Link_DriverPicture);
                    WebUtils.ImageUpload(ConfigurationManager.AppSettings["ImagePath"], Data_Driver.DriverPic);
                    WebDriverAction.Click(Page_Driver_Details.Button_Crop);
                    Thread.Sleep(1000);
                    WebDriverAction.Click(Page_Driver_Details.Link_RemoveFile);

                    WebDriverAction.SendKeys(Page_Driver_Details.TextBox_SSN, Data_Driver.DriverSSN_Invalid);

                    WebDriverAction.SendKeys(Page_Driver_Details.TextBox_Birthday, Data_Driver.Date_Invalid);

                    break;

                default: break;
            }
        }

        [When(@"I click Next on ""(.*)"" page")]
        public void WhenIClickNextOnPage(string p0)
        {
            switch (p0)
            {
                case "Driver Details":
                    WebDriverAction.Click(Page_Driver_Details.Button_DetailsNext);
                    break;
                case "Pickup Information":
                    WebDriverAction.Click(Page_Driver_PickupInfo.Button_PickUpNext);
                    break;
                case "Documentation":
                    WebDriverAction.Click(Page_Driver_Doc.Button_DocNext);
                    break;
                default: break;
            }
        }

        [Then(@"I should see blank fields validation on ""(.*)"" page")]
        public void ThenIShouldSeeBlankFieldsValidationOnPage(string p0)
        {
            switch (p0)
            {
                case "Driver Details":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.Err_DriverDetails_AllBlank, Data_valid_Driver.Err_Pages_BlankFields);
                    break;
                case "Pickup Information":
                    WebAssertionManager.ElementTextEqual(Page_Driver_PickupInfo.Err_PickupInfo_AllBlank, Data_valid_Driver.Err_Pages_BlankFields);
                    break;
                case "Documentation":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Doc.Err_Documentation_AllBlank, Data_valid_Driver.Err_Pages_BlankFields);
                    break;
                default: break;
            }
        }

        [Then(@"I should see individual field validations on ""(.*)"" page")]
        public void ThenIShouldSeeIndividualFieldValidationsOnPage(string p0)
        {
            switch (p0)
            {
                case "Driver Details":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.Err_ZipCode, Data_valid_Driver.Err_DriverDetails_ZipCode);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.Err_Other, Data_valid_Driver.Err_DriverDetails_Other);
                    WebAssertionManager.ElementEnabled(Page_Driver_Details.Link_DriverPicture);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.Err_SSN, Data_valid_Driver.Err_DriverDetails_SSN);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.Err_Birthday, Data_valid_Driver.Err_DriverDetails_Birthday);
                    break;

                case "Pickup Information - i":
                    WebAssertionManager.ElementTextEqual(Page_Driver_PickupInfo.Err_AddTruckImages, Data_valid_Driver.Err_Add1MoreTruckImage);
                    break;

                case "Pickup Information - ii":
                    WebAssertionManager.ElementTextEqual(Page_Driver_PickupInfo.Err_AddTruckImages, Data_valid_Driver.Err_Add2MoreTruckImage);
                    break;

                case "date on Documentation":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Doc.Err_LicenseExpiry, Data_valid_Driver.Err_InvalidDate);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Doc.Err_InsuranceExpiry, Data_valid_Driver.Err_InvalidDate);
                    break;

                case "Documentation":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Doc.Err_LicenseNumber, Data_valid_Driver.Err_LicenseNumber);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Doc.Err_LicenseExpiry, Data_valid_Driver.Err_InvalidLicenseExpiryDate);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Doc.Err_InsuranceExpiry, Data_valid_Driver.Err_InvalidInsuranceExpiryDate);
                    break;

                default: break;
            }
        }
    }
}
