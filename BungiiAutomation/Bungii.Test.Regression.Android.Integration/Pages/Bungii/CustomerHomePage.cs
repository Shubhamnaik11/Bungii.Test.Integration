﻿using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class CustomerHomePage
    {
        public CustomerHomePage(IWebDriver driver)
        {
           PageFactory.InitElements(driver, this);
        }

        [FindsBy(How = How.XPath, Using = "//android.widget.EditText[@resource-id='com.bungii.customer:id/autocomplete_textview' and @instance='0']")]   
        public IWebElement Textfield_PickupLocation { get; set; }                             

        [FindsBy(How = How.XPath, Using = "//android.widget.EditText[@resource-id='com.bungii.customer:id/autocomplete_textview' and @instance='1']")]   //same id as above comment in inspector  //places_autocomplete_dropoff_location actual id
        public IWebElement Textfield_DropoffLocation { get; set; }

        [FindsBy(How = How.Id, Using = "places_autocomplete_dropoff_location")]   //same id as above comment in inspector  //places_autocomplete_dropoff_location actual id
        public IWebElement Textfield_ActualDropoffLocation { get; set; }

        //------Title-----------------------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/toolbar_main_title")]
        public IWebElement Title_HomePage { get; set; }

        //------Buttons---------------------------------------------------------------------------------------

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/menu_invite")]
        public IWebElement Link_Invite { get; set; }

        [FindsBy(How = How.Id, Using = "pickup_location_get_estimate_button")]
        public IWebElement Button_GetEstimate { get; set; }

        [FindsBy(How = How.Id, Using = "My Location")]    //accessibility Id or try with XPath
        public IWebElement Button_MyLocation { get; set; }

        [FindsBy(How = How.ClassName, Using = "android.widget.FrameLayout")]
        public IWebElement MapPanningArea { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/eta_bar_button_set")]
        public IWebElement Button_ETASet { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='BUNGII']")]
        public IWebElement Header_HomePage { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/header_textview_username")]
        public IWebElement Link_Slider { get; set; }

        [FindsBy(How = How.ClassName, Using = "android.widget.ImageButton")]
        public IWebElement Link_Menu { get; set; }
    }
}
