using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class SaveMoneyPage
    {
        public SaveMoneyPage(IWebDriver driver)
        {
           PageFactory.InitElements(driver, this);
        }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='SAVE MONEY']")]     
        public IWebElement Header_SavePage { get; set; }   //places_autocomplete_pickup_location is actual id 

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_promo_code")]
        public IWebElement Textfield_PromoCode { get; set; }

        [FindsBy(How = How.XPath, Using = "//TextInputLayout[@resource-id='com.bungii.customer:id/field_promo_code_layout']/following-sibling::android.widget.Button")]
        public IWebElement Button_Add { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/btn_get_more_money")]
        public IWebElement Button_GetMoreMoney { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label'][1]")]
        public IWebElement SaveMoney_PromoCode1 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label'][2]")]
        public IWebElement SaveMoney_PromoCode2 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label'][3]")]
        public IWebElement SaveMoney_PromoCode3 { get; set; }
    }
}
