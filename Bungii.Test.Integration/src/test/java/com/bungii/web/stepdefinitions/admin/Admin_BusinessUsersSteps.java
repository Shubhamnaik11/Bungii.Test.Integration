package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.web.manager.*;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.driver.Driver_DetailsPage;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.sound.midi.SysexMessage;
import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_BusinessUsersSteps extends DriverBase {
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_BusinessUsersSteps.class);
    Admin_RevivalPage admin_revivalPage = new Admin_RevivalPage();
    Admin_BusinessUsersPage admin_BusinessUsersPage = new Admin_BusinessUsersPage();
    Admin_PromoterPage admin_PromoterPage = new Admin_PromoterPage();
    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();
    Admin_DriverVerificationPage admin_driverVerificationPage = new Admin_DriverVerificationPage();

    Admin_ScheduledTripsPage admin_ScheduledTripsPage= new Admin_ScheduledTripsPage();
    Admin_TripsPage admin_TripsPage =  new Admin_TripsPage();
    Admin_PotentialPartnersPage admin_potentialPartnersPage = new Admin_PotentialPartnersPage();
    Admin_LogviewPage admin_logviewPage = new Admin_LogviewPage();

    GeneralUtility utility= new GeneralUtility();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();

    Driver_DetailsPage driver_detailsPage = new Driver_DetailsPage();
    Admin_GeofenceAtrributesPage admin_geofenceAtrributesPage =  new Admin_GeofenceAtrributesPage();
    Admin_PaymentMethodsPage admin_paymentMethodsPage = new Admin_PaymentMethodsPage();
    Admin_RefundsPage admin_refundsPage = new Admin_RefundsPage();
    Partner_DashboardPage partner_dashboardPage = new Partner_DashboardPage();
    @And("^I enter following values in \"([^\"]*)\" fields$")
    public void i_enter_following_values_in_something_fields(String fields, DataTable data) throws Throwable {
        try{
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            Long now = Instant.now().toEpochMilli();
            int i = now.intValue() / 1000;
        switch (fields) {
//            case "Business Users" :
            case "New Partner" :
                String Name = dataMap.get("Name").trim().replace("<<UniqueNo>>", Integer.toString(i));
                String Phone = dataMap.get("Phone").trim().replace("<<UniquePhone>>", "");
                String Email = dataMap.get("Email").trim();
                if (Phone.isEmpty())
                    Phone = generatePhoneNumber();
                action.selectElementByText(admin_BusinessUsersPage.DropDown_BusinessUserIsActive(), "Active");
                Thread.sleep(1000);
                action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserName(), Name);
                action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo(), Phone);
                action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserEmailAddress(), Email);
                log("I enter values on Add Business User page",
                        "I entered values on Add Business User page", false);

                cucumberContextManager.setScenarioContext("BO_NAME", Name);
                cucumberContextManager.setScenarioContext("BO_PHONE", admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo().getAttribute("value"));
                cucumberContextManager.setScenarioContext("BO_EMAIL", Email);
                cucumberContextManager.setScenarioContext("BO_STATUS", "Active");


            break;
            case "Geofence" :
                    String Primary = dataMap.get("Primary").trim();
                    String Secondary = dataMap.get("Secondary").trim();
                    String GeofenceName = dataMap.get("Geo-Name").trim().replace("-<<UniqueNo>>", Integer.toString(i));
                    String GeoTimeZone = dataMap.get("Geo-TimeZone").trim();
                    String GeoStatus = dataMap.get("Geo-Status").trim();

                    action.selectElementByText(admin_GeofencePage.Dropdown_Timezone(), GeoTimeZone);
                    action.selectElementByText(admin_GeofencePage.Dropdown_Status(), GeoStatus);

                    action.sendKeys(admin_GeofencePage.TextBox_Primary(), Primary);
                    action.sendKeys(admin_GeofencePage.TextBox_Secondary(), Secondary);
                    if(!GeofenceName.equals("")) {
                        action.sendKeys(admin_GeofencePage.TextBox_GeoName(), GeofenceName);
                        cucumberContextManager.setScenarioContext("GF_GEONAME", GeofenceName);
                    }

                    log("I enter values on Geofence page",
                            "I entered values on Geofence page", true);

                    cucumberContextManager.setScenarioContext("GF_PRIMARY", Primary);
                    cucumberContextManager.setScenarioContext("GF_SECONDARY",Secondary);
                    cucumberContextManager.setScenarioContext("GF_GEOTIMEZONE", GeoTimeZone);
                    cucumberContextManager.setScenarioContext("GF_STATUS", GeoStatus);



                break;
            case "Geofence Attributes" :
                 String Key = dataMap.get("Key").trim();
                    String DefaultValue = dataMap.get("Default-Value").trim();
                    String Description = dataMap.get("Description").trim();
                    String Label = dataMap.get("Label").trim();

                    action.sendKeys(admin_geofenceAtrributesPage.TextBox_Key(), Key);
                    action.sendKeys(admin_geofenceAtrributesPage.TextBox_DefaultValue(), DefaultValue);

                    action.sendKeys(admin_geofenceAtrributesPage.TextBox_Description(), Description);
                    action.sendKeys(admin_geofenceAtrributesPage.TextBox_Label(), Label);
