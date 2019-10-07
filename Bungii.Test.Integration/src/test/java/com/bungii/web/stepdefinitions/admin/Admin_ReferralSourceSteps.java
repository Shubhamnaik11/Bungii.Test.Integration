package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.stepdefinitions.customer.EstimateSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_PromoCodesPage;
import com.bungii.web.pages.admin.Admin_ReferralSourcePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.bungii.common.manager.ResultManager.log;

public class Admin_ReferralSourceSteps extends DriverBase {
    Admin_ReferralSourcePage admin_ReferralSourcePage = new Admin_ReferralSourcePage();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(EstimateSteps.class);
    List<WebElement> GridColumn1, GridColumn2, GridColumn3, GridColumn4, GridColumn5;
    String[] array = new String[0];
    String[] array1 = new String[0];

    @When("^I click on \"([^\"]*)\" header \"([^\"]*)\"$")
    public void i_click_on_something_header_something(String header, String sortOrder) throws Throwable {

        GridColumn1 = SetupManager.getDriver().findElements(By.xpath("//tr/td[1]"));
        GridColumn2 = SetupManager.getDriver().findElements(By.xpath("//tr/td[2]"));
        GridColumn3 = SetupManager.getDriver().findElements(By.xpath("//tr/td[3]"));
        GridColumn4 = SetupManager.getDriver().findElements(By.xpath("//tr/td[4]"));
        GridColumn5 = SetupManager.getDriver().findElements(By.xpath("//tr/td[5]"));

        String sort = null;
        switch (header) {
            case "Source":
                sort = admin_ReferralSourcePage.Header_Source().getAttribute("class");
                if (sortOrder.equals("Ascending")) {
                    if (!sort.equals("sorting_asc")) {
                        action.click(admin_ReferralSourcePage.Header_Source());
                    }
                } else {
                    if (!sort.equals("sorting_desc")) {
                        action.click(admin_ReferralSourcePage.Header_Source());
                    }
                }
                break;
            case "Accounts Created":
                sort = admin_ReferralSourcePage.Header_AccountsCreated().getAttribute("class");
                if (sortOrder.equals("Ascending")) {
                    if (!sort.equals("sorting_asc")) {
                        action.click(admin_ReferralSourcePage.Header_AccountsCreated());

                    }
                } else {
                    if (!sort.equals("sorting_desc")) {
                        action.click(admin_ReferralSourcePage.Header_AccountsCreated());
                    }
                }
                break;
            case "Percentage of total(Accounts Created)":
                sort = admin_ReferralSourcePage.Header_PercentageOfTotalAC().getAttribute("class");
                if (sortOrder.equals("Ascending")) {
                    if (!sort.equals("sorting_asc")) {
                        action.click(admin_ReferralSourcePage.Header_PercentageOfTotalAC());
                    }
                } else {
                    if (!sort.equals("sorting_desc")) {
                        action.click(admin_ReferralSourcePage.Header_PercentageOfTotalAC());

                    }
                }
                break;
            case "Trips Completed":
                sort = admin_ReferralSourcePage.Header_TripsCompleted().getAttribute("class");
                if (sortOrder.equals("Ascending")) {
                    if (!sort.equals("sorting_asc")) {
                        action.click(admin_ReferralSourcePage.Header_TripsCompleted());
                    }
                } else {
                    if (!sort.equals("sorting_desc")) {
                        action.click(admin_ReferralSourcePage.Header_TripsCompleted());
                    }
                }
                break;
            case "Percentage of total(Trips Completed)":
                sort = admin_ReferralSourcePage.Header_PercentageOfTotalTC().getAttribute("class");
                if (sortOrder.equals("Ascending")) {
                    if (!sort.equals("sorting_asc")) {
                        action.click(admin_ReferralSourcePage.Header_PercentageOfTotalTC());
                    }
                } else {
                    if (!sort.equals("sorting_desc")) {
                        action.click(admin_ReferralSourcePage.Header_PercentageOfTotalTC());
                    }
                }
                break;
        }
        log("I click on "+header+" to sort by order "+ sortOrder ,
                "I have clicked on "+header+" to sort by order "+ sortOrder, true);
    }

