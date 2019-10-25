package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.customer.EstimateSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_BusinessUsersPage;
import com.bungii.web.pages.admin.Admin_GeofencePage;
import com.bungii.web.pages.admin.Admin_PromoterPage;
import com.bungii.web.pages.admin.Admin_ReferralSourcePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_BusinessUsersSteps extends DriverBase {
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_BusinessUsersSteps.class);
    Admin_BusinessUsersPage admin_BusinessUsersPage = new Admin_BusinessUsersPage();
    Admin_PromoterPage admin_PromoterPage = new Admin_PromoterPage();
    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();

    @And("^I enter following values in \"([^\"]*)\" fields$")
    public void i_enter_following_values_in_something_fields(String fields, DataTable data) throws Throwable {
        switch (fields) {
            case "Business Users" :
            try {
                Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
                Long now = Instant.now().toEpochMilli();
                int i = now.intValue() / 1000;
                String Name = dataMap.get("Name").trim().replace("<<UniqueNo>>", Integer.toString(i));
                String Phone = dataMap.get("Phone").trim().replace("<<UniquePhone>>", "");
                String Email = dataMap.get("Email").trim();
                if (Phone.isEmpty())
                    Phone = generatePhoneNumber();
                action.selectElementByText(admin_BusinessUsersPage.DropDown_BusinessUserIsActive(), "Active");

                action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserName(), Name);
                action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo(), Phone);
                action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserEmailAddress(), Email);
                log("I enter values on Add Business User page",
                        "I entered values on Add Business User page", true);

                cucumberContextManager.setScenarioContext("BO_NAME", Name);
                cucumberContextManager.setScenarioContext("BO_PHONE", admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo().getAttribute("value"));
                cucumberContextManager.setScenarioContext("BO_EMAIL", Email);
                cucumberContextManager.setScenarioContext("BO_STATUS", "Active");

            } catch (Exception e) {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step  Should be successful", "Error performing step,Please check logs for more details",
                        true);
            }
            break;
            case "Geofence" :
                try {
                    Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
                    Long now = Instant.now().toEpochMilli();
                    int i = now.intValue() / 1000;
                    String Primary = dataMap.get("Primary").trim();
                    String Secondary = dataMap.get("Secondary").trim();
                    String GeofenceName = dataMap.get("Geo-Name").trim().replace("-<UniqueNumber>", Integer.toString(i));
                    String GeoTimeZone = dataMap.get("Geo-TimeZone").trim();
                    String GeoStatus = dataMap.get("Geo-Status").trim();

                    action.selectElementByText(admin_GeofencePage.Dropdown_Timezone(), GeoTimeZone);
                    action.selectElementByText(admin_GeofencePage.Dropdown_Status(), GeoStatus);

                    action.sendKeys(admin_GeofencePage.TextBox_Primary(), Primary);
                    action.sendKeys(admin_GeofencePage.TextBox_Secondary(), Secondary);
                    if(!GeofenceName.equals("")) {
                        action.sendKeys(admin_GeofencePage.TextBox_GeoName(), GeofenceName);
                        cucumberContextManager.setScenarioContext("GF_GEONAME", GeofenceName);
                    }

                    log("I enter values on Geofence page",
                            "I entered values on Geofence page", true);

                    cucumberContextManager.setScenarioContext("GF_PRIMARY", Primary);
                    cucumberContextManager.setScenarioContext("GF_SECONDARY",Secondary);
                    cucumberContextManager.setScenarioContext("GF_GEOTIMEZONE", GeoTimeZone);
                    cucumberContextManager.setScenarioContext("GF_STATUS", GeoStatus);

                } catch (Exception e) {
                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step  Should be successful", "Error performing step,Please check logs for more details",
                            true);
                }

                break;
        }
    }
    @When("^I enter invalid phone number and email field$")
    public void i_enter_invalid_phone_number_and_email_field() throws Throwable {
        action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserEmailAddress(),"INVALID");
        action.sendKeys(admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo(),"INVALID");
        log("I enter invalid values on Add Business User page",
                "I entered  invalid values on Add Business User page", true);

    }
    @When("^I search by Name \"([^\"]*)\" in \"([^\"]*)\" page$")
    public void i_search_by_name_something_in_something_page(String currentdatetime, String strArg1) throws Throwable {
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        action.clearSendKeys(admin_BusinessUsersPage.TextBox_Search(),Name + Keys.ENTER);
        log("I search by name in Business User list page",
                "I searched by name in Business User list page", true);
    }

    @When("^I edit the \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_edit_the_something_and_something(String strArg1, String strArg2) throws Throwable {

       String Xpath = (String) cucumberContextManager.getScenarioContext("XPATH");

       WebElement row = SetupManager.getDriver().findElement(By.xpath(Xpath));
      //  WebElement edit = row.findElement(By.xpath("following-sibling::td/button[@id='btnEditBusinessUser']"));
        action.click(row);
        action.clearSendKeys(admin_BusinessUsersPage.TextBox_BusinessUserEmailAddress(),"krishna.hoderker@creativecapsule.com");
        cucumberContextManager.setScenarioContext("BO_EMAIL", "krishna.hoderker@creativecapsule.com");
        String Phone = (String) cucumberContextManager.getScenarioContext("BO_PHONE");
         long Newphone = Long.parseLong(Phone) + 1;
       action.clearSendKeys(admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo(),String.valueOf(Newphone));
        cucumberContextManager.setScenarioContext("BO_PHONE", admin_BusinessUsersPage.TextBox_BusinessUserPhoneNo().getAttribute("value"));

    }

    @Then("^the business user gets saved successfully and it is displayed in the \"([^\"]*)\" grid$")
    public void the_business_user_gets_saved_successfully_and_it_is_displayed_in_the_something_grid(String strArg1) throws Throwable {
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        String Phone = (String) cucumberContextManager.getScenarioContext("BO_PHONE");
        String Email = (String) cucumberContextManager.getScenarioContext("BO_EMAIL");
        String Status = (String) cucumberContextManager.getScenarioContext("BO_STATUS");

        String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td/button[@id='btnEditBusinessUser']",Name,Phone,Email,Status);
        cucumberContextManager.setScenarioContext("XPATH", Xpath );
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Business User should be listed in grid", "Business User is listed in grid","Business User is not listed in grid");
    }

    @Then("^the user \"([^\"]*)\" is displayed in the Business users grid$")
    public void the_user_something_is_displayed_in_the_business_users_grid(String currentdatetime) throws Throwable {
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        String Phone = (String) cucumberContextManager.getScenarioContext("BO_PHONE");
        String Email = (String) cucumberContextManager.getScenarioContext("BO_EMAIL");
        String Status = (String) cucumberContextManager.getScenarioContext("BO_STATUS");

        String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td/button[@id='btnEditBusinessUser']",Name,Phone,Email,Status);
        cucumberContextManager.setScenarioContext("XPATH", Xpath );
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Business User should be listed in grid", "Business User is listed in grid","Business User is not listed in grid");
    }

    @Then("^the business user gets updated successfully and it is displayed in the Business users grid$")
    public void the_business_user_gets_updated_successfully_and_it_is_displayed_in_the_business_users_grid() throws Throwable {

        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        String Phone = (String) cucumberContextManager.getScenarioContext("BO_PHONE");
        String Email = (String) cucumberContextManager.getScenarioContext("BO_EMAIL");
        String Status = (String) cucumberContextManager.getScenarioContext("BO_STATUS");

        String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td/button[@id='btnEditBusinessUser']",Name,Phone,Email,Status);
        cucumberContextManager.setScenarioContext("XPATH", Xpath );
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Business User should be listed in grid", "Business User is listed in grid","Business User is not listed in grid");


    }
    @Then("^the business user is not displayed in Bulk Trips since payment is not set$")
    public void the_business_user_is_not_displayed_in_bulk_trips_since_payment_is_not_set() throws Throwable {

        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        Select select = new Select(admin_BusinessUsersPage.DropDown_BusinessUser());

        List<WebElement> dropdown = select.getOptions();

        for (WebElement e : dropdown) {
            if(e.getText().equals(Name))
                testStepAssert.isFalse(e.getText().equals(Name),"Business user should not be listed in dropdown without payment method being set", "Business user is listed without payment method being set");
        }


    }

    @Then("^the card is added to the user \"([^\"]*)\"$")
    public void the_card_is_added_to_the_user_something(String uniqueno) throws Throwable {

        testStepAssert.isElementTextEquals(admin_BusinessUsersPage.Label_SuccessMessage(),"Payment details added successfully for Business User.","Payment details added successfully for Business User. message should be displayed" ,"Payment details added successfully for Business User. message is displayed","Payment details added successfully for Business User. message should be displayed is not displayed");

    }

    @Then("^the business user is displayed in Bulk Trips since payment is set$")
    public void the_business_user_is_displayed_in_bulk_trips_since_payment_is_set() throws Throwable {
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        Select select = new Select(admin_BusinessUsersPage.DropDown_BusinessUser());

        List<WebElement> dropdown = select.getOptions();
        boolean bit = false;
        for (WebElement e : dropdown) {
            if(e.getText().equals(Name))
                bit = true;
        }
        if(!bit)
        testStepAssert.isFalse(true,"Business user should be listed in dropdown since payment method is set", "Business user is not listed though payment method is set");

    }

    @And("^I select \"([^\"]*)\" from the \"([^\"]*)\" dropdown$")
    public void i_select_something_from_the_something_dropdown(String strArg1, String field) throws Throwable {
        String Name = null;
        switch(field) {
            case "Select Business User":
                 Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
                action.selectElementByText(admin_BusinessUsersPage.DropDown_AddBusinessUserPayment(),Name);
                log("I select element from Select Business User dropdown",
                        "I have selected element from Select Business User dropdown", true);
            break;
            case "Select Promoter":
                 Name = (String) cucumberContextManager.getScenarioContext("PROMOTER_NAME");
                action.selectElementByText(admin_PromoterPage.DropDown_SelectPromoter(),Name);
                log("I select element from Select Business User dropdown",
                        "I have selected element from Select Business User dropdown", true);
                break;
        }
    }

    @And("^I click on \"([^\"]*)\" button on \"([^\"]*)\" page$")
    public void i_click_on_something_button(String button, String page) throws Throwable {
        switch(page) {
            case "Business Users Payment":
            switch (button) {
                case "Add Payment Method":
                    action.click(admin_BusinessUsersPage.Button_RequestPayment());

                    break;
            }
            break;
            case "Bulk Trips":
                switch (button) {
                    case "Upload":
                        action.click(admin_BusinessUsersPage.Button_Upload());
                        break;
                    case "Confirm":
                        action.click(admin_BusinessUsersPage.Button_Confirm());
                        break;
                }
                break;
            case "Promoter Cards":
                    switch (button) {
                        case "Add Payment Method":
                            action.click(admin_BusinessUsersPage.Button_RequestPayment());
                            break;
                    }
                    break;
            }

        log("I select "+button+" from Business User page",
                "I have selected "+button+" from Business User page", true);

    }

    @And("^I enter following card details$")
    public void i_enter_following_card_details(DataTable data) throws Throwable {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String CardNumber = dataMap.get("Card Number").trim();
        String ExpirationDate  = dataMap.get("Expiration Date").trim();
        String CVV  = dataMap.get("CVV").trim();
        String PostalCode  = dataMap.get("Postal Code").trim();
        new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("braintree-hosted-field-postalCode")));
        String pin = SetupManager.getDriver().getPageSource();
        action.sendKeys(admin_BusinessUsersPage.TextBox_PostalCode(),PostalCode);


        SetupManager.getDriver().switchTo().defaultContent();
        new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-number")));
        action.sendKeys(admin_BusinessUsersPage.TextBox_CreditCardNumber(),CardNumber);
        SetupManager.getDriver().switchTo().defaultContent();

        new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-cvv")));
        action.sendKeys(admin_BusinessUsersPage.TextBox_CVV(),CVV);
        SetupManager.getDriver().switchTo().defaultContent();

        new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-expirationDate")));
        action.sendKeys(admin_BusinessUsersPage.TextBox_ExpirationDate(),ExpirationDate);
        SetupManager.getDriver().switchTo().defaultContent();
        log("I enter card details on Add Payment to Business user page",
                "I have entered card details on Add Payment to Business user page", true);

    }

    @And("^I click on \"([^\"]*)\" button on \"([^\"]*)\" screen$")
    public void i_click_on_something_button_on_something_screen(String button, String Screen) throws Throwable {
        switch(Screen)
        {
            case "Business User Cards":
                switch(button) {
                    case "Save":
                        action.click(admin_BusinessUsersPage.Button_PaymentSave());
                        break;
                }
                break;
            case "Promoter Cards":
                switch(button) {
                    case "Save":
                        action.click(admin_PromoterPage.Button_SavePayment());
                        break;
                }
                break;

        }
        log("I click save on Add Payment to Business user page",
                "I have clicked save on Add Payment to Business user page", true);
    }

    @When("^I select user \"([^\"]*)\"$")
    public void i_select_user_something(String uniqueno) throws Throwable {
        String Name = (String) cucumberContextManager.getScenarioContext("BO_NAME");
        action.selectElementByText(admin_BusinessUsersPage.DropDown_BusinessUser(),Name);
        log("I select "+uniqueno+" from Bulk Trips page",
                "I have selected "+uniqueno+" from Bulk Trips page", true);
    }

    @And("^I upload image to be associated with the trip$")
    public void i_upload_image_to_be_associated_with_the_trip() throws Throwable {
        String csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP1"));
        String imagefilepath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("LOADING_ITEM"));

        action.sendKeys(admin_BusinessUsersPage.Input_DataFile(),csvFile);
        action.sendKeys(admin_BusinessUsersPage.Input_ImageFile(),imagefilepath);
        log("I upload csv and image on Bulk Trips page",
                "I have uploaded csv and image on Bulk Trips page", true);

    }
    @Then("^the pickup from the csv are listed down$")
    public void the_pickup_from_the_csv_are_listed_down() throws Throwable {
        String csvFile =FileUtility.getSuiteResource(PropertyUtility.getFileLocations("csv.folder"),PropertyUtility.getCsvLocations("BULK_TRIP1"));
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            int rowIndex = 0;
            while ((line = br.readLine()) != null) {

                if(rowIndex!=0) {
                    String[] column = line.split("\",\"");
                    String[] column2 = column[1].split("\",");
                    String[] column3 = column2[1].split(",");
                    String pickupAddress = column[0].replace("\"","");
                    String dropdoffAddress = column2[0].replace("\"","");

                    String xpath = String.format("//tr/td[contains(text(),'%s')]/following-sibling::td[normalize-space(.)='']/following-sibling::td[normalize-space(.)='']/following-sibling::td[normalize-space(.)='']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']", rowIndex, pickupAddress, dropdoffAddress, column3[0]);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)), "Confim Record should be displayed", "Confim Record is displayed", "Confim Record is not displayed");
                }
                rowIndex++;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }




    }
    private String generatePhoneNumber()
    {
        Random rand = new Random();
        int num1 = 9999;
        int num2 = 700 + new Random().nextInt(999 - 700 + 1);;
        int num3 = rand.nextInt(998);

        DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
        DecimalFormat df4 = new DecimalFormat("000"); // 4 zeros

        return df3.format(num1)  + df3.format(num2)  + df4.format(num3);

    }



}
