package com.bungii.web.stepdefinitions.partner;

import com.bungii.common.core.DriverBase;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import com.bungii.web.pages.partner.Partner_Done;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.WebElement;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;


import java.util.*;


public class Partner_Reports_Steps extends DriverBase {

    ActionManager action = new ActionManager();
    Partner_Done Page_Partner_Done = new Partner_Done();
    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();

    @And("^The dropdown menu should have an \"([^\"]*)\" icon$")
    public void the_dropdown_menu_should_have_an_something_icon(String strArg1) throws Throwable {
        WebElement source = Page_Partner_Delivery.Image_dropdown_Setting();
        String hamburgerIconpath = "https://qaauto-brandsmart.gobungii-dev.com/static/media/head-hamburger.deeb6afd.svg";
        Thread.sleep(1000);
        testStepAssert.isEquals(source.getAttribute("src"),hamburgerIconpath,"Hamburger icon path should match","Hamburger icon path matches the expected path","Hamburger icon path doesnt match the expected path");
        testStepAssert.isTrue(source.isDisplayed(),"Hamburger icon should be displayed","Hamburger icon is displayed", "Hamburger icon is not displayed");


    }


    @When("^I click on the \"([^\"]*)\" button on the top right side of the page$")
    public void i_click_on_the_something_button_on_the_top_right_side_of_the_page(String strArg1) throws Throwable {
        Thread.sleep(5000);
        action.click(Page_Partner_Done.Dropdown_Setting());
    }

    @Then("^I should see \"([^\"]*)\" as an option$")
    public void i_should_see_something_as_an_option(String expectedOption) throws Throwable {
        List<WebElement> dropdownSettings = Page_Partner_Done.List_Dropdown_Setting();
        for (int i = 0; i < dropdownSettings.size(); i++) {
            dropdownSettings.get(i).getText();
            if (dropdownSettings.get(i).getText().contains(expectedOption)) {
                String option = dropdownSettings.get(i).getText();
                testStepAssert.isEquals(option,expectedOption,option+" option should be displayed",option+ " option  is displayed",option + " option is not displayed");
                break;
            }
        }
    }

    @When("^I click on the \"([^\"]*)\" link$")
    public void i_click_on_the_something_link(String text) throws Throwable {
        switch (text){
            case "Reports":
                action.click(Page_Partner_Delivery.Link_Report());
                break;
            case "Today":
                action.click(Page_Partner_Delivery.Link_ReportFilter(text));
                String filePath ="C:\\Users\\allan.fernandes\\Downloads\\Partner-deliveries.csv";
                File file = new File(filePath);
                boolean fileShouldNotExist =file.exists();
                testStepAssert.isFalse(fileShouldNotExist,"File should not exist in the directory","File doesnt exist in the directory","File exists in the directory");
                break;
        }


    }

    @Then("^I should see \"([^\"]*)\" message on the popup$")
    public void i_should_see_something_message_on_the_popup(String text) throws Throwable {
        Thread.sleep(1000);;
        testStepAssert.isTrue(action.isElementPresent(Page_Partner_Delivery.Label_Report_HeaderPopup()),text+" text  should be displayed", text+" text is displayed", text+" text is not displayed");
        Thread.sleep(1000);
        boolean buttonState = Page_Partner_Delivery.Button_GenerateReport().isEnabled();
        testStepAssert.isFalse(buttonState,"Generate report button should be disabled","Generate report button is disabled","Generate report button is not disabled");
//        int calenders = Page_Partner_Delivery.Text_CalenderCount().toString().length()-1;
//        testStepAssert.isTrue(calenders ==2,"2 calenders should be displayed","2 calenders are displayed","2 calenders are not displayed");
//        int filters = Page_Partner_Delivery.Text_FilterList().toString().length();
//        testStepAssert.isTrue(filters ==7,"7 filter methods should be displayed","7 filter methods are displayed","7 filter methods are not displayed");






    }

    @When("^I select the month which is two months ahead of the current month$")
    public void i_select_the_month_which_is_two_months_ahead_of_the_current_month() throws Throwable {
        action.click(Page_Partner_Delivery.Dropdown_Calender2());
        Thread.sleep(1000);
        int currentMonthIndex  =((Calendar. getInstance(). get(Calendar. MONTH)+1) + 2);
        action.click(Page_Partner_Delivery.Text_MonthOfTheYear(currentMonthIndex));
    }

