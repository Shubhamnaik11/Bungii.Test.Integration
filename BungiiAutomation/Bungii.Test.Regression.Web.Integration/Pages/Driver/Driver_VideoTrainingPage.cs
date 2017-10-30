using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_VideoTrainingPage
    {
        public Driver_VideoTrainingPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }
        
        //Video Blank - error
        [FindsBy(How = How.Id, Using = "summary6")]
        public IWebElement Err_Video { get; set; }

        //Video iFrame
        [FindsBy(How = How.XPath, Using = "//div[@id='divStep6']/div/div/iframe")]
        public IWebElement Screen_Video { get; set; }

        //Video - Viewed checkbox
        [FindsBy(How = How.XPath, Using = "//input[@id='TermsnConditions.ViewedVideo']/following-sibling::label")]
        public IWebElement CheckBox_Viewed_Click { get; set; }

        //Video - Viewed checkbox
        [FindsBy(How = How.XPath, Using = "//input[@id='TermsnConditions.ViewedVideo']")]
        public IWebElement CheckBox_Viewed { get; set; }

        //Video - Next Button
        [FindsBy(How = How.Id, Using = "btnVideo")]
        public IWebElement Button_VideoNext { get; set; }
    }
}
