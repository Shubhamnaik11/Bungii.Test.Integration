package com.bungii.web.stepdefinitions.partner;

import com.bungii.android.utilityfunctions.GeneralUtility;
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
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.Keys;
import java.util.Map;

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

        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String Pickup_Address;
        String Delivery_Address;

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

        cucumberContextManager.setScenarioContext("GEOFENCE", geofence);
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
                    Thread.sleep(2000);
                    action.click(Page_Partner_Dashboard.List_Pickup_Address());

                    Thread.sleep(2000);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), Delivery_Address + Keys.TAB);
                    action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                    Thread.sleep(2000);
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
                    //Thread.sleep(1000);
                    action.click(Page_Partner_Dashboard.List_Delivery_Address());

                    //Clicking on duo radio button
                    action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());

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
                    //Thread.sleep(1000);
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
                    //Thread.sleep(1000);
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
                    //Thread.sleep(1000);
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
                    //Thread.sleep(1000);
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
                    //Thread.sleep(1000);
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
    }


}
