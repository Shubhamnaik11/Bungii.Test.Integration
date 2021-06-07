package com.bungii.api.stepdefinitions;

import com.bungii.api.utilityFunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import java.util.Map;

public class PartnerSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(BungiiSteps.class);
    AuthServices authServices = new AuthServices();
    CoreServices coreServices = new CoreServices();
    PaymentServices paymentServices = new PaymentServices();
    DriverServices driverServices = new DriverServices();
    CustomerServices customerServices = new CustomerServices();
    com.bungii.android.utilityfunctions.GeneralUtility utility=new com.bungii.android.utilityfunctions.GeneralUtility();
    com.bungii.ios.utilityfunctions.DbUtility dbUtility = new DbUtility();

    @When("^I request Partner Portal \"([^\"]*)\" Trip for \"([^\"]*)\" partner$")
    public void i_request_partner_portal_something_trip_for_something(String Trip_Type, String Partner_Portal, DataTable data) throws Throwable {

        cucumberContextManager.setScenarioContext("BUNGII_TYPE",Trip_Type);
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String customerName = dataMap.get("Customer Name").trim();
        cucumberContextManager.setScenarioContext("CUSTOMER",customerName);
        String customerPhone = dataMap.get("Customer Phone").trim();
        cucumberContextManager.setScenarioContext("Phone",customerPhone);
        String geofence = dataMap.get("Geofence").trim();
        cucumberContextManager.setScenarioContext("GEOFENCE",geofence);
        String bungii_time = dataMap.get("Bungii Time").trim();

        String Access_Token = authServices.partnerLogin(Partner_Portal);

        cucumberContextManager.setScenarioContext("Partner_Access_Token",Access_Token);

        String[] PartnerSettings = authServices.partnerSettings(Access_Token);

        cucumberContextManager.setScenarioContext("Partner_Location_Config_VersionRef",PartnerSettings[0]);

        String PickupRequestID = coreServices.partnerPickupEstimate(Partner_Portal,geofence,bungii_time,PartnerSettings[0],PartnerSettings[1]);
        cucumberContextManager.setScenarioContext("Pickup_Request",PickupRequestID);
        cucumberContextManager.setScenarioContext("PICKUP_REQUEST",PickupRequestID);

        coreServices.partnerPickupDetails(PickupRequestID);

        String ClientToken = paymentServices.GetTokenForPartner(Access_Token);
            //String token = coreServices.partner_graphql();
        coreServices.partnerDeliveryInformation(PickupRequestID);
        coreServices.partnerConfirmPickup(PickupRequestID);

    }
}