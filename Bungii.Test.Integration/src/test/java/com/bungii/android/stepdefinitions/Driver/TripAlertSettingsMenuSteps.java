package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.ScheduledTripsPage;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.driver.*;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.android.pages.customer.PromosPage;

import com.bungii.android.pages.driver.TripAlertSettingsPage;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.pass;

public class TripAlertSettingsMenuSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    TripAlertSettingsPage tripAlertSettingsPage= new TripAlertSettingsPage();
    EstimatePage estimatePage = new EstimatePage();
    ScheduledBungiisPage scheduledBungiisPage=new ScheduledBungiisPage();
    PromosPage promosPage=new PromosPage();
    BungiiRequest bungiiRequestPage = new BungiiRequest();
    InProgressBungiiPages inProgressPages=new InProgressBungiiPages();
    HomePage homePage=new HomePage();
    ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();
    SetPickupTimePage setPickupTimePage = new SetPickupTimePage();
    SearchingPage searchingPage = new SearchingPage();


    @And("^I click on \"([^\"]*)\" tab$")
    public void i_click_on_something_tab(String option) throws Throwable {
        try {
            switch (option) {
                case "Delivery Alerts":
                    action.click(tripAlertSettingsPage.Tab_TripAlerts());
                    break;

                case "SMS Alerts":
                    action.click(tripAlertSettingsPage.Tab_SMSAlerts());
                    break;

                case "Scheduled":
                    action.click(homePage.Tab_MyBungiisScheduled());
                    break;

                case "Past":
                    Thread.sleep(10000);
                    action.click(homePage.Tab_MyBungiisPast());
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log(" I tap on " + option + " on My Bungiis",
                    "I  tap on  " + option, true);        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }


    @Then("^I should be able to see \"([^\"]*)\" Text and Time$")
    public void i_should_be_able_to_see_something_text_and_time(String tab)  {
        String data=null;Boolean b;
        WebElement element;
        String time=PropertyUtility.getDataProperties("alert.time.to.android");
        try {
            action.scrollToTop();
            int count =0;
            switch (tab) {
                case "Delivery Alerts":
                    data = action.getText(tripAlertSettingsPage.Text_TripAndSMSAlertsText());
                    testStepVerify.isEquals(data.trim(), PropertyUtility.getMessage("trip.alert.android.text"));
                    //b = clickDriverMenu(time);
                    //testStepVerify.isEquals(b.toString(), "true");
                     count = getTimeRow();
                     //At a time u can see only 6
                    testStepAssert.isTrue(count==6,"All Weekdays should be displayed","All Weekdays are displayed","All Weekdays are not displayed. Days displayed : "+ count);
                    action.scrollToBottom();
                    element = getLastTimeRow();
                    testStepAssert.isEquals(element.getText(),"Saturday","All Weekdays should be displayed","All Weekdays are displayed","All Weekdays are not displayed.");

                    break;

                case "SMS Alerts":
                    data = action.getText(tripAlertSettingsPage.Text_TripAndSMSAlertsText());
                    testStepVerify.isEquals(data.trim(), PropertyUtility.getMessage("sms.alert.text"));
                  //  b = clickDriverMenu(time);
                   // testStepVerify.isEquals(b.toString(), "true");
                    //At a time u can see  7 in SMS alerts
                    count = getTimeRow();
                    testStepAssert.isTrue(count==7,"All Weekdays should be displayed","All Weekdays are displayed","All Weekdays are not displayed. Days displayed : "+ count);
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be able to see \"([^\"]*)\" Text$")
    public void i_should_be_able_to_see_something_text(String tab)  {
        try {
            switch (tab) {
                case "Note Details":
                    String noteText=action.getText(estimatePage.Text_DetailsNote());
                    String enteredNoteText=(String)cucumberContextManager.getScenarioContext("NOTE_TEXT");
                    if(noteText.equals(enteredNoteText)){
                        testStepAssert.isTrue(true, "The note text should match.", "The note text didn't match.");
                    }
                    else
                    {
                        testStepAssert.isFail("The note text didn't match.");
                    }
                    break;

                case "Customer Note":
                    noteText=action.getText(bungiiRequestPage.Text_CustomerNote());
                    enteredNoteText=(String)cucumberContextManager.getScenarioContext("NOTE_TEXT");
                    if(noteText.equals(enteredNoteText)){
                        testStepAssert.isTrue(true, "The note text of customer and driver should match.", "The note text of customer and driver didn't match.");
                    }
                    else
                    {
                        testStepAssert.isFail("The note text of customer and driver didn't match.");
                    }
                    break;

                case "Details From Customer":
                    noteText=action.getText(inProgressPages.Text_CustomerNote());
                    enteredNoteText=(String)cucumberContextManager.getScenarioContext("NOTE_TEXT");
                    if(noteText.equals(enteredNoteText)){
                        testStepAssert.isTrue(true, "The note text of customer and driver should match.", "The note text of customer and driver didn't match.");
                    }
                    else
                    {
                        testStepAssert.isFail("The note text of customer and driver didn't match.");
                    }
                    break;

                case "No Note":
                    noteText=action.getText(estimatePage.Text_DetailsNote());
                    enteredNoteText="";
                    if(noteText.equals(enteredNoteText)){
                        testStepAssert.isTrue(true, "The note text should match.", "The note text didn't match.");
                    }
                    else
                    {
                        testStepAssert.isFail("The note text didn't match.");
                    }
                    break;

                case "Customer Entered":
                    String expectedSchdlDateTime= (String) cucumberContextManager.getScenarioContext("SCHEDULE_BUNGII_DATE");
                    String actualSchdlDateTime=setPickupTimePage.Text_DateTime().getText();
                    testStepAssert.isEquals(actualSchdlDateTime, expectedSchdlDateTime,expectedSchdlDateTime+" is expected schedule date and time.", expectedSchdlDateTime+" is displayed.", expectedSchdlDateTime+" is not displayed.");
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP ");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I click on time and change \"([^\"]*)\" time$")
    public void i_click_on_time_and_change_something_time(String strArg1) throws Throwable {
        try {
            action.click(tripAlertSettingsPage.Image_TimeSettingsArrow());
            action.click(tripAlertSettingsPage.Text_TimeSettingsFromTime());
            action.click(tripAlertSettingsPage.TimePicker_ChangeTime());
            log(" I click on time and change " + strArg1 + "",
                    "I clicked on time and change  " + strArg1, true);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @And("^I click on \"([^\"]*)\" button$")
    public void i_click_on_something_button(String Name) throws Throwable {
        try {
            switch (Name) {
                case "SAVE TIME":
                    action.click(tripAlertSettingsPage.TimePicker_OK());
                    action.click(tripAlertSettingsPage.Button_SaveTime());

                    break;

                case "ADD":
                    action.click(promosPage.Button_AddPromoCode());
                    break;

                case "OK":
                    action.click(promosPage.Button_Ok());
                    break;

                case "SAVE MONEY":
                    action.click(scheduledBungiisPage.Button_SaveMoney());
                    break;

                case "GET MORE MONEY":
                    action.click(promosPage.Button_GetMoreMoney());
                    break;

                case "ADD NOTE":
                    action.click(estimatePage.Button_AddNotes());
                    break;

                case "MORE":
                    action.click(inProgressPages.Button_More());
                    break;

                case "DETAILS FROM CUSTOMER":
                    action.click(inProgressPages.Button_DetailsFromCustomer());
                    break;

                case "VERIFY":
                    action.click(scheduledTripsPage.Button_VerifyDriver());
                    break;

                case "SAVE CHANGES":
                    action.click(scheduledTripsPage.Button_SaveChanges());
                    break;

                case "Edit Trip":
                    scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//p[@id='btnEdit']")).click();
                    break;

                case "Edit Trip1":
                    scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row1']/td/p[@id='btnEdit'][1]")).click();
                    break;

                case "Edit Trip2":
                    Thread.sleep(2000);
                    scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row2']/td/p[@id='btnEdit'][1]")).click();
                    break;

                case "Close":
                    action.click(scheduledTripsPage.Button_ClosePopUp());
                    break;

                case "SET PICKUP LOCATION":
                    action.click(homePage.Button_ETASet());
                    break;

                case "SET DROP OFF LOCATION":
                    Thread.sleep(2000);
                    action.click(homePage.Button_ETASet());
                    break;

                case "SCHEDULE BUNGII":
                    Thread.sleep(2000);
                    String actualSchdlDateTime=setPickupTimePage.Text_DateTime().getText();
                    cucumberContextManager.setScenarioContext("NEW_SCHDL_BUNGII_TIME", actualSchdlDateTime);
                    action.click(setPickupTimePage.Button_ScheduleBungii());
                    break;

                case "CANCEL":
                    action.click(searchingPage.Link_CancelSearch());
                    break;

                case "SUBMIT":
                    action.click(setPickupTimePage.Button_EnterCancellationReason());
                    break;

                case "SUBMIT REASON":
                    action.click(setPickupTimePage.Button_SubmitCancellationReason());
                    break;

                default:
                    error("Implemented Step", "UnImplemented Step");
            }
            pass(" I click on " + Name + " button",
                    "I clicked on  " + Name +" button");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify that changes in time are saved$")
    public void i_verify_that_changes_in_time_are_saved() throws Throwable {
        try {
            //Boolean b = checkTimeChange("08:00 - 09:00");
            action.scrollToTop();
            int count = getTimeRow();
            testStepAssert.isTrue(count==6,"All Weekdays should be displayed","All Weekdays are displayed","All Weekdays are not displayed. Days displayed : "+ count);
            action.scrollToBottom();
            WebElement element = getLastTimeRow();
            testStepAssert.isEquals(element.getText(),"Saturday","All Weekdays should be displayed","All Weekdays are displayed","All Weekdays are not displayed.");

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    public boolean checkTime(String time) {
        Boolean isClicked = false;
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        for (WebElement element : elements) {
            if (element.getText().equals(time)) {
                isClicked = true;
                break;
            }
        }
        return isClicked;
    }

    public boolean checkTimeChange(String time) {
        Boolean isClicked = false;
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        for (WebElement element : elements) {
            if (element.getText().equals(time)) {
                isClicked = true;
            }
        }
        return isClicked;
    }

    public boolean clickDriverMenu(String time) {
        Boolean isCorrectTime = true;

        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        for (WebElement element : elements) {
            if (element.getText().contains(time) && isCorrectTime) {
                isCorrectTime = true;
                 }else{
                isCorrectTime = false;
            }
        }
        if(elements.size()==0)
            isCorrectTime=false;

        return isCorrectTime;
    }
    public int getTimeRow() {
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        return elements.size();
    }

    public WebElement getLastTimeRow() {
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsDay();
        return elements.get(5);
    }
}
