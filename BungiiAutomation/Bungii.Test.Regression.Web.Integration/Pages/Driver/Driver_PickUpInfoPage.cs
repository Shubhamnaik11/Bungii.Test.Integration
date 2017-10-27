using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_PickUpInfoPage
    {
        public Driver_PickUpInfoPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        //Pickup Information - Header
        [FindsBy(How = How.XPath, Using = "//div[@id='tab-title']/h3")]
        public IWebElement Header_PickupInfo { get; set; }

        //Pickup Information - Blank field validation
        [FindsBy(How = How.Id, Using = "summary2")]
        public IWebElement Err_PickupInfo_AllBlank { get; set; }

        //Pickup Information - Pickup make
        [FindsBy(How = How.Id, Using = "DriverVehicleDetails_Make")]
        public IWebElement TextBox_PickupMake { get; set; }

        //Pickup Information - Pickup model
        [FindsBy(How = How.Id, Using = "DriverVehicleDetails_Model")]
        public IWebElement TextBox_PickupModel { get; set; }

        //Pickup Information - Pickup year
        [FindsBy(How = How.Id, Using = "DriverVehicleDetails_Year")]
        public IWebElement DropDown_PickupYear { get; set; }

        //Pickup Information - License plate number
        [FindsBy(How = How.Id, Using = "DriverVehicleDetails_VehicleNumber")]
        public IWebElement TextBox_LicenseNo { get; set; }

        //Pickup Information - Truck Image Upload
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone2']")]
        public IWebElement DropZone2_TruckImages { get; set; }

        //Pickup Information - Truck image error
        [FindsBy(How = How.Id, Using = "dropzone2-error")]
        public IWebElement Err_AddTruckImages { get; set; }

        //Pickup Information - Uploaded Truck Image 1
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone2']/div[2]")]
        public IWebElement Image_Truck1 { get; set; }

        //Pickup Information - Uploaded Truck Image 2
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone2']/div[3]")]
        public IWebElement Image_Truck2 { get; set; }

        //Pickup Information - Uploaded Truck Image 3
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone2']/div[4]")]
        public IWebElement Image_Truck3 { get; set; }

        //Pickup Information - Remove
        [FindsBy(How = How.XPath, Using = "//div[@id='dropzone2']/div[2]/a[text()='Remove file']")]
        public IWebElement Link_Truck1Image_Remove { get; set; }

        //ickup Information - Next Button
        [FindsBy(How = How.Id, Using = "btnPickUpInfo")]
        public IWebElement Button_PickUpNext { get; set; }
    }
}
