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
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.time.Duration;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
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

    @When("^I tap on \"([^\"]*)\" on Bungii estimate$")
    public void iTapOnOnBungiiEstimate(String arg0) throws Throwable {
        try {
            switch (arg0) {
                case "two drivers selector":
                    action.click(Page_CustHome.Selector_Duo());
                    cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER", "DUO");
                    break;
                case "Get Estimate button":
                    while (action.isElementPresent(Page_CustHome.Button_GetEstimate()) == false)
                        iEnterOnBungiiEstimate("current location in pickup and dropoff fields");
                    //if (DriverAction.isElementPresent(Page_CustHome.Button_GetEstimate))
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
                    action.click((WebElement) SetupManager.getDriver().findElement(By.xpath("//android.widget.TextView[contains(@text,'" + promoCode + "')]")));
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
                    action.waitUntilIsElementExistsAndDisplayed(Page_BungiiComplete.Button_OK());
                    action.click(Page_BungiiComplete.Button_OK());
                    break;

                case "No free money":
                    if (!action.isElementPresent(Page_WantDollar5.Button_NoFreeMoney(true)))
                        action.scrollToBottom();
                    action.click(Page_WantDollar5.Button_NoFreeMoney());
                    break;

                default:
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
                    testStepAssert.isElementDisplayed(Page_Estimate.Header_Estimate(), "Estimate header should be displayed ", "Estimate header is displayed", "Estimate header is not displayed");


                    testStepVerify.isElementTextEquals(Page_Estimate.Text_PickupLocation(), PropertyUtility.getDataProperties("pickup.locationB"));
                    testStepVerify.isElementTextEquals(Page_Estimate.Text_DropOffLocation(), PropertyUtility.getDataProperties("dropoff.locationB"));

                    double expectedTotalEstimate = utility.bungiiEstimate(action.getText(Page_Estimate.Text_TripDistance()), action.getText(Page_Estimate.Link_LoadingUnloadingTime()), utility.getEstimateTime(), action.getText(Page_Estimate.Link_Promo()));
                    String loadTime = action.getText(Page_Estimate.Text_TotalEstimate());
                    String truncValue = new DecimalFormat("#.#").format(expectedTotalEstimate);
                    if(!truncValue.contains("."))truncValue=truncValue+".0";
                    String actualValue = loadTime.substring(0, loadTime.length() - 1);
                    testStepVerify.isEquals("$" + String.valueOf(truncValue), actualValue);
                    break;

                case "driver cancelled":
                    testStepAssert.isElementDisplayed(Page_CustHome.Title_HomePage(), "Home page title should be displayed", "Home page title is displayed", "Home page title is not displayed");
                    testStepAssert.isElementDisplayed(Page_CustHome.Button_GetEstimate(), "Get estimate button should be displayed", "Get estimate button is displayed", "Get estimate button is not displayed");
                    break;
                case "customer cancelled":
            //        utility.verifyIsPageIsCorrectlyDisplayed("Estimate");
                    action.scrollToTop();
                    String pickUpLocation=action.getText(Page_Estimate.Text_PickupLocation()),dropLocation=action.getText(Page_Estimate.Text_DropOffLocation());
                    testStepVerify.isFalse(pickUpLocation.equals(""),"Pickup Location should not be empty ","Pickup location is"+pickUpLocation,"Pickup location is Empty");
                    testStepVerify.isFalse(dropLocation.equals(""),"Drop Location should not be empty ","Drop location is"+dropLocation,"Drop location is Empty");
                    break;
                case "Bungii posted Success page":
                    testStepAssert.isElementDisplayed(Page_CustHome.Image_Tick(), "Bungii Posted image should be displayed ", "Bungii posted image is displayed ", "Bungii posted image is not displayed");
                    break;

                default:
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
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("valid.customer.phone"), PropertyUtility.getDataProperties("valid.customer.password"));
                    break;
                default:
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
            switch (arg0) {
                case "valid pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.Textfield_PickupLocation(), PropertyUtility.getDataProperties("pickup.locationB"));
                    Thread.sleep(4000);
                    utility.selectAddress(Page_CustHome.Textfield_DropoffLocation(), PropertyUtility.getDataProperties("dropoff.locationB"));
                    break;
                case "Atlanta pickup and dropoff locations":
                    utility.selectAddress(Page_CustHome.Textfield_PickupLocation(), PropertyUtility.getDataProperties("pickup.locationC"));
                    Thread.sleep(2000);
                    utility.selectAddress(Page_CustHome.Textfield_DropoffLocation(), PropertyUtility.getDataProperties("dropoff.locationC"));
                    break;
                case "current location in pickup and dropoff fields":
                    //string a = driver.PageSource;
                    action.click(Page_CustHome.Button_Locator());
                    Thread.sleep(5500);
                    if (action.isElementPresent(Page_CustHome.Text_ETAvalue(true))) {
                        int ETA = Integer.parseInt(Page_CustHome.Text_ETAvalue().getText().replace(" MINS", ""));
                        if (ETA <= 30)
                            action.click(Page_CustHome.Button_ETASet());
                    }
                    action.click(Page_CustHome.Button_Locator());
                    action.click(Page_CustHome.Button_ETASet());
                    break;
                default:
                    break;
            }
            //savePickupAddress();
            //saveDropupAddress();
            String pickUpLocation = action.getText(Page_CustHome.Textfield_PickupLocation()), dropUpLocation = action.getText(Page_CustHome.Textfield_DropoffLocation());
            if(dropUpLocation.equalsIgnoreCase("Set Drop Off Location"))
            {
                Thread.sleep(5000);
                dropUpLocation = action.getText(Page_CustHome.Textfield_DropoffLocation());
            }
            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION", pickUpLocation);
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION", dropUpLocation);

            testStepAssert.isFalse(pickUpLocation.equals(""), "I should able to select pickup location", "Pickup location was selected , Pickup value is " + pickUpLocation, "I was not able select pickup location");
            testStepAssert.isFalse(dropUpLocation.equals(""), "I should able to select pickup location", "Pickup location was selected , Pickup value is " + dropUpLocation, "I was not able select pickup location");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    private String savePickupAddress() {
        action.click(Page_CustHome.Textfield_PickupLocation());
        String pickUpLocation = Page_CustHome.Textfield_PickupLocation().getText();
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION", pickUpLocation);

        return pickUpLocation;
    }

    private String saveDropupAddress() {
        action.click(Page_CustHome.Textfield_DropoffLocation());
        String dropUpLocation = Page_CustHome.Textfield_DropoffLocation().getText();
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION", dropUpLocation);
        return dropUpLocation;
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

    @And("^I add loading/unloading time of \"([^\"]*)\"$")
    public void iAddLoadingUnloadingTimeOf(String arg0) throws Throwable {
        try {
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
                    break;
            }
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

                action.click(Page_Estimate.Link_AddPhoto());

                if (action.isElementPresent(Page_Estimate.Message_CameraPermissions(true)))
                    action.click(Page_Estimate.Permissions_CameraAllow());

                if(!action.isElementPresent(Page_Estimate.Option_Camera(true)))
                    action.scrollToBottom();

                action.click(Page_Estimate.Option_Camera());
                String manufacturer = driver.getCapabilities().getCapability("deviceType").toString();
                if (manufacturer.equalsIgnoreCase("MOTOROLA")) {
                    Thread.sleep(3000);
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

            if (action.isElementPresent(Page_Estimate.Text_PickupLocation())) {
                //code to be added incase of "Invalid Image error"
            }


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
                    utility.selectBungiiTime();
                    break;
                case "next possible scheduled for duo":
                    break;
            }
            String bungiiTime = action.getText(bungiiEstimatePage.Time());
            cucumberContextManager.setScenarioContext("BUNGII_TIME", bungiiTime);
            log(" I select Bungii Time as " + arg0,
                    " I select Bungii Time as " + bungiiTime + ", Selected Bungii time is " + bungiiTime, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