//                    if(!GeofenceName.equals("")) {
//                        action.sendKeys(admin_GeofencePage.TextBox_GeoName(), GeofenceName);
//                        cucumberContextManager.setScenarioContext("GF_GEONAME", GeofenceName);
//                    }

                    log("I enter values on Geofence Attribute page",
                            "I entered values on Geofence Attribute page", false);

                    cucumberContextManager.setScenarioContext("GF_ATTR_KEY", Key);
                    cucumberContextManager.setScenarioContext("GF_ATTR_DEFAULT_VALUE",DefaultValue);
                    cucumberContextManager.setScenarioContext("GF_ATTR_DESCRIPTION", Description);
                    cucumberContextManager.setScenarioContext("GF_ATTR_LABEL", Label);

                break;
        }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I enter invalid phone number and email field$")
    public void i_enter_invalid_phone_number_and_email_field() throws Throwable {
        try{
        action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserEmailAddress(),"INVALID");
        action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo(),"INVALID");
        log("I enter invalid values on Add Business User page",
                "I entered  invalid values on Add Business User page", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @When("^I search by Name \"([^\"]*)\" in \"([^\"]*)\" page$")
    public void i_search_by_name_something_in_something_page(String currentdatetime, String strArg1) throws Throwable {
        try{
        Thread.sleep(2000);
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        action.clearSendKeys(admin_BusinessUsersPage.TextBox_Search(),Name + Keys.ENTER);
        log("I search by name in Business User list page",
                "I searched by name in Business User list page", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I edit the \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_edit_the_something_and_something(String strArg1, String strArg2) throws Throwable {
        try{
        Thread.sleep(3000);
       String Xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
       testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Search should return the customer", "Search returns the customer", "Search doesn't return the customer");
       WebElement row = SetupManager.getDriver().findElement(By.xpath(Xpath));
        action.click(row);

        action.clearSendKeys(admin_BusinessUsersPage.TextBox_BusinessUserEmailAddress(),"krishna.hoderker@creativecapsule.com");
        cucumberContextManager.setScenarioContext("BO_EMAIL", "krishna.hoderker@creativecapsule.com");
        String Phone = (String) cucumberContextManager.getScenarioContext("BO_PHONE");
         long Newphone = Long.parseLong(Phone) + 1;
       action.clearSendKeys(admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo(),String.valueOf(Newphone));
        cucumberContextManager.setScenarioContext("BO_PHONE", admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo().getAttribute("value"));
        log("I edit " + strArg1 +" and "+ strArg2,
                "I edited " + strArg1 +" and "+ strArg2, false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
       }

    @Then("^the partner gets saved successfully and it is displayed in the \"([^\"]*)\" grid$")
    public void the_partner_gets_saved_successfully_and_it_is_displayed_in_the_something_grid(String strArg1) throws Throwable {
        try{
        Thread.sleep(1000);
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        String Phone = (String) cucumberContextManager.getScenarioContext("BO_PHONE");
        String Email = (String) cucumberContextManager.getScenarioContext("BO_EMAIL");
        String Status = (String) cucumberContextManager.getScenarioContext("BO_STATUS");
        action.sendKeys(admin_BusinessUsersPage.TextBox_Search(),Name + Keys.ENTER);
        Thread.sleep(4000);
        String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td/button[@id='btnEditBusinessUser']",Name,Phone,Email,Status);
        //String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td/span[contains(.,'%s')]/following-sibling::td/button[@id='btnEditBusinessUser']",Name,Phone,Email,Status);

        cucumberContextManager.setScenarioContext("XPATH", Xpath );
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Business User should be listed in grid", "Business User is listed in grid","Business User is not listed in grid");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the user \"([^\"]*)\" is displayed in the Partners grid$")
    public void the_user_something_is_displayed_in_the_business_users_grid(String currentdatetime) throws Throwable {
        try{
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        String Phone = (String) cucumberContextManager.getScenarioContext("BO_PHONE");
        String Email = (String) cucumberContextManager.getScenarioContext("BO_EMAIL");
        String Status = (String) cucumberContextManager.getScenarioContext("BO_STATUS");
        action.clearSendKeys(admin_BusinessUsersPage.TextBox_Search(),Name + Keys.ENTER);

        String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td/button[@id='btnEditBusinessUser']",Name,Phone,Email,Status);
        cucumberContextManager.setScenarioContext("XPATH", Xpath );
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Business User should be listed in grid", "Business User is listed in grid","Business User is not listed in grid");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the partner gets updated successfully and it is displayed in the Partners grid$")
    public void the_business_user_gets_updated_successfully_and_it_is_displayed_in_the_business_users_grid() throws Throwable {

        try{
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        String Phone = (String) cucumberContextManager.getScenarioContext("BO_PHONE");
        String Email = (String) cucumberContextManager.getScenarioContext("BO_EMAIL");
        String Status = (String) cucumberContextManager.getScenarioContext("BO_STATUS");
        Thread.sleep(4000);
        action.clearSendKeys(admin_BusinessUsersPage.TextBox_Search(),Name + Keys.ENTER);
        Thread.sleep(4000);
        String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td/button[@id='btnEditBusinessUser']",Name,Phone,Email,Status);
        cucumberContextManager.setScenarioContext("XPATH", Xpath );
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Business User should be listed in grid", "Business User is listed in grid","Business User is not listed in grid");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @Then("^the partner is not displayed in Upload deliveries since payment is not set$")
    public void the_business_user_is_not_displayed_in_bulk_trips_since_payment_is_not_set() throws Throwable {
    try{
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        Select select = new Select(admin_BusinessUsersPage.DropDown_BusinessUser());

        List<WebElement> dropdown = select.getOptions();

        for (WebElement e : dropdown) {
            if(e.getText().equals(Name))
                testStepAssert.isFalse(e.getText().equals(Name),"Business user should not be listed in dropdown without payment method being set", "Business user is listed without payment method being set");
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @Then("^the card is added to the user \"([^\"]*)\"$")
    public void the_card_is_added_to_the_user_something(String uniqueno) throws Throwable {

        testStepAssert.isElementTextEquals(admin_BusinessUsersPage.Label_SuccessMessage(),"Payment details added successfully for Partner.","Payment details added successfully for Business User. message should be displayed" ,"Payment details added successfully for Business User. message is displayed","Payment details added successfully for Business User. message should be displayed is not displayed");
    }
    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String message) throws Throwable {
        testStepAssert.isElementTextEquals(admin_BusinessUsersPage.Label_ErrorContainer(),message,message+ " message should be displayed" ,message+ " message is displayed",message+ "  message should be displayed is not displayed");
    }

    @Then("^the partner is displayed in Upload Deliveries since payment is set$")
    public void the_business_user_is_displayed_in_bulk_trips_since_payment_is_set() throws Throwable {
        try{
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        Select select = new Select(admin_BusinessUsersPage.DropDown_BusinessUser());

        List<WebElement> dropdown = select.getOptions();
        boolean bit = false;
        for (WebElement e : dropdown) {
            if(e.getText().equals(Name))
                bit = true;
        }
        if(!bit)
        testStepAssert.isFalse(true,"Business user should be listed in dropdown since payment method is set", "Business user is not listed though payment method is set");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I select \"([^\"]*)\" from the \"([^\"]*)\" dropdown$")
    public void i_select_something_from_the_something_dropdown(String strArg1, String field) throws Throwable {
        try{
        String Name = null;
        switch(field) {
//            case "Select Business User":
            case "Select Partner":
                 Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");

                action.selectElementByText(admin_BusinessUsersPage.DropDown_AddBusinessUserPayment(),Name);
                log("I select element from Select Business User dropdown",
                        "I have selected element from Select Business User dropdown", true);
            break;
            case "Select Partners":
                 Name = (String) cucumberContextManager.getScenarioContext("PROMOTER_NAME");
                action.selectElementByText(admin_PromoterPage.DropDown_SelectPromoter(),Name);
                log("I select element from Select Business User dropdown",
                        "I have selected element from Select Business User dropdown", true);
                break;
            case "Cancellation Reason":
                //Name = (String) cucumberContextManager.getScenarioContext("REASON_NAME");
                action.selectElementByText(admin_ScheduledTripsPage.Dropdown_CancellationReason(),strArg1);
                log("I select element from Cancellation reason dropdown",
                        "I have selected element from Cancellation reason dropdown", true);
                break;
            case "Partner Cards":
                action.selectElementByText(admin_paymentMethodsPage.Dropdown_Partners(),strArg1);
                log("I select element from Partner Cards dropdown",
                        "I have selected element from Partner Cards dropdown", true);
                break;
        }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on \"([^\"]*)\" button on \"([^\"]*)\" page$")
    public void i_click_on_something_button(String button, String page) throws Throwable {
        try{
        switch(page) {
            case "Partner Payment":
            switch (button) {
                case "Add Payment Method":
                    action.click(admin_BusinessUsersPage.Button_RequestPayment());

                    break;
            }
            break;
            case "Upload Deliveries":
                switch (button) {
                    case "Upload":
                        action.click(admin_BusinessUsersPage.Button_Upload());
                        break;
                    case "Confirm":
                        action.click(admin_BusinessUsersPage.Button_Confirm());
                        break;
                    case "Cancel":
                        action.click(admin_BusinessUsersPage.Button_BulkTripCancel());
                }
                break;
            case "Free Delivery Credit Card":
                    switch (button) {
                        case "Add Payment Method":
                            action.click(admin_BusinessUsersPage.Button_RequestPayment());
                            break;
                    }
                    break;
            case "All Deliveries":
                    switch (button){
                        case "Apply":
                            action.click(admin_TripsPage.Button_Apply());
                            break;
                    }
                    break;
            case "Partner Cards":
                switch (button) {
                    case "Add Payment Method":
                        action.click(admin_paymentMethodsPage.Button_AddPaymentMethod());
                        break;
                }
                break;
            case "Bungii Cards":
                switch (button) {
                    case "Add Payment Method":
                        action.click(admin_paymentMethodsPage.Button_AddPaymentMethod());
                        break;
                }
                break;
            }

        log("I select "+button+" from "+page+ " page",
                "I have selected "+button+" from "+page+ " page", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I enter following card details$")
    public void i_enter_following_card_details(DataTable data) throws Throwable {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String CardNumber = dataMap.get("Card Number").trim();
        String ExpirationDate  = dataMap.get("Expiration Date").trim();
        String CVV  = dataMap.get("CVV").trim();
        String PostalCode  = dataMap.get("Postal Code").trim();
        new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("braintree-hosted-field-postalCode")));
        action.sendKeys(admin_BusinessUsersPage.TextBox_PostalCode(),PostalCode);


        SetupManager.getDriver().switchTo().defaultContent();
        new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-number")));
        action.sendKeys(admin_BusinessUsersPage.TextBox_CreditCardNumber(),CardNumber);
        SetupManager.getDriver().switchTo().defaultContent();

        new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-cvv")));
        action.sendKeys(admin_BusinessUsersPage.TextBox_CVV(),CVV);
        SetupManager.getDriver().switchTo().defaultContent();

        new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-expirationDate")));
        action.sendKeys(admin_BusinessUsersPage.TextBox_ExpirationDate(),ExpirationDate);
        SetupManager.getDriver().switchTo().defaultContent();
        log("I enter card details on Add Payment to Business user page",
                "I have entered card details on Add Payment to Business user page", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click on \"([^\"]*)\" button on \"([^\"]*)\" screen$")
    public void i_click_on_something_button_on_something_screen(String button, String Screen) throws Throwable {
        try{
        switch(Screen)
        {
            case "Partner Payment":
                switch(button) {
                    case "Save":
                        action.click(admin_BusinessUsersPage.Button_PaymentSave());
                        break;
                }
                break;
            case "Promoter Cards":
                switch(button) {
                    case "Save":
                        action.click(admin_PromoterPage.Button_SavePayment());
                        break;
                }
                break;
            case "Partner Cards":
            case "Bungii Cards":
                switch(button) {
                    case "Save":
                        action.click(admin_paymentMethodsPage.Button_Save());
                        break;
                    case "Cancel":
                        action.click(admin_paymentMethodsPage.Button_Cancel());
                        break;
                }
                break;

        }
        log("I click save on "+button +" to "+Screen+" page",
                "I have clicked save on "+button +" to "+Screen+" page", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I select user \"([^\"]*)\"$")
    public void i_select_user_something(String uniqueno) throws Throwable {
        try{
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        action.selectElementByText(admin_BusinessUsersPage.DropDown_BusinessUser(),Name);
        log("I select "+uniqueno+" from Bulk Trips page",
                "I have selected "+uniqueno+" from Bulk Trips page", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @When("^I select business user \"([^\"]*)\"$")
    public void i_select_username_something(String username) throws Throwable {
        try{
        action.selectElementByText(admin_BusinessUsersPage.DropDown_BusinessUser(),username);
        String customerRef = null;
        switch(username) {
            case "Testcustomertywd_apple-Jd1":
                customerRef = "cffb87f0-ca0a-497f-a854-2b5c17367da3";
                break;
            case "Testcustomertywd_apple-jd3":
                customerRef = "1b9b1b3c-2c71-40e6-8e89-59dbc46ada9f";
                break;
        }
            cucumberContextManager.setScenarioContext("CUSTOMER_REF", customerRef);
            cucumberContextManager.setScenarioContext("BUSINESSUSER_NAME", username);

        log("I select "+username+" from Bulk Trips page",
                "I have selected "+username+" from Bulk Trips page", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I upload image to be associated with the delivery$")
    public void i_upload_image_to_be_associated_with_the_trip() throws Throwable {
        try{
        String csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP1"));
        String imagefilepath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("LOADING_ITEM"));

        action.sendKeys(admin_BusinessUsersPage.Input_DataFile(),csvFile);
        action.sendKeys(admin_BusinessUsersPage.Input_ImageFile(),imagefilepath);
        log("I upload csv and image on Bulk Trips page",
                "I have uploaded csv and image on Bulk Trips page", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^I upload image and csv file associated with the \"([^\"]*)\" trip$")
    public void i_upload_image_and_csv_file_associated_with_the_something_trip(String csvname) throws Throwable {
        try{
        String csvFile = null;
    switch (csvname)
    {
        case "Ondemand":
             csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP_PARTNER_FIRM_ONDEMAND"));
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE","washingtondc");
             break;
        case "Solo Scheduled":
             csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP_PARTNER_FIRM_SCHEDULED"));
             csvFile =   utility.generateScheduledBungiiCSV(csvFile,"America/New_York",1, (String)(cucumberContextManager.getScenarioContext("BUSINESSUSER_NAME")), "9999794897");
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE","washingtondc");
             break;
    }
        String imagefilepath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("LOADING_ITEM"));

        action.sendKeys(admin_BusinessUsersPage.Input_DataFile(),csvFile);
        action.sendKeys(admin_BusinessUsersPage.Input_ImageFile(),imagefilepath);
        log("I upload csv and image on Bulk Trips page",
                "I have uploaded csv and image on Bulk Trips page", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @Then("^the pickup from the csv are listed down$")
    public void the_pickup_from_the_csv_are_listed_down() throws Throwable {
        try{
        String csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP1"));
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            int rowIndex = 0;
            while ((line = br.readLine()) != null) {

                if (rowIndex != 0) {
                    String[] column = line.split("\",\"");
                    String[] column2 = column[1].split("\",");
                    String[] column3 = column2[1].split(",");
                    String pickupAddress = column[0].replace("\"", "");
                    String dropdoffAddress = column2[0].replace("\"", "");

                    String xpath = String.format("//tr/td[contains(text(),'%s')]/following-sibling::td[normalize-space(.)='']/following-sibling::td[normalize-space(.)='']/following-sibling::td[normalize-space(.)='']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']", rowIndex, pickupAddress, dropdoffAddress, column3[0]);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)), "Confim Record should be displayed", "Confim Record is displayed", "Confim Record is not displayed");
                }
                rowIndex++;
            }
            log("the pickup from the csv should be listed down",
                    "the pickup from the csv are listed down", false);
        }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    //BOC
    @And("^I select the file with invalid data for \"([^\"]*)\"$")
    public void i_select_the_file_with_invalid_data_for_something(String ErrorField) throws Throwable {
        try{
        String csvFile, imagefilepath;
        switch(ErrorField){
            case "Pickup address":
                csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP2"));
                action.sendKeys(admin_BusinessUsersPage.Input_DataFile(),csvFile);
                cucumberContextManager.setScenarioContext("CSVFILE",PropertyUtility.getCsvLocations("BULK_TRIP2").toString());
                imagefilepath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("LOADING_ITEM"));
                action.sendKeys(admin_BusinessUsersPage.Input_ImageFile(),imagefilepath);
                break;

            case"Pickup Date":
                csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP3"));
                action.sendKeys(admin_BusinessUsersPage.Input_DataFile(),csvFile);
                cucumberContextManager.setScenarioContext("CSVFILE",PropertyUtility.getCsvLocations("BULK_TRIP3").toString());
                imagefilepath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("LOADING_ITEM"));
                action.sendKeys(admin_BusinessUsersPage.Input_ImageFile(),imagefilepath);
                break;

            case "No of Drivers":
                csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP4"));
                action.sendKeys(admin_BusinessUsersPage.Input_DataFile(),csvFile);
                cucumberContextManager.setScenarioContext("CSVFILE",PropertyUtility.getCsvLocations("BULK_TRIP4").toString());
                imagefilepath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("LOADING_ITEM"));
                action.sendKeys(admin_BusinessUsersPage.Input_ImageFile(),imagefilepath);

                break;

            case "Loading/Unloading time":
                csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP5"));
                action.sendKeys(admin_BusinessUsersPage.Input_DataFile(),csvFile);
                cucumberContextManager.setScenarioContext("CSVFILE",PropertyUtility.getCsvLocations("BULK_TRIP5").toString());
                imagefilepath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("LOADING_ITEM"));
                action.sendKeys(admin_BusinessUsersPage.Input_ImageFile(),imagefilepath);
                break;

            case "Blank CSV":
                csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP6"));
                action.sendKeys(admin_BusinessUsersPage.Input_DataFile(),csvFile);
                cucumberContextManager.setScenarioContext("CSVFILE",PropertyUtility.getCsvLocations("BULK_TRIP6").toString());
                imagefilepath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("LOADING_ITEM"));
                action.sendKeys(admin_BusinessUsersPage.Input_ImageFile(),imagefilepath);
                break;
        }
        log("I upload csv file with invalid data for "+ErrorField+" and image on Bulk Trips page",
                "I have uploaded csv and image on Bulk Trips page", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I click on the error link and download the file with error$")
    public void i_click_on_the_error_link_and_download_the_file_with_error() throws Throwable {
        try {
            String errorFileName = cucumberContextManager.getScenarioContext("CSVFILE").toString();
            errorFileName = utility.GetFormattedString(errorFileName, ".csv");
            errorFileName = errorFileName + "_errors";
            String home = SystemUtils.getUserHome().getPath();//System.getProperty("user.home");
            File file = new File(home + "/Downloads/" + errorFileName + ".csv");
            try {
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception ex) {
            }
            action.click(admin_BusinessUsersPage.Link_DownloadFailedCSVFile());
            Thread.sleep(2000);
            String dirPath = home + "/Downloads/";

            cucumberContextManager.setScenarioContext("DIR_PATH", dirPath);
            String fileName = "";
            File getLatestFile;
            do {
                getLatestFile = GetLatestFilefromDir(dirPath);
                fileName = getLatestFile.getName();
                if (fileName.equalsIgnoreCase(errorFileName))
                    break;
            }
            while (!fileName.equalsIgnoreCase(errorFileName + ".csv"));

            String filePath = getLatestFile.getAbsolutePath();

            cucumberContextManager.setScenarioContext("FILE_PATH", filePath);
            testStepAssert.isTrue(fileName.equals(errorFileName + ".csv"), errorFileName + ".csv", "Downloaded (" + fileName + ") file name matches with expected (" + errorFileName + ".csv)file name", "Downloaded (" + fileName + ") file name is not matching with expected (" + errorFileName + ".csv) file name");

            log("The " + errorFileName + " file should get downloaded.",
                    "I am able to download the " + errorFileName, true);
        }
        catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to download or read from the downloads folder of VM",
                    true);
        }
    }
    @Then("^the error \"([^\"]*)\" is displayed$")
    public void the_error_something_is_displayed(String message) throws Throwable {
        testStepAssert.isElementTextEquals(admin_BusinessUsersPage.Label_ErrorOnBulkTripsPage(), "Please check the CSV for errors.", message, message + " is displayed.",message + " is not displayed.");
    }
    @And("^the error \"([^\"]*)\" is displayed in the csv file$")
    public void the_error_something_is_displayed_in_the_csv_file(String message) throws Throwable {

        String filePath = cucumberContextManager.getScenarioContext("FILE_PATH").toString();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // read the first line from the text file
            line = br.readLine();
            while (line != null) {
                line = br.readLine();
                if (line != null) {
                    switch (message) {
                        case "Max pickup Dropoff Distance exceeded":
                            testStepAssert.isTrue(line.contains(message), "Max pickup Dropoff Distance exceeded", message + " is not displayed.");
                            break;

                        case "Please enter a valid date time":
                            testStepAssert.isTrue(line.contains(message), "Please enter a valid date time", message + " is not displayed.");
                            break;

                        case "Loading/Unloading time should be a multiple of 15 minutes ranging from 15 to 90":
                            testStepAssert.isTrue(line.contains(message), "Loading/Unloading time should be a multiple of 15 minutes ranging from 15 to 90", message + " is not displayed.");
                            break;

                        case "Invalid no. of drivers":
                            testStepAssert.isTrue(line.contains(message), "Invalid no. of drivers", message + " is not displayed.");
                            break;

                    }
                }
            }
            log("The "+message+" is found.",
                    "I am able to find the "+message, true);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
        catch (Exception ex){}

    }
    //BOC
    @And("^I Update the \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_update_the_something_and_something(String PhoneNumber, String Email) throws InterruptedException {
        try{
        Thread.sleep(3000);
        String Xpath = (String) cucumberContextManager.getScenarioContext("XPATH");

        WebElement row = SetupManager.getDriver().findElement(By.xpath(Xpath));
        //  WebElement edit = row.findElement(By.xpath("following-sibling::td/button[@id='btnEditBusinessUser']"));
        action.click(row);
        action.clearSendKeys(admin_BusinessUsersPage.TextBox_BusinessUserEmailAddress(),"krishna.hoderker@creativecapsule.com");
        cucumberContextManager.setScenarioContext("BO_EMAIL", "krishna.hoderker@creativecapsule.com");
        String Phone = (String) cucumberContextManager.getScenarioContext("BO_PHONE");
        long Newphone = Long.parseLong(Phone) + 1;
        action.clearSendKeys(admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo(),String.valueOf(Newphone));
        cucumberContextManager.setScenarioContext("BO_PHONE", admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo().getAttribute("value"));

        log("I enter values for PhoneNumber and Email",
                "I entered values for PhoneNumber and Email", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I enter above same Phone number in Phone Number fields$")
    public void i_enter_above_same_phone_number_in_phone_number_fields() throws InterruptedException {
        try{
        Thread.sleep(2000);
        String PhoneNumber=cucumberContextManager.getScenarioContext("BO_PHONE").toString();
        action.clearSendKeys(admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo(), PhoneNumber);
        log("I enter value of PhoneNumber",
                "I entered value of PhoneNumber", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I enter the following values in \"([^\"]*)\" fields$")
    public void i_enter_the_following_values_in_something_fields(String strArg1, DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            Long now = Instant.now().toEpochMilli();
            int i=now.intValue()/1000;
            String Name = dataMap.get("Name").trim().replace("<<UniqueNo>>",Integer.toString(i));
            String Phone = cucumberContextManager.getScenarioContext("BO_PHONE").toString();
            String Email = dataMap.get("Email").trim();
            if(Phone.isEmpty())
                Phone = generatePhoneNumber();
            action.selectElementByText(admin_BusinessUsersPage.DropDown_BusinessUserIsActive(), "Active");

            action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserName(), Name);
            action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo(), Phone);
            action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserEmailAddress(), Email);
            log("I enter values on Add Business User page",
                    "I entered values on Add Business User page", false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^the partner does not get saved successfully$")
    public void the_business_user_does_not_get_saved_successfully() throws Throwable {
        testStepAssert.isEquals(admin_BusinessUsersPage.Label_ErrorContainer().getText(), "Phone number already exists.", "Phone number already exists." + " should be displayed", "Phone number already exists." + " is displayed", "Need to specify message" + " is not displayed");

    }

    @And("^I select the \"([^\"]*)\"$")
    public void i_select_the_something(String strArg1) throws Throwable {
        try{
        String BusinessUserName=cucumberContextManager.getScenarioContext("BO_NAME").toString();
        action.selectElementByText(admin_BusinessUsersPage.DropDown_AddBusinessUserPayment(),BusinessUserName);
        log("I should be able to select business user.",
                "I am able to select business user.", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The payment details page is loaded$")
    public void the_payment_details_page_is_loaded() throws Throwable {
        try{
        String Title= admin_BusinessUsersPage.Label_PayWithCard().getText().toString();
        testStepAssert.isElementTextEquals(admin_BusinessUsersPage.Label_PayWithCard(),"Pay with card","Pay with card","Pay with card is displayed", "Pay with card is not displayed");
        log("I should be able to see Pay with card label.",
                "I am able to see Pay with card label.", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I enter the card details$")
    public void i_enter_the_card_details(DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            Thread.sleep(2000);
            String CardNumber = dataMap.get("CardNumber").trim();
            String ExpiryDate = dataMap.get("ExpiryDate").trim();
            String CVV = dataMap.get("CVV").trim();
            String PostalCode = dataMap.get("PostalCode").trim();

            new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("braintree-hosted-field-postalCode")));
            String pin = SetupManager.getDriver().getPageSource();
            action.sendKeys(admin_BusinessUsersPage.TextBox_PostalCode(),PostalCode);


            SetupManager.getDriver().switchTo().defaultContent();
            new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-number")));
            action.sendKeys(admin_BusinessUsersPage.TextBox_CreditCardNumber(),CardNumber);
            SetupManager.getDriver().switchTo().defaultContent();

            new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-cvv")));
            action.sendKeys(admin_BusinessUsersPage.TextBox_CVV(),CVV);
            SetupManager.getDriver().switchTo().defaultContent();

            new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-expirationDate")));
            action.sendKeys(admin_BusinessUsersPage.TextBox_ExpirationDate(),ExpiryDate);
            SetupManager.getDriver().switchTo().defaultContent();
            log("I enter card details on Add Payment to Business user page",
                    "I have entered card details on Add Payment to Business user page", true);

            cucumberContextManager.setScenarioContext("C_NUMBER", CardNumber);
            cucumberContextManager.setScenarioContext("EXPIRY_DATE", ExpiryDate);
            cucumberContextManager.setScenarioContext("CVV", CVV);
            cucumberContextManager.setScenarioContext("POSTAL_CODE", PostalCode);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

        @And("^I click on \"([^\"]*)\" button$")
        public void i_click_on_something_button(String Name) throws Throwable {
        try{
            switch(Name)
            {
                case "Start Over":
                    action.click(partner_dashboardPage.Label_Start_Over());
                    break;

                case "Save":
                    action.click(admin_BusinessUsersPage.Button_PaymentSave());
                    break;

                case "Submit":
                    action.click(admin_ScheduledTripsPage.Button_Submit());
                    break;

                case "Remove Driver":
                    action.click(admin_ScheduledTripsPage.Button_RemoveDrivers());
                    break;

                case "Research":
                    Thread.sleep(4000);
                    action.click(admin_ScheduledTripsPage.Button_Research());
                    break;

                case "Update" :
                    action.click(driver_detailsPage.Button_Update());
                    break;

                case "Save Driver Details":
                    action.click(admin_driverVerificationPage.Button_SaveForDriver());
                    break;

                case "APPLY":
                    action.click(admin_potentialPartnersPage.Button_ApplyGeofenceFilter());
                    break;

                case "VERIFY":
                    Thread.sleep(5000);
                    action.click(admin_potentialPartnersPage.Button_VerifyDriver());
                    break;

                case "SAVE CHANGES":
                    action.click(admin_potentialPartnersPage.Button_SaveChanges());
                    break;

                case "Close":
                    action.click(admin_potentialPartnersPage.Button_ClosePopUp());
                    break;

                case "ISSUE REFUND":
                    action.click(admin_refundsPage.Button_IssueRefund());
                    break;
                case "OK":
                    action.click(admin_refundsPage.Button_OK());
                    break;

                case "RESET":
                    action.click(admin_refundsPage.Button_Reset());
                    break;

                case "GO BACK":
                    action.click(admin_refundsPage.Button_GoBack());
                    break;

                case "Close icon":
                    action.click(admin_refundsPage.Button_Close());
                    Thread.sleep(5000);
                    break;
                case "Revive":
                    String reviveLink = (String) cucumberContextManager.getScenarioContext("REVIVE_LINK");
                    action.click(admin_TripsPage.findElement(reviveLink,PageBase.LocatorType.XPath));
                    break;
                case "Confirm":
                    action.click(admin_revivalPage.Button_Confirm());
                    break;
                case "Execute":
                    action.click(admin_logviewPage.Button_Execute());
                    break;

            }
            log("I click on the "+Name+" button",
                    "I clicked the "+Name+" button", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }

        @When("I change the status to \"([^\"]*)\"")
        public void i_change_the_status_to(String string) {
        try{
            // Write code here that turns the phrase above into concrete actions
            action.selectElementByText(admin_BusinessUsersPage.DropDown_BusinessUserIsActive(), "Inactive");
            log("I change the status to "+ string,
                    "I changed the status to "+ string, false);
        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
        }

    private File GetLatestFilefromDir(String dirPath){
            File dir = new File(dirPath);
            File[] files = dir.listFiles();
            if (files == null || files.length == 0) {
                return null;
            }
            File lastModifiedFile = files[0];
            for (int i = 1; i < files.length; i++) {
                if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                    lastModifiedFile = files[i];
                }
            }
            return lastModifiedFile;
        }


    //EOC
    private String generatePhoneNumber()
    {
        Random rand = new Random();
        int num1 = 9999;
        int num2 = 700 + new Random().nextInt(999 - 700 + 1);;
        int num3 = rand.nextInt(998);

        DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
        DecimalFormat df4 = new DecimalFormat("000"); // 4 zeros

        return df3.format(num1)  + df3.format(num2)  + df4.format(num3);

    }

    @And("^I get new pickuref$")
    public void i_get_new_pickuref() throws Throwable {
        try{
        String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        pickupRequest = new DbUtility().getResarchedPickupReference(pickupRequest); //researched pickup ref
        cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


}
