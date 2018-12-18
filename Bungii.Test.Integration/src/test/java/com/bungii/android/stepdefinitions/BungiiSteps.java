package com.bungii.android.stepdefinitions;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.bungii.*;
import com.bungii.android.pages.bungii.SearchingPage;
import com.bungii.android.pages.bungiiDriver.*;
import com.bungii.android.pages.otherApps.OtherAppsPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.AssertManager;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.pages.driver.BungiiRequestPage;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.StepDefinitionMatch;

public class BungiiSteps extends DriverBase {

    SearchingPage Page_BungiiSearch = new SearchingPage();
    BungiiAcceptedPage Page_BungiiAccepted = new BungiiAcceptedPage();
    BungiiProgressPage Page_CustomerBungiiProgress = new BungiiProgressPage();
    DriverInProgressBungiiPage Page_DriverBungiiProgress = new DriverInProgressBungiiPage();
    OtherAppsPage Page_OtherApps = new OtherAppsPage();
    DriverHomePage Page_DriverHome = new DriverHomePage();
    DriverBungiiRequestPage Page_BungiiRequest = new DriverBungiiRequestPage();
    BungiiCompletedPage Page_BungiiComplete = new BungiiCompletedPage();

    AssertManager assertManager = new AssertManager();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    PropertyUtility properties = new PropertyUtility();

    @And("^I add loading/unloading time of \"([^\"]*)\"$")
    public void iAddLoadingUnloadingTimeOf(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^for a Bungii I should see \"([^\"]*)\"$")
    public void forABungiiIShouldSee(String arg0) throws Throwable {
        switch (arg0)
        {
            case "Bungii Estimate page with all details":
                break;

            case "Bungii Home page with locations":
                break;

            case "Bungii search screen":

               /* final ThreadLocal<StepDefinitionMatch> threadStepDefMatch = new InheritableThreadLocal<StepDefinitionMatch>();
                StepDefinitionMatch match = threadStepDefMatch.get();
                System.out.println(match.getSte());


                testStepVerify.isTrue(Page_BungiiSearch.Loader().isSelected(),"","","");
                assertManager.isTrue(Page_BungiiSearch.Text_MsgSearching().isSelected());
                assertManager.isTrue(Page_BungiiSearch.ProgressBar().isSelected());
                break;

            case "Bungii accepted":
                assertManager.ElementDisplayed(Page_BungiiAccepted.PageTitle_BungiiAccepted());
                assertManager.ElementTextEqual(Page_BungiiAccepted.Label_BungiiAccepted(), Data_Valid_Customer.BungiiAccepted);
                assertManager.ElementTextEqual(Page_BungiiAccepted.Label_DriverEnRoute(), Data_Valid_Customer.DriverEnRoute);
                assertManager.ElementDisplayed(Page_BungiiAccepted.Image_Driver());
                assertManager.ElementDisplayed(Page_BungiiAccepted.DriverRatingBar());
                assertManager.ElementTextEqual(Page_BungiiAccepted.Label_DriverName(), Data_Valid_Customer.DriverName);
                break;

            case "Enroute screen":
                assertManager.ElementTextEqual(Page_BungiiProgress.PageTitle(), Data_Valid_Customer.PageTitle_Enroute);
                assertManager.ElementSelected(Page_BungiiProgress.BungiiStatus_Enroute());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem());

                //AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location, Data_Valid_Customer.ETAPickup);
                break;

            case "Arrived screen":
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title(), Data_Valid_Customer.PageTitle_Arrived);

                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute());
                assertManager.ElementSelected(Page_BungiiProgress.BungiiStatus_Arrived());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem());

                break;

            case "Loading Item screen":
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title(), Data_Valid_Customer.PageTitle_Loading);

                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived());
                assertManager.ElementSelected(Page_BungiiProgress.BungiiStatus_LoadingItem());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem()
                break;

            case "Driving to DropOff screen":
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title(), Data_Valid_Customer.PageTitle_Driving);

                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem());
                assertManager.ElementSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem());

                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location(), Data_Valid_Customer.ETAPickup);
                break;

            case "Unloading Item screen":
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title(), Data_Valid_Customer.PageTitle_Unloading);
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem());
                assertManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff());
                assertManager.ElementSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem());
                break;

            case "Pickup location details":
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_LocationTitle(), Data_Valid_Customer.LocationTitlePickup);
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location(), Data_Customer.PickupLocation_AT);
                break;

            case "Dropoff location details":
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_LocationTitle(), Data_Valid_Customer.LocationTitleDropOff);
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location(), Data_Customer.DropoffLocation_AT);
                break;

            case "Driver Details":
                assertManager.elementDisplayed(Page_BungiiProgress.Bungii_Driver_Image());
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title(), Data_Valid_Customer.DriverTitle);
                assertManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Name(), Data_Valid_Customer.DriverName);
                assertManager.ElementDisplayed(Page_BungiiProgress.Bungii_Driver_RatingBar());
                assertManager.ElementEnabled(Page_BungiiProgress.Button_Bungii_Driver_SMS());
                assertManager.ElementEnabled(Page_BungiiProgress.Button_Bungii_Driver_Call());
*/                break;

            default: break;
        }
    }



