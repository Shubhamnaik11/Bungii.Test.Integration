package com.bungii.ios.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.pages.customer.EnableLocationPage;
import com.bungii.ios.pages.customer.EnableNotificationPage;
import com.bungii.ios.pages.driver.*;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.stepdefinitions.driver.*;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.web.utilityfunctions.DbUtility.getLinkedPickupRef;


public class CommonStepsDriver extends DriverBase {
    private static LogUtility logger = new LogUtility(CommonSteps.class);
    ActionManager action = new ActionManager();

    String Image_Solo = "bungii_type-solo", Image_Duo = "bungii_type-duo";
    private TripAlertSettingsPage tripAlertSettingsPage = new TripAlertSettingsPage();
    private BungiiCompletedPage driverBungiiCompletedPage= new BungiiCompletedPage();
    private com.bungii.ios.pages.driver.HomePage driverHomePage;
    private com.bungii.ios.pages.driver.LoginPage driverLoginPage;
    private com.bungii.ios.pages.driver.UpdateStatusPage driverUpdateStatusPage;
    private ScheduledTripsPage scheduledTripsPage;
    private com.bungii.ios.pages.driver.ForgotPasswordPage driverForgotPasswordPage;
    private BungiiDetailsPage bungiiDetailsPage;
    //private EnableLocationPage enableLocationPage;
    EnableNotificationPage enableNotificationPage = new EnableNotificationPage();
    EnableLocationPage enableLocationPage = new EnableLocationPage();
    GeneralUtility utility = new GeneralUtility();
    private ScheduledBungiiPage scheduledBungiipage = new ScheduledBungiiPage();
    com.bungii.web.utilityfunctions.DbUtility dbUtility = new com.bungii.web.utilityfunctions.DbUtility();

    public CommonStepsDriver(
                       com.bungii.ios.pages.driver.UpdateStatusPage updateStatusPage,
                       ScheduledTripsPage scheduledTripsPage,
                       BungiiRequestPage bungiiRequestPage,
                        com.bungii.ios.pages.driver.HomePage driverHomePage,
                       com.bungii.ios.pages.driver.ForgotPasswordPage driverForgotPasswordPage,  com.bungii.ios.pages.driver.LoginPage driverLoginPage,BungiiDetailsPage bungiiDetailsPage) {

        this.driverUpdateStatusPage = updateStatusPage;
        this.scheduledTripsPage = scheduledTripsPage;
        this.driverHomePage = driverHomePage;
        this.driverLoginPage=driverLoginPage;
        this.driverForgotPasswordPage=driverForgotPasswordPage;
        this.bungiiDetailsPage = bungiiDetailsPage;
        //this.enableLocationPage=enableLocationPage;

    }

