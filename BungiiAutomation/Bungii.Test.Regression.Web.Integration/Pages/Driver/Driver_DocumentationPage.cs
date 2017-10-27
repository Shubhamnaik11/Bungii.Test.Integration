using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_DocumentationPage
    {
        public Driver_DocumentationPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        //Documentation - Header
        [FindsBy(How = How.XPath, Using = "//div[@id='tab-title']/h3")]
        public IWebElement Header_Documentation { get; set; }

        //Pickup Information - Blank field validation
        [FindsBy(How = How.Id, Using = "summary3")]
        public IWebElement Err_Documentation_AllBlank { get; set; }

        //Documentation - Driver’s License Image Upload
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone3']")]
        public IWebElement DropZone3_LicenseImage { get; set; }

        //Documentation - Driver’s License Image Upload - Remove file
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone3']/div/a[contains(text(),'Remove')]")]
        public IWebElement Link_LicenseRemoveFile { get; set; }

        //Documentation - License number
        [FindsBy(How = How.Id, Using = "DriverDocument_DocumentNo")]
        public IWebElement TextBox_LicenseNumber { get; set; }

        //Documentation - License number - Error
        [FindsBy(How = How.Id, Using = "DriverDocument_DocumentNo-error")]
        public IWebElement Err_LicenseNumber { get; set; }

        //Documentation - License expiration
        [FindsBy(How = How.Id, Using = "DriverDocument_LicenseExpiry")]
        public IWebElement TextBox_LicenseExpiry { get; set; }

        //Documentation - License expiration - Error
        [FindsBy(How = How.Id, Using = "DriverDocument_LicenseExpiry-error")]
        public IWebElement Err_LicenseExpiry { get; set; }

        //Documentation - Insurance Image Upload
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone4']")]
        public IWebElement DropZone4_InsuranceImage { get; set; }

        //Documentation - Insurance Image Upload - Remove file
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone4']/div/a[contains(text(),'Remove')]")]
        public IWebElement Link_InsuranceRemoveFile { get; set; }

        //Documentation - Insurance expiration
        [FindsBy(How = How.Id, Using = "DriverDocument_InsuranceExpiry")]
        public IWebElement TextBox_InsuranceExpiry { get; set; }

        //Documentation - Insurance expiration - Error
        [FindsBy(How = How.Id, Using = "DriverDocument_InsuranceExpiry-error")]
        public IWebElement Err_InsuranceExpiry { get; set; }

        //Documentation - Next Button
        [FindsBy(How = How.Id, Using = "btnDoc")]
        public IWebElement Button_DocNext { get; set; }
    }
}
