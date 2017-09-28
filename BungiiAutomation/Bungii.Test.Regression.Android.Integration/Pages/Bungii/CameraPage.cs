using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class CameraPage
    {
        public CameraPage(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }

        [FindsBy(How = How.Id, Using = "com.motorola.camera:id/review_approve")]
        public IWebElement Button_Review { get; set; }

       
    }
}
