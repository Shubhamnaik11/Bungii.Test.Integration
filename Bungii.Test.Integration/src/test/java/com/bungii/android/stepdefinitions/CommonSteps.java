package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.EstimatePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;

public class CommonSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(CommonSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    EstimatePage estimatePage = new EstimatePage();

    @When("^I Switch to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_switch_to_something_application_on_something_devices(String appName, String device) {
        boolean isApplicationIsInForeground = false;

        try {
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(5000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    ((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));

                    ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));

                    //  utility.launchDriverApplication();
                    isApplicationIsInForeground = utility.isDriverApplicationOpen();
                    break;
                case "CUSTOMER":
                    //  utility.launchCustomerApplication();
                    ((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));

                    ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));

                    isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        try {
            //if switch was unsucessfull, try to switch again
            if (!isApplicationIsInForeground) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        utility.launchDriverApplication();
                        //SetupManager.getObject().launchApp(PropertyUtility.getProp("bundleId_Driver"));
                        isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        break;
                    case "CUSTOMER":
                        utility.launchCustomerApplication();
                        // SetupManager.getObject().restartApp();
                        isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        break;
                    default:
                        error("UnImplemented Step or in correct app", "UnImplemented Step");
                        break;
                }
            }
            Thread.sleep(2000);
            if (!isApplicationIsInForeground)
                warning("Switch to " + appName + " application", "Not able to currently verify if " + appName + " application was not successfull");
            else
                pass("Switch to " + appName + " application", "Switch to " + appName + " application is successful");

            //    Thread.sleep(5000);
            //     testStepVerify.isTrue(isApplicationIsInForeground, "Switch to " + appName + " application", "Switch to " + appName + " application is successful", "Switch to " + appName + " application was not successfull");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }
    //open app without restart
    @When("^I Open \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_open_to_something_application_on_something_devices(String appName, String device) {
        boolean isApplicationIsInForeground = false;

        try {
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(5000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                  //  ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));

                      utility.launchDriverApplication();
                    isApplicationIsInForeground = utility.isDriverApplicationOpen();
                    break;
                case "CUSTOMER":
                      utility.launchCustomerApplication();
                   // ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));

                    isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        try {
            //if switch was unsucessfull, try to switch again
            if (!isApplicationIsInForeground) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        utility.launchDriverApplication();
                        //SetupManager.getObject().launchApp(PropertyUtility.getProp("bundleId_Driver"));
                        isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        break;
                    case "CUSTOMER":
                        utility.launchCustomerApplication();
                        // SetupManager.getObject().restartApp();
                        isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        break;
                    default:
                        error("UnImplemented Step or in correct app", "UnImplemented Step");
                        break;
                }
            }
            Thread.sleep(2000);
            if (!isApplicationIsInForeground)
                warning("Switch to " + appName + " application", "Not able to currently verify if " + appName + " application was not successfull");
            else
                pass("Switch to " + appName + " application", "Switch to " + appName + " application is successful");

            //    Thread.sleep(5000);
            //     testStepVerify.isTrue(isApplicationIsInForeground, "Switch to " + appName + " application", "Switch to " + appName + " application is successful", "Switch to " + appName + " application was not successfull");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @Given("^I have \"([^\"]*)\" app \"([^\"]*)\"$")
    public void i_have_something_app_something(String appName, String expectedOutcome) throws Throwable {
        try {
            boolean isAppInstalled = false;
            switch (appName.toUpperCase()) {
                case "TWITTER":
                    isAppInstalled = ((AndroidDriver) (SetupManager.getDriver())).isAppInstalled(PropertyUtility.getDataProperties("twitter.bundle.id"));
                    break;
                case "FACEBOOK":
                    SetupManager.getObject().terminateApp(PropertyUtility.getDataProperties("facebook.bundle.id"));
                   // ((AndroidDriver) (SetupManager.getDriver())).terminateApp(PropertyUtility.getDataProperties("facebook.bundle.id"));
                    isAppInstalled = ((AndroidDriver) (SetupManager.getDriver())).isAppInstalled(PropertyUtility.getDataProperties("facebook.bundle.id"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            switch (expectedOutcome.toUpperCase()) {
                case "INSTALLED":
                    testStepAssert.isTrue(isAppInstalled, appName + " should be installed", appName + " is Not installed");
                    break;
                case "NOT INSTALLED":
                    testStepAssert.isFalse(isAppInstalled, appName + " should be installed", appName + " is Not installed");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    @When("^I open new \"([^\"]*)\" browser for \"([^\"]*)\"$")
    public void i_open_new_something_browser_for_something_instance(String browser, String instanceName) {
        try {

            SetupManager.getObject().createNewWebdriverInstance(instanceName, browser);
            SetupManager.getObject().useDriverInstance(instanceName);
            log(
                    "I open new " + browser + " browser for " + instanceName + " instance$",
                    "I open new " + browser + " browser for " + instanceName + " instance$", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I connect to \"([^\"]*)\" using \"([^\"]*)\" instance$")
    public void i_connect_to_something_using_something_instance(String deviceId, String instanceName) {
        try {
            SetupManager.getObject().createNewAndroidInstance(instanceName, deviceId);
            SetupManager.getObject().useDriverInstance(instanceName);
            log("I should be connected to " + deviceId,
                    "I connected to " + deviceId + " device and assigned session name " + instanceName, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I switch to \"([^\"]*)\" instance$")
    public void i_switch_to_something_instance(String instanceName) {
        try {
            SetupManager.getObject().useDriverInstance(instanceName);
            log("I switch to  " + instanceName + " instance session",
                    "I switch to  " + instanceName + "instance session", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" page should be opened$")
    public void ThenPageShouldBeOpened(String page) {
        try {
            boolean isCorrectPage = utility.isCorrectPage(page);
            testStepAssert.isTrue(isCorrectPage, page + " should be displayed", page + " is displayed correctly  ", page + " is not displayed correct");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    // TODO change catch to error
    @Then("^Alert message with (.+) text should be displayed$")
    public void alert_message_with_text_should_be_displayed(String message) {
        try {
            String actualMessage = utility.getAlertMessage();
            String expectedMessage;
            switch (message.toUpperCase()) {
                case "DRIVER CANCELLED":
                    expectedMessage = PropertyUtility.getMessage("customer.alert.driver.cancel");
                    break;
                case "TRIP CANNOT BE CANCELED AS CONTROL DRIVER NOT STARTED":
                    expectedMessage=PropertyUtility.getMessage("driver.alert.noncontrol.cancel.before.control");
                    logger.detail("PAGE SOURCE"+SetupManager.getDriver().getPageSource());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }

            testStepVerify.isEquals(actualMessage, expectedMessage,
                    "Alert with text" + expectedMessage + "should be displayed",
                    "Alert with text ," + expectedMessage + " should be displayed",
                    "Alert Message is not displayed, actual Message" + actualMessage + " Expected is "
                            + expectedMessage);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" on alert message$")
    public void i_click_something_on_alert_message(String strArg1) throws Throwable {
        try {
            if(strArg1.equalsIgnoreCase("cancel"))
                action.click(estimatePage.Button_Cancel());
            else
                action.click(estimatePage.Button_OK());


            log("I should able to click " + strArg1 + "on Alert Message",
                    "I clicked " + strArg1 + "on Alert Message", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
    @Then("^Alert should have \"([^\"]*)\" button$")
    public void alert_should_have_something_button(String list) throws Throwable {
        switch (list) {
            case "cancel,proceed":
                testStepVerify.isElementEnabled(estimatePage.Button_Cancel(true),"Cancel button should be displayed");
                testStepVerify.isElementEnabled(estimatePage.Button_Proceed(true)," Proceed button should be displayed");
                break;

            default:
                throw new Exception(" UNIMPLEMENTED STEP");
        }
    }
    @Given("^I newly installed \"([^\"]*)\" app$")
    public void i_newly_installed_something_app(String strArg1) throws Throwable {
        try {
            GeneralUtility utility = new GeneralUtility();
            utility.resetApp();
            log("I reset Cancel App Data",
                    "I reset Estimate App Data", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
