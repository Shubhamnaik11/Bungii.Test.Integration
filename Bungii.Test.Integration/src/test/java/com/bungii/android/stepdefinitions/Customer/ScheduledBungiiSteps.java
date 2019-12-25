package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.BungiiDetailsPage;
import com.bungii.android.pages.customer.EstimatePage;
import com.bungii.android.pages.customer.HomePage;
import com.bungii.android.pages.customer.ScheduledBungiisPage;
import com.bungii.android.stepdefinitions.CommonSteps;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bungii.common.manager.ResultManager.*;

public class ScheduledBungiiSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(PromosSteps.class);
    ActionManager action = new ActionManager();
    ScheduledBungiisPage scheduledBungiisPage;
    GeneralUtility utility = new GeneralUtility();
    CommonSteps commonSteps = new CommonSteps();

    BungiiDetailsPage bungiiDetailsPage= new BungiiDetailsPage();
    EstimatePage estimatePage=new EstimatePage();
     HomePage homePage=new HomePage();
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

    @When("^I try to schedule bungii for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_try_to_schedule_bungii_for_something_for_something(String strArg1, String tripType) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "today - after working hour":
                    selectBungiiTime(0, "11", "45", "PM", tripType);
                    //log("I select time for trip as 11:45  pm", "I selected time for trip as 11:45  pm");
                    break;
                case "tommorow - before working hour":
                    selectBungiiTime(1, "12", "00", "AM",tripType);
                    //log("I select time for trip tomorrow 12 00 AM", "I selected time for trip as  tomorrow 12 00 AM");
                    break;
                case "today+5":
                    selectBungiiTime(5, "", "", "",tripType);
                    log("I select time for trip today+5 1:00 pm", "I selected time for trip as today+5 1:00 pm");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");

            } }catch(Exception e){
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
               // error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
            }
        }

    @When("^I confirm trip with following detail$")
    public void i_confirm_trip_with_following_detail(DataTable tripInformation) throws Throwable {
        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String day = data.get("Day"),
                    tripType=data.get("Trip Type");

            selectBungiiTime(Integer.parseInt(day), "05", "30", "PM",tripType);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I confirm trip with following details$")
    public void i_confirm_trip_with_following_details(DataTable tripInformation) throws Throwable {
        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String day = data.get("Day"),
                    tripType=data.get("Trip Type"),
                    time=data.get("Time");
            if(time.equalsIgnoreCase("<TIME WITHIN TELET>")){

                String teletTime=(String) cucumberContextManager.getScenarioContext("TELET");
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                //By default data is in UTC
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date teletTimeInUtc = null;
                try {
                    teletTimeInUtc = formatter.parse(teletTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(teletTimeInUtc);
                int mnts = calendar.get(Calendar.MINUTE);

                calendar.set(Calendar.MINUTE, mnts - 30);
                int unroundedMinutes = calendar.get(Calendar.MINUTE);
                int mod = unroundedMinutes % 15;
                calendar.add(Calendar.MINUTE, (15 - mod));
                calendar.set(Calendar.SECOND, 0);

                Date nextQuatter = calendar.getTime();
                String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();

                DateFormat formatterForLocalTimezone  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

                formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

                String strdate = formatter.format(calendar.getTime());
                Date teletTimeInLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(strdate);



                String[] dateScroll = commonSteps.bungiiTimeForScroll(teletTimeInLocal);
                //String strTime = commonSteps.bungiiTimeDisplayInTextArea(teletTimeInLocal);
                //action.click(estimatePage.Row_TimeSelect());
                selectBungiiTime(Integer.parseInt(day), dateScroll[1], dateScroll[2], dateScroll[3], tripType);

            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" screen$")
    public void i_click_something_button_on_something_screen(String strArg1, String strArg2) throws Throwable {
        try {
            action.scrollToBottom();
            action.click(estimatePage.Button_DoneOnSuccess());
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
    }

    @When("^I try to schedule bungii for \"([^\"]*)\"$")
    public void i_try_to_schedule_bungii_for_something(String strArg1, DataTable tripInformation) throws Throwable {
        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String day = data.get("Day"), tripType=data.get("Trip Type");

            selectBungiiTime(Integer.parseInt(day), "05", "30", "PM",tripType);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
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
    public void selectBungiiTime(int nextDate, String hour, String minutes, String meridiem, String tripType) {
        try {
            if (nextDate == 0) {
                action.click(estimatePage.Time());
                if (tripType.equals("SOLO")) {
                    action.click(estimatePage.Button_Later());
                }
                action.click(estimatePage.Button_SystemCalenderOK());
                action.sendKeys(estimatePage.Text_TimeHourPicker(), hour);
                action.sendKeys(estimatePage.Text_TimeMinutesPicker(), minutes);
                action.sendKeys(estimatePage.Text_TimeMeridian(), meridiem);
                action.click(estimatePage.Button_OKOnTimePicker());
            } else if (nextDate > 0 && nextDate < 5) {
                String month = getCurrentMonthName();
                String dayValue = generateNextDay();
                int day = Integer.parseInt(dayValue);
                if(day== 30 || day ==31)
                {
                    day=1;
                    month=getNextMonthName();
                }

                day = day + nextDate;

                action.click(estimatePage.Time());
                Thread.sleep(1000);
                if (tripType.equals("SOLO")) {
                    action.click(estimatePage.Button_Later());
                }
                String year="";
                if(day==31 && month.equals("DECEMBER"))
                {
                    Calendar now = Calendar.getInstance();
                    int y = now.get(Calendar.YEAR)+1;
                    year=String.valueOf(y);
                }
                else {
                    year= action.getText(estimatePage.Text_Year());
                }

                String Date = day + " " + month + " " + year;
                WebElement Select_Day = scheduledBungiisPage.findElement("//android.view.View[@content-desc='" + Date + "']", PageBase.LocatorType.XPath);
                action.click(Select_Day);
                action.click(estimatePage.Button_SystemCalenderOK());
                action.sendKeys(estimatePage.Text_TimeHourPicker(), hour);
                action.sendKeys(estimatePage.Text_TimeMinutesPicker(), minutes);
                action.sendKeys(estimatePage.Text_TimeMeridian(), meridiem);
                action.click(estimatePage.Button_OKOnTimePicker());
            } else if (nextDate == 5) {
                String dayValue = generateNextDay();
                int day = Integer.parseInt(dayValue);
                day = day + nextDate;
                action.click(estimatePage.Time());
                Thread.sleep(1000);
                if (tripType.equals("SOLO")) {
                    action.click(estimatePage.Button_Later());
                }
                String month = getCurrentMonthName();
                String year = action.getText(estimatePage.Text_Year());
                String Date = day + " " + month + " " + year;
                WebElement Select_Day = scheduledBungiisPage.findElement("//android.view.View[@content-desc='" + Date + "']", PageBase.LocatorType.XPath);
                testStepVerify.isElementNotEnabled(Select_Day,String.valueOf(day),"Element is not enabled.","Element is enabled.");
            }
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
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

    public String getNextMonthName(){
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        String month = monthName[cal.get(Calendar.MONTH)+1];
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
