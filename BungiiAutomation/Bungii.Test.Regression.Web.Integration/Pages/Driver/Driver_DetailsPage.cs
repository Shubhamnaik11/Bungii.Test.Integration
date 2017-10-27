using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_DetailsPage
    {
        public Driver_DetailsPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        //Driver Details - Header
        [FindsBy(How = How.XPath, Using = "//div[@id='tab-title']/h3")]
        public IWebElement Header_DriverDetails { get; set; }

        //Driver Details - Blank field validation
        [FindsBy(How = How.Id, Using = "summary1")]
        public IWebElement Err_DriverDetails_AllBlank { get; set; }

        //Details - Address Information - Street Address
        [FindsBy(How = How.Id, Using = "DriverDetails_Address1")]
        public IWebElement TextBox_StreetAddress { get; set; }

        //Details - Address Information - City
        [FindsBy(How = How.Id, Using = "City")]
        public IWebElement TextBox_City { get; set; }

        //Details - Address Information - State
        [FindsBy(How = How.Id, Using = "DriverDetails_State")]
        public IWebElement DropDown_State { get; set; }

        //Details - Address Information - Zip Code
        [FindsBy(How = How.Id, Using = "DriverDetails_ZipPostalCode")]
        public IWebElement TextBox_ZipCode { get; set; }

        //Details - Address Information - Zip Code - Error
        [FindsBy(How = How.Id, Using = "DriverDetails_ZipPostalCode-error")]
        public IWebElement Err_ZipCode { get; set; }

        //Details - 'Im 18 or older' checkbox
        [FindsBy(How = How.XPath, Using = "//input[@id='DriverDetails.MeetsAgeLimit']/following-sibling::label")]
        public IWebElement CheckBox_Age18 { get; set; }

        //Details - 'I'm able to lift up 75 pounds' checkbox
        [FindsBy(How = How.XPath, Using = "//input[@id='DriverDetails.CanLiftRequiredPounds']/following-sibling::label")]
        public IWebElement CheckBox_Lift75 { get; set; }

        //Details - 'I have had at least one year of driving experience' checkbox
        [FindsBy(How = How.XPath, Using = "//input[@id='DriverDetails.HasSufficientDrivingExperience']/following-sibling::label")]
        public IWebElement CheckBox_DrivingExp { get; set; }

        //Details - Driver Availability - Clear all
        [FindsBy(How = How.XPath, Using = "//input[@class='clearAll']/parent::label")]
        public IWebElement Link_ClearAll { get; set; }

        //Details - Driver Availability - Wednesday Afternoon
        [FindsBy(How = How.XPath, Using = "//input[@id='DriverDetails_DriverAvailability_TimeSlots_2__Afternoon']")]
        public IWebElement CheckBox_WedAftrn { get; set; }

        //Details - Driver Availability - Select all
        [FindsBy(How = How.XPath, Using = "//input[@class='selectAll']/parent::label")]
        public IWebElement Link_SelectAll { get; set; }

        //Details - Driver Availability - Sunday Evening
        [FindsBy(How = How.XPath, Using = "//input[@id='DriverDetails_DriverAvailability_TimeSlots_6__Evening']")]
        public IWebElement CheckBox_SunEve { get; set; }

        //Details - TextArea - Other
        [FindsBy(How = How.Id, Using = "DriverDetails_DriverAvailability_OtherAvailabilityDetails")]
        public IWebElement TextArea_Other { get; set; }

        //Details - TextArea - Other - Error
        [FindsBy(How = How.Id, Using = "DriverDetails_DriverAvailability_OtherAvailabilityDetails-error")]
        public IWebElement Err_Other { get; set; }

        //Details - TextArea - Current / Primary Occupation
        [FindsBy(How = How.Id, Using = "DriverDetails_CurrentOccupation")]
        public IWebElement TextArea_Occupation { get; set; }

        //Details - Driver Picture Upload
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone1']/div/p/a[contains(text(),'click here')]")]
        public IWebElement Link_DriverPicture { get; set; }

        //Details - Driver Picture Upload - Crop Button
        [FindsBy(How = How.XPath, Using = "//div[@class='modal-content']/div[@class='modal-footer']/button[text()='Crop']")]
        public IWebElement Button_Crop { get; set; }

        //Details - Driver Picture Upload - Remove File Link
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone1']/div/a[text()='Remove file']")]
        public IWebElement Link_RemoveFile { get; set; }

        //Details - More Details - Social Security Number
        [FindsBy(How = How.Id, Using = "DriverDetails_SocialSecurityNumber")]
        public IWebElement TextBox_SSN { get; set; }

        //Details - More Details - Social Security Number - Error
        [FindsBy(How = How.Id, Using = "DriverDetails_SocialSecurityNumber-error")]
        public IWebElement Err_SSN { get; set; }

        //Details - More Details - Birthday
        [FindsBy(How = How.Id, Using = "DriverDetails_DateOfBirth")]
        public IWebElement TextBox_Birthday { get; set; }

        //Details - More Details - Birthday - Error
        [FindsBy(How = How.Id, Using = "DriverDetails_DateOfBirth-error")]
        public IWebElement Err_Birthday { get; set; }

        //Details - More Details - How’d you hear about us?
        [FindsBy(How = How.Id, Using = "DriverDetails_ReferralSource")]
        public IWebElement DropDown_Info { get; set; }

        //Details - "I consent to background and driving record checks" checkbox
        [FindsBy(How = How.XPath, Using = "//input[@id='DriverDetails.HasBackgroundConsent']/following-sibling::label")]
        public IWebElement CheckBox_Consent { get; set; }

        //Details - Next Button
        [FindsBy(How = How.Id, Using = "btnAddInfo")]
        public IWebElement Button_DetailsNext { get; set; }
    }
}
