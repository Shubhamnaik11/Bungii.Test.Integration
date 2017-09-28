using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class HomePage
    {
        public HomePage(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='BUNGII']")]
        public IWebElement Header_HomePage { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/header_textview_username")]
        public IWebElement Link_Slider { get; set; }

        [FindsBy(How = How.ClassName, Using = "android.widget.ImageButton")]
        public IWebElement Link_Menu { get; set; }

    }
}
