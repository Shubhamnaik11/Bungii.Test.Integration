using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.LoginSignupPages
{
    public class PermissionsPage
    {
        public PermissionsPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //Location Permission Page
        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='Where to?']")]
        public IWebElement Text_Location_text1 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='Wherever you need us, we’ll be there. We use your location to find drivers near you.']")]
        public IWebElement Text_Location_text2 { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/button_location_permission_sure")]
        public IWebElement Button_Location_Sure { get; set; }

        [FindsBy(How = How.Id, Using = "com.android.packageinstaller:id/permission_allow_button")]
        public IWebElement Button_Location_Allow { get; set; }
    }
}
