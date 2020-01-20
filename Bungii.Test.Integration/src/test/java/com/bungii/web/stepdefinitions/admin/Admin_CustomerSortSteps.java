package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_CustomerPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.log;

public class Admin_CustomerSortSteps extends DriverBase {
    ActionManager action = new ActionManager();
    Admin_CustomerPage admin_customerPage=new Admin_CustomerPage();
    Admin_ReferralSourceSteps admin_ReferralSourceSteps = new Admin_ReferralSourceSteps();
    GeneralUtility utility= new GeneralUtility();
    private static LogUtility logger = new LogUtility(Admin_PromoCodesSteps.class);

    List<List<String>> DefaultGridData =  new ArrayList<>();

    @When("^I click on \"([^\"]*)\" header for \"([^\"]*)\" order in the \"([^\"]*)\" table$")
    public void i_click_on_something_header_for_something_order_in_the_something_table(String header, String order, String table) throws Throwable {
        cucumberContextManager.setScenarioContext("HEADER", header);

        int pageno = 2;
        String sort = null;
        //Thread.sleep(5000);
        switch (table) {
            case "Customer List":

                switch (header) {
                    case "Name":
                        action.clearSendKeys(admin_customerPage.TextBox_SearchCustomer(),"go"+ Keys.ENTER);
                        Thread.sleep(2000);
                        DefaultGridData = admin_ReferralSourceSteps.paginateAndGetGridData(6);
                        sort = admin_customerPage.Header_Name().getAttribute("class");
                        if (order.equals("Ascending")) {
                            if (!sort.equals("sorting_asc")) {
                                action.click(admin_customerPage.Header_Name());
                            }
                        } else {
                            if (!sort.equals("sorting_desc")) {
                                action.click(admin_customerPage.Header_Name());
                            }
                        }
                        break;
                    case "Customer Join Date (CST)":
                        sort = admin_customerPage.Header_CustomerJoinDate().getAttribute("class");
                        if (order.equals("Ascending")) {
                            if (!sort.equals("sorting_asc")) {
                                action.click(admin_customerPage.Header_CustomerJoinDate());

                            }
                        } else {
                            if (!sort.equals("sorting_desc")) {
                                action.click(admin_customerPage.Header_CustomerJoinDate());
                            }
                        }
                        break;
                    case "Trips Requested":
                        sort = admin_customerPage.Header_TripsRequested().getAttribute("class");
                        if (order.equals("Ascending")) {
                            if (!sort.equals("sorting_asc")) {
                                action.click(admin_customerPage.Header_TripsRequested());
                            }
                        } else {
                            if (!sort.equals("sorting_desc")) {
                                action.click(admin_customerPage.Header_TripsRequested());

                            }
                        }
                        break;
                    case "Trips Estimated":
                        sort = admin_customerPage.Header_TripsEstimated().getAttribute("class");
                        if (order.equals("Ascending")) {
                            if (!sort.equals("sorting_asc")) {
                                action.click(admin_customerPage.Header_TripsEstimated());
                            }
                        } else {
                            if (!sort.equals("sorting_desc")) {
                                action.click(admin_customerPage.Header_TripsEstimated());
                            }
                        }
                        break;
                    case "Spent":
                        sort = admin_customerPage.Header_Spent().getAttribute("class");
                        if (order.equals("Ascending")) {
                            if (!sort.equals("sorting_asc")) {
                                action.click(admin_customerPage.Header_Spent());
                            }
                        } else {
                            if (!sort.equals("sorting_desc")) {
                                action.click(admin_customerPage.Header_Spent());
                            }
                        }
                        break;

                    case "Last Activity (CST)":
                        sort = admin_customerPage.Header_LastActivity().getAttribute("class");
                        if (order.equals("Ascending")) {
                            if (!sort.equals("sorting_asc")) {
                                action.click(admin_customerPage.Header_LastActivity());
                            }
                        } else {
                            if (!sort.equals("sorting_desc")) {
                                action.click(admin_customerPage.Header_LastActivity());
                            }
                        }
                        break;
                }
                break;

        }
        log("I click on "+header+" to sort by order "+ order ,
                "I have clicked on "+header+" to sort by order "+ order, true);
    }



