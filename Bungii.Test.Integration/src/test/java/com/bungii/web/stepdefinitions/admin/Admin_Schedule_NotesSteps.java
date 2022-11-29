package com.bungii.web.stepdefinitions.admin;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_LiveTripsPage;
import com.bungii.web.pages.admin.Admin_LoginPage;
import com.bungii.web.pages.admin.Admin_ScheduledTripsPage;
import com.bungii.web.pages.admin.Admin_TripsPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_Schedule_NotesSteps extends DriverBase {

    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    Admin_LoginPage Page_AdminLogin = new Admin_LoginPage();
    Admin_TripsPage adminTripsPage = new Admin_TripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    DbUtility dbUtility = new DbUtility();
    private static LogUtility logger = new LogUtility(Admin_TripsSteps.class);

    private int notesCount = 0;
    private int tempNotesCount = 0;
    @And("^I search the delivery using \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_search_the_delivery_using_something_as_something(String strArg1, String admin) throws Throwable {
        try {
            switch (admin){
                case "Admin1":
                    Thread.sleep(1000);
                    cucumberContextManager.setScenarioContext("ADMIN1_NAME",action.getText(admin_ScheduledTripsPage.Text_AdminName()));
                    action.clearSendKeys(adminTripsPage.TextBox_Search(), (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST") + Keys.ENTER);
                    break;
                case "Admin2":
                    Thread.sleep(1000);
                    cucumberContextManager.setScenarioContext("ADMIN2_NAME",action.getText(admin_ScheduledTripsPage.Text_AdminName()));
                    action.clearSendKeys(adminTripsPage.TextBox_Search(), (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST") + Keys.ENTER);
                    break;
                case "Admin3":
                    Thread.sleep(1000);
                    action.clearSendKeys(adminTripsPage.TextBox_Search(), (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST") + Keys.ENTER);
                    break;
            }
            log("I should be able to search delivery as " + admin,"I could search delivery as " + admin,false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I search the delivery using \"([^\"]*)\"$")
    public void i_search_the_delivery_using_something(String newpickupRef) throws Throwable {
        try {
            action.refreshPage();
            cucumberContextManager.setScenarioContext("ADMIN1_NAME", action.getText(admin_ScheduledTripsPage.Text_AdminName()));
            String pickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            Thread.sleep(2000);
            action.clearSendKeys(adminTripsPage.TextBox_Search(), pickupRef + Keys.ENTER);
            log("I should be able to search the delivery using pickup reference","I could search the delivery using pickup reference",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on the \"([^\"]*)\" link beside scheduled bungii for \"([^\"]*)\"$")
    public void i_click_on_the_something_link_beside_scheduled_bungii_for_something(String strArg1, String deliveryType) throws Throwable {
        try{
            switch (deliveryType){
                case "Schedule Deliveries":
                    Thread.sleep(4000);
                    action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
                    Thread.sleep(2000);
                    boolean notesTabDisplayed = admin_ScheduledTripsPage.Dropdown_Notes_History().isDisplayed();
                    testStepAssert.isTrue(notesTabDisplayed,"Notes & History option should be displayed","Notes & History option is displayed","Notes & History option is not be displayed");
                    action.click(admin_ScheduledTripsPage.Dropdown_Notes_History());
                    break;
                case "Live Deliveries":
                case "All Deliveries":
                    Thread.sleep(4000);
                    action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
                    Thread.sleep(2000);
                    boolean notesDisplayed = admin_ScheduledTripsPage.Link_Notes().isDisplayed();
                    testStepAssert.isTrue(notesDisplayed,"Notes & History option should be displayed","Notes & History option is displayed","Notes & History option is not be displayed");
                    action.click(admin_ScheduledTripsPage.Link_Notes());
                    break;
                case "Completed Deliveries":
                    Thread.sleep(4000);
                    action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
                    Thread.sleep(2000);
                    action.click(admin_ScheduledTripsPage.List_ViewDeliveries());
                    break;
                case "Live Duo Deliveries":
                    Thread.sleep(4000);
                    action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
                    action.click(admin_ScheduledTripsPage.List_ViewDeliveries());
                    break;
                case "Payment Pending Deliveries":
                    Thread.sleep(4000);
                    action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
                    action.click(admin_TripsPage.Link_ChangePaymentStatus());
                    break;
            }
            log("I should be able to click on "+deliveryType+" link","I could click on "+deliveryType+" link",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the following text message \"([^\"]*)\" displayed$")
    public void i_should_see_the_following_text_message_somethingdisplayed(String Message) throws Throwable {
        try{
            Thread.sleep(2000);
            String defaultMessage = action.getText(admin_ScheduledTripsPage.Text_NotesEmpty_Message());
            testStepAssert.isEquals(defaultMessage,Message,"Text message should match","Text message matches","Text message doesnt match");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I enter the text \"([^\"]*)\" in the text area$")
    public void i_enter_the_text_something_in_the_text_area(String Text) throws Throwable {
        try{
            cucumberContextManager.setScenarioContext("ADMIN1_NOTE",Text);
            Thread.sleep(1000);
            action.clearSendKeys(admin_ScheduledTripsPage.Textbox_AddNote(),Text);
            log("I should be able to enter the text"+Text+"in the text area","I could enter the text"+Text+"in the text area",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click the \"([^\"]*)\" button$")
    public void i_click_the_something_button(String ButtonText) throws Throwable {
        try {
            Thread.sleep(1000);
            switch (ButtonText) {
                case "Save":
                    action.click(admin_ScheduledTripsPage.Button_SaveNote());
                    break;
                case "Edit":
                    action.click(admin_ScheduledTripsPage.Link_EditNote());
                    break;
                case "Update":
                    action.click(admin_ScheduledTripsPage.Link_NoteUpdate());
                    break;
                case "Delete":
                    action.click(admin_ScheduledTripsPage.Link_DeleteNote());
                    action.click(admin_ScheduledTripsPage.Link_ConfirmDeleteNote());
                    notesCount = notesCount-1;
                    log("I should be able to click on the"+ButtonText+ "button","I could click on the"+ButtonText+ "button",false);
            }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the text Displayed under notes$")
    public void i_should_see_the_text_displayed_under_notes() throws Throwable {
        try {
            Thread.sleep(2000);
            String noteText = action.getText(admin_ScheduledTripsPage.Text_FirstSavedNote());
            String adminNote= action.getText(admin_ScheduledTripsPage.Text_AdminCreatedNote());
            Thread.sleep(2000);
            testStepAssert.isEquals(noteText,(String) cucumberContextManager.getScenarioContext("ADMIN1_NOTE")," Note should be displayed","Note is displayed","Note is not displayed");
            testStepAssert.isEquals(adminNote,(String) cucumberContextManager.getScenarioContext("ADMIN1_NAME"),"Name should be displayed","Name is displayed","Name is not displayed" );
            Thread.sleep(1000);

            String []time = action.getText(admin_ScheduledTripsPage.Text_NoteTime()).split(" ");
            String noteTime =time[0];
            String properTimeDisplayed = String.format("%s ago",noteTime);
            testStepAssert.isEquals(properTimeDisplayed,action.getText(admin_ScheduledTripsPage.Text_NoteTime()),"Note created Time should be the same","Note created time is the same","Note created time doesnt not match");

            if(adminNote.contentEquals((String) cucumberContextManager.getScenarioContext("ADMIN1_NAME"))){
                action.JavaScriptClick(admin_ScheduledTripsPage.Button_NoteClose());
            }
            else{
                boolean editLinkEnable =admin_ScheduledTripsPage.Link_EditNote().isDisplayed();
                testStepAssert.isTrue(editLinkEnable,"Edit link should not be displayed","Edit link is displayed");
                action.JavaScriptClick(admin_ScheduledTripsPage.Button_NoteClose());
            }


            Thread.sleep(1000);
            String pickupReference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String noteFromDb = dbUtility.getCustomerServiceNote(pickupReference);

            testStepAssert.isEquals(noteFromDb,noteText,"Note from database should match with the displayed note","Note from database matches with the displayed note","Note from database doesnt match with the displayed note");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I click on the \"([^\"]*)\" link scheduled bungii and click on the\"([^\"]*)\" button$")
    public void i_click_on_the_something_link_scheduled_bungii_and_click_on_thesomething_button(String strArg1, String strArg2) throws Throwable {
        try {
            Thread.sleep(1000);
            action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
            action.click(admin_ScheduledTripsPage.List_ViewDeliveries());
            Thread.sleep(1000);
            action.click(admin_ScheduledTripsPage.Button_View());
            log("I should be able to click on the View link","I should be able to click on the View link",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the note created by the admin$")
    public void i_should_see_the_note_created_by_the_admin() throws Throwable {
        try {
            Thread.sleep(1000);
            boolean notePresent =admin_ScheduledTripsPage.Text_FirstSavedNote().isDisplayed();
            boolean adminPresent = admin_ScheduledTripsPage.Text_AdminCreatedNote().isDisplayed();
            Thread.sleep(1000);
            testStepAssert.isTrue(notePresent,"Note created by admin should be displayed","Note created by admin is  displayed","Note created by admin is not displayed");
            testStepAssert.isTrue(adminPresent,"Admin Name should be displayed","Admin Name is displayed","Admin Name is not displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @When("^I change the text to \"([^\"]*)\"$")
    public void i_change_the_text_to_something(String NewMessage) throws Throwable {
        try {
            cucumberContextManager.setScenarioContext("UPDATEDMESSAGE",NewMessage);
            Thread.sleep(1000);
            action.clearSendKeys(admin_ScheduledTripsPage.Text_EditNote_TextArea(),NewMessage);
            log("I should see the note changed to"+ NewMessage,"I could see the note change to"+ NewMessage,false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the text message updated to the new message$")
    public void i_should_see_the_text_message_updated_to_the_new_message() throws Throwable {
        try {
            Thread.sleep(1000);
            String []time = action.getText(admin_ScheduledTripsPage.Text_NoteTime()).split(" ");
            String noteTime =time[1];
            String properTimeDisplayed = String.format("Edited %s ago",noteTime);
            testStepAssert.isEquals(properTimeDisplayed,action.getText(admin_ScheduledTripsPage.Text_NoteTime()),"Note edited Time should be the same","Note edited time is the same","Note edited time doesnt not match");
            Thread.sleep(1000);
            String updatedMessage = action.getText(admin_ScheduledTripsPage.Text_FirstSavedNote());
            Thread.sleep(2000);
            List<WebElement> notes = admin_ScheduledTripsPage.List_Notes();
            for(int i=0; i< notes.size();i++) {
                notesCount = notesCount +1;
            }
            testStepAssert.isFalse(updatedMessage.equals(cucumberContextManager.getScenarioContext("ADMIN1_NOTE")),"Old and updated note should not match","Old and updated notes match");
            testStepAssert.isEquals(updatedMessage,(String)cucumberContextManager.getScenarioContext("UPDATEDMESSAGE"),"Updated Note should match","Updated note matches","Updated note doesnt match");
            action.JavaScriptClick(admin_ScheduledTripsPage.Button_NoteClose());
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I view the Live Deliveries list on  admin portal$")
    public void i_view_the_live_deliveries_list_on_admin_portal() throws Throwable {
        try {
            action.click(admin_LiveTripsPage.Menu_LiveTrips());
            log("I should be able to click on the Live deliveries link","I could click on the Live deliveries link",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @When("^I enter more than 2100 characters in the text area$")
    public void i_enter_more_than_2100_characters_in_the_text_area() throws Throwable {
        try {
            int J =100;
            while (J<2200) {
                action.sendKeys(admin_ScheduledTripsPage.Textbox_AddNote(),"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m");
                J= J + 100;
                log("I should be able to enter 2100 characters in the text area","I could enter 2100 characters in the text area",false);
            }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the \"([^\"]*)\" button disabled$")
    public void i_should_see_the_something_button_disabled(String strArg1) throws Throwable {
        try {
            Thread.sleep(2000);
            boolean saveButtonDisabled =admin_ScheduledTripsPage.Button_SaveNote().isEnabled();
            testStepAssert.isFalse(saveButtonDisabled,"Save button should be disabled","Save button is disabled","Save button is not disabled");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I log into another \"([^\"]*)\" portal in a new tab$")
    public void i_log_into_another_something_portal_in_a_new_tab(String strArg1) throws Throwable {
        try {
            String adminURL = "https://qaauto-portal-v2.gobungii-dev.com/login";
            Thread.sleep(2000);
            action.openNewTab();
            action.navigateTo(adminURL);
            action.sendKeys(Page_AdminLogin.TextBox_Phone(), "9765330125");
            action.sendKeys(Page_AdminLogin.TextBox_Password(),"cci12345");
            Thread.sleep(2000);
            action.click(Page_AdminLogin.Button_AdminLogin());
            Thread.sleep(3000);
            cucumberContextManager.setScenarioContext("ADMIN2_NAME",action.getText(admin_ScheduledTripsPage.Text_AdminName()));
            log("I should be able to open new tab and login to admin portal","I should be able to open new tab and login to admin portal",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I should see the \"([^\"]*)\" created by \"([^\"]*)\"$")
    public void i_should_see_the_something_created_by_something(String strArg1, String strArg2) throws Throwable {
        try {
            String existingMessage = action.getText(admin_ScheduledTripsPage.Text_FirstSavedNote());
            testStepAssert.isEquals(existingMessage,(String) cucumberContextManager.getScenarioContext("ADMIN1_NOTE"),"Note Should match","Note matches","Note doesnt match");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^The \"([^\"]*)\" link should not be displayed$")
    public void the_something_link_should_not_be_displayed(String strArg1) throws Throwable {
        try {
            Thread.sleep(1000);
            testStepAssert.isTrue(action.isElementPresent(
                    admin_ScheduledTripsPage.Link_EditNote_NotDisplayed(true)),"Edit link should not be displayed","Edit link is not displayed", "Edit link is  displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The \"([^\"]*)\" page should display the delivery in \"([^\"]*)\" form")
    public void the_something_page_should_display_the_delivery_in_something_form(String PagenName, String ExpectedText) throws Throwable {
        try {
            action.click(admin_ScheduledTripsPage.Menu_CompletedDeliveries());
            Thread.sleep(4000);
            String deliveryComplete = action.getText(admin_ScheduledTripsPage.Text_Delivery_Successfull());
            testStepAssert.isEquals(deliveryComplete,ExpectedText,"Delivery Status should match","Delivery status matches","Delivery status doesnt match");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the note deleted$")
    public void i_should_see_the_note_deleted() throws Throwable {
        try {
            Thread.sleep(4000);
            List<WebElement> notes = admin_ScheduledTripsPage.List_Notes();
            int totalNotesSize = notes.size();
            if(notesCount >0) {
                for (int i = 0; i < notes.size(); i++) {
                    if (notes.get(i).getText().contains((CharSequence) cucumberContextManager.getScenarioContext("UPDATEDMESSAGE"))) {
                        testStepAssert.isFail("Note is not deleted ");
                        break;
                    } else {
                        tempNotesCount = tempNotesCount + 1;
                    }
                }
            }
            else{
                Thread.sleep(3000);
                String expectedTextMessage = "No notes available. Please start entering notes to appear here.";
                String defaultMessage = action.getText(admin_ScheduledTripsPage.Text_NotesEmpty_Message());
                testStepAssert.isEquals(defaultMessage,expectedTextMessage,"No Notes Message should match","No notes message matches","No notes message is doesnt match");
            }
            testStepAssert.isEquals(Integer.toString(tempNotesCount),Integer.toString(totalNotesSize),"Note should be deleted","Note is deleted","Note is not deleted");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I create a new note as \"([^\"]*)\"$")
    public void i_create_a_new_note_as_something(String strArg1) throws Throwable {
        try {
            Thread.sleep(1000);
            List<WebElement> notes = admin_ScheduledTripsPage.List_Notes();
            cucumberContextManager.setScenarioContext("NOTES",notes.size());
            String newMessage ="Note created by admin2";
            cucumberContextManager.setScenarioContext("ADMIN2_NOTE",newMessage);
            action.clearSendKeys(admin_ScheduledTripsPage.Textbox_AddNote(),newMessage);
            log("I should be able to create note with the text"+newMessage,"I could create note with the text"+newMessage);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^A new note should be displayed under notes$")
    public void a_new_note_should_be_displayed_under_notes() throws Throwable {
        try {
            Thread.sleep(3000);
            String adminName= action.getText(admin_ScheduledTripsPage.Text_Admin2Name());
            List<WebElement> notes = admin_ScheduledTripsPage.List_Notes();
            int totalNotesSize = notes.size();
            if(notesCount >0) {
                for (int i = 0; i < notes.size(); i++) {
                    if (notes.get(i).getText().contains((CharSequence) cucumberContextManager.getScenarioContext("ADMIN2_NOTE"))) {
                        testStepAssert.isEquals(notes.get(i).getText(),(String) cucumberContextManager.getScenarioContext("ADMIN2_NOTE"),"New note added should be displayed","New note added is displayed","New note added is not displayed");
                    }
                }
            }
            Thread.sleep(2000);
            testStepAssert.isEquals(adminName,(String) cucumberContextManager.getScenarioContext("ADMIN2_NAME"),"Admin names should match","Admin names match","Admin names dont match");
            testStepAssert.isTrue(totalNotesSize> Integer.parseInt((String)cucumberContextManager.getScenarioContext("NOTES")),"Note count should be increased","Note count is increased","Note count is not increased" );
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I should see all the notes$")
    public void i_should_see_all_the_notes() throws Throwable {
        try {
            Thread.sleep(2000);
            List<WebElement> allNotes = admin_ScheduledTripsPage.List_AllNotes();
            for (int i = 0; i < allNotes.size(); i++) {
                String admin =  action.getText(admin_ScheduledTripsPage.Text_AdminNames(i+1));
                String adminNote =  action.getText(admin_ScheduledTripsPage.Text_AdminCreatedNotes(i+1));
                if (admin.contentEquals((CharSequence) cucumberContextManager.getScenarioContext("ADMIN1_NAME"))) {
                    testStepAssert.isEquals(admin ,(String) cucumberContextManager.getScenarioContext("ADMIN1_NAME"),"Names should match","Names match","Names dont match");
                    testStepAssert.isEquals(adminNote,(String) cucumberContextManager.getScenarioContext("ADMIN1_NOTE"),"Admin notes should match","Notes match","Notes dont match");
                    continue;
                }
                if  (admin.contentEquals((CharSequence) cucumberContextManager.getScenarioContext("ADMIN2_NAME"))) {
                    testStepAssert.isEquals(admin ,(String) cucumberContextManager.getScenarioContext("ADMIN2_NAME"),"Names should match","Names match","Names dont match");
                    testStepAssert.isEquals(adminNote,(String) cucumberContextManager.getScenarioContext("ADMIN2_NOTE"),"Admin notes should match","Notes match","Notes dont match");
                }
            }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I navigate to \"([^\"]*)\" portal configured for \"([^\"]*)\"$")
    public void i_navigate_to_something_portal_configured_for_something(String strArg1, String url) throws Throwable {
        try {
            action.openNewTab();
            String partnerUrl =  utility.NavigateToPartnerLogin(url);
            log("I should be able to navigate to partner portal","I should be able to navigate to partner portal",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I navigate to \"([^\"]*)\" portal$")
    public void i_navigate_to_something_portal(String strArg1) throws Throwable {
        try {
            ArrayList<String> tabs = new ArrayList<String> (SetupManager.getDriver().getWindowHandles());
            SetupManager.getDriver().switchTo().window(tabs.get(0));
            action.refreshPage();
            log("I should be able to switch the tab back to admin portal","I could switch the tab back to admin portal",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should be able to see the respective bungii with the status$")
    public void i_should_be_able_to_see_the_respective_bungii_with_the_status(DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String status = dataMap.get("Status").trim();
            switch (status) {
                case "Assigning Driver(s)":
                    Thread.sleep(2000);
                    String searchingDriversStatus = action.getText(admin_ScheduledTripsPage.Text_DeliveryStatus(status));
                    Thread.sleep(1000);
                    testStepAssert.isEquals(searchingDriversStatus,status,"Delivery Status should be set to Assigning Drivers","Delivery Status is set to Assigning Drivers","Delivery Status is not set to Assigning Drivers");
                    break;
                case "Scheduled":
                    Thread.sleep(2000);
                    String scheduledStatus = action.getText(admin_ScheduledTripsPage.Text_DeliveryStatus(status));
                    Thread.sleep(1000);
                    testStepAssert.isEquals(scheduledStatus,status,"Delivery Status should be set to Scheduled ","Delivery Status is set to Scheduled ","Delivery Status is not set to Scheduled");
                    break;
                case "Trip Started":
                    Thread.sleep(5000);
                    String tripStartedStatus = action.getText(admin_ScheduledTripsPage.Text_DeliveryStatus(status));
                    Thread.sleep(3000);
                    testStepAssert.isEquals(tripStartedStatus,status,"Delivery Status should be set to Trip Started  ","Delivery Status is Trip Started ","Delivery Status is not set to Trip Started");
                    break;
                case  "Driver(s) Arrived":
                    Thread.sleep(2000);
                    String driversArrivedStatus = action.getText(admin_ScheduledTripsPage.Text_DeliveryStatus(status));
                    Thread.sleep(1000);
                    testStepAssert.isEquals(driversArrivedStatus,status,"Delivery Status should be set to  Driver(s) Arrived","Delivery Status is set to Driver(s) Arrived ","Delivery Status is not set to Driver(s) Arrived");
                    break;
                case  "Loading Item":
                    Thread.sleep(2000);
                    String loadingItemStatus= action.getText(admin_ScheduledTripsPage.Text_DeliveryStatus(status));
                    Thread.sleep(1000);
                    testStepAssert.isEquals(loadingItemStatus,status,"Delivery Status should be set to Loading Item","Delivery Status is Loading Item","Delivery Status is not set to Loading Item");
                    break;
                case "Driving To Dropoff":
                    Thread.sleep(2000);
                    String drivingToDropoffStatus = action.getText(admin_ScheduledTripsPage.Text_DeliveryStatus(status));
                    Thread.sleep(1000);
                    testStepAssert.isEquals(drivingToDropoffStatus,status,"Delivery Status should be set to Driving To Dropoff ","Delivery Status is Driving To Dropoff","Delivery Status is not set to Driving To Dropoff");
                    break;
                case "Unloading Items":
                    Thread.sleep(2000);
                    String unloadingItemsStatus = action.getText(admin_ScheduledTripsPage.Text_DeliveryStatus(status));
                    Thread.sleep(1000);
                    testStepAssert.isEquals(unloadingItemsStatus,status,"Delivery Status should be set to Unloading Items ","Delivery Status is Unloading Items","Delivery Status is not set to Unloading Items");
                    break;
            }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I close the Note$")
    public void i_close_the_note() throws Throwable {
        try {
            Thread.sleep(1000);
            action.JavaScriptClick(admin_ScheduledTripsPage.Button_NoteClose());
            log("I should be able to close the note","I could close the note",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I create multiple notes$")
    public void i_create_multiple_notes() throws Throwable {
        try {
            action.waitUntilIsElementExistsAndDisplayed(admin_ScheduledTripsPage.Textbox_AddNote(),(long)3000);
            action.clearSendKeys(admin_ScheduledTripsPage.Textbox_AddNote(),"Added Customer Note");
            action.waitUntilIsElementExistsAndDisplayed(admin_ScheduledTripsPage.Button_SaveNote(),(long)3000);
            action.click(admin_ScheduledTripsPage.Button_SaveNote());
            action.waitUntilIsElementExistsAndDisplayed(admin_ScheduledTripsPage.Textbox_AddNote(),(long)3000);
            action.clearSendKeys(admin_ScheduledTripsPage.Textbox_AddNote(),"Added note by admin");
            action.waitUntilIsElementExistsAndDisplayed(admin_ScheduledTripsPage.Button_SaveNote(),(long)3000);
            action.click(admin_ScheduledTripsPage.Button_SaveNote());
            log("I should be able to create multiple notes","I could create multiple notes",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the notes displayed̥$")
    public void i_should_see_the_notes_displayed() throws Throwable {
        try {
            Thread.sleep(1000);
            List<WebElement> allNotes = admin_ScheduledTripsPage.List_AllNotes();
            int adminNotesCount  =0;
            for (int i = 0; i < allNotes.size(); i++) {
                String admin =  action.getText(admin_ScheduledTripsPage.Text_AdminNames(i+1));
                if  (admin.contentEquals((CharSequence) cucumberContextManager.getScenarioContext("ADMIN2_NAME"))) {
                    adminNotesCount = adminNotesCount +1;
                }
            }
            testStepAssert.isTrue(adminNotesCount>1,"Multiple Notes should be created by same admin","Multiple Notes are created by same admin","Multiple Notes are not  created by same admin");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on the dropdown beside scheduled bungii$")
    public void i_click_on_the_dropdown_beside_scheduled_bungii() throws Throwable {
        try {
            Thread.sleep(1000);
            action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
            log("I should be able to click on the dropdown besides scheduled bungii","I could click on the dropdown besides scheduled bungii",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the \"([^\"]*)\" underlined$")
    public void i_should_see_the_something_underlined(String link) throws Throwable {
        try {
            switch (link) {
                case "Notes":
                    Thread.sleep(1000);
                    boolean notesUnderlined = admin_ScheduledTripsPage.Link_Notes().getCssValue("border-bottom").contentEquals("3px solid rgb(0, 128, 0)");
                    testStepAssert.isTrue(notesUnderlined,"Notes should be underlined","Notes is underlined","Notes in not Underlined");
                    break;

                case "History":
                    Thread.sleep(1000);
                    boolean historyUnderlined = admin_ScheduledTripsPage.Link_History().getCssValue("border-bottom").contentEquals("3px solid rgb(255, 165, 0)");
                    testStepAssert.isTrue(historyUnderlined, "History should be underlined", "History is underlined", "History is not Underlined");
                    break;
            }
            } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I click the \"([^\"]*)\" link$")
    public void i_click_the_something_link(String notesHistory) throws Throwable {
        try {
            Thread.sleep(4000);
            action.click(admin_ScheduledTripsPage.Dropdown_Notes_History());
            log("I should be able to click on the" +notesHistory+ "link","I could click on the" +notesHistory+ "link",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the \"([^\"]*)\" field not underlined$")
    public void i_should_see_the_something_field_not_underlined(String link) throws Throwable {
        try {
            switch (link) {
                case "Notes":
                    Thread.sleep(1000);
                    boolean notesNotUnderlined = admin_ScheduledTripsPage.Link_Notes().getCssValue("border-bottom").contentEquals("0px none rgb(31, 31, 31)");
                    testStepAssert.isFalse(notesNotUnderlined, "Notes should not be underlined", "Notes is not underlined", "Notes in Underlined");
                    break;

                case "History":
                    Thread.sleep(1000);
                    boolean historyNotUnderlined = admin_ScheduledTripsPage.Link_History().getCssValue("border-bottom").contentEquals("0px none rgb(31, 31, 31)");
                    testStepAssert.isFalse(historyNotUnderlined, "History should not be underlined", "History is not underlined", "History is Underlined");
                    break;
            }
            } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I click on \"([^\"]*)\"$")
    public void i_click_on_something(String strArg1) throws Throwable {
        try {
           action.click(admin_ScheduledTripsPage.Button_History());
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The \"([^\"]*)\" tab should be selected$")
    public void the_something_tab_should_be_selected(String strArg1) throws Throwable {
        try {
            boolean historyTabSelected = admin_ScheduledTripsPage.Button_History().getAttribute("Class").contentEquals("modal-title tab active");
            testStepAssert.isTrue(historyTabSelected, "History should be selected", "History is not selected", "History is selected");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I should see no history text$")
    public void i_should_see_no_history_text() throws Throwable {
        try {
            testStepVerify.isEquals(action.getText(admin_ScheduledTripsPage.Text_HistoryEmptyMessage()), PropertyUtility.getMessage("No_HistoryData"));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I should see drop off address edit history$")
    public void i_should_see_drop_off_address_edit_history() throws Throwable {
        try {
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_AdminNameHistoryTab())).equals(PropertyUtility.getMessage("AdminName")),"It should show admin name", "It does not show admin Name");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Header_HistoryEvent())).equals(PropertyUtility.getMessage("Header_Event")),"Event Header should be shown","Event Header should be shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Header_HistoryOldValue())).equals(PropertyUtility.getMessage("Header_OldValue")),"Old Value Header should be shown","Old Value Header not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Header_HistoryNewValue())).equals(PropertyUtility.getMessage("Header_NewValue")),"New Value Header should be shown","New Value Header not shown");
            testStepAssert.isTrue(action.getText(admin_ScheduledTripsPage.Text_HistoryEditedTime()).contains(PropertyUtility.getMessage("Text_MinsAgo")),"It should show m ago","It does not show min ago");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_DropoffAddressChange")),"event should be shown","Event not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData())).equals(PropertyUtility.getMessage("WashigtonDropOffLocation")),"Drop off location should be shown","Drop off location not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData())).contains(PropertyUtility.getMessage("WashigtonChangedLocation")),"Changed location should be shown","Changed location not shown");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I should see pickup address edit history$")
    public void i_should_see_pickup_address_edit_history() throws Throwable {
        try {
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_AdminNameHistoryTab())).equals(PropertyUtility.getMessage("AdminName")),"It should show admin name", "It does not show admin Name");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Header_HistoryEvent())).equals(PropertyUtility.getMessage("Header_Event")),"Event Header should be shown","Event Header should be shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Header_HistoryOldValue())).equals(PropertyUtility.getMessage("Header_OldValue")),"Old Value Header should be shown","Old Value Header not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Header_HistoryNewValue())).equals(PropertyUtility.getMessage("Header_NewValue")),"New Value Header should be shown","New Value Header not shown");
            testStepAssert.isTrue(action.getText(admin_ScheduledTripsPage.Text_HistoryEditedTime()).contains(PropertyUtility.getMessage("Text_MinsAgo")),"It should show m ago","It does not show min ago");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_PickupAddressChange")),"event should be shown","Event not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData())).equals(PropertyUtility.getMessage("WashigtonPickupLocation")),"Pickup location should be shown","Pickup location not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData())).contains(PropertyUtility.getMessage("WashigtonChangedLocation")),"Changed location should be shown","Changed location not shown");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I should see solo to duo and assign remove one driver edit history$")
    public void i_should_see_solo_to_duo_and_assign_remove_one_driver_edit_history() throws Throwable {
        try {
            String DriverName=(String)cucumberContextManager.getScenarioContext("Driver_Name");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_DriverRemoved")),"event should be shown","Event not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData())).contains(DriverName),"OldValueData should be shown","Old Value Data not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData())).isEmpty(),"New Value data should be shown","New Value Data not shown");

            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValuePreviousEdit())).equals(PropertyUtility.getMessage("Text_SoloToDuo")),"event should be shown","Event not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueDataPreviousEdit())).equals(PropertyUtility.getMessage("Text_Solo")),"OldValueData should be shown","Old Value Data not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueDataPreviousEdit())).equals(PropertyUtility.getMessage("Text_Duo")),"New Value data should be shown","New Value Data not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValueRow2())).equals(PropertyUtility.getMessage("Text_DriverAdded")),"event should be shown","Event not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueDataRow2())).isEmpty(),"old Value data should be shown","Old Value Data not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueDataRow2())).contains(DriverName),"New Value data should be shown","New Value Data not shown");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I click on the Notes link for Live Deliveries$")
    public void i_click_on_the_notes_link_for_live_deliveries() throws Throwable{
        try {
            Thread.sleep(4000);
            action.click(admin_ScheduledTripsPage.Link_NotesHistoryLiveDelivery());
            log("I should be able to click on the notesHistory link","I could click on the notesHistory link",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I should see edit date time history$")
    public void i_should_see_edit_date_time_history() throws Throwable {
        try
        {
            String DateChanged=(String) cucumberContextManager.getScenarioContext("Date_Changed");
            String[] dateN=DateChanged.split(",");
            String TimeChanged=(String) cucumberContextManager.getScenarioContext("Time_Changed");
            String OldScheduleTime=(String)cucumberContextManager.getScenarioContext("BUNGII_TIME");
            String ACtualnewdate=action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData());
            String ACtualOlddate=action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData());
            ACtualOlddate= ACtualOlddate.replace(":00 AM"," AM");
            ACtualnewdate=ACtualnewdate.replace(":00 AM"," AM");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_DateTimeEdit")),"event should be shown","Event not shown");
            testStepAssert.isTrue((ACtualOlddate).contains(OldScheduleTime),"Old Value Data should be shown","Old Value Data not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData())).contains(dateN[0]),"New Value data should be shown","New Value Data not shown");
            testStepAssert.isTrue((ACtualnewdate).contains(TimeChanged),"New Value data should be shown","New Value Data not shown");

    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I should see edit Service level history$")
    public void i_should_see_edit_service_level_history() throws Throwable {
        try {
            String OldService = (String) cucumberContextManager.getScenarioContext("Old_service");
            String NewService = (String) cucumberContextManager.getScenarioContext("Change_service");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryEventValue())).equals(PropertyUtility.getMessage("Text_ServiceLevelEdit")), "event should be shown", "Event not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryOldValueData())).contains(OldService),"OldValueData should be shown","Old Value Data not shown");
            testStepAssert.isTrue((action.getText(admin_ScheduledTripsPage.Text_HistoryNewValueData())).contains(NewService), "New Value data should be shown", "New Value Data not shown");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}