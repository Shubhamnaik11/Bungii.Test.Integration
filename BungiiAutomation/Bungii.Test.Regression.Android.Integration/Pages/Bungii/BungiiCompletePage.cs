using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bungii.Test.Regression.Android.Integration.Pages.Bungii
{
    class BungiiCompletePage
    {
        public BungiiCompletePage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='BUNGII COMPLETE']")]
        public IWebElement PageTitle_BungiiComplete { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.view.View[@id='com.bungii.customer:id/toolbar']/android.widget.ImageView")]
        public IWebElement CloseRateTipPage { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text ='Rate Your Driver']")]
        public IWebElement Title_RateYourDriver { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/driver_profile_image")]
        public IWebElement Image_Driver { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@instance='2']")]
        public IWebElement DriverName { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/rating_bar")]
        public IWebElement RatingBar { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='Tip']")]
        public IWebElement Title_Tip { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/tip_value_label")]
        public IWebElement TipAmount { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/tip_increase_button")]
        public IWebElement Button_IncreaseTip { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/tip_decrease_button")]
        public IWebElement Button_DecreaseTip { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/summary_value_time")]
        public IWebElement BungiiTime { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/summary_value_distance")]
        public IWebElement Distance { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/summary_value_distance")]
        public IWebElement FinalCost { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_success_ok_button")]
        public IWebElement Button_OK { get; set; }
    }
}
