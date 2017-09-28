using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class CustomerHomePage
    {
        public CustomerHomePage(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/autocomplete_textview")]   //com.bungii.customer:id/autocomplete_textview  from inspector   
        public IWebElement Textbox_PickupLocation { get; set; }                              //places_autocomplete_pickup_location is actual id 

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/autocomplete_textview")]   //same id as above comment in inspector  //places_autocomplete_dropoff_location actual id
        public IWebElement Textbox_DropoffLocation { get; set; }

        [FindsBy(How = How.Id, Using = "places_autocomplete_dropoff_location")]   //same id as above comment in inspector  //places_autocomplete_dropoff_location actual id
        public IWebElement Textbox_ActualDropoffLocation { get; set; }

        //--------------Title-------------------//
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/toolbar_main_title")]
        public IWebElement Title_HomePage { get; set; }

        //----------------Buttons--------------//

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
        

    }
}
