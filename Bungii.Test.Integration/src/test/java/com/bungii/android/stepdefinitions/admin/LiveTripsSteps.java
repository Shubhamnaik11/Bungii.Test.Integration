package com.bungii.android.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.android.pages.admin.DriversPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.android.pages.admin.LiveTripsPage;
import com.bungii.android.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.support.ui.Select;

import java.text.DecimalFormat;

import static com.bungii.common.manager.ResultManager.error;

public class LiveTripsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    LiveTripsPage liveTripsPage = new LiveTripsPage();
    ActionManager action = new ActionManager();
    DriversPage driversPage = new DriversPage();

    @Then("^I select trip from live trips$")
    public void i_select_trip_from_live_trips() throws Throwable {
        try {
            String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            action.sendKeys(liveTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
            action.click(liveTripsPage.Button_Search());
            Thread.sleep(5000);
            action.click(liveTripsPage.Button_StartDateSort());
            action.click(liveTripsPage.Button_RowOne());
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
            switch (strArg1.toLowerCase()) {
                case "promo":
                    bungiiCostCustomer = bungiiCostCustomer.replace(".00", "");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_Code(), Promo);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeType(), "Promo");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue);
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_TripPayment(), bungiiCostCustomer);
                    break;
                case "fbshare":
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
                    testStepVerify.isElementTextEquals(liveTripsPage.Text_TripPayment(), bungiiCostCustomer);
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
}
