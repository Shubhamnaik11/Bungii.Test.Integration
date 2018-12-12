package com.bungii.ios.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.DashBoardPage;
import cucumber.api.java.en.When;

public class DashBoardSteps extends DriverBase{
	DashBoardPage dashBoardPage;
	public DashBoardSteps( DashBoardPage dashBoardPage) {
		this.dashBoardPage = dashBoardPage;
	}
	ActionManager action = new ActionManager();
    @When("^I Select \"([^\"]*)\" from admin sidebar$")
    public void i_select_something_from_admin_sidebar(String strArg1)  {
    	switch (strArg1.toLowerCase()) {
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
		default:
			break;
		}
    }
    

}
