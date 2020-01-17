package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.manager.*;
import com.bungii.android.pages.customer.*;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.sun.org.apache.xpath.internal.operations.Bool;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

import java.util.*;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.*;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class EstimateBungiiSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(EstimateBungiiSteps.class);
    EstimatePage bungiiEstimatePage = new EstimatePage();
    SearchingPage Page_DriverSearch = new SearchingPage();
    HomePage Page_CustHome = new HomePage();
    com.bungii.android.pages.driver.HomePage Page_DriverHome = new com.bungii.android.pages.driver.HomePage();
    //EstimatePage Page_Estimate = new EstimatePage();
    BungiiCompletePage Page_BungiiComplete = new BungiiCompletePage();
    WantDollar5Page Page_WantDollar5 = new WantDollar5Page();
    PromosPage Page_SaveMoney = new PromosPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    PropertyUtility properties = new PropertyUtility();
    SignupPage Page_Signup = new SignupPage();
    HomePage homePage = new HomePage();
    EstimatePage estimatePage= new EstimatePage();
    ScheduledBungiisPage scheduledBungiisPage=new ScheduledBungiisPage();
    private String[] loadTimeValue = {"15 mins", "30 mins", "45 mins", "60 mins", "75 mins", "90+ mins"};

    @When("^I tap on \"([^\"]*)\" on Bungii estimate$")
    public void iTapOnOnBungiiEstimate(String arg0) throws Throwable {
        try {
            switch (arg0) {
                case "two drivers selector":

                    action.click(Page_CustHome.Selector_Duo());
                    cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER", "DUO");
                    break;
                case "Get Estimate button":
                    //   while (action.isElementPresent(Page_CustHome.Button_GetEstimate(true)) == false)
                    //       iEnterOnBungiiEstimate("current location in pickup and dropoff fields");
                    //if (DriverAction.isElementPresent(Page_CustHome.Button_GetEstimate))
                    if (!action.isElementPresent(Page_CustHome.Button_GetEstimate()))
                        Thread.sleep(5000);
                    action.click(Page_CustHome.Button_GetEstimate());
                    Thread.sleep(2000);
                    String distance=action.getText(estimatePage.Text_GetDistance());
                    cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", distance);
                    break;

                case "Cancel during search":
                    action.click(Page_DriverSearch.Link_CancelSearch());
                    action.click(Page_DriverSearch.Button_CancelConfirm());
                    break;

                case "Promo Code":
                    Thread.sleep(3000);
                    action.click(bungiiEstimatePage.Link_Promo(true));
                    break;

                case "desired Promo Code":
                    String promoCode = (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE");
                    action.click((WebElement) getDriver().findElement(By.xpath("//android.widget.TextView[contains(@text,'" + promoCode + "')]")));
                    break;

                case "Payment Mode":
                    action.click(bungiiEstimatePage.Select_PaymentMode());
                    break;

                case "Request Bungii":
                    String cost=action.getText(bungiiEstimatePage.Text_GetCost());
                    cost=cost.replace("~","");
                    cucumberContextManager.setScenarioContext("BUNGII_COST_CUSTOMER",cost);
                    cucumberContextManager.setScenarioContext("PROMOCODE_VALUE", action.getText(estimatePage.Link_Promo(true)));
                    action.scrollToBottom();
                    action.scrollToBottom();

                    String checked="checked";
                    checked=action.getAttribute(bungiiEstimatePage.Checkbox_AgreeEstimate(), checked);
                    if(checked.equals("false")) {
                        action.click(bungiiEstimatePage.Checkbox_AgreeEstimate());
                    }
                    if (!action.isElementPresent(bungiiEstimatePage.Button_RequestBungii(true)))
                        action.scrollToBottom();
                    action.click(bungiiEstimatePage.Button_RequestBungii());
                    break;

                case "Yes on HeadsUp pop up":
                    action.waitUntilIsElementExistsAndDisplayed(bungiiEstimatePage.Alert_ConfirmRequestMessage(), 120L);
                    action.click(bungiiEstimatePage.Button_RequestConfirm());Thread.sleep(3000);
                    action.eitherTextToBePresentInElementText(bungiiEstimatePage.GenericHeader(true), "Success!", "SEARCHING…");
                    break;

                case "Done after requesting a Scheduled Bungii":
                    //vishal[23092019]:commented as scroll is mostly equired
                    //  if (!action.isElementPresent(Page_CustHome.Button_Done(true)))
                    action.scrollToBottom();
                    action.click(Page_CustHome.Button_Done());
                    break;

                case "Cancel on HeadsUp pop up":
                    action.waitUntilIsElementExistsAndDisplayed(bungiiEstimatePage.Alert_ConfirmRequestMessage());
                    action.click(bungiiEstimatePage.Button_RequestConfirmCancel());
                    break;

                case "X on complete":
                    action.click(Page_BungiiComplete.CloseRateTipPage());
                    break;

                case "OK on complete":
                    boolean isDuo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO");
                    if (isDuo) {
                        Thread.sleep(100000);
                    }Thread.sleep(5000);
                    try {
                        if(action.getText(Page_Signup.GenericHeader(true)).equals("COMPLETE"))
                            action.textToBePresentInElementText(Page_Signup.GenericHeader(),"BUNGII COMPLETE");

                    }catch (Exception e){}
                    action.click(Page_BungiiComplete.Button_OK());
                    break;

                case "No free money":
                    Thread.sleep(2000);
                    action.scrollToBottom();
                    if (!action.isElementPresent(Page_WantDollar5.Button_NoFreeMoney(true)))
                        action.scrollToBottom();
                    action.click(Page_WantDollar5.Button_NoFreeMoney());
                    break;
                case "back":
                    Thread.sleep(2000);
                    if (!action.isElementPresent(bungiiEstimatePage.Header_Estimate(true)))
                        Thread.sleep(5000);
                    ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                    break;
                case "ok on already scheduled bungii message":
                    testStepVerify.isEquals(action.getText(bungiiEstimatePage.Alert_ConfirmRequestMessage()), PropertyUtility.getMessage("customer.alert.alreadyscheduled"));
                    action.click(bungiiEstimatePage.Button_RequestConfirm());
                    break;

                case "back page":
                    action.click(Page_CustHome.Button_BackOfPage());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log(" I tap on " + arg0 + " on Bungii estimate",
                    "I  Selected " + arg0, true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on Bungii estimate$")
    public void iShouldSeeOnBungiiEstimate(String arg0) throws Throwable {
        try {
            // Write code here that turns the phrase above into concrete actions
            switch (arg0) {
                case "two drivers selected":

                    testStepAssert.isElementTextEquals(Page_CustHome.Switch_SoloDuo(), "2", "Driver trip should be Duo", "'2' text is displayed ", "2 text message is not displayed");
                    break;

                case "all elements":
                    testStepVerify.isEquals(action.getText(bungiiEstimatePage.Time()), "Now", "Bungii time should be 'Now'", "Bungii time is" + action.getText(bungiiEstimatePage.Time()));
                    testStepAssert.isElementDisplayed(bungiiEstimatePage.Header_Estimate(), "Estimate header should be displayed ", "Estimate header is displayed", "Estimate header is not displayed");


                    //        testStepVerify.isElementTextEquals(Page_Estimate.Text_PickupLocation(), PropertyUtility.getDataProperties("pickup.locationB"));
                    //       testStepVerify.isElementTextEquals(Page_Estimate.Text_DropOffLocation(), PropertyUtility.getDataProperties("dropoff.locationB"));
                    cucumberContextManager.setScenarioContext("PROMOCODE_VALUE", action.getText(bungiiEstimatePage.Link_Promo(true)));
                    double expectedTotalEstimate = utility.bungiiEstimate(action.getText(bungiiEstimatePage.Text_TripDistance()), action.getText(bungiiEstimatePage.Link_LoadingUnloadingTime()), utility.getEstimateTime(), action.getText(bungiiEstimatePage.Link_Promo(true)));
                    String loadTime = action.getText(bungiiEstimatePage.Text_TotalEstimate());
                    String truncValue = new DecimalFormat("#.##").format(expectedTotalEstimate);
                    if (!truncValue.contains(".")) truncValue = truncValue + ".00";
                    int index = truncValue.indexOf(".");
                    if (truncValue.substring(index).length() == 2) truncValue = truncValue + "0";
                    String actualValue = loadTime;//vishal[2503]2 digit verification//loadTime.substring(0, loadTime.length() - 1);
                    testStepVerify.isEquals(actualValue, "~$" + String.valueOf(truncValue));
                    //vishal[1803]
                    testStepVerify.isTrue(action.getText(bungiiEstimatePage.Text_TripDistance()).contains("miles"), "Trip distance should be in miles", "Trip Distance does contains miles , actual value" + action.getText(bungiiEstimatePage.Text_TripDistance()), "Trip Distance does not contains miles , actual value" + action.getText(bungiiEstimatePage.Text_TripDistance()));

                    testStepVerify.isElementNotEnabled(bungiiEstimatePage.Button_RequestBungii(true), "Request Bungii should be disabled", "Reguest Bungii button is disabled", "Reguest Bungii button is enabled");
                    action.scrollToBottom();
                    testStepVerify.isTrue(bungiiEstimatePage.Checkbox_AgreeEstimate().getAttribute("checked").equals("false"), "Estimate agree checkbox should be unchecked", "Estimate agree checkbox should be is checked");
                    break;

                case "driver cancelled":
                    testStepAssert.isElementDisplayed(Page_CustHome.Title_HomePage(), "Home page title should be displayed", "Home page title is displayed", "Home page title is not displayed");
                    testStepAssert.isElementDisplayed(Page_CustHome.Button_GetEstimate(), "Get estimate button should be displayed", "Get estimate button is displayed", "Get estimate button is not displayed");
                    break;
                case "Bungii posted Success page":
                    testStepAssert.isElementDisplayed(Page_CustHome.Image_Tick(), "Bungii Posted image should be displayed ", "Bungii posted image is displayed ", "Bungii posted image is not displayed");
                    break;
                case "previous values":
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_TripDistance(), (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
//                    testStepVerify.isElementTextEquals(Page_Estimate.Text_TotalEstimate(),(String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_PickupLocation_LineOne(), (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_PickupLocation_LineTwo(), (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_DropOffLocation_LineOne(), (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_DropOffLocation_LineTwo(), (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2"));

                    //    testStepVerify.isElementTextEquals(Page_Estimate.Link_LoadingUnloadingTime(),(String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME"));
                    //   testStepVerify.isElementTextEquals(Page_Estimate.Text_PickupLocation(),(String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
                    //   testStepVerify.isElementTextEquals(Page_Estimate.Text_DropOffLocation(),(String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"));
                    break;
                case "Bungii Estimate page with all details":
                    action.scrollToTop();
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Time(), (String) cucumberContextManager.getScenarioContext("BUNGII_TIME"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_TripDistance(), (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_TotalEstimate(), (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Link_LoadingUnloadingTime(), (String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME"));

                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_PickupLocation_LineOne(), (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_PickupLocation_LineTwo(), (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_DropOffLocation_LineOne(), (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));
                    testStepVerify.isElementTextEquals(bungiiEstimatePage.Text_DropOffLocation_LineTwo(), (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2"));

                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I am logged in as \"([^\"]*)\" customer$")
    public void iAmLoggedInAsCustomer(String arg0) throws Throwable {

        try {

            Thread.sleep(2000);
            switch (arg0) {
                case "existing":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    break;
                case "newly registered":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_newlyregistered.phonenumber"), PropertyUtility.getDataProperties("customer_newlyregistered.password"));
                    break;
                case "already having bungiis":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_withbungiis.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    break;
                case "having referral code":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_havingReferral.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    break;
                case "my":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    break;
                case "stage":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    break;
                case "valid":
                    //utility.loginToCustomerApp(PropertyUtility.getDataProperties("valid.customer.phone"), PropertyUtility.getDataProperties("valid.customer.password"));
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer_generic.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                    break;
                case "no promocode":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("valid.customer.no.promocode"), PropertyUtility.getDataProperties("valid.customer.password.no.promocode"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("valid.name.customer.no.promocode"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("valid.customer.no.promocode"));
                    break;
                case "valid boston":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("boston.customer.phone"), PropertyUtility.getDataProperties("boston.customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("boston.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("boston.customer.phone"));
                    break;
                case "valid baltimore":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("baltimore.customer.phone"), PropertyUtility.getDataProperties("baltimore.customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("baltimore.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("baltimore.customer.phone"));
                    break;
                case "valid atlanta":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("atlanta.customer.phone"), PropertyUtility.getDataProperties("atlanta.customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("atlanta.customer.phone"));
                    break;
                case "valid customer 2":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("atlanta.customer2.phone"), PropertyUtility.getDataProperties("atlanta.customer2.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2", PropertyUtility.getDataProperties("atlanta.customer2.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2_PHONE", PropertyUtility.getDataProperties("atlanta.customer2.phone"));
                    break;
                case "newly created user":
                    utility.loginToCustomerApp((String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER"), PropertyUtility.getDataProperties("customer.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER"));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log(" I am logged in as" + arg0 + " customer",
                    " I am logged in as" + arg0 + " customer", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I enter \"([^\"]*)\" on Bungii estimate$")
    public void iEnterOnBungiiEstimate(String arg0) throws Throwable {
        try {
            Thread.sleep(2000);
            switch (arg0) {
                case "valid pickup and dropoff locations":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.locationB"));
                    Thread.sleep(2000);
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.locationB"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");
                    break;
                case "goa location in pickup and dropoff fields long distance":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.locationA"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.locationA"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");
                    break;
                case "kansas pickup and dropoff locations":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.kansas"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.kansas"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");
                    Thread.sleep(1000);
                    break;
                case "kansas short pickup and dropoff locations":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location2.kansas"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location2.kansas"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");
                    Thread.sleep(1000);
                    break;
                case "atlanta pickup and dropoff locations away from driver":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.away.atlanta"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.away.atlanta"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "atlanta");
                    Thread.sleep(1000);
                    break;
                case "boston pickup and dropoff locations":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.boston"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.boston"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "boston");
                    Thread.sleep(1000);
                    break;
                case "baltimore pickup and dropoff locations":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.baltimore"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.baltimore"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "baltimore");
                    Thread.sleep(1000);
                    break;
                case "atlanta pickup and dropoff locations":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.atlanta"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.atlanta"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "atlanta");
                    Thread.sleep(1000);
                    break;
                case "current location in pickup and dropoff fields":
                    //string a = driver.PageSource;
                    action.click(Page_CustHome.Button_Locator());
                    Thread.sleep(5500);
/*                    if (action.isElementPresent(Page_CustHome.Text_ETAvalue(true))) {
                        int ETA = Integer.parseInt(Page_CustHome.Text_ETAvalue().getText().replace(" MINS", ""));
                        if (ETA <= 30)
                            action.click(Page_CustHome.Button_ETASet());
                    }*/
                    action.click(Page_CustHome.Button_ETASet());

                    action.click(Page_CustHome.Button_Locator());
                    action.click(Page_CustHome.Button_ETASet());
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");
                    break;
                case "current location in pickup and dropoff fields long distance":
                    action.click(Page_CustHome.Button_Locator());
                    action.click(Page_CustHome.Button_Locator());

                    Thread.sleep(5500);
                    if (action.isElementPresent(Page_CustHome.Text_ETAvalue(true))) {
                        int ETA = Integer.parseInt(Page_CustHome.Text_ETAvalue().getText().replace(" MINS", ""));
                        if (ETA <= 30)
                            action.click(Page_CustHome.Button_ETASet());
                    }
                    Thread.sleep(2000);
                    action.hideKeyboard();
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.locationA"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");

                    break;

                case "Goa pickup and dropoff locations":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Goa"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Goa"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");
                    action.click(Page_CustHome.Button_ETASet());
                    Thread.sleep(2000);
                    testStepAssert.isNotElementDisplayed(homePage.Text_ETAvalue(),"Less than 30mins", "Less than 30mins","More than 30mins");
                    break;

                case "kansas pickup and dropoff locations less than 150 miles":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Kansas.less.150"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Kansas.less.150"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Kansas");
                    //action.click(Page_CustHome.Button_ETASet());
                    Thread.sleep(2000);
                    testStepAssert.isElementDisplayed(Page_CustHome.Button_GetEstimate(),"Less than 150 miles", "Less than 150 miles","More than 150 miles");
                    break;

                case "kansas pickup and dropoff locations equal to 150 miles":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Kansas.equal.150"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Kansas.equal.150"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Kansas");
                    //action.click(Page_CustHome.Button_ETASet());
                    Thread.sleep(2000);
                    testStepAssert.isElementDisplayed(Page_CustHome.Button_GetEstimate(),"Equal to 150 miles", "Equal to 150 miles","Not equal to 150 miles");
                    break;

                case "kansas pickup and dropoff locations greater than 150 miles":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Kansas.more.150"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Kansas.more.150"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Kansas");
                    //action.click(Page_CustHome.Button_ETASet());
                    Thread.sleep(2000);
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            //savePickupAddress();
            //saveDropupAddress();

            String pickUpLocationLine1 = action.getText(Page_CustHome.TextBox_PickUpLocLine1()), pickUpLocationLine2 = action.getText(Page_CustHome.TextBox_PickUpLocLine2());

            //   Page_CustHome.Button_GetEstimate()
            if (!action.isElementPresent(Page_CustHome.TextBox_DropOff(true))) {
                Thread.sleep(5000);
                action.click(Page_CustHome.Button_ETASet());
            }
            action.waitUntilIsElementExistsAndDisplayed(Page_CustHome.Button_GetEstimate());
            //     action.invisibilityOfElementLocated(Page_CustHome.Button_ETASet(true));
            String dropUpLocationLine1 = action.getText(Page_CustHome.TextBox_DropOffLine1()), dropUpLocationLine2 = action.getText(Page_CustHome.TextBox_DropOffLine2());

            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", pickUpLocationLine1);
            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", pickUpLocationLine2);
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", dropUpLocationLine1);
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", dropUpLocationLine2);

            testStepAssert.isFalse(pickUpLocationLine1.equals(""), "I should able to select pickup location", "Pickup location was selected , Pickup value is " + pickUpLocationLine1, "I was not able select pickup location");
            testStepAssert.isFalse(dropUpLocationLine2.equals(""), "I should able to select drop location", "drop location was selected , drop value is " + dropUpLocationLine1, "I was not able select pickup location");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I enter the \"([^\"]*)\" on the Bungii estimate$")
    public void i_enter_the_something_on_the_bungii_estimate(String strArg1) throws Throwable {
        try{
            switch (strArg1)
            {
            case "kansas pickup and dropoff locations greater than 150 miles":
                if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                    action.click(Page_CustHome.Button_ClearPickUp());
                utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Kansas.more.150"));
                utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Kansas.more.150"));
                cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "Kansas");
                Thread.sleep(2000);
                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }

    }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    private void savePickupAddress() {
        String pickUpLocationLine1 = action.getText(Page_CustHome.TextBox_PickUpLocLine1()), pickUpLocationLine2 = action.getText(Page_CustHome.TextBox_PickUpLocLine2());
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", pickUpLocationLine1);
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", pickUpLocationLine2);
    }

    private void saveDropupAddress() {
        String dropUpLocationLine1 = action.getText(Page_CustHome.TextBox_DropOffLine1()), dropUpLocationLine2 = action.getText(Page_CustHome.TextBox_DropOffLine2());
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", dropUpLocationLine1);
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", dropUpLocationLine2);
    }

/*    @And("^I add \"([^\"]*)\" PromoCode$")
    public void iAddPromoCode(String arg0) throws Throwable {
        try {
        String promoCode="";
        switch (arg0) {
            case "valid":
                promoCode= PropertyUtility.getDataProperties("promocode.valid");
                break;
            case "fixed valid":
                promoCode= PropertyUtility.getDataProperties("promocode.fixedvalid");
                break;
            case "invalid":
                promoCode= PropertyUtility.getDataProperties("promocode.invalid");
                break;
            case "expired":
                promoCode= PropertyUtility.getDataProperties("promocode.expired");
                break;
            case "referral":
                promoCode= PropertyUtility.getDataProperties("referral.code");
                break;
            case "first time":
                promoCode= PropertyUtility.getDataProperties("promocode.firsttime");
                break;
            case "used one off":
                promoCode=PropertyUtility.getDataProperties("promocode.useedoneoff");
                break;
            default:
                break;
        }
        action.sendKeys(Page_SaveMoney.Textfield_PromoCode(), promoCode);

        log(" I should able to add " + arg0 + " promo code ",
                "I entered promo code '" + promoCode + "'", true);

        } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }*/

    /*    @And("^I tap \"([^\"]*)\" on Save Money page$")
        public void iTapOnSaveMoneyPage(String arg0) throws Throwable {
            try {
            switch (arg0) {
                case "Add":
                    action.click(Page_SaveMoney.Button_AddPromoPage());
                    break;
                case "Get More Money":
                    action.click(Page_SaveMoney.Button_GetMoreMoney());
                    break;
                default:
                    break;
            }
            log(" I should able to tap " + arg0 + " on Save Money page",
                    "I tapped on " + arg0 + " on Save Money Page", true);
            } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
            }
        }*/
    @And("^I get Bungii details on Bungii Estimate$")
    public void i_get_bungii_details_on_bungii_estimate() throws Throwable {
        action.scrollToTop();
        // SAVE required values in scenario context
        cucumberContextManager.setScenarioContext("BUNGII_TIME", action.getText(bungiiEstimatePage.Time()));
        cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", action.getText(bungiiEstimatePage.Text_TripDistance()));
        cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", action.getText(bungiiEstimatePage.Text_TotalEstimate()));
        cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", action.getText(bungiiEstimatePage.Link_LoadingUnloadingTime()));
        //Sprint 29 change
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", action.getText(bungiiEstimatePage.Text_PickupLocation_LineOne()));
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", action.getText(bungiiEstimatePage.Text_PickupLocation_LineTwo()));
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", action.getText(bungiiEstimatePage.Text_DropOffLocation_LineOne()));
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", action.getText(bungiiEstimatePage.Text_DropOffLocation_LineTwo()));

    }

    @And("^I get Bungii location details on Bungii Estimate$")
    public void i_get_bungii_locations_details_on_bungii_estimate() throws Throwable {
        // SAVE required values in scenario context
        //Sprint 29 change
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", action.getText(bungiiEstimatePage.Text_PickupLocation_LineOne()));
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", action.getText(bungiiEstimatePage.Text_PickupLocation_LineTwo()));
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", action.getText(bungiiEstimatePage.Text_DropOffLocation_LineOne()));
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", action.getText(bungiiEstimatePage.Text_DropOffLocation_LineTwo()));
    }

    @And("^I add loading/unloading time of \"([^\"]*)\"$")
    public void iAddLoadingUnloadingTimeOf(String arg0) throws Throwable {
        try {
            if (!action.isElementPresent(bungiiEstimatePage.Header_Estimate(true)))
                Thread.sleep(5000);

            action.click(bungiiEstimatePage.Link_LoadingUnloadingTime());
            if (!action.isElementPresent(bungiiEstimatePage.LoadingUnloadingTime_15(true)))
                action.click(bungiiEstimatePage.Link_LoadingUnloadingTime());

            switch (arg0) {
                case "15 mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_15());
                    break;

                case "30 mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_30());
                    break;

                case "45 mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_45());
                    break;

                case "60 mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_60());
                    break;

                case "75 mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_75());
                    break;

                case "90+ mins":
                    action.click(bungiiEstimatePage.LoadingUnloadingTime_90());
                    break;

                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            Thread.sleep(5000);
            //save load time in cucumber context
            cucumberContextManager.setScenarioContext("BUNGII_LOAD_TIME", arg0.replace(" mins", ""));
            log(" I add loading/unloading time " + arg0 + "on Estimate page",
                    "I clicked on " + arg0 + "as Estimate loading/unloading time ", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I add \"([^\"]*)\" photos to the Bungii$")
    public void iAddPhotosToTheBungii(String arg0) throws Throwable {
        try {
            action.waitUntilIsElementExistsAndDisplayed(bungiiEstimatePage.Header_Estimate(), 30L);
            int i = 0;
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            //    action.scrollToBottom();
            do {
                if (!action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)))
                    action.swipeLeft(bungiiEstimatePage.Row_Images());

                if (!action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)))
                    action.scrollToBottom();
                if (!action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)) && i >= 3)
                {
                    testStepAssert.isFalse(action.isElementPresent(bungiiEstimatePage.Link_AddPhoto(true)),"False","True" );
                    break;
                }
                action.click(bungiiEstimatePage.Link_AddPhoto());
                Thread.sleep(2000);
                //adding most probable outcome first
                if (action.isElementPresent(bungiiEstimatePage.Option_Camera(true))) {
                    //do nothing,
                }
                else if (action.isElementPresent(bungiiEstimatePage.Message_CameraPermissions(true)))
                    action.click(bungiiEstimatePage.Permissions_CameraAllow());

                action.click(bungiiEstimatePage.Option_Camera());
                String manufacturer = driver.getCapabilities().getCapability("deviceType").toString();
                if (manufacturer.equalsIgnoreCase("MOTOROLA")) {
                    Thread.sleep(5000);
                    // driver.tap(1, 100, 500, 1);
                    new TouchAction(driver)
                            .tap(point(100, 500))
                            .waitAction(waitOptions(Duration.ofMillis(250))).perform();
                    Thread.sleep(2000);
                    if (action.isElementPresent(bungiiEstimatePage.Button_Review(true)))
                        action.click(bungiiEstimatePage.Button_Review());
                    else if (driver.getCapabilities().getCapability("deviceModel").toString().contains("Moto G") &&driver.getCapabilities().getCapability("deviceModel").toString().contains("4")) {
                        action.click(new Point(644, 1562));
                        Thread.sleep(2000);
                        logger.detail("clicked by cordinate");
                        if (!action.isElementPresent(bungiiEstimatePage.Header_Estimate(true))) {
                            Thread.sleep(120000);//wait for auto approve
                            logger.detail("wait to auto approve");
                        }
                    }
                }
                if (manufacturer.equalsIgnoreCase("") || manufacturer.equalsIgnoreCase("SAMSUNG")) {
                    action.click(bungiiEstimatePage.Button_Camera_ClickAlternate());
                    //DriverAction.keyBoardEvent(AndroidKeyCode.Keycode_CAMERA);
                    Thread.sleep(2000);
                    action.click(bungiiEstimatePage.Button_Camera_OK());
                }
                Thread.sleep(2000);

                i++;
            } while (i < Integer.parseInt(arg0));

/*            if (action.isElementPresent(Page_Estimate.Text_PickupLocation())) {
                //code to be added incase of "Invalid Image error"
            }*/


            testStepVerify.isElementDisplayed(bungiiEstimatePage.Button_SelectedImage(), "I add " + arg0 + " photos to the Bungii", "I selected photos on estimate page", "Selected image was not displayed on Estimate page");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("I select Bungii Time as {string}")
    public void iSelectBungiiTimeAs(String arg0) {
        try {
            switch (arg0) {
                case "next possible scheduled":
                case "OLD BUNGII TIME":
                    utility.selectBungiiTime();
                    break;
                case "next possible scheduled for duo":
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            String bungiiTime = action.getText(bungiiEstimatePage.Time());
            if (arg0.equalsIgnoreCase("OLD BUNGII TIME")) {
                testStepVerify.isEquals(bungiiTime, (String) cucumberContextManager.getScenarioContext("BUNGII_TIME"), "I selected bungii time as old bungii time:" + bungiiTime, "I was not able to select bungii with old bungii time , Bungii time" + bungiiTime + " expected time" + (String) cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            }
            cucumberContextManager.setScenarioContext("BUNGII_TIME", bungiiTime);
            log(" I select Bungii Time as " + arg0,
                    " I select Bungii Time as " + bungiiTime + ", Selected Bungii time is " + bungiiTime, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" information icon should display correct information$")
    public void something_information_icon_should_display_correct_information(String iconName) throws Throwable {
        try {

            String loadTime = (String) cucumberContextManager.getScenarioContext("BUNGII_LOAD_TIME");
            String expectedMessage = "", actualMessage = "";


            switch (iconName.toUpperCase()) {
                case "LOAD/UPLOAD TIME":
                    expectedMessage = PropertyUtility.getMessage("customer.info.loadtime");
                    action.click(bungiiEstimatePage.Button_InfoLoadTime());
                    break;
                case "TIME":
                    expectedMessage = PropertyUtility.getMessage("customer.info.time");
                    action.click(bungiiEstimatePage.Button_InfoTime());

                    break;
                case "TOTAL ESTIMATE":
                    expectedMessage = PropertyUtility.getMessage("customer.info.totalestimate").replaceAll("<TIME>", loadTime.trim());
                    action.click(bungiiEstimatePage.Button_InfoEstimate());
                    break;
                default:
                    fail("Step  Should be successful",
                            "UnImplemented STEP , please verify test step", true);
                    break;
            }
            action.waitUntilIsElementExistsAndDisplayed(bungiiEstimatePage.Alert_ConfirmRequestMessage(), 120L);
            actualMessage = action.getText(bungiiEstimatePage.Alert_ConfirmRequestMessage());
            testStepVerify.isEquals(actualMessage, expectedMessage);

            action.click(bungiiEstimatePage.Button_RequestConfirm());
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^check if I have ability to select different load time and Estimate cost is re calculated$")
    public void check_if_i_have_ability_to_select_different_load_time_and_estimate_cost_is_re_calculated() {
        try {
            String oldEstimateValue = action.getText(bungiiEstimatePage.Text_TotalEstimate());
            action.click(bungiiEstimatePage.Link_LoadingUnloadingTime());
            for (int i = 0; i < loadTimeValue.length; i++) {

                if (i != 0)
                    action.click(bungiiEstimatePage.Link_LoadingUnloadingTime());
                List<WebElement> loadTime = bungiiEstimatePage.LoadingUnloadingTime();
                String optionText = action.getText(loadTime.get(i));
                boolean flag = optionText.equals(loadTimeValue[i]);

                //Click on options
                action.click(loadTime.get(i));

                testStepVerify.isTrue(flag,
                        "I should able to to select " + loadTimeValue[i] + " as load time",
                        "I was able to to select " + loadTimeValue[i] + " as load time",
                        "I was not able to to select " + loadTimeValue[i] + " as load time");

                String newEstimateValue = action.getText(bungiiEstimatePage.Text_TotalEstimate());
                Thread.sleep(2000);
/*                if (i == 0)
                    testStepVerify.isTrue(newEstimateValue.equals(oldEstimateValue),
                            "total Estimated cost is calculated considering  Loading/unloading time",
                            "Total Estimate cost for first scroll value should be same as default one, Previous cost is " + oldEstimateValue + " , new cost is " + newEstimateValue,
                            "Total Estimate cost was recalculated");
                else*/
                    testStepVerify
                            .isFalse(newEstimateValue.equals(oldEstimateValue),
                                    "total Estimated cost is calculated considering  Loading/unloading time",
                                    "Total Estimate cost is recalculated , previous cost is" + oldEstimateValue + " , new cost is" + newEstimateValue,
                                    "Total Estimate cost was not recalculated");
                oldEstimateValue = newEstimateValue;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^I verify that selected time is next available time$")
    public void i_verify_that_selected_time_is_next_available_time(){
        try{
        String time= (String) cucumberContextManager.getScenarioContext("TIME");
          time=formatDate(time,8);
        String actualtime=action.getText(bungiiEstimatePage.Text_BungiiTime());
        testStepVerify.isEquals(time, actualtime);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    public String formatDate(String date,int position){
        Calendar calOne = Calendar.getInstance();
        int year = calOne.get(Calendar.YEAR);
        String stringToBeInserted=year+" - ";

        // Create a new string
        String newDate = new String();

        for (int i = 0; i < date.length(); i++) {
            if (i == position) {
                // Insert the string to be inserted
                // into the new string
                newDate += stringToBeInserted;
            }
            // Insert the original string character
            // into the new string
            newDate += date.charAt(i);
        }
        return newDate;
    }

}
