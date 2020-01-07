package com.bungii.ios.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.pages.admin.LiveTripsPage;
import com.bungii.web.manager.ActionManager;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class LiveTripsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    LiveTripsPage liveTripsPage = new LiveTripsPage();
    ActionManager action = new ActionManager();
    @Then("^I select trip from live trips$")
    public void i_select_trip_from_live_trips() throws Throwable {
        String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");


        action.sendKeys(liveTripsPage.Text_SearchCriteria(),custName.substring(0,custName.indexOf(" ")));
        action.click(liveTripsPage.Button_Search());Thread.sleep(5000);
        action.click(liveTripsPage.Button_StartDateSort());
        action.click(liveTripsPage.Button_RowOne());
    }
    @Then("^manually end bungii should be \"([^\"]*)\"$")
    public void manually_end_bungii_should_be_something(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "enabled":
                    testStepVerify.isElementEnabled(liveTripsPage.Button_ManuallyEndBungii(true)," Manually end bungii should be enabled");
                    break;
                case "disabled":
                    testStepVerify.isElementNotEnabled(liveTripsPage.Button_ManuallyEndBungii(true)," Manually end bungii should be disabled"," Manually end bungii is disabled"," Manually end bungii is enabled");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }    }

    @Then("^I wait for trip status to be \"([^\"]*)\"$")
    public void i_wait_for_trip_status_to_be_something(String strArg1) throws Throwable {
        try {
            String status=action.getText(liveTripsPage.Text_TripStatus());
            for (int i = 0; i < 5 && !status.equalsIgnoreCase(strArg1)  ; i++) {
                Thread.sleep(30000);
                SetupManager.getDriver().navigate().refresh();
                status=action.getText(liveTripsPage.Text_TripStatus());
            }

            testStepVerify.isElementTextEquals(liveTripsPage.Text_TripStatus(),strArg1);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }     }
}
