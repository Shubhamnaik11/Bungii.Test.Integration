package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.HomePage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.*;


public class HomeSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(EstimateSteps.class);
    ActionManager action = new ActionManager();
    private HomePage homePage;
    DbUtility dbUtility= new DbUtility();
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
        String expectedPickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")), expectedPickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")),
                expectedDropLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")), expectedDropLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")), expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

        String[] actualPickUpLocation = getSelectedPickUpLocation();
        String[] actualDropLocation = getSelectedDropLocation();

        testStepVerify.isTrue(actualPickUpLocation[0].equals(expectedPickUpLocationLineOne) && actualPickUpLocation[1].equals(expectedPickUpLocationLineTwo),

                "Pick up address should be Line 1:" + expectedPickUpLocationLineOne + " Line 2:" + expectedPickUpLocationLineTwo, "Pick up address is Line 1:" + actualPickUpLocation[0] + " Line 2:" + actualPickUpLocation[1],
                "Expected pickup address is " + expectedPickUpLocationLineOne + "|" + expectedPickUpLocationLineTwo + ", but actual is " + actualPickUpLocation[0] + "|" + actualPickUpLocation[1]);
        testStepVerify.isTrue(actualDropLocation[0].equals(expectedDropLocationLineOne) && actualDropLocation[1].equals(expectedDropLocationLineTwo),
                "Drop address should be Line 1:" + expectedDropLocationLineOne + " , 2:" + expectedDropLocationLineTwo, "Drop address is Line 1" + actualDropLocation[0] + "Line 2" + actualDropLocation[1],
                "Expected Drop address is " + expectedDropLocationLineOne + "|" + expectedDropLocationLineTwo + ", but actual is" + actualDropLocation[0] + "|" + actualDropLocation[1]);
        testStepVerify.isTrue(verifyNoOfDriver(expectedTripNoOfDriver),
                "Number of driver for Bungii should be " + expectedTripNoOfDriver, "Number of driver for Bungii is " + expectedTripNoOfDriver,
                "Number of driver for Bungii is not " + expectedTripNoOfDriver);

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
                case "CURRENT":
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            if (distance.equalsIgnoreCase("CURRENT")) {
                waitForLoadingDisappear();
                action.dragFromToForDuration(150, 470, 151, 471, 1);
                action.click(homePage.BUTTON_SET());
                waitForLoadingDisappear();
                action.click(homePage.BUTTON_SET());

            } else {
                selectPickUpLocation(dragFactor);
                selectDropLocation(dragFactor);
            }
            selectTripDriver(tripDriverType);
            String bungiiType = saveBungiiHomeDetails(tripDriverType);
            boolean isbungiiTypeCorrect = false;
            isbungiiTypeCorrect = (tripDriverType.toUpperCase().equalsIgnoreCase("SOLO") && bungiiType.equals("1")) || (tripDriverType.toUpperCase().equalsIgnoreCase("DUO") && bungiiType.equals("2"));

