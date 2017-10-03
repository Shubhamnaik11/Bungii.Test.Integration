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

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='PAYMENT']")]
        public IWebElement Header_PaymentPage { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/payment_methods_button_add")]
        public IWebElement Button_Add { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[2]")]
        public IWebElement Text_NoPaymentExists { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/bt_payment_method_type'][2]")]
        public IWebElement Select_Method_Card { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/payment_methods_textview_add_new_payment")]
        public IWebElement Link_AddNew { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/payment_methods_textview_last_four' and @instance='2']")]
        public IWebElement PaymentCard1 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/payment_methods_textview_last_four' and @instance='3']")]
        public IWebElement PaymentCard2 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.RelativeLayout[@resource-id='com.bungii.customer:id/payment_methods_layout_row' and @instance='10']")]
        public IWebElement PaymentCard3 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.RelativeLayout[@resource-id='com.bungii.customer:id/payment_methods_layout_row' and @instance='13']")]
        public IWebElement PaymentCard4 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.RelativeLayout[@resource-id='com.bungii.customer:id/payment_methods_layout_row' and @instance='16']")]
        public IWebElement PaymentCard5 { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/payment_methods_imageview_default_tick")]
        public IWebElement DefaultTick { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/payment_methods_textview_last_four' and @instance='2']/following-sibling::android.widget.ImageView[@resource-id='com.bungii.customer:id/payment_methods_imageview_default_tick']")]
        public IWebElement DefaultTick_payment1 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/payment_methods_textview_last_four' and @instance='3']/following-sibling::android.widget.ImageView[@resource-id='com.bungii.customer:id/payment_methods_imageview_default_tick']")]
        public IWebElement DefaultTick_payment2 { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/payment_methods_checkbox_set_default")]
        public IWebElement Checkbox_Default { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/payment_methods_button_save")]
        public IWebElement Button_Save { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/payment_methods_textview_slide_delete")]
        public IWebElement Text_SwipeLeftToDelete { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/delete_button")]
        public IWebElement Button_Delete { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button1")]
        public IWebElement Button_Delete_Yes { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button2")]
        public IWebElement Button_Delete_No { get; set; }
    }
}
