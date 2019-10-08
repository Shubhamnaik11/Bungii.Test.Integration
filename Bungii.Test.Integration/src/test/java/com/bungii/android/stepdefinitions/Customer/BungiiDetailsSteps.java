package com.bungii.android.stepdefinitions.Customer;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.BungiiDetailsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class BungiiDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(PromosSteps.class);
    ActionManager action = new ActionManager();
    BungiiDetailsPage bungiiDetailsPage;
    public BungiiDetailsSteps(BungiiDetailsPage bungiiDetailsPage){
        this.bungiiDetailsPage=bungiiDetailsPage;
    }
    @Then("^I Cancel selected Bungii$")
    public void i_cancel_selected_bungii() {
        try {
            Thread.sleep(5000);
            action.click(bungiiDetailsPage.Button_CancelBungii());
            action.click(bungiiDetailsPage.Button_CancelAccept());
            action.click(bungiiDetailsPage.Button_Yes());
            pass("I should able to cancel bungii","I cancelled bungii",true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
