using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BungiiProject1.PageElements
{
    class SearchingPageElements
    {
        public SearchingPageElements(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/toolbar_button_cancel")]
        public IWebElement Link_CancelSearch { get; set; }

        [FindsBy(How = How.Id, Using ="android:id/button1")]
        public IWebElement Button_CancelConfirm { get; set; }          //By.XPath("//android.widget.Button[@text='YES']")

        [FindsBy(How = How.Id, Using = "android:id/button2")]
        public IWebElement Button_CloseCancel { get; set; }
    }
}
