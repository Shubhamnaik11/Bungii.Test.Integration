package com.bungii.web.stepdefinitions.driver;

import com.bungii.api.stepdefinitions.BungiiSteps;
import com.bungii.api.utilityFunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.driver.Driver_RegistrationPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.log;

public class Driver_LoginSteps extends DriverBase {

    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    Driver_DashboardPage Page_Driver_Dashboard = new Driver_DashboardPage();
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    DriverRegistrationSteps driverRegistrationSteps = new DriverRegistrationSteps();
    BungiiSteps bungiiSteps = new BungiiSteps();
    ActionManager action = new ActionManager();
    AuthServices authServices = new AuthServices();
    CoreServices coreServices = new CoreServices();

    @When("^I enter \"([^\"]*)\" driver Phone Number on Driver portal$")
    public void WhenIEnterDriverPhoneNumberOnDriverPortal(String p0)
    {
        switch (p0)
        {
            case "valid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), PropertyUtility.getDataProperties("DriverPhoneNumber"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), PropertyUtility.getDataProperties("Invalid_DriverPhoneNumber"));
                break;
            default: break;
        }
        log("I should able to enter "+p0+" driver phone number on Driver portal","I entered "+p0 +" driver phone number on Driver portal", true);
    }

    @And("^I enter \"([^\"]*)\" driver Password on Driver portal$")
    public void WhenIEnterDriverPasswordOnDriverPortal(String p0)
    {
        switch (p0)
        {
            case "valid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("DriverPassword"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("Invalid_DriverPassword"));
                break;
            default: break;
        }
        log("I should able to enter "+p0+" driver Password on Driver portal","I entered "+p0 +" driver Password on Driver portal", true);

    }

    @Then("^the driver should \"([^\"]*)\"$")
    public void ThenTheDriverShould(String p0)
    {
        switch (p0)
        {
            case "be logged in":
        //        testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.Header_Dashboard()), PropertyUtility.getMessage("DriverDashboardHeader"));
                testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.SideNavigationSetting()), PropertyUtility.getMessage("DriverHomeSetting"));
                testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.SideNavigationGeneral()), PropertyUtility.getMessage("DriverHomeGENERAL"));
                break;
            case "see validation message for blank fields":
                testStepVerify.isEquals(action.getText(Page_Driver_Login.Err_DriverLogin_Blank()), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                break;
            case "see validation message for invalid phone field":
                testStepVerify.isEquals(action.getText(Page_Driver_Login.Err_DriverLogin_Phone()), PropertyUtility.getMessage("Err_DriverLogin_Phone"));
                break;
            case "see validation message for incorrect credentials":
                testStepVerify.isEquals(action.getText(Page_Driver_Login.Err_DriverLogin_FieldValidation()), PropertyUtility.getMessage("Err_DriverLogin_IncorrectCredentials"));
                break;
            default: break;
        }
    }

    @And("^I login to the driver portal as driver \"([^\"]*)\"$")
    public void i_login_to_the_driver_portal_as_driver_something(String strArg1) throws Throwable {
        String phone = bungiiSteps.getDriverPhone(strArg1);
        driverRegistrationSteps.i_navigate_to_something("Bungii Driver URL");
        driverRegistrationSteps.i_click_something_on_driver_portal("LOG IN link");
        driverRegistrationSteps.i_enter_driver_phone_number_as_something_and_valid_password(phone);
        driverRegistrationSteps.i_click_something_on_driver_portal("LOG IN button");
    }

    @Given("^I Login as a driver with below phone numbers and Make then online$")
    public void i_login_as_a_driver_with_below_phone_numbers_and_make_then_online(DataTable data) throws Throwable {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String phoneNumber = dataMap.get("PhoneNumber").trim();

        String driverAccessToken = authServices.getDriverToken("1", phoneNumber, "cci12345");
      //  coreServices.updateDriverLocation(driverAccessToken, geofence);
        coreServices.updateDriverStatus(driverAccessToken);




    }
}
