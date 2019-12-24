package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.BungiiDetailsPage;
import com.bungii.android.pages.customer.EstimatePage;
import com.bungii.android.pages.customer.ScheduledBungiisPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.bungii.common.manager.ResultManager.*;

public class ScheduledBungiiSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(PromosSteps.class);
    ActionManager action = new ActionManager();
    ScheduledBungiisPage scheduledBungiisPage;
    GeneralUtility utility = new GeneralUtility();

    BungiiDetailsPage bungiiDetailsPage= new BungiiDetailsPage();
    EstimatePage estimatePage=new EstimatePage();
    public ScheduledBungiiSteps(ScheduledBungiisPage scheduledBungiisPage) {
        this.scheduledBungiisPage = scheduledBungiisPage;
    }

    @Then("^Bungii must be removed from \"([^\"]*)\" screen$")
    public void bungii_must_be_removed_from_something_screen(String screen) {
        try {
            String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
    //        tripTime="Mar 14, 01:15 PM";
            Thread.sleep(20000);
            action.scrollToTop();
            boolean isBungiiPresent =isBungiiPresent(tripNoOfDriver, tripTime);
            //do half screen swipe if Bungii is present
            if(isBungiiPresent)
            {	action.scrollToTop();
                isBungiiPresent = isBungiiPresent(tripNoOfDriver, tripTime);
            }
            testStepVerify.isFalse(isBungiiPresent, "Bungii must be removed from " + screen + " screen",
                    "Bungii Must be deleted", "Bungii is not deleted");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I select already scheduled bungii$")
    public void i_select_already_scheduled_bungii() {
        try {
            String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            selectBungii(tripNoOfDriver, tripTime);
            pass("I select already scheduled bungii", "I selected already scheduled bungii of "+tripNoOfDriver+" type and at time: " + tripTime , true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^The status on \"([^\"]*)\" should be displayed as \"([^\"]*)\"$")
    public void the_status_on_something_should_be_displayed_as_something(String strArg1, String status) throws Throwable {
    try{
                testStepVerify.isElementTextEquals(scheduledBungiisPage.Text_TripStatus(),status);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

   @Then("^trips status on bungii details should be \"([^\"]*)\"$")
    public void trips_status_on_bungii_details_should_be_something(String strArg1) throws Throwable {

        try {
            String tripStatus="",actualDriverName="";
            switch (strArg1.toLowerCase()) {
                case "driver 1 - contacting drivers":
                    tripStatus=action.getText(bungiiDetailsPage.Text_Driver1Status());
                    testStepVerify.isEquals(tripStatus,"Contacting");
                    testStepVerify.isElementEnabled(bungiiDetailsPage.Text_Driver1StatusTag()," Driver # 1 tag should be displayed");
                    break;
                case "driver 2 - contacting drivers":
                    tripStatus=action.getText(bungiiDetailsPage.Text_Driver2Status());
                    testStepVerify.isEquals(tripStatus,"Contacting");
                    testStepVerify.isElementEnabled(bungiiDetailsPage.Text_Driver2StatusTag()," Driver # 2 tag should be displayed");
                    break;

                case"driver1 name":
                    actualDriverName=action.getText(bungiiDetailsPage.Text_Driver1Name());
                    String expectedDriver1Name=(String) cucumberContextManager.getScenarioContext("DRIVER_1");
                    if(expectedDriver1Name.contains(actualDriverName)) {
                        testStepVerify.isElementDisplayed(bungiiDetailsPage.Text_Driver1Name()," Driver # 1 Name should be displayed","Driver # 1 Name should be displayed","Driver # 1 Name is not displayed");
                    }
                    break;

                case"driver2 name":
                    actualDriverName=action.getText(bungiiDetailsPage.Text_Driver2Name());
                    String expectedDriver2Name=(String) cucumberContextManager.getScenarioContext("DRIVER_2");
                    if(expectedDriver2Name.contains(actualDriverName)) {
                        testStepVerify.isElementEnabled(bungiiDetailsPage.Text_Driver2Name()," Driver # 2 Name should be displayed");
                    }
                    break;

                case "driver 2 - contacting drivers1":
                    tripStatus=action.getText(bungiiDetailsPage.Text_Driver2Status1());
                    testStepVerify.isEquals(tripStatus,"Contacting");
                    testStepVerify.isElementEnabled(bungiiDetailsPage.Text_Driver2StatusTag1()," 1Driver # 2 tag should be displayed");
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I try to schedule bungii for \"([^\"]*)\"$")
    public void i_try_to_schedule_bungii_for_something(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "today - after working hour":
                    selectBungiiTime(0, "11", "45", "PM");
                    //log("I select time for trip as 11:45  pm", "I selected time for trip as 11:45  pm");
                    break;
                case "tommorow - before working hour":
                    selectBungiiTime(1, "12", "00", "AM");
                    //log("I select time for trip tomorrow 12 00 AM", "I selected time for trip as  tomorrow 12 00 AM");
                    break;
                case "today+5":
                    selectBungiiTime(5, "", "", "");
                    log("I select time for trip today+5 1:00 pm", "I selected time for trip as today+5 1:00 pm");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");

            } }catch(Exception e){
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
               // error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
            }
        }

    /**
     * Select Bungii time in scroll wheel
     *
     * @param nextDate    Date you want to be selected
     * @param hour        Trip hour
     * @param minutes     trip minutes
     * @param meridiem    AM/PM
     */
    public void selectBungiiTime(int nextDate, String hour, String minutes, String meridiem) {
        if(nextDate == 0) {
            action.click(estimatePage.Time());
            action.click(estimatePage.Button_Later());
            action.click(estimatePage.Button_SystemCalenderOK());
            action.sendKeys(estimatePage.Text_TimeHourPicker(), hour);
            action.sendKeys(estimatePage.Text_TimeMinutesPicker(), minutes);
            action.sendKeys(estimatePage.Text_TimeMeridian(), meridiem);
            action.click(estimatePage.Button_OKOnTimePicker());
        }
        else{
            String dayValue=generateNextDay();
            int day = Integer.parseInt(dayValue);
            day=day+nextDate;
            action.click(estimatePage.Time());
            action.click(estimatePage.Button_Later());
            String month=getCurrentMonthName();
            String year=action.getText(estimatePage.Text_Year());
            String Date=day+" "+month+" "+year;
            WebElement Select_Day=scheduledBungiisPage.findElement("//android.view.View[@content-desc='"+Date+"']", PageBase.LocatorType.XPath);
            action.click(Select_Day);
            action.click(estimatePage.Button_SystemCalenderOK());
            action.sendKeys(estimatePage.Text_TimeHourPicker(), hour);
            action.sendKeys(estimatePage.Text_TimeMinutesPicker(), minutes);
            action.sendKeys(estimatePage.Text_TimeMeridian(), meridiem);
            action.click(estimatePage.Button_OKOnTimePicker());
        }

    }

    public String generateNextDay(){
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String DateToStr = format.format(curDate);
        format = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        DateToStr = format.format(curDate);
        DateToStr.toString();
        String day=DateToStr.substring(0,2);
        return day;
    }

    public String getCurrentMonthName(){
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
         String month = monthName[cal.get(Calendar.MONTH)];
         return month;
    }

    /**
     * select bungii
     * @param bungiiType
     *            identifer for bungii type
     * @param bungiiTime
     *            Scheduled bungii time
     */
    public void selectBungii(String bungiiType, String bungiiTime) {
        Date currentDate = new Date();
        int year=currentDate.getYear()+1900;
        if(!bungiiTime.contains(utility.getTimeZoneBasedOnGeofence()))
            action.click(getLocatorForBungii(bungiiType, bungiiTime.replace(",",", "+year+" -")+" " +utility.getTimeZoneBasedOnGeofence()));
        else
            action.click(getLocatorForBungii(bungiiType, bungiiTime.replace(",",", "+year+" -")));

    }


        /**
     * Check if bungii is present
     * @param bungiiType
     *            Bungii Type , Solo /Duo
     * @param bungiiTime
     *            Scheduled bungii time
     * @return
     */
    public boolean isBungiiPresent(String bungiiType, String bungiiTime) {
        try{
            return action.isElementPresent(getLocatorForBungii(bungiiType, bungiiTime));}
        catch (Exception e){
            return  false;
        }
    }
    /**
     * Construct locator for bungii from given bungii information
     *
     * @param bungiiType
     *            identifer for bungii type
     * @param bungiiTime
     *            Scheduled bungii time
     * @return
     */
    public WebElement getLocatorForBungii(String bungiiType, String bungiiTime) {
        //By Image_SelectBungii = MobileBy.xpath("//XCUIElementTypeStaticText[@name='" + bungiiTime+ "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell");

        WebElement Image_SelectBungii=scheduledBungiisPage.findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/item_my_bungii_tv_date' and @text='"+bungiiTime+"']", PageBase.LocatorType.XPath);

        return Image_SelectBungii;
    }

}
