package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.enums.Status;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.BungiiAcceptedPage;
import com.bungii.android.pages.customer.BungiiProgressPage;
import com.bungii.android.pages.customer.SearchingPage;
import com.bungii.android.pages.driver.*;
import com.bungii.android.pages.otherApps.OtherAppsPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class BungiiSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(BungiiSteps.class);
    SearchingPage Page_BungiiSearch = new SearchingPage();
    BungiiAcceptedPage Page_BungiiAccepted = new BungiiAcceptedPage();
    BungiiProgressPage Page_CustomerBungiiProgress = new BungiiProgressPage();
    InProgressBungiiPages Page_DriverBungiiProgress = new InProgressBungiiPages();
    OtherAppsPage Page_OtherApps = new OtherAppsPage();
    HomePage Page_DriverHome = new HomePage();
    com.bungii.android.pages.customer.HomePage customerHomePage = new com.bungii.android.pages.customer.HomePage();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    BungiiCompletedPage Page_BungiiComplete = new BungiiCompletedPage();
    ScheduledBungiiPage scheduledBungiiPage = new ScheduledBungiiPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();


    @Then("^for a Bungii I should see \"([^\"]*)\"$")
    public void forABungiiIShouldSee(String arg0) throws Throwable {
        try {

            switch (arg0) {
                case "Bungii Home page with locations":
                    testStepVerify.isTrue(utility.isCorrectPage("Home"), "I should be navigated to Home Page", "I was navigated to Home Page", "I was not navigate to Home page");
                    String pickUpLocation = action.getText(customerHomePage.Textfield_PickupLocation()), dropUpLocation = action.getText(customerHomePage.Textfield_DropoffLocation());
                    testStepVerify.isEquals(pickUpLocation, (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
                    testStepVerify.isEquals(dropUpLocation, (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"));

                    break;

                case "Bungii search screen":
                    testStepVerify.isElementDisplayed(Page_BungiiSearch.Loader(), "I should able  to see Loader on Bungii search screen ", "I was able to see Loader on Bungii search screen", "Loader was not displayed on Bungii Search screen");
                    testStepVerify.isElementDisplayed(Page_BungiiSearch.Text_MsgSearching(), "I should able  see Searching message on Bungii search screen", "I was able to see Searching message Loader on Bungii search screen", "Searching message was not displayed on Bungii Search screen");
                    testStepVerify.isElementDisplayed(Page_BungiiSearch.ProgressBar(), "I should able  to see Progress bar on Bungii search screen", "I was able to see Progress Bar on Bungii search screen", "Progress bar was not displayed on Bungii Search screen");
                    Thread.sleep(1000);
                    break;
                case "Enroute screen":
                    testStepVerify.isEquals(Page_CustomerBungiiProgress.PageTitle().getText(), Status.EN_ROUTE.toString(), "I should be navigate to En route Screen", "I am navigate to En route Screen", "I was not navigate to En route Screen");
                    testStepVerify.isElementSelected(Page_CustomerBungiiProgress.BungiiStatus_Enroute(), " En route icon should be high lighted ", "En route icon is high lighted", "En route icon is not high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_Arrived(), " Arrived icon should not be high lighted ", "Arrived icon is not high lighted", "Arrived icon is  high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_LoadingItem(), " Loading icon should not be high lighted ", " Loading icon is not high lighted", "Loading icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_DrivingToDropOff(), " Driving to Drop Off icon should not be high lighted ", " Driving to Drop Off icon is not high lighted", " Driving to Drop Off icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_UnloadingItem(), " Unloading icon should not be high lighted ", "Unloading icon is not high lighted", "Unloading icon is  high lighted");
                    break;
                case "Arrived screen":
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_Enroute(), " En route icon should not be high lighted ", "En route icon is not high lighted", "En route icon is high lighted");
                    testStepVerify.isElementSelected(Page_CustomerBungiiProgress.BungiiStatus_Arrived(), " Arrived icon should be high lighted ", "Arrived icon is high lighted", "Arrived icon is not  high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_LoadingItem(), " Loading icon should not be high lighted ", " Loading icon is not high lighted", "Loading icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_DrivingToDropOff(), " Driving to Drop Off icon should not be high lighted ", " Driving to Drop Off icon is not high lighted", " Driving to Drop Off icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_UnloadingItem(), " Unloading icon should not be high lighted ", "Unloading icon is not high lighted", "Unloading icon is  high lighted");
                    testStepVerify.isEquals(Page_CustomerBungiiProgress.PageTitle().getText(), Status.ARRIVED.toString(), "I should be navigate to ARRIVED Screen", "I am navigate to ARRIVED Screen", "I was not navigate to ARRIVED Screen");
                    break;

                case "Loading Item screen":
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_Enroute(), " En route icon should not be high lighted ", "En route icon is not high lighted", "En route icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_Arrived(), " Arrived icon should not be high lighted ", "Arrived icon is not high lighted", "Arrived icon is high lighted");
                    testStepVerify.isElementSelected(Page_CustomerBungiiProgress.BungiiStatus_LoadingItem(), " Loading icon should be high lighted ", " Loading icon is high lighted", "Loading icon is not high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_DrivingToDropOff(), " Driving to Drop Off icon should not be high lighted ", " Driving to Drop Off icon is not high lighted", " Driving to Drop Off icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_UnloadingItem(), " Unloading icon should not be high lighted ", "Unloading icon is not high lighted", "Unloading icon is  high lighted");
                    testStepVerify.isEquals(Page_CustomerBungiiProgress.PageTitle().getText(), Status.LOADING_ITEM.toString(), "I should be navigate to LOADING ITEM Screen", "I am navigate to LOADING ITEM Screen", "I was not navigate to LOADING ITEM Screen");
                    break;

                case "Driving to DropOff screen":
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_Enroute(), " En route icon should not be high lighted ", "En route icon is not high lighted", "En route icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_Arrived(), " Arrived icon should not be high lighted ", "Arrived icon is not high lighted", "Arrived icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_LoadingItem(), " Loading icon should not be high lighted ", " Loading icon is not high lighted", "Loading icon is high lighted");
                    testStepVerify.isElementSelected(Page_CustomerBungiiProgress.BungiiStatus_DrivingToDropOff(), " Driving to Drop Off icon should be high lighted ", " Driving to Drop Off icon is high lighted", " Driving to Drop Off icon is not high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_UnloadingItem(), " Unloading icon should not be high lighted ", "Unloading icon is not high lighted", "Unloading icon is  high lighted");
                    testStepVerify.isEquals(Page_CustomerBungiiProgress.PageTitle().getText(), Status.DRIVING_TO_DROP_OFF.toString(), "I should be navigate to DRIVING TO DROP OFF Screen", "I am navigate to DRIVING TO DROP OFF Screen", "I was not navigate to LOADING ITEM Screen");
                    break;
                case "Unloading Item screen":
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_Enroute(), " En route icon should not be high lighted ", "En route icon is not high lighted", "En route icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_Arrived(), " Arrived icon should not be high lighted ", "Arrived icon is not high lighted", "Arrived icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_LoadingItem(), " Loading icon should not be high lighted ", " Loading icon is not high lighted", "Loading icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_CustomerBungiiProgress.BungiiStatus_DrivingToDropOff(), " Driving to Drop Off icon should not be high lighted ", " Driving to Drop Off icon is not high lighted", " Driving to Drop Off icon is high lighted");
                    testStepVerify.isElementSelected(Page_CustomerBungiiProgress.BungiiStatus_UnloadingItem(), " Unloading icon should be high lighted ", "Unloading icon is high lighted", "Unloading icon is not high lighted");
                    testStepVerify.isEquals(Page_CustomerBungiiProgress.PageTitle().getText(), Status.UNLOADING_ITEM.toString(), "I should be navigate to UNLOADING_ TEM Screen", "I am navigate to UNLOADING ITEM Screen", "I was not navigate to LOADING ITEM Screen");
                    break;
               /*
            case "Bungii accepted":
                assertManager.ElementDisplayed(Page_BungiiAccepted.PageTitle_BungiiAccepted());
                assertManager.ElementTextEqual(Page_BungiiAccepted.Label_BungiiAccepted(), Data_Valid_Customer.BungiiAccepted);
                assertManager.ElementTextEqual(Page_BungiiAccepted.Label_DriverEnRoute(), Data_Valid_Customer.DriverEnRoute);
                assertManager.ElementDisplayed(Page_BungiiAccepted.Image_Driver());
                assertManager.ElementDisplayed(Page_BungiiAccepted.DriverRatingBar());
                assertManager.ElementTextEqual(Page_BungiiAccepted.Label_DriverName(), Data_Valid_Customer.DriverName);
                break;

            case "Pickup location details":
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_LocationTitle(), Data_Valid_Customer.LocationTitlePickup);
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location(), Data_Customer.PickupLocation_AT);
                break;

            case "Dropoff location details":
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_LocationTitle(), Data_Valid_Customer.LocationTitleDropOff);
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location(), Data_Customer.DropoffLocation_AT);
                break;

            case "driver Details":
                assertManager.elementDisplayed(Page_BungiiProgress.Bungii_Driver_Image());
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title(), Data_Valid_Customer.DriverTitle);
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Name(), Data_Valid_Customer.DriverName);
                assertManager.ElementDisplayed(Page_BungiiProgress.Bungii_Driver_RatingBar());
                assertManager.ElementEnabled(Page_BungiiProgress.Button_Bungii_Driver_SMS());
                assertManager.ElementEnabled(Page_BungiiProgress.Button_Bungii_Driver_Call());
                 break;
*/

                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I tap on \"([^\"]*)\" on Driver Home page$")
    public void iTapOnOnDriverHomePage(String arg0) throws Throwable {
        try {

            switch (arg0) {
                case "Online/Offline button":
                    action.click(Page_DriverHome.Button_OnlineOffline());
                    break;

                case "Available Trips link":
                    if (action.isNotificationAlertDisplayed()) {
                        action.click(Page_BungiiRequest.AlertButton_Cancel(true));
                        Thread.sleep(1000);
                    }
                    if (!action.isElementPresent(Page_DriverHome.Link_AvailableTrips(true))) {
                        utility.launchDriverApplication();
                        Thread.sleep(3000);
                    }

                    action.click(Page_DriverHome.Link_AvailableTrips());
                    break;
                case "Go Online button":
                    if (Page_DriverHome.Button_OnlineOffline().getText().equalsIgnoreCase("GO OFFLINE"))
                        log("I tap on Go Online button on driver Home page",
                                " driver is already Online", true);
                    else {
                        action.click(Page_DriverHome.Button_OnlineOffline());
                        log("I tap on Go Online button on driver Home page",
                                " Selected Go Online Button", true);
                    }
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

    @And("^Bungii Driver \"([^\"]*)\" request$")
    public void bungiiDriverRequest(String arg0) {
        try {
            if (arg0.equalsIgnoreCase("accepts On Demand Bungii")||arg0.equalsIgnoreCase("rejects On Demand Bungii")) {
                boolean isDisplayed = action.waitUntilAlertDisplayed(60L);
                if (!isDisplayed)
                    i_click_on_notification_for_something("on demand trip");
                isDisplayed = action.waitUntilAlertDisplayed(180L);

                if (action.isElementPresent(Page_BungiiRequest.Alert_Msg())) {
                    action.click(Page_BungiiRequest.AlertButton_View());
                    switch (arg0) {
                        case "accepts On Demand Bungii":
                            action.click(Page_BungiiRequest.Button_Accept());
                            break;

                        case "rejects On Demand Bungii":
                            action.click(Page_BungiiRequest.Button_Reject());
                            break;
                    }
                }
            } else if (arg0.equalsIgnoreCase("Start Schedule Bungii")) {
                boolean skipClick = false;

                if (action.isNotificationAlertDisplayed()) {
                    if (action.getText(Page_BungiiRequest.Alert_Msg()).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))) {
                        utility.acceptNotificationAlert();
                        skipClick = true;
                    } else {
                        action.click(Page_BungiiRequest.Button_Reject());
                    }

                }
                Thread.sleep(5000);
                action.scrollToBottom();
                action.click(scheduledBungiiPage.Button_Start());
            }
            log("Bungii driver should able to" + arg0 + " request", "Bungii driver  " + arg0,true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^I click on notification for \"([^\"]*)\"$")
    public void i_click_on_notification_for_something(String strArg1) {
        try {
            action.showNotifications();
            String expecteMessage = utility.getExpectedNotification(strArg1.toUpperCase());
            boolean isFound = utility.clickOnNofitication("Bungii", expecteMessage);
            if (!isFound) {
                Thread.sleep(50000);
                isFound = utility.clickOnNofitication("Bungii", expecteMessage);
            }
            //if no notificatiaon then hide
            if (!isFound)
                action.hideNotifications();


            testStepVerify.isTrue(isFound, "I should able to on notification for " + strArg1, "I clicked on notification for " + strArg1 + " with message" + expecteMessage, "I was not able to find notification with " + expecteMessage + " message");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I tap \"([^\"]*)\" during a Bungii$")
    public void iTapDuringABungii(String arg0) throws Throwable {
        try {

            switch (arg0) {
                case "OK on Driver Accepted screen":
                    action.waitUntilIsElementExistsAndDisplayed(Page_BungiiAccepted.Button_OK());
                    action.click(Page_BungiiAccepted.Button_OK());
                    break;

                case "SMS for a solo driver":
                    action.click(Page_CustomerBungiiProgress.Button_Bungii_Driver_SMS());
                    break;
                case "SMS for driver 1":
                    action.click(Page_CustomerBungiiProgress.Button_DuoMore1());
                    action.click(Page_CustomerBungiiProgress.Button_DuoDriver_SMS());
                    break;
                case "SMS for driver 2":
                    action.click(Page_CustomerBungiiProgress.Button_DuoMore2());
                    action.click(Page_CustomerBungiiProgress.Button_DuoDriver_SMS());
                    break;
                case "Call for a solo driver":
                    action.click(Page_CustomerBungiiProgress.Button_Bungii_Driver_Call());
                    break;
                case "Call for driver 1":
                    action.click(Page_CustomerBungiiProgress.Button_DuoMore1());
                    action.click(Page_CustomerBungiiProgress.Button_DuoDriver_Call());
                    break;
                case "Call for driver 2":
                    action.click(Page_CustomerBungiiProgress.Button_DuoMore2());
                    action.click(Page_CustomerBungiiProgress.Button_DuoDriver_Call());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log("I should able to click on" + arg0, "I clicked on " + arg0, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Bungii driver should see \"([^\"]*)\"$")
    public void bungiiDriverShouldSee(String arg0) throws Throwable {
        try {
            switch (arg0) {
                case "Home screen":
                    testStepVerify.isElementTextEquals(Page_DriverHome.Title_Status(), PropertyUtility.getMessage("driver.home.title.online"), "Driver status should be Online", "Driver status is Online", "Driver status is offline");
                    testStepVerify.isElementTextEquals(Page_DriverHome.Button_OnlineOffline(), PropertyUtility.getMessage("driver.home.gooffline"), "Go offline button on driver should be enabled", "Go Offline button on driver home page is Enabled", "Go Offline button on driver home page is disabled");
                    break;
                case "Enroute screen":
                    testStepVerify.isElementSelected(Page_DriverBungiiProgress.BungiiStatus_Enroute(), " En route icon should be high lighted ", "En route icon is high lighted", "En route icon is not high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_Arrived(), " Arrived icon should not be high lighted ", "Arrived icon is not high lighted", "Arrived icon is  high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_LoadingItem(), " Loading icon should not be high lighted ", " Loading icon is not high lighted", "Loading icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_DrivingToDropOff(), " Driving to Drop Off icon should not be high lighted ", " Driving to Drop Off icon is not high lighted", " Driving to Drop Off icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_UnloadingItem(), " Unloading icon should not be high lighted ", "Unloading icon is not high lighted", "Unloading icon is  high lighted");
                    testStepVerify.isEquals(Page_DriverBungiiProgress.Title_Status().getText(), Status.EN_ROUTE.toString(), "I should be navigate to En route Screen", "I am navigate to En route Screen", "I was not navigate to En route Screen");
                    break;

                case "Arrived screen":
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_Enroute(), " En route icon should not be high lighted ", "En route icon is not high lighted", "En route icon is high lighted");
                    testStepVerify.isElementSelected(Page_DriverBungiiProgress.BungiiStatus_Arrived(), " Arrived icon should be high lighted ", "Arrived icon is high lighted", "Arrived icon is not  high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_LoadingItem(), " Loading icon should not be high lighted ", " Loading icon is not high lighted", "Loading icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_DrivingToDropOff(), " Driving to Drop Off icon should not be high lighted ", " Driving to Drop Off icon is not high lighted", " Driving to Drop Off icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_UnloadingItem(), " Unloading icon should not be high lighted ", "Unloading icon is not high lighted", "Unloading icon is  high lighted");
                    testStepVerify.isEquals(Page_DriverBungiiProgress.Title_Status().getText(), Status.ARRIVED.toString(), "I should be navigate to ARRIVED Screen", "I am navigate to ARRIVED Screen", "I was not navigate to ARRIVED Screen");
                    break;

                case "Loading Item screen":
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_Enroute(), " En route icon should not be high lighted ", "En route icon is not high lighted", "En route icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_Arrived(), " Arrived icon should not be high lighted ", "Arrived icon is not high lighted", "Arrived icon is high lighted");
                    testStepVerify.isElementSelected(Page_DriverBungiiProgress.BungiiStatus_LoadingItem(), " Loading icon should be high lighted ", " Loading icon is high lighted", "Loading icon is not high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_DrivingToDropOff(), " Driving to Drop Off icon should not be high lighted ", " Driving to Drop Off icon is not high lighted", " Driving to Drop Off icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_UnloadingItem(), " Unloading icon should not be high lighted ", "Unloading icon is not high lighted", "Unloading icon is  high lighted");
                    testStepVerify.isEquals(Page_DriverBungiiProgress.Title_Status().getText(), Status.LOADING_ITEM.toString(), "I should be navigate to LOADING ITEM Screen", "I am navigate to LOADING ITEM Screen", "I was not navigate to LOADING ITEM Screen");
                    break;

                case "Driving to DropOff screen":
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_Enroute(), " En route icon should not be high lighted ", "En route icon is not high lighted", "En route icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_Arrived(), " Arrived icon should not be high lighted ", "Arrived icon is not high lighted", "Arrived icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_LoadingItem(), " Loading icon should not be high lighted ", " Loading icon is not high lighted", "Loading icon is high lighted");
                    testStepVerify.isElementSelected(Page_DriverBungiiProgress.BungiiStatus_DrivingToDropOff(), " Driving to Drop Off icon should be high lighted ", " Driving to Drop Off icon is high lighted", " Driving to Drop Off icon is not high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_UnloadingItem(), " Unloading icon should not be high lighted ", "Unloading icon is not high lighted", "Unloading icon is  high lighted");
                    testStepVerify.isEquals(Page_DriverBungiiProgress.Title_Status().getText(), Status.DRIVING_TO_DROP_OFF.toString(), "I should be navigate to DRIVING TO DROP OFF Screen", "I am navigate to DRIVING TO DROP OFF Screen", "I was not navigate to LOADING ITEM Screen");
                    break;

                case "Unloading Item screen":
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_Enroute(), " En route icon should not be high lighted ", "En route icon is not high lighted", "En route icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_Arrived(), " Arrived icon should not be high lighted ", "Arrived icon is not high lighted", "Arrived icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_LoadingItem(), " Loading icon should not be high lighted ", " Loading icon is not high lighted", "Loading icon is high lighted");
                    testStepVerify.isElementNotSelected(Page_DriverBungiiProgress.BungiiStatus_DrivingToDropOff(), " Driving to Drop Off icon should not be high lighted ", " Driving to Drop Off icon is not high lighted", " Driving to Drop Off icon is high lighted");
                    testStepVerify.isElementSelected(Page_DriverBungiiProgress.BungiiStatus_UnloadingItem(), " Unloading icon should be high lighted ", "Unloading icon is high lighted", "Unloading icon is not high lighted");
                    testStepVerify.isEquals(Page_DriverBungiiProgress.Title_Status().getText(), Status.UNLOADING_ITEM.toString(), "I should be navigate to UNLOADING_ TEM Screen", "I am navigate to UNLOADING ITEM Screen", "I was not navigate to LOADING ITEM Screen");
                    break;
                case "Pickup Item":
                    testStepVerify.isElementEnabled(Page_DriverBungiiProgress.Image_BungiiItem(),"Bungii item should be displayed","Bungii item is be displayed","Bungii item is not displayed");
                    testStepVerify.isElementEnabled(Page_DriverBungiiProgress.Button_CancelImage(),"Cancel on Bungii item should be displayed","Cancel on Bungii item is be displayed","Cancel on Bungii item is not displayed");
                    action.click(Page_DriverBungiiProgress.Button_CancelImage());
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

    //VISHAL[2512]:DONE
    @Then("^correct details should be displayed on \"([^\"]*)\" app$")
    public void correctDetailsShouldBeDisplayedOnApp(String arg0) throws Throwable {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            String expectedDuoNumber;
            String DriverAppdeviceType = driver.getCapabilities().getCapability("deviceType").toString();
            switch (arg0) {
                case "Driver 1 SMS":
                case "Driver 2 SMS":
                case "SMS":
                    expectedDuoNumber=arg0.contains("2")?PropertyUtility.getMessage("twilio.number.driver2"):PropertyUtility.getMessage("twilio.number");

                    if (DriverAppdeviceType.equalsIgnoreCase("Samsung"))
                        utility.isPhoneNumbersEqual(Page_OtherApps.SMS_Samsung_RecipientNo(), expectedDuoNumber);

                    if (DriverAppdeviceType.equalsIgnoreCase("MOTOROLA")||!DriverAppdeviceType.equalsIgnoreCase("Samsung")) {
                        utility.isPhoneNumbersEqual(Page_OtherApps.SMS_Moto_RecipientNo(),expectedDuoNumber);
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));

                    }

                    break;

                case "Calling":
                case "Driver 1 Calling":
                case "Driver 2 Calling":
                    expectedDuoNumber=arg0.contains("2")?PropertyUtility.getMessage("twilio.number.driver2"):PropertyUtility.getMessage("twilio.number");

                    if (DriverAppdeviceType.equalsIgnoreCase("Samsung"))
                        utility.isPhoneNumbersEqual(Page_OtherApps.Call_Samsung_Number(), expectedDuoNumber);

                    if (DriverAppdeviceType.equalsIgnoreCase("MOTOROLA")||!DriverAppdeviceType.equalsIgnoreCase("Samsung")) {
                        utility.isPhoneNumbersEqual(Page_OtherApps.Call_Moto_Number(), expectedDuoNumber);
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));

                    }

                    break;

                default:
                    break;
            }

            while (Page_CustomerBungiiProgress.Title_Status(true) == null)
                ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^Bungii Driver taps \"([^\"]*)\" during a Bungii$")
    public void bungiiDriverTapsDuringABungii(String arg0) throws Throwable {
        try {
            boolean isDuo=String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase( "DUO");
            if(!isDuo){
            switch (arg0) {
                case "SMS for a customer":
                    action.click(Page_DriverBungiiProgress.Button_More());
                    testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_Customer_SMS()).trim(),PropertyUtility.getMessage("driver.text.customer"));
                    action.click(Page_DriverBungiiProgress.Button_Customer_SMS());
                    break;

                case "Call for a customer":
                    action.click(Page_DriverBungiiProgress.Button_More());
                    testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_Customer_Call()).trim(),PropertyUtility.getMessage("driver.call.customer"));
                    action.click(Page_DriverBungiiProgress.Button_Customer_Call());
                    break;
                case"Contact support":
                    action.click(Page_DriverBungiiProgress.Button_More());
                    testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_Customer_CallSupport()).trim(),PropertyUtility.getMessage("driver.text.support"));
                    action.click(Page_DriverBungiiProgress.Button_Customer_CallSupport());
                    break;
                case"View items":
                    action.click(Page_DriverBungiiProgress.Button_More());
                    testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_Customer_ViewItem()).trim(),PropertyUtility.getMessage("driver.view.items.customer"));
                    action.click(Page_DriverBungiiProgress.Button_Customer_ViewItem());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }}else{
                    if(!action.isElementPresent(Page_DriverBungiiProgress.Button_DuoMore(true)))
                    {
                        Thread.sleep(5000);
                    }
                    switch (arg0) {
                        case "SMS for a customer":
                            action.click(Page_DriverBungiiProgress.Button_DuoMore1());
                            if(!action.isElementPresent(Page_DriverBungiiProgress.Button_DuoCustomer_SMS(true)))
                                action.click(Page_DriverBungiiProgress.Button_DuoMore1());

                            testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_DuoCustomer_SMS()).trim(),PropertyUtility.getMessage("driver.text.customer"));
                            action.click(Page_DriverBungiiProgress.Button_DuoCustomer_SMS());
                            break;

                        case "Call for a customer":
                            action.click(Page_DriverBungiiProgress.Button_DuoMore1());
                            if(!action.isElementPresent(Page_DriverBungiiProgress.Button_DuoCustomer_Call(true)))
                                action.click(Page_DriverBungiiProgress.Button_DuoMore1());

                            testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_DuoCustomer_Call()).trim(),PropertyUtility.getMessage("driver.call.customer"));
                            action.click(Page_DriverBungiiProgress.Button_DuoCustomer_Call());
                            break;
                        case "SMS for a driver":
                            action.click(Page_DriverBungiiProgress.Button_DuoMore2());
                            if(!action.isElementPresent(Page_DriverBungiiProgress.Button_DuoCustomer_SMS(true)))
                                action.click(Page_DriverBungiiProgress.Button_DuoMore2());
                            testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_DuoCustomer_SMS()).trim(),PropertyUtility.getMessage("driver.text.driver"));
                            action.click(Page_DriverBungiiProgress.Button_DuoCustomer_SMS());
                            break;

                        case "Call for a driver":
                            action.click(Page_DriverBungiiProgress.Button_DuoMore2());
                            if(!action.isElementPresent(Page_DriverBungiiProgress.Button_DuoCustomer_Call(true)))
                                action.click(Page_DriverBungiiProgress.Button_DuoMore2());
                            testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_DuoCustomer_Call()).trim(),PropertyUtility.getMessage("driver.call.driver"));
                            action.click(Page_DriverBungiiProgress.Button_DuoCustomer_Call());
                            break;
                        case"Contact support":
                            action.click(Page_DriverBungiiProgress.Button_DuoMore1());
                            if(!action.isElementPresent(Page_DriverBungiiProgress.Button_DuoCustomer_CallSupport(true)))
                                action.click(Page_DriverBungiiProgress.Button_DuoMore1());
                            testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_DuoCustomer_CallSupport()).trim(),PropertyUtility.getMessage("driver.text.support"));
                            action.click(Page_DriverBungiiProgress.Button_DuoCustomer_CallSupport());
                            break;
                        case"Contact support for driver":
                            action.click(Page_DriverBungiiProgress.Button_DuoMore2());
                            if(!action.isElementPresent(Page_DriverBungiiProgress.Button_DuoCustomer_CallSupport(true)))
                                action.click(Page_DriverBungiiProgress.Button_DuoMore2());
                            testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_DuoCustomer_CallSupport()).trim(),PropertyUtility.getMessage("driver.text.support"));
                            action.click(Page_DriverBungiiProgress.Button_DuoCustomer_CallSupport());
                            break;
                        case"View items":
                            action.click(Page_DriverBungiiProgress.Button_DuoMore1());
                            if(!action.isElementPresent(Page_DriverBungiiProgress.Button_DuoCustomer_ViewItem(true)))
                                action.click(Page_DriverBungiiProgress.Button_DuoMore1());
                            testStepVerify.isEquals(action.getText(Page_DriverBungiiProgress.Button_DuoCustomer_ViewItem()).trim(),PropertyUtility.getMessage("driver.view.items.customer"));
                            action.click(Page_DriverBungiiProgress.Button_DuoCustomer_ViewItem());
                            break;
                        default:
                            error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                            break;
                    }
            }

            log("Bungii Driver should able to  taps on " + arg0 + " during Bungii",
                    " Bungii Driver taps " + arg0 + " during a Bungii", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    //VISHAL[2512]:DONE
    @Then("^correct details should be displayed to driver on \"([^\"]*)\" app$")
    public void correctDetailsShouldBeDisplayedToDriverOnApp(String arg0) throws Throwable {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            String expectedDuoNumber="";
            String DriverAppdeviceType = driver.getCapabilities().getCapability("deviceType").toString();
            switch (arg0) {
                case "Driver 2 SMS":
                case "Driver 1 SMS":
                case "SMS":
                    expectedDuoNumber=arg0.contains("2")?String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_1_PHONE")):String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_2_PHONE"));
                    if(arg0.equals("SMS"))
                        expectedDuoNumber=PropertyUtility.getMessage("twilio.number");

                    if (DriverAppdeviceType.equalsIgnoreCase("Samsung"))
                        utility.isPhoneNumbersEqual(Page_OtherApps.SMS_Samsung_RecipientNo(), expectedDuoNumber,"Number "+expectedDuoNumber+"should be correctly displayed","Number"+expectedDuoNumber+" is not correctly displayed");

                    if (DriverAppdeviceType.equalsIgnoreCase("MOTOROLA")||!DriverAppdeviceType.equalsIgnoreCase("Samsung")) {
                        utility.isPhoneNumbersEqual(Page_OtherApps.SMS_Moto_RecipientNo(), expectedDuoNumber,"Number "+expectedDuoNumber+"should be correctly displayed","Number"+expectedDuoNumber+" is not correctly displayed");
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));

                    }

                    break;
                case "Support-SMS":

                    if (DriverAppdeviceType.equalsIgnoreCase("Samsung"))
                        utility.isPhoneNumbersEqual(Page_OtherApps.SMS_Samsung_RecipientNo(), PropertyUtility.getMessage("driver.support.number"),"Support number should be correctly displayed","Support number is not correctly displayed");

                    if (DriverAppdeviceType.equalsIgnoreCase("MOTOROLA")||!DriverAppdeviceType.equalsIgnoreCase("Samsung")) {
                        utility.isPhoneNumbersEqual(Page_OtherApps.SMS_Moto_RecipientNo(), PropertyUtility.getMessage("driver.support.number"),"Support number should be correctly displayed","Support number is not correctly displayed");
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));

                    }
                    break;
                case "Driver 1 Calling":
                case "Driver 2 Calling":
                case "Calling":
                    expectedDuoNumber=arg0.contains("2")?String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_1_PHONE")):String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_2_PHONE"));
                    if(arg0.equals("Calling"))
                        expectedDuoNumber=PropertyUtility.getMessage("twilio.number");

                    if (DriverAppdeviceType.equalsIgnoreCase("Samsung"))
                        utility.isPhoneNumbersEqual(Page_OtherApps.Call_Samsung_Number(), expectedDuoNumber,"Number "+expectedDuoNumber+"should be correctly displayed","Number"+expectedDuoNumber+" is not correctly displayed");

                    if (DriverAppdeviceType.equalsIgnoreCase("MOTOROLA")||!DriverAppdeviceType.equalsIgnoreCase("Samsung")) {
                        utility.isPhoneNumbersEqual(Page_OtherApps.Call_Moto_Number(), expectedDuoNumber,"Number "+expectedDuoNumber+"should be correctly displayed","Number"+expectedDuoNumber+" is not correctly displayed");
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                        ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));

                    }

                    break;

                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            while (Page_DriverBungiiProgress.Title_Status(true) == null)
                ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^Bungii Driver \"([^\"]*)\"$")
    public void bungiiDriver(String arg0) throws Throwable {
        try {

            switch (arg0) {
                case "cancels Bungii":
                    action.click(Page_DriverBungiiProgress.Button_Cancel());
                    action.click(Page_DriverBungiiProgress.Button_Cancel_Yes());
                    Thread.sleep(5000);
                    break;

                case "slides to the next state":
                    action.swipeRight(Page_DriverBungiiProgress.Slider());
                    Thread.sleep(1000);
                    break;

                case "completes Bungii":
                    action.click(Page_BungiiComplete.Button_OnToTheNext());
                    if(action.isElementPresent(Page_BungiiComplete.Button_OnToTheNext(true))){
                        Thread.sleep(5000);
                        action.click(Page_BungiiComplete.Button_OnToTheNext());
                    }

                    break;

                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log("Bungii Driver should " + arg0,
                    " Bungii Driver " + arg0, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I accept Alert message for \"([^\"]*)\"$")
    public void i_accept_alert_message_for_something(String strArg1) throws Throwable {
        try {

            String actualText = action.getText(Page_DriverBungiiProgress.Alert_Message());
            String expectedText = "";
            switch (strArg1) {
                case "Reminder: both driver at pickup":
                    expectedText = PropertyUtility.getMessage("bungii.duo.driver.pickup");
                    break;
                case "Reminder: both driver at drop off":
                    expectedText = PropertyUtility.getMessage("bungii.duo.driver.drop");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");break;
            }
            testStepVerify.isEquals(actualText, expectedText, strArg1 + "should be displayed", expectedText + " is displayed", "Expect alert text is " + expectedText + " and actual is " + actualText);
            action.click(Page_DriverBungiiProgress.Alert_Accept());
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^Quit Bungii Driver app$")
    public void quitBungiiDriverApp() throws Throwable {

    }

    @When("^simulator driver is \"([^\"]*)\"$")
    public void simulatorDriverIs(String arg0) throws Throwable {

    }

    @And("^Simulator Bungii Driver \"([^\"]*)\"$")
    public void simulatorBungiiDriver(String arg0) throws Throwable {

    }
}
