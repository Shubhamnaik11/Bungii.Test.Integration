package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.*;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;
import com.bungii.android.pages.driver.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TermsAndConditionSteps extends DriverBase {
    TermsPage termsAndConditionPage;
    ActionManager action = new ActionManager();
    DriverHomePage driverHomePage=new DriverHomePage();
    HomePage homePage=new HomePage();
    private static LogUtility logger = new LogUtility(TermsAndConditionSteps.class);

    public TermsAndConditionSteps(TermsPage termsAndConditionPage) {
        this.termsAndConditionPage = termsAndConditionPage;
    }

    @Then("^I accept Term and Condition agreement$")
    public void i_accept_term_and_condition_agreement() {
        try {
            action.click(termsAndConditionPage.Checkbox_Agree());
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
                    testStepVerify.isTrue(action.getAttribute(termsAndConditionPage.Checkbox_Agree(),"checked").equalsIgnoreCase("false"),"Terms and condition checkbox should be unchecked");
                    testStepVerify.isElementEnabled(termsAndConditionPage.Text_Label(true),"'TERMS OF USE' lable should be displayed");
                    testStepVerify.isElementTextEquals(termsAndConditionPage.Text_Accept(),PropertyUtility.getMessage("customer.terms.accept"));
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

    @Then("^I accept \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" permission if exist$")
    public void i_accept_term_and_condition_agreement_and_rest(String terms, String notification, String location) {
        try {
            GeneralUtility utility = new GeneralUtility();
            Thread.sleep(3000);
            String pageHeader = utility.getPageHeader();

            if(action.isElementPresent(termsAndConditionPage.Checkbox_Agree())) {
                action.click(termsAndConditionPage.Checkbox_Agree());
                action.click(termsAndConditionPage.Button_Continue());
                Thread.sleep(3000);
                // pageHeader = utility.getPageHeader();
            }
            if(action.isElementPresent(driverHomePage.Button_Sure(true))) {
                action.click(driverHomePage.Button_Sure(true));
                Thread.sleep(3000);
                action.clickAlertButton("Allow");
                Thread.sleep(3000);
                // pageHeader = utility.getPageHeader();
            }
            if(action.isElementPresent(driverHomePage.Button_Sure(true))) {
                action.click(driverHomePage.Button_Sure(true));
                Thread.sleep(3000);
                action.clickAlertButton("Allow");  //Customer App alert for ios 12 and below
                Thread.sleep(3000);
                // pageHeader = utility.getPageHeader();
            }

        } catch (Exception e) {
        }
    }

    @Then("^I close \"([^\"]*)\" if exist$")
    public void i_close_tutorial_page(String Tutorial) throws Throwable {
        try {
            if(action.isElementPresent(homePage.Button_Closetutorials())) {
                List<WebElement> xpath = homePage.Button_PdfPages();
                int xpathCount = xpath.size();
                boolean isClicked = false, isSwiped = false;
                for (WebElement tutorialPage : xpath) {
                    action.click(tutorialPage);
                    isClicked = true;
                }
                testStepAssert.isTrue(isClicked, "5 pages are present.", "5 pages are not present.");
                action.click(homePage.Text_TutorialPdfPage1());
                for (int i = 0; i < xpathCount - 1; i++) {
                    action.swipeLeft(homePage.Text_TutorialPdf());
                    isSwiped = true;
                }

                testStepAssert.isTrue(isSwiped, "Swiped through the pages.", "Couldn't swipe through the pages.");
                    action.click(homePage.Button_Closetutorials());
            }

        } catch (Exception e) {
        }

    }

}
