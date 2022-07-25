package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import com.bungii.web.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.time.LocalTime;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Partner_Delivery_Details extends DriverBase {

    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();
    ActionManager action = new ActionManager();
    DbUtility dbUtility = new DbUtility();
    GeneralUtility utility = new GeneralUtility();


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
//    @And("^I get the time in CST$")
//    public void i_get_the_time_in_cst() throws Throwable {
//       cucumberContextManager.setScenarioContext("UTC_TIME","2022-07-07 06:06:15");
//        String timeWithZone = (String) cucumberContextManager.getScenarioContext("UTC_TIME");
////        System.out.println("Date with default formatter: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(a));
////
////        // This prints: Date with IST time zone formatter: 2013-03-15 10:30:12 GMT+05:30
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
////        TimeZone tz = TimeZone.getTimeZone("CDT");
////        sdf.setTimeZone(tz);
////        Date dateIST = sdf.parse(a);
////        System.out.println("Date with IST time zone formatter: " + dateIST);
////
////        // This prints: Date CST time zone formatter: 2013-03-15 00:00:12 CDT
////        tz = TimeZone.getTimeZone("CST");
////        sdf.setTimeZone(tz);
////        System.out.println("Date CST time zone formatter: " + sdf.format(a));
////        Instant instant = Instant.parse( "2022-07-07 06:06:15" ) ;
////        String a = instant.toString();
////
////        String timestamp = "2018-12-18 16:00:28";
//        String zone = "UTC";
////        String timeWithZone = a;
//
//        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
//                .append(DateTimeFormatter.ISO_LOCAL_DATE)
//                .appendLiteral(' ')
//                .append(DateTimeFormatter.ISO_LOCAL_TIME)
//                .appendLiteral(' ')
//                .appendPattern("yyyy-MM-dd HH:mm:ss") // Zone
//                .toFormatter();
//
//        ZonedDateTime edt = ZonedDateTime.parse(timeWithZone, formatter);
//        ZonedDateTime utc = edt.withZoneSameInstant(ZoneId.of("CTD"));;
//
//    }

    @Then("^I should see the delivery status highlighted and to be set as \"([^\"]*)\" on partner portal delivery details page$")
    public void i_should_see_the_delivery_status_highlighted_and_to_be_set_as_something_on_partner_portal_delivery_details_page(String deliveryStatus) throws Throwable {
        Thread.sleep(3000);
        String dbDeliveryStepCompletionTime = (String) cucumberContextManager.getScenarioContext("DeliveryStepCompletionTime");
        String uiDeliveryStatus = action.getText(Page_Partner_Delivery.Text_PartnerDeliveryStatus(deliveryStatus));
        switch (deliveryStatus){
            case"Scheduled":
                String [] fullScheduledStepCompletionText =action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(1)).split(" ");
                String properScheduledStepCompletionTime = fullScheduledStepCompletionText[4] + " " + fullScheduledStepCompletionText[5];
                testStepVerify.isEquals(properScheduledStepCompletionTime,dbDeliveryStepCompletionTime,"proper","proper","Proper");
                testStepAssert.isEquals(uiDeliveryStatus,deliveryStatus,"Delivery should be in " +deliveryStatus+" state","Delivery is in " +deliveryStatus+" state","Delivery is not in " +deliveryStatus+" state");
                break;
            case "En Route To Pickup":
                String []fullEnrouteStepCompletionText =action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(2)).split(" ");
                String properEnrouteStepCompletionTime = fullEnrouteStepCompletionText[4] + " " + fullEnrouteStepCompletionText[5];
                testStepVerify.isEquals(properEnrouteStepCompletionTime,dbDeliveryStepCompletionTime,"proper","proper","Proper");
                testStepAssert.isEquals(uiDeliveryStatus,deliveryStatus,"Delivery should be in " +deliveryStatus+" state","Delivery is in " +deliveryStatus+" state","Delivery is not in " +deliveryStatus+" state");
                break;
                case "Driver Arrived At Pickup":
                String[] fullDriverArrivedStepCompletionText =action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(3)).split(" ");
                String properDriverArrivedStepCompletionTime = fullDriverArrivedStepCompletionText[4] + " " + fullDriverArrivedStepCompletionText[5];
                testStepVerify.isEquals(properDriverArrivedStepCompletionTime,dbDeliveryStepCompletionTime,"proper","proper","Proper");
                testStepAssert.isEquals(uiDeliveryStatus,deliveryStatus,"Delivery should be in " +deliveryStatus+" state","Delivery is in " +deliveryStatus+" state","Delivery is not in " +deliveryStatus+" state");
                break;
            case "Loading Items":
                String []fullLoadingItemsStepCompletionText =action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(4)).split(" ");
                 String properLoadingItemsStepCompletionTime = fullLoadingItemsStepCompletionText[4] + " " + fullLoadingItemsStepCompletionText[5];
                 testStepVerify.isEquals(properLoadingItemsStepCompletionTime,dbDeliveryStepCompletionTime,"proper","proper","Proper");
                testStepAssert.isEquals(uiDeliveryStatus,deliveryStatus,"Delivery should be in " +deliveryStatus+" state","Delivery is in " +deliveryStatus+" state","Delivery is not in " +deliveryStatus+" state");
                break;
            case "Driving To Drop Off":
                String []fullDrivingToDropoffStepCompletionText =action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(5)).split(" ");
                 String properDrivingToDropoffStepCompletionTime = fullDrivingToDropoffStepCompletionText[4] + " " + fullDrivingToDropoffStepCompletionText[5];
                 testStepVerify.isEquals(properDrivingToDropoffStepCompletionTime,dbDeliveryStepCompletionTime,"proper","proper","Proper");
                testStepAssert.isEquals(uiDeliveryStatus,deliveryStatus,"Delivery should be in " +deliveryStatus+" state","Delivery is in " +deliveryStatus+" state","Delivery is not in " +deliveryStatus+" state");
                break;
            case "Unloading Items":
                String []fullUnloadingItemsStepCompletionText =action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(6)).split(" ");
                String fullUnloadingItemsCompletionTime = fullUnloadingItemsStepCompletionText[4] + " " + fullUnloadingItemsStepCompletionText[5];
                 testStepVerify.isEquals(fullUnloadingItemsCompletionTime,dbDeliveryStepCompletionTime,"proper","proper","Proper");
                testStepAssert.isEquals(uiDeliveryStatus,deliveryStatus,"Delivery should be in " +deliveryStatus+" state","Delivery is in " +deliveryStatus+" state","Delivery is not in " +deliveryStatus+" state");
                break;
            case "Done":
                String []fullDoneStepCompletionText =action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(7)).split(" ");
                 String properDoneStepCompletionTime = fullDoneStepCompletionText[4] + " " + fullDoneStepCompletionText[5];
                 testStepVerify.isEquals(properDoneStepCompletionTime,dbDeliveryStepCompletionTime,"proper","proper","Proper");
                 testStepAssert.isEquals(uiDeliveryStatus,deliveryStatus,"Delivery should be in " +deliveryStatus+" state","Delivery is in " +deliveryStatus+" state","Delivery is not in " +deliveryStatus+" state");
                break;
            case "Canceled":
                String[] fullCancellationStepCompletionTime =action.getText(Page_Partner_Delivery.Text_DeliveryCompletedStepTime(2)).split(" ");
                 String properCanceledStepCompletionTime = fullCancellationStepCompletionTime[4].toString() + " " + fullCancellationStepCompletionTime[5].toString();
                 testStepVerify.isEquals(properCanceledStepCompletionTime,dbDeliveryStepCompletionTime,"proper","proper","Proper");
                testStepAssert.isEquals(uiDeliveryStatus,deliveryStatus,"Delivery should be in " +deliveryStatus+" state","Delivery is in " +deliveryStatus+" state","Delivery is not in " +deliveryStatus+" state");
                break;
        }
    }

    @And("^I select \"([^\"]*)\" option from the filter$")
    public void i_select_something_option_from_the_filter(String filterOption) throws Throwable {
        Thread.sleep(3000);
        action.click(Page_Partner_Dashboard.DropDown_Filter());
        switch (filterOption){
            case "Completed":
            case "Canceled":
            case "Check / uncheck all":
                action.click(Page_Partner_Dashboard.Checkbox_Completed(filterOption));
                break;
        }
    }

    @And("^I save the delivery details$")
    public void i_save_the_delivery_details() throws Throwable {
        String driver = action.getText(Page_Partner_Dashboard.Text_DriverName());
        cucumberContextManager.setScenarioContext("DriverName",driver);
    }

    @And("^The driver name should be changed$")
    public void the_driver_name_should_be_changed() throws Throwable {
        String newDriverName = action.getText(Page_Partner_Dashboard.Text_DriverName());
        String oldDriverName = (String) cucumberContextManager.getScenarioContext("DriverName");
        testStepVerify.isNotEquals(newDriverName,oldDriverName);
    }

    @And("^its blah$")
    public void its_blah() throws Throwable {
        String pickupRef =(String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String time =dbUtility.getPartnerName2(pickupRef);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        String timer=time.substring(11,23);
        Time timeValue = new Time(formatter.parse(timer).getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeValue);
        calendar.add(Calendar.MINUTE, -300);
        String lowerRangeTime=String.valueOf(calendar.getTime());
        String estimateLowerRange=lowerRangeTime.substring(11,16);
        System.out.println(estimateLowerRange);
        String result = LocalTime.parse(estimateLowerRange, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
        System.out.println(result);
        cucumberContextManager.setScenarioContext("DeliveryStepCompletionTime",result);
    }
}
