package com.bungii.android.stepdefinitions.Customer;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.HomePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import static com.bungii.common.manager.ResultManager.*;

public class HomeSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(HomeSteps.class);
    GeneralUtility utility = new GeneralUtility();
    HomePage homePage = new HomePage();
    ActionManager action = new ActionManager();

    @When("^I Select \"([^\"]*)\" from customer app menu list$")
    public void i_select_something_from_customer_app_menu_list(String strArg1) throws Throwable {
        try {
            //    action.click(homePage.Button_NavigationBar());
            utility.clickCustomerMenuItem(strArg1);
            log(" I Select " + strArg1 + " from customer app menu list", " I tapped on " + strArg1 + " from customer app menu list");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I tap on \"([^\"]*)\" > \"([^\"]*)\" link$")
    public void i_tap_on_something_something_link(String strArg1, String strArg2) throws Throwable {
        try {
            //     action.click(homePage.Button_NavigationBar());
            utility.clickCustomerMenuItem(strArg2);
            log(" I should able to tap on " + strArg2, " I tapped on " + strArg2,true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I tap \"([^\"]*)\" on Home page$")
    public void i_tap_something_on_home_page(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Referral Invite link":
                    action.click(homePage.Link_Invite());
                    break;
                case "Drop Clear Text":
                    WebElement row=homePage.Textfield_DropoffLocation();
                    int xAxis = row.getLocation().getX() + row.getSize().getWidth() -4;
                    int yAxis = row.getLocation().getY() + row.getRect().getHeight() / 2;
                    action.click(new Point(xAxis,yAxis));
                    action.hideKeyboard();
                    break;
                case "Pick Up Clear Text":
                    WebElement pickRow=homePage.Textfield_PickupLocation();
                    int x1Axis = pickRow.getLocation().getX() + pickRow.getSize().getWidth() -4;
                    int y1Axis = pickRow.getLocation().getY() + pickRow.getRect().getHeight() / 2;
                    action.click(new Point(x1Axis,y1Axis));
                    action.hideKeyboard();
                    break;
                case "My location":
                    action.click(homePage.Button_Locator());
                    Thread.sleep(3000);
                    String addressValue = action.getText(homePage.Textfield_PickupLocation());
                    if(addressValue.isEmpty())
                        action.click(homePage.Button_Locator());
                    break;
            }
            log(" I should able to tap on " + strArg1 + " on Home page", " I tapped on " + strArg1 + " on Home page",true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^Trip Information should be correctly displayed on CUSTOMER HOME screen$")
    public void trip_information_should_be_correctly_displayed_on_something_screen()  {
        try {
            String expectedPickUpLocation = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION")),
                    expectedDropLocation = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION")), expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO")?"DUO":"SOLO";

            String pickUpLocation = action.getText(homePage.Textfield_PickupLocation()), dropUpLocation = action.getText(homePage.Textfield_DropoffLocation());

            testStepVerify.isTrue(pickUpLocation.equals(expectedPickUpLocation),

                    "Pick up address should be " + expectedPickUpLocation, "Pick up address is " + expectedPickUpLocation,
                    "Expected pickup address is " + expectedPickUpLocation + ", but actual is" + pickUpLocation);
            testStepVerify.isTrue(dropUpLocation.equals(expectedDropLocation),

                    "Drop address should be " + expectedDropLocation, "Drop address is " + expectedDropLocation,
                    "Expected Drop address is " + expectedDropLocation + ", but actual is" + dropUpLocation);
            testStepVerify.isTrue(verifyNoOfDriver(expectedTripNoOfDriver),
                    "Number of driver for Bungii should be " + expectedTripNoOfDriver, "Number of driver for Bungii is " + expectedTripNoOfDriver,
                    "Number of driver for Bungii is not " + expectedTripNoOfDriver);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
    /**
     * Verify number of driver selected
     *
     * @param strDriverType : Identifer for driver type , SOLO, DUO
     * @return
     */
    public boolean verifyNoOfDriver(String strDriverType) {
        String expectedText = "";
        switch (strDriverType.toUpperCase()) {
            case "SOLO":
                expectedText ="1";
                break;
            case "DUO":
                expectedText ="2";
                break;
            default:
                logger.error("Please enter driver type correctly");
        }
        return  action.getText(homePage.Switch_SoloDuo()).equals(expectedText);
    }
    @Given("^I am on Customer logged in Home page$")
    public void iAmOnCustomerLoggedInHomePage() {
        try {

/*            String NavigationBarName = action.getText(homePage.Title_HomePage(true));

            if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))
                    || NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.signup"))) {
                utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer.user"),
                        PropertyUtility.getDataProperties("customer.password"));
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                // do nothing
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
              //  iClickButtonOnScreen("CANCEL", "SEARCHING");
               // iRejectAlertMessage();
            } else {
                i_tap_on_something_something_link(NavigationBarName,"HOME");
            }*/

            //   String NavigationBarName = action.getText(homePage.Title_HomePage(true));

            if (utility.isCorrectPage("Signup") || utility.isCorrectPage("Login")) {
               // utility.loginToCustomerApp(PropertyUtility.getDataProperties("valid.customer.phone"), PropertyUtility.getDataProperties("valid.customer.password"));
                utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
            } else if (utility.isCorrectPage("Home")) {
                // do nothing
            } else if (utility.isCorrectPage("Searching")) {
                //  iClickButtonOnScreen("CANCEL", "SEARCHING");
                // iRejectAlertMessage();
            } else {
                i_tap_on_something_something_link("Menu", "HOME");
            }
            log(" I am on Customer logged in Home page", "");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        } catch (Throwable throwable) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(throwable));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I close tutorial Page$")
    public void i_close_tutorial_page() throws Throwable {
        try {
            action.click(homePage.Button_Closetutorials());
            pass("I close tutorial Page", "I clicked on close button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^current location should be present as pickup location$")
    public void current_location_should_be_present_as_pickup_location() {
        try {
            String addressValue = action.getText(homePage.Textfield_PickupLocation());
            if(addressValue.isEmpty())
            testStepVerify.isTrue(!addressValue.isEmpty() && !addressValue.equals("Set Pickup Location"),
                    "Pickup location value should be non empty", "Pickup location value is" + addressValue,
                    "Pickup location value should be non empty");

            testStepVerify.isEquals(action.getText(homePage.Text_ETAvalue()), "0 MINS");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));

            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" box header and ETA bar header should be correctly displayed$")
    public void something_box_header_and_eta_bar_header_should_be_correctly_displayed(String action)  {
        try {
            switch (action.toUpperCase()) {
                case "DROP":
                    testStepVerify.isEquals(getEtaBarHeader("DROP"), PropertyUtility.getMessage("customer.drop.etaheader"));
                    testStepVerify.isEquals(getLocationBoxHeaderValue("DROP"),
                            PropertyUtility.getMessage("customer.drop.boxheader"));
                    break;
                case "PICK UP":
                    testStepVerify.isEquals(getEtaBarHeader("PICKUP"),
                            PropertyUtility.getMessage("customer.pickup.etaheader"));
                    testStepVerify.isEquals(getLocationBoxHeaderValue("PICKUP"),
                            PropertyUtility.getMessage("customer.pickup.boxheader"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));

            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * @param location whose eta header is required
     * @return value of eta header
     */
    public String getEtaBarHeader(String location) {
        if (location.equalsIgnoreCase("PICKUP"))
            return action.getText(homePage.Text_ETAHeader());
        else if (location.equalsIgnoreCase("DROP"))
            return action.getText(homePage.Text_ETAHeader());
        else
            return "";
    }

    /**
     * @param location whoes data is required ,pickup or drop
     * @return value of header
     */
    public String getLocationBoxHeaderValue(String location) {
        if (location.equalsIgnoreCase("PICKUP"))
            return action.getText(homePage.Textfield_PickupLocation());
        else if (location.equalsIgnoreCase("DROP"))
            return action.getText(homePage.Textfield_DropoffLocation());
        else
            return "";
    }

    @When("^I select \"([^\"]*)\" location$")
    public void i_select_something_location(String toDoAction) {
        try {
            switch (toDoAction.toUpperCase()) {
                case "DROP":
                    action.click(homePage.Button_Locator());
                    action.click(homePage.Button_ETASet());
                    Thread.sleep(3000);
                    break;
                case "PICK UP":
                    action.click(homePage.Button_Locator());
                    action.click(homePage.Button_ETASet());
                    Thread.sleep(3000);
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP ");
            }
            pass(toDoAction + " location should be selected",
                    toDoAction + " location is selected", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^\"([^\"]*)\" address should be displayed in text box$")
    public void something_address_should_be_displayed_in_text_box(String action) {
        try {
            String textBoxValue = "";
            Thread.sleep(7000);
            switch (action.toUpperCase()) {
                case "DROP":
                    textBoxValue = getLocationBoxHeaderValue("DROP");
                    break;
                case "PICK UP":
                    textBoxValue = getLocationBoxHeaderValue("PICKUP");
                    break;
                default:
                    throw new Exception(" UN IMPLEMENTED STEP");
            }
            testStepVerify.isTrue(
                    !textBoxValue.isEmpty() && !textBoxValue.equals("Set Drop Off Location")
                            && !textBoxValue.equals("Set Pickup Location"),
                    action + "address bar value should be not empty", action + "address bar value is " + textBoxValue,
                    action + "address bar value is empty");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^\"([^\"]*)\" address should be empty$")
    public void something_address_should_be_empty(String action) {
        try {
            String textBoxValue = "";
            switch (action.toUpperCase()) {
                case "DROP":
                    textBoxValue =  getLocationBoxHeaderValue("DROP");
                    break;
                case "PICK UP":
                    textBoxValue = getLocationBoxHeaderValue("PICKUP");
                    break;
                default:
                    throw new Exception(" UN IMPLEMENTED STEP");
            }
            testStepVerify.isTrue(
                    textBoxValue.isEmpty() || textBoxValue.equals("Set Drop Off Location")
                            || textBoxValue.equals("Set Pickup Location"),
                    action + "address bar value is empty",
                    action + "address bar value is not empty , its value is" + textBoxValue);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
