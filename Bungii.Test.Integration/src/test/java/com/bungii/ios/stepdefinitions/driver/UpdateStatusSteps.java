package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.enums.Status;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.UpdateStatusPage;
import com.bungii.ios.pages.other.MessagesPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;


public class UpdateStatusSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(UpdateStatusSteps.class);
    MessagesPage messagesPage;
    Rectangle initial;
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    int[][] rgb = {
            {238, 29, 58},
            {255, 169, 61},
            {169, 204, 50},
            {37, 171, 226},
            {50, 51, 255},

    };
    private UpdateStatusPage updateStatusPage;

    public UpdateStatusSteps(UpdateStatusPage updateStatusPage, MessagesPage messagesPage) {
        this.updateStatusPage = updateStatusPage;
        this.messagesPage = messagesPage;
    }

    @Then("^I check ETA of \"([^\"]*)\"$")
    public void i_check_eta_of_something(String strArg1){
        try {
            switch (strArg1.toLowerCase()) {
                case "control driver":
                    cucumberContextManager.setScenarioContext("ETA_VALUE",action.getNameAttribute(updateStatusPage.Text_ETAValue()));
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" eta should be displayed to customer$")
    public void something_eta_should_be_displayed_to_customer(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "control driver":
                    String controlDriverEta=(String) cucumberContextManager.getScenarioContext("ETA_VALUE");
                    testStepVerify.isTrue(action.getNameAttribute(updateStatusPage.Text_ETAValue()).equals(controlDriverEta),controlDriverEta+" should be displayed");
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }    }
    @When("^I slide update button on \"([^\"]*)\" Screen$")
    public void i_start_selected_bungii(String screen) {
        try {
            String expectedMessage = "";
            switch (screen.toUpperCase()) {
                case "EN ROUTE":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.enroute");
                    break;
                case "ARRIVED":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.arrived");
                    break;
                case "LOADING ITEM":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.loading");
                    break;
                case "DRIVING TO DROP OFF":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.drop.off");
                    break;
                case "UNLOADING ITEM":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.unloading");
                    break;
                default:
                    break;
            }
            //String actualValue = updateStatusPage.getSliderText();

            updateStatus();
            Thread.sleep(5000);
            log("I slide update button on " + screen + " screen", "I slide update button on " + screen + " screen", true);

		/*testStepVerify.isEquals(actualValue, expectedMessage,
				"I slide update button on " + screen + " Screen",
				"Slider value should be" + expectedMessage + "and actual is" + actualValue,
				"Slider value should be" + expectedMessage + "and actual is" + actualValue);*/
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in sliding on " + screen + " screen in driver app", true);
        }
    }
    @Then("^non control driver should see \"([^\"]*)\" screen$")
    public void non_control_driver_should_see_something_screen(String strArg1) throws Throwable {
        try{
            testStepVerify.isElementEnabled(updateStatusPage.Activity_loader(true)," Driver should be shown loader screen");
            testStepVerify.isElementTextEquals(updateStatusPage.Text_WaitingForDriver(),"Waiting for the other driver to end Bungii.");

    } catch (Throwable e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }
    @When("^I verify and slide update button on \"([^\"]*)\" Screen$")
    public void i_verify_and_start_selected_bungii(String screen) {
        try {
            String expectedMessage = "";

            switch (screen.toUpperCase()) {
                case "EN ROUTE":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.enroute");
                    break;
                case "ARRIVED":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.arrived");
                    break;
                case "LOADING ITEM":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.loading");
                    break;
                case "DRIVING TO DROP OFF":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.drop.off");
                    break;
                case "UNLOADING ITEM":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.unloading");
                    break;
                default:
                    break;
            }
            String actualValue = getSliderText();

            updateStatus();
            Thread.sleep(7000);
            testStepVerify.isEquals(actualValue, expectedMessage,
                    "I slide update button on " + screen + " Screen",
                    "Slider value should be" + expectedMessage + "and actual is" + actualValue,
                    "Slider value should be" + expectedMessage + "and actual is" + actualValue);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Trip Information should be correctly displayed on \"([^\"]*)\" status screen for driver$")
    public void trip_information_should_be_correctly_displayed_on_something_status_screen_for_customer(String key) {
        try {
            boolean isInfoCorrectlyDisplayed = false;

            String expectedCustName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            expectedCustName = expectedCustName.substring(0, expectedCustName.indexOf(" ") + 2);
            String actualName ="" ;
            //boolean isCustomerNameCorrect = ActualName.equals(expectedCustName);
            String TripType= (String) cucumberContextManager.getScenarioContext("TripType");

            switch (key) {
                case "EN ROUTE":
                    if(TripType.equals("Duo")){
                        actualName = getCustomerNameOnDriverApp(5);
                    }
                    else {
                        actualName = getCustomerNameOnDriverApp(4);
                    }
                    isInfoCorrectlyDisplayed = validateEnRouteInfo(getTripInformation());
                    break;
                case "ARRIVED":
                    if(TripType.equals("Duo")){
                        actualName = getCustomerNameOnDriverApp(5);
                    }
                    else {
                        actualName = getCustomerNameOnDriverApp(3);
                    }
                    isInfoCorrectlyDisplayed = validateArrivedInfo(getTripInformation());
                    break;
                case "LOADING ITEMS":
                    if(TripType.equals("Duo")){
                        actualName = getCustomerNameOnDriverApp(4);
                    }
                    else {
                        actualName = getCustomerNameOnDriverApp(3);
                    }
                    isInfoCorrectlyDisplayed = validateLoadingItemsInfo(getTripInformation());
                    break;
                case "DRIVING TO DROP-OFF":
                    if(TripType.equals("Duo")){
                        actualName = getCustomerNameOnDriverApp(5);
                    }
                    else {
                        actualName = getCustomerNameOnDriverApp(4);
                    }
                    isInfoCorrectlyDisplayed = validateDrivingInfo(getTripInformation());
                    break;
                case "UNLOADING ITEMS":
                    if(TripType.equals("Duo")){
                        actualName = getCustomerNameOnDriverApp(5);
                    }
                    else {
                        actualName = getCustomerNameOnDriverApp(3);
                    }
                    isInfoCorrectlyDisplayed = validateUnloadingInfo(getTripInformation());
                    break;
                default:
                    break;
            }

            boolean isCustomerNameCorrect = actualName.equals(expectedCustName);

            if (isInfoCorrectlyDisplayed && isCustomerNameCorrect) {
                pass("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");
            } else {
                fail("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");

            }
          //  logger.detail("PageSource" + SetupManager.getDriver().getPageSource());
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^correct details should be displayed to driver on \"([^\"]*)\" app$")
    public void correct_details_should_be_displayed_to_customer_on_something_app(String key) {
        try {
            switch (key.toUpperCase()) {
                case "SMS":
                    clickSMSToCustomer();
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()));
                    break;
                case "CALL":
                    clickCallToCustomer();
                    if(action.isAlertPresent()) {
                        validateCallButtonAction(); //Commented Call validation as it doesnt open call app or alert on browserstack so if alert is not shown then its skipped
                    }
                    else
                    {
                        warning("Call alert with phone number should be shown","Call alert with phone number is not shown. but test will continue as Browserstack phones doesnt show call app");
                    }
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in tapping on call/sms icons", true);
        }
    }

    @Then("^correct details should be displayed to driver for \"([^\"]*)\"$")
    public void correct_details_should_be_displayed_to_driver_for_something(String key) throws Throwable {
        try {
            ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
            switch (key.toUpperCase()) {
                case "VIEW ITEMS":
                    clickViewItems();
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    Thread.sleep(5000);
                    validateViewImage(1);
                    break;
                case "SMS FOR SUPPORT":
                    clickSMSToSupport();
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), PropertyUtility.getMessage("driver.support.number"));
                    break;
                case "SMS FOR CANCEL INCASE OF EMERGENCEY":
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), PropertyUtility.getMessage("driver.support.number"));
                    break;
                case "DUO CUSTOMER-VIEW ITEM":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions1());
                    action.click(updateStatusPage.Button_ViewItems());
                    validateViewImage(1);
                    break;
                case "DUO CUSTOMER-CALL CUSTOMER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions1());
                    action.click(updateStatusPage.Button_Call());
                    if(action.isAlertPresent()) {
                        validateCallButtonAction(); //Commented Call validation as it doesnt open call app or alert on browserstack so if alert is not shown then its skipped
                    }
                    else
                    {
                        warning("Call alert with phone number should be shown","Call alert with phone number is not shown. but test will continue as Browserstack phones doesnt show call app");
                    }
                    break;
                case "DUO CUSTOMER-TEXT CUSTOMER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions1());
                    action.click(updateStatusPage.Button_Sms());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()));
                    break;
                case "DUO CUSTOMER-TEXT BUNGII SUPPORT":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions1());
                    action.click(updateStatusPage.Button_SupportSms());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), PropertyUtility.getMessage("driver.support.number"));
                    break;
                case "DUO DRIVER 1-CALL DRIVER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_CallDriver());
                    if(action.isAlertPresent()) {
                        validateCallButtonAction(String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_1_PHONE"))); //Commented Call validation as it doesnt open call app or alert on browserstack so if alert is not shown then its skipped
                    }
                    else
                    {
                        warning("Call alert with phone number should be shown","Call alert with phone number is not shown. but test will continue as Browserstack phones doesnt show call app");
                    }

                    break;
                case "DUO DRIVER 2-CALL DRIVER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_CallDriver());
                    if(action.isAlertPresent()) {
                        validateCallButtonAction(String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_2_PHONE"))); //Commented Call validation as it doesnt open call app or alert on browserstack so if alert is not shown then its skipped
                    }
                    else
                    {
                        warning("Call alert with phone number should be shown","Call alert with phone number is not shown. but test will continue as Browserstack phones doesnt show call app");
                    }
                    break;
                case "DUO DRIVER 1-TEXT DRIVER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_SmsDriver());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_1_PHONE")));
                    break;
                case "DUO DRIVER 2-TEXT DRIVER":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_SmsDriver());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_2_PHONE")));
                    break;
                case "DUO DRIVER-TEXT BUNGII SUPPORT":
                    action.tapByElement(updateStatusPage.Button_DuoMoreOptions2());
                    action.click(updateStatusPage.Button_SupportSms());
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()), PropertyUtility.getMessage("driver.support.number"));
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    private void validateViewImage(int image) {

        //  testStepVerify.isElementEnabled(updateStatusPage.Image_TripItem(),"Trip Item should be displayed");
        //testStepVerify.isTrue(action.getValueAttribute(updateStatusPage.PageIndicator_Page1()).equals("page 1 of 1"), "One image scrol slide should be present");
        testStepVerify.isElementDisplayed(updateStatusPage.Header_Item_Details(),"Item Details Header should display.","Item Details Header is display.","Item Details Header is not display.");
        testStepVerify.isTrue(action.getValueAttribute(updateStatusPage.PageIndicator_Page1()).equals("Photos"), "One image scrol slide should be present");
        //   testStepVerify.isElementEnabled(updateStatusPage.PageIndicator_Page1(),"Trip Item should be displayed");

        action.click(updateStatusPage.Button_CloseViewItems());
    }

    private void validateSMSNumber(String actualValue) {
        String expectedNumber = PropertyUtility.getMessage("twilio.number").replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "");
     //   boolean isMessagePage = isMessageAppPage();
        boolean isPhoneNumCorrect = actualValue.contains(expectedNumber);

