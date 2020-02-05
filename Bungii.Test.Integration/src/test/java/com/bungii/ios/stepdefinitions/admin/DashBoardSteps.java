package com.bungii.ios.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.DashBoardPage;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.common.manager.ResultManager.error;

public class DashBoardSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    DashBoardPage dashBoardPage;
    ActionManager action = new ActionManager();

    public DashBoardSteps(DashBoardPage dashBoardPage) {
        this.dashBoardPage = dashBoardPage;
    }

    @When("^I Select \"([^\"]*)\" from admin sidebar$")
    public void i_select_something_from_admin_sidebar(String option) {
        try {
            switch (option.toLowerCase()) {
                case "scheduled trip":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_ScheduledTrips());
                    break;
                case "promo code":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_PromoCode());
                    break;
                case "referral source":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_ReferralSource());
                    break;
                case "live trips":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_LiveTrips());
                    break;
                case "trips":
                    action.click(dashBoardPage.Button_Trips());
                    break;
                case "customers":
                    action.click(dashBoardPage.Button_Customers());
                    break;
                case "drivers":
                    action.click(dashBoardPage.Button_Drivers());
                    break;
                case "Geofence":
                    action.click(dashBoardPage.Menu_Geofences());
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