    @Then("^The column \"([^\"]*)\" data gets sorted in \"([^\"]*)\" order in the \"([^\"]*)\" table$")
    public void the_column_something_data_gets_sorted_in_something_order_in_the_something_table(String field, String order, String table) throws Throwable {
        ArrayList<String> dateList = new ArrayList<String>();
        DateFormat parser = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
        DateFormat parser2 = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
        int pageno = 2;
        List<List<String>> CurrentGridData =  new ArrayList<>();
        ArrayList<String> gridData = new ArrayList<String>();

        switch(table) {
            case "Customer List":
                CurrentGridData = admin_ReferralSourceSteps.paginateAndGetGridData(6);

                switch (field) {
                    case "Name":
                        Collections.sort(DefaultGridData.get(0));
                        if (order.equals("Ascending")) {
                            testStepAssert.isTrue(DefaultGridData.get(0).equals(CurrentGridData.get(0)), field + " should sort by " + order, field + " is not sorted by " + order);
                        } else {
                            Collections.reverse(DefaultGridData.get(0));
                            testStepAssert.isTrue(DefaultGridData.get(0).equals(CurrentGridData.get(0)), field + " should sort by " + order, field + " is not sorted by " + order);
                        }
                        break;
                    case "Customer Join Date (CST)":
                        for (String dateString : CurrentGridData.get(1)) {
                            try {
                                Date convertedDate = parser.parse(dateString);
                                String date = parser2.format(convertedDate);
                                gridData.add(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        for (String dateString : DefaultGridData.get(1)) {
                            try {
                                Date convertedDate = parser.parse(dateString);
                                String date = parser2.format(convertedDate);
                                //date=utility.FirstAndLast(date);
                                dateList.add(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a");
                        Collections.sort(dateList, (s1, s2) -> LocalDate.parse(s1, formatter).compareTo(LocalDate.parse(s2, formatter)));
                        dateList.replaceAll(String::toUpperCase);

                        Collections.sort(gridData, (s1, s2) -> LocalDate.parse(s1, formatter).compareTo(LocalDate.parse(s2, formatter)));
                        gridData.replaceAll(String::toUpperCase);

                        if (order.equals("Ascending")) {
                            testStepAssert.isTrue(dateList.equals(gridData.get(1)), field + " should sort by " + order, field + " is not sorted by " + order);
                        } else {
                            Collections.reverse(dateList);
                            testStepAssert.isTrue(dateList.equals(gridData.get(1)), field + " should sort by " + order, field + " is not sorted by " + order);
                        }
                        break;
                    case "Trips Requested":
                        Collections.sort(DefaultGridData.get(2), Comparator.comparingInt(Integer::parseInt));
                        if (order.equals("Ascending")) {
                            testStepAssert.isTrue(DefaultGridData.get(2).equals(CurrentGridData.get(2)), field + " should sort by " + order, field + " is not sorted by " + order);
                        } else {
                            Collections.reverse(DefaultGridData.get(2));
                            testStepAssert.isTrue(DefaultGridData.get(2).equals(CurrentGridData.get(2)), field + " should sort by " + order, field + " is not sorted by " + order);
                        }
                        break;
                    case "Trips Estimated":
                        Collections.sort(DefaultGridData.get(3), Comparator.comparingInt(Integer::parseInt));
                        if (order.equals("Ascending")) {

                            testStepAssert.isTrue(DefaultGridData.get(3).equals(CurrentGridData.get(3)), field + " should sort by " + order, field + " is not sorted by " + order);
                        } else {
                            Collections.reverse(DefaultGridData.get(3));
                            testStepAssert.isTrue(DefaultGridData.get(3).equals(CurrentGridData.get(3)), field + " should sort by " + order, field + " is not sorted by " + order);
                        }
                        break;
                    case "Spent":
                        Collections.sort(DefaultGridData.get(4), Comparator.comparingInt(Integer::parseInt));
                        if (order.equals("Ascending")) {

                            testStepAssert.isTrue(DefaultGridData.get(4).equals(CurrentGridData.get(4)), field + " should sort by " + order, field + " is not sorted by " + order);
                        } else {
                            Collections.reverse(DefaultGridData.get(4));
                            testStepAssert.isTrue(DefaultGridData.get(4).equals(CurrentGridData.get(4)), field + " should sort by " + order, field + " is not sorted by " + order);
                        }
                        break;
                    case "Last Activity (CST)":
                        Collections.sort(DefaultGridData.get(5), Comparator.comparingDouble(Double::parseDouble));
                        if (order.equals("Ascending")) {
                            testStepAssert.isTrue(DefaultGridData.get(5).equals(CurrentGridData.get(5)), field + " should sort by " + order, field + " is not sorted by " + order);
                        } else {
                            Collections.reverse(DefaultGridData.get(5));
                            testStepAssert.isTrue(DefaultGridData.get(5).equals(CurrentGridData.get(5)), field + " should sort by " + order, field + " is not sorted by " + order);
                        }
                        break;
                }
                break;
        }

    }

}