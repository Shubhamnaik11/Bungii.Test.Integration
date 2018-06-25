using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.DriverPages
{
    class Driver_AvailableTripsPage
    {
        public Driver_AvailableTripsPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //------Available Trips Page--------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/available_trips_recyclerview_list")]
        public IWebElement Row_AvailableTrip_01 { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/row_available_pickup_drivername")]
        public IWebElement Trip01_CustomerName { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/row_available_pickup_textview_time_home")]
        public IWebElement Trip01_TimeFromHome { get; set; }

        //------Trip Details Page-----------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_item_detail_image")]
        public IWebElement Image { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/activity_pickup_request_time_to_pickup_textview")]
        public IWebElement Text_TimeToPickUp { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/activity_pickup_request_trip_distance_textview")]
        public IWebElement Text_TripDistance { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/activity_pickup_request_scheduled_date_textview")]
        public IWebElement Text_ScheduledDate { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/activity_pickup_request_scheduled_time_textview")]
        public IWebElement Text_ScheduledTime { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/activity_pickup_request_accept_available_pickup_button")]
        public IWebElement Button_Accept { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.ImageButton[@content-desc='Navigate up']")]
        public IWebElement Button_Back { get; set; }
    }
}