    @Then("^the \"([^\"]*)\" list should be sorted by \"([^\"]*)\" order of \"([^\"]*)\"$")
    public void the_something_list_should_be_sorted_by_something_order_of_something(String strArg1, String sortOrder, String field) throws Throwable {

        List<String> Column1 = new ArrayList<String>();
        List<String> Column2 = new ArrayList<String>();
        List<String> Column3 = new ArrayList<String>();
        List<String> Column4 = new ArrayList<String>();
        List<String> Column5 = new ArrayList<String>();

        List<String> DefaultColumn1 = new ArrayList<String>();
        List<String> DefaultColumn2 = new ArrayList<String>();
        List<String> DefaultColumn3 = new ArrayList<String>();
        List<String> DefaultColumn4 = new ArrayList<String>();
        List<String> DefaultColumn5 = new ArrayList<String>();

        List<WebElement> CurrentGridColumn1 = SetupManager.getDriver().findElements(By.xpath("//tr/td[1]"));
        List<WebElement> CurrentGridColumn2 = SetupManager.getDriver().findElements(By.xpath("//tr/td[2]"));
        List<WebElement> CurrentGridColumn3 = SetupManager.getDriver().findElements(By.xpath("//tr/td[3]"));
        List<WebElement> CurrentGridColumn4 = SetupManager.getDriver().findElements(By.xpath("//tr/td[4]"));
        List<WebElement> CurrentGridColumn5 = SetupManager.getDriver().findElements(By.xpath("//tr/td[5]"));

        for (WebElement e : CurrentGridColumn1)
            Column1.add(e.getText());
        for (WebElement e : CurrentGridColumn2)
            Column2.add(e.getText());
        for (WebElement e : CurrentGridColumn3)
            Column3.add(e.getText());
        for (WebElement e : CurrentGridColumn4)
            Column4.add(e.getText());
        for (WebElement e : CurrentGridColumn5)
            Column5.add(e.getText());
        for (WebElement e : GridColumn1)
            DefaultColumn1.add(e.getText());
        for (WebElement e : GridColumn2)
            DefaultColumn2.add(e.getText());
        for (WebElement e : GridColumn3)
            DefaultColumn3.add(e.getText());
        for (WebElement e : GridColumn4)
            DefaultColumn4.add(e.getText());
        for (WebElement e : GridColumn5)
            DefaultColumn5.add(e.getText());

        switch (field) {
            case "Source":
                Collections.sort(DefaultColumn1);
                if (sortOrder.equals("Ascending")) {
                    testStepAssert.isTrue(DefaultColumn1.equals(Column1), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                } else {
                    Collections.reverse(DefaultColumn1);
                    testStepAssert.isTrue(DefaultColumn1.equals(Column1), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                }
                break;
            case "Accounts Created":
                Collections.sort(DefaultColumn2, Comparator.comparingInt(Integer::parseInt));
                if (sortOrder.equals("Ascending")) {
                    testStepAssert.isTrue(DefaultColumn2.equals(Column2), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                } else {
                    Collections.reverse(DefaultColumn2);
                    testStepAssert.isTrue(DefaultColumn2.equals(Column2), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                }
                break;
            case "Percentage of total(Accounts Created)":
                Collections.sort(DefaultColumn3, Comparator.comparingDouble(Double::parseDouble));
                if (sortOrder.equals("Ascending")) {
                    testStepAssert.isTrue(DefaultColumn3.equals(Column3), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                } else {
                    Collections.reverse(DefaultColumn3);
                    testStepAssert.isTrue(DefaultColumn3.equals(Column3), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                }
                break;
            case "Trips Completed":
                Collections.sort(DefaultColumn4, Comparator.comparingInt(Integer::parseInt));
                if (sortOrder.equals("Ascending")) {

                    testStepAssert.isTrue(DefaultColumn4.equals(Column4), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                } else {
                    Collections.reverse(DefaultColumn4);
                    testStepAssert.isTrue(DefaultColumn4.equals(Column4), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                }
                break;
            case "Percentage of total(Trips Completed)":
                Collections.sort(DefaultColumn5, Comparator.comparingDouble(Double::parseDouble));
                if (sortOrder.equals("Ascending")) {
                    testStepAssert.isTrue(DefaultColumn5.equals(Column5), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                } else {
                    Collections.reverse(DefaultColumn5);
                    testStepAssert.isTrue(DefaultColumn5.equals(Column5), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                }
                break;
        }
    }

    @Then("^the \"([^\"]*)\" should display accurate value for each Source$")
    public void the_something_should_display_accurate_value_for_each_source(String strArg1) throws Throwable {
        GridColumn2 = SetupManager.getDriver().findElements(By.xpath("//tr/td[2]"));
        GridColumn3 = SetupManager.getDriver().findElements(By.xpath("//tr/td[3]"));

        DecimalFormat df = new DecimalFormat("0.00");
        List<String> Column2 = new ArrayList<String>();
        List<String> Column3 = new ArrayList<String>();

        List<String> Percentage1 = new ArrayList<String>();
        int PotAC = 0;
        for (WebElement e : GridColumn2) {
            Column2.add(e.getText());
            PotAC = PotAC + Integer.parseInt(e.getText());
        }
        for (WebElement e : GridColumn3) {
            Column3.add(e.getText());
        }
        int i = 0;
        for (WebElement e : GridColumn2) {
            Double Numerator = Double.parseDouble(e.getText());
            int Denominator = PotAC;
            Double value = (Numerator * 100) / Denominator;

            Percentage1.add(df.format(value).toString());
            i++;
        }

        testStepAssert.isTrue(Percentage1.equals(Column3), "Percentage of total of Accounts created should match", "Percentage of total of Accounts created doesn't match");


    }

    @And("^ the \"([^\"]*)\" should display accurate values for each Source$")
    public void the_something_should_display_accurate_values_for_each_source(String strArg1) throws Throwable {
        GridColumn4 = SetupManager.getDriver().findElements(By.xpath("//tr/td[4]"));
        GridColumn5 = SetupManager.getDriver().findElements(By.xpath("//tr/td[5]"));
        List<String> Column4 = new ArrayList<String>();
        List<String> Column5 = new ArrayList<String>();
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> Percentage2 = new ArrayList<String>();
        int PotTC = 0, i = 0;

        for (WebElement e : GridColumn4) {
            Column4.add(e.getText());
            PotTC = PotTC + Integer.parseInt(e.getText());
        }
        for (WebElement e : GridColumn5) {
            Column5.add(e.getText());
        }

        i = 0;
        for (WebElement e : GridColumn4) {
            Double Numerator = Double.parseDouble(e.getText());
            int Denominator = PotTC;
            Double value = (Numerator * 100) / Denominator;

            Percentage2.add(df.format(value).toString());
            i++;
        }
        testStepAssert.isTrue(Percentage2.equals(Column5), "Percentage of total of Trips completed should match", "Percentage of total of Trips completed doesn't match");
    }

    @When("^I click on \"([^\"]*)\" button with entering \"([^\"]*)\" and \"([^\"]*)\" date$")
    public void i_click_on_something_button_with_entering_something_and_something_date(String strArg1, String strArg2, String strArg3) throws Throwable  {
                        action.click(admin_ReferralSourcePage.Button_Search());
                        log("I click Search on Referral source page",
                "I have clicked Search on Referral source page", true);
      }

    @Then("^the \"([^\"]*)\" message is displayed beside \"([^\"]*)\" field$")
    public void the_something_message_is_displayed_beside_something_field(String message, String field) throws Throwable {

        switch(field) {
            case "From Date":
                testStepAssert.isElementTextEquals(admin_ReferralSourcePage.Label_FromDateError(), message, "From date is required should be displayed", "From date is required is displayed", "From date is required is not displayed");
                break;
            case "To Date":
            testStepAssert.isElementTextEquals(admin_ReferralSourcePage.Label_ToDateError(), message, message + " should be displayed", message+ " is displayed", message+" is not displayed");
        break;
        }
    }
    @When("^I enter \"([^\"]*)\" less than the \"([^\"]*)\"$")
    public void i_enter_something_less_than_the_something(String strArg1, String strArg2) throws Throwable {
        action.sendKeys(admin_ReferralSourcePage.TextBox_FromDate(),"11/11/2019");
        action.sendKeys(admin_ReferralSourcePage.TextBox_ToDate(),"10/11/2015");
        log("I enter From and To date on Referral source page",
                "I have entered From and To date on Referral source page", true);

    }

}