/*

            testStepVerify.isTrue(isMessagePage,
                    "I should be navigated to SMS app", "I was navigate to sms app", "I was not navigated to sms app");
*/

            testStepVerify.isTrue(isPhoneNumCorrect,
                    "To Field should contains " + expectedNumber,
                    "To Field should contains " + expectedNumber + "and  actual value is" + actualValue,
                    "To Field should contains " + expectedNumber + "and  actual value is" + actualValue);

        action.click(messagesPage.Button_Cancel());
    }

    private void validateSMSNumber(String actualValue, String expectedValue) {
        String expectedNumber = expectedValue.replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "");
       // boolean isMessagePage = isMessageAppPage();
        boolean isPhoneNumCorrect = actualValue.contains(expectedNumber);

/*

            testStepVerify.isTrue(isMessagePage,
                    "I should be navigated to SMS app", "I was navigate to sms app", "I was not navigated to sms app");
*/

            testStepVerify.isTrue(isPhoneNumCorrect,
                    "To Field should contains " + expectedNumber,
                    "To Field should contains " + expectedNumber + "and  actual value is" + actualValue,
                    "To Field should contains " + expectedNumber + "and  actual value is" + actualValue);

        action.click(messagesPage.Button_Cancel());
    }

    private void validateCallButtonAction() {
        String iosVersion ="";
        if(SetupManager.BrowserStackLocal().equalsIgnoreCase("true")){
//            iosVersion = SetupManager.getBrowserStackOSVersion();
            iosVersion =  ((IOSDriver) SetupManager.getDriver()).getCapabilities().getCapability("os_version").toString();
            action.waitForAlert();
        } else {
            iosVersion = ((IOSDriver) SetupManager.getDriver()).getCapabilities().getCapability("platformVersion").toString();
        }
        if (!iosVersion.startsWith("10.")) {
            action.waitForAlert();
            String actualMessage = action.getAlertMessage().replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
                    .replace("?", "").replace("+", "").trim();
            actualMessage = actualMessage.substring(1, actualMessage.length() - 1);
            String expectedMessage = PropertyUtility.getMessage("twilio.number").replace("(", "").replace(")", "").replace(" ", "")
                    .replace("-", "").replace("+", "").trim();
            //    List<String> options = action.getListOfAlertButton();
            boolean isMessageCorrect = actualMessage.equals(expectedMessage);
            //   boolean isOptionsCorrect = options.contains("Cancel") && options.contains("Call");

            testStepVerify.isTrue(isMessageCorrect,
                    "I should be alerted to call twillo number", "Twillo number was displayed in alert message",
                    "Twillo number was not displayed in alert message , Actual message :" + actualMessage + " , Expected Message:" + PropertyUtility.getMessage("twilio.number"));

/*                testStepVerify
                        .isTrue(isOptionsCorrect,
                                "Alert should have option to cancel and call twilio number ",
                                "Alert  have option to cancel and call twilio number , options are" + options.get(0) + " and " + options.get(1),
                                "Alert dont have option to cancel and call twilio number");*/

            action.clickAlertButton("Cancel");
        } else {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //logger.detail("Pagesource" + SetupManager.getDriver().getPageSource());
            String actualMessage = updateStatusPage.CallNumeberValue_iOS10().getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
                    .replace("?", "").replace("+", "").trim();
            String expectedMessage = PropertyUtility.getMessage("twilio.number").replace("(", "").replace(")", "").replace(" ", "")
                    .replace("-", "").replace("+", "").trim();
            boolean isMessageCorrect = actualMessage.equals(expectedMessage);
            testStepVerify.isTrue(isMessageCorrect,
                    "I should be alerted to call twillo number", "Twillo number was displayed in alert message",
                    "Twillo number was not displayed in alert message , Actual message :" + actualMessage + " , Expected Message:" + PropertyUtility.getMessage("twilio.number"));
            if (action.isElementPresent(updateStatusPage.ButtonCancelCall_iOS10(true)))
                action.click(updateStatusPage.ButtonCancelCall_iOS10());
            action.click(updateStatusPage.EndCall_iOS10());
        }
    }


    private void validateCallButtonAction(String expectedNumber) {
        action.waitForAlert();
        String actualMessage = action.getAlertMessage().replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
                .replace("?", "").replace("+", "").trim();
        actualMessage = actualMessage.substring(1, actualMessage.length() - 1);
        String expectedMessage = expectedNumber.replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "").replace("+", "").trim();
   //     List<String> options = action.getListOfAlertButton();
        boolean isAlertMessageCorrect = actualMessage.equals(expectedMessage);
   //     boolean isOptionsCorrect = options.contains("Cancel") && options.contains("Call");


            testStepVerify.isTrue(isAlertMessageCorrect,
                    "I should be alerted to call twillo number", "Twillo number was displayed in alert message",
                    "Twillo number was not displayed in alert message , Actual message :" + actualMessage + " , Expected Message:" + expectedNumber);


