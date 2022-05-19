package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_EditScheduledBungiiPage;
import com.bungii.web.pages.admin.Admin_TripDetailsPage;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.DecimalFormat;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class Admin_PriceOverrideSteps extends DriverBase {

    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_AccessorialChargesSteps.class);

    Admin_TripDetailsPage admin_tripDetailsPage = new Admin_TripDetailsPage();
    Partner_DeliveryPage partner_deliveryPage = new Partner_DeliveryPage();
    Admin_TripDetailsPage Page_Admin_Trips_Details = new Admin_TripDetailsPage();
    Admin_EditScheduledBungiiPage admin_editScheduledBungiiPage= new Admin_EditScheduledBungiiPage();

    @And("^I check if \"([^\"]*)\" button is displayed$")
    public void i_check_if_something_button_is_displayed(String strArg1) throws Throwable {

        try{
            testStepAssert.isElementDisplayed(admin_tripDetailsPage.Button_Price_Override(),"I should be able to see Price Override button","I could see the Price Override button","I could not see the Price Override button");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on \"([^\"]*)\" button on delivery details$")
    public void i_click_on_something_button_on_delivery_details(String strArg1) throws Throwable {
        try{
            action.click(admin_tripDetailsPage.Button_Price_Override());
            Thread.sleep(5000);

            log("I should be able to click on Price Override button ",
                    "I could click on Price Override button",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @And("^I get the old values of \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_get_the_old_values_of_something_for_something(String price, String type) throws Throwable {
        try {
            switch (type){
                case "Service level":
                    switch (price){
                        case "Customer price":
                            String customerPrice = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String oldCustomerPrice = customerPrice.substring(1);
                            float oldPrice= Float.parseFloat(oldCustomerPrice);
                            float newPrice= (float) (oldPrice+20.08);
                            cucumberContextManager.setScenarioContext("OLD_CUSTOMER_PRICE", oldCustomerPrice);
                            cucumberContextManager.setScenarioContext("NEW_CUSTOMER_PRICE",newPrice);
                            break;

                        case "Driver cut":
                            String driverCut = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
                            String oldDriverCut = driverCut.substring(1);
                            float oldDriverPrice= Float.parseFloat(oldDriverCut);
                            float newDriverPrice= (float) (oldDriverPrice+20.08);
                            cucumberContextManager.setScenarioContext("OLD_DRIVER_CUT",oldDriverCut);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_CUT",newDriverPrice);
                            break;
                    }
                    break;

                case "Service level - fnd":
                    switch (price){
                        case "Customer price":
                            String customerPriceFnd = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String oldCustomerPriceFnd = customerPriceFnd.substring(1);
                            float oldPriceFnd= Float.parseFloat(oldCustomerPriceFnd);
                            float newPriceFnd= (float) (oldPriceFnd+20.08);
                            cucumberContextManager.setScenarioContext("OLD_CUSTOMER_PRICE", oldCustomerPriceFnd);
                            cucumberContextManager.setScenarioContext("NEW_CUSTOMER_PRICE",newPriceFnd);
                            break;

                        case "Driver cut":
                            String goodsWeight= (String) cucumberContextManager.getScenarioContext("Weight");
                            String driverCutFnd = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings_Fnd(goodsWeight));
                            String oldDriverCutFnd = driverCutFnd.substring(1);
                            float oldDriverPriceFnd= Float.parseFloat(oldDriverCutFnd);
                            float newDriverPriceFnd= (float) (oldDriverPriceFnd+20.08);
                            DecimalFormat df = new DecimalFormat("0.00");
                            float newFormatedDriverPriceFnd = Float.parseFloat(df.format(newDriverPriceFnd));
                            cucumberContextManager.setScenarioContext("OLD_DRIVER_CUT",oldDriverPriceFnd);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_CUT",newFormatedDriverPriceFnd);
                            break;

                    }
                 break;
                case "Service level-duo":
                    switch (price) {
                        case "Customer price":
                            String customerPriceFnd = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String oldCustomerPriceFnd = customerPriceFnd.substring(1);
                            float oldPriceFnd= Float.parseFloat(oldCustomerPriceFnd);
                            float newPriceFnd= (float) (oldPriceFnd+20.08);
                            cucumberContextManager.setScenarioContext("OLD_CUSTOMER_PRICE", oldCustomerPriceFnd);
                            cucumberContextManager.setScenarioContext("NEW_CUSTOMER_PRICE",newPriceFnd);
                            break;

                        case "Driver cut":
                            String driverCut = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
                            String oldDriverCut = driverCut.substring(1);
                            float oldDriverPrice= Float.parseFloat(oldDriverCut);
                            float newDriverPrice= (float) (oldDriverPrice+2.08);
                            DecimalFormat df = new DecimalFormat("0.00");
                            float newFormatedDriverPrice = Float.parseFloat(df.format(newDriverPrice));
                            cucumberContextManager.setScenarioContext("OLD_DRIVER_CUT",oldDriverCut);
                            cucumberContextManager.setScenarioContext("NEW_DRIVER_CUT",newFormatedDriverPrice);
                            break;
                    }

            }
            log("I should be able to save the old values of customer price and driver cut",
                    "I could save the old values of customer price and driver cut",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I check the new values of \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_check_the_new_values_of_something_for_something(String price, String type) throws Throwable {
        try {
            switch (type){
                case "Service level":
                    switch (price) {
                        case "Customer price":
                            action.refreshPage();
                            String estimatedCharges = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String actualEstimatedCharges = estimatedCharges.substring(1);
                            String expectedEstimatedCharges = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
                            testStepAssert.isEquals(actualEstimatedCharges, expectedEstimatedCharges, "Estimated Charges should be overridden", "Estimated Charges are overridden", "Estimated Charges are not overridden");
                            break;

                        case "Driver Fixed Earnings":
                            action.refreshPage();
                            String driverCharges = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
                            String actualDriverCharges = driverCharges.substring(1);
                            String expectedDriverCharges = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                            testStepAssert.isEquals(actualDriverCharges, expectedDriverCharges, "Driver Charges should be overridden", "Driver Charges are overridden", "Driver Charges are not overridden");
                            break;
                    }
                    break;
                case "Service level - fnd":
                    switch (price) {
                        case "Customer price":
                            action.refreshPage();
                            String estimatedChargesFnd = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String actualEstimatedChargesFnd = estimatedChargesFnd.substring(1);
                            String expectedEstimatedChargesFnd = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
                            testStepAssert.isEquals(actualEstimatedChargesFnd, expectedEstimatedChargesFnd, "Estimated Charges should be overridden", "Estimated Charges are overridden", "Estimated Charges are not overridden");
                            break;

                        case "Driver Fixed Earnings":
                            action.refreshPage();
                            String goodsWeight = (String) cucumberContextManager.getScenarioContext("Weight");
                            String driverChargesFnd = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings_Fnd(goodsWeight));
                            String actualDriverChargesFnd = driverChargesFnd.substring(1);
                            String expectedDriverChargesFnd = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                            testStepAssert.isEquals(actualDriverChargesFnd, expectedDriverChargesFnd, "Driver Charges should be overridden", "Driver Charges are overridden", "Driver Charges are not overridden");
                            break;
                    }
                    break;
                case "Service level-duo":
                    switch (price){
                        case "Customer price":
                            action.refreshPage();
                            String estimatedChargesFnd = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                            String actualEstimatedChargesFnd = estimatedChargesFnd.substring(1);
                            String expectedEstimatedChargesFnd = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
                            testStepAssert.isEquals(actualEstimatedChargesFnd, expectedEstimatedChargesFnd, "Estimated Charges should be overridden", "Estimated Charges are overridden", "Estimated Charges are not overridden");
                            break;

                        case "Driver Fixed Earnings":
                            action.refreshPage();
                            String driverCharges = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
                            String actualDriverCharges = driverCharges.substring(1);
                            String expectedDriverCharges = (String) cucumberContextManager.getScenarioContext("OLD_DRIVER_CUT");
                            testStepAssert.isEquals(actualDriverCharges, expectedDriverCharges, "Driver Charges should be overridden", "Driver Charges are overridden", "Driver Charges are not overridden");
                            break;

                    }
                    break;
            }
            log("I should be able to check the new values of customer price and driver cut",
                    "I could check the new values of customer price and driver cut",false);


        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I check if \"([^\"]*)\" icon is displayed$")
    public void i_check_if_something_icon_is_displayed(String strArg1) throws Throwable {
       try{
           action.refreshPage();
           testStepAssert.isElementDisplayed(admin_tripDetailsPage.Icon_Price_Override(),
                   "I should be able to see price override icon displayed",
                   "I could see price override icon displayed",
                   "Price override icon is not displayed");
       }
       catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }
    @When("^I navigate back to Live Deliveries$")
    public void i_navigate_back_to_live_deliveries() throws Throwable {
        try {
                action.click(Page_Admin_Trips_Details.Button_Ok());
                log("I should able to navigate back to Live Deliveries.","I have navigated back to Live Deliveries", true);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I change the \"([^\"]*)\"$")
    public void i_change_the_something(String price) throws Throwable {
        try{
            switch (price){
                case "Customer price":
                    String newCustomerPrice = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
                    action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Customer_Price(),newCustomerPrice);
                    break;

                case "Driver cut":
                    String newDriverCut = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                    action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Driver_Cut(),newDriverCut);
                    break;
            }
            log("I should be able to override the customer price and driver cut",
                    "I could override the customer price and driver cut",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I change the \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_change_the_something_for_something(String price, String type) throws Throwable {
        try{
            switch (type){
                case "Service level-duo":
                    switch (price){
                        case "Driver cut":
                            String oldDriverCut = (String) cucumberContextManager.getScenarioContext("OLD_DRIVER_CUT");
                            String newDriverCut = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
                            action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Driver_Cut(),newDriverCut);
                            action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Driver_Cut_Duo(),oldDriverCut);
                            break;

                    }
                    log("I should be able to override the driver cut for duo delivery",
                            "I could override the driver cut for duo delivery",false);
            }

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I select Reason as \"([^\"]*)\"$")
    public void i_select_reason_as_something(String reason) throws Throwable {
        try{
            switch (reason){
                case "Custom Quote":
                    Select selectOverrideReason = new Select((WebElement) admin_tripDetailsPage.Dropdown_Reason_Override_Customer_Price());
                    selectOverrideReason.selectByVisibleText("Custom Quote");
                    break;

                case "Driver Incentive":
                    Select selectDriverOverrideReason = new Select((WebElement) admin_tripDetailsPage.Dropdown_Reason_Override_Driver_Cut());
                    selectDriverOverrideReason.selectByVisibleText("Driver Incentive");
                    break;
            }
            log("I should be able to select reason to override the customer price and driver cut",
                    "I could select reason to override the customer price and driver cut",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^I click on \"([^\"]*)\" button on price override pop-up$")
    public void i_click_on_something_button_on_price_override_popup(String button) throws Throwable {
        try {
            switch (button){
                case "Save":
                    action.click(admin_tripDetailsPage.Button_Save());
                    break;
                case "Ok":
                    action.click(admin_tripDetailsPage.Button_Success_Ok());
                    break;
                case "Cancel":
                    action.click(admin_tripDetailsPage.Button_Override_Cancel());
                    break;
            }
            log("I should be able to click on the "+button+"button",
                    "I could click on the "+button+"button",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^I check if values of \"([^\"]*)\" and \"([^\"]*)\" remain unchanged$")
    public void i_check_if_values_of_something_and_something_remain_unchanged(String strArg1, String strArg2) throws Throwable {
       try{
            action.refreshPage();
           String CustomerPrice = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
           String actualCustomerPrice = CustomerPrice.substring(1);
           String expectedCustomerPrice = (String) cucumberContextManager.getScenarioContext("OLD_CUSTOMER_PRICE");
           testStepAssert.isEquals(actualCustomerPrice,expectedCustomerPrice,
                   "Estimated Charges should not be overridden",
                   "Estimated Charges are not overridden",
                   "Estimated Charges are overridden");


           String driverCut = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
           String actualDriverCut = driverCut.substring(1);
           String expectedDriverCut = (String) cucumberContextManager.getScenarioContext("OLD_DRIVER_CUT");
           testStepAssert.isEquals(actualDriverCut,expectedDriverCut,
                   "Estimated Charges should not be overridden",
                   "Estimated Charges are not overridden",
                   "Estimated Charges are overridden");
       }
       catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }
    @Then("^I check if the delivery cost is updated$")
    public void i_check_if_the_delivery_cost_is_updated() throws Throwable {
        try{
            String expectedEstimatedCharges = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
            String actualDeliveryCost = action.getText(admin_tripDetailsPage.Text_Partner_Delivery_Cost());
            testStepAssert.isEquals(actualDeliveryCost,expectedEstimatedCharges,"Driver Charges should not be overridden","Driver Charges are not overridden","Driver Charges are overridden");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I check if the \"([^\"]*)\" menu is displayed$")
    public void i_check_if_the_something_menu_is_displayed(String menuName) throws Throwable {
        try{
            switch (menuName){
                case "Customer price":
                    testStepAssert.isElementDisplayed(admin_tripDetailsPage.Textbox_Override_Customer_Price(),"I should be able to check if Customer price is displayed","I could check if Customer price is displayed","Customer price is not displayed");
                    break;
                case "Driver cut":
                    testStepAssert.isElementDisplayed(admin_tripDetailsPage.Textbox_Override_Driver_Cut(),"I should be able to check if Driver cut is displayed","I could check if Driver cut is displayed","Driver cut is not displayed");
                    break;
            }
            log("I should be able to check if "+menuName+"is displayed",
                    "I could check if "+menuName+"is displayed",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("^I check if the delivery cost is updated on partner portal$")
    public void i_check_if_the_delivery_cost_is_updated_on_partner_portal() throws Throwable {
        try {
            String expectedEstimatedCharges = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
            String estimatedCharges = action.getText(partner_deliveryPage.Text_Estiated_Cost());
            String actualestimatedCharges = estimatedCharges.substring(1);
            testStepAssert.isEquals(actualestimatedCharges, expectedEstimatedCharges,
                    "Estimated Charges should not be overridden",
                    "Estimated Charges are not overridden",
                    "Estimated Charges are overridden");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @And("^I increase the \"([^\"]*)\" more than \"([^\"]*)\"$")
    public void i_increase_the_something_more_than_something(String strArg1, String strArg2) throws Throwable {
        try {
            String newCustomerPrice = (String) cucumberContextManager.getScenarioContext("NEW_CUSTOMER_PRICE");
            action.clearSendKeys(admin_tripDetailsPage.Textbox_Override_Driver_Cut(), newCustomerPrice);

            log("I should be able to change customer price",
                    "I could change customer price",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^\"([^\"]*)\" error message should be displayed$")
    public void something_error_message_should_be_displayed(String errorMessage) throws Throwable {
        try{
            testStepAssert.isElementDisplayed(admin_tripDetailsPage.Text_Driver_Cut_Error(),errorMessage,errorMessage+" is displayed",errorMessage+"is not displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^I check the new values of \"([^\"]*)\" and \"([^\"]*)\" for changed \"([^\"]*)\"$")
    public void i_check_the_new_values_of_something_and_something_for_changed_something(String strArg1, String strArg2, String type) throws Throwable {

        try{
            switch (type){
                case "Service level":
                    action.refreshPage();
                    String estimatedCharges = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                    String actualEstimatedCharges = estimatedCharges.substring(1);
                    String expectedEstimatedCharges = (String) cucumberContextManager.getScenarioContext("OLD_CUSTOMER_PRICE");
                    testStepAssert.isEquals(actualEstimatedCharges,expectedEstimatedCharges,
                            "Estimated Charges should be overridden",
                            "Estimated Charges are overridden",
                            "Estimated Charges are not overridden");


                    String driverCharges = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings());
                    String actualDriverCharges = driverCharges.substring(1);
                    String expectedDriverCharges  = (String) cucumberContextManager.getScenarioContext("OLD_DRIVER_CUT");
                    testStepAssert.isEquals(actualDriverCharges,expectedDriverCharges,
                            "Driver Charges should be overridden",
                            "Driver Charges are overridden",
                            "Driver Charges are not overridden");
                    break;

                case "Service level - fnd":
                    action.refreshPage();
                    String estimatedChargesFnd = action.getText(admin_tripDetailsPage.Text_Estimated_Charge());
                    String actualEstimatedChargesFnd = estimatedChargesFnd.substring(1);
                    String expectedEstimatedChargesFnd = (String) cucumberContextManager.getScenarioContext("OLD_CUSTOMER_PRICE");
                    testStepAssert.isEquals(actualEstimatedChargesFnd,expectedEstimatedChargesFnd,
                            "Estimated Charges should be overridden",
                            "Estimated Charges are overridden",
                            "Estimated Charges are not overridden");

                    String goodsWeight= (String) cucumberContextManager.getScenarioContext("Weight");
                    String driverChargesFnd = action.getText(admin_tripDetailsPage.Text_Driver_Est_Eranings_Fnd(goodsWeight));
                    String actualDriverChargesFnd = driverChargesFnd.substring(1);
                    String expectedDriverChargesFnd  = (String) cucumberContextManager.getScenarioContext("OLD_DRIVER_CUT");
                    testStepAssert.isEquals(actualDriverChargesFnd,expectedDriverChargesFnd,
                            "Driver Charges should be overridden",
                            "Driver Charges are overridden",
                            "Driver Charges are not overridden");
                    break;

            }

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^I check if \"([^\"]*)\" button is not present$")
    public void i_check_if_something_button_is_not_present(String strArg1) throws Throwable {
        try{
            action.isElementPresent(admin_tripDetailsPage.Button_Price_Override(true));

            log("I should not be able to see Price Override button",
                    "I could not see the Price Override button",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }



}