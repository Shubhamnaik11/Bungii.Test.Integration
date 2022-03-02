package com.bungii.web.stepdefinitions.partner;
import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.LogInSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_LiveTripsPage;
import com.bungii.web.pages.admin.Admin_ScheduledTripsPage;
import com.bungii.web.pages.admin.Admin_TripDetailsPage;
import com.bungii.web.pages.admin.Admin_TripsPage;
import com.bungii.web.pages.partner.*;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Map;
import static com.bungii.common.manager.ResultManager.error;


public class Partner_TrackingIdSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(LogInSteps.class);
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();
    GeneralUtility utility = new GeneralUtility();
    Partner_Done Page_Partner_Done = new Partner_Done();
    ActionManager action = new ActionManager();
    Partner_LoginPage Page_Partner_Login = new Partner_LoginPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();

    @Given("^I'm logged into \"([^\"]*)\" portal and  created a new  delivery$")
    public void im_logged_into_something_portal_and_created_a_new_delivery(String strArg1) throws Throwable {
        try {
            //Login
            String partnerUrl =  utility.NavigateToPartnerLogin("normal");
            action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
            action.click(Page_Partner_Login.Button_Sign_In());
            testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_Start_Over()), PropertyUtility.getMessage("Start_Over_Header"));



            // pickup Address , delivery address , load time
            action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
            action.click(Page_Partner_Dashboard.Button_PickupClear());
            action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), "601 13th Street Northwest, Washington, United States, District of Columbia, 20005 " + Keys.TAB);
            action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
            Thread.sleep(3000);
            action.click(Page_Partner_Dashboard.List_Pickup_Address());

            Thread.sleep(5000);
            action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
            action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), "234 13th Street Northeast, Washington, District of Columbia 20002" + Keys.TAB);
            Thread.sleep(3000);
            action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
            Thread.sleep(5000);
            action.click(Page_Partner_Dashboard.List_Delivery_Address());
            Thread.sleep(5000);
            action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
            action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
            Thread.sleep(2000);
            action.click(Page_Partner_Dashboard.Button_Get_Estimate());
            Thread.sleep(5000);
            action.click(Page_Partner_Dashboard.Button_Continue());
            Thread.sleep(3000);


            //Delivery Details
            action.clearSendKeys(Page_Partner_Delivery.TextBox_Item_To_Deliver(), "Furniture");
            action.clearSendKeys(Page_Partner_Delivery.TextBox_Customer_Name(), "Test");
            action.click(Page_Partner_Delivery.TextBox_Customer_Mobile());


            action.clearSendKeys(Page_Partner_Delivery.TextBox_Customer_Mobile(), "9998887777");
            action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Name(), "Test Pickup");
            action.click(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone());
            action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone(), "9999999359");
            action.click(Page_Partner_Delivery.Radio_Button_Customer_Card());
            Thread.sleep(5000);

            //Credit card Details
            action.switchToFrame("braintree-hosted-field-number");
            action.click(Page_Partner_Delivery.TextBox_Card_Number());
            action.sendKeys(Page_Partner_Delivery.TextBox_Card_Number(), "4242424242424242");

            action.switchToMainFrame();

            ;
            action.switchToFrame("braintree-hosted-field-expirationDate");
            action.click(Page_Partner_Delivery.TextBox_Expiry_Date());
            action.sendKeys(Page_Partner_Delivery.TextBox_Expiry_Date(), "12/29");
            action.switchToMainFrame();

            action.switchToFrame("braintree-hosted-field-postalCode");
            action.click(Page_Partner_Delivery.TextBox_Postal_Code());
            action.sendKeys(Page_Partner_Delivery.TextBox_Postal_Code(), "XYZ");
            action.switchToMainFrame();

            action.switchToFrame("braintree-hosted-field-cvv");
            action.click(Page_Partner_Delivery.TextBox_CVV());
            action.sendKeys(Page_Partner_Delivery.TextBox_CVV(), "124");
            action.switchToMainFrame();
            Thread.sleep(3000);

            action.JavaScriptScrolldown();
            action.click(Page_Partner_Delivery.Button_Schedule_Bungii());
            Thread.sleep(5000);

            testStepVerify.isEquals(action.getText(Page_Partner_Done.Text_Schedule_Done_Success_Header()), PropertyUtility.getMessage("Done_Success_Header"));

            Thread.sleep(2000);

            cucumberContextManager.setScenarioContext("TRACKINGID_SUMMARY", action.getText(Page_Partner_Dashboard.Text_Summary_TrackingId()));
            Assert.assertTrue(cucumberContextManager.getScenarioContext("TRACKINGID_SUMMARY").toString().length()>0,"TrackingId is not displayed");

            cucumberContextManager.setScenarioContext("DELIVERY_SUMMARY", Page_Partner_Dashboard.Text_Summary_DeliveryAddress().getText().replace(",", "").replace(" United States", ""));

            String PickupRequest = new DbUtility().getPickupRef("9998887777");
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST",PickupRequest);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I should see the trackingid displayed on the delivery confirmation page$")
    public void i_should_see_the_trackingid_displayed_on_the_delivery_confirmation_page() throws Throwable {
        try {
            Thread.sleep(1000);
            Assert.assertTrue(cucumberContextManager.getScenarioContext("TRACKINGID_SUMMARY").toString().length() > 0, "TrackingId is not displayed");
        }
        catch (Exception e) {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step should be successful", "Error performing step,Please check logs for more details",
                        true);
            }
        }


    @And("^I click the \"([^\"]*)\" button on Partner Portal$")
    public void I_Click_the_Some_Button_On_Partner_Portal(String str) throws InterruptedException {
        try {
            Thread.sleep(2000);
        action.click(Page_Partner_Done.Dropdown_Setting());
        action.click(Page_Partner_Done.Button_Track_Deliveries());
        Thread.sleep(5000);
        String  columnTracking = "TRACKING ID";
        cucumberContextManager.setScenarioContext("TRACKINGID_COLUMN", action.getText(Page_Partner_Dashboard.Text_TrackingId_Column()));
        Assert.assertEquals(cucumberContextManager.getScenarioContext("TRACKINGID_COLUMN"),columnTracking,"Tracking Id column doesnt exist");

        cucumberContextManager.setScenarioContext("PARTNER_CUSTOMERNAME",action.getText(Page_Partner_Dashboard.Text_Trip_Customer()));
        cucumberContextManager.setScenarioContext("PARTNER_TRACKINGID", action.getText(Page_Partner_Dashboard.Text_Trip_TrackingId()));
        cucumberContextManager.setScenarioContext("DELIVERY_ADDRESS", action.getText(Page_Partner_Dashboard.Text_Trip_DeliveryAddress()).replace(",", ""));
        Thread.sleep(2000);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I search the trip using a correct tracking id$")
    public void i_search_the_trip_using_a_correct_tracking_id() throws Throwable {
        try {
            action.click(Page_Partner_Dashboard.Textbox_SearchBar());
            Thread.sleep(1000);
            action.clearSendKeys(Page_Partner_Dashboard.Textbox_SearchBar(), (String) cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID") + Keys.ENTER);
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the trip Details$")
    public void i_should_see_the_trip_details() throws Throwable {
        try{
        Thread.sleep(5000);
        Assert.assertEquals(cucumberContextManager.getScenarioContext("TRACKINGID_SUMMARY"),cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID"),"Tracking Id doesnt match the expected data");
        Assert.assertEquals(cucumberContextManager.getScenarioContext("DELIVERY_SUMMARY"),cucumberContextManager.getScenarioContext("DELIVERY_ADDRESS"),"Delivery Address doesnt match the expected data");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I search the trip using invalid tracking id \"([^\"]*)\"$")
    public void i_search_the_trip_using_invalid_tracking_id_something(String TrackingID) throws Throwable {
        try {
            action.click(Page_Partner_Dashboard.Textbox_SearchBar());
            Thread.sleep(1000);
            action.clearSendKeys(Page_Partner_Dashboard.Textbox_SearchBar(), TrackingID + Keys.ENTER);
            Thread.sleep(2000);
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the message \"([^\"]*)\"$")
    public void i_should_see_the_message_something(String ErrorMessage) throws Throwable {
        try {
        String errorText = action.getText(Page_Partner_Dashboard.Label_Trip_ErrorMessage());
        Thread.sleep(1000);
        Assert.assertEquals(errorText,ErrorMessage,"Error messages dont match");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^I navigate to the \"([^\"]*)\" portal configured for \"([^\"]*)\" URL$")
    public void i_navigate_to_the_something_portal_configured_for_something_url(String strArg1, String strArg2) throws Throwable {
        try {
            utility.AdminLoginFromPartner();
            Thread.sleep(60000);
        }catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @When("^I click on the \"([^\"]*)\" button and enter the \"([^\"]*)\" in the search bar$")
    public void i_click_on_the_something_button_and_enter_the_something_in_the_search_bar(String deliveryType, String strArg2) throws Throwable {
       try {
           switch (deliveryType){
               case "Scheduled Deliveries":
                   Thread.sleep(15000);
                   action.click(admin_ScheduledTripsPage.Menu_ScheduledTrips());
                   action.clearSendKeys(admin_TripsPage.TextBox_Search(),(String) cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID") + Keys.ENTER);
                   Thread.sleep(2000);
                   break;
            case "Live Deliveries":
                   Thread.sleep(6000);
                   action.click(admin_LiveTripsPage.Menu_LiveTrips());
                   action.clearSendKeys(admin_TripsPage.TextBox_Search(),(String) cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID") + Keys.ENTER);
                   Thread.sleep(1000);
                   action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
                   action.click(admin_ScheduledTripsPage.Dropdown_LiveDelivery_Details());
                   Thread.sleep(5000);
                   break;
            case "All Deliveries":
                   action.click(admin_ScheduledTripsPage.Menu_AllTrips());
                   action.clearSendKeys(admin_TripsPage.TextBox_Search(),(String) cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID") + Keys.ENTER);
                   Thread.sleep(1000);
                   action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
                   action.click(admin_ScheduledTripsPage.List_ViewDeliveries());
                   Thread.sleep(5000);
                   break;

           }

    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @Then("^I should be able to see the respective delivery$")
    public void i_should_be_able_to_see_the_respective_delivery() throws Throwable {
        try {
        cucumberContextManager.setScenarioContext("ADMIN_CUSTOMERNAME",action.getText(admin_ScheduledTripsPage.Text_Admin_CustomerName()));
       Assert.assertEquals(cucumberContextManager.getScenarioContext("PARTNER_CUSTOMERNAME"),cucumberContextManager.getScenarioContext("ADMIN_CUSTOMERNAME").toString().trim(),"Customer names dont match");
        Thread.sleep(1000);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I click on the \"([^\"]*)\" button from the dropdown$")
    public void i_click_on_the_something_button_from_the_dropdown(String strArg1) throws Throwable {
        try {
        action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
        action.click(admin_ScheduledTripsPage.List_ViewDeliveries());
        Thread.sleep(5000);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @Then("^I should see the \"([^\"]*)\" displayed on the delivery details$")
    public void i_should_see_the_something_displayed_on_the_delivery_details(String strArg1) throws Throwable {
       try {
        String scheduledTrackingId = action.getText(admin_ScheduledTripsPage.Text_Admin_TrackingId()).replace("Tracking Id:","").trim();
        Assert.assertTrue(scheduledTrackingId.length()>0,"TrackingId is not displayed on admin portal");
        Assert.assertEquals(cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID"),scheduledTrackingId,"Tracking Ids dont match");
        Thread.sleep(1000);
        action.click(admin_TripDetailsPage.Button_Ok());
        Thread.sleep(1000);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should be able to see the  bungii trip status to be changed to the below status$")
    public void i_should_be_able_to_see_the_bungii_trip_status_to_be_changed_to_the_below_status(DataTable data) throws Throwable {
        try {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String status = dataMap.get("Status").trim();
        action.click(admin_LiveTripsPage.Menu_LiveTrips());
      action.clearSendKeys(admin_TripsPage.TextBox_Search(),(String) cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID") + Keys.ENTER);
        Thread.sleep(4000);
        String deliveryStatus = action.getText(admin_ScheduledTripsPage.Text_Delivery_TripStarted()).trim();
        Assert.assertEquals(status,deliveryStatus,"Delivery Status dont match");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @When("^I click on the \"([^\"]*)\" link and click on the \"([^\"]*)\" button from the dropdown$")
    public void i_click_on_the_something_link_and_click_on_the_something_button_from_the_dropdown(String deliveryType, String strArg2) throws Throwable {
        try {
            switch (deliveryType) {
                case "Scheduled Deliveries":
                    action.clearSendKeys(admin_TripsPage.TextBox_Search(), (String) cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID") + Keys.ENTER);
                    Thread.sleep(2000);
                    action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
                    action.click(admin_ScheduledTripsPage.List_ViewEdit());
                    action.click(admin_ScheduledTripsPage.Dropdown_Edit_DeliveryDetails());
                    Thread.sleep(3000);
                    break;

            }
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the delivery details displayed$")
    public void i_should_see_the_delivery_details_displayed() throws Throwable {
        try {
        String liveTrackingId = action.getText(admin_ScheduledTripsPage.Text_Admin_TrackingId()).replace("Tracking Id:","").trim();
        Thread.sleep(1000);
        Assert.assertTrue(liveTrackingId.length()>0,"TrackingId is not displayed on admin portal");
       Assert.assertEquals(cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID"),liveTrackingId,"Tracking Ids dont match");
        action.click(admin_TripDetailsPage.Button_Ok());
        Thread.sleep(1000);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should be able to see the respective bungii trip with the below status$")
    public void i_should_be_able_to_see_the_respective_bungii_trip_with_the_below_status(DataTable data) throws Throwable {
        try {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String status = dataMap.get("Status").trim();
        action.click(admin_TripsPage.Menu_Trips());
        action.click(admin_ScheduledTripsPage.Menu_ScheduledTrips());
        action.clearSendKeys(admin_TripsPage.TextBox_Search(),(String) cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID") + Keys.ENTER);
        Thread.sleep(1000);
        String deliveryStatus = action.getText(admin_ScheduledTripsPage.Text_Delivery_Scheduled());
        Assert.assertEquals(status,deliveryStatus,"Delivery Statuses dont match");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see delivery details displayed$")
    public void i_should_see_delivery_details_displayed() throws Throwable {
        try {
        String allTrackingId = action.getText(admin_ScheduledTripsPage.Text_Admin_TrackingId()).replace("Tracking Id:","").trim();
        Assert.assertTrue(allTrackingId.length()>0,"TrackingId is not displayed on admin portal");
        Assert.assertEquals(cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID"),allTrackingId,"Tracking Ids dont match");
        action.click(admin_TripDetailsPage.Button_Ok());
        Thread.sleep(1000);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see the \"([^\"]*)\" details form$")
    public void i_should_see_the_something_details_form(String strArg1) throws Throwable {
        try {
    String editFormHeader = action.getText(admin_ScheduledTripsPage.RadioButton_EditDeliveryDetails());
    Assert.assertTrue(editFormHeader.equals("Edit Scheduled Bungii"),"Edit form is not displayed");
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I click on the \"([^\"]*)\" button and click  the \"([^\"]*)\" button$")
    public void i_click_on_the_something_button_and_click_the_something_button(String strArg1, String strArg2) throws Throwable {
        try {
        cucumberContextManager.setScenarioContext("OLD_DROPOFF_ADDRESS",action.getText(admin_ScheduledTripsPage.Text_NewDropoffAddress()));
        action.click(admin_ScheduledTripsPage.Link_Edit_dropOffLocation());
        action.click(admin_ScheduledTripsPage.Textbox_Edit_dropOfflocationAddress());
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^change the \"([^\"]*)\" location$")
    public void change_the_something_location(String strArg1) throws Throwable {
        try {
        action.clearSendKeys(admin_ScheduledTripsPage.Textbox_Edit_dropOfflocationAddress(),"Fort Lesley J. McNair, Washington, District of Columbia, 20024");
        action.click(admin_ScheduledTripsPage.Text_SelectAdd());
        Thread.sleep(1000);
        cucumberContextManager.setScenarioContext("NEW_DROPOFF_ADDRESS",action.getText(admin_ScheduledTripsPage.Text_NewDropoffAddress()));
        action.click(admin_ScheduledTripsPage.Button_Edit_Verify());
        action.click(admin_ScheduledTripsPage.Button_Edit_Save());
        action.click(admin_ScheduledTripsPage.Button_Edit_Close());
        Thread.sleep(1000);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @Then("^I should see the location changed$")
    public void i_should_see_the_location_changed() throws Throwable {
        try {
    Assert.assertNotEquals(cucumberContextManager.getScenarioContext("NEW_DROPOFF_ADDRESS"),cucumberContextManager.getScenarioContext("OLD_DROPOFF_ADDRESS"),"drop off location is not changed ");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I navigate back to \"([^\"]*)\" portal and click on \"([^\"]*)\" button$")
    public void i_navigate_back_to_something_portal_and_click_on_something_button(String strArg1, String strArg2) throws Throwable {
        try {
        ArrayList<String> tabs = new ArrayList<String> (SetupManager.getDriver().getWindowHandles());
        SetupManager.getDriver().switchTo().window(tabs.get(0));
        action.refreshPage();
        action.click(Page_Partner_Dashboard.Textbox_SearchBar());
        Thread.sleep(1000);
        action.clearSendKeys(Page_Partner_Dashboard.Textbox_SearchBar(), (String) cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID") + Keys.ENTER);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see the delivery address changed and get navigated to the \"([^\"]*)\" portal$")
    public void i_should_see_the_delivery_address_changed_and_get_navigated_to_the_something_portal(String strArg1) throws Throwable {
        try {
        Thread.sleep(2000);
        cucumberContextManager.setScenarioContext("DELIVERY_ADDRESS", action.getText(Page_Partner_Dashboard.Text_Trip_DeliveryAddress()));
        Assert.assertEquals(cucumberContextManager.getScenarioContext("DELIVERY_ADDRESS").toString().replace(",",""),cucumberContextManager.getScenarioContext("NEW_DROPOFF_ADDRESS").toString().replace(",",""),"Delivery addresses dont match");
        ArrayList<String> tabs = new ArrayList<String> (SetupManager.getDriver().getWindowHandles());
        Thread.sleep(2000);
        SetupManager.getDriver().switchTo().window(tabs.get(1));
        SetupManager.getDriver().manage().window().maximize();
        Thread.sleep(4000);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @Then("^The \"([^\"]*)\" page should display the delivery in \"([^\"]*)\" state$")
    public void the_something_page_should_display_the_delivery_in_something_state(String PagenName, String ExpectedText) throws Throwable {
        try {
        action.click(admin_ScheduledTripsPage.Menu_AllTrips());
        action.clearSendKeys(admin_TripsPage.TextBox_Search(),(String) cucumberContextManager.getScenarioContext("PARTNER_TRACKINGID") + Keys.ENTER);
        Thread.sleep(4000);
        String deliveryComplete = action.getText(admin_ScheduledTripsPage.Text_Delivery_Successfull());
        Assert.assertEquals(ExpectedText,deliveryComplete,"Delivery Statuses dont match");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
}
