package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.InvitePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class InviteSteps extends DriverBase {
    InvitePage invitePage = new InvitePage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    private static LogUtility logger = new LogUtility(InviteSteps.class);

    @When("^I tap \"([^\"]*)\" on Invite page$")
    public void i_tap_something_on_invite_page(String actionItem) throws Throwable {
        try {
            switch (actionItem) {
                case "Share":
                    Thread.sleep(7000);
                    action.waitUntilIsElementExistsAndDisplayed(invitePage.Button_Share());
                    action.click(invitePage.Button_Share());
                    break;
                case "Share on Facebook":
                    action.click(invitePage.Share_Facebook());
                    break;
                case "Share on Twitter":
                    action.click(invitePage.Share_Twitter());
                    break;
                case "Share by Email":
                    action.click(invitePage.Share_Email());
                    break;
                case "Share by Text Message":
                    action.click(invitePage.Share_TextMessage());
                    break;
                case "Back":
                    action.click(invitePage.Button_Back());
                    //((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log("I should able to tap on" + actionItem, "I tapped on actionItem", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }



    @Then("^I should see \"([^\"]*)\" on Invite Page$")
    public void i_should_see_something_on_invite_page(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "all elements":
                    testStepAssert.isElementDisplayed(invitePage.Header_Invite(), "Invite page header should be displayed", "Invite page is displayed", "Invite page is not displayed");
                    testStepAssert.isElementTextEquals(invitePage.Invite_Title(), PropertyUtility.getMessage("customer.invite.header"), "Invite title should be displayed ", " Invite page title is displayed correctly", "Invite page title is not correctly displayed");
                    testStepAssert.isElementTextEquals(invitePage.Invite_Text(), PropertyUtility.getMessage("customer.invite.info"), "Invite page info is correctly displayed", "Invite page info is correctly displayed", "Invite page info is not displayed correctly");
                    testStepAssert.isTrue(utility.isAlphaNumeric(action.getText(invitePage.Invite_Code())), "Invite code should be alphanumeric", "Invite code is alphanumeric", "Invite code is not alphanumeric");
                    testStepAssert.isEquals("5", String.valueOf(action.getText(invitePage.Invite_Code()).trim().length()), "Promo code should be of 5 character", "Promo code is of 5 character", "Promo code is not 5 character ");
                    testStepAssert.isElementEnabled(invitePage.Button_Share(), "Share button should be enabled", "Share button is enabled ", "Share button is not enabled");
                    break;
                case "Referral Code":
                    Thread.sleep(5000);
                    ActionManager.waitUntilIsElementExistsAndDisplayed(invitePage.Invite_Code());
                    if(action.getText(invitePage.Invite_Code()).equals(""))
                        Thread.sleep(5000);
                    cucumberContextManager.setScenarioContext("ReferralCode", action.getText(invitePage.Invite_Code()));
                    log("I should able to see referral code", " Referral Code is " + (String) cucumberContextManager.getScenarioContext("ReferralCode"), true);
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I share on \"([^\"]*)\"$")
    public void i_share_on_something(String strArg1) throws Throwable {
        try {

            switch (strArg1) {
                case "Facebook with app installed":
                    testStepAssert.isElementDisplayed(invitePage.FBApp_PostLink(true), "Overlay post button should be be displayed", "Post button is displayed", "Post button is not displayed");
                    action.sendKeys(invitePage.FBApp_StatusText(), PropertyUtility.getDataProperties("support.text"));
                    action.click(invitePage.FBApp_PostLink());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see post \"([^\"]*)\"$")
    public void i_should_see_post_something(String strArg1) throws Throwable {
        try {
            String referralCode = (String) cucumberContextManager.getScenarioContext("ReferralCode");
            String expectedText = "";
            switch (strArg1) {
                case "on text message app":
                    action.hideKeyboard();
                    expectedText = PropertyUtility.getMessage("customer.invite.sms").replace("{0}", referralCode);
                    testStepVerify.contains(action.getText(invitePage.TextMsg_TextField()), expectedText, " I should able to see properly invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");
                    break;

                case "on gmail app":
                    action.hideKeyboard();
                    testStepVerify.isElementTextEquals(invitePage.Gmail_Referral_Subject(), PropertyUtility.getMessage("customer.invite.mailsub"));
                    expectedText = PropertyUtility.getMessage("customer.invite.mailbody").replace("{0}", referralCode);
                    testStepVerify.contains(action.getText(invitePage.Gmail_Referral_Body()), expectedText, " I should able to see propert invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");
                    break;

                case "on Twitter in browser":
                    action.hideKeyboard();
                    expectedText = PropertyUtility.getMessage("customer.invite.twitter.on.browser").replace("{0}", referralCode);
                    testStepVerify.contains(action.getText(invitePage.Twitter_Referral_Body()), expectedText, " I should able to see proper invite code message on text message app", "Post is correctly displayed ", "Post is correctly is not displayed");
                    break;

                case "on Facebook app":
                    break;

                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
