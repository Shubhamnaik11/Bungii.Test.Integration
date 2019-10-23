package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.stepdefinitions.customer.EstimateSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_PromoCodesPage;
import com.bungii.web.pages.admin.Admin_PromoterPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_PromoterSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(Admin_PromoterSteps.class);
    Admin_PromoterPage admin_PromoterPage = new Admin_PromoterPage();
    ActionManager action = new ActionManager();
    Admin_PromoCodesPage admin_PromoCodesPage = new Admin_PromoCodesPage();

    @And("^I enter following values in fields in \"([^\"]*)\" popup$")
    public void i_enter_following_values_in_fields_in_something_popup(String popup, DataTable data) throws Throwable {

        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            Long now = Instant.now().toEpochMilli();
            int i = now.intValue() % 1000;

            switch (popup) {
                case "Add New Promoter" :
                    String id = uniqid();
                String PromoterName = dataMap.get("Promoter Name").trim().replace("<<Unique>>", Integer.toString(i));
                String CodeInitials = dataMap.get("Code Initials").trim().replace("<<Unique>>", id);;
                String Description = dataMap.get("Description").trim();
                String Status = dataMap.get("Status").trim();

                action.sendKeys(admin_PromoterPage.TextBox_PromoterName(), PromoterName);
                action.sendKeys(admin_PromoterPage.TextBox_CodeInitials(), CodeInitials);
                action.sendKeys(admin_PromoterPage.TextBox_Discription(), Description);
                action.selectElementByText(admin_PromoterPage.DropDown_Status(), Status);

                cucumberContextManager.setScenarioContext("PROMOTER_NAME", PromoterName);
                cucumberContextManager.setScenarioContext("CODE_INITIALS", CodeInitials);
                cucumberContextManager.setScenarioContext("DESCRIPTION", Description);
                cucumberContextManager.setScenarioContext("STATUS", Status);
                break;
                case "Add Promotion" :
                    String PromotionName = dataMap.get("Promotion Name").trim().replace("<<Unique>>", Integer.toString(i));
                    String PromoStartDate = dataMap.get("Promotion Start Date").trim();
                    String PromoExpirationDate = dataMap.get("Expiration Date").trim();

                    Date today = new Date();
                    Date tomorrow = new Date(today.getTime()+ (1000 * 60 * 60 * 24));
                    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    PromoStartDate = PromoStartDate.equals("<<CurrentDateTime>>")? dateFormat.format(today).toString() : PromoStartDate;
                    PromoExpirationDate = PromoExpirationDate.contains("<<CurrentDateTime>>+1")?  dateFormat.format(tomorrow).toString(): PromoExpirationDate;

                    action.sendKeys(admin_PromoterPage.TextBox_PromotionName(), PromotionName);
                    action.sendKeys(admin_PromoterPage.TextBox_PromoStartDate(), PromoStartDate);
                    action.sendKeys(admin_PromoterPage.TextBox_PromoEndDate(), PromoExpirationDate);

                    cucumberContextManager.setScenarioContext("PROMOTION_NAME", PromotionName);
                    cucumberContextManager.setScenarioContext("PROMOTION_STARTDATE", PromoStartDate);
                    cucumberContextManager.setScenarioContext("PROMOTION_EXPDATE", PromoExpirationDate);
                    break;
            }



        log("I enter data into Add Promoter popup" ,
                "I have entered data into Add Promoter popup" , true);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @When("^I search by promoter Name \"([^\"]*)\"$")
    public void i_search_by_promoter_name_something(String strArg1) throws Throwable {
        String PromoterName =(String)cucumberContextManager.getScenarioContext("PROMOTER_NAME");;
        action.sendKeys(admin_PromoterPage.TextBox_Search(),PromoterName);
    }

    @Then("^the promoter \"([^\"]*)\" is displayed in the Promocodes grid$")
    public void the_promoter_something_is_displayed_in_the_promocodes_grid(String currentdatetime) throws Throwable {
        String PromoterName =(String)cucumberContextManager.getScenarioContext("PROMOTER_NAME");;
        String CodeInitials = (String) cucumberContextManager.getScenarioContext("CODE_INITIALS");
        String Description = (String) cucumberContextManager.getScenarioContext("DESCRIPTION");
        String Status = (String)cucumberContextManager.getScenarioContext("STATUS");

        String xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/span[text()='%s']",PromoterName, CodeInitials, Description, Status);
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
        cucumberContextManager.setScenarioContext("XPath",xpath);
    }



    @When("^I view Details of promoter Name \"([^\"]*)\"$")
    public void i_view_details_of_promoter_name_something(String currentdatetime) throws Throwable {


        String xpath = (String) cucumberContextManager.getScenarioContext("XPath");
        action.click(SetupManager.getDriver().findElement(By.xpath(xpath)).findElement(By.xpath("parent::td/following-sibling::td/button[@id='btnEditPromotionDetails']")));
        log("I click on Details link" ,
                "I have clicked on Details link", true);


    }

    @Then("^the promoter \"([^\"]*)\" gets saved successfully and it is displayed in the Promoters grid$")
    public void the_promoter_something_gets_saved_successfully_and_it_is_displayed_in_the_promoters_grid(String currentdatetime) throws Throwable {

        String PromoterName =(String)cucumberContextManager.getScenarioContext("PROMOTER_NAME");;
        String CodeInitials = (String) cucumberContextManager.getScenarioContext("CODE_INITIALS");
        String Description = (String) cucumberContextManager.getScenarioContext("DESCRIPTION");
        String Status = (String)cucumberContextManager.getScenarioContext("STATUS");
        i_search_by_promoter_name_something(PromoterName);
        String xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/span[text()='%s']",PromoterName, CodeInitials, Description, Status);
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
        cucumberContextManager.setScenarioContext("XPath",xpath);
    }

    @And("^I click on \"([^\"]*)\" button on the \"([^\"]*)\" page$")
    public void i_click_on_something_button_on_the_something_page(String button, String page) throws Throwable {

        switch (page)
        {
            case "Promotions":

                switch (button) {
                    case "New Promotion":
                        action.click(admin_PromoterPage.Button_NewPromotion());
                        break;
                }
                break;
        }
    }

    @Then("^the \"([^\"]*)\" is displayed$")
    public void the_something_is_displayed(String message) throws Throwable {
        testStepAssert.isElementDisplayed(admin_PromoterPage.Label_GeneratePromocodeMessageContainer(), message +" should be displayed", message +" is displayed", message +" is not displayed");


    }

    @And("^I enter following values in fields on \"([^\"]*)\" popup$")
    public void i_enter_following_values_in_fields_on_something_popup(String popup, DataTable data) throws Throwable {
        try {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        Long now = Instant.now().toEpochMilli();
        int i = now.intValue();
        switch(popup) {
            case "Promo Code":
                String PromocodeName = dataMap.get("Promo Code Name").trim().replace("<<Unique>>",Integer.toString(i));
                String  NoOfCodes = dataMap.get("No Of Codes").trim();
                action.sendKeys(admin_PromoCodesPage.TextBox_PromoCodeName(), PromocodeName);
                action.sendKeys(admin_PromoCodesPage.TextBox_CodeCount(), NoOfCodes);
                cucumberContextManager.setScenarioContext("PROMOCODE_NAME", PromocodeName);
                cucumberContextManager.setScenarioContext("CODE_COUNT", NoOfCodes);
                cucumberContextManager.setScenarioContext("PROMOCODE_TYPE", action.getFirstSelectedOption(admin_PromoCodesPage.DropDown_PromoType()));
                cucumberContextManager.setScenarioContext("PROMOTER", action.getFirstSelectedOption(admin_PromoCodesPage.DropDown_Promoter()));
                cucumberContextManager.setScenarioContext("PROMOTION", action.getFirstSelectedOption(admin_PromoCodesPage.DropDown_Promotion()));
                String StartDate = (String) cucumberContextManager.getScenarioContext("PROMOTION_STARTDATE");
                String EndDate = (String) cucumberContextManager.getScenarioContext("PROMOTION_EXPDATE");
                DateFormat dateFormatFetch = new SimpleDateFormat("MMM dd, yyyy");
                Date date = new Date(EndDate);

                testStepAssert.isEquals(admin_PromoCodesPage.TextBox_PromotionStartDate().getAttribute("value"),StartDate, "Promo start date should match", "Promo start date is matching", "Promo start date doesnt match");

                cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", admin_PromoCodesPage.TextBox_DiscountValue().getAttribute("value"));
                cucumberContextManager.setScenarioContext("DISCOUNT_CATEGORY", (admin_PromoCodesPage.RadioButton_DollarsDisabled().isSelected() ? "Dollars" : "Percent"));
                cucumberContextManager.setScenarioContext("EXP_DATE", dateFormatFetch.format(date).toString());

                break;
        }
            log("I enter data into Add Promocode popup" ,
                    "I have entered data into Add Promocode popup" , true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I enter following card details on \"([^\"]*)\" screen$")
    public void i_enter_following_card_details_on_something_screen(String page, DataTable data) throws Throwable {
        switch (page) {
            case "Promoter Cards":
                Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
                String CardNumber = dataMap.get("Card Number").trim();
                String ExpirationDate  = dataMap.get("Expiration Date").trim();
                String CVV  = dataMap.get("CVV").trim();
                String PostalCode  = dataMap.get("Postal Code").trim();
                new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("braintree-hosted-field-postalCode")));
                String pin = SetupManager.getDriver().getPageSource();
                action.sendKeys(admin_PromoterPage.TextBox_PostalCode(),PostalCode);


                SetupManager.getDriver().switchTo().defaultContent();
                new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-number")));
                action.sendKeys(admin_PromoterPage.TextBox_CreditCardNumber(),CardNumber);
                SetupManager.getDriver().switchTo().defaultContent();

                new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-cvv")));
                action.sendKeys(admin_PromoterPage.TextBox_CVV(),CVV);
                SetupManager.getDriver().switchTo().defaultContent();

                new WebDriverWait(SetupManager.getDriver(), 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("braintree-hosted-field-expirationDate")));
                action.sendKeys(admin_PromoterPage.TextBox_ExpirationDate(),ExpirationDate);
                SetupManager.getDriver().switchTo().defaultContent();
                log("I enter card details on Add Payment to "+page + " page",
                        "I have entered card details on Add Payment to Business user page", true);


                break;
        }

    }

    @Then("^the card is added to the promoter \"([^\"]*)\"$")
    public void the_card_is_added_to_the_promoter_something(String currentdatetime) throws Throwable {

        testStepAssert.isElementTextEquals(admin_PromoterPage.Label_SuccessMessage(),"Payment details added successfully for Promoter.","Payment details added successfully for Promoter. message should be displayed" ,"Payment details added successfully for Promoter. message is displayed","Payment details added successfully for Business User. message should be displayed is not displayed");

    }
    private String uniqid() {
        Random random = new Random();
        String tag = Long.toString(Math.abs(random.nextLong()), 36);
        return tag.substring(0, 3).toUpperCase();
    }
}
