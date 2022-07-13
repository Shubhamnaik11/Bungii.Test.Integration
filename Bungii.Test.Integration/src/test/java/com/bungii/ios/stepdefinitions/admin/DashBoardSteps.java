package com.bungii.ios.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.DashBoardPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.List;

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
                    action.click(dashBoardPage.Button_Deliveries());
                    break;
                case "customers":
                    action.click(dashBoardPage.Button_Customers());
                    break;
                case "drivers":
                    action.click(dashBoardPage.Button_Drivers());
                    break;
                case "geofence":
                    Thread.sleep(2000);
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
    @And("^I uncheck the Active Geofences Only$")
    public void i_uncheck_the_active_geofence_only() throws InterruptedException {
        action.click(dashBoardPage.Checkbox_Active_geofence());
        Thread.sleep(1000);
    }
    @And("^I open the trip for \"([^\"]*)\" the customer for delivery details$")
    public void i_open_the_trip_for_something_the_customer_for_delivery_details(String custName) throws Throwable {
        try{
            String[] name = custName.split(" ");

            action.clearSendKeys(dashBoardPage.Text_SearchCriteria(),name[0]);
            action.click(dashBoardPage.Button_Search());

            Thread.sleep(25000);
/*			List<WebElement> rows = scheduledTripsPage.findElements(String.format("//td/a[contains(text(),'%s')]/ancestor::tr/td/p[@id='btnEdit']",name[0]),PageBase.LocatorType.XPath);
			if(rows.size()>0)
			rows.get(0).click();
			else {
			    String xpath = String.format("//td/a[contains(text(),'%s')]/ancestor::tr/td/p[@id='btnEdit']",name[0]);
                error("I open the trip for "+custName+" customer","Not Found Bungii with XPath :" +xpath, true);
            }*/

            List<WebElement> rows_editicon = dashBoardPage.findElements(String.format("//td/a[contains(text(),'%s')]/parent::td/following-sibling::td/div/img",name[0]), PageBase.LocatorType.XPath);
            List<WebElement> rows_editlink = dashBoardPage.findElements(String.format("//td/a[contains(text(),'%s')]/ancestor::td/following-sibling::td/div/ul/li/p[contains(text(),'Delivery Details')]",name[0]),PageBase.LocatorType.XPath);

            if(rows_editicon.size()>0)
            {
                rows_editicon.get(0).click();
                rows_editlink.get(0).click();
            }

            pass("I should able to open trip", "I viewed scheduled delivery",
                    false);

            log(" I click on Delivery Details besides the scheduled bungii",
                    "I have clicked on Delivery Details besides the scheduled bungii", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I check if delivery status is \"([^\"]*)\"$")
    public void i_check_if_delivery_status_is_something(String status) throws Throwable {
        try {

            testStepAssert.isEquals(dashBoardPage.Text_BungiiStatus().getText(),status,"The status should be No Driver(s) Found","The status is No Driver(s) Found","The status is not No Driver(s) Found");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

}

