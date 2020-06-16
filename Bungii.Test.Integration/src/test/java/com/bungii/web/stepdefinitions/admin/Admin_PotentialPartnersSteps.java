package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.*;
import com.bungii.web.pages.admin.Admin_PotentialPartnersPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Date;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;

public class Admin_PotentialPartnersSteps extends DriverBase {
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_BusinessUsersSteps.class);
    Admin_PotentialPartnersPage admin_potentialPartnersPage = new Admin_PotentialPartnersPage();

    @Then("^I wait for \"([^\"]*)\" mins$")
    public void i_wait_for_something_mins(String strArg1) throws Throwable {
        action.hardWaitWithSwipeUp(Integer.parseInt(strArg1));
    }

    @Then("^I verify that the point of interests fields are populated$")
    public void i_verify_that_the_point_of_interests_fields_are_populated() throws Throwable {
        try {
            String pointOfInterests = admin_potentialPartnersPage.Label_PointOfInterest().getText();
            System.out.println("Point Of Interests Are: " + pointOfInterests);
            if (pointOfInterests.contains("Do Not Assign Right Now") && pointOfInterests.contains("Not A Partner") && pointOfInterests.contains("Residential / Home") && pointOfInterests.contains("Others") && pointOfInterests.contains("Potential Partners")) {
                testStepAssert.isTrue(true, "The Point Of Interests are present.", "The Point Of Interests are not present.");
            } else {
                testStepAssert.isFail("The Point Of Interests are not present.");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @When("^I get the count of \"([^\"]*)\"$")
    public void i_get_the_count_of_something(String strArg1) throws Throwable {
        try {
            String countTrips = admin_potentialPartnersPage.Text_PickupsNumberInCluster().getText();
            cucumberContextManager.setScenarioContext("TRIPSINCLUSTER", countTrips);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify the field \"([^\"]*)\"$")
    public void i_verify_the_field_something(String option) throws Throwable {
        try{
            switch (option){
                case "Pickups in this Cluster":
                    String pickupsCount=(String)cucumberContextManager.getScenarioContext("TRIPSINCLUSTER");
                    String pickupsCountUnderCLusterTrip=action.getText(admin_potentialPartnersPage.Text_PickupCountUnderClusterTrips());
                    testStepAssert.isEquals(pickupsCount,pickupsCountUnderCLusterTrip,"Count expected is "+pickupsCountUnderCLusterTrip,"Expected count is displayed.",pickupsCount+" count is not displayed.");
                    break;
                default:
                    error("UnImplemented Step or incorrect option.", "UnImplemented Step");
                    break;
            }

        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I click on \"([^\"]*)\" hyperlink$")
    public void i_click_on_something_hyperlink(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "View Trips":
                    action.click(admin_potentialPartnersPage.Hyperlink_ViewTrips());
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }
    @And("^I assign driver \"([^\"]*)\" for the trip$")
    public void i_assign_driver_something_for_the_trip(String driverName) throws Throwable {
        try{
            admin_potentialPartnersPage.TextBox_DriverSearch().sendKeys(driverName);
            admin_potentialPartnersPage.Select_TestDriver().click();
            String driver1Name=admin_potentialPartnersPage.Text_EditTrpDetailsDriver1Name().getText();
            cucumberContextManager.setScenarioContext("DRIVER1_NAME",driver1Name);
            cucumberContextManager.setScenarioContext("DRIVER2_NAME",driver1Name);

        }catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify that the \"([^\"]*)\" is displayed$")
    public void i_verify_that_the_something_is_displayed(String strArg1) throws Throwable {

        try {
            switch (strArg1) {
                case "Testdrivertywd_appledc_a_web Sundarm":
                    String drivers= action.getText(admin_potentialPartnersPage.Text_DriversListScheduledTrips());
                    String driverName= (String)cucumberContextManager.getScenarioContext("DRIVER2_NAME");
                    if(drivers.contains(driverName)){
                        testStepAssert.isTrue(true, "Driver is assigned.", "Driver is not assigned.");
                    }
                    else {
                        testStepAssert.isFail("Driver is assigned.");
                    }
                    break;

                default:
                    logger.error("Method for " + strArg1 + "is not implemented ");

            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should not get alert as \"([^\"]*)\"$")
    public void i_should_not_get_alert_as_something(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Customer has ongoing trip":
                    String drivers= action.getText(admin_potentialPartnersPage.Text_DriversListScheduledTrips());
                    String driverName= (String)cucumberContextManager.getScenarioContext("DRIVER2_NAME");
                    if(drivers.contains(driverName)){
                        testStepAssert.isTrue(true, "Driver is assigned.", "Driver is not assigned.");
                    }
                    else {
                        testStepAssert.isFail("Driver is assigned.");
                    }
                    break;

                default:
                    logger.error("Method for " + strArg1 + "is not implemented ");

            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

}
