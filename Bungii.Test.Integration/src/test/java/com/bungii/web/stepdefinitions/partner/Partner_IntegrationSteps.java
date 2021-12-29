package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.web.utilityfunctions.GeneralUtility;
import com.bungii.api.utilityFunctions.CoreServices;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryList;
import com.bungii.web.stepdefinitions.admin.Admin_BusinessUsersSteps;
import com.bungii.web.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Keys;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Partner_IntegrationSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Partner_DeliveryList Page_Partner_Delivery_List = new Partner_DeliveryList();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    com.bungii.web.utilityfunctions.DbUtility dbUtility = new DbUtility();

    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_CustomerPage admin_CustomerPage = new Admin_CustomerPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    Admin_EditScheduledBungiiPage admin_EditScheduledBungiiPage = new Admin_EditScheduledBungiiPage();
    CoreServices coreServices = new CoreServices();

    Admin_BusinessUsersSteps admin_businessUsersSteps = new Admin_BusinessUsersSteps();

    @When("^I request \"([^\"]*)\" Bungii trip in partner portal configured for \"([^\"]*)\" in \"([^\"]*)\" geofence$")
    public void i_request_something_bungii_trip_in_partner_portal_for_some_geofence(String Type,String Site,String geofence, DataTable data) throws InterruptedException{
try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String Pickup_Address;
        String Delivery_Address;
        SetupManager.getDriver().manage().window().maximize();

        cucumberContextManager.setScenarioContext("Bungii_Type", Type);
        cucumberContextManager.setScenarioContext("Partner_Bungii_type",Type);

        Pickup_Address = dataMap.get("Pickup_Address");

        Delivery_Address = dataMap.get("Delivery_Address");

        cucumberContextManager.setScenarioContext("PickupAddress",Pickup_Address);
        //Delivery_Address = action.getText(Page_Partner_Dashboard.SetDeliveryAddress());
        cucumberContextManager.setScenarioContext("Delivery_Address", Delivery_Address);

        String Load_Unload = dataMap.get("Load_Unload_Time");

        //int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
        //int numberOf_Driver = dataMap.get("Driver").trim().equalsIgnoreCase("duo") ? 2 :1;

        //cucumberContextManager.setScenarioContext("GEOFENCE", geofence);
        cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence);
        cucumberContextManager.setScenarioContext("PP_Site",Site);
         Thread.sleep(10000);
        if(Site.equalsIgnoreCase("normal")) {
            switch (Type) {
                case "Solo":
                    //action.click(Page_Partner_Dashboard.Partner_Solo());
                    action.click(Page_Partner_Dashboard.Button_Pickup_Edit());

                    action.click(Page_Partner_Dashboard.Button_PickupClear());
                    action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    Thread.sleep(3000);
                    action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    Thread.sleep(5000);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    Thread.sleep(3000);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    Thread.sleep(5000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    Thread.sleep(5000);

                    action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                    switch (Load_Unload) {
                        case "15 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 15);
                            break;
                        case "30 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 30);
                            break;
                        case "45 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 45);
                            break;
                        case "60 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 60);
                            break;
                        case "75 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 75);
                            break;
                        case "90+ minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 90);
                            break;
                        default:
                            break;
                    }
                    break;
                case "Duo":

                    action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
                    action.click(Page_Partner_Dashboard.Button_PickupClear());

                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                    //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    Thread.sleep(1000);
                    action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    Thread.sleep(6000);
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    Thread.sleep(5000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    //Clicking on duo radio button
                    action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                    Thread.sleep(5000);
                    //Clicking on Load Unload dropdown
                    action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());

                    switch (Load_Unload) {
                        case "15 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 15);
                            break;
                        case "30 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 30);
                            break;
                        case "45 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 45);
                            break;
                        case "60 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 60);
                            break;
                        case "75 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 75);
                            break;
                        case "90+ minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 90);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        /*
        Pickup_Address = action.getText(Page_Partner_Dashboard.SetPickupAddress());
        cucumberContextManager.setScenarioContext("PickupAddress",Pickup_Address);
        Delivery_Address = action.getText(Page_Partner_Dashboard.SetDeliveryAddress());
        cucumberContextManager.setScenarioContext("Delivery_Address", Delivery_Address);
        */

        }
        else if(Site.equalsIgnoreCase("kiosk mode")){
            switch (Type) {
                case "Solo":
                    action.click(Page_Partner_Dashboard.Button_Pickup_Edit());

                    action.click(Page_Partner_Dashboard.Button_PickupClear());
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    Thread.sleep(1000);
                    action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    Thread.sleep(2000);
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    Thread.sleep(5000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    Thread.sleep(5000);

                    action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                    switch (Load_Unload) {
                        case "15 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 15);
                            break;
                        case "30 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 30);
                            break;
                        case "45 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 45);
                            break;
                        case "60 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 60);
                            break;
                        case "75 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 75);
                            break;
                        case "90+ minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 90);
                            break;
                        default:
                            break;
                    }
                    break;
                case "Duo":
                    action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
                    action.click(Page_Partner_Dashboard.Button_PickupClear());

                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                    //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    Thread.sleep(1000);
                    action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    Thread.sleep(2000);
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    //Thread.sleep(1000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    //Clicking on duo radio button
                    action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                    Thread.sleep(5000);

                    //Clicking on Load Unload dropdown
                    action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());

                    switch (Load_Unload) {
                        case "15 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 15);
                            break;
                        case "30 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 30);
                            break;
                        case "45 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 45);
                            break;
                        case "60 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 60);
                            break;
                        case "75 minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 75);
                            break;
                        case "90+ minutes":
                            action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                            cucumberContextManager.setScenarioContext("LoadUnload_Time", 90);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }

        }else if(Site.equalsIgnoreCase("service level")){
            switch (Type) {
                case "Solo":
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                    //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    Thread.sleep(1000);
                    action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    Thread.sleep(5000);
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    Thread.sleep(5000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                    break;
                case "Duo":

                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                    //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    Thread.sleep(1000);
                    action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    Thread.sleep(5000);
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                   Thread.sleep(5000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    //Clicking on duo radio button
                    action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                    Thread.sleep(2000);

                    //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                    break;
                default:
                    break;
            }

        }else if(Site.equalsIgnoreCase("FloorDecor service level")){
            switch (Type) {
                case "Solo":
                    //action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                    //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                    //action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    //Thread.sleep(1000);
                   // action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    Thread.sleep(5000);
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    Thread.sleep(5000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                    break;
                case "Duo":

                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                    //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    Thread.sleep(1000);
                    action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    Thread.sleep(5000);
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    Thread.sleep(5000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    //Clicking on duo radio button
                    action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                    Thread.sleep(2000);

                    //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                    break;
                default:
                    break;
            }

        }
        else if(Site.equalsIgnoreCase("BestBuy service level")){
            switch (Type) {
                case "Solo":
                    //action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                    //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                    //action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    //Thread.sleep(1000);
                    //action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    //Thread.sleep(2000);
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                 Thread.sleep(5000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                    break;
                case "Duo":

                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), Pickup_Address + Keys.TAB);
                    //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                    Thread.sleep(1000);
                    action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    Thread.sleep(2000);
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    Thread.sleep(5000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    //Clicking on duo radio button
                    action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                    Thread.sleep(2000);

                    //action.click(Page_Partner_Dashboard.Checkbox_Driver_HelperCarry());
                    break;
                default:
                    break;
            }
            log("I request "+Type+" Bungii trip in partner portal configured for "+Site+" in "+geofence+" geofence", "I have requested "+Type+" Bungii trip in partner portal configured for "+Site+" in "+geofence+" geofence", false);

        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I navigate to \"([^\"]*)\" page$")
    public void i_navigate_to_something_page(String strArg1) throws Throwable {
        try{
            String url = utility.getCurrentUrl().replace("/login", "/quote-only");
            action.navigateTo(url);
            log("I navigate to Quote-only page" ,
                    "I navigated to Quote-only page" , false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in navigating to Quote-only page",
                    true);
        }
    }
    @Then("^I should see the estimate cost quote$")
    public void i_should_see_the_estimate_cost_quote() throws Throwable {
        try{
            String Alias_Name= (String) cucumberContextManager.getScenarioContext("Alias");
            String Selected_Service =(String) cucumberContextManager.getScenarioContext("Selected_service");
            String Trip_Type = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
            int Driver_Number=1;
            if(Trip_Type.equalsIgnoreCase("Duo")){
                Driver_Number=2;
            }
           /* String pickupAddress = (String) cucumberContextManager.getScenarioContext("PickupAddress");
            String dropoffAddress =(String) cucumberContextManager.getScenarioContext("Delivery_Address");
            String Estimate_distance  = new GoogleMaps().getMiles(pickupAddress, dropoffAddress);
            double Estimate_distance_value = Double.parseDouble(Estimate_distance)/1000;
            Estimate_distance_value = Estimate_distance_value / 1.609344; //to convert kms to miles
            */
            String Estimated_distance = action.getText(Page_Partner_Dashboard.Label_Distance()).replace(" miles","");//calculate values as per the displayed miles value to avoid mismatch in calculation
            double Estimate_distance_value = Double.parseDouble(Estimated_distance);

            logger.detail("Estimated Distance : "+ Estimated_distance);
            String Last_Tier_Milenge_Min_Range = dbUtility.getMaxMilengeValue(Alias_Name,Selected_Service);
            double Last_Tier_Milenge_Min_Range_value = Double.parseDouble(Last_Tier_Milenge_Min_Range);
            String Price="";
            if(Estimate_distance_value <= Last_Tier_Milenge_Min_Range_value) {
                Price = dbUtility.getServicePrice(Alias_Name, Driver_Number, String.valueOf(Estimated_distance), Selected_Service);
            }
            else{
                Price = dbUtility.getServicePriceLastTier(Alias_Name, Driver_Number, String.valueOf(Estimated_distance), Selected_Service);
            }
            String Price_Estimated_Page = action.getText(Page_Partner_Dashboard.Label_Estimated_Cost());
            Price_Estimated_Page = Price_Estimated_Page.replace("Estimated Cost: $","");
            testStepAssert.isEquals(Price_Estimated_Page,Price,"For Selected "+Selected_Service+" service correct price should be shown. Expected : "+Price +" | Actual : "+ Price_Estimated_Page,"For Selected "+Selected_Service+" service correct price is shown. Expected : "+Price +" | Actual : "+ Price_Estimated_Page, "For Selected "+Selected_Service+" service correct price is not shown. Expected : "+Price +" | Actual : "+ Price_Estimated_Page);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in getting estimate cost quote",
                    true);
        }

    }

    @Then("^Fields get reset to default state$")
    public void fields_get_reset_to_default_state() throws Throwable {
        try{
         testStepAssert.isEquals(Page_Partner_Dashboard.Dropdown_Pickup_Address().getAttribute("value"),"","Pickup address field should get cleared","Pickup address field is cleared","Pickup address field is not cleared");
            testStepAssert.isEquals(Page_Partner_Dashboard.Dropdown_Delivery_Address().getAttribute("value"),"","Dropdown address field should get cleared","Dropdown address field is cleared","Dropdown address field is not cleared");
            testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Label_NoServiceSelected(),"Service Level should get default to No Service Selected","Service Level gets default to No Service Selected","Service Level should is not defaulted to No Service Selected");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in starting over",
                    true);
        }

    }

    @Then("^I should see header as \"([^\"]*)\"$")
    public void i_should_see_header_as_something(String strArg1) throws Throwable {
        try{
            testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Header_QuotesOnly(),"Header Get Quotes should be displayed","Header Get Quotes is displayed","Header Get Quotes is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }

    @And("^Pickup Time field should not be displayed$")
    public void pickup_time_field_should_not_be_displayed() throws Throwable {
        try{
            testStepAssert.isFalse(action.isElementPresent(Page_Partner_Dashboard.Dropdown_Pickup_Time(true)),"Header Get Quotes should be displayed","Header Get Quotes is displayed","Header Get Quotes is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }

}
