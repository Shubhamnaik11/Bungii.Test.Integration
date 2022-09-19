package com.bungii.web.stepdefinitions.partner;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_DriversPage;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import com.bungii.web.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Partner_Delivery_Details extends DriverBase {

    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();
    ActionManager action = new ActionManager();
    DbUtility dbUtility = new DbUtility();
    Admin_DriversPage admin_DriverPage=new Admin_DriversPage();


    @When("^I enter following details on \"([^\"]*)\" for \"([^\"]*)\" on partner screen$")
    public void i_enter_following_details_on_some_partner_screen(String str, String Site, DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String Items_deliver = dataMap.get("Items_To_Deliver").trim();
            cucumberContextManager.setScenarioContext("Item_Name",Items_deliver);
            String CustomerName = dataMap.get("Customer_Name").trim();
            //String SpecialInstruction = dataMap.get("Special_Instruction").trim();
            cucumberContextManager.setScenarioContext("Customer_Name", CustomerName);
            //for admin trips step on admin portal
            cucumberContextManager.setScenarioContext("CUSTOMER", CustomerName);
            //cucumberContextManager.setScenarioContext("Customer", CustomerName);
            String CustomerMobile = dataMap.get("Customer_Mobile").trim();
            cucumberContextManager.setScenarioContext("CustomerPhone", CustomerMobile);
            String PickupContactName = dataMap.get("Pickup_Contact_Name").trim();
            cucumberContextManager.setScenarioContext("PickupContactName",PickupContactName);
            String PickupContactPhone = dataMap.get("Pickup_Contact_Phone").trim();
            cucumberContextManager.setScenarioContext("PickupContactPhone",PickupContactPhone);

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
            String ProductDescription = "";
            String Dimensions = "";
            String Weight = "";
            String DeliveryPurpose = "";
            String RbSbNumber = "";
            String Items_deliver = "";
            String BodcCode ="";
            String bidderNumber = "";


            if(dataMap.containsKey("Items_To_Deliver")){
                Items_deliver = dataMap.get("Items_To_Deliver");
            }
            if(dataMap.containsKey("Product_Description")) {
                ProductDescription = dataMap.get("Product_Description").trim();
                cucumberContextManager.setScenarioContext("Product_Description",ProductDescription);
            }
            if(dataMap.containsKey("Dimensions")){
                Dimensions = dataMap.get("Dimensions").trim();
                cucumberContextManager.setScenarioContext("Dimensions",Dimensions);
            }
            if(dataMap.containsKey("Weight")){
                Weight = dataMap.get("Weight").trim();
                cucumberContextManager.setScenarioContext("Weight",Weight);
            }
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
            if (dataMap.containsKey("Delivery_Purpose")) {
                DeliveryPurpose = dataMap.get("Delivery_Purpose").trim();
            }
            if (dataMap.containsKey("Rb_Sb_Number")) {
                RbSbNumber = dataMap.get("Rb_Sb_Number").trim();
            }
            if (dataMap.containsKey("Bodc_Code")) {
                BodcCode = dataMap.get("Bodc_Code").trim();
            }

            if(dataMap.containsKey("Bidder_Number")){
                bidderNumber = dataMap.get("Bidder_Number");
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

            } else if (Site.equalsIgnoreCase("Equip-bid")) {

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
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_LotNumber(), String.valueOf(ThreadLocalRandom.current().nextInt()));
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Delivery_Purpose(),DeliveryPurpose);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_BidderNumber(),bidderNumber);
                        action.click(Page_Partner_Delivery.Checkbox_Helper());

                        String scheduled_date_time1 = action.getText(Page_Partner_Delivery.Label_Pickup_Date_Time());
                        cucumberContextManager.setScenarioContext("Schedule_Date_Time", scheduled_date_time1);

                        break;
                    default:
                        break;
                }
                log("I enter all details on "+str+" for "+Site+" on partner screen", "I have entered all details on "+str+" for "+Site+" on partner screen", false);

            } else if (Site.equalsIgnoreCase("FloorDecor service level")) {

                switch (str) {
                    case "Delivery Details":
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Product_Description(), ProductDescription);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Dimensions(),Dimensions);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Weight(),Weight);
                        //action.click(Page_Partner_Delivery.Button_SKU_Add());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Special_Intruction(), SpecialInstruction);

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
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Delivery_Purpose(),DeliveryPurpose);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Rb_Sb_Number(),RbSbNumber);
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_SoldBy(),SoldBuy);


                        break;
                    default:
                        break;
                }
                log("I enter all details on "+str+" for "+Site+" on partner screen", "I have entered all details on "+str+" for "+Site+" on partner screen", false);

            }
            else if (Site.equalsIgnoreCase("Cort service level")) {

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

                        String scheduled_date_time = action.getText(Page_Partner_Delivery.Label_Pickup_Date_Time());
                        cucumberContextManager.setScenarioContext("Schedule_Date_Time", scheduled_date_time);
                        cucumberContextManager.setScenarioContext("Customer_Name", Page_Partner_Delivery.TextBox_Customer_Name().getAttribute("value"));
                        cucumberContextManager.setScenarioContext("CUSTOMER", Page_Partner_Delivery.TextBox_Customer_Name().getAttribute("value"));
                        cucumberContextManager.setScenarioContext("Customer_Mobile", Page_Partner_Delivery.TextBox_Customer_Mobile().getAttribute("value"));
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Name(), DropOffContactName);
                        action.click(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Phone());
                        action.clearSendKeys(Page_Partner_Delivery.TextBox_Drop_Off_Contact_Phone(), DropOffContactPhone);

                        action.click(Page_Partner_Delivery.Dropdown_SoldBuy());
                        action.click(Page_Partner_Delivery.List_StoreAssociate(BodcCode));

                        break;
                    default:
                        break;
                }
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

    @And("^I select the value in Bodc Code$")
    public void i_select_the_value_in_bodc_code() throws Throwable{
       try {
           action.click(Page_Partner_Delivery.Dropdown_BodcCode());
           action.click(Page_Partner_Delivery.Dropdown_BodcCodeValue());
           String BodcCode=action.getText(Page_Partner_Delivery.Dropdown_BodcCode());
           log("I should able to select SVC2/09/00 in Scheduled by field", "I selected " + BodcCode + " in Scheduled by field.", false);
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }

    @Then("^Partner invoice should be selected as default Payment Method$")
    public void partner_invoice_should_be_selected_as_default_payment_method() throws Throwable {
        try {
            testStepAssert.isTrue(Page_Partner_Delivery.RadioButton_PartnerInvoice().isSelected(), "Partner Invoice is selected by default", "Partner Invoice is Not selected by Default");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I check the Bodc Code dropdown options$")
    public void i_check_the_bodc_code_dropdown_options() throws Throwable {
        try {
            String currentUrl= action.getCurrentURL();
            int indexValueOne = currentUrl.indexOf("/",(currentUrl.indexOf("/") + 1));
            int indexValueTwo = currentUrl.indexOf(".");
            String subDomainName= currentUrl.substring(indexValueOne+1,indexValueTwo);
            List<String> expectedOptions= dbUtility.getBodcCode(subDomainName);

            action.click(Page_Partner_Delivery.Dropdown_BodcCode());
            List<WebElement> actualOptions = Page_Partner_Delivery.Dropdown_BodcCodeOptions();
            List<String> Options= new ArrayList();
            int size = actualOptions.size();
            for (int i = 0; i < size; i++)
            {
                String options = actualOptions.get(i).getText();
                Options.add(options);
            }
            action.click(Page_Partner_Delivery.Dropdown_BodcCodeValue());
            testStepAssert.isTrue(Options.containsAll(expectedOptions), "Correct dropdown options need to be displayed", "Correct dropdown options are displayed", "Incorrect dropdown options are displayed");
        }

        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);

        }
    }

    @Then("^I should see the delivery status highlighted and to be set as \"([^\"]*)\" on partner portal delivery details page$")
    public void i_should_see_the_delivery_status_highlighted_and_to_be_set_as_something_on_partner_portal_delivery_details_page(String deliveryStatus) throws Throwable {
       try {
           Thread.sleep(3000);
           String dbDeliveryStepCompletionTime = (String) cucumberContextManager.getScenarioContext("DeliveryStepCompletionTime").toString().trim();
           String dbDeliveryStepCompletionTime1minuteAhead = (String) cucumberContextManager.getScenarioContext("hourFormat12Hr1MinuteAhead").toString().trim();
           String dbDeliveryStepCompletionTime1MinuteBack = (String) cucumberContextManager.getScenarioContext("hourFormat12Hr1MinuteBack").toString().trim();

           String uiDeliveryStatus = action.getText(Page_Partner_Delivery.Text_PartnerDeliveryStatus(deliveryStatus)).trim();

           switch (deliveryStatus) {
               case "Scheduled":
                   String[] fullScheduledStepCompletionText = action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(1)).split(" ");
                   String properScheduledStepCompletionTime = fullScheduledStepCompletionText[4] + " " + fullScheduledStepCompletionText[5];

                   if(properScheduledStepCompletionTime.equals(dbDeliveryStepCompletionTime)) {

                       testStepAssert.isEquals(properScheduledStepCompletionTime, dbDeliveryStepCompletionTime, "Schedule time should be  "+dbDeliveryStepCompletionTime, "Schedule time is   "+properScheduledStepCompletionTime, "Schedule time is not  "+dbDeliveryStepCompletionTime);

                   }

                   else if((properScheduledStepCompletionTime.equals(dbDeliveryStepCompletionTime1minuteAhead))) {

                       testStepAssert.isEquals(properScheduledStepCompletionTime, dbDeliveryStepCompletionTime1minuteAhead, "Schedule time should be  "+dbDeliveryStepCompletionTime1minuteAhead, "Schedule time is   "+properScheduledStepCompletionTime, "Schedule time is not  "+dbDeliveryStepCompletionTime1minuteAhead);

                   }
                   else{

                       testStepAssert.isEquals(properScheduledStepCompletionTime, dbDeliveryStepCompletionTime1MinuteBack, "Schedule time should be  "+dbDeliveryStepCompletionTime1MinuteBack, "Schedule time is   "+properScheduledStepCompletionTime, "Schedule time is not  "+dbDeliveryStepCompletionTime1MinuteBack);

                   }
                   testStepAssert.isEquals(uiDeliveryStatus, deliveryStatus, "Delivery should be in " + deliveryStatus + " state", "Delivery is in " + deliveryStatus + " state", "Delivery is not in " + deliveryStatus + " state");
                   break;

               case "En Route To Pickup":
                   String[] fullEnrouteStepCompletionText = action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(2)).split(" ");
                   String properEnrouteStepCompletionTime = fullEnrouteStepCompletionText[4] + " " + fullEnrouteStepCompletionText[5];

                   if(properEnrouteStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime)) {

                       testStepAssert.isEquals(properEnrouteStepCompletionTime, dbDeliveryStepCompletionTime, "Enroute to pickup time should be  "+dbDeliveryStepCompletionTime, "Enroute to pickup time is   "+properEnrouteStepCompletionTime, "Enroute to pickup time is not  "+properEnrouteStepCompletionTime);

                   }

                   else if((properEnrouteStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime1minuteAhead))) {

                       testStepAssert.isEquals(properEnrouteStepCompletionTime, dbDeliveryStepCompletionTime1minuteAhead, "Enroute to pickup time should be  "+dbDeliveryStepCompletionTime1minuteAhead, "Enroute to pickup time is   "+properEnrouteStepCompletionTime, "Enroute to pickup time is not  "+dbDeliveryStepCompletionTime1minuteAhead);

                   }
                   else{

                       testStepAssert.isEquals(properEnrouteStepCompletionTime, dbDeliveryStepCompletionTime1MinuteBack, "Enroute to pickup time should be  "+dbDeliveryStepCompletionTime1MinuteBack, "Enroute to pickup time is  "+properEnrouteStepCompletionTime, "Enroute to pickup time is not  "+dbDeliveryStepCompletionTime1MinuteBack);

                   }
                   testStepAssert.isEquals(uiDeliveryStatus, deliveryStatus, "Delivery should be in " + deliveryStatus + " state", "Delivery is in " + deliveryStatus + " state", "Delivery is not in " + deliveryStatus + " state");
                   break;
               case "Driver Arrived At Pickup":
                   String[] fullDriverArrivedStepCompletionText = action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(3)).split(" ");
                   String properDriverArrivedStepCompletionTime = fullDriverArrivedStepCompletionText[4] + " " + fullDriverArrivedStepCompletionText[5];
                   if(properDriverArrivedStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime)) {

                       testStepAssert.isEquals(properDriverArrivedStepCompletionTime, dbDeliveryStepCompletionTime, "Driver Arrived At Pickup time should be  "+dbDeliveryStepCompletionTime, "Driver Arrived At Pickup time is   "+properDriverArrivedStepCompletionTime, "Driver Arrived At Pickup time is not  "+dbDeliveryStepCompletionTime);

                   }

                   else if((properDriverArrivedStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime1minuteAhead))) {

                       testStepAssert.isEquals(properDriverArrivedStepCompletionTime, dbDeliveryStepCompletionTime1minuteAhead, "Driver Arrived At Pickup time should be  "+dbDeliveryStepCompletionTime1minuteAhead, "Driver Arrived At Pickup time is   "+properDriverArrivedStepCompletionTime, "Driver Arrived At Pickup time is not  "+dbDeliveryStepCompletionTime1minuteAhead);

                   }
                   else{

                       testStepAssert.isEquals(properDriverArrivedStepCompletionTime, dbDeliveryStepCompletionTime1MinuteBack, "Driver Arrived At Pickup time should be  "+dbDeliveryStepCompletionTime1MinuteBack, "Driver Arrived At Pickup time is   "+properDriverArrivedStepCompletionTime, "Driver Arrived At Pickup time is not  "+dbDeliveryStepCompletionTime1MinuteBack);

                   }
                   testStepAssert.isEquals(uiDeliveryStatus, deliveryStatus, "Delivery should be in " + deliveryStatus + " state", "Delivery is in " + deliveryStatus + " state", "Delivery is not in " + deliveryStatus + " state");
                   break;
               case "Loading Items":
                   String[] fullLoadingItemsStepCompletionText = action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(4)).split(" ");
                   String properLoadingItemsStepCompletionTime = fullLoadingItemsStepCompletionText[4] + " " + fullLoadingItemsStepCompletionText[5];
                   if(properLoadingItemsStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime)) {

                       testStepAssert.isEquals(properLoadingItemsStepCompletionTime, dbDeliveryStepCompletionTime, "Loading Items time should be  "+dbDeliveryStepCompletionTime, "Loading Items time is   "+properLoadingItemsStepCompletionTime, "Loading Items time is not  "+dbDeliveryStepCompletionTime);

                   }

                   else if((properLoadingItemsStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime1minuteAhead))) {

                       testStepAssert.isEquals(properLoadingItemsStepCompletionTime, dbDeliveryStepCompletionTime1minuteAhead, "Loading Items time should be  "+dbDeliveryStepCompletionTime1minuteAhead, "Loading Items time is   "+properLoadingItemsStepCompletionTime, "Loading Items time is not  "+dbDeliveryStepCompletionTime1minuteAhead);

                   }
                   else{

                       testStepAssert.isEquals(properLoadingItemsStepCompletionTime, dbDeliveryStepCompletionTime1MinuteBack, "Loading Items time should be  "+dbDeliveryStepCompletionTime1MinuteBack, "Loading Items time is   "+properLoadingItemsStepCompletionTime, "Loading Items time is not  "+dbDeliveryStepCompletionTime1MinuteBack);

                   }
                   testStepAssert.isEquals(uiDeliveryStatus, deliveryStatus, "Delivery should be in " + deliveryStatus + " state", "Delivery is in " + deliveryStatus + " state", "Delivery is not in " + deliveryStatus + " state");
                   break;

               case "Driving To Drop Off":
                   String[] fullDrivingToDropoffStepCompletionText = action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(5)).split(" ");
                   String properDrivingToDropoffStepCompletionTime = fullDrivingToDropoffStepCompletionText[4] + " " + fullDrivingToDropoffStepCompletionText[5];
                   if(properDrivingToDropoffStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime)) {

                       testStepAssert.isEquals(properDrivingToDropoffStepCompletionTime, dbDeliveryStepCompletionTime, "Driving To Drop Off time should be  "+dbDeliveryStepCompletionTime, "Driving To Drop Off time is   "+properDrivingToDropoffStepCompletionTime, "Driving To Drop Off time is not  "+dbDeliveryStepCompletionTime);

                   }

                   else if((properDrivingToDropoffStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime1minuteAhead))) {

                       testStepAssert.isEquals(properDrivingToDropoffStepCompletionTime, dbDeliveryStepCompletionTime1minuteAhead, "Driving To Drop Off time should be  "+dbDeliveryStepCompletionTime1minuteAhead, "Driving To Drop Off time is   "+properDrivingToDropoffStepCompletionTime, "Driving To Drop Off time is not  "+dbDeliveryStepCompletionTime1minuteAhead);

                   }
                   else{

                       testStepAssert.isEquals(properDrivingToDropoffStepCompletionTime, dbDeliveryStepCompletionTime1MinuteBack, "Driving To Drop Off time should be  "+dbDeliveryStepCompletionTime1MinuteBack, "Driving To Drop Off time is   "+properDrivingToDropoffStepCompletionTime, "Driving To Drop Off time is not  "+dbDeliveryStepCompletionTime1MinuteBack);

                   }
                   testStepAssert.isEquals(uiDeliveryStatus, deliveryStatus, "Delivery should be in " + deliveryStatus + " state", "Delivery is in " + deliveryStatus + " state", "Delivery is not in " + deliveryStatus + " state");
                   break;
               case "Unloading Items":
                   String[] fullUnloadingItemsStepCompletionText = action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(6)).split(" ");
                   String fullUnloadingItemsCompletionTime = fullUnloadingItemsStepCompletionText[4] + " " + fullUnloadingItemsStepCompletionText[5];
                   if(fullUnloadingItemsCompletionTime.contentEquals(dbDeliveryStepCompletionTime)) {

                       testStepAssert.isEquals(fullUnloadingItemsCompletionTime, dbDeliveryStepCompletionTime, "Unloading Items time should be  "+dbDeliveryStepCompletionTime, "Unloading Items time is   "+fullUnloadingItemsCompletionTime, "Unloading Items time is not  "+dbDeliveryStepCompletionTime);

                   }

                   else if((fullUnloadingItemsCompletionTime.contentEquals(dbDeliveryStepCompletionTime1minuteAhead))) {

                       testStepAssert.isEquals(fullUnloadingItemsCompletionTime, dbDeliveryStepCompletionTime1minuteAhead, "Unloading Items time should be  "+dbDeliveryStepCompletionTime1minuteAhead, "Unloading Items time is   "+fullUnloadingItemsCompletionTime, "Unloading Items time is not  "+dbDeliveryStepCompletionTime1minuteAhead);

                   }
                   else{

                       testStepAssert.isEquals(fullUnloadingItemsCompletionTime, dbDeliveryStepCompletionTime1MinuteBack, "Unloading Items time should be  "+dbDeliveryStepCompletionTime1MinuteBack, "Unloading Items time is   "+fullUnloadingItemsCompletionTime, "Unloading Items time is not  "+dbDeliveryStepCompletionTime1MinuteBack);

                   }
                   testStepAssert.isEquals(uiDeliveryStatus, deliveryStatus, "Delivery should be in " + deliveryStatus + " state", "Delivery is in " + deliveryStatus + " state", "Delivery is not in " + deliveryStatus + " state");
                   break;

               case "Done":
                   String[] fullDoneStepCompletionText = action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(7)).split(" ");
                   String properDoneStepCompletionTime = fullDoneStepCompletionText[4] + " " + fullDoneStepCompletionText[5];
                   if(properDoneStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime)) {

                       testStepAssert.isEquals(properDoneStepCompletionTime, dbDeliveryStepCompletionTime, "Done time should be  "+dbDeliveryStepCompletionTime, "Done time is   "+properDoneStepCompletionTime, "Done time is not  "+dbDeliveryStepCompletionTime);

                   }

                   else if((properDoneStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime1minuteAhead))) {

                       testStepAssert.isEquals(properDoneStepCompletionTime, dbDeliveryStepCompletionTime1minuteAhead, "Done time should be  "+dbDeliveryStepCompletionTime1minuteAhead, "Done time is   "+properDoneStepCompletionTime, "Done time is not  "+dbDeliveryStepCompletionTime1minuteAhead);

                   }
                   else{

                       testStepAssert.isEquals(properDoneStepCompletionTime, dbDeliveryStepCompletionTime1MinuteBack, "Done time should be  "+dbDeliveryStepCompletionTime1MinuteBack, "Done time is   "+properDoneStepCompletionTime, "Done time is not  "+dbDeliveryStepCompletionTime1MinuteBack);

                   }
                   testStepAssert.isEquals(uiDeliveryStatus, deliveryStatus, "Delivery should be in " + deliveryStatus + " state", "Delivery is in " + deliveryStatus + " state", "Delivery is not in " + deliveryStatus + " state");
                   break;
               case "Canceled":
                   String[] fullCancellationStepCompletionTime = action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(2)).split(" ");
                   String properCanceledStepCompletionTime = fullCancellationStepCompletionTime[4].toString() + " " + fullCancellationStepCompletionTime[5].toString();
                   if(properCanceledStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime)) {

                       testStepAssert.isEquals(properCanceledStepCompletionTime, dbDeliveryStepCompletionTime, "Cancelled time should be  "+dbDeliveryStepCompletionTime, "Cancelled time is   "+properCanceledStepCompletionTime, "Cancelled time is not  "+dbDeliveryStepCompletionTime);

                   }

                   else if((properCanceledStepCompletionTime.contentEquals(dbDeliveryStepCompletionTime1minuteAhead))) {

                       testStepAssert.isEquals(properCanceledStepCompletionTime, dbDeliveryStepCompletionTime1minuteAhead, "Cancelled time should be  "+dbDeliveryStepCompletionTime1minuteAhead, "Cancelled time is   "+properCanceledStepCompletionTime, "Cancelled time is not  "+dbDeliveryStepCompletionTime1minuteAhead);

                   }
                   else{

                       testStepAssert.isEquals(properCanceledStepCompletionTime, dbDeliveryStepCompletionTime1MinuteBack, "Cancelled time should be  "+dbDeliveryStepCompletionTime1MinuteBack, "Cancelled time is   "+properCanceledStepCompletionTime, "Cancelled time is not  "+dbDeliveryStepCompletionTime1MinuteBack);

                   }
                   testStepAssert.isEquals(uiDeliveryStatus, deliveryStatus, "Delivery should be in " + deliveryStatus + " state", "Delivery is in " + deliveryStatus + " state", "Delivery is not in " + deliveryStatus + " state");
                   break;
           }
       }catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);

        }
    }

    @And("^I select \"([^\"]*)\" option from the filter$")
    public void i_select_something_option_from_the_filter(String filterOption) throws Throwable {
        try{
        Thread.sleep(3000);
        action.click(Page_Partner_Dashboard.DropDown_Filter());
        switch (filterOption){
            case "Completed":
            case "Canceled":
            case "Check / uncheck all":
                action.click(Page_Partner_Dashboard.Checkbox_Completed(filterOption));
                break;
        }
        log("I should be able to select "+ filterOption+" option from the filter","I should be able to select "+ filterOption+" option from the filter" ,false);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);

    }
}

    @And("^I save the delivery details$")
    public void i_save_the_delivery_details() throws Throwable {
        try{
        String driver = action.getText(Page_Partner_Dashboard.Text_DriverName());
        cucumberContextManager.setScenarioContext("DriverName",driver);
        log("I should be able to save the delivery details","I could save the delivery details",false);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);

    }
}

    @And("^The driver name should be changed$")
    public void the_driver_name_should_be_changed() throws Throwable {
        try{
        String newDriverName = action.getText(Page_Partner_Dashboard.Text_DriverName());
        String oldDriverName = (String) cucumberContextManager.getScenarioContext("DriverName");
        testStepAssert.isFalse(newDriverName.contentEquals(oldDriverName),"Driver name should be changed",
                "Driver name is  changed","Driver name is not changed");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);

    }
}

    @And("^I get time stamp for \"([^\"]*)\" delivery step$")
    public void i_get_time_stamp_for_something_delivery_step(String deliveryStatus) throws Throwable {
        try {
            switch (deliveryStatus) {
                case "Cancelled":
                case "Driver Cancelled":
                case "Enroute":
                case "Arrived":
                case "Loading Item":
                case "Driving To Dropoff":
                case "Unloading Item":
                case "Bungii Completed":
                case "Admin Cancelled":
                case "Partner Cancelled":
                case "Admin Completed":
                String pickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                String time = dbUtility.getStatusTimestamp(pickupRef);
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
                String timer = time.substring(11, 23);
                Time timeValue = new Time(formatter.parse(timer).getTime());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(timeValue);
                calendar.add(Calendar.MINUTE, -300);
                String timeInCST = String.valueOf(calendar.getTime());
                String timeIn24HourFormat = timeInCST.substring(11, 16);
                String hourFormat12 = LocalTime.parse(timeIn24HourFormat, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
                if (hourFormat12.startsWith("0")) {
                    String timeWithoutStartingWithZero = hourFormat12.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("DeliveryStepCompletionTime", timeWithoutStartingWithZero);
                } else {
                    cucumberContextManager.setScenarioContext("DeliveryStepCompletionTime", hourFormat12);
                }
                calendar.setTime(timeValue);
                calendar.add(Calendar.MINUTE, -301);
                String timeInCST1MinuteAhead = String.valueOf(calendar.getTime());
                String timeIn24HourFormat1MinuteAhead = timeInCST1MinuteAhead.substring(11, 16);
                String hourFormat12Hr1MinuteAhead = LocalTime.parse(timeIn24HourFormat1MinuteAhead, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
                if (hourFormat12Hr1MinuteAhead.startsWith("0")) {
                    String timeWithoutStartingWithZero1MinuteAhead = hourFormat12Hr1MinuteAhead.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("hourFormat12Hr1MinuteAhead", timeWithoutStartingWithZero1MinuteAhead);
                } else {
                    cucumberContextManager.setScenarioContext("hourFormat12Hr1MinuteAhead", hourFormat12Hr1MinuteAhead);
                }
                calendar.setTime(timeValue);
                calendar.add(Calendar.MINUTE, -299);
                String timeInCST1MinuteBack = String.valueOf(calendar.getTime());
                String timeIn24HourFormat1MinuteBack = timeInCST1MinuteBack.substring(11, 16);
                String hourFormat12Hr1MinuteBack = LocalTime.parse(timeIn24HourFormat1MinuteBack, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
                if (hourFormat12Hr1MinuteBack.startsWith("0")) {
                    String timeWithoutStartingWithZero1MinuteBack = hourFormat12Hr1MinuteAhead.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("hourFormat12Hr1MinuteBack", timeWithoutStartingWithZero1MinuteBack);
                } else {
                    cucumberContextManager.setScenarioContext("hourFormat12Hr1MinuteBack", hourFormat12Hr1MinuteBack);
                }
            }
            log("I should be able to get the timestamp of the completed step","I could  get the timestamp of the completed step",false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);

        }
    }

    @Then("^The admin \"([^\"]*)\" delivery should be highlighted in partner portal delivery details page$")
    public void the_admin_something_delivery_should_be_highlighted_in_partner_portal_delivery_details_page(String deliveryStatus) throws Throwable {
        try {

            Thread.sleep(3000);
            String dbDeliveryStepCompletionTime = (String) cucumberContextManager.getScenarioContext("DeliveryStepCompletionTime").toString().trim();
            String dbDeliveryStepCompletionTime1minuteAhead = (String) cucumberContextManager.getScenarioContext("hourFormat12Hr1MinuteAhead").toString().trim();
            String dbDeliveryStepCompletionTime1MinuteBack = (String) cucumberContextManager.getScenarioContext("hourFormat12Hr1MinuteBack").toString().trim();

            String uiDeliveryStatus = action.getText(Page_Partner_Delivery.Text_PartnerDeliveryStatus(deliveryStatus)).trim();

            switch (deliveryStatus) {
                case "Completed":
                    String[] deliveryCompletedByAdmin = action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(3)).split(" ");
                    String properDoneStepCompletionByAdmin = deliveryCompletedByAdmin[4] + " " + deliveryCompletedByAdmin[5];
                    if (properDoneStepCompletionByAdmin.contentEquals(dbDeliveryStepCompletionTime)) {

                        testStepAssert.isEquals(properDoneStepCompletionByAdmin, dbDeliveryStepCompletionTime, "Done time should be  " + dbDeliveryStepCompletionTime, "Done time is   " + properDoneStepCompletionByAdmin, "Done time is not  " + dbDeliveryStepCompletionTime);

                    } else if ((properDoneStepCompletionByAdmin.contentEquals(dbDeliveryStepCompletionTime1minuteAhead))) {

                        testStepAssert.isEquals(properDoneStepCompletionByAdmin, dbDeliveryStepCompletionTime1minuteAhead, "Done time should be  " + dbDeliveryStepCompletionTime1minuteAhead, "Done time is   " + properDoneStepCompletionByAdmin, "Done time is not  " + dbDeliveryStepCompletionTime1minuteAhead);

                    } else {

                        testStepAssert.isEquals(properDoneStepCompletionByAdmin, dbDeliveryStepCompletionTime1MinuteBack, "Done time should be  " + dbDeliveryStepCompletionTime1MinuteBack, "Done time is   " + properDoneStepCompletionByAdmin, "Done time is not  " + dbDeliveryStepCompletionTime1MinuteBack);

                    }
                    testStepAssert.isEquals(uiDeliveryStatus, deliveryStatus, "Delivery should be in " + deliveryStatus + " state", "Delivery is in " + uiDeliveryStatus + " state", "Delivery is not in " + deliveryStatus + " state");
                    break;
                case "Cancelled":
                    String[] fullCancellationStepCompletionByAdminTime = action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(3)).split(" ");
                    String properCanceledStepCompletionByAdminTime = fullCancellationStepCompletionByAdminTime[4].toString() + " " + fullCancellationStepCompletionByAdminTime[5].toString();
                    if (properCanceledStepCompletionByAdminTime.contentEquals(dbDeliveryStepCompletionTime)) {

                        testStepAssert.isEquals(properCanceledStepCompletionByAdminTime, dbDeliveryStepCompletionTime, "Cancelled time should be  " + dbDeliveryStepCompletionTime, "Cancelled time is   " + properCanceledStepCompletionByAdminTime, "Cancelled time is not  " + dbDeliveryStepCompletionTime);

                    } else if ((properCanceledStepCompletionByAdminTime.contentEquals(dbDeliveryStepCompletionTime1minuteAhead))) {

                        testStepAssert.isEquals(properCanceledStepCompletionByAdminTime, dbDeliveryStepCompletionTime1minuteAhead, "Cancelled time should be  " + dbDeliveryStepCompletionTime1minuteAhead, "Cancelled time is   " + properCanceledStepCompletionByAdminTime, "Cancelled time is not  " + dbDeliveryStepCompletionTime1minuteAhead);

                    } else {

                        testStepAssert.isEquals(properCanceledStepCompletionByAdminTime, dbDeliveryStepCompletionTime1MinuteBack, "Cancelled time should be  " + dbDeliveryStepCompletionTime1MinuteBack, "Cancelled time is   " + properCanceledStepCompletionByAdminTime, "Cancelled time is not  " + dbDeliveryStepCompletionTime1MinuteBack);

                    }
                    testStepAssert.isEquals(uiDeliveryStatus, deliveryStatus, "Delivery should be in " + deliveryStatus + " state", "Delivery is in " + deliveryStatus + " state", "Delivery is not in " + deliveryStatus + " state");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);

        }
    }

    @Then("^The \"([^\"]*)\" \"([^\"]*)\" should be displayed$")
    public void the_something_something_should_be_displayed(String element, String strArg2) throws Throwable {
        try{
            switch (element){
                case "Phone Icon":
                    testStepAssert.isTrue(action.isElementPresent(Page_Partner_Delivery.Icon_Phone()),"Phone Icon should be displayed","Phone Icon is displayed","Phone Icon is not displayed");
                    break;
                case "Confirm":
                    testStepAssert.isTrue(action.isElementPresent(Page_Partner_Delivery.Icon_Phone()),"Confirm button should be displayed","Confirm button is displayed","Confirm button is not displayed");
                    break;
                case "Cancel":
                    testStepAssert.isTrue(action.isElementPresent(Page_Partner_Delivery.Icon_Phone()),"Cancel button should be displayed","Cancel button is displayed","Cancel button is not displayed");
                    break;
                case "Call Alert Message":
                    String expectedMessage =PropertyUtility.getDataProperties("phone.message.if.tried.to.call");
                    String alertMessage = action.getText(Page_Partner_Delivery.Alert_MessageForCall());
                    testStepAssert.isTrue(action.isElementPresent(Page_Partner_Delivery.Alert_MessageForCall()),"Call request text should be displayed","Call request text is displayed","Call request text is not displayed");
                    testStepAssert.isEquals(alertMessage,expectedMessage,"The text "+expectedMessage+" should be displayed","The text "+alertMessage+" is displayed","The text "+expectedMessage+" should be displayed");
                    break;
                case "Active Driver Map":
                    Thread.sleep(2000);
                    testStepAssert.isTrue(action.isElementPresent(admin_DriverPage.Link_ActiveDriverMap()),"Active driver map link should be displayed","Active driver map link is displayed","Active driver map link is not displayed");
                    String activeText = action.getText(admin_DriverPage.Link_ActiveDriverMap());
                    testStepAssert.isEquals(activeText,element,"The text "+element+" should be displayed","The text "+activeText+" is displayed","The text "+element+" should be displayed");
                    break;
                case "Map":
                    testStepAssert.isTrue(action.isElementPresent(admin_DriverPage.Image_Map()),"Map should be displayed","Map is displayed","Map is not displayed");
                    break;
                case "Testdrivertywd_appleks_a_drval Kansas_al":
                case "Testdrivertywd_appleks_a_drvam Kansas_am":
                case "Testdrivertywd_appleks_a_drvbc Kansas_bc":
                case "Testdrivertywd_appleks_a_drvbd Kansas_bd":
                    Thread.sleep(3000);
                    testStepAssert.isTrue(action.isElementPresent(admin_DriverPage.Text_DriverName()),"Driver should be displayed","Driver is displayed","Driver is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(admin_DriverPage.Icon_DriverPosition()),"Drivers current location icon should be displayed","Drivers current location icon is displayed","Drivers current location icon is not displayed");
                    String kansasDriver1Name= action.getText(admin_DriverPage.Text_DriverName());
                    testStepAssert.isEquals(kansasDriver1Name,element,"The Driver name "+element+" should be displayed","The Driver name "+kansasDriver1Name+" is displayed","The Driver name  "+element+" should be displayed");
                    Thread.sleep(3000);
                    break;
                case "Driver Status":
                    action.JavaScriptScrolldown();
                    action.JavaScriptScrolldown();
                    testStepAssert.isTrue(action.isElementPresent(admin_DriverPage.Label_DriverStatus()),"Driver should be displayed","Driver is displayed","Driver is not displayed");
                    break;
                case "Details":
                    testStepAssert.isTrue(action.isElementPresent(admin_DriverPage.Header_Details()),"Details header should be displayed","Details header is displayed","Details header is not displayed");
                    break;
            }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);

    }
    }

    @Then("^The Phone Icon should not be displayed$")
    public void the_phone_icon_should_not_be_displayed() throws Throwable {
        try{
        Thread.sleep(4000);
        testStepAssert.isNotElementDisplayed(Page_Partner_Delivery.Icon_Phone(true),"Phone Icon should not be displayed","Phone Icon is not displayed","Phone Icon is displayed");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);

    }
    }


}