    @When("^I tap on \"([^\"]*)\" on Driver Home page$")
    public void iTapOnOnDriverHomePage(String arg0) throws Throwable {
        switch (arg0)
        {
            case "Online/Offline button":
                action.click(Page_DriverHome.Button_OnlineOffline());
                break;

            case "Available Trips link":
                action.click(Page_DriverHome.Link_AvailableTrips());
                break;

            default: break;
        }
    }

    @And("^Bungii Driver \"([^\"]*)\" request$")
    public void bungiiDriverRequest(String arg0) throws Throwable {
        action.waitUntilAlertDisplayed(Page_BungiiRequest.Alert_Msg());
        if (action.isElementPresent(Page_BungiiRequest.Alert_Msg()))
        {
            action.click(Page_BungiiRequest.AlertButton_View());
            switch (arg0)
            {
                case "accepts On Demand Bungii":
                    action.click(Page_BungiiRequest.Button_Accept());
                    break;

                case "rejects Bungii":
                    action.click(Page_BungiiRequest.Button_Reject());
                    break;
            }
        }
    }

    @When("^I tap \"([^\"]*)\" during a Bungii$")
    public void iTapDuringABungii(String arg0) throws Throwable {
        switch (arg0)
        {
            case "OK on Driver Accepted screen":
                action.waitUntilIsElementExistsAndDisplayed(Page_BungiiAccepted.Button_OK());
                action.click(Page_BungiiAccepted.Button_OK());
                break;

            case "SMS for a solo driver":
                action.click(Page_CustomerBungiiProgress.Button_Bungii_Driver_SMS());
                break;

            case "Call for a solo driver":
                action.click(Page_CustomerBungiiProgress.Button_Bungii_Driver_Call());
                break;

            default: break;
        }
    }

    @Then("^Bungii driver should see \"([^\"]*)\"$")
    public void bungiiDriverShouldSee(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^correct details should be displayed on \"([^\"]*)\" app$")
    public void correctDetailsShouldBeDisplayedOnApp(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Bungii Driver taps \"([^\"]*)\" during a Bungii$")
    public void bungiiDriverTapsDuringABungii(String arg0) throws Throwable {
        switch (arg0)
        {
            case "SMS for a customer":
                action.click(Page_DriverBungiiProgress.Button_Customer_SMS());
                break;

            case "Call for a solo driver":
                action.click(Page_DriverBungiiProgress.Button_Customer_Call());
                break;

            default: break;
        }
    }

    @Then("^correct details should be displayed to driver on \"([^\"]*)\" app$")
    public void correctDetailsShouldBeDisplayedToDriverOnApp(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Bungii Driver \"([^\"]*)\"$")
    public void bungiiDriver(String arg0) throws Throwable {
        switch (arg0)
        {
            case "cancels Bungii":
                action.click(Page_DriverBungiiProgress.Button_Cancel());
                action.click(Page_DriverBungiiProgress.Button_Cancel_Yes());
                break;

            case "slides to the next state":
                action.swipeLeft(Page_DriverBungiiProgress.Slider());
                break;

            case "completes Bungii":
                action.click(Page_BungiiComplete.Button_OnToTheNext());
                break;

            default: break;
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