/*            testStepVerify
                    .isTrue(isOptionsCorrect,
                            "Alert should have option to cancel and call twilio number ",
                            "Alert  have option to cancel and call twilio number , options are" + options.get(0) + " and " + options.get(1),
                            "Alert dont have option to cancel and call twilio number");*/

        action.clickAlertButton("Cancel");
    }

    private boolean validateUnloadingInfo(List<String> actualInfo) {
        logger.detail("INside trip info validation");
        String dropOffLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",", "").replace("Rd", "Road").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",", "").replace("Rd", "Road").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();
        //boolean isTagDisplayed = actualInfo.get(1).equals("DROP OFF LOCATION");
        String actualDropOfflocation = actualInfo.get(4).replace(",", "").replace("  ", " ");

        boolean isDropLocationDisplayed = actualDropOfflocation
                .contains(dropOffLocationLineOne) && actualDropOfflocation
                .contains(dropOffLocationLineTwo);

       // if (isTagDisplayed && isDropLocationDisplayed) {
            //removed pass statement to avoid multiple screenshot and log in result

       // } else {
        /*
            testStepVerify.isEquals(actualInfo.get(1), "DROP OFF LOCATION",
                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'DROP OFF LOCATION' Tag was not correctly displayed");


            testStepVerify.isEquals(actualInfo.get(5), (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"),

                    "DROP OFF location should be correctly displayed ",
                    "DROP OFF location was correctly displayed , actual was is " + actualDropOfflocation + "and expected is " + dropOffLocationLineOne + dropOffLocationLineTwo,
                    "DROP OFF location was not displayed correctly, actual was is " + actualDropOfflocation + " and expected is" + dropOffLocationLineOne + dropOffLocationLineTwo);

        //}
         */
        return isDropLocationDisplayed;
        //return isTagDisplayed && isDropLocationDisplayed;
    }

    private boolean validateEnRouteInfo(List<String> actualInfo) {
        logger.detail("Inside trip info validation");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",", "").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",", "").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();

        //boolean isTagDisplayed = actualInfo.get(5).equals("PICKUP LOCATION");

