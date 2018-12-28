package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.bungii.*;
import com.bungii.android.pages.menus.SaveMoneyPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;

import static com.bungii.common.manager.ResultManager.log;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

public class EstimateBungiiSteps extends DriverBase {
    EstimatePage bungiiEstimatePage = new EstimatePage() ;
    SearchingPage Page_DriverSearch = new SearchingPage();
    CustomerHomePage Page_CustHome = new CustomerHomePage();
    EstimatePage Page_Estimate= new EstimatePage();
    BungiiCompletePage Page_BungiiComplete= new BungiiCompletePage() ;
    WantDollar5Page Page_WantDollar5= new WantDollar5Page();
    SaveMoneyPage Page_SaveMoney = new SaveMoneyPage();

    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    PropertyUtility properties = new PropertyUtility();

    @When("^I tap on \"([^\"]*)\" on Bungii estimate$")
    public void iTapOnOnBungiiEstimate(String arg0) throws Throwable {
        switch (arg0)
        {
            case "two drivers selector":
                action.click(Page_CustHome.Selector_Duo());
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
                action.click(Page_Estimate.Select_PromoCode());
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
                action.waitUntilIsElementExistsAndDisplayed(Page_Estimate.Alert_ConfirmRequestMessage(),120L);
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
                if(!action.isElementPresent(Page_WantDollar5.Button_NoFreeMoney(true)))
                    action.scrollToBottom();
                action.click(Page_WantDollar5.Button_NoFreeMoney());
                break;

            default: break;
        }
        log( " I tap on "+arg0+" on Bungii estimate",
                 "I  Selected"+ arg0, true);    }

    @Then("^I should see \"([^\"]*)\" on Bungii estimate$")
    public void iShouldSeeOnBungiiEstimate(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I am logged in as \"([^\"]*)\" customer$")
    public void iAmLoggedInAsCustomer(String arg0) throws Throwable {
        switch (arg0)
        {
            case "existing":
                utility.loginToCustomerApp(properties.getProp("customer_generic.phonenumber"), properties.getProp("customer_generic.password"));
                break;
            case "newly registered":
                utility.loginToCustomerApp(properties.getProp("customer_newlyregistered.phonenumber"), properties.getProp("customer_generic.password"));
                break;
            case "already having bungiis":
                utility.loginToCustomerApp(properties.getProp("customer_withbungiis.phonenumber"), properties.getProp("customer_generic.password"));
                break;
            case "having referral code":
                utility.loginToCustomerApp(properties.getProp("customer_havingReferral.phonenumber"), properties.getProp("customer_generic.password"));
                break;
            case "my":
                utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                break;
            case "stage":
                utility.loginToCustomerApp(properties.getProp("customer_generic.phonenumber"), properties.getProp("customer_generic.password"));
                break;
            case "valid":
                utility.loginToCustomerApp(PropertyUtility.getDataProperties("valid.customer.phone"), PropertyUtility.getDataProperties("valid.customer.password"));
                break;
            default: break;
        }
    }

    @When("^I enter \"([^\"]*)\" on Bungii estimate$")
    public void iEnterOnBungiiEstimate(String arg0) throws Throwable {
        switch (arg0)
        {
            case "valid pickup and dropoff locations":
                utility.selectAddress(Page_CustHome.Textfield_PickupLocation(), properties.getProp("pickup.LocationB"));
                Thread.sleep(2000);
                utility.selectAddress(Page_CustHome.Textfield_DropoffLocation(), properties.getProp("dropoff.locationB"));
                break;
            case "Atlanta pickup and dropoff locations":
                utility.selectAddress(Page_CustHome.Textfield_PickupLocation(), properties.getProp("pickup.locationC"));
                Thread.sleep(2000);
                utility.selectAddress(Page_CustHome.Textfield_DropoffLocation(), properties.getProp("dropoff.locationC"));
                break;
            case "current location in pickup and dropoff fields":
                //string a = driver.PageSource;
                action.click(Page_CustHome.Button_Locator());
                Thread.sleep(5500);
                if(action.isElementPresent(Page_CustHome.Text_ETAvalue(true)))
                {
                    int ETA = Integer.parseInt(Page_CustHome.Text_ETAvalue().getText().replace(" MINS", ""));
                    if (ETA <= 30)
                        action.click(Page_CustHome.Button_ETASet());
                }
                action.click(Page_CustHome.Button_Locator());
                action.click(Page_CustHome.Button_ETASet());
               break;
            default: break;
        }
        String pickUpLocation=Page_CustHome.Textfield_PickupLocation().getText(),dropUpLocation=Page_CustHome.Textfield_DropoffLocation().getText();
        testStepAssert.isFalse(pickUpLocation.equals(""),"I should able to select pickup location","Pickup location was selected , Pickup value is "+pickUpLocation,"I was not able select pickup location");
        testStepAssert.isFalse(dropUpLocation.equals(""),"I should able to select pickup location","Pickup location was selected , Pickup value is "+dropUpLocation,"I was not able select pickup location");

    }

