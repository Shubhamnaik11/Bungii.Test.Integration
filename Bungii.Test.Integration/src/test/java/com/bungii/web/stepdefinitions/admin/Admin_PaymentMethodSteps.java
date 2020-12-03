package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_PaymentMethodsPage;
import cucumber.api.java.en.Then;
import org.joda.time.DateTime;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin_PaymentMethodSteps extends DriverBase {

        ActionManager action = new ActionManager();
        private static LogUtility logger = new LogUtility(Admin_PaymentMethodSteps.class);

        Admin_PaymentMethodsPage admin_paymentMethodsPage = new Admin_PaymentMethodsPage();

        @Then("^The \"([^\"]*)\" gets saved successfully and it is displayed in the grid$")
        public void the_something_gets_saved_successfully_and_it_is_displayed_in_the_grid(String pageName) throws Throwable {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/yyyy");
            Date parsedDate = null;
            switch (pageName) {
                case "Partner Cards":
                    String CardNumber = (String) cucumberContextManager.getScenarioContext("CARD_NUMBER");
                    String CardExpiryDate = (String) cucumberContextManager.getScenarioContext("CARD_EXPIRY_DATE");
                     parsedDate = sdf.parse(CardExpiryDate);
                    CardExpiryDate = sdf1.format(parsedDate).toString();
                    Thread.sleep(4000);
                    CardNumber = "**** **** **** " + CardNumber.substring(11,15);
                    String PartnerXpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/i[contains(@class, 'fa fa-check-circle text-green-alt')]", CardNumber, CardExpiryDate);
//                testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(PartnerXpath)),PartnerXpath +" Element should be displayed",PartnerXpath+ " Element is displayed", PartnerXpath+ " Element is not displayed");
                    testStepAssert.isElementTextEquals(admin_paymentMethodsPage.Label_SuccessMessageForPartner(), "Partner Payment Method added successfully.", "Partner Payment Method added successfully. message should be displayed", "Partner Payment Method added successfully. message is displayed", "Partner Payment Method added successfully. message should be displayed is not displayed");
                    cucumberContextManager.setScenarioContext("XPath", PartnerXpath);
                    break;
                case "Bungii Cards":
                    String BungiiCardNumber = (String) cucumberContextManager.getScenarioContext("CARD_NUMBER");;
                    String BungiiCardExpiryDate = (String) cucumberContextManager.getScenarioContext("CARD_EXPIRY_DATE");
                     parsedDate = sdf.parse(BungiiCardExpiryDate);
                    BungiiCardExpiryDate = sdf1.format(parsedDate).toString();
                    Thread.sleep(4000);
                    BungiiCardNumber = "**** **** **** " + BungiiCardNumber.substring(11,15);
                    String BungiiXpath = String.format("//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td/i[contains(@class, 'fa fa-check-circle text-green-alt')]", BungiiCardNumber, BungiiCardExpiryDate);
                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(BungiiXpath)), BungiiXpath + " Element should be displayed", BungiiXpath + " Element is displayed", BungiiXpath + " Element is not displayed");
                    cucumberContextManager.setScenarioContext("XPath", BungiiXpath);
                    break;

            }
        }

//    @Then("^the card is added to the user \"([^\"]*)\" on \"([^\"]*)\" page$")
//    public void the_card_is_added_to_the_user_something_on_something_page(String strArg1, String strArg2) throws Throwable {
//            testStepAssert.isElementTextEquals(admin_paymentMethodsPage.Label_SuccessMessage(),"Partner Payment Method added successfully.","Partner Payment Method added successfully. message should be displayed" ,"Partner Payment Method added successfully. message is displayed","Partner Payment Method added successfully. message should be displayed is not displayed");
//    }
        @Then("^the card is added to the user \"([^\"]*)\" page$")
        public void the_card_is_added_to_the_user_something_page(String PageName) throws Throwable {
            switch(PageName) {
                case "partner Cards":
                    testStepAssert.isElementTextEquals(admin_paymentMethodsPage.Label_SuccessMessageForPartner(),"Partner Payment Method added successfully.","Partner Payment Method added successfully. message should be displayed" ,"Partner Payment Method added successfully. message is displayed","Partner Payment Method added successfully. message should be displayed is not displayed");
                    break;

                case "Bungii Cards":
                    testStepAssert.isElementTextEquals(admin_paymentMethodsPage.Label_SuccessMessageForBungii(),"Bungii Payment Method added successfully.","Bungii Payment Method added successfully. message should be displayed" ,"Bungii Payment Method added successfully. message is displayed","Bungii Payment Method added successfully. message should be displayed is not displayed");
                    break;
            }
        }

        @Then("^The \"([^\"]*)\" details screen is removed from UI$")
        public void the_something_details_screen_is_removed_from_ui(String PageName) throws Throwable {
            switch(PageName) {
                case "Add Partner Cards":
                    testStepAssert.isNotElementDisplayed(admin_paymentMethodsPage.Button_Save(), PageName + " button should be hidden",
                            PageName +" Popup is hidden", PageName+" Popup is not hidden");
                    break;

                case "Add Bungii Cards":
                    testStepAssert.isNotElementDisplayed(admin_paymentMethodsPage.Button_Save(), PageName + " button should be hidden",
                            PageName +" button is hidden", PageName+" button is not hidden");
                    break;
            }
        }
}
