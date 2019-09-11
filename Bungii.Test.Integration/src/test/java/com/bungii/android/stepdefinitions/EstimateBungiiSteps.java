package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.*;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.*;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class EstimateBungiiSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(EstimateBungiiSteps.class);
    EstimatePage bungiiEstimatePage = new EstimatePage();
    SearchingPage Page_DriverSearch = new SearchingPage();
    HomePage Page_CustHome = new HomePage();
    EstimatePage Page_Estimate = new EstimatePage();
    BungiiCompletePage Page_BungiiComplete = new BungiiCompletePage();
    WantDollar5Page Page_WantDollar5 = new WantDollar5Page();
    PromosPage Page_SaveMoney = new PromosPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    PropertyUtility properties = new PropertyUtility();
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
                    if(!action.isElementPresent(Page_CustHome.Button_GetEstimate()))
                        Thread.sleep(2000);
                    action.click(Page_CustHome.Button_GetEstimate());
                    break;

                case "Cancel during search":
                    action.click(Page_DriverSearch.Link_CancelSearch());
                    action.click(Page_DriverSearch.Button_CancelConfirm());
                    break;

                case "Promo Code":
                    action.click(Page_Estimate.Link_Promo());
                    break;

                case "desired Promo Code":
                    String promoCode = (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE");
                    action.click((WebElement) getDriver().findElement(By.xpath("//android.widget.TextView[contains(@text,'" + promoCode + "')]")));
//                action.click(Page_Estimate.Select_PromoCode());
                    break;

                case "Payment Mode":
                    action.click(Page_Estimate.Select_PaymentMode());
                    break;

                case "Request Bungii":
                    if (!action.isElementPresent(Page_Estimate.Checkbox_AgreeEstimate(true)))
                        action.scrollToBottom();
                    action.click(Page_Estimate.Checkbox_AgreeEstimate());

                    if (!action.isElementPresent(Page_Estimate.Button_RequestBungii(true)))
                        action.scrollToBottom();
                    action.click(Page_Estimate.Button_RequestBungii());
                    break;

                case "Yes on HeadsUp pop up":
                    action.waitUntilIsElementExistsAndDisplayed(Page_Estimate.Alert_ConfirmRequestMessage(), 120L);
                    action.click(Page_Estimate.Button_RequestConfirm());
                    action.invisibilityOfElementLocated(Page_Estimate.Alert_ConfirmRequestMessage(true));Thread.sleep(2000);
                    //--------*to be worked on*-------------
                    //If time has passed
                    /*if (DriverAction.isElementPresent(Page_Estimate.Alert_DelayRequestingTrip))
                    {
                        if (deviceType.Equals("SamsungS5") || deviceType.Equals("SamsungS6"))
                        {
                            DriverAction.click(Page_Estimate.Button_DelayRequestingTrip_OK);
                            DriverAction.click(Page_Estimate.Time);

                            //choose current date
                            DriverAction.click(Page_Estimate.Samsung_CurrentSelectedDate);
                            DriverAction.click(Page_Estimate.Samsung_Date_OK);

                            //set time with 15 mins delay
                            DriverAction.click(Page_Estimate.Samsung_SetTime_Min_Next);
                            UtilFunctions.ScrollUp(Page_Estimate.Samsung_SetTime_Min_Next);
                            if (Page_Estimate.Samsung_SetTime_Min_Current.Text == "00" || Page_Estimate.Samsung_SetTime_Min_Current.Text == "15" || Page_Estimate.Samsung_SetTime_Min_Current.Text == "30")
                                DriverAction.click(Page_Estimate.Samsung_SetTime_Hour_Next);

                            DriverAction.click(Page_Estimate.Button_RequestConfirm);
                        }
                        Thread.Sleep(2000);
                    }*/
                    break;

                case "Done after requesting a Scheduled Bungii":
                    if (!action.isElementPresent(Page_CustHome.Button_Done(true)))
                        action.scrollToBottom();
                    action.click(Page_CustHome.Button_Done());
                    break;

                case "Cancel on HeadsUp pop up":
                    action.waitUntilIsElementExistsAndDisplayed(Page_Estimate.Alert_ConfirmRequestMessage());
                    action.click(Page_Estimate.Button_RequestConfirmCancel());
                    break;

                case "X on complete":
                    action.click(Page_BungiiComplete.CloseRateTipPage());
                    break;

                case "OK on complete":
                    boolean isDuo=String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase( "DUO");
                    if(isDuo){action.waitUntilIsElementExistsAndDisplayed(Page_BungiiComplete.Button_OK());Thread.sleep(20000);}
                    action.waitUntilIsElementExistsAndDisplayed(Page_BungiiComplete.Button_OK());
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
                    ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                    break;
                case "ok on already scheduled bungii message":
                    testStepVerify.isEquals(action.getText(bungiiEstimatePage.Alert_ConfirmRequestMessage()),PropertyUtility.getMessage("customer.alert.alreadyscheduled"));
                    action.click(bungiiEstimatePage.Button_RequestConfirm());
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
    public void     iShouldSeeOnBungiiEstimate(String arg0) throws Throwable {
        try {
            // Write code here that turns the phrase above into concrete actions
            switch (arg0) {
                case "two drivers selected":

                    testStepAssert.isElementTextEquals(Page_CustHome.Switch_SoloDuo(), "2", "Driver trip should be Duo", "'2' text is displayed ", "2 text message is not displayed");
                    break;

                case "all elements":
                  //  utility.getEstimateTime();
                    testStepAssert.isElementDisplayed(Page_Estimate.Header_Estimate(), "Estimate header should be displayed ", "Estimate header is displayed", "Estimate header is not displayed");


            //        testStepVerify.isElementTextEquals(Page_Estimate.Text_PickupLocation(), PropertyUtility.getDataProperties("pickup.locationB"));
             //       testStepVerify.isElementTextEquals(Page_Estimate.Text_DropOffLocation(), PropertyUtility.getDataProperties("dropoff.locationB"));
                    cucumberContextManager.setScenarioContext("PROMOCODE_VALUE",action.getText(Page_Estimate.Link_Promo()));
                    double expectedTotalEstimate = utility.bungiiEstimate(action.getText(Page_Estimate.Text_TripDistance()), action.getText(Page_Estimate.Link_LoadingUnloadingTime()), utility.getEstimateTime(), action.getText(Page_Estimate.Link_Promo()));
                    String loadTime = action.getText(Page_Estimate.Text_TotalEstimate());
                    String truncValue = new DecimalFormat("#.##").format(expectedTotalEstimate);
                    if(!truncValue.contains("."))truncValue=truncValue+".00";
                    int index=truncValue.indexOf(".");
                    if(truncValue.substring(index).length()==2)truncValue=truncValue+"0";
                    String actualValue = loadTime;//vishal[2503]2 digit verification//loadTime.substring(0, loadTime.length() - 1);
                    testStepVerify.isEquals(actualValue,"$" + String.valueOf(truncValue));
                    //vishal[1803]
                    testStepVerify.isTrue(action.getText(Page_Estimate.Text_TripDistance()).contains("miles"),"Trip distance should be in miles","Trip Distance does contains miles , actual value"+action.getText(Page_Estimate.Text_TripDistance()),"Trip Distance does not contains miles , actual value"+action.getText(Page_Estimate.Text_TripDistance()));

                    testStepVerify.isElementNotEnabled(Page_Estimate.Button_RequestBungii(true),"Request Bungii should be disabled","Reguest Bungii button is disabled","Reguest Bungii button is enabled");
                    action.scrollToBottom();
                    testStepVerify.isTrue(Page_Estimate.Checkbox_AgreeEstimate().getAttribute("checked").equals("false"),"Estimate agree checkbox should be unchecked","Estimate agree checkbox should be is checked");
                    testStepVerify.isEquals(action.getText(Page_Estimate.Time()),"Now", "Bungii time should be 'Now'", "Bungii time is"+action.getText(Page_Estimate.Time()));
                    break;

                case "driver cancelled":
                    testStepAssert.isElementDisplayed(Page_CustHome.Title_HomePage(), "Home page title should be displayed", "Home page title is displayed", "Home page title is not displayed");
                    testStepAssert.isElementDisplayed(Page_CustHome.Button_GetEstimate(), "Get estimate button should be displayed", "Get estimate button is displayed", "Get estimate button is not displayed");
                    break;
                case "Bungii posted Success page":
                    testStepAssert.isElementDisplayed(Page_CustHome.Image_Tick(), "Bungii Posted image should be displayed ", "Bungii posted image is displayed ", "Bungii posted image is not displayed");
                    break;
                case "previous values":
                    testStepVerify.isElementTextEquals(Page_Estimate.Text_TripDistance(),(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    testStepVerify.isElementTextEquals(Page_Estimate.Text_TotalEstimate(),(String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
                 //    testStepVerify.isElementTextEquals(Page_Estimate.Link_LoadingUnloadingTime(),(String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME"));
                 //   testStepVerify.isElementTextEquals(Page_Estimate.Text_PickupLocation(),(String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
                 //   testStepVerify.isElementTextEquals(Page_Estimate.Text_DropOffLocation(),(String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"));
                    break;
                case "Bungii Estimate page with all details":
                    action.scrollToTop();
                    testStepVerify.isElementTextEquals(Page_Estimate.Time(),(String) cucumberContextManager.getScenarioContext("BUNGII_TIME"));
                    testStepVerify.isElementTextEquals(Page_Estimate.Text_TripDistance(),(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    testStepVerify.isElementTextEquals(Page_Estimate.Text_TotalEstimate(),(String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
                    testStepVerify.isElementTextEquals(Page_Estimate.Link_LoadingUnloadingTime(),(String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME"));

                    testStepVerify.isElementTextEquals(Page_Estimate.Text_PickupLocation_LineOne(),(String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));
                    testStepVerify.isElementTextEquals(Page_Estimate.Text_PickupLocation_LineTwo(),(String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));
                    testStepVerify.isElementTextEquals(Page_Estimate.Text_DropOffLocation_LineOne(),(String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));
                    testStepVerify.isElementTextEquals(Page_Estimate.Text_DropOffLocation_LineTwo(),(String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2"));

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
                    cucumberContextManager.setScenarioContext("CUSTOMER",PropertyUtility.getDataProperties("customer_generic.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE",PropertyUtility.getDataProperties("customer_generic.phonenumber") );
                    break;
                case "no promocode":
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("valid.customer.no.promocode"), PropertyUtility.getDataProperties("valid.customer.password.no.promocode"));
                    cucumberContextManager.setScenarioContext("CUSTOMER",PropertyUtility.getDataProperties("valid.name.customer.no.promocode"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE",PropertyUtility.getDataProperties("valid.customer.no.promocode") );
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log(" I am logged in as"+arg0+" customer",
                    " I am logged in as"+arg0+" customer", true);
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
                    if(action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.locationB"));
                    Thread.sleep(2000);
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.locationB"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE","kansas");
                    break;
                case "goa location in pickup and dropoff fields long distance":
                    if(action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.locationA"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.locationA"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE","goa");
                    break;
                case "kansas pickup and dropoff locations":
                    if(action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.kansas"));
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.kansas"));
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE","kansas");
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
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE","goa");
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
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE","goa");

                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            //savePickupAddress();
            //saveDropupAddress();

            String pickUpLocationLine1 = action.getText(Page_CustHome.TextBox_PickUpLocLine1()),pickUpLocationLine2= action.getText(Page_CustHome.TextBox_PickUpLocLine2());

            if(!action.isElementPresent(Page_CustHome.TextBox_DropOff(true)))
            {
                Thread.sleep(5000);
                action.click(Page_CustHome.Button_ETASet());
            }
            action.invisibilityOfElementLocated(Page_CustHome.Button_ETASet(true));
            String dropUpLocationLine1 = action.getText(Page_CustHome.TextBox_DropOffLine1()),dropUpLocationLine2 = action.getText(Page_CustHome.TextBox_DropOffLine2());

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

    private void savePickupAddress() {
        String pickUpLocationLine1 = action.getText(Page_CustHome.TextBox_PickUpLocLine1()),pickUpLocationLine2= action.getText(Page_CustHome.TextBox_PickUpLocLine2());
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", pickUpLocationLine1);
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", pickUpLocationLine2);
    }

    private void saveDropupAddress() {
        String dropUpLocationLine1 = action.getText(Page_CustHome.TextBox_DropOffLine1()),dropUpLocationLine2 = action.getText(Page_CustHome.TextBox_DropOffLine2());
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
    cucumberContextManager.setScenarioContext("BUNGII_TIME", action.getText(Page_Estimate.Time()));
    cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", action.getText(Page_Estimate.Text_TripDistance()));
    cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", action.getText(Page_Estimate.Text_TotalEstimate()));
    cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", action.getText(Page_Estimate.Link_LoadingUnloadingTime()));
    //Sprint 29 change
    cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1",action.getText(Page_Estimate.Text_PickupLocation_LineOne()));
    cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2",action.getText(Page_Estimate.Text_PickupLocation_LineTwo()));
    cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1",action.getText(Page_Estimate.Text_DropOffLocation_LineOne()));
    cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2",action.getText(Page_Estimate.Text_DropOffLocation_LineTwo()));

}

    @And("^I get Bungii location details on Bungii Estimate$")
    public void i_get_bungii_locations_details_on_bungii_estimate() throws Throwable {
        // SAVE required values in scenario context
        //Sprint 29 change
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1",action.getText(Page_Estimate.Text_PickupLocation_LineOne()));
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2",action.getText(Page_Estimate.Text_PickupLocation_LineTwo()));
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1",action.getText(Page_Estimate.Text_DropOffLocation_LineOne()));
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2",action.getText(Page_Estimate.Text_DropOffLocation_LineTwo()));
    }

    @And("^I add loading/unloading time of \"([^\"]*)\"$")
    public void iAddLoadingUnloadingTimeOf(String arg0) throws Throwable {
        try {
            if(!action.isElementPresent(Page_Estimate.Header_Estimate(true)))
                Thread.sleep(5000);

            action.click(Page_Estimate.Link_LoadingUnloadingTime());
            if(!action.isElementPresent(Page_Estimate.LoadingUnloadingTime_15(true)))
                action.click(Page_Estimate.Link_LoadingUnloadingTime());

            switch (arg0) {
                case "15 mins":
                    action.click(Page_Estimate.LoadingUnloadingTime_15());
                    break;

                case "30 mins":
                    action.click(Page_Estimate.LoadingUnloadingTime_30());
                    break;

                case "45 mins":
                    action.click(Page_Estimate.LoadingUnloadingTime_45());
                    break;

                case "60 mins":
                    action.click(Page_Estimate.LoadingUnloadingTime_60());
                    break;

                case "75 mins":
                    action.click(Page_Estimate.LoadingUnloadingTime_75());
                    break;

                case "90+ mins":
                    action.click(Page_Estimate.LoadingUnloadingTime_90());
                    break;

                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            //save load time in cucumber context
            cucumberContextManager.setScenarioContext("BUNGII_LOAD_TIME", arg0.replace(" mins",""));
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
            int i = 0;
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            //    action.scrollToBottom();
            do {
                if (!action.isElementPresent(Page_Estimate.Link_AddPhoto()))
                    action.swipeLeft(Page_Estimate.Row_Images());

                if(!action.isElementPresent(Page_Estimate.Link_AddPhoto(true)))
                    action.scrollToBottom();

                action.click(Page_Estimate.Link_AddPhoto());

                if (action.isElementPresent(Page_Estimate.Message_CameraPermissions(true)))
                    action.click(Page_Estimate.Permissions_CameraAllow());

;

                action.click(Page_Estimate.Option_Camera());
                String manufacturer = driver.getCapabilities().getCapability("deviceType").toString();
                if (manufacturer.equalsIgnoreCase("MOTOROLA")) {
                    Thread.sleep(5000);
                    // driver.tap(1, 100, 500, 1);
                    new TouchAction(driver)
                            .tap(point(100, 500))
                            .waitAction(waitOptions(Duration.ofMillis(250))).perform();
                    Thread.sleep(5000);
                    action.click(Page_Estimate.Button_Review());
                }
                if (manufacturer.equalsIgnoreCase("") || manufacturer.equalsIgnoreCase("SAMSUNG")) {
                    action.click(Page_Estimate.Button_Camera_ClickAlternate());
                    //DriverAction.keyBoardEvent(AndroidKeyCode.Keycode_CAMERA);
                    Thread.sleep(2000);
                    action.click(Page_Estimate.Button_Camera_OK());
                }
                Thread.sleep(2000);
                i++;
            } while (i < Integer.parseInt(arg0));

/*            if (action.isElementPresent(Page_Estimate.Text_PickupLocation())) {
                //code to be added incase of "Invalid Image error"
            }*/


            testStepVerify.isElementDisplayed(Page_Estimate.Button_SelectedImage(), "I add " + arg0 + " photos to the Bungii", "I selected photos on estimate page", "Selected image was not displayed on Estimate page");
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
                case"OLD BUNGII TIME":
                    utility.selectBungiiTime();
                    break;
                case "next possible scheduled for duo":
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");break;
            }
            String bungiiTime = action.getText(bungiiEstimatePage.Time());
            if(arg0.equalsIgnoreCase("OLD BUNGII TIME")){
                testStepVerify.isEquals(bungiiTime,(String) cucumberContextManager.getScenarioContext("BUNGII_TIME"),"I selected bungii time as old bungii time:"+bungiiTime,"I was not able to select bungii with old bungii time , Bungii time"+bungiiTime+" expected time"+(String) cucumberContextManager.getScenarioContext("BUNGII_TIME"));
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
            action.waitUntilIsElementExistsAndDisplayed(Page_Estimate.Alert_ConfirmRequestMessage(), 120L);
            actualMessage=action.getText(Page_Estimate.Alert_ConfirmRequestMessage());
            testStepVerify.isEquals(actualMessage, expectedMessage);

            action.click(Page_Estimate.Button_RequestConfirm());
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }    }

    @Then("^check if I have ability to select different load time and Estimate cost is re calculated$")
    public void check_if_i_have_ability_to_select_different_load_time_and_estimate_cost_is_re_calculated() {
        try {
            String oldEstimateValue =  action.getText(Page_Estimate.Text_TotalEstimate());
            action.click(Page_Estimate.Link_LoadingUnloadingTime());
            for (int i = 0; i < loadTimeValue.length; i++) {

                if(i!=0)
                    action.click(Page_Estimate.Link_LoadingUnloadingTime());
                List<WebElement>loadTime =Page_Estimate.LoadingUnloadingTime();
                String optionText=action.getText(loadTime.get(i));
                boolean flag = optionText.equals(loadTimeValue[i]);

                //Click on options
                action.click(loadTime.get(i));

                testStepVerify.isTrue(flag,
                        "I should able to to select " + loadTimeValue[i] + " as load time",
                        "I was able to to select " + loadTimeValue[i] + " as load time",
                        "I was not able to to select " + loadTimeValue[i] + " as load time");

                String newEstimateValue = action.getText(Page_Estimate.Text_TotalEstimate());
                Thread.sleep(2000);
                if (i == 0)
                    testStepVerify.isTrue(newEstimateValue.equals(oldEstimateValue),
                            "total Estimated cost is calculated considering  Loading/unloading time",
                            "Total Estimate cost for first scroll value should be same as default one, Previous cost is " + oldEstimateValue + " , new cost is " + newEstimateValue,
                            "Total Estimate cost was recalculated");
                else
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

}
