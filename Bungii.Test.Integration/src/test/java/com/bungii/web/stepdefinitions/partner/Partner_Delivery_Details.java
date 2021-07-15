package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.JavascriptExecutor;


import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Partner_Delivery_Details extends DriverBase {

    private static LogUtility logger = new LogUtility(DashBoardSteps.class);

    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();
    ActionManager action = new ActionManager();

    @When("^I enter following details on \"([^\"]*)\" for \"([^\"]*)\" on partner screen$")
    public void i_enter_following_details_on_some_partner_screen(String str, String Site, DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String Items_deliver = dataMap.get("Items_To_Deliver").trim();
            String CustomerName = dataMap.get("Customer_Name").trim();
            //String SpecialInstruction = dataMap.get("Special_Instruction").trim();
            cucumberContextManager.setScenarioContext("Customer_Name", CustomerName);
            //for admin trips step on admin portal
            cucumberContextManager.setScenarioContext("CUSTOMER", CustomerName);
            //cucumberContextManager.setScenarioContext("Customer", CustomerName);
            String CustomerMobile = dataMap.get("Customer_Mobile").trim();
            cucumberContextManager.setScenarioContext("CustomerPhone", CustomerMobile);
            String PickupContactName = dataMap.get("Pickup_Contact_Name").trim();
            String PickupContactPhone = dataMap.get("Pickup_Contact_Phone").trim();

            if (Site.equalsIgnoreCase("normal")) {
                switch (str) {
                    case "Delivery Details":
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Item_To_Deliver(), Items_deliver);
                        //action.clearSendKeys(Page_Partner_Delivery.TextBox_Special_Intruction(),SpecialInstruction);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Customer_Name(), CustomerName);
                        //cucumberContextManager.setScenarioContext("CUSTOMER_MOBILE", CustomerMobile);
                        action.click(Page_Partner_Delivery.TextBox_Customer_Mobile());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Customer_Mobile(), CustomerMobile);

                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Name(), PickupContactName);
                        action.click(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone(), PickupContactPhone);

                        String scheduled_date_time = action.getText(Page_Partner_Delivery.Label_Pickup_Date_Time());
                        cucumberContextManager.setScenarioContext("Schedule_Date_Time", scheduled_date_time);

                        break;
                    default:
                        break;
                }
            }
            log("I enter following details on  "+str+" for "+Site+" on partner screen", "I have entered following details on "+str+" for "+Site+" on partner screen", false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @When("^I enter all details on \"([^\"]*)\" for \"([^\"]*)\" on partner screen$")
    public void i_enter_all_details_on_some_partner_screen(String str, String Site, DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            cucumberContextManager.setScenarioContext("Site", Site);
            String CustomerName = "";
            String CustomerMobile = "";
            String ReceiptNumber = "";
            String OrderNumber = "";
            String SoldBuy = "";
            String Items_deliver = dataMap.get("Items_To_Deliver").trim();
            if (dataMap.containsKey("Customer_Name")) {
                CustomerName = dataMap.get("Customer_Name").trim();
                cucumberContextManager.setScenarioContext("Customer_Name", CustomerName);
            }
            if (dataMap.containsKey("Customer_Mobile")) {
                CustomerMobile = dataMap.get("Customer_Mobile").trim();
                cucumberContextManager.setScenarioContext("CustomerPhone", CustomerMobile);
            }
            if (dataMap.containsKey("Receipt_Number")) {
                ReceiptNumber = dataMap.get("Receipt_Number").trim();
            }
            if (dataMap.containsKey("Order_Number")) {
                OrderNumber = dataMap.get("Order_Number").trim();
            }
            if (dataMap.containsKey("SoldBuy")) {
                SoldBuy = dataMap.get("SoldBuy").trim();
            }

            //cucumberContextManager.setScenarioContext("Customer", CustomerName);
            String SpecialInstruction = dataMap.get("Special_Instruction").trim();

            String PickupContactName = dataMap.get("Pickup_Contact_Name").trim();
            String PickupContactPhone = dataMap.get("Pickup_Contact_Phone").trim();

            String DropOffContactName = dataMap.get("Drop_Off_Contact_Name").trim();
            String DropOffContactPhone = dataMap.get("Drop_Contact_Phone").trim();
            //String ReceiptNumber = dataMap.get("Receipt_Number").trim();


            if (Site.equalsIgnoreCase("normal")) {
                switch (str) {
                    case "Delivery Details":
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Item_To_Deliver(), Items_deliver);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Special_Intruction(), SpecialInstruction);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Customer_Name(), CustomerName);
                        action.click(Page_Partner_Delivery.TextBox_Customer_Mobile());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Customer_Mobile(), CustomerMobile);


                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Name(), PickupContactName);
                        action.click(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone(), PickupContactPhone);

                        String scheduled_date_time = action.getText(Page_Partner_Delivery.Label_Pickup_Date_Time());
                        cucumberContextManager.setScenarioContext("Schedule_Date_Time", scheduled_date_time);

                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Name(), DropOffContactName);
                        action.click(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Phone());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Phone(), DropOffContactPhone);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Receipt_Number(), ReceiptNumber);

                        break;
                    default:
                        break;
                }
            } else if (Site.equalsIgnoreCase("kiosk mode")) {
                switch (str) {
                    case "Delivery Details":
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Item_To_Deliver(), Items_deliver);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Special_Intruction(), SpecialInstruction);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Customer_Name(), CustomerName);
                        //cucumberContextManager.setScenarioContext("CUSTOMER_MOBILE", CustomerMobile);
                        action.click(Page_Partner_Delivery.TextBox_Customer_Mobile());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Customer_Mobile(), CustomerMobile);

                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Name(), PickupContactName);
                        action.click(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone(), PickupContactPhone);

                        String scheduled_date_time1 = action.getText(Page_Partner_Delivery.Label_Pickup_Date_Time());
                        cucumberContextManager.setScenarioContext("Schedule_Date_Time", scheduled_date_time1);

                        break;
                    default:
                        break;
                }

            } else if (Site.equalsIgnoreCase("service level")) {
                switch (str) {
                    case "Delivery Details":
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Item_To_Deliver(), Items_deliver);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Special_Intruction(), SpecialInstruction);

                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Name(), PickupContactName);
                        action.click(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone(), PickupContactPhone);

                        String scheduled_date_time = action.getText(Page_Partner_Delivery.Label_Pickup_Date_Time());
                        cucumberContextManager.setScenarioContext("Schedule_Date_Time", scheduled_date_time);
                        cucumberContextManager.setScenarioContext("Customer_Name", Page_Partner_Delivery.TextBox_Customer_Name().getAttribute("value"));
                        String Mobile_number = Page_Partner_Delivery.TextBox_Customer_Mobile().getAttribute("value");
                        Mobile_number = Mobile_number.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
                        cucumberContextManager.setScenarioContext("Customer_Mobile", Mobile_number);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Name(), DropOffContactName);
                        action.click(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Phone());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Phone(), DropOffContactPhone);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Receipt_Number(), ReceiptNumber);

                        break;
                    default:
                        break;
                }

            } else if (Site.equalsIgnoreCase("BestBuy service level")) {

                switch (str) {
                    case "Delivery Details":
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_SKU(), Items_deliver);
                        action.click(Page_Partner_Delivery.Button_SKU_Add());
                        //action.clearSendKeys(Page_Partner_Delivery.TextBox_Special_Intruction(), SpecialInstruction);

                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Customer_Name(), CustomerName);
                        //cucumberContextManager.setScenarioContext("CUSTOMER_MOBILE", CustomerMobile);
                        action.click(Page_Partner_Delivery.TextBox_Customer_Mobile());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Customer_Mobile(), CustomerMobile);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Name(), PickupContactName);
                        action.click(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone(), PickupContactPhone);

                        String scheduled_date_time = action.getText(Page_Partner_Delivery.Label_Pickup_Date_Time());
                        cucumberContextManager.setScenarioContext("Schedule_Date_Time", scheduled_date_time);
                        cucumberContextManager.setScenarioContext("Customer_Name", Page_Partner_Delivery.TextBox_Customer_Name().getAttribute("value"));
                        cucumberContextManager.setScenarioContext("Customer_Mobile", Page_Partner_Delivery.TextBox_Customer_Mobile().getAttribute("value"));
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Name(), DropOffContactName);
                        action.click(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Phone());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Phone(), DropOffContactPhone);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Order_Number(), OrderNumber);
                        action.click(Page_Partner_Delivery.Dropdown_SoldBuy());
                        action.click(Page_Partner_Delivery.List_StoreAssociate("Krishna"));


                        break;
                    default:
                        break;
                }
                log("I enter all details on "+str+" for "+Site+" on partner screen", "I have entered all details on "+str+" for "+Site+" on partner screen", false);

            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("^I enter the value \"([^\"]*)\" in Scheduled by field$")
    public void i_enter_the_some_value_in_scheduled_by_field(String scheduled_by) {
        try{
        action.click(Page_Partner_Delivery.TextBox_Scheduled_By());
        action.clearSendKeys(Page_Partner_Delivery.TextBox_Scheduled_By(), scheduled_by);
        log("I should able to enter " + scheduled_by + " in Scheduled by field", "I entered " + scheduled_by + " in Scheduled by field.", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I confirm details show in summary$")
    public void i_confirm_details_shown_in_summary() {
        try{
        String Bungii_type = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
        if (Bungii_type.equalsIgnoreCase("Solo")) {
            testStepVerify.isElementTextEquals(Page_Partner_Delivery.Text_Driver_Truck(), "Solo - 1 driver 1 truck");
        } else {
            testStepVerify.isElementTextEquals(Page_Partner_Delivery.Text_Driver_Truck(), "Duo - 2 driver 2 truck");
        }

        String Pickup_Address = (String) cucumberContextManager.getScenarioContext("PickupAddress");
        testStepVerify.isElementTextEquals(Page_Partner_Delivery.Text_Pick_Address(), Pickup_Address);

        String DeliveryAddress = (String) cucumberContextManager.getScenarioContext("Delivery_Address");
        testStepVerify.isElementTextEquals(Page_Partner_Delivery.Text_Delivery_Address(), DeliveryAddress);

        String EstimatedCost = (String) cucumberContextManager.getScenarioContext("Estimated_Cost");
        testStepVerify.isElementTextEquals(Page_Partner_Delivery.Text_Estiated_Cost(), EstimatedCost);

        log("I should able to confirm details shown in summary.", "I am able to confirmed details shown in summary.", false);
    } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should \"([^\"]*)\" on Delivery Details screen$")
    public void i_should_see_something_on_delivery_details_screen(String str) {
        try {
            switch (str) {
                case "see validations message for blank Items To Deliver field":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Message_Black_Item_To_Deliver()), PropertyUtility.getMessage("Message_Blank_Item_To_Deliver"));
                    break;
                case "see validations message for blank Customer Name field":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Message_Black_Customer_Name()), PropertyUtility.getMessage("Message_Blank_Customer"));
                    break;
                case "see validations message for blank Customer Mobile field":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Message_Blank_Customer_Mobile()), PropertyUtility.getMessage("Message_Blank_CustomerMobile"));
                    break;
                case "see validations message for blank Pickup Contact Name field":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Message_Blank_Pickup_Contact_Name()), PropertyUtility.getMessage("Message_Blank_Pickup_Contact_Name"));
                    break;
                case "see validations message for blank Pickup Contact Phone field":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Message_Blank_Pickup_Contact_Phone()), PropertyUtility.getMessage("Message_Blank_Pickup_Contact_Phone"));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
}
