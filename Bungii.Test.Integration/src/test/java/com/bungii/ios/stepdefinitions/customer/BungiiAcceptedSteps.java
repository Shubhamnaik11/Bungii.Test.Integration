package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.BungiiAcceptedPage;
import cucumber.api.java.en.Then;

public class BungiiAcceptedSteps extends DriverBase {
    BungiiAcceptedPage bungiiAcceptedPage;
    ActionManager action = new ActionManager();

    public  BungiiAcceptedSteps(BungiiAcceptedPage bungiiAcceptedPage){
        this.bungiiAcceptedPage=bungiiAcceptedPage;
    }


    @Then("^correct details should do be displayed on (.+) screen for Stack screen$")
    public void correct_details_should_do_be_displayed_on_bungii_accepted_screen_for_stack_screen(String key)  {
        switch (key.trim()){
            case"BUNGII ACCEPTED":
                String driverName=(String)cucumberContextManager.getScenarioContext("DRIVER_1");
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Label_DriverName(),driverName.substring(0, driverName.indexOf(" ") + 2));
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Text_StackInfo(), PropertyUtility.getMessage("customer.stack.accepted.info"));
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Text_BungiiAccepted(), PropertyUtility.getMessage("customer.bungii.accepted"));
                testStepVerify.isElementDisplayed(bungiiAcceptedPage.Image_RattingBar(),"Ratting bar should be displayed","Ratting bar is displayed","Ratting bar is not displayed");
                break;
            case"BUNGII ACCEPTED with arrival time":
                String driver1Name=(String)cucumberContextManager.getScenarioContext("DRIVER_1");
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Textlabel_DriverNearby(),PropertyUtility.getMessage("customer.stack.driver.neighborhood").replace("<DRIVER_NAME>",driver1Name.substring(0, driver1Name.indexOf(" ") + 2)));
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Textlabel_StackSubtitle(),PropertyUtility.getMessage("customer.stack.driver.subtitle.ios"));
                testStepVerify.isElementEnabled(bungiiAcceptedPage.Textlabel_ProjectedTime(),"Projected driver arrival time label should be displayed");
                testStepVerify.isElementEnabled(bungiiAcceptedPage.Button_CancelBungii(),"Cancel Bungii should be displayed");
                //validate stack arrival value
                //Textlabel_ProjectedTimeValue
                break;
        }


    }
}


