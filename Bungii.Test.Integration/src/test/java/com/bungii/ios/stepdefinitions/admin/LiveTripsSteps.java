package com.bungii.ios.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.android.pages.admin.DriversPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.pages.admin.LiveTripsPage;
import com.bungii.web.manager.ActionManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class LiveTripsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    LiveTripsPage liveTripsPage = new LiveTripsPage();
    ActionManager action = new ActionManager();
    DriversPage driversPage = new DriversPage();
    @Then("^I select trip from live trips$")
    public void i_select_trip_from_live_trips() throws Throwable {
        String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        action.sendKeys(liveTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
        action.click(liveTripsPage.Button_Search());
        Thread.sleep(5000);
        action.click(liveTripsPage.Button_StartDateSort());
        action.click(liveTripsPage.Button_RowOne());
    }

    @Then("^I select trip from trips$")
    public void i_select_trip_from_trips() throws Throwable {
        try {
            String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            action.sendKeys(liveTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
            action.click(liveTripsPage.Button_Search());
            Thread.sleep(5000);
            action.click(liveTripsPage.Button_RowOne());
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I select \"([^\"]*)\" from search peroid$")
    public void i_select_something_from_search_peroid(String strArg1) throws Throwable {
        try {
            Select dropdown = new Select(liveTripsPage.Text_SearchPeroid());
            dropdown.selectByVisibleText("The Beginning of Time");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }    }
    @Then("^I search driver from drivers$")
    public void i_select_driver_from_drivers() throws Throwable {
        try {
            String driver1Name = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            action.sendKeys(liveTripsPage.Text_SearchCriteria(), driver1Name.substring(0, driver1Name.indexOf(" ")));
            action.click(liveTripsPage.Button_Search());
            Thread.sleep(5000);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^rattings should be correctly displayed on grid$")
    public void rattings_should_be_correctly_displayed() throws Throwable {
        try {
            String currentRatting = (String) cucumberContextManager.getScenarioContext("DRIVER_CURRENT_RATTING");
            testStepVerify.isElementTextEquals(driversPage.Text_DriverRatting(),currentRatting);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^rattings should be correctly displayed on profile$")
    public void rattings_should_be_correctly_displayedProfile() throws Throwable {
        try {
            action.click(driversPage.Button_DriverProfileLink());
            String currentRatting = (String) cucumberContextManager.getScenarioContext("DRIVER_CURRENT_RATTING");
            testStepVerify.isElementTextEquals(driversPage.Text_DriverRattingProfile(),currentRatting);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^manually end bungii should be \"([^\"]*)\"$")
    public void manually_end_bungii_should_be_something(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "enabled":
                    testStepVerify.isElementEnabled(liveTripsPage.Button_ManuallyEndBungii(true), " Manually end bungii should be enabled");
                    break;
                case "disabled":
                    testStepVerify.isElementNotEnabled(liveTripsPage.Button_ManuallyEndBungii(true), " Manually end bungii should be disabled", " Manually end bungii is disabled", " Manually end bungii is enabled");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^On admin trip details page \"([^\"]*)\" should be displayed$")
    public void verifyTrip(String strArg1) throws Throwable {
        try {
            String PromoValue = String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE")).replace("-", "").replace(".00", "");
            String Promo = String.valueOf(cucumberContextManager.getScenarioContext("ADDED_PROMO_CODE"));
            String discountValue = String.valueOf(cucumberContextManager.getScenarioContext("DISCOUNT_VALUE"));
            String bungiiCostCustomer = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_COST_CUSTOMER"));

            double dblDiscountValue=Double.parseDouble((discountValue).replace("$",""));
            String truncValue = new DecimalFormat("#.00").format(dblDiscountValue);truncValue=truncValue.replace(".00", "");
            //decimal formating
            bungiiCostCustomer=new DecimalFormat("#.##").format(Double.parseDouble(bungiiCostCustomer.replace("$","")));
            bungiiCostCustomer = bungiiCostCustomer.replace(".00", "");bungiiCostCustomer="$"+bungiiCostCustomer;
            logger.detail("bungii cost to customer "+bungiiCostCustomer);
            switch (strArg1.toLowerCase()) {
                case "promo":
                    logger.detail("bungii cost to customer after replacing"+bungiiCostCustomer);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_Code(), Promo);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeType(), "Promo");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_TripPayment(), bungiiCostCustomer);
                    break;
                case "fbshare":
                    bungiiCostCustomer = bungiiCostCustomer.replace(".00", "");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_Code(), Promo);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeType(), "OneOffFBShare");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_TripPayment(), bungiiCostCustomer);
                    break;
                case "oneoff":
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_Code(), Promo);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeType(), "OneOffByAdmin");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_TripPayment(), bungiiCostCustomer);
                    break;
                case "referral":

                    testStepVerify.isElementTextEquals(liveTripsPage.Text_Code(), Promo);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeType(), "Referral");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_TripPayment(), bungiiCostCustomer);
                    break;
                case "promoter":
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_Code(), Promo);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeType(), "DeliveryChargesByPromoter");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_TripPayment(), "$0");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I wait for trip status to be \"([^\"]*)\"$")
    public void i_wait_for_trip_status_to_be_something(String strArg1) throws Throwable {
        try {
            String status = action.getText(liveTripsPage.Text_TripStatus());
            for (int i = 0; i < 5 && !status.equalsIgnoreCase(strArg1); i++) {
                Thread.sleep(30000);
                SetupManager.getDriver().navigate().refresh();
                status = action.getText(liveTripsPage.Text_TripStatus());
            }

            testStepVerify.isElementTextEquals(liveTripsPage.Text_TripStatus(), strArg1);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I click on \"([^\"]*)\" link$")
    public void i_click_on_something_link(String link) throws Throwable {
        switch(link) {
            case "Manually End Bungii":
                action.click(liveTripsPage.Link_ManuallyEndBungii());
                break;
        }
    }

    @And("^Enter the End Date and Time$")
    public void enter_the_end_date_time() throws Throwable {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY");


        String RequestTime = SetupManager.getDriver().findElement(By.xpath("//td[contains(text(),'Initial Request')]/following-sibling::td/strong")).getText();
        String[] splitedDate = RequestTime.split(" ");
        LocalDateTime now = LocalDateTime.now();
        String[] splitedTime = splitedDate[2].split(":");
        DecimalFormat formatter = new DecimalFormat("00");
        int minutes = Integer.parseInt(splitedTime[1])+20;
        int hours = Integer.parseInt(splitedTime[0]);
        if (minutes > 60) {
            hours = hours + 1;
            minutes = minutes -20;
        }

        // ZonedDateTime zonedNZ = ZonedDateTime.of(now,ZoneId.of("5:00"));
        action.clearSendKeys(liveTripsPage.Textbox_PickupEndDate(),dtf.format(now));
        action.clearSendKeys(liveTripsPage.Textbox_PickupEndTime(),formatter.format(hours)+":"+formatter.format(minutes));
        action.selectElementByText(liveTripsPage.Dropdown_ddlpickupEndTime(),splitedDate[3]);
    }

    @And("^Click on \"([^\"]*)\" button$")
    public void click_on_something_button(String button) throws Throwable {

        switch (button)
        {
            case "Calculate Cost":
                action.click(liveTripsPage.Button_CalculateCost());
                break;
            case "Confirm":
                action.click(liveTripsPage.Button_Confirm());
                break;
            case "Cancel":
                action.click(liveTripsPage.Button_Cancel());
                break;
        }

    }

    @And("^I view the Trips list on the admin portal$")
    public void i_view_the_trips_list_on_the_admin_portal() throws Throwable {
        action.click(liveTripsPage.Menu_Trips());
        SetupManager.getDriver().navigate().refresh();
        action.selectElementByText(liveTripsPage.Dropdown_SearchForPeriod(),"The Beginning of Time");
        log("I view the Trips list on the admin portal",
                "I viewed the Trips list on the admin portal", true);

    }

    @Then("^I should be able to see the respective bungii with the below status$")
    public void i_should_be_able_to_see_the_respective_bungii_with_the_below_status(DataTable data) throws Throwable {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String status = dataMap.get("Status").trim();
        String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        String tripType[] = tripTypeAndCategory.split(" ");
        String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        String geofenceName = getGeofence(geofence);
        action.selectElementByText(liveTripsPage.Dropdown_Geofence(),geofenceName);
        action.click(liveTripsPage.Button_ApplyGeofenceFilter());

        cucumberContextManager.setScenarioContext("STATUS",status);
        String driver = driver1;
        if (tripType[0].equalsIgnoreCase("duo"))
            driver = driver1 + "," + driver2;
        if (status.equalsIgnoreCase("Scheduled") ||status.equalsIgnoreCase("Searching Drivers") || status.equalsIgnoreCase("Driver Removed") || (status.equalsIgnoreCase("Admin Cancelled"))) {
            String xpath= String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[4]", tripType[0].toUpperCase(), customer);
            int retrycount =10;

            boolean retry = true;
            while (retry == true && retrycount >0) {
                try {
                    WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                    retry = false;
                } catch (Exception ex) {
                    SetupManager.getDriver().navigate().refresh();
                    action.selectElementByText(liveTripsPage.Dropdown_Geofence(),geofenceName);
                    action.click(liveTripsPage.Button_ApplyGeofenceFilter());
                    retrycount--;
                    retry = true;
                }

            }
            int retryCount = 1;
            while (!SetupManager.getDriver().findElement(By.xpath(xpath)).getText().equalsIgnoreCase(status)) {
                if (retryCount >= 20) break;
                Thread.sleep(15000); //Wait for 15 seconds
                retryCount++;
                SetupManager.getDriver().navigate().refresh();
            }
            cucumberContextManager.setScenarioContext("XPATH",xpath);
            testStepAssert.isElementTextEquals(SetupManager.getDriver().findElement(By.xpath(xpath)), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

        } else {
            String XPath= String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND")?"Solo":StringUtils.capitalize(tripType[0]), driver, customer);
            int retrycount =10;
            boolean retry = true;
            while (retry == true && retrycount >0) {
                try {
                    WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                    retry = false;
                } catch (Exception ex) {
                    SetupManager.getDriver().navigate().refresh();
                    action.selectElementByText(liveTripsPage.Dropdown_Geofence(),geofenceName);
                    action.click(liveTripsPage.Button_ApplyGeofenceFilter());
                    retrycount--;
                    retry = true;
                }

            }
            int retryCount = 1;
            while (!SetupManager.getDriver().findElement(By.xpath(XPath)).getText().equalsIgnoreCase(status)) {
                if (retryCount >= 20) break;
                Thread.sleep(15000); //Wait for 15 seconds
                retryCount++;
                SetupManager.getDriver().navigate().refresh();
            }
            cucumberContextManager.setScenarioContext("XPATH",XPath);
            testStepAssert.isElementTextEquals(SetupManager.getDriver().findElement(By.xpath(XPath)), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
        }


    }
    @When("^I view the trip details$")
    public void i_view_the_trip_details() throws Throwable {

        String xpath=  (String)cucumberContextManager.getScenarioContext("XPATH");
        action.click(SetupManager.getDriver().findElement(By.xpath(xpath)));

    }

    public String getGeofence(String geofence)
    {
        String geofenceName = "";
        switch(geofence) {
            case "washingtondc":
                geofenceName = "Washington DC";
                break;

            case "miami":
                geofenceName = "Miami";
                break;

        }
        return geofenceName;
    }
}