//        boolean isETACorrect = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("mins");
        String actualPickuplocation="";
        String tripType= (String) cucumberContextManager.getScenarioContext("TripType");
        if(tripType.equalsIgnoreCase("Duo")){
            actualPickuplocation = actualInfo.get(6).replace(",", "").replace("  ", " ");
        }else {
            actualPickuplocation = actualInfo.get(5).replace(",", "").replace("  ", " ");
        }
        boolean isPickUpDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) && actualPickuplocation.contains(pickUpLocationLineTwo);

       // if (isETACorrect && isPickUpDisplayed) {
            //removed pass statement to avoid multiple screenshot and log in result

        //} else {
            //testStepVerify.isTrue(isTagDisplayed, "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed", "'PICKUP LOCATION' Tag was not correctly displayed");
/*            testStepVerify.isTrue(isETACorrect,
                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

 */
            testStepVerify.isTrue(isPickUpDisplayed,
                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualPickuplocation + " and expected is " + pickUpLocationLineOne + pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualPickuplocation + " and expected is " + pickUpLocationLineOne + pickUpLocationLineTwo);
        //}
        //return isTagDisplayed && isETACorrect && isPickUpDisplayed;
        //return isETACorrect && isPickUpDisplayed;
        return isPickUpDisplayed;
    }

    private boolean validateDrivingInfo(List<String> actualInfo) {
        logger.detail("inside trip info validation");

        String dropOffLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",", "").replace("Rd", "Road").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",", "").replace("Rd", "Road").replace(PropertyUtility.getDataProperties("bungii.country.name"), "").replace("  ", " ").trim();
        //boolean isTagDisplayed = actualInfo.get(1).equals("DROP OFF LOCATION");
        boolean isETAdisplayed = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("mins");
        String actualDropoffLocation = actualInfo.get(5).replace(",", "").replace("  ", " ");
        boolean isDropDisplayed = actualDropoffLocation.contains(dropOffLocationLineOne) && actualDropoffLocation.contains(dropOffLocationLineTwo);

       // if (isTagDisplayed && isETAdisplayed && isDropDisplayed) {
            //removed pass statement to avoid multiple screenshot and log in result

       // } else {
        /*
            testStepVerify.isTrue(isTagDisplayed,
                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'DROP OFF LOCATION' Tag was not correctly displayed");
            */

            testStepVerify.isTrue(isETAdisplayed,
                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

            testStepVerify.isTrue(isDropDisplayed,
                    "DROP OFF  location should be correctly displayed ",
                    "DROP OFF  location was correctly displayed , actual was is" + actualDropoffLocation + " and expected is " + dropOffLocationLineOne + dropOffLocationLineTwo,
                    "DROP OFF location was not displayed correctly, actual was is" + actualDropoffLocation + "and expected is" + dropOffLocationLineOne + dropOffLocationLineTwo);
        //}
        //return isTagDisplayed && isETAdisplayed && isDropDisplayed;
        return isETAdisplayed && isDropDisplayed;
    }

    private boolean validateArrivedInfo(List<String> actualInfo) {
        logger.detail("inside trip info validation");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",", "").replace("  ", " ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",", "").replace("  ", " ").trim();

       // boolean isTagDisplayed = actualInfo.get(1).equals("PICKUP LOCATION");
        String actualPickuplocation ="";
        String tripType= (String) cucumberContextManager.getScenarioContext("TripType");
        if(tripType.equalsIgnoreCase("Duo")) {
            actualPickuplocation = actualInfo.get(7).replace(",", "").replace("  ", " ");
        }
        else{
            actualPickuplocation = actualInfo.get(4).replace(",", "").replace("  ", " ");
        }
        boolean isPickupDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) && actualPickuplocation
                .contains(pickUpLocationLineTwo);
        //if (isTagDisplayed && isPickupDisplayed) {
            //removed pass statement to avoid multiple screenshot and log in result
        //} else {
        /*
            testStepVerify.isTrue(isTagDisplayed,
                    "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
                    "'PICKUP LOCATION' Tag was not correctly displayed");

         */

            testStepVerify.isTrue(isPickupDisplayed,
                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualPickuplocation + " and expected is " + pickUpLocationLineOne + pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualPickuplocation + "and expected is" + pickUpLocationLineOne + pickUpLocationLineTwo);
        //}
        //return isTagDisplayed && isPickupDisplayed;
        return isPickupDisplayed;
    }

    private boolean validateLoadingItemsInfo(List<String> actualInfo) {
        logger.detail("inside trip info validation");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",", "").replace("  ", " ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",", "").replace("  ", " ").trim();

        // boolean isTagDisplayed = actualInfo.get(1).equals("PICKUP LOCATION");
        String actualPickuplocation ="";
        String tripType= (String) cucumberContextManager.getScenarioContext("TripType");
        if(tripType.equalsIgnoreCase("Duo")) {
            actualPickuplocation = actualInfo.get(5).replace(",", "").replace("  ", " ");
        }
        else {
            actualPickuplocation = actualInfo.get(4).replace(",", "").replace("  ", " ");
        }
        boolean isPickupDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) && actualPickuplocation
                .contains(pickUpLocationLineTwo);
        //if (isTagDisplayed && isPickupDisplayed) {
        //removed pass statement to avoid multiple screenshot and log in result
        //} else {
        /*
            testStepVerify.isTrue(isTagDisplayed,
                    "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
                    "'PICKUP LOCATION' Tag was not correctly displayed");

         */

        testStepVerify.isTrue(isPickupDisplayed,
                "Pick up location should be correctly displayed ",
                "Pick up location was correctly displayed , actual was is" + actualPickuplocation + " and expected is " + pickUpLocationLineOne + pickUpLocationLineTwo,
                "Pick up location was not displayed correctly, actual was is" + actualPickuplocation + "and expected is" + pickUpLocationLineOne + pickUpLocationLineTwo);
        //}
        //return isTagDisplayed && isPickupDisplayed;
        return isPickupDisplayed;
    }



    @Then("^I should be navigated to \"([^\"]*)\" trip status screen$")
    public void iShouldBeNaviagatedToTripStatusScreen(String screen) {
        try {
            int activeStatus = 0;

            boolean pageFlag = false;
            if (screen.equalsIgnoreCase(Status.ARRIVED.toString())) {
                pageFlag = isUpdatePage(Status.ARRIVED.toString());
                activeStatus = 1;
            } else if (screen.equals(Status.EN_ROUTE.toString())) {
                pageFlag = isUpdatePage(Status.EN_ROUTE.toString());
                activeStatus = 0;
            } else if (screen.equals(Status.LOADING_ITEM.toString())) {
                pageFlag = isUpdatePage(Status.LOADING_ITEM.toString());
                activeStatus = 2;
            } else if (screen.equals(Status.DRIVING_TO_DROP_OFF.toString())) {
                pageFlag = isUpdatePage(Status.DRIVING_TO_DROP_OFF.toString());
                activeStatus = 3;
            } else if (screen.equals(Status.UNLOADING_ITEM.toString())) {
                pageFlag = isUpdatePage(Status.UNLOADING_ITEM.toString());
                activeStatus = 4;
            }

            boolean[] statusCheck = utility.checkStatusOnDriver();
            for (int i = 0; i < statusCheck.length; i++) {
                if (activeStatus == i) {
                    testStepVerify.isTrue(statusCheck[i], "I should be navigated to " + screen + "screen", screen + " screen icon is highlighted", screen + " screen icon is not highlighted");
                } else {
                    int screenNo = i + 1;
                    if (statusCheck[i])
                        testStepVerify.isFalse(statusCheck[i], "I should be navigated to " + screen + "screen", "Pickup status " + screenNo + " screen should not be highlighted", screenNo + " status should is highlighted");
                    else
                        log("Pickup status " + screenNo + " screen should not be highlighted", screenNo + " status should is not highlighted", false);
                }

            }
            testStepVerify.isTrue(pageFlag, "I should be navigated to " + screen + "screen", "I was not navigated to" + screen);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be navigated to \"([^\"]*)\" trip status screen on driver$")
    public void i_should_be_navigated_to_something_trip_status_screen_on_driver(String screen) throws Throwable {
        try{
            testStepAssert.isElementDisplayed(updateStatusPage.Text_Header(screen),"I should be navigated to " + screen + "screen","I have navigated to " + screen + "screen","I have not navigated to " + screen + "screen");
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
        //throw new PendingException();
    }

    @Then("^I accept Alert message for \"([^\"]*)\"$")
    public void i_accept_alert_message_for_something(String strArg1) throws Throwable {
        action.waitForAlert();

        String actualText = action.getAlertMessage();
        String expectedText = "";
        switch (strArg1) {
            case "Reminder: both driver at pickup":
                
                expectedText = PropertyUtility.getMessage("bungii.duo.driver.pickup");
                break;
            case "Reminder: both driver at drop off":
                expectedText = PropertyUtility.getMessage("bungii.duo.driver.drop");
                break;

        }
        testStepVerify.isEquals(actualText, expectedText, strArg1 + "should be displayed", expectedText + " is displayed", "Expect alert text is " + expectedText + " and actual is " + actualText);
        action.clickAlertButton("Initiate");
    }
    @And("^stack trip information should be displayed on deck$")
    public void stack_trip_information_should_be_displayed_on_deck() {
        try {
            //String customerName = (String) cucumberContextManager.getScenarioContext("LATEST_LOGGEDIN_CUSTOMER_NAME");
            //testStepVerify.isElementTextEquals(updateStatusPage.Text_NextLabel(), "NEXT CUSTOMER","'NEXT CUSTOMER' text lable should be displayed","'NEXT CUSTOMER' text lable is displayed","'NEXT CUSTOMER' text lable is not displayed");
            String OnDeckText= action.getText(updateStatusPage.Text_OnDeckLabel());
            boolean onDeck=false;
            if(OnDeckText.contains("Bungii on deck, try to finish up by"))
                onDeck=true;
            testStepVerify.isTrue(onDeck,"Bungii on deck, try to finish up by should be displayed","Bungii on deck, try to finish up by is not displayed.");

            //testStepVerify.isElementTextEquals(updateStatusPage.Text_OnDeckLabel(), "ON DECK","'ON DECK' text lable should be displayed","'ON DECK' text lable is displayed","'ON DECK' text lable is not displayed");
            //testStepVerify.isElementTextEquals(updateStatusPage.Text_StackCustomer(), customerName.substring(0, customerName.indexOf(" ") + 2));
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^stack trip information should not be displayed on deck$")
    public void stack_trip_information_should_not_be_displayed_on_deck() {
        try {
            testStepVerify.isElementNotEnabled(updateStatusPage.Text_NextLabel(true), "Next tag should not be enabled","Next tag is not displayed","Next tag is displayed");
            testStepVerify.isElementNotEnabled(updateStatusPage.Text_OnDeckLabel(true), "ON DECK should not be displayed" ,"'ON DECK' text lable is not displayed","ON DECK is displayed");
            testStepVerify.isElementNotEnabled(updateStatusPage.Text_StackCustomer(true),"stack Customer name should be not be diplayed","stack Customer name should be not be diplayed","stack Customer name is diplayed");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^try to finish time should be correctly displayed for long stack trip$")
    public void try_to_finish_time_should_be_correctly_displayed() throws Throwable {

        if(((String)cucumberContextManager.getScenarioContext("DRIVER_MIN_ARRIVAL")).equalsIgnoreCase(""))
        {
            String[] calculatedTime=utility.getTeletTimeinLocalTimeZone();
            cucumberContextManager.setScenarioContext("DRIVER_TELET",calculatedTime[0]);
            cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL",calculatedTime[1]);
            cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL",calculatedTime[2]);
        }
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        String expectedTime="";
        if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
            expectedTime = ((String)cucumberContextManager.getScenarioContext("DRIVER_TELET")) + "  "; //+ PropertyUtility.getDataProperties("browserstack.time.label");
        else
            expectedTime = ((String)cucumberContextManager.getScenarioContext("DRIVER_TELET")) + "  " + utility.getTimeZoneBasedOnGeofence();
       // expectedTime=expectedTime.replace("am", "AM").replace("pm","PM");
        expectedTime=expectedTime.replace("am", "").replace("pm","").replace("AM", "").replace("PM","").trim();
        String actualValue= action.getText(updateStatusPage.Text_StackInfo());
        testStepAssert.isTrue(actualValue.contains(expectedTime), "Try to finish by should be displayed","Try to finish by "+expectedTime+" is displayed", "Try to finish by "+expectedTime+ " is not displayed. instead "+ actualValue +"is displayed");
    }

    @Then("^try to finish time should be correctly displayed for short stack trip$")
    public void try_to_finish_time_should_be_correctly_displayed_ShortStack() throws Throwable {
        if(((String)cucumberContextManager.getScenarioContext("DRIVER_MIN_ARRIVAL")).equalsIgnoreCase(""))
        {
            String[] calculatedTime=utility.getTeletTimeinLocalTimeZone();
            cucumberContextManager.setScenarioContext("DRIVER_TELET",calculatedTime[0]);
            cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL",calculatedTime[1]);
            cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL",calculatedTime[2]);
        }
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        String expectedTime="";
        if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
            expectedTime = ((String)cucumberContextManager.getScenarioContext("DRIVER_FINISH_BY")) + " " ;//+ PropertyUtility.getDataProperties("browserstack.time.label");
        else
            expectedTime = ((String)cucumberContextManager.getScenarioContext("DRIVER_FINISH_BY")) + " " + utility.getTimeZoneBasedOnGeofence();
       // expectedTime=expectedTime.replace("am", "AM").replace("pm","PM");
        expectedTime=expectedTime.replace("am", "").replace("pm","").replace("AM", "").replace("PM","").trim();
        String elementText=updateStatusPage.Text_StackInfo().getText();elementText=elementText.replace("  ","").trim();
        logger.detail("Element Text"+elementText);
        testStepAssert.isTrue(elementText.contains(expectedTime), "Try to finish by should be displayed","Try to finish by "+expectedTime+" is displayed", "Try to finish by "+expectedTime+ " is not displayed");

    }
    @Then("^I calculate projected driver arrival time$")
    public void i_calculate_projected_driver_arrival_time() throws Throwable {
        utility.calculateShortStack();
    }

    @When("^I verify that driver to pickup time is greater than 100 mins for second trip$")
    public void i_verify_that_driver_to_pickup_time_is_greater_than_100_mins_for_second_trip() {
        String customer2PhoneNumber=(String)cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
        String driverPhoneNumber=(String)cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
        String custRef = DbUtility.getCustomerRefference(customer2PhoneNumber);
        String pickupID = DbUtility.getPickupID(custRef);
        String pickupRef = DbUtility.getPickupRef(customer2PhoneNumber);
        DbUtility.isDriverEligibleForTrip(driverPhoneNumber,pickupRef);
        int driverToPickUP=Integer.valueOf(DbUtility.getDriverToPickupTime(driverPhoneNumber,pickupID));

        testStepVerify.isTrue(driverToPickUP>100,"Driver to pickp value should be greater that 100 ", "Driver to pickup value is "+driverToPickUP +" min","Driver to pickup value is "+driverToPickUP +" min");
    }

    public boolean isMessageAppPage() {
        action.textToBePresentInElementName(updateStatusPage.Text_NavigationBar(), PropertyUtility.getMessage("messages.navigation.new"));
        return action.getNameAttribute(updateStatusPage.Text_NavigationBar()).equals(PropertyUtility.getMessage("messages.navigation.new"));
    }

    /**
     * Slide the slider to update status
     */
    public void updateStatus() {
        //get locator rectangle is time consuming process
/*        if (initial == null)
            initial = action.getLocatorRectangle(updateStatusPage.AreaSlide());*/
/*        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));*/


        WebElement slider = updateStatusPage.AreaSlide();
        Rectangle initial;
        if (!utility.isSliderValueContainsInContext("DRIVER")) {
            initial = action.getLocatorRectangle(slider);
            utility.addSliderValueToFeatureContext("DRIVER", initial);

        } else {
            initial = utility.getSliderValueFromContext("DRIVER");
        }

        action.dragFromToForDuration(0, 0, initial.getWidth(), initial.getHeight(), 1, slider);
    }

    /**
     * Check if active page is update page.
     *
     * @return return comparison result navigation header to expected msg from
     * property
     */
    public boolean isUpdatePage(String pageName) {
        action.textToBePresentInElementName(updateStatusPage.Text_NavigationBarScreen(pageName), pageName);
        return action.getScreenHeader(updateStatusPage.Text_NavigationBarScreen(pageName)).equals(pageName);

    }

    /**
     * Cancel accepted bungii
     */
    public void cancelAcceptedBungii() {
        action.click(updateStatusPage.Button_Cancel());
    }

    /**
     * Get text on slider box
     *
     * @return value of slider box
     */
    public String getSliderText() {
        return action.getValueAttribute(updateStatusPage.AreaSlide());
    }

    /**
     * Click call to customer
     */
    public void clickCallToCustomer() throws InterruptedException{
        action.click(updateStatusPage.Button_Call());
        //Thread.sleep(2000);

    }

    /**
     * Click SMS to customer
     */
    public void clickSMSToCustomer() throws InterruptedException{

        action.click(updateStatusPage.Button_Sms());
        Thread.sleep(2000);

    }

    /**
     * Click SMS to Bungii
     */
    public void clickSMSToSupport() throws InterruptedException{
        action.click(updateStatusPage.Button_SupportSms());
        Thread.sleep(2000);

    }

    /**
     * Click View Items
     */
    public void clickViewItems() throws InterruptedException {
        action.click(updateStatusPage.Button_ViewItems());
        Thread.sleep(2000);

    }

    /**
     * Get Customer Name
     *
     * @return value of customer name
     */
    public String getCustomerName() {
        String customerName= getTripInformation().get(4);
        return action.getNameAttribute(updateStatusPage.Text_Customer());
    }

    /**
     * Get trip information that is displayed below status icon
     *
     * @return List of string containing trip information
     */
    public List<String> getTripInformation() {
        List<String> details = new ArrayList<>();
        List<WebElement> textInfo = updateStatusPage.Text_Info();
        for (WebElement info : textInfo) {
            details.add(action.getValueAttribute(info));
        }
        return details;
    }

    /**
     * Get name information that is displayed in driver app
     *
     * @return customer name string containing trip information in driver app
     */
    public String getCustomerNameOnDriverApp(int i) throws InterruptedException {
        List<String> details = new ArrayList<>();
        List<WebElement> textInfo = updateStatusPage.Text_Info();
        Thread.sleep(2000);
        for (WebElement info : textInfo) {
            details.add(action.getValueAttribute(info));
        }
        String CustName= details.get(i);
        return CustName;
    }

    /**
     * Verify if image status is displayed or not
     *
     * @param key Identifier for image, Key with same name should be present in image.properties
     * @return
     */
    public boolean verifyStatus(String key) {

        return action.verifyImageIsPresent(key);
    }

}
