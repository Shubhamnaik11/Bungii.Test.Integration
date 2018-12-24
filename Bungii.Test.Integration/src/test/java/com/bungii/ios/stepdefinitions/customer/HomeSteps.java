package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.HomePage;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Point;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.*;


public class HomeSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(EstimateSteps.class);
    ActionManager action = new ActionManager();
    private HomePage homePage;

    public HomeSteps(HomePage homePage) {
        this.homePage = homePage;
    }

    @Then("^User should be successfully logged in to the application$")
    public void user_should_be_successfully_logged_in_to_the_system() {
        try {
            GeneralUtility utility = new GeneralUtility();
            boolean isHomePage = utility.verifyPageHeader("HOME");
            testStepVerify.isTrue(isHomePage, "User should be loggind in", " Home screen is displayed", "User was not logged in");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    public void verifyTripInformationOnHome() {
        String expectedPickUpLocation = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION")),
                expectedDropLocation = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION")), expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

        String actualPickUpLocation = getSelectedPickUpLocation();
        String actualDropLocation = getSelectedDropLocation();

        testStepVerify.isTrue(actualPickUpLocation.equals(expectedPickUpLocation),

                "Pick up address should be " + expectedPickUpLocation, "Pick up address is " + expectedPickUpLocation,
                "Expected pickup address is " + expectedPickUpLocation + ", but actual is" + actualPickUpLocation);
        testStepVerify.isTrue(actualDropLocation.equals(expectedDropLocation),

                "Drop address should be " + expectedDropLocation, "Drop address is " + expectedDropLocation,
                "Expected Drop address is " + expectedDropLocation + ", but actual is" + actualDropLocation);
        testStepVerify.isTrue(verifyNoOfDriver(expectedTripNoOfDriver),
                "Number of Driver for Bungii should be " + expectedTripNoOfDriver, "Number of Driver for Bungii is " + expectedTripNoOfDriver,
                "Number of Driver for Bungii is not " + expectedTripNoOfDriver);

    }

    @When("^I request for  bungii$")
    public void iRequestForBungii(DataTable data) {

        try {

            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String tripDriverType = dataMap.get("Driver").trim();
            String distance = dataMap.get("Distance").trim();
            int dragFactor = 1;
            switch (distance.toUpperCase()) {
                case "SMALL":
                    dragFactor = 1;
                    break;
                case "MEDIUM":
                    dragFactor = 2;
                    break;

                case "LONG":
                    dragFactor = 5;
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }

            selectPickUpLocation(dragFactor);

            selectDropLocation(dragFactor);
            selectTripDriver(tripDriverType);

            testStepVerify.isTrue(verifyNoOfDriver(tripDriverType), "I Requested Bungii",
                    "Number of Driver for Bungii should be " + tripDriverType,
                    "Number of Driver for Bungii is not " + tripDriverType);


            saveBungiiHomeDetails(tripDriverType);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I request for  bungii for given pickup and drop location$")
    public void i_request_for_bungii_for_given_pickup_and_drop_location(DataTable data) {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

        String pickup = dataMap.get("Pickup Location").trim();
        String drop = dataMap.get("Drop Location").trim();
        String tripDriverType = dataMap.get("Driver").trim();

        selectBungiiLocation("PICK UP", pickup);
        selectBungiiLocation("DROP", drop);
        selectTripDriver(tripDriverType);
        saveBungiiHomeDetails(tripDriverType);
        testStepVerify.isTrue(verifyNoOfDriver(tripDriverType),
                "I should request " + tripDriverType +" Bungii", tripDriverType +" Bungii was requested for Pick up  address"+ pickup+" and drop address "+ drop +" using search dropdown",
                "Number of Driver for Bungii is not " + tripDriverType);
    } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    public void saveBungiiHomeDetails(String tripDriverType) {
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION", getSelectedPickUpLocation());
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION", getSelectedDropLocation());
        cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER", tripDriverType.toUpperCase());
    }

    public void selectBungiiLocation(String type, String location) {
        switch (type.toUpperCase()) {
            case "PICK UP":
                selectPickUpLocation(location);
                break;
            case "DROP":
                selectDropLocation(location);
                break;
            default:
                break;
        }
    }


    @When("^I select \"([^\"]*)\" location$")
    public void i_select_something_location(String action) {
        try {
            switch (action.toUpperCase()) {
                case "DROP":
                    selectDropLocation(1);
                    break;
                case "PICK UP":
                    selectPickUpLocation(1);
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP ");
            }
            pass(action + " location should be selected",
                    action + " location is selected", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^current location should be present as pickup location$")
    public void current_location_should_be_present_as_pickup_location() {
        try {
            String addressValue = getSelectedPickUpLocation();
            testStepVerify.isTrue(!addressValue.isEmpty() && !addressValue.equals("Set Pickup Location"),
                    "Pickup location value should be non empty", "Pickup location value is" + addressValue,
                    "Pickup location value should be non empty");

            testStepVerify.isEquals(getEtaTime(), "0 MINS");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));

            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Clear Button should be enabled for \"([^\"]*)\" box$")
    public void clear_button_should_be_enabled_for_something_box(String action) {
        try {
            switch (action.toUpperCase()) {
                case "DROP":
                    testStepVerify.isTrue(isClearButtonEnabled("DROP"),
                            "Clear Button should be enabled for " + action + "  box", "Clear Button is enabled",
                            "Clear button is not enabled for " + action + "box");
                    break;
                case "PICK UP":
                    testStepVerify.isTrue(isClearButtonEnabled("PICKUP"),
                            "Clear Button should be enabled for " + action + "  box", "Clear Button is enabled",
                            "Clear button is not enabled for " + action + "box");
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

    @Then("^\"([^\"]*)\" address should be displayed in text box$")
    public void something_address_should_be_displayed_in_text_box(String action) {
        try {
            String textBoxValue = "";
            switch (action.toUpperCase()) {
                case "DROP":
                    textBoxValue = getSelectedDropLocation();
                    break;
                case "PICK UP":
                    textBoxValue = getSelectedPickUpLocation();
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
                    textBoxValue = getSelectedDropLocation();
                    break;
                case "PICK UP":
                    textBoxValue = getSelectedPickUpLocation();
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

    @And("^I Select \"([^\"]*)\" from Customer App menu$")
    public void i_select_something_from_customer_app_menu(String menuItem) {
        try {
            goToAppMenu();
            clickAppMenu(menuItem);
            log(menuItem + " must be selected sucessfully",
                    menuItem + " is selected sucessfully", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Trip Information should be correctly displayed on CUSTOMER HOME screen$")
    public void trip_information_should_be_correctly_displayed_on_something_screen()  {
        try {
            verifyTripInformationOnHome();
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
    /**
     * Get navigation header name
     *
     * @return Navigation header name
     */
    public String getNavigationBarName() {
        return action.getNameAttribute(homePage.Text_NavigationBar());
    }


    /**
     * Check if invite button is present on screen
     *
     * @return boolean value if invite button is present
     */
    public boolean isInviteButtonPresent() {
        return homePage.isElementEnabled((homePage.Button_Invite()));
    }

    /**
     * Click App menu icon
     */
    public void goToAppMenu() {
       // action.invisibilityOfElementLocated(homePage.Indicator_Loading(true));
        action.click(homePage.Button_AppMenu());
    }

    /**
     * @param location whoes data is required ,pickup or drop
     * @return value of header
     */
    public String getLocationBoxHeaderValue(String location) {
        if (location.equalsIgnoreCase("PICKUP"))
            return action.getValueAttribute(homePage.Text_HeaderPickup());
        else if (location.equalsIgnoreCase("DROP"))
            return action.getValueAttribute(homePage.Text_HeaderDrop());
        else
            return "";
    }

    /**
     * Click clear text button on either pickup or drip fields
     *
     * @param location whose text need to be cleared
     */
    public void clickClearTextButton(String location) {
        if (location.equalsIgnoreCase("PICKUP"))
            action.click(homePage.Button_ClearPickup());
        else if (location.equalsIgnoreCase("DROP"))
            action.click(homePage.Button_ClearDrop());

    }

    /**
     * @param location whose eta header is required
     * @return value of eta header
     */
    public String getEtaBarHeader(String location) {
        if (location.equalsIgnoreCase("PICKUP"))
            return action.getNameAttribute(homePage.Text_EtaPickupHeader());
        else if (location.equalsIgnoreCase("DROP"))
            return action.getNameAttribute(homePage.Text_EtaDropHeader());
        else
            return "";
    }

    /**
     * Get ETA time value
     *
     * @return Value of ETA time
     */
    public String getEtaTime() {
        return action.getNameAttribute(homePage.Text_EtaTime());
    }

    /**
     * Check if clear button is enabled
     *
     * @param location : Identifer for ETA type , values : pickup drop
     * @return
     */
    public boolean isClearButtonEnabled(String location) {
        if (location.equalsIgnoreCase("PICKUP"))
            return homePage.isElementEnabled(homePage.Button_ClearPickup());
        else if (location.equalsIgnoreCase("DROP"))
            return homePage.isElementEnabled(homePage.Button_ClearDrop());
        else
            return false;
    }

    /**
     * Verify number of driver selected
     *
     * @param strDriverType : Identifer for driver type , SOLO, DUO
     * @return
     */
    public boolean verifyNoOfDriver(String strDriverType) {
        boolean flag = false;
        switch (strDriverType.toUpperCase()) {
            case "SOLO":
                flag = homePage.isElementEnabled(homePage.Button_SoloActive()) && homePage.isElementEnabled(homePage.Button_DuoDeActive());
                break;
            case "DUO":
                flag = homePage.isElementEnabled(homePage.Button_DuoActive()) && homePage.isElementEnabled(homePage.Button_SoloDeActive());
                break;
            default:
                logger.error("Please enter driver type correctly");
        }
        return flag;
    }

    /**
     * Click in app menu
     *
     * @param appMenuItem : Identifier for app menu
     */
    public void clickAppMenu(String appMenuItem) {
        switch (appMenuItem.toUpperCase()) {
            case "HOME":
                action.click(homePage.AppMenu_Home());
                break;
            case "FAQ":
                action.click(homePage.AppMenu_FAQ());
                break;
            case "ACCOUNT":
                action.click(homePage.AppMenu_Account());
                break;
            case "SCHEDULED BUNGIIS":
                action.click(homePage.AppMenu_ScheduledTrip());
                break;
            case "PAYMENT":
                action.click(homePage.AppMenu_Payment());
                break;
            case "SUPPORT":
                action.click(homePage.AppMenu_Support());
                break;
            case "PROMOS":
                action.click(homePage.AppMenu_Promos());
                break;
            case "DRIVE WITH BUNGII":
                action.click(homePage.AppMenu_DriveWithBungii());
                break;
            case "LOGOUT":
                action.click(homePage.AppMenu_LogOut());
                break;
            default:
                logger.error("Please specify valid application menu item");
                break;
        }
    }

    /**
     * Get Pick up location value
     *
     * @return value of selected pickup location
     */
    public String getSelectedPickUpLocation() {
        return homePage.TextBox_Pickup().getAttribute("value");
    }

    /**
     * Get Drop location value
     *
     * @return value of selected drop location
     */
    public String getSelectedDropLocation() {
        return homePage.TextBox_Drop().getAttribute("value");
    }

    /**
     * Wait for loading to disappear
     */
    public void waitForLoadingDisappear() {
        action.invisibilityOfElementLocated(homePage.Image_Loading());
    }

    /**
     * Select pickup location by dragging on map
     *
     * @param dragFactor Value of how much location must be far from given point
     */
    public void selectPickUpLocation(int dragFactor) {

        // wait for loading to disappear
        //action.invisibilityOfElementLocated(homePage.Indicator_Loading());

        int offset = 70 * dragFactor;
        Point initial = homePage.Image_eta_bar().getLocation();
        while (homePage.TextBox_Pickup().getAttribute("value").contains("Pick")) {
            action.dragFromToForDuration(initial.x, initial.y, initial.x, initial.y + offset, 1);
            //	action.invisibilityOfElementLocated(homePage.Image_Loading());
        }
        action.click(homePage.BUTTON_SET());
    }

    /**
     * Select pickup location by entering value in textbox
     *
     * @param location : Location value that is to be entered in textbox
     */
    public void selectPickUpLocation(String location) {

        // wait for loading to disappear
        //action.invisibilityOfElementLocated(homePage.Indicator_Loading());
        action.clearEnterText(homePage.TextBox_Pickup(), location);
        action.click(homePage.Link_PickUpSuggestion());
      //  action.hideKeyboard();
        action.click(homePage.BUTTON_SET());
    }

    /**
     * Select drop location by entering value in textbox
     *
     * @param location Location value that is to be entered in textbox
     */
    public void selectDropLocation(String location) {

        // wait for loading to disappear
        //action.invisibilityOfElementLocated(homePage.Indicator_Loading());
        action.clearEnterText(homePage.TextBox_Drop(), location);
        action.click(homePage.Link_DropSuggestion());
        //	hideKeyboard();
        // Click(BUTTON_SET);
    }

    /**
     * Select drop location by dragging on map
     *
     * @param dragFactor Value of how much location must be far from given point
     */
    public void selectDropLocation(int dragFactor) {

        // wait for loading to disappear
        //action.invisibilityOfElementLocated(homePage.Indicator_Loading());

        int offset = 70 * dragFactor;
        Point initial = homePage.Image_eta_bar().getLocation();
        // scroll and click on set , if alert is present then repeat
        while (homePage.TextBox_Drop().getAttribute("value").contains("Set Drop")) {
            action.dragFromToForDuration(initial.x, initial.y, initial.x, initial.y + offset, 1);
            //	action.invisibilityOfElementLocated(homePage.Image_Loading());
            action.click(homePage.BUTTON_SET());
            if (action.isAlertPresent())
                SetupManager.getDriver().switchTo().alert().accept();
        }
    }

    /**
     * Select number of driver for trip
     *
     * @param noOfDriver Identifer fo driver
     */
    public void selectTripDriver(String noOfDriver) {
        if (noOfDriver.equalsIgnoreCase("duo")) {
            action.click(homePage.Scroll_SoloToDuo());
        } else {
            // TODO: verify solo is active
        }

    }


}
