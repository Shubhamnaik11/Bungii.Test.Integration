package com.bungii.android.stepdefinitions;

import com.bungii.android.pages.bungii.BungiiAcceptedPage;
import com.bungii.android.pages.bungii.BungiiProgressPage;
import com.bungii.android.pages.bungii.SearchingPage;
import com.bungii.android.pages.otherApps.OtherAppsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.AssertManager;
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
    BungiiProgressPage Page_BungiiProgress = new BungiiProgressPage();
    OtherAppsPage Page_OtherApps = new OtherAppsPage();

    AssertManager assertManager = new AssertManager();

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

    @Given("^I am logged in as \"([^\"]*)\" driver$")
    public void iAmLoggedInAsDriver(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I tap on \"([^\"]*)\" on Driver Home page$")
    public void iTapOnOnDriverHomePage(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Bungii Driver \"([^\"]*)\" request$")
    public void bungiiDriverRequest(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I tap \"([^\"]*)\" during a Bungii$")
    public void iTapDuringABungii(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
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
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^correct details should be displayed to driver on \"([^\"]*)\" app$")
    public void correctDetailsShouldBeDisplayedToDriverOnApp(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Bungii Driver \"([^\"]*)\"$")
    public void bungiiDriver(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Quit Bungii Driver app$")
    public void quitBungiiDriverApp() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^simulator driver is \"([^\"]*)\"$")
    public void simulatorDriverIs(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Simulator Bungii Driver \"([^\"]*)\"$")
    public void simulatorBungiiDriver(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
