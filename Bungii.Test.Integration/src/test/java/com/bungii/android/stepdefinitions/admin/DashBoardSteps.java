package com.bungii.android.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class DashBoardSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.android.stepdefinitions.admin.DashBoardSteps.class);
    DashBoardPage dashBoardPage=new DashBoardPage();
    ActionManager action = new ActionManager();
    LiveTripsPage liveTripsPage=new LiveTripsPage();

    @When("^I Select \"([^\"]*)\" from admin sidebar$")
    public void i_select_something_from_admin_sidebar(String option) {
        try {
            switch (option.toLowerCase()) {
                case "scheduled trip":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_ScheduledTrips());
                    break;
                case "live trips":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_LiveTrips());
                    break;
                case "promo code":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_PromoCode());
                    break;
                case "referral source":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_ReferralSource());
                    break;
                case "customers":
                    action.click(dashBoardPage.Button_Customers());
                    break;
                case "trips":
                    action.click(dashBoardPage.Button_Trips());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should able to select "+option,"I Selected "+option+" on admin sidebar" ,true );
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

}