    @Then("^The dates should be disabled$")
    public void the_dates_should_be_disabled() throws Throwable {
        Thread.sleep(1000);
        String dateDisabled = Page_Partner_Delivery.Text_DisabledDate().getAttribute("disabled");
        testStepAssert.isTrue(dateDisabled.equals("true"),"Date should be disabled","Date is disabled","Date is not disabled");


    }

    @When("^I click on the below filter options i should see the respective date displayed$")
    public void i_click_on_the_below_filter_options_i_should_see_the_respective_date_displayed(DataTable data) throws Throwable {
        List<Map<String, String>> DataList = data.asMaps();
        int i = 0;
        while (i < DataList.size()) {
            Thread.sleep(1000);
            String filterBy = DataList.get(i).get("Filter DateBy").trim();
            String[] monthAndYear = LocalDate.now().toString().split("-");
            int year = Integer.parseInt( monthAndYear[0]);
            switch (filterBy) {
                case "Today":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    String[] date = LocalDate.now().toString().split("-");
                    String currentDate = date[2];

                    if (currentDate.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("DateOfToday",currentDate.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("DateOfToday",currentDate);
                    }

                    List<WebElement> dateForToday= Page_Partner_Delivery.List_AllDatesOfTheMonth();
                    String uiTodaysDate = dateForToday.get(0).getText();

                   testStepAssert.isEquals(uiTodaysDate, (String) cucumberContextManager.getScenarioContext("DateOfToday"), "Todays date should be" + (String) cucumberContextManager.getScenarioContext("DateOfToday"), "Todays date is " + uiTodaysDate, "The wrong date displayed is " + uiTodaysDate);
                   break;
                case "Yesterday":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    LocalDate today = LocalDate.now();
                    String[] previousdate = (today.minusDays(1)).format(DateTimeFormatter.ISO_DATE).split("-");
                    String yesterdaysDate = previousdate[2];

                    if (yesterdaysDate.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("DateOfYesterday",yesterdaysDate.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("DateOfYesterday",yesterdaysDate);
                    }

                    List<WebElement> dateForYesterday= Page_Partner_Delivery.List_AllDatesOfTheMonth();
                    String uiYesterdayssDate = dateForYesterday.get(0).getText().replace("0","");

                    testStepAssert.isEquals(uiYesterdayssDate, (String) cucumberContextManager.getScenarioContext("DateOfYesterday"), "Yesterdays date should be " + (String) cucumberContextManager.getScenarioContext("DateOfYesterday"), "Yesterdays date is " + uiYesterdayssDate, "The wrong date displayed is " + uiYesterdayssDate);
                    break;
                case "This Week":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    LocalDate todaysDate = LocalDate.now();

                    LocalDate sunday = todaysDate;
                    while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
                        sunday = sunday.minusDays(1);
                    }
                    LocalDate saturday = todaysDate;
                    while (saturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
                        saturday = saturday.plusDays(1);
                    }

                    String[] weekStart = sunday.toString().split("-");
                    String[] weekEnd = saturday.toString().split("-");
                    String startOfThisWeek = weekStart[2] ;
                    String endOfThisWeek = weekEnd[2];


                    if (startOfThisWeek.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("StartOfTheWeekDate",startOfThisWeek.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("StartOfTheWeekDate",startOfThisWeek);
                    }

                    if (endOfThisWeek.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("EndOfTheWeekDate",endOfThisWeek.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("EndOfTheWeekDate",endOfThisWeek);
                    }

                    List<WebElement> allDatesForThisWeek = Page_Partner_Delivery.List_AllDatesOfTheMonth();


                    ArrayList dates = new ArrayList();
                    for (int j = 0; j < allDatesForThisWeek.size(); j++) {
                        dates.add(allDatesForThisWeek.get(j).getText());
                    }

                    testStepAssert.isEquals(dates.get(0).toString(),(String) cucumberContextManager.getScenarioContext("StartOfTheWeekDate"), "Start of this week should be " + (String) cucumberContextManager.getScenarioContext("StartOfTheWeekDate"), "Start of this week is " + (String) cucumberContextManager.getScenarioContext("StartOfTheWeekDate"), "Wrong end date of this  " + dates.get(0).toString());
                    testStepAssert.isEquals(dates.get(1).toString(), (String) cucumberContextManager.getScenarioContext("EndOfTheWeekDate"), "End of this week should be " + (String) cucumberContextManager.getScenarioContext("EndOfTheWeekDate"), "End of this week is " + (String) cucumberContextManager.getScenarioContext("EndOfTheWeekDate"), "Wrong end date of this week " + dates.get(1).toString());
                    break;
                case "Last Week":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    final ZonedDateTime input = ZonedDateTime.now();
                    final ZonedDateTime startOfLastWeek = input.minusWeeks(2).with(DayOfWeek.SUNDAY);
                    final ZonedDateTime endOfLastWeek = startOfLastWeek.plusDays(6);

                    String[] weekStarts = startOfLastWeek.format(DateTimeFormatter.ISO_LOCAL_DATE).split("-");
                    String[] weekEnds= endOfLastWeek.format(DateTimeFormatter.ISO_LOCAL_DATE).split("-");
                    String LastWeekStart = weekStarts[2];
                    String LastWeekEnd = weekEnds[2];

                    if (LastWeekStart.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("StartOfLastWeekDate",LastWeekStart.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("StartOfLastWeekDate",LastWeekStart);
                    }

                    if (LastWeekEnd.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("EndOfLastWeekDate",LastWeekEnd.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("EndOfLastWeekDate",LastWeekEnd);
                    }

                    List<WebElement> allDatesForLastWeek = Page_Partner_Delivery.List_AllDatesOfTheMonth();
                    ArrayList datesForLastWeek = new ArrayList();
                    for (int j = 0; j < allDatesForLastWeek.size(); j++) {
                        datesForLastWeek.add(allDatesForLastWeek.get(j).getText());
                    }

                    testStepAssert.isEquals( datesForLastWeek.get(0).toString(), (String) cucumberContextManager.getScenarioContext("StartOfLastWeekDate"),"Start of last week should be "+ (String) cucumberContextManager.getScenarioContext("StartOfLastWeekDate"),"Start of last week is "+ datesForLastWeek.get(0).toString(),"Wrong end date of last week "+ datesForLastWeek.get(0).toString());
                    testStepAssert.isEquals( datesForLastWeek.get(1).toString(),(String) cucumberContextManager.getScenarioContext("EndOfLastWeekDate"),"End of last week should be "+ (String) cucumberContextManager.getScenarioContext("EndOfLastWeekDate"),"End of last week is "+ datesForLastWeek.get(1).toString(),"Wrong end date of last week "+ datesForLastWeek.get(1).toString());
                    break;
                case "Last 7 Days":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    String[] weekBeforeFirst = LocalDate.now().minusDays(7).toString().replace("0","").split("-");
                    String[] weekBeforeLast= LocalDate.now().toString().replace("0","").split("-");
                    String firstDayofLastSevenDays = weekBeforeFirst[2];
                    String LasttDayofLastSevenDays = weekBeforeLast[2];

                    List<WebElement> lastSevenDays = Page_Partner_Delivery.List_AllDatesOfTheMonth();
                    ArrayList datesForLastSevenDays= new ArrayList();
                    for (int j = 0; j < lastSevenDays.size(); j++) {
                        datesForLastSevenDays.add(lastSevenDays.get(j).getText());
                    }

                    if (firstDayofLastSevenDays.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("DayOneOfSeven",firstDayofLastSevenDays.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("DayOneOfSeven",firstDayofLastSevenDays);
                    }

                    if (LasttDayofLastSevenDays.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("DaySevenOfSeven",LasttDayofLastSevenDays.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("DaySevenOfSeven",LasttDayofLastSevenDays);
                    }

                    testStepAssert.isEquals( datesForLastSevenDays.get(0).toString(),(String) cucumberContextManager.getScenarioContext("DayOneOfSeven"),"Starting date should be "+ (String) cucumberContextManager.getScenarioContext("DayOneOfSeven"),"Starting date is "+ datesForLastSevenDays.get(0).toString(),"Wrong starting date "+ datesForLastSevenDays.get(0).toString());
                    testStepAssert.isEquals( datesForLastSevenDays.get(1).toString(),(String) cucumberContextManager.getScenarioContext("DaySevenOfSeven"),"Ending date should be "+ (String) cucumberContextManager.getScenarioContext("DaySevenOfSeven"),"Ending date is "+ datesForLastSevenDays.get(1).toString(),"Wrong ending date "+ datesForLastSevenDays.get(1).toString());
                    break;
                case "This Month":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    int month = Integer.parseInt( monthAndYear[1]);
                    YearMonth yearMonth = YearMonth.of( year , month );
                    String[] endOfMonthDate = yearMonth.atEndOfMonth().toString().split("-");
                    String[] StartOfMonthDate = MonthDay.of(month,1).toString().replace("0","").split("-");
                    String CurrentMonthEndDate = endOfMonthDate[2];
                    String CurrentMonthStartDate = StartOfMonthDate[3];

                    List<WebElement> currentMonth = Page_Partner_Delivery.List_AllDatesOfTheMonth();
                    ArrayList datesForCurrentMonth= new ArrayList();
                    for (int j = 0; j < currentMonth.size(); j++) {
                        datesForCurrentMonth.add(currentMonth.get(j).getText());
                    }

                    testStepAssert.isEquals( datesForCurrentMonth.get(0).toString(),CurrentMonthStartDate,"Starting date of the month should be "+ CurrentMonthStartDate,"Starting date of the month is "+ datesForCurrentMonth.get(0).toString(),"Wrong starting date of the month "+ datesForCurrentMonth.get(0).toString());
                    testStepAssert.isEquals( datesForCurrentMonth.get(1).toString(),CurrentMonthEndDate,"Ending date of the month should be  "+ CurrentMonthEndDate,"Ending  date of the month is "+ datesForCurrentMonth.get(1).toString(),"Wrong ending date of the month"+ datesForCurrentMonth.get(1).toString());
                    break;
                case "Last Month":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    int previousMonth = Integer.parseInt( monthAndYear[1])-1;
                        if(previousMonth ==0){
                        YearMonth forDecember = YearMonth.of( year , 12 );
                        String[] endOfLastMonthDate = forDecember.atEndOfMonth().toString().split("-");
                        String[] StartOfLastMonthDate = MonthDay.of(previousMonth,1).toString().replace("0","").split("-");
                        String lastMonthEndDate = endOfLastMonthDate[2];
                        String lastMonthStartDate = StartOfLastMonthDate[3];


                        List<WebElement> lastMonth = Page_Partner_Delivery.List_AllDatesOfTheMonth();
                        ArrayList datesForLastMonth= new ArrayList();
                        for (int j = 0; j < lastMonth.size(); j++) {
                            datesForLastMonth.add(lastMonth.get(j).getText());
                        }

                        testStepAssert.isEquals( datesForLastMonth.get(0).toString(),lastMonthStartDate,"Starting date of last month should be "+ lastMonthStartDate,"Starting date of last month is "+ datesForLastMonth.get(0).toString(),"Wrong starting date of last month "+ datesForLastMonth.get(0).toString());
                        testStepAssert.isEquals( datesForLastMonth.get(1).toString(),lastMonthEndDate,"Ending date of last month should be  "+ lastMonthEndDate,"Ending  date of last month is "+ datesForLastMonth.get(1).toString(),"Wrong ending date of last month"+ datesForLastMonth.get(1).toString());
                        break;
                    }
                        else {

                            YearMonth forDecember = YearMonth.of(year, previousMonth);
                            String[] endOfLastMonthDate = forDecember.atEndOfMonth().toString().split("-");
                            String[] StartOfLastMonthDate = MonthDay.of(previousMonth, 1).toString().replace("0","").split("-");
                            String lastMonthEndDate = endOfLastMonthDate[2];
                            String lastMonthStartDate = StartOfLastMonthDate[3];


                            List<WebElement> lastMonth = Page_Partner_Delivery.List_AllDatesOfTheMonth();
                            ArrayList datesForLastMonth = new ArrayList();
                            for (int j = 0; j < lastMonth.size(); j++) {
                                datesForLastMonth.add(lastMonth.get(j).getText());
                            }

                            testStepAssert.isEquals(datesForLastMonth.get(0).toString(), lastMonthStartDate, "Starting date of last month should be " + lastMonthStartDate, "Starting date of last month is " + datesForLastMonth.get(0).toString(), "Wrong starting date of last month " + datesForLastMonth.get(0).toString());
                            testStepAssert.isEquals(datesForLastMonth.get(1).toString(), lastMonthEndDate, "Ending date of last month should be  " + lastMonthEndDate, "Ending  date of last month is " + datesForLastMonth.get(1).toString(), "Wrong ending date of last month" + datesForLastMonth.get(1).toString());
                            break;
                        }
            }

            i++;
        }
    }



    @Then("^The csv file should get downloaded having name \"([^\"]*)\"$")
    public void the_csv_file_should_get_downloaded_having_name_something(String expectedText) throws Throwable {
        Thread.sleep(7000);
        String filePath ="C:\\Users\\allan.fernandes\\Downloads\\Partner-deliveries.csv";
        File file = new File(filePath);
       if (file.exists()){
           String fileName = file.getName();
           testStepAssert.isEquals(fileName,expectedText,"File names should match","File names matches","File names dont match");
           boolean deleteFile =  file.delete();
           testStepAssert.isFalse(deleteFile,"File should be deleted","File is deleted","File is not deleted");
       }
       else {
           testStepAssert.isFail("File is not present in the directory");
       }
    }




}


