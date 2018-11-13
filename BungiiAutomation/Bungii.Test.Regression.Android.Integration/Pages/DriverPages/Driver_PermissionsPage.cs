using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.DriverPages
{
    class Driver_PermissionsPage
    {
        public Driver_PermissionsPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //Location Permission Page
        [FindsBy(How = How.XPath, Using = "//android.view.ViewGroup[@id='com.bungii.driver:id/toolbar_location_permission']/android.widget.TextView[@text='LOCATION']")]
        public IWebElement Title_Location { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='Where to?']")]
        public IWebElement Text_Location_text1 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='We use your location to find deliveries closest to you. We can’t send you delivery requests without this.']")]
        public IWebElement Text_Location_text2 { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/button_location_permission_sure")]
        public IWebElement Button_Location_Sure { get; set; }

        [FindsBy(How = How.Id, Using = "com.android.packageinstaller:id/permission_allow_button")]
        public IWebElement Button_Location_Allow { get; set; }
    }
}
