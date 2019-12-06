package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.ScheduledBungiisPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.Date;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class ScheduledBungiiSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(PromosSteps.class);
    ActionManager action = new ActionManager();
    ScheduledBungiisPage scheduledBungiisPage;
    GeneralUtility utility = new GeneralUtility();

    public ScheduledBungiiSteps(ScheduledBungiisPage scheduledBungiisPage) {
        this.scheduledBungiisPage = scheduledBungiisPage;
    }

    @Then("^Bungii must be removed from \"([^\"]*)\" screen$")
    public void bungii_must_be_removed_from_something_screen(String screen) {
        try {
            String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
    //        tripTime="Mar 14, 01:15 PM";
            Thread.sleep(20000);
            action.scrollToTop();
            boolean isBungiiPresent =isBungiiPresent(tripNoOfDriver, tripTime);
            //do half screen swipe if Bungii is present
            if(isBungiiPresent)
            {	action.scrollToTop();
                isBungiiPresent = isBungiiPresent(tripNoOfDriver, tripTime);
            }
            testStepVerify.isFalse(isBungiiPresent, "Bungii must be removed from " + screen + " screen",
                    "Bungii Must be deleted", "Bungii is not deleted");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I select already scheduled bungii$")
    public void i_select_already_scheduled_bungii() {
        try {
            String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            selectBungii(tripNoOfDriver, tripTime);
            pass("I select already scheduled bungii", "I selected already scheduled bungii of "+tripNoOfDriver+" type and at time: " + tripTime , true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    /**
     * select bungii
     * @param bungiiType
     *            identifer for bungii type
     * @param bungiiTime
     *            Scheduled bungii time
     */
    public void selectBungii(String bungiiType, String bungiiTime) {
        Date currentDate = new Date();
        int year=currentDate.getYear()+1900;
        if(!bungiiTime.contains(utility.getTimeZoneBasedOnGeofence()))
            action.click(getLocatorForBungii(bungiiType, bungiiTime.replace(",",", "+year+" -")+" " +utility.getTimeZoneBasedOnGeofence()));
        else
            action.click(getLocatorForBungii(bungiiType, bungiiTime.replace(",",", "+year+" -")));

    }


        /**
     * Check if bungii is present
     * @param bungiiType
     *            Bungii Type , Solo /Duo
     * @param bungiiTime
     *            Scheduled bungii time
     * @return
     */
    public boolean isBungiiPresent(String bungiiType, String bungiiTime) {
        try{
            return action.isElementPresent(getLocatorForBungii(bungiiType, bungiiTime));}
        catch (Exception e){
            return  false;
        }
    }
    /**
     * Construct locator for bungii from given bungii information
     *
     * @param bungiiType
     *            identifer for bungii type
     * @param bungiiTime
     *            Scheduled bungii time
     * @return
     */
    public WebElement getLocatorForBungii(String bungiiType, String bungiiTime) {
        //By Image_SelectBungii = MobileBy.xpath("//XCUIElementTypeStaticText[@name='" + bungiiTime+ "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell");

        WebElement Image_SelectBungii=scheduledBungiisPage.findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/item_my_bungii_tv_date' and @text='"+bungiiTime+"']", PageBase.LocatorType.XPath);

        return Image_SelectBungii;
    }

}