    @Then("^\"([^\"]*)\" message should be displayed on \"([^\"]*)\" page on driverApp$")
    public void something_message_should_be_displayed_on_something_page_driverApp(String messageElement, String screen) {
        try {
            boolean messageDisplayed = false;

            switch (messageElement.toUpperCase()) {
                case "BUNGII CANCEL":
                    messageDisplayed = scheduledTripsPage.isElementEnabled(scheduledTripsPage.Text_Success()) && scheduledTripsPage.Text_Success().getText().equals(PropertyUtility.getMessage("admin.cancel.sucess"));
                    break;
                case "FORGOT PASSWORD INFORMATION":
                    messageDisplayed = action.getValueAttribute(driverForgotPasswordPage.Text_Info())
                            .equals(PropertyUtility.getMessage("driver.forgotpassword.info"));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            testStepAssert.isTrue(messageDisplayed,
                    messageElement + " should be displayed", messageElement + " Message is Displayed",
                    messageElement + " Message is not Displayed");
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }



    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" screen on driverApp$")
    public void iClickButtonOnScreenDriverApp(String button, String screen) {
        try {
            action.hideKeyboard();
            switch (button.toUpperCase()) {
                case "LOG IN":
                    if (screen.equalsIgnoreCase("log in"))
                        action.click(driverLoginPage.Button_Login());
                    else
                        System.err.println("test");
                    // none, error
                    break;
                case "SEND":
                    if (screen.equalsIgnoreCase("forgot password"))
                        action.click(driverForgotPasswordPage.Button_Send());
                    break;
                case "SMS":
                    action.click(driverUpdateStatusPage.Button_Sms());
                    break;
                case "CALL":
                    action.click(driverUpdateStatusPage.Button_Call());
                    break;
                case "FORGOT PASSWORD":
                    action.click(driverLoginPage.Button_ForgotPassword());
                    break;
                case "CONTINUE":
                    action.swipeUP();
                    action.nextFieldKeyboard();
                    action.click(driverForgotPasswordPage.Button_Continue());
                    break;
                case "BACK":
                    action.click(driverForgotPasswordPage.Button_Back());
                    break;
                case "ONLINE":
                    Thread.sleep(2000);
                    action.click(driverHomePage.GoOffline_Btn());
                    break;
                case "OFFLINE":
                    action.click(driverHomePage.GoOnline_Btn());
                    break;
                case "AVAILABLE BUNGIIS":
                    //action.click(driverHomePage.Text_AvailableTrips());
                    action.click(driverHomePage.Link_View_AvailableTrips());
                    break;
                case "SMS ALERT":
                    action.click(tripAlertSettingsPage.Button_SMSAlerts());
                    break;
                case "DELIVERY ALERT":
                    action.click(tripAlertSettingsPage.Button_DeliveryAlerts());
                    break;
                case "ITEMIZED EARNINGS":
                    action.click(driverHomePage.Link_Itemized_Earnings());
                        break;
                default:
                    error("UnImplemented Step or incorrect button name",
                            "UnImplemented Step");
                    break;

            }

            log("Click " + button + " button ",
                    "Clicked " + button + " button", true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be navigated to \"([^\"]*)\" screen on driverApp$")
    public void i_should_be_naviagated_to_something_screen(String screen) {
        try {
            boolean isCorrectPage = false;

            GeneralUtility utility = new GeneralUtility();
            //isCorrectPage = utility.verifyPageHeader(screen);
            if(screen.equalsIgnoreCase("Home")){
                screen ="Bungii";
            }
            isCorrectPage = utility.verifyDriverPageHeader(screen);
            testStepAssert.isTrue(isCorrectPage, "I should be naviagated to " + screen + " screen",
                    "I have navigated to " + screen, "I didnt navigate to " + screen + " screen ");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error in navigating to screen : "+ screen, true);
        }
    }


    public String generateMobileNumber() {

        String phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");
        while (!DbUtility.isPhoneNumberUnique(phoneNumber)) {
            phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");

        }
        return phoneNumber;
    }

    @When("^I Enter \"([^\"]*)\" value in \"([^\"]*)\" field in \"([^\"]*)\" Page on driverApp$")
    public void iEnterValueInFieldInPageDriverApp(String value, String field, String screen) {

        try {
            String inputValue = RandomGeneratorUtility.getData(value, 10);

            if (!value.equalsIgnoreCase("{RANDOM_PHONE_NUM}")) {
                inputValue = value.equalsIgnoreCase("{EMPTY}") ? "     " : inputValue;
                inputValue = value.equalsIgnoreCase("{BLANK}") ? "" : inputValue;
            } else {
                inputValue = generateMobileNumber();
            }

            switch (field.toUpperCase()) {
                case "PHONE NUMBER":
                        if (screen.equalsIgnoreCase("FORGOT PASSWORD")) {
                            if(value.equalsIgnoreCase("{VALID USER}")) {
                                inputValue = value.equalsIgnoreCase("{VALID USER}") ? PropertyUtility.getDataProperties("ios.valid.driver.phone") : inputValue;
                            }
                            else if(value.equalsIgnoreCase("{VALID USER1}")){
                                inputValue = value.equalsIgnoreCase("{VALID USER1}") ? PropertyUtility.getDataProperties("ios.valid.driver1.phone") : inputValue;
                            }
                            action.clearEnterText(driverForgotPasswordPage.Text_InputNumber(), inputValue);
                            cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", inputValue);
                        }
                    break;
                case "SMS CODE":
                    inputValue = inputValue.equalsIgnoreCase("valid") ? (String) cucumberContextManager.getScenarioContext("SMS_CODE")
                            : "111";
                    action.clearEnterText(driverForgotPasswordPage.Text_SmsCode(), inputValue);
                    break;
                case "NEW PASSWORD":
                    action.clearEnterText(driverForgotPasswordPage.Text_Password(), inputValue);
                    break;
                case "CONFIRM PASSWORD":
                    action.clearEnterText(driverForgotPasswordPage.Text_Confirm_Password(), inputValue);
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            action.hideKeyboard();

            log("I should able to Enter " + value + " value in " + field + " field in " + screen + " Page",
                    "I Entered " + inputValue + " in " + field + " field", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("^user is alerted for \"([^\"]*)\" on driverApp$")
    public void user_is_alerted_for_something_driverApp(String key) {
        try {
            action.waitForAlert();
            if (!action.isAlertPresent())
                action.waitForAlert();
            String expectedText = "";
            switch (key.toUpperCase()) {

                case "FAILED TO SEND TOKEN":
                    //expectedText = PropertyUtility.getMessage("driver.forgotpassword.failed.reset");
                    expectedText = PropertyUtility.getMessage("common.failed.message");
                    break;
                case "PASSWORD CHANGE SUCCESS":
                    expectedText = PropertyUtility.getMessage("driver.forgotpassword.success");
                    break;
                case "INVALID SMS CODE":
                    expectedText = PropertyUtility.getMessage("driver.forgotpassword.invalid.code");
                    break;
                case "INVALID PASSWORD WHILE RESET":
                    expectedText = PropertyUtility.getMessage("driver.forgotpassword.invalid.password");
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            String alertText = SetupManager.getDriver().switchTo().alert().getText();
            testStepAssert.isEquals(alertText, expectedText,alertText+" should be displayed",alertText+" is displayed", alertText+" is displayed instead of "+expectedText );
            SetupManager.getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Alert message with (.+) text should be displayed on driverApp$")
    public void alert_message_with_text_should_be_displayed_driverApp(String message) {
        String actualMessage ="";
        String expectedMessage="";;
        try {
            action.waitForAlert();
            actualMessage = action.getAlertMessage();

            switch (message.toUpperCase()) {
                case "INVALID_PASSWORD":
                    expectedMessage = PropertyUtility.getMessage("driver.error.invalidpassword");
                    break;
                case "EMPTY_FIELD":
                    expectedMessage = PropertyUtility.getMessage("driver.error.emptyfield");
                    break;
                case "YOUR ACCOUNT REGISTRATION IS STILL UNDER PROCESS.":
                    expectedMessage = PropertyUtility.getMessage("driver.error.pending.status");
                    break;
                case "INVALID_PASSWORD_3_TIMES":
                    expectedMessage=PropertyUtility.getMessage("driver.error.invalidpassword.three.times");
                    break;
                case "INVALID_PASSWORD_5_TIMES":
                    expectedMessage=PropertyUtility.getMessage("driver.error.invalidpassword.five.times");
                    break;
                case "HICCUP MESSAGE":
                    expectedMessage=PropertyUtility.getMessage("driver.error.payment.status.pending");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }

        } catch (Throwable e) {
            logger.error("Invalid Password Alert Not Displayed", ExceptionUtils.getStackTrace(e));
            fail("Step should be successful",
                    "Expected Alert Not Displayed", true);
        }

        testStepAssert.isEquals(actualMessage, expectedMessage,
                "Alert : " + expectedMessage + " should be displayed",
                "Alert : " + actualMessage + " is displayed",
                "Actual Message is displayed " + actualMessage + " instead of "
                        + expectedMessage);
    }

    @And("^I accept Alert message on driverApp$")
    public void iAcceptAlertMessage_DriverApp() {
        try {
            SetupManager.getDriver().switchTo().alert().accept();
            log("Alert Message should be accepted", "Alert Message is accepted");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Given("^I am on the \"([^\"]*)\" page on driverApp$")
    public void i_am_on_the_something_page_on_driverApp(String screen) {
        try {
            //adding temp page source , can remove later
          //  logger.error("Page source", SetupManager.getDriver().getPageSource());

            String navigationBarName =  action.getScreenHeader(driverHomePage.NavigationBar_Text());
            switch (screen.trim().toUpperCase()) {
                case "LOG IN":
                    goToDriverLogInPage(navigationBarName);
                    break;
                case "HOME":
                    testStepVerify.isEquals(navigationBarName, PropertyUtility.getMessage("driver.home.title.offline"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in navigating to "+ screen + " screen on driver app ", true);

        }
    }

    public void goToDriverLogInPage(String navigationBarName) throws Throwable {
        HomePageSteps homeSteps = new HomePageSteps(driverHomePage);
        if (action.isAlertPresent()) {
            String alertMessage = action.getAlertMessage();
            logger.detail("Alert is present on screen, Alert message:" + alertMessage);
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            if (getListOfAlertButton.contains("Done"))
                action.clickAlertButton("Done");

        }
         navigationBarName =  action.getScreenHeader(driverHomePage.NavigationBar_Text());
        if(navigationBarName.equalsIgnoreCase("Bungii Completed")){
            action.click(driverBungiiCompletedPage.Button_Next_Bungii());
            navigationBarName =  action.getScreenHeader(driverHomePage.NavigationBar_Text());
        }

        if (!navigationBarName.equals(PropertyUtility.getMessage("driver.navigation.login"))) {
            try {
               // GeneralUtility utility = new GeneralUtility();
               // String pageName = utility.getPageHeader();
                if(action.isElementPresent(enableNotificationPage.Button_Sure())) {
                    action.click(enableNotificationPage.Button_Sure());
                    action.clickAlertButton("Allow");
                    // pageName = utility.getPageHeader();
                    Thread.sleep(3000);
                }
                if(action.isElementPresent(enableLocationPage.Button_Sure())) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Always Allow");
                    //pageName = utility.getPageHeader();
                }

            } catch (Exception e) {

            }
            navigationBarName =  action.getScreenHeader(driverHomePage.NavigationBar_Text());
            if (navigationBarName.equals("LOG IN")||navigationBarName.equals("ARRIVED")||navigationBarName.equals("ARRIVED")||navigationBarName.equals("EN ROUTE")||navigationBarName.equals("LOADING ITEM")||navigationBarName.equals("UNLOADING ITEM")||navigationBarName.equals("DRIVING TO DROPOFF"))
            {
                //Do nothing - Its fresh Bungii requested as precondition step
            }
                else
                homeSteps.i_select_something_from_driver_app_memu("LOGOUT");
        }


    }

    @And("^I get \"([^\"]*)\" from earnings page$")
    public void i_get_something_from_earnings_page(String earningsType) throws Throwable {
        try{
            switch (earningsType){
                case "Itemized Earnings":
                    Thread.sleep(7000);
                    action.click(driverHomePage.Button_ItemizedEarnings());
                    Thread.sleep(7000);
                    String itemizedEarnings = action.getText(driverHomePage.Text_ItemizedEarnings());
                    String actualItemizedEarnings = itemizedEarnings.substring(1);
                    cucumberContextManager.setScenarioContext("DRIVER_ITEMIZED_EARNINGS",actualItemizedEarnings);
                    break;
            }
            log("I should be able to get "+earningsType,
                    "I could get the "+earningsType,false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error while getting earnings from earnings page", true);
        }
    }
    @Then("^I compare with earnings from admin portal for \"([^\"]*)\"$")
    public void i_compare_with_earnings_from_admin_portal_for_something(String bungiiDriver) throws Throwable {
        try{
            switch (bungiiDriver){
                case "solo driver":
                    String driverEarningsOnAdminPortal= (String) cucumberContextManager.getScenarioContext("DRIVER_EARNINGS_ADMIN");
                    String driverEarningsOnDriverApp= (String) cucumberContextManager.getScenarioContext("DRIVER_ITEMIZED_EARNINGS");
                    testStepAssert.isEquals(driverEarningsOnDriverApp,driverEarningsOnAdminPortal,
                            "The earnings should be same on admin portal and driver app",
                            "The earnings are same on admin portal and driver app",
                            "The earnings are not same on admin portal and driver app");
                    break;
                case "duo first driver":
                    String firstDriverEarningsOnAdminPortal= (String) cucumberContextManager.getScenarioContext("DRIVER_ONE_EARNINGS_ADMIN");
                    String firstDriverEarningsOnDriverApp= (String) cucumberContextManager.getScenarioContext("DRIVER_ITEMIZED_EARNINGS");
                    testStepAssert.isEquals(firstDriverEarningsOnDriverApp,firstDriverEarningsOnAdminPortal,
                            "The earnings should be same on admin portal and driver app",
                            "The earnings are same on admin portal and driver app",
                            "The earnings are not same on admin portal and driver app");
                    break;
                case "duo second driver":
                    String secondDriverEarningsOnAdminPortal= (String) cucumberContextManager.getScenarioContext("DRIVER_TWO_EARNINGS_ADMIN");
                    String secondDriverEarningsOnDriverApp= (String) cucumberContextManager.getScenarioContext("DRIVER_ITEMIZED_EARNINGS");
                    testStepAssert.isEquals(secondDriverEarningsOnDriverApp,secondDriverEarningsOnAdminPortal,
                            "The earnings should be same on admin portal and driver app",
                            "The earnings are same on admin portal and driver app",
                            "The earnings are not same on admin portal and driver app");
                    break;
            }

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error while getting driver earnings", true);
        }
    }
    @And("^I verify all the elements on itemized earnings page$")
    public void i_verify_all_the_elements_on_itemized_earnings_page() throws Throwable {
        try{
            boolean isCorrectPage = false;
            isCorrectPage = utility.verifyPageHeader("ITEMIZED EARNINGS");
            testStepAssert.isTrue(isCorrectPage, "I should be naviagated to ITEMIZED EARNINGS screen",
                    "I should be navigated to ITEMIZED EARNINGS" , "I was not navigated to ITEMIZED EARNINGS screen ");

            testStepAssert.isElementDisplayed(driverHomePage.Dropdown_EndDate(),"The element should be displayed","The element is displayed","The element is not displayed");
            action.click(driverHomePage.Dropdown_EndDate());
            testStepAssert.isElementDisplayed(driverHomePage.Calendar_StartDate(),"The element should be displayed","The element is displayed","The element is not displayed");
            action.click(driverHomePage.Button_Cancel());

            testStepAssert.isElementDisplayed(driverHomePage.Dropdown_StartDate(),"The element should be displayed","The element is displayed","The element is not displayed");
            action.click(driverHomePage.Dropdown_StartDate());
            testStepAssert.isElementDisplayed(driverHomePage.Calendar_StartDate(),"The element should be displayed","The element is displayed","The element is not displayed");

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error while verifying if element is present", true);
        }
    }
    @And("^I verify all the elements on earnings page$")
    public void i_verify_all_the_elements_on_earnings_page() throws Throwable {
        try {
            boolean isCorrectPage = false;
            isCorrectPage = utility.verifyPageHeader("EARNINGS");
            testStepAssert.isTrue(isCorrectPage,
                    "I should be navigated to EARNINGS screen",
                    "I have navigated to EARNINGS screen" ,
                    "I was not navigated to EARNINGS screen ");

            testStepAssert.isElementDisplayed(driverHomePage.Dropdown_SelectYear(),"The element should be displayed","The element is displayed","The element is not displayed");
            testStepAssert.isElementDisplayed(driverHomePage.Button_ItemizedEarnings(),"The itemized earnings button should be displayed","The itemized earnings button is displayed","The itemized earnings button is not displayed");

            String actualDisclaimer = action.getText(driverHomePage.Text_Disclaimer());
            String expectedDisclaimer = PropertyUtility.getMessage("ios.earnings.page.disclaimer");
            testStepAssert.isEquals(actualDisclaimer,expectedDisclaimer,
                    "The Disclaimer displayed should be "+expectedDisclaimer,
                    "The Disclaimer displayed is "+expectedDisclaimer,
                    "The Disclaimer displayed is incorrect");

            testStepAssert.isElementDisplayed(driverHomePage.Text_MilesDriven(),"The Miles Driven should be displayed","The Miles Driven are displayed","The  Miles Driven are not displayed");
            testStepAssert.isElementDisplayed(driverHomePage.Text_WorkHours(),"The Work Hours should be displayed","The Work Hours are displayed","The Work Hours are not displayed");
            testStepAssert.isElementDisplayed(driverHomePage.Text_NoOfTrips(),"The number of trips should be displayed","The number of trips are displayed","The number of trips are not displayed");
            testStepAssert.isElementDisplayed(driverHomePage.Text_DisbursementInfo(),"The Disbursement Info should be displayed","The Disbursement Info  is displayed","The Disbursement Info is not displayed");


        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error while verifying if element is present", true);
        }
    }
    @And("^I search for \"([^\"]*)\" driver on driver details$")
    public void i_search_for_something_driver_on_driver_details(String driverName) throws Throwable {
        try{
            action.clearSendKeys(scheduledTripsPage.Text_SearchCriteria(),driverName);
            action.click(scheduledTripsPage.Button_Search());

            Thread.sleep(25000);
            log("I should be able to search for the driver",
                    "I was able to search the driver",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in searching the driver", true);
        }
    }
    @And("^I click on \"([^\"]*)\" icon on driver page$")
    public void i_click_on_something_icon_on_driver_page(String icon) throws Throwable {
        try {
            switch (icon){
                case "Driver Earnings":
                    action.click(driverHomePage.Icon_DriverEarnings());
                    break;
                case "View":
                    action.click(driverHomePage.Link_ViewTrips());
                    break;
            }
            log("I should be able to click on "+icon,
                    "I could click on"+icon,false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error while clicking on"+icon, true);
        }
    }
    @And("^I get \"([^\"]*)\" from driver earnings page on admin portal for \"([^\"]*)\"$")
    public void i_get_something_from_driver_earnings_page_on_admin_portal_for_something(String strArg1, String bungiiType) throws Throwable {
        try{
            switch(bungiiType){
                case "solo driver":
                    String driverEarnings = action.getText(driverHomePage.Text_DriverEarnings());
                    String actualDriverEarnings = driverEarnings.substring(1);
                    cucumberContextManager.setScenarioContext("DRIVER_EARNINGS_ADMIN",actualDriverEarnings);
                    break;
                case "duo first driver":
                    String firstDriverEarnings = action.getText(driverHomePage.Text_DriverEarnings());
                    String firstActualDriverEarnings = firstDriverEarnings.substring(1);
                    cucumberContextManager.setScenarioContext("DRIVER_ONE_EARNINGS_ADMIN",firstActualDriverEarnings);
                    break;
                case "duo second driver":
                    String secondDriverEarnings = action.getText(driverHomePage.Text_DriverEarnings());
                    String secondActualDriverEarnings = secondDriverEarnings.substring(1);
                    cucumberContextManager.setScenarioContext("DRIVER_TWO_EARNINGS_ADMIN",secondActualDriverEarnings);
                    break;

            }
            log("I should get the driver earnings from the admin portal",
                    "I could get the driver earnings from the admin portal",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in verifying details under Past Bungiis", true);
        }

    }
    @And("^I assign driver \"([^\"]*)\" for the trip$")
    public void i_assign_driver_something_for_the_trip(String driverName) throws Throwable {
        try{
            scheduledTripsPage.TextBox_DriverSearch().sendKeys(driverName);
            scheduledTripsPage.Select_TestDriver().click();
            String driver1Name=scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
            cucumberContextManager.setScenarioContext("DRIVER1_NAME",driver1Name);
            cucumberContextManager.setScenarioContext("DRIVER2_NAME",driver1Name);

            log("I should be able to assign driver to the trip","I am able to assign driver to the trip",false);

        }catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error in assigning driver "+driverName+" to the delivery by admin or viewing assigned driver slot", true);
        }
    }
    @And("^I click on the \"([^\"]*)\" and select future time$")
    public void i_click_on_the_something_and_select_future_time(String scheduleDate) throws Throwable {
        try{
            switch (scheduleDate) {
                case "Time":
                    action.click(scheduledTripsPage.TimePicker_Time());
                    Thread.sleep(3000);
                    action.click(scheduledTripsPage.Dropdown_ScheduledDateTime());
                    String timeChanged = scheduledTripsPage.TimePicker_Time().getText();
                    cucumberContextManager.setScenarioContext("Time_Changed", timeChanged);
                    break;

                default: break;
            }
            log("I can select future time/date",
                    "I was able to change time/date to future time/date", false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }

    }
    @And("^I click on \"([^\"]*)\" in the dropdown$")
    public void i_click_on_something_in_the_dropdown(String dropdown) throws Throwable {
        try{
            switch (dropdown) {
                case "Customer initiated":
                    Select selectCustomer = new Select((WebElement) scheduledTripsPage.Dropdown_Result());
                    selectCustomer.selectByVisibleText("Customer initiated");
                    break;
                case "Partner initiated":
                    Select selectPartner = new Select((WebElement) scheduledTripsPage.Dropdown_Result());
                    selectPartner.selectByVisibleText("Partner initiated");
                    break;
                default: break;
            }
            log("I view "+dropdown+" in the dropdown",
                    "I could see "+dropdown+" in the dropdown", false);
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }


    @And("^I click on \"([^\"]*)\" for change time$")
    public void i_click_on_something_for_change_time(String strArg1) throws Throwable {

        try {
            action.click(scheduledTripsPage.Dropdown_Result());

            log("I can click on reason dropdown",
                    "I clicked on reason dropdown", false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @And("^I get the new pickup reference generated$")
    public void i_get_the_new_pickup_reference_generated() throws Throwable {

        try {
            String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            pickupRequest = getLinkedPickupRef(pickupRequest);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
            log("I get the new pickup reference generated",
                    "Pickupref is " + pickupRequest, false);
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "New pickup reference is not generated",
                    true);
        }

    }

    @And("^I click on \"([^\"]*)\" button$")
    public void i_click_on_something_button(String button) throws Throwable {
        try{
            switch (button)
            {
                case "BACK":
                    action.click(driverHomePage.Button_BackItemizedEarnings());
                    break;
                case "STAY ONLINE":
                    action.click(driverBungiiCompletedPage.Button_StayOnline());
                    break;
                case "GO OFFLINE":
                    action.click(driverBungiiCompletedPage.Button_GoOffline());
                    break;
                case "VERIFY":
                    action.click(scheduledTripsPage.Button_VerifyDriver());
                    break;
                case "SAVE CHANGES":
                    action.click(scheduledTripsPage.Button_SaveChanges());
                    break;
                case "CLOSE":
                    action.click(scheduledTripsPage.Button_ClosePopUp());
                    break;
                case "REVIVE":
                    action.click(scheduledTripsPage.Button_ReviveTrip());
                    break;
                case "CONFIRM":
                    action.click(scheduledTripsPage.Button_Confirm());
                    Thread.sleep(10000);
                    String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    pickuprequest = dbUtility.getLinkedPickupRef(pickuprequest);
                    cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickuprequest);
                    break;
                case "GOT IT":
                    action.click(scheduledTripsPage.Button_GotIt());
                    break;
                case "SKIP CUSTOMER SIGNATURE":
                    action.click(scheduledTripsPage.Button_SkipCustomerRating());
                    break;
            }
            log("I should be able to click on "+button+" button","I am able to click on "+button+" button",false);
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I change the \"([^\"]*)\" to future time$")
    public void i_change_the_something_to_future_time(String strArg1) throws Throwable {
        try{
            Thread.sleep(2000);
            String newTime ="";
            String currentTime=scheduledTripsPage.Time_EditTripDetailsTime().getAttribute("value");
            switch (strArg1) {
                case "trip time to before the overlapping trip":
                    newTime = currentTime;
                    DateFormat formatterBefore = new SimpleDateFormat("hh:mm a");
                    Date Newtime = formatterBefore.parse(newTime);

                    Calendar cL = Calendar.getInstance();
                    cL.setTime(Newtime);
                    cL.add(Calendar.MINUTE,-30);

                    Date NewtimeOne = cL.getTime();
                    String newTimeOne = formatterBefore.format(NewtimeOne);

                    cucumberContextManager.setScenarioContext("NEW_TIME", newTimeOne);
                    action.click(scheduledTripsPage.Time_EditTripDetailsTime());
                    WebElement selectTime = SetupManager.getDriver().findElement(By.xpath("//li[contains(text(),'" + newTimeOne + "')]"));
                    action.click(selectTime);
                    logger.detail("I update time to "+newTimeOne,"I updated time to "+newTimeOne, false);
                    break;

                case "trip time to after the overlapping trip":
                    newTime = currentTime;
                    DateFormat formatterAfter = new SimpleDateFormat("hh:mm a");
                    Date NewtimeAfter = formatterAfter.parse(newTime);

                    Calendar cLAfter = Calendar.getInstance();
                    cLAfter.setTime(NewtimeAfter);
                    cLAfter.add(Calendar.MINUTE,30);

                    Date NewtimeAfterOne = cLAfter.getTime();
                    String newTimeAfterOne = formatterAfter.format(NewtimeAfterOne);

                    cucumberContextManager.setScenarioContext("NEW_TIME", newTimeAfterOne);
                    action.click(scheduledTripsPage.Time_EditTripDetailsTime());
                    selectTime = SetupManager.getDriver().findElement(By.xpath("//li[contains(text(),'" + newTimeAfterOne + "')]"));
                    action.click(selectTime);
                    logger.detail("I update time to "+newTimeAfterOne,"I updated time to "+newTimeAfterOne, false);
                    break;
            }
        }catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I Select reason as \"([^\"]*)\" to edit datetime$")
    public void i_select_reason_as_something_to_edit_datetime(String reason) throws Throwable {
        try {
            testStepAssert.isElementDisplayed(scheduledTripsPage.Select_EditReason(),"Select reason dropdown should be displayed on editing date/time","Select reason dropdown is displayed on editing date/time","Select reason dropdown is NOT displayed on editing date/time");
            switch (reason)
            {
                case "Partner initiated" :
                    action.selectElementByText(scheduledTripsPage.Select_EditReason(),reason);
                    break;
                case "Customer initiated" :
                    action.selectElementByText(scheduledTripsPage.Select_EditReason(),reason);
                    break;
            }

            log("I should be able to select the reason for date/time change",
                    "I am able to select the reason for date/time change",false);
        }
        catch (Exception e){
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^The trip should be present in schedule delivery$")
    public void the_trip_should_be_present_in_schedule_delivery() throws Throwable {
        try {
            Thread.sleep(3000);
            String driverAppTripTimeDate = action.getText(scheduledBungiipage.Text_Trip_DateTime());
            String customerAppTripTimeDate = (String) cucumberContextManager.getScenarioContext("CUSTOMER_APP_TRIP_TIME");
            testStepAssert.isEquals(driverAppTripTimeDate, customerAppTripTimeDate, "Trip should be present in schedule bungiis", "Trip is  present in schedule bungiis", "Trip is not present in schedule bungiis");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I should see \"([^\"]*)\" popup displayed$")
    public void i_should_see_something_popup_displayed(String expectedMessage) throws Throwable {
        try{
            switch (expectedMessage.toLowerCase()) {
                case "pickup instructions":
                    boolean isPickUpHeaderDisplayed = bungiiDetailsPage.Alert_PickupInstructions().isDisplayed();
                    testStepAssert.isTrue(isPickUpHeaderDisplayed, "Pickup instruction alert should be displayed", "Pickup instruction alert is displayed", "Pickup instruction alert is not displayed");
                    String pickupInstructionOnPopUp = action.getText(bungiiDetailsPage.Alert_PickupInstructions()).toLowerCase();
                    testStepVerify.isEquals(pickupInstructionOnPopUp, expectedMessage, expectedMessage + " Header should be displayed", pickupInstructionOnPopUp + " Header is displayed", expectedMessage + " Header is not displayed");
                    break;
                case "drop-off instructions":
                    boolean isDropOffHeaderDisplayed = bungiiDetailsPage.Alert_DropOffInstructions().isDisplayed();
                    testStepAssert.isTrue(isDropOffHeaderDisplayed, "DropOff instruction alert should be displayed", "DropOff instruction alert is displayed", "DropOff instruction alert is not displayed");
                    String dropOffInstructionOnPopUp = action.getText(bungiiDetailsPage.Alert_DropOffInstructions()).toLowerCase();
                    testStepVerify.isEquals(dropOffInstructionOnPopUp, expectedMessage, expectedMessage + " Header should be displayed", dropOffInstructionOnPopUp + " Header is displayed", expectedMessage + " Header is not displayed");
                    break;
            }
        }catch (Exception e){
            logger.error("Error performing step", e);
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

}