    @And("^I add \"([^\"]*)\" PromoCode$")
    public void iAddPromoCode(String arg0) throws Throwable {
        switch (arg0)
        {
            case "valid":
                action.sendKeys(Page_SaveMoney.Textfield_PromoCode(),  properties.getProp("promocode.valid"));
                break;
            case "fixed valid":
                action.sendKeys(Page_SaveMoney.Textfield_PromoCode(),  properties.getProp("promocode.fixedvalid"));
                break;
            case "invalid":
                action.sendKeys(Page_SaveMoney.Textfield_PromoCode(), properties.getProp("promocode.invalid"));
                break;
            case "expired":
                action.sendKeys(Page_SaveMoney.Textfield_PromoCode(), properties.getProp("promocode.expired"));
                break;
            case "referral":
                action.sendKeys(Page_SaveMoney.Textfield_PromoCode(), properties.getProp("referral.code"));
                break;
            case "first time":
                action.sendKeys(Page_SaveMoney.Textfield_PromoCode(),  properties.getProp("promocode.firsttime"));
                break;
            case "used one off":
                action.sendKeys(Page_SaveMoney.Textfield_PromoCode(),  properties.getProp("promocode.useedoneoff"));
                break;
            default: break;
        }
    }

    @And("^I tap \"([^\"]*)\" on Save Money page$")
    public void iTapOnSaveMoneyPage(String arg0) throws Throwable {
        switch (arg0) {
            case "Add":
                action.click(Page_SaveMoney.Button_Add());
                break;
            case "Get More Money":
                action.click(Page_SaveMoney.Button_GetMoreMoney());
                break;
            default:
                break;
        }
    }

    @And("^I add loading/unloading time of \"([^\"]*)\"$")
    public void iAddLoadingUnloadingTimeOf(String arg0) throws Throwable {
        action.click(Page_Estimate.Link_LoadingUnloadingTime());
        switch (arg0)
        {
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

            default: break;
        }
        log( " I add loading/unloading time "+ arg0+ "on Estimate page",
                "I clicked on "+arg0 +"as Estimate loading/unloading time ", true);    }
    @When("^I add \"([^\"]*)\" photos to the Bungii$")
    public void iAddPhotosToTheBungii(String arg0) throws Throwable {
      int i= 0;
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        do
        {
            if (!action.isElementPresent(Page_Estimate.Link_AddPhoto()))
                action.swipeLeft(Page_Estimate.Row_Images());

            action.click(Page_Estimate.Link_AddPhoto());

            if (action.isElementPresent(Page_Estimate.Message_CameraPermissions(true)))
                action.click(Page_Estimate.Permissions_CameraAllow());

            action.click(Page_Estimate.Option_Camera());
            String manufacturer  = driver.getCapabilities().getCapability("deviceType").toString();
            if (manufacturer.equalsIgnoreCase("MOTOROLA"))
            {
                Thread.sleep(3000);
               // driver.tap(1, 100, 500, 1);
                new TouchAction(driver)
                        .tap(point(100,500))
                        .waitAction(waitOptions(Duration.ofMillis(250))).perform();
                Thread.sleep(3000);
                action.click(Page_Estimate.Button_Review());
            }
           if (manufacturer.equalsIgnoreCase("") || manufacturer.equalsIgnoreCase("SAMSUNG"))
            {
                action.click(Page_Estimate.Button_Camera_ClickAlternate());
                //DriverAction.keyBoardEvent(AndroidKeyCode.Keycode_CAMERA);
                Thread.sleep(2000);
                action.click(Page_Estimate.Button_Camera_OK());
            }
            Thread.sleep(2000);
            i++;
        } while (i < Integer.parseInt(arg0));

        if(action.isElementPresent(Page_Estimate.Text_PickupLocation()))
        {
            //code to be added incase of "Invalid Image error"
        }


        testStepVerify.isElementDisplayed(Page_Estimate.Button_SelectedImage(),"I add "+arg0+" photos to the Bungii" ,"I selected photos on estimate page","Selected image was not displayed on Estimate page");
    }


    @And("I select Bungii Time as {string}")
    public void iSelectBungiiTimeAs(String arg0) {
        utility.selectBungiiTime();
        String bungiiTime=action.getText(bungiiEstimatePage.Time());
        cucumberContextManager.setScenarioContext("BUNGII_TIME",bungiiTime);

    }
}