/*            testStepVerify.isTrue(verifyNoOfDriver(tripDriverType), "I Requested Bungii",
                    "Number of driver for Bungii should be " + tripDriverType,
                    "Number of driver for Bungii is not " + tripDriverType);*/
            testStepVerify.isTrue(isbungiiTypeCorrect, "I Requested Bungii",
                    "Number of driver for Bungii should be " + tripDriverType,
                    "Number of driver for Bungii is not " + tripDriverType);


        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I request for  bungii for given pickup and drop location$")
    public void i_request_for_bungii_for_given_pickup_and_drop_location(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String pickup = dataMap.get("Pickup Location").trim();
            String drop = dataMap.get("Drop Location").trim();
            String tripDriverType = dataMap.get("Driver").trim();
            try {
                String geofenceName = dataMap.get("Geofence");
                cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofenceName.toLowerCase());
                logger.detail("Geofence is specified as input is" + (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE"));
            } catch (Exception e) {
                logger.detail("Geofence is not specified as input");
            }
            selectBungiiLocation("PICK UP", pickup);
            selectBungiiLocation("DROP", drop);
            selectTripDriver(tripDriverType);
            cucumberContextManager.setScenarioContext("BUNGII_TYPE", tripDriverType.toLowerCase());

            String bungiiType = saveBungiiHomeDetails(tripDriverType);
            boolean isbungiiTypeCorrect = false;
            isbungiiTypeCorrect = (tripDriverType.toUpperCase().equalsIgnoreCase("SOLO") && bungiiType.equals("1")) || (tripDriverType.toUpperCase().equalsIgnoreCase("DUO") && bungiiType.equals("2"));
            testStepVerify.isTrue(isbungiiTypeCorrect,
                    "I should request " + tripDriverType + " Bungii", tripDriverType + " Bungii was requested for Pick up  address" + pickup + " and drop address " + drop + " using search dropdown",
                    "Number of driver for Bungii is not " + tripDriverType);
        } catch (Exception e) {
            logger.error("Error Requesting Bungii", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error Requesting Bungii for pickup location : "+ data.transpose().asMap(String.class, String.class).get("Pickup Location") ,
                    true);
        }

    }

    @And("^I enter pickup location$")
    public void i_request_for_bungii_for_given_pickup_location(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String pickup = dataMap.get("Pickup Location").trim();
            selectBungiiLocation("PICK UP", pickup);
            log("I enter pickup location", " I entered location" + pickup);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("^I enter drop location$")
    public void i_request_for_bungii_for_given_drop_location(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String drop = dataMap.get("Drop Location").trim();
            selectBungiiLocation("DROP", drop);
            log("I enter DROP location", " I entered location" + drop);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    public String saveBungiiHomeDetails(String tripDriverType) {
/*        String[] pickUpLocation = getSelectedPickUpLocation();
        String[] dropOffLocation = getSelectedDropLocation();
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", pickUpLocation[0]);
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", pickUpLocation[1]);
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", dropOffLocation[0]);
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", dropOffLocation[1]);
        cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER", tripDriverType.toUpperCase());*/
        action.waitUntilIsElementExistsAndDisplayed(homePage.Button_GetEstimate(true));
        String[] bungiiLocation = getPickUpAndDropLocation();
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", bungiiLocation[0]);
        cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", bungiiLocation[1]);
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", bungiiLocation[2]);
        cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", bungiiLocation[3]);
        cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER", tripDriverType.toUpperCase());

        return bungiiLocation[4];

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
    public void i_select_something_location(String actionToDo) {
        try {
            switch (actionToDo.toUpperCase()) {
                case "DROP":
                    selectDropLocation(1);
                    break;
                case "PICK UP":
                    selectPickUpLocation(1);
                    //   action.click(homePage.BUTTON_SET());
                    break;
                case "CURRENT PICK UP":
                    action.click(homePage.BUTTON_SET());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP ");
            }
            pass(actionToDo + " location should be selected",
                    actionToDo + " location is selected", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^current location should be present as pickup location$")
    public void current_location_should_be_present_as_pickup_location() {
        try {

            String addressValue = action.getValueAttribute(homePage.TextBox_Pickup_LineOne());

            testStepVerify.isTrue(!addressValue.isEmpty() && !addressValue.equals(""),
                    "Pickup location value should be non empty", "Pickup location value is" + addressValue,
                    "Pickup location value should be non empty");

            //       testStepVerify.isEquals(getEtaTime(), "0 MINS");
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
    public void something_box_header_and_eta_bar_header_should_be_correctly_displayed(String action) {
        try {
            switch (action.toUpperCase()) {
                case "DROP":
                    testStepVerify.isEquals(getEtaBarHeader("DROP"), PropertyUtility.getMessage("customer.drop.etaheader"));
                    break;
                case "PICK UP":
                    testStepVerify.isEquals(getEtaBarHeader("PICKUP"),
                            PropertyUtility.getMessage("customer.pickup.etaheader"));
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
    public void something_address_should_be_displayed_in_text_box(String actionAddress) {
        try {
            Thread.sleep(10000);
            String textBoxValue = "";
            switch (actionAddress.toUpperCase()) {
                case "DROP":
                    textBoxValue = action.getValueAttribute(homePage.TextBox_Drop_LineOne());
                    break;
                case "PICK UP":
                    textBoxValue = action.getValueAttribute(homePage.TextBox_Pickup_LineOne());
                    break;
                default:
                    throw new Exception(" UN IMPLEMENTED STEP");
            }
            testStepVerify.isTrue(
                    !textBoxValue.isEmpty() && !textBoxValue.equals("Set Drop Off Location")
                            && !textBoxValue.equals("Set Pickup Location"),
                    actionAddress + "address bar value should be not empty", actionAddress + "address bar value is " + textBoxValue,
                    actionAddress + "address bar value is empty");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^\"([^\"]*)\" address should be empty$")
    public void something_address_should_be_empty(String actionAddress) {
        try {
            String textBoxValue = "";
            switch (actionAddress.toUpperCase()) {
                case "DROP":
                    textBoxValue = action.getValueAttribute(homePage.TextBox_Pickup());
                    break;
                case "PICK UP":
                    textBoxValue = action.getValueAttribute(homePage.TextBox_Drop());
                    break;
                default:
                    throw new Exception(" UN IMPLEMENTED STEP");
            }
            testStepVerify.isTrue(
                    textBoxValue.isEmpty() || textBoxValue.equals("Set Drop Off Location")
                            || textBoxValue.equals("Set Pickup Location"),
                    actionAddress + "address bar value is empty",
                    actionAddress + "address bar value is not empty , its value is" + textBoxValue);
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

    public void i_selectlogout() {
            goToAppMenu();
            clickAppMenu("LOGOUT");
    }

    @Then("^I customers active flag should be \"([^\"]*)\"$")
    public void i_active_flag_should_be_something(String strArg1) throws Throwable {
        try {
            String phone=(String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
            Thread.sleep(5000);
            String actualActiveFlag=DbUtility.getActiveFlag(phone);
            testStepVerify.isEquals(strArg1,actualActiveFlag,"Active flag should be :"+strArg1 +" for customer : "+ phone,"Active flag is :"+actualActiveFlag +" for customer : "+ phone);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I driver active flag should be \"([^\"]*)\"$")
    public void i_driveractive_flag_should_be_something(String strArg1) throws Throwable {
        try {
            String phone=(String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
            Thread.sleep(5000);
            String actualActiveFlag=DbUtility.getDriverActiveFlag(phone);
            testStepVerify.isEquals(strArg1,actualActiveFlag,"Active flag should be :"+strArg1 +" for customer : " + phone,"Active flag is :"+actualActiveFlag+" for customer : " + phone);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Trip Information should be correctly displayed on CUSTOMER HOME screen$")
    public void trip_information_should_be_correctly_displayed_on_something_screen() {
        try {
            verifyTripInformationOnHome();
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^drop off field should be \"([^\"]*)\"$")
    public void drop_off_field_should_be_something(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "not be displayed":
                    testStepVerify.isFalse(action.isElementPresent(homePage.TextBox_Drop(true)), "Drop off field should be displayed", "Drop off field is displayed", "Drop off field is not displayed");
                    break;
                case "displayed":
                    testStepVerify.isTrue(action.isElementPresent(homePage.TextBox_Drop(true)), "Drop off field should be displayed");
                    break;
                default:
                    throw new Exception(" UN IMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^driver eta should be \"([^\"]*)\"$")
    public void driver_eta_should_be_something(String strArg1) throws Throwable {
        try {
            Thread.sleep(10000);
            switch (strArg1.toLowerCase()) {
                case "less than 30 mins":
                    String minsValue = action.getValueAttribute(homePage.Text_eta_mins());
                    int intMinValue = Integer.parseInt(minsValue.replace(" MINS", ""));
                    testStepVerify.isTrue(minsValue.contains(" MINS"), "Mins should displayed");
                    testStepVerify.isTrue(intMinValue < 31, " Mins valus should be less than 30", "Mins value is" + intMinValue, "Mins value is" + intMinValue);
                    break;
                case "not be displayed":
                    testStepVerify.isFalse(action.isElementPresent(homePage.Text_eta_mins(true)), "Driver eta should bot be displayed", "Driver ETA is not displayed", "Driver ETA is displayed");
                    break;
                default:
                    throw new Exception(" UN IMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^geofence not active message should be displayed$")
    public void geofence_not_active_message_should_be_displayed() throws Throwable {
        try {
            testStepVerify.isElementEnabled(homePage.Text_OutOfOffice(),"'Whoops! Sorry, we’re not operating here yet.' should be displayed");
            testStepVerify.isElementEnabled(homePage.Text_OutOfOffice_RequestCity(),"'Request your city' should be displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
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
            case "MY BUNGIIS":
                action.click(homePage.AppMenu_MyBungiisTrip());
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
    public String[] getSelectedPickUpLocation() {
        String[] pickUpLocation = new String[2];
        pickUpLocation[0] = homePage.TextBox_Pickup_LineOne().getAttribute("value");
        pickUpLocation[1] = homePage.TextBox_Pickup_LineTwo().getAttribute("value");

        return pickUpLocation;
    }

    /**
     * Get Pick up and drop location value
     *
     * @return value of selected pickup drop location
     */
    public String[] getPickUpAndDropLocation() {
        List<WebElement> staticFields = homePage.TextBox_AddressGeneric();
        String[] pickUpLocation = new String[5];
        if (staticFields.size() != 5)
            error("i should able to get all information from home screen", "Not able to get all information from home screen", true);
        for (int i = 0; i < 5; i++) {
            pickUpLocation[i] = staticFields.get(i).getAttribute("value");

        }
        return pickUpLocation;
    }

    /**
     * Get Drop location value
     *
     * @return value of selected drop location
     */
    public String[] getSelectedDropLocation() {
        String[] dropLocation = new String[2];
        dropLocation[0] = homePage.TextBox_Drop_LineOne().getAttribute("value");
        dropLocation[1] = homePage.TextBox_Drop_LineTwo().getAttribute("value");
        return dropLocation;
    }

    /**
     * Wait for loading to disappear
     */
    public void waitForLoadingDisappear() {
        action.invisibilityOfElementLocated(homePage.Image_Loading(true));
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
        boolean isPickupLineDisplayed = action.isElementPresent(homePage.TextBox_Pickup(true));
        if (isPickupLineDisplayed) {
            while (action.getValueAttribute(homePage.TextBox_Pickup(true)).contains("Pick")) {
                action.dragFromToForDuration(initial.x, initial.y, initial.x, initial.y + offset, 1);

                if (!action.isElementPresent(homePage.TextBox_Pickup(true))) break;
            }
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
        //VISHAL[12042019]: Quick fix for QA auto
        try {Thread.sleep(3000);}catch (Exception e){}
        if (action.isElementPresent(homePage.Button_ClearPickup(true)))
            action.click(homePage.Button_ClearPickup());
        action.clearEnterText(homePage.TextBox_Pickup(), location);
        action.click(homePage.Link_PickUpSuggestion());
        //  action.hideKeyboard();
        try {
            // wait for loading to disappear
/*            if (action.isElementPresent(homePage.Image_Loading(true))) {
                action.invisibilityOfElementLocated(homePage.Image_Loading(true));
            }*/
            Thread.sleep(3000);


            action.click(homePage.BUTTON_SET());
            if (!action.isElementPresent(homePage.TextBox_Pickup_LineTwo(true))) {
                Point initial = homePage.Image_eta_bar().getLocation();
                action.dragFromToForDuration(initial.x, initial.y, initial.x, initial.y + 80, 1);
                //action.dragFromToForDuration(82, 262, 82, 300, 2);
                action.click(homePage.BUTTON_SET());
            }
        } catch (Exception e) {
        }
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
        while (action.isElementPresent(homePage.TextBox_Drop(true))) {
            //   while (homePage.TextBox_Drop().getAttribute("value").contains("Set Drop")) {
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
