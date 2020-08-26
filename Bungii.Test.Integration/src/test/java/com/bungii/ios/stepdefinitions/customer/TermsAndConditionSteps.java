package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.EnableLocationPage;
import com.bungii.ios.pages.customer.EnableNotificationPage;
import com.bungii.ios.pages.customer.TermsAndConditionPage;
import com.bungii.ios.pages.customer.TutorialPage;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.List;

import static com.bungii.common.manager.ResultManager.*;

public class TermsAndConditionSteps extends DriverBase {
    TermsAndConditionPage termsAndConditionPage;
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(TermsAndConditionSteps.class);
    EnableNotificationPage enableNotificationPage = new EnableNotificationPage();
    EnableLocationPage enableLocationPage = new EnableLocationPage();
    TutorialPage tutorialPage = new TutorialPage();

    public TermsAndConditionSteps(TermsAndConditionPage termsAndConditionPage) {
        this.termsAndConditionPage = termsAndConditionPage;
    }

    @Then("^I accept \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" permission if exist$")
    public void i_accept_term_and_condition_agreement_and_rest(String terms, String notification, String location) {
        try {
            GeneralUtility utility = new GeneralUtility();
            Thread.sleep(3000);
            String pageHeader = utility.getPageHeader();

            if(action.isElementPresent(termsAndConditionPage.Button_CheckOff())) {
                action.click(termsAndConditionPage.Button_CheckOff());
                action.click(termsAndConditionPage.Button_Continue());
                Thread.sleep(3000);
               // pageHeader = utility.getPageHeader();
            }
            if(action.isElementPresent(enableNotificationPage.Button_Sure())) {
                action.click(enableNotificationPage.Button_Sure());
                Thread.sleep(3000);
                action.clickAlertButton("Allow");
                Thread.sleep(3000);
               // pageHeader = utility.getPageHeader();
            }
            if(action.isElementPresent(enableLocationPage.Button_Sure())) {
                action.click(enableLocationPage.Button_Sure());
                Thread.sleep(3000);
                action.clickAlertButton("Allow");  //Customer App alert for ios 12 and below
                Thread.sleep(3000);
               // pageHeader = utility.getPageHeader();
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I close \"([^\"]*)\" if exist$")
    public void i_close_tutorial_page(String Tutorial) throws Throwable {
        try {
            if(action.isElementPresent(tutorialPage.Button_Close())) {
                action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                action.click(tutorialPage.Button_Start());
                if (action.isAlertPresent()) {
                    String alertMessage = action.getAlertMessage();
                    List<String> getListOfAlertButton = action.getListOfAlertButton();
                    if (alertMessage.contains("we are not operating in your area")) {
                        if (getListOfAlertButton.contains("Done")) {
                            action.clickAlertButton("Done");
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^I accept Term and Condition agreement$")
    public void i_accept_term_and_condition_agreement() {
        try {
            action.click(termsAndConditionPage.Button_CheckOff());
            action.click(termsAndConditionPage.Button_Continue());
            pass("I accept Term and Condition agreement", "I selected terms and condition checkbox and selected Continue Button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I should see \"([^\"]*)\" on Term and Condition agreement$")
    public void i_should_see_something_on_term_and_condition_agreement(String identifier) throws Throwable {

        try {

            switch (identifier.toLowerCase()) {
                case "all details":
                    testStepVerify.isTrue(action.isElementPresent(termsAndConditionPage.Button_CheckOff(true)) && !action.isElementPresent(termsAndConditionPage.Button_CheckOn(true)),"Terms and condition checkbox should be unchecked");
                    testStepVerify.isElementEnabled(termsAndConditionPage.Text_Label(true),"'TERMS OF USE' lable should be displayed");
                    testStepVerify.isElementEnabled(termsAndConditionPage.Text_Accept(true), PropertyUtility.getMessage("customer.terms.accept")+" Should be displayed");
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

}
