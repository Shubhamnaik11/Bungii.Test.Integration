using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class PaymentPage
    {
        public PaymentPage(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='SUPPORT']")]
        public IWebElement Header_SupportPage { get; set; }

       
    }
}
