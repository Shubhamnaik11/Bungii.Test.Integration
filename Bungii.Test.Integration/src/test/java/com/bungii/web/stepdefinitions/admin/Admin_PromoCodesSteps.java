package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.stepdefinitions.customer.EstimateSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_PromoCodesSteps extends DriverBase {
    Admin_PromoCodesPage admin_PromoCodesPage = new Admin_PromoCodesPage();
    Admin_BusinessUsersPage admin_BusinessUsersPage = new Admin_BusinessUsersPage();
    Admin_PromoterPage admin_PromoterPage = new Admin_PromoterPage();
    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_PromoCodesSteps.class);
    Admin_ReferralSourcePage admin_ReferralSourcePage = new Admin_ReferralSourcePage();


    @When("^I click on \"([^\"]*)\" Menu$")
    public void i_click_something_menu(String link) throws Throwable {
       switch(link) {
           case "Marketing  > Promocode" :
              action.click(admin_PromoCodesPage.Menu_Marketing());
              break;
           case "Marketing  > Referral Source" :
               action.click(admin_PromoCodesPage.Menu_Marketing());
               action.click(admin_ReferralSourcePage.Menu_ReferralSource());
               break;
           case "Business Users  > Business Users" :
               action.click(admin_BusinessUsersPage.Menu_BusinessUsers());
               break;
           case "Business Users  > Bulk Trips" :
               action.click(admin_BusinessUsersPage.Menu_BusinessUsers());
               action.click(admin_BusinessUsersPage.Menu_BulkTrips());
               break;
           case "Business Users  > Business Users Payment" :
               action.click(admin_BusinessUsersPage.Menu_BusinessUsers());
               action.click(admin_BusinessUsersPage.Menu_BusinessUsersPayment());
               break;
           case "Promotion  > Promoters" :
               action.click(admin_PromoterPage.Menu_Promotion());
               break;
           case "Promotion  > Promoter Cards" :
               action.click(admin_PromoterPage.Menu_Promotion());
               action.click(admin_PromoterPage.Menu_PromoterPayment());
               break;
           case "Geofences  > Geofences" :
               action.click(admin_GeofencePage.Menu_Geofences());
               break;
           case "Trips > Trips" :
               action.click(admin_TripsPage.Menu_Trips());

       }
        log("I click on "+link+" menu link" ,
                "I have clicked on "+link+" menu link", true);

    }

    @When("^I search by Name \"([^\"]*)\"$")
    public void i_search_by_name_something(String strArg1) throws Throwable {
        String Name = (String)cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
        action.sendKeys(admin_PromoCodesPage.TextBox_Search(),Name +Keys.ENTER);
        log("I search "+ Name + "prmocode" ,
                "I have on searched "+Name+" prmocode", true);
    }

    @When("^I search by Code \"([^\"]*)\"$")
    public void i_search_by_code_something(String strArg1) throws Throwable {

        String Code = (String) cucumberContextManager.getScenarioContext("PROMOCODE");
        action.sendKeys(admin_PromoCodesPage.TextBox_Search(), Code+Keys.ENTER);

        log("I search "+ Code + "prmocode" ,
                "I have on searched "+Code+" prmocode", true);
    }
    @When("^I search by first code generated for above promocode$")
    public void i_search_by_any_code_generated_for_above_promocode() throws Throwable {
        String LastCode = (String) cucumberContextManager.getScenarioContext("LASTCODE");
        action.sendKeys(admin_PromoCodesPage.TextBox_Search(), LastCode+Keys.ENTER);
        log("I search "+ LastCode + "prmocode" ,
                "I have on searched "+LastCode+" prmocode", true);
    }
    @Then("^the searched promocode data gets populated correctly$")
    public void the_searched_promocode_data_gets_populated_correctly() throws Throwable {

        String Count = (String) cucumberContextManager.getScenarioContext("CODE_COUNT");
        String Name = (String)cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
        String Type = (String)cucumberContextManager.getScenarioContext("PROMOCODE_TYPE");
        String Expires = (String)cucumberContextManager.getScenarioContext("EXP_DATE_VIEW");
        String Promoter = (String)cucumberContextManager.getScenarioContext("PROMOTER");
        String Promotion = (String)cucumberContextManager.getScenarioContext("PROMOTION");
        String Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
        String PromotionStartDate = (String) cucumberContextManager.getScenarioContext("PROMOTION_STARTDATE");
      //  Date PromoStartDate=new SimpleDateFormat("MM/dd/yyyy").parse(PromotionStartDate);
      //  Date ExpirationDate=new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(Expires);

        testStepAssert.isEquals(admin_PromoCodesPage.TextBox_DiscountValue().getAttribute("value"), Value, "Discount Value " +Value +" should be displayed", "Discount Value " + Value +" is displayed", "Discount Value " + Value + " is not displayed" );
        testStepAssert.isEquals(admin_PromoCodesPage.TextBox_PromoCodeName().getAttribute("value"), Name, "Promocode name "+Name+" should be displayed", "Promocode name "+Name+" is displayed", "Promocode name "+Name+" is not displayed" );
        String LastCode = (String) cucumberContextManager.getScenarioContext("LASTCODE");

        testStepAssert.isElementDisplayed(admin_PromoCodesPage.DropDown_PromoType().findElement(By.xpath(String.format("//option[@selected='selected' and text()='%s']",Type))),Type + " should be displayed",Type +" is displayed",Type + " is not displayed");
        testStepAssert.isElementDisplayed(admin_PromoCodesPage.DropDown_Promoter().findElement(By.xpath(String.format("//option[@selected='selected' and text()='%s']",Promoter))),Promoter+ " should be displayed",Promoter + " is displayed",Promoter +" is not displayed");
        testStepAssert.isElementDisplayed(admin_PromoCodesPage.DropDown_Promotion().findElement(By.xpath(String.format("//option[@data-valuetype='1' and text()='%s']",Promotion))),Promotion+ " should be displayed",Promotion + " is displayed",Promotion+ " is not displayed");


        testStepAssert.isEquals(admin_PromoCodesPage.TextBox_PromoCode().getAttribute("value"), LastCode , LastCode  +"should be displayed", LastCode  +" is displayed", LastCode  +" is not displayed" );
        testStepAssert.isEquals(admin_PromoCodesPage.TextBox_PromotionStartDate().getAttribute("value"), PromotionStartDate.toString(),  PromotionStartDate.toString()+" should be displayed",  PromotionStartDate.toString()+" is displayed",  PromotionStartDate.toString()+" is not displayed" );
        testStepAssert.isEquals(admin_PromoCodesPage.TextBox_PromotionExpirationDate().getAttribute("value"), Expires.toString(), Expires.toString()+ " should be displayed", Expires.toString()+" is displayed", Expires.toString() +" is not displayed" );

    }

    @When("^I view the searched promocode$")
    public void i_view_the_searched_promocode() throws Throwable {
       String xpath = (String) cucumberContextManager.getScenarioContext("XPath");
        action.click(SetupManager.getDriver().findElement(By.xpath(xpath)).findElement(By.xpath("following-sibling::td[1]")));
        log("I click on View link" ,
                "I have clicked on View link", true);
    }

    @When("^I click on \"([^\"]*)\" icon$")
    public void i_click_on_something_icon(String button) throws Throwable {
        switch (button)
        {
            case "Filter":
                action.click(admin_PromoCodesPage.Button_Filter());
                break;

        }
        log("I click on Filter icon" ,
                "I have clicked on Filter icon", true);
    }

    @When("^I select \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_select_something_as_something1(String CodeType, String value) throws Throwable {
        switch (CodeType)
        {
            case "Code Type":
                switch (value) {
                    case "Promo":
                        action.click(admin_PromoCodesPage.CheckBox_FilterPromo());
                        break;
                    case "Referral":
                        action.click(admin_PromoCodesPage.CheckBox_FilterReferral());
                        break;
                    case "One Off":
                        action.click(admin_PromoCodesPage.CheckBox_FilterOneOffByAdmin());
                        break;
                    case "FB Shared":
                        action.click(admin_PromoCodesPage.CheckBox_FilterOneOffFBShare());
                        break;
                    case "Delivery By Promoter":
                        action.click(admin_PromoCodesPage.CheckBox_FilterDeliveryChargesByPromoter());
                        break;
                    case "Delivery By Promoter (M)":
                        action.click(admin_PromoCodesPage.CheckBox_FilterDeliveryChargesByPromoterMultipleUse());
                        break;


                }
                break;
            case "Active":
            case "Hide Expired":
                action.click(admin_PromoCodesPage.CheckBox_FilterAll());
                action.click(admin_PromoCodesPage.CheckBox_HideExpired());
                break;
        }
        log("I select "+value+" in CodeType "+ CodeType ,
                "I have selected "+value+" in CodeType "+ CodeType, true);
    }

    @When("^I select promocode type as \"([^\"]*)\"$")
    public void i_select_promocode_type_as_something(String promoCodeType) throws Throwable {
        Thread.sleep(5000);
        action.selectElementByText(admin_PromoCodesPage.DropDown_PromoType(), promoCodeType);
        log("I select promocode type as "+ promoCodeType ,
                "I have selected promocode type as "+ promoCodeType, true);
    }

    @Then("^the \"([^\"]*)\" type promocode gets saved successfully and it is displayed in the Promocodes grid$")
    public void the_something_type_promocode_gets_saved_successfully_and_it_is_displayed_in_the_promocodes_grid(String promocodetype) throws Throwable {

        String Name = null, Type = null, CreatedDate= null, Expires = "", Code = null, Status ="Active", Value = null, Discount = null,Entered = "0", Used ="0";
        String xpath = null;
        Name = (String)cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
        Type = (String)cucumberContextManager.getScenarioContext("PROMOCODE_TYPE");
        Type = Type.replace("Delivery By Promoter (M)","Delivery Charges By Promoter Multiple Use"); ////////////////
        Date today = new Date();
        Date tomorrow = new Date(today.getTime());
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        CreatedDate = dateFormat.format(tomorrow).toString();

        switch (promocodetype)
        {
            case "Promo":


                 CreatedDate = dateFormat.format(tomorrow).toString();

                 Expires = (String)cucumberContextManager.getScenarioContext("EXP_DATE");

                 Code =(String)cucumberContextManager.getScenarioContext("PROMOCODE");;
                 Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                 Discount = (cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY").equals("Dollars") ? "$"+Value : Value+"%");
                cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY");

                 xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']",Name, CreatedDate, Expires, Code, Type, Status, Discount, Entered, Used);
                testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
                cucumberContextManager.setScenarioContext("XPath",xpath);

                break;

            case "One Off":


                 Code =(String)cucumberContextManager.getScenarioContext("PROMOCODE");;
                 Status ="Active";
                 Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                 Discount = (cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY").equals("Dollars") ? "$"+Value : Value+"%");

                cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY");

                 xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[normalize-space(.)='']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']",Name, CreatedDate, Code, Type, Status, Discount, Entered, Used);
                testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
                cucumberContextManager.setScenarioContext("XPath",xpath);

                break;
            case "Delivery By Promoter (M)":
                Code =(String)cucumberContextManager.getScenarioContext("PROMOCODE");;
                Status ="Active";
                Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                Discount = (cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY").equals("Dollars") ? "$"+Value : Value+"%");
                Expires = (String)cucumberContextManager.getScenarioContext("EXP_DATE");
                cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
                cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY");

                xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']",Name, CreatedDate, Expires, Type, Status, Discount, Entered, Used);
                testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
                cucumberContextManager.setScenarioContext("XPath",xpath);

                break;

        }
    }

    @Then("^the promocode \"([^\"]*)\" is displayed in the Promocodes grid$")
    public void the_promocode_something_is_displayed_in_the_promocodes_grid(String strArg1) throws Throwable {
        String xpath = (String)cucumberContextManager.getScenarioContext("XPath");
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");

    }

    @Then("^the \"([^\"]*)\" type 5 promocodes gets saved successfully and it is displayed in the Promocodes grid$")
    public void the_something_type_5_promocodes_gets_saved_successfully_and_it_is_displayed_in_the_promocodes_grid(String strArg1) throws Throwable {

        String Name = null, Type = null, CreatedDate= null, Expires = "", Code = null, Status ="Active", Value = null, Discount = null,Entered = "0", Used ="0";
        String xpath = null;
        String Count = (String) cucumberContextManager.getScenarioContext("CODE_COUNT");
        Name = (String)cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
        Type = (String)cucumberContextManager.getScenarioContext("PROMOCODE_TYPE");
        Expires = (String)cucumberContextManager.getScenarioContext("EXP_DATE");
        Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
        Discount = (cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY").equals("Dollars") ? "$"+Value : Value+"%");

        Date today = new Date();
        Date today1 = new Date(today.getTime());
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        CreatedDate = dateFormat.format(today1).toString();

        xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']",Name, CreatedDate, Expires, Type, Status, Discount, Entered, Used);

       List<WebElement> listOfElements = SetupManager.getDriver().findElements(By.xpath(xpath));
       testStepAssert.isEquals(String.valueOf(listOfElements.size()),Count,"No of Codes generated should be " + Count,"No of Codes generated is " + Count, "No of Codes generated is " + String.valueOf(listOfElements.size()));
        cucumberContextManager.setScenarioContext("XPath",xpath);
        String LastCode = "";
        for (int i=0; i<listOfElements.size();i++){
            WebElement element = listOfElements.get(i).findElement(By.xpath(".."));
            if(i==listOfElements.size()-1)
                LastCode = listOfElements.get(i).findElement(By.xpath("preceding-sibling::td[5]")).getText();
        }
        cucumberContextManager.setScenarioContext("LASTCODE", LastCode);


    }

    @Then("^the promocode is displayed in the Promocodes grid$")
    public void the_promocode_is_displayed_in_the_promocodes_grid() throws Throwable {
        String Name = null, Type = null, CreatedDate = null, Expires = "", Code = null, Status = "Active", Value = null, Discount = null, Entered = "0", Used = "0";
        String xpath = null;

        String Count = (String) cucumberContextManager.getScenarioContext("CODE_COUNT");
        Name = (String) cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
        Type = (String) cucumberContextManager.getScenarioContext("PROMOCODE_TYPE");
        Expires = (String) cucumberContextManager.getScenarioContext("EXP_DATE");
        Value = (String) cucumberContextManager.getScenarioContext("DISCOUNT_VALUE");
        Discount = (cucumberContextManager.getScenarioContext("DISCOUNT_CATEGORY").equals("Dollars") ? "$" + Value : Value + "%");
        Code = (String) cucumberContextManager.getScenarioContext("LASTCODE");
        Date today = new Date();
        Date today1 = new Date(today.getTime());
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        CreatedDate = dateFormat.format(today1).toString();

        xpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']", Name, CreatedDate, Expires, Code, Type, Status, Discount, Entered, Used);
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(xpath)), xpath + "Element should be displayed", xpath + "Element is displayed", xpath + "Element is not displayed");
        cucumberContextManager.setScenarioContext("XPath", xpath);
    }
    @Then("^the promocode saved data gets populated correctly$")
    public void the_saved_data_gets_populated_correctly() throws Throwable {

        testStepAssert.isElementDisplayed(admin_PromoCodesPage.DropDown_PromoType().findElement(By.xpath("//option[3][@selected='selected' and text()='Delivery By Promoter']")),"Delivery By Promoter should be displayed","Delivery By Promoter is displayed","Delivery By Promoter is not displayed");

    }

    @Then("^the \"([^\"]*)\" and \"([^\"]*)\" is set to \"([^\"]*)\" by default$")
    public void the_something_and_something_is_set_to_something_by_default(String strArg1, String strArg2, String strArg3) throws Throwable {
        testStepAssert.isTrue(admin_PromoCodesPage.CheckBox_FilterAll().isSelected()," Checkbox Code Type All should be selected","Checkbox Code Type All is selected","Checkbox Code Type All is not selected");
        testStepAssert.isTrue(admin_PromoCodesPage.CheckBox_DateFilterAll().isSelected()," Checkbox Creation Date All should be selected","Checkbox Creation Date All is selected","Checkbox Creation Date All is not selected");
    }

    @Then("^the promocode grid shows the results by type \"([^\"]*)\"$")
    public void the_promocode_grid_shows_the_results_by_type_something(String Type) throws Throwable {
        Type = Type.replace("Delivery By Promoter (M)","Delivery Charges By Promoter Multiple Use"); ////////////////
        String xpath = String.format("//tr/td[5][text()='%s']",Type);
        //page 1 records verified
        List<WebElement> rowswithtype = SetupManager.getDriver().findElements(By.xpath(xpath));
        List<WebElement> rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
        testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithtype.size()),Type + " records should be displayed",Type + " records is displayed", Type + " records is not displayed");
    }
    @Then("^the promocode grid shows the results by type \"([^\"]*)\" having Code value starting with \"([^\"]*)\"$")
    public void the_promocode_grid_shows_the_results_by_type_something_having_code_value_starting_with_something(String Type, String Value) throws Throwable {
        String xpath = String.format("//tr/td[contains(text(),'%s')]/following-sibling::td[text()='%s']",Value, Type);
        //page 1 records verified
        List<WebElement> rowswithtype = SetupManager.getDriver().findElements(By.xpath(xpath));
        List<WebElement> rows = SetupManager.getDriver().findElements(By.xpath("//tr"));

        testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithtype.size()),Type + " records should be displayed",Type + " records is displayed", Type + " records is not displayed");
    }

    @Then("^the promocode grid shows the only the \"([^\"]*)\" promocodes$")
    public void the_promocode_grid_shows_the_only_the_something_promocodes(String Value) throws Throwable {
        String xpath = String.format("//tr/td[6][contains(.,'%s')]",Value);
        //page 1 records verified
        List<WebElement> rowswithtype = SetupManager.getDriver().findElements(By.xpath(xpath));
        List<WebElement> rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
        testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithtype.size()),Value + " records should be displayed",Value + " records is displayed", Value + " records is not displayed");
    }

    @Then("^the promocode grid shows the both \"([^\"]*)\" promocodes$")
    public void the_promocode_grid_shows_the_both_something_promocodes(String strArg1) throws Throwable {


        String xpath1 = String.format("//tr/td[6][contains(.,'%s')]","Active");
        String xpath2 = String.format("//tr/td[6][contains(.,'%s')]","Expired");
        //page 1 records verified
        List<WebElement> rowswithstatusActive = SetupManager.getDriver().findElements(By.xpath(xpath1));
        List<WebElement> rowswithstatusExpired = SetupManager.getDriver().findElements(By.xpath(xpath2));
        int pageno = 2;
        while (rowswithstatusActive.size() == 0 || rowswithstatusExpired.size() == 0 ){
            action.click(SetupManager.getDriver().findElement(By.id(String.valueOf(pageno))));
            pageno=pageno+2;
            Thread.sleep(1000);
            rowswithstatusActive = SetupManager.getDriver().findElements(By.xpath(xpath1));
            rowswithstatusExpired = SetupManager.getDriver().findElements(By.xpath(xpath2));
        }
        int totalValue = rowswithstatusActive.size()+rowswithstatusExpired.size();
        List<WebElement> rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
        testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(totalValue),String.valueOf(rows.size()) + " records should be displayed",String.valueOf(rows.size()) + " records are displayed", String.valueOf(rows.size()) + " records are not displayed");

    }



    @Then("^the \"([^\"]*)\" popup gets removed from UI$")
    public void the_something_popup_gets_removed_from_ui(String popup) throws Throwable {
        switch(popup) {
            case "Add New Promocode":
                testStepAssert.isNotElementDisplayed(admin_PromoCodesPage.Button_Save(), popup + " Popup should be hidden", popup +" Popup is hidden", popup+" Popup is not hidden");
                break;

            case "Business User":
                testStepAssert.isNotElementDisplayed(admin_BusinessUsersPage.Button_Save(), popup + " Popup should be hidden", popup +" Popup is hidden", popup+" Popup is not hidden");
                break;
        }
        }

    @Then("^the \"([^\"]*)\" message is displayed$")
    public void the_something_message_is_displayed(String message) throws Throwable {

        switch(message) {
            case "Oops! It looks like you missed something. Please fill out all fields before proceeding.":
            testStepAssert.isEquals(admin_PromoCodesPage.Label_ErrorContainer().getText(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
        break;
            case "Trips have been requested successfully.":
                testStepAssert.isEquals(admin_BusinessUsersPage.Label_BulkTripSuccess().getText(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                break;
        }
    }


    @And("^I click on the \"([^\"]*)\" Button on \"([^\"]*)\" popup$")
    public void i_click_on_the_something_button_on_something_popup(String button, String popup) throws Throwable {
        switch(popup) {
            case "Add New Promocode":
            action.click(admin_PromoCodesPage.Button_Cancel());
            break;
            case "Business Users":
                switch(button) {
                    case "Cancel":
                    action.click(admin_BusinessUsersPage.Button_Cancel());
                    break;
                    case "Save":
                        action.click(admin_BusinessUsersPage.Button_Save());
                        break;
                }
                break;
            case "Add New Promoter":
                switch(button) {
                    case "Cancel":
                        action.click(admin_PromoterPage.Button_Cancel());
                        break;
                    case "Save":
                        action.click(admin_PromoterPage.Button_SavePromoter());
                        break;
                }
                break;
            case "Add Promotion":
                switch(button) {
                    case "Cancel":
                        action.click(admin_PromoterPage.Button_Cancel());
                        break;
                    case "Save":
                        action.click(admin_PromoterPage.Button_SavePromotion());
                        break;
                }
                 break;
            case "Generate Promo Code":
                switch(button) {
                    case "No":
                        action.click(admin_PromoterPage.Button_SavePromotionNo());
                        break;
                    case "Yes":
                        action.JavaScriptClick(admin_PromoterPage.Button_SavePromotionYes());
                      //  action.click(admin_PromoterPage.Button_SavePromotionYes());
                        break;
                }
                break;
        }
        log("I click on "+button+" on "+ popup ,
                "I have clicked on "+button+" on "+ popup, true);
    }
    @And("^I enter following values in fields$")
    public void i_enter_following_values_in_fields(DataTable data) throws Throwable {

        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            Long now = Instant.now().toEpochMilli();
            int i=now.intValue();
            String Code =  null,  DiscountValue = null,DiscountCategory = null, Promoter= null, Promotion= null,NoOfCodes= null;
            String PromoCodeType = dataMap.get("Promo Code Type").trim();
            String PromoCodeName = dataMap.get("Promo Code Name").trim().replace("<<CurrentDateTime>>",Integer.toString(i));
            Thread.sleep(5000);
            action.selectElementByText(admin_PromoCodesPage.DropDown_PromoType(), PromoCodeType);

            action.sendKeys(admin_PromoCodesPage.TextBox_PromoCodeName(), PromoCodeName);

            cucumberContextManager.setScenarioContext("PROMOCODE_TYPE", PromoCodeType);
            cucumberContextManager.setScenarioContext("PROMOCODE_NAME", PromoCodeName);


            switch (PromoCodeType) {
                case "Promo":
                     DiscountValue = dataMap.get("Discount Value").trim();
                     DiscountCategory = dataMap.get("Discount Category").trim();
                    Date today = new Date();
                    Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
                    DateFormat dateFormatFetch = new SimpleDateFormat("MMM dd, yyyy");
                    DateFormat dateFormatInput = new SimpleDateFormat("MM/dd/yyyy");
                    String ExpirationDate = dataMap.get("Expiration Date").trim();
                    Code = dataMap.get("Code").trim().replace("<<CurrentDateTime>>",Integer.toString(i));
                    action.sendKeys(admin_PromoCodesPage.TextBox_PromoCode(), Code);
                    cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", DiscountValue);
                    cucumberContextManager.setScenarioContext("DISCOUNT_CATEGORY", DiscountCategory);
                    cucumberContextManager.setScenarioContext("EXP_DATE", dateFormatFetch.format(tomorrow).toString());
                    cucumberContextManager.setScenarioContext("PROMOCODE", admin_PromoCodesPage.TextBox_PromoCode().getAttribute("value"));
                    action.click(admin_PromoCodesPage.TextBox_DiscountValue());
                    action.clear(admin_PromoCodesPage.TextBox_DiscountValue());
                    admin_PromoCodesPage.TextBox_DiscountValue().sendKeys(Keys.BACK_SPACE);
                    action.sendKeys(admin_PromoCodesPage.TextBox_DiscountValue(), DiscountValue);
                    action.click(admin_PromoCodesPage.RadioButton_Dollars());
                    action.sendKeys(admin_PromoCodesPage.TextBox_PromotionExpirationDate(), dateFormatInput.format(tomorrow).toString());
                    break;
                case "One Off":
                     DiscountValue = dataMap.get("Discount Value").trim();
                     DiscountCategory = dataMap.get("Discount Category").trim();
                    Code = dataMap.get("Code").trim().replace("<<CurrentDateTime>>",Integer.toString(i));
                    cucumberContextManager.setScenarioContext("EXP_DATE", "");
                    action.sendKeys(admin_PromoCodesPage.TextBox_PromoCode(), Code);
                    cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", DiscountValue);
                    cucumberContextManager.setScenarioContext("DISCOUNT_CATEGORY", DiscountCategory);
                    cucumberContextManager.setScenarioContext("PROMOCODE", admin_PromoCodesPage.TextBox_PromoCode().getAttribute("value"));
                    action.click(admin_PromoCodesPage.TextBox_DiscountValue());
                    action.clear(admin_PromoCodesPage.TextBox_DiscountValue());
                    admin_PromoCodesPage.TextBox_DiscountValue().sendKeys(Keys.BACK_SPACE);
                    action.sendKeys(admin_PromoCodesPage.TextBox_DiscountValue(), DiscountValue);
                    action.click(admin_PromoCodesPage.RadioButton_Percent());
                    break;
                case "Delivery By Promoter":
                    Promoter = dataMap.get("Select Promoter").trim();
                    Promotion = dataMap.get("Select Promotion").trim();
                    NoOfCodes = dataMap.get("No Of Codes").trim();
                    action.selectElementByText(admin_PromoCodesPage.DropDown_Promoter(), Promoter);
                    action.selectElementByText(admin_PromoCodesPage.DropDown_Promotion(), Promotion);
                    action.sendKeys(admin_PromoCodesPage.TextBox_CodeCount(), NoOfCodes);
                    DateFormat dateFormatFetch1 = new SimpleDateFormat("MMM dd, yyyy");
                    Date date = new Date(admin_PromoCodesPage.TextBox_PromotionExpirationDate().getAttribute("value"));
                    cucumberContextManager.setScenarioContext("PROMOTER", Promoter);
                    cucumberContextManager.setScenarioContext("PROMOTION", Promotion);
                    cucumberContextManager.setScenarioContext("EXP_DATE", dateFormatFetch1.format(date).toString());
                    cucumberContextManager.setScenarioContext("EXP_DATE_VIEW", admin_PromoCodesPage.TextBox_PromotionExpirationDate().getAttribute("value").toString());
                    cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", admin_PromoCodesPage.TextBox_DiscountValue().getAttribute("value"));
                    cucumberContextManager.setScenarioContext("PROMOTION_STARTDATE", admin_PromoCodesPage.TextBox_PromotionStartDate().getAttribute("value"));
                    cucumberContextManager.setScenarioContext("CODE_COUNT", NoOfCodes);
                    cucumberContextManager.setScenarioContext("DISCOUNT_CATEGORY", (admin_PromoCodesPage.RadioButton_DollarsDisabled().isSelected() ? "Dollars" : "Percent"));
                    break;
                case "Delivery By Promoter (M)":
                    Promoter = dataMap.get("Select Promoter").trim();
                    Promotion = dataMap.get("Select Promotion").trim();
                    Code = dataMap.get("Code").trim().replace("<<CurrentDateTime>>",Integer.toString(i));
                    action.selectElementByText(admin_PromoCodesPage.DropDown_Promoter(), Promoter);
                    action.selectElementByText(admin_PromoCodesPage.DropDown_Promotion(), Promotion);
                    action.sendKeys(admin_PromoCodesPage.TextBox_PromoCode(), Code);
                    DateFormat dateFormatFetch2 = new SimpleDateFormat("MMM dd, yyyy");
                    Date date2 = new Date(admin_PromoCodesPage.TextBox_PromotionExpirationDate().getAttribute("value"));
                    cucumberContextManager.setScenarioContext("PROMOTER", Promoter);
                    cucumberContextManager.setScenarioContext("PROMOTION", Promotion);
                    cucumberContextManager.setScenarioContext("EXP_DATE", dateFormatFetch2.format(date2).toString());
                    cucumberContextManager.setScenarioContext("EXP_DATE_VIEW", admin_PromoCodesPage.TextBox_PromotionExpirationDate().getAttribute("value").toString());
                    cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", admin_PromoCodesPage.TextBox_DiscountValue().getAttribute("value"));
                    cucumberContextManager.setScenarioContext("PROMOTION_STARTDATE", admin_PromoCodesPage.TextBox_PromotionStartDate().getAttribute("value"));
                    cucumberContextManager.setScenarioContext("PROMOCODE", Code);
                    cucumberContextManager.setScenarioContext("DISCOUNT_CATEGORY", (admin_PromoCodesPage.RadioButton_DollarsDisabled().isSelected() ? "Dollars" : "Percent"));

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




    @And("^I uncheck \"([^\"]*)\"$")
    public void i_uncheck_something(String strArg1) throws Throwable {
        if(admin_PromoCodesPage.CheckBox_HideExpired().isSelected())
        action.click(admin_PromoCodesPage.CheckBox_HideExpired());

        log("I uncheck Hide Expired filter" ,
                "I have unchecked Hide Expired filter" , true);
    }

    @And("^the \"([^\"]*)\" message is displayed for the \"([^\"]*)\" field$")
    public void the_something_message_is_displayed_for_the_something_field(String message, String field) throws Throwable {

        switch(field)
        {
            case "Select Promoter":
                testStepAssert.isEquals(admin_PromoCodesPage.Label_PromoterErrorContainer().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "No of Codes":
                testStepAssert.isEquals(admin_PromoCodesPage.Label_CountErrorContainer().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "Phone Number":
                testStepAssert.isEquals(admin_BusinessUsersPage.Label_ErrorContainerPhone().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
            case "Email":
                testStepAssert.isEquals(admin_BusinessUsersPage.Label_ErrorContainerEmail().getText(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
                break;
        }
    }

    @When("^I enter \"([^\"]*)\" field with below values and click Save$")
    public void i_enter_something_field_with_below_values_and_click_save(String field , DataTable data) throws Throwable {
        List<Map<String, String>> DataList = data.asMaps();
        int i = 0;
        switch (field) {
            case "No of Codes":

            while (i < DataList.size()) {
                String Value = DataList.get(i).get("Value");
                String Message = DataList.get(i).get("Message");
                i++;

                action.sendKeys(admin_PromoCodesPage.TextBox_CodeCount(), Value);
                action.click(admin_PromoCodesPage.Button_Save());
                the_corresponding_message_is_displayed_beside_the_something_field(Message,field);
                log("I enter data into No of Codes field on Add Promocode popup" ,
                        "I have entered data into No of Codes field on Add Promocode popup" , true);
            }
            break;
            case "Code Initials":

                while (i < DataList.size()) {
                    String Value = DataList.get(i).get("Value");
                    String Message = DataList.get(i).get("Message");
                    i++;

                    action.sendKeys(admin_PromoterPage.TextBox_CodeInitials(), Value);
                    action.click(admin_PromoterPage.Button_SavePromoter());
                    the_corresponding_message_is_displayed_beside_the_something_field(Message,field);
                    log("I enter data into Code Initials field on Add Promoter popup" ,
                            "I have entered data into Code Initials field on Add Promoter popup" , true);
                }
                break;
        }

    }

    @Then("^the \"([^\"]*)\" message is displayed beside the \"([^\"]*)\" field$")
    public void the_corresponding_message_is_displayed_beside_the_something_field(String message, String field) throws Throwable {
        switch (field)
        {
            case "respective":
                break;
            case "No of Codes":
          testStepAssert.isEquals(admin_PromoCodesPage.Label_CountErrorContainer().getText(),message,message +" should be displayed",message +" is displayed",message +" is not displayed");
       break;
            case "Code Initials":
                testStepAssert.isEquals(admin_PromoterPage.Label_CodeInitialsContainer().getText(),message,message +" should be displayed",message +" is displayed",message +" is not displayed");
                break;
            default:
                testStepAssert.isTrue(false, message + "should be displayed", message + " displayed", message + "did not displayed");
                break;
        }
    }


}