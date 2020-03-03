package com.bungii.web.stepdefinitions.driver;

import com.bungii.SetupManager;
import com.bungii.api.stepdefinitions.BungiiSteps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.driver.*;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Driver_DetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(Driver_DetailsSteps.class);
    Driver_ForgotPasswordPage Page_ForgotPassword = new Driver_ForgotPasswordPage();
    Driver_VerifyPhonePage Page_VerifyPhone = new Driver_VerifyPhonePage();
    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    Driver_DetailsPage Page_Driver_Details = new Driver_DetailsPage();
    Driver_PickUpInfoPage Page_Driver_PickupInfo = new Driver_PickUpInfoPage();
    Driver_DocumentationPage Page_Driver_Doc = new Driver_DocumentationPage();
    Driver_BankDetailsPage Page_Driver_Bank = new Driver_BankDetailsPage();
    Driver_TermsPage Page_Driver_Terms = new Driver_TermsPage();
    Driver_VideoTrainingPage Page_Driver_Video = new Driver_VideoTrainingPage();

    DriverRegistrationSteps driverRegistrationSteps = new DriverRegistrationSteps();
    Driver_ViewDetailsPage Page_Driver_ViewDetails = new Driver_ViewDetailsPage();

    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    BungiiSteps bungiiSteps = new BungiiSteps();

    @When("^I enter \"([^\"]*)\" data on Driver Details page$")
    public void i_enter_something_data_on_driver_details_page(String strArg1) throws Throwable {
        String driverImagePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),
                PropertyUtility.getImageLocations("DRIVER_IMAGE"));
        switch (strArg1) {
            case "valid":
                action.clearSendKeys(Page_Driver_Details.TextBox_StreetAddress(), PropertyUtility.getDataProperties("DriverStreet"));
                Thread.sleep(5000);
                try{
                    JavascriptExecutor executor = (JavascriptExecutor) SetupManager.getDriver();
                    executor.executeScript("arguments[0].click();", Page_Driver_Details.List_StreetAddress());
                    if(Page_Driver_Details.TextBox_City().getAttribute("value").equalsIgnoreCase(""))
                    {
                        logger.detail("Google places API limit Exhausted.. Proceeding with manually entering address details");
                        action.clearSendKeys(Page_Driver_Details.TextBox_City(), PropertyUtility.getDataProperties("DriverCity"));
                        action.selectElementByText(Page_Driver_Details.DropDown_State(), PropertyUtility.getDataProperties("DriverState"));
                        action.clearSendKeys(Page_Driver_Details.TextBox_ZipCode(), PropertyUtility.getDataProperties("DriverZipCode"));
                    }
                }
                catch(Exception ex) {
                   // action.JavaScriptClick(Page_Driver_Details.List_StreetAddress());
                  //  Thread.sleep(2000);
                    logger.detail("Google places API limit Exhausted.. Proceeding with manually entering address details");
                    action.clearSendKeys(Page_Driver_Details.TextBox_City(), PropertyUtility.getDataProperties("DriverCity"));
                    action.selectElementByText(Page_Driver_Details.DropDown_State(), PropertyUtility.getDataProperties("DriverState"));
                    action.clearSendKeys(Page_Driver_Details.TextBox_ZipCode(), PropertyUtility.getDataProperties("DriverZipCode"));
                }
                action.click(Page_Driver_Details.CheckBox_Age18());
                action.click(Page_Driver_Details.CheckBox_Lift75());
                action.click(Page_Driver_Details.CheckBox_DrivingExp());

                action.clearSendKeys(Page_Driver_Details.TextArea_Other(), PropertyUtility.getDataProperties("DriverOther"));

                action.clearSendKeys(Page_Driver_Details.TextArea_Occupation(), PropertyUtility.getDataProperties("DriverOccupation"));
                action.clearSendKeys(Page_Driver_Details.TextBox_SSN(), PropertyUtility.getDataProperties("DriverSSN"));
                action.clearSendKeys(Page_Driver_Details.TextBox_Birthday(), PropertyUtility.getDataProperties("DriverBirthday"));
                action.selectRandomDropdown(Page_Driver_Details.DropDown_Info());
                utility.addImageInDropZone(Page_Driver_Details.DropZoneHiddenFileTag_ProfileImage(), driverImagePath);

                action.click(Page_Driver_Details.Button_Crop());
                action.invisibilityOfElementLocated(Page_Driver_Details.Wrapper_Spinner());

                testStepVerify.isElementEnabled(Page_Driver_Details.Link_RemoveFile(), "driver remove link should be present", "driver remove link is present", "driver remove link is not present");


                action.click(Page_Driver_Details.CheckBox_Consent());

                break;

            case "invalid":
                action.clearSendKeys(Page_Driver_Details.TextBox_ZipCode(), PropertyUtility.getDataProperties("DriverZipCode_Invalid"));

                action.click(Page_Driver_Details.Link_ClearAll());
                action.click(Page_Driver_Details.Link_SelectAll());
                testStepVerify.isElementSelected(Page_Driver_Details.CheckBox_SunEve(), "Checkbox for sunday evening should be selected ", " Checkbox for sunday evening was selected", "Checkbox for sunday evening is not selected");
                action.click(Page_Driver_Details.Link_ClearAll());
                testStepVerify.isElementNotSelected(Page_Driver_Details.CheckBox_WedAftrn(), "Checkbox for Wed afternoon should not be selected ", " Checkbox for Wed afternoon is not selected", "Checkbox for Wed afternoon is selected");

                utility.addImageInDropZone(Page_Driver_Details.DropZoneHiddenFileTag_ProfileImage(), driverImagePath);
                action.click(Page_Driver_Details.Button_Crop());
                action.invisibilityOfElementLocated(Page_Driver_Details.Wrapper_Spinner());

                action.click(Page_Driver_Details.Link_RemoveFile());

                action.clearSendKeys(Page_Driver_Details.TextBox_SSN(), PropertyUtility.getDataProperties("DriverSSN_Invalid"));

                action.clearSendKeys(Page_Driver_Details.TextBox_Birthday(), PropertyUtility.getDataProperties("Date_Invalid"));

                break;

            default:
                break;
        }
    }

    @Then("^I should see individual field validations on \"([^\"]*)\" page$")
    public void i_should_see_individual_field_validations_on_something_page(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "driver Details":
                    testStepVerify.isElementTextEquals(Page_Driver_Details.Err_ZipCode(), PropertyUtility.getMessage("Err_DriverDetails_ZipCode"));
                    testStepVerify.isElementTextEquals(Page_Driver_Details.Err_Other(), PropertyUtility.getMessage("Err_DriverDetails_Other"));
                    testStepVerify.isElementEnabled(Page_Driver_Details.Link_DriverPicture(), "driver Picture should be displayed", "driver Picture is displayed", "driver picture was not displayed");
                    testStepVerify.isElementTextEquals(Page_Driver_Details.Err_SSN(), PropertyUtility.getMessage("Err_DriverDetails_SSN"));
                    testStepVerify.isElementTextEquals(Page_Driver_Details.Err_Birthday(), PropertyUtility.getMessage("Err_DriverDetails_Birthday"));
                    break;

                case "Pickup Information - i":
                    testStepVerify.isElementTextEquals(Page_Driver_PickupInfo.Err_AddTruckImages(), PropertyUtility.getMessage("Err_Add1MoreTruckImage"));
                    break;

                case "Pickup Information - ii":
                    testStepVerify.isElementTextEquals(Page_Driver_PickupInfo.Err_AddTruckImages(), PropertyUtility.getMessage("Err_Add2MoreTruckImage"));
                    break;

                case "date on Documentation":
                    testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_LicenseExpiry(), PropertyUtility.getMessage("Err_InvalidDate"));
                    testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_InsuranceExpiry(), PropertyUtility.getMessage("Err_InvalidDate"));
                    break;

                case "Documentation":
                    testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_LicenseExpiry(), PropertyUtility.getMessage("Err_InvalidLicenseExpiryDate"));
                    testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_InsuranceExpiry(), PropertyUtility.getMessage("Err_InvalidInsuranceExpiryDate"));
                    testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_LicenseNumber(), PropertyUtility.getMessage("Err_LicenseNumber"));
                    break;

                case "bank account on Bank Details":
                    testStepVerify.isElementTextEquals(Page_Driver_Bank.Err_BankAccNumber(), PropertyUtility.getMessage("Err_ShortBankAccount"));
                    break;

                case "Bank Details":
                    testStepVerify.isElementTextEquals(Page_Driver_Bank.Err_RoutingNumber(), PropertyUtility.getMessage("Err_InvalidRoutingNumber"));
                    testStepVerify.isElementTextEquals(Page_Driver_Bank.Err_BankAccNumber(), PropertyUtility.getMessage("Err_InvalidBankAccount"));
                    break;

                default:
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I uncheck \"([^\"]*)\" checkbox$")
    public void i_uncheck_something_checkbox(String strArg1) throws Throwable {
        if(Page_Driver_Terms.CheckBox_Agree().isSelected())
            action.click(Page_Driver_Terms.CheckBox_Agree());
    }

    @And("^I check \"([^\"]*)\" checkbox$")
    public void i_check_something_checkbox(String strArg1) throws Throwable {
        if(!Page_Driver_Terms.CheckBox_Agree().isSelected())
            action.click(Page_Driver_Terms.CheckBox_Agree());
    }
    @And("^I click Next on \"([^\"]*)\" page$")
    public void i_click_next_on_something_page(String strArg1) throws Throwable {
        switch (strArg1) {
            case "Driver Details":
                action.click(Page_Driver_Details.Button_DetailsNext());
                break;
            case "Pickup Information":
                action.click(Page_Driver_PickupInfo.Button_PickUpNext());
                break;
            case "Documentation":
                Thread.sleep(6000);
                action.click(Page_Driver_Doc.Button_DocNext());
                break;
            case "Bank Details":
                action.click(Page_Driver_Bank.Button_BankNext());
                break;
            case "Terms & Conditions":

                action.click(Page_Driver_Terms.Button_TermsNext());
                break;
            case "Video Training":
                action.click(Page_Driver_Video.Button_VideoNext());
                break;
            default:
                break;
        }
        log("I should able to click " + strArg1 + " page", "I clicked " + strArg1 + " page", true);

    }

    @Then("^I should see blank fields validation on \"([^\"]*)\" page$")
    public void i_should_see_blank_fields_validation_on_something_page(String strArg1) throws Throwable {
        switch (strArg1) {
            case "Forgot Password":
                testStepVerify.isElementTextEquals(Page_ForgotPassword.Err_ForgotPass_BlankField(), PropertyUtility.getMessage("Err_Pages_BlankFields"), PropertyUtility.getMessage("Err_Pages_BlankFields") + " should be displayed", PropertyUtility.getMessage("Err_Pages_BlankFields") + " is displayed", PropertyUtility.getMessage("Err_Pages_BlankFields") + " was not displayed");
                break;
            case "Verify your phone":
                testStepVerify.isElementTextEquals(Page_VerifyPhone.Err_VerifyPhone_BlankField(), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                break;
            case "driver Details":
                testStepVerify.isElementTextEquals(Page_Driver_Details.Err_DriverDetails_AllBlank(), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                break;
            case "Pickup Information":
                testStepVerify.isElementTextEquals(Page_Driver_PickupInfo.Err_PickupInfo_AllBlank(), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                break;
            case "Documentation":
                testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_Documentation_AllBlank(), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                break;
            case "Bank Details":
                testStepVerify.isElementTextEquals(Page_Driver_Bank.Err_BankDetails_AllBlank(), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                break;
            case "Terms & Conditions":
                testStepVerify.isElementTextEquals(Page_Driver_Terms.Err_Terms(), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                break;
            case "Video Training":
                testStepVerify.isElementTextEquals(Page_Driver_Video.Err_Video(), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                break;
            default:
                break;
        }
    }


    @And("^I update the rejected \"([^\"]*)\" field$")
    public void i_update_the_rejected_something_field(String strArg1) throws Throwable {
        driverRegistrationSteps.i_navigate_to_something("Driver Details");
        action.JavaScriptClear(Page_Driver_Details.Textbox_DriverDetails_DOB());
        action.sendKeys(Page_Driver_Details.Textbox_DriverDetails_DOB(),"01/01/1992");
        log("I update the rejected DOB field" ,
                "I have updated the rejected DOB field");    }

    @And("^I update the accepted \"([^\"]*)\" field$")
    public void i_update_the_accepted_something_field(String str){
        action.JavaScriptClear(Page_Driver_Details.Textbox_DriverDetails_SSN());
        action.sendKeys(Page_Driver_Details.Textbox_DriverDetails_SSN(),"1111111111");
        log("I update the approved SSN field" ,
                "I have updated teh approved SSN field");
    }


    @And("^I submit the updated application$")
    public void i_submit_the_updated_application() throws Throwable {
        action.click(Page_Driver_Details.Button_Submit());
        action.click(Page_Driver_Details.Button_ConfirmSubmit());
        log("I can submit the updated application" ,
                "I have submitted the updated application");
    }

    @And("^I logout of driver portal$")
    public void i_logout_of_driver_portal() throws Throwable {
        try{
            action.waitUntilIsElementExistsAndDisplayed(Page_Driver_Details.Link_Logout(),(long) 5000);
            Page_Driver_Details.Link_Logout().click();
            log("I should be logged out" ,
                    "I am logged out");
        }catch (StaleElementReferenceException e){ }

    }

    @Then("^The 'My Stats' section should be shown on the Dashboard page$")
    public void the_my_stats_section_should_be_shown_on_the_dashboard_page() throws Throwable {
        testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.Label_Header_MyStats(),"My Stats section should be displayed", "My Stats sesction is displayed","My Stats section is not displayed");
    }

    @And("^Below stats are displayed correctly$")
    public void below_stats_are_displayed_correctly( DataTable data) throws Throwable {
        List<Map<String, String>> DataList = data.asMaps();
        int i = 0;
        cucumberContextManager.setScenarioContext("TOTAL_TRIPS", Page_Driver_ViewDetails.Label_TotalTripsCount().getText());
        while (i < DataList.size()) {
            String statistics = DataList.get(i).get("Statistics").trim();
            String xpath = String.format("//p[contains(text(),'%s')]/following-sibling::h3",statistics);
            if (statistics.equals("Total Earnings per year"))
                testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.Label_Statistics_Total_Earnings_Per_Year(),statistics + " should be displayed" , statistics+ " is displayed",statistics + " is not displayed");
            else
             testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.Label_Statistics(xpath),statistics + " should be displayed",statistics+ " is displayed", statistics + " is not displayed");
            i++;
        }
    }

    @Then("^The My Stats section should be updated$")
    public void the_my_stats_section_should_be_updated() throws Throwable {
        SetupManager.getDriver().navigate().refresh();
        driverRegistrationSteps.i_click_something_on_driver_portal("LOG IN link");
        driverRegistrationSteps.i_enter_driver_phone_number_as_something_and_valid_password("8888881014");
        driverRegistrationSteps.i_click_something_on_driver_portal("LOG IN button");

        //Verify that Total Trips count is incremented by 1
       // String old_count_string = (String) cucumberContextManager.getScenarioContext("TOTAL_TRIPS");
       // int old_count = Integer.parseInt(old_count_string);
        int old_count = 0;
        int new_count = old_count + 1 ;
        String xpath = String.format("//p[contains(text(),'Total Trips')]/following-sibling::h3[contains(text(),'%s')]",new_count);
        Boolean isCountIncremented = action.waitForElement(xpath);
        testStepAssert.isTrue(isCountIncremented == true,"Total Trip count should be incremented", "Total trip count is incremented", "Total trip count is not incremented");
    }

    @When("^I click on calendar to select date range$")
    public void i_click_on_calendar_to_select_date_range() throws Throwable {
        action.click(Page_Driver_ViewDetails.Calendar_TripsDaterange());
        log("I select the date range",
                "I selected the date range ", true);
    }

    @Then("^I can select date range for one year$")
    public void i_can_select_date_range_for_one_year() throws Throwable {
        while(!Page_Driver_ViewDetails.Calendar_FromDateMonth().getText().equalsIgnoreCase("Jan 2019"))
        {
            action.click(Page_Driver_ViewDetails.Calendar_PreviousMonth());
        }
        action.click(Page_Driver_ViewDetails.Calendar_FromDate());
        while(!Page_Driver_ViewDetails.Calendar_ToDateMonth().getText().equalsIgnoreCase("Jan 2020"))
        {
            action.click(Page_Driver_ViewDetails.Calendar_NextMonth());
        }
        action.click(Page_Driver_ViewDetails.Calendar_ToDate());
        testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.Label_SelectedDateRange(),"Selected date range should be displayed","Selected date range is displayed","Selected date range is not displayed");
        action.click(Page_Driver_ViewDetails.Button_Apply());
        testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.Label_SearchResultDateRange(),"Selected date range should be displayed","Selected date range is displayed","Selected date range is not displayed");

    }
    @And("^I wait for data to synch$")
    public void i_wait_for_data_to_synch() throws Throwable {
        Thread.sleep(120000);
    }
}
