package com.bungii.ios.stepdefinitions.admin;

import com.bungii.SetupManager;
//import com.bungii.android.pages.admin.DriversPage;
//import com.bungii.android.pages.admin.LiveTripsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.pages.admin.*;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import com.bungii.web.manager.ActionManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class LiveTripsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    LiveTripsPage liveTripsPage = new LiveTripsPage();
    ActionManager action = new ActionManager();
    DriversPage driversPage = new DriversPage();
    GeneralUtility utility = new GeneralUtility();

    @Then("^I select trip from live trips$")
    public void i_select_trip_from_live_trips() throws Throwable {
        try {
            String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            action.sendKeys(liveTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
            action.click(liveTripsPage.Button_Search());
            Thread.sleep(5000);
            action.click(liveTripsPage.Button_StartDateSort());Thread.sleep(2000);
            action.click(liveTripsPage.Image_Three_Dot());
            action.click(liveTripsPage.Link_View_Delivery_Details());
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in viewing delivery from live deliveries",
                    true);
        }
    }

    @Then("^I select trip from all deliveries$")
    public void i_select_trip_from_all_deliveries() throws Throwable {
        try {
            //Thread.sleep(120000);
            String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            action.sendKeys(liveTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
            action.click(liveTripsPage.Button_Search());
            Thread.sleep(5000);
            action.click(liveTripsPage.Button_RowOneAll());
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting trip from All deliveries",
                    true);
        }
    }
    @Then("^I select trip from trips$")
    public void i_select_trip_from_trips() throws Throwable {
        try {
            //Thread.sleep(120000);
            String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            action.sendKeys(liveTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
            action.click(liveTripsPage.Button_Search());

            action.click(liveTripsPage.Button_RowOne());
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting trip from All deliveries",
                    true);
        }
    }
    @And("^I select \"([^\"]*)\" from search peroid$")
    public void i_select_something_from_search_peroid(String strArg1) throws Throwable {
        try {
            Select dropdown = new Select(liveTripsPage.Text_SearchPeroid());
            dropdown.selectByVisibleText("The Beginning of Time");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }    }
    @Then("^I search driver from drivers$")
    public void i_select_driver_from_drivers() throws Throwable {
        try {
            String driver1Name = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            action.sendKeys(liveTripsPage.Text_SearchCriteria(), driver1Name.substring(0, driver1Name.indexOf(" ")));
            action.click(liveTripsPage.Button_Search());
            Thread.sleep(5000);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^rattings should be correctly displayed on grid$")
    public void rattings_should_be_correctly_displayed() throws Throwable {
        try {
            String currentRatting = (String) cucumberContextManager.getScenarioContext("DRIVER_CURRENT_RATTING");
            testStepVerify.isElementTextEquals(driversPage.Text_DriverRatting(),currentRatting);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^rattings should be correctly displayed on profile$")
    public void rattings_should_be_correctly_displayedProfile() throws Throwable {
        try {
            action.click(driversPage.Button_DriverProfileLink());
            String currentRatting = (String) cucumberContextManager.getScenarioContext("DRIVER_CURRENT_RATTING");
            testStepVerify.isElementTextEquals(driversPage.Text_DriverRattingProfile(),currentRatting);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^manually end bungii should be \"([^\"]*)\"$")
    public void manually_end_bungii_should_be_something(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "enabled":
                    testStepVerify.isElementEnabled(liveTripsPage.Button_ManuallyEndBungii(true), " Manually end bungii should be enabled");
                    break;
                case "disabled":
                    testStepVerify.isElementNotEnabled(liveTripsPage.Button_ManuallyEndBungii(true), " Manually end bungii should be disabled", " Manually end bungii is disabled", " Manually end bungii is enabled");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^On admin trip details page \"([^\"]*)\" should be displayed$")
    public void verifyTrip(String strArg1) throws Throwable {
        try {
            String PromoValue = String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE")).replace("-", "").replace(".00", "");
            String Promo = String.valueOf(cucumberContextManager.getScenarioContext("ADDED_PROMO_CODE"));
            String discountValue = String.valueOf(cucumberContextManager.getScenarioContext("DISCOUNT_VALUE"));
            String bungiiCostCustomer = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_COST_CUSTOMER"));

            double dblDiscountValue=Double.parseDouble((discountValue).replace("$",""));
            String truncValue = new DecimalFormat("#.##").format(dblDiscountValue); //truncValue=truncValue.replace(".00", "");//commented as 0.00 is not truncated
            String displayValue = new DecimalFormat("0.00").format(dblDiscountValue); //truncValue=truncValue.replace(".00", "");//commented as 0.00 is not truncated

            //decimal formating
            bungiiCostCustomer=new DecimalFormat("0.00").format(Double.parseDouble(bungiiCostCustomer.replace("$","")));
           // bungiiCostCustomer = bungiiCostCustomer.replace(".00", ""); //commented as 0.00 is not truncated
            bungiiCostCustomer="$"+bungiiCostCustomer;
            logger.detail("bungii cost to customer "+bungiiCostCustomer);

            String code = action.getText(liveTripsPage.Text_Code());
            String codeType = action.getText(liveTripsPage.Text_CodeType());
            String codeValue = action.getText(liveTripsPage.Text_CodeValue());
            String promoCode = action.getText(liveTripsPage.Text_PromoCode());
            String tripPayment = action.getText(liveTripsPage.Text_TripPayment());


            switch (strArg1.toLowerCase()) {
                case "promo":
                    logger.detail("bungii cost to customer after replacing"+bungiiCostCustomer);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_Code(), Promo ,Promo+" should be displayed", code+" is displayed",code +" is displayed instead of "+Promo);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_CodeType(), "Promo","Promo should be displayed",codeType +" is displayed",codeType +" is displayed instead of Promo");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue, PromoValue+" should be displayed",codeValue +" is displayed",codeValue +" is displayed instead of "+PromoValue);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + displayValue + " (" + Promo + " - " + PromoValue + ")", "$" + truncValue + " (" + Promo + " - " + PromoValue + ")"+" should be displayed",promoCode +" is displayed",promoCode +" is displayed instead of "+"$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_TripPayment(), bungiiCostCustomer,bungiiCostCustomer+" should be displayed",tripPayment +" is displayed",tripPayment +" is displayed instead of "+bungiiCostCustomer);
                    break;
                case "fbshare":
                    bungiiCostCustomer = bungiiCostCustomer.replace(".00", "");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_Code(), Promo ,Promo+" should be displayed",code +" is displayed",code +" is displayed instead of "+Promo);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_CodeType(), "OneOffFBShare","OneOffFBShare should be displayed",codeType +" is displayed",codeType +" is displayed instead of OneOffFBShare");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue, PromoValue+" should be displayed",codeValue +" is displayed",codeValue +" is displayed instead of "+PromoValue);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + truncValue + " (" + Promo + " - " + PromoValue + ")", "$" + truncValue + " (" + Promo + " - " + PromoValue + ")"+" should be displayed",promoCode +" is displayed",promoCode +" is displayed instead of "+"$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_TripPayment(), bungiiCostCustomer,bungiiCostCustomer+" should be displayed",tripPayment +" is displayed",tripPayment +" is displayed instead of "+bungiiCostCustomer);
                    break;
                case "oneoff":
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_Code(), Promo ,Promo+" should be displayed",code +" is displayed",code +" is displayed instead of "+Promo);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_CodeType(), "OneOffByAdmin","OneOffByAdmin should be displayed",codeType +" is displayed",codeType +" is displayed instead of OneOffByAdmin");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue, PromoValue+" should be displayed",codeValue +" is displayed",codeValue +" is displayed instead of "+PromoValue);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + truncValue + " (" + Promo + " - " + PromoValue + ")", "$" + truncValue + " (" + Promo + " - " + PromoValue + ")"+" should be displayed",promoCode +" is displayed",promoCode +" is displayed instead of "+"$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_TripPayment(), bungiiCostCustomer,bungiiCostCustomer+" should be displayed",tripPayment +" is displayed",tripPayment +" is displayed instead of "+bungiiCostCustomer);
                    break;
                case "referral":
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_Code(), Promo ,Promo+" should be displayed",code +" is displayed",code +" is displayed instead of "+Promo);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_CodeType(), "Referral","Referral should be displayed",codeType +" is displayed",codeType +" is displayed instead of Referral");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue, PromoValue+" should be displayed",codeValue +" is displayed",codeValue +" is displayed instead of "+PromoValue);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + truncValue + " (" + Promo + " - " + PromoValue + ")", "$" + truncValue + " (" + Promo + " - " + PromoValue + ")"+" should be displayed",promoCode +" is displayed",promoCode +" is displayed instead of "+"$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_TripPayment(), bungiiCostCustomer,bungiiCostCustomer+" should be displayed",tripPayment +" is displayed",tripPayment +" is displayed instead of "+bungiiCostCustomer);
                    break;
                case "promoter":
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_Code(), Promo ,Promo+" should be displayed",code +" is displayed",code +" is displayed instead of "+Promo);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_CodeType(), "DeliveryChargesByPromoter","DeliveryChargesByPromoter should be displayed",codeType +" is displayed",codeType +" is displayed instead of DeliveryChargesByPromoter");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_CodeValue(), PromoValue, PromoValue+" should be displayed",codeValue +" is displayed",codeValue +" is displayed instead of "+PromoValue);
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_PromoCode(), "$" + truncValue + " (" + Promo + " - " + PromoValue + ")", "$" + truncValue + " (" + Promo + " - " + PromoValue + ")"+" should be displayed",promoCode +" is displayed",promoCode +" is displayed instead of "+"$" + truncValue + " (" + Promo + " - " + PromoValue + ")");
                    testStepAssert.isElementTextEquals(liveTripsPage.Text_TripPayment(), "$0", "$0 should be displayed",tripPayment +" is displayed",tripPayment +" is displayed instead of $0");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I wait for trip status to be \"([^\"]*)\"$")
    public void i_wait_for_trip_status_to_be_something(String strArg1) throws Throwable {
        try {
            String status = action.getText(liveTripsPage.Text_TripStatus());
            for (int i = 0; i < 5 && !status.equalsIgnoreCase(strArg1); i++) {
                Thread.sleep(30000);
                SetupManager.getDriver().navigate().refresh();
                status = action.getText(liveTripsPage.Text_TripStatus());
            }

            testStepVerify.isElementTextEquals(liveTripsPage.Text_TripStatus(), strArg1);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I click on \"([^\"]*)\" link$")
    public void i_click_on_something_link(String link) throws Throwable {
        try{
            switch(link) {
                case "Manually End Bungii":
                    action.click(liveTripsPage.Link_ManuallyEndBungii());
                    break;


            }
            log("I click on "+link+" link", "I clicked on "+link+" link", false);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in clicking "+ link,
                    true);
        }
    }

    @And("^Enter the End Date and Time$")
    public void enter_the_end_date_time() throws Throwable {
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY");
            String RequestTime = SetupManager.getDriver().findElement(By.xpath("//td[contains(text(),'Initial Request')]/following-sibling::td/strong")).getText();
            String[] splitedDate = RequestTime.split(" ");
            LocalDateTime now = LocalDateTime.now();
            String[] splitedTime = splitedDate[2].split(":");
            DecimalFormat formatter = new DecimalFormat("00");
            int minutes = Integer.parseInt(splitedTime[1])+20;
            int hours = Integer.parseInt(splitedTime[0]);
            if (minutes > 60) {
                hours = hours + 1;
                minutes = minutes -20;
            }
            ZoneId fromTimeZone = ZoneId.of("Asia/Kolkata");    //Source timezone
            ZoneId toTimeZone = ZoneId.of("America/New_York");  //Target timezone

            LocalDateTime today = LocalDateTime.now();          //Current time

            //Zoned date time at source timezone
            ZonedDateTime currentISTime = today.atZone(fromTimeZone);

            //Zoned date time at target timezone
            ZonedDateTime currentETime = currentISTime.withZoneSameInstant(toTimeZone);

            TimeZone.setDefault(TimeZone.getTimeZone("EST"));
            String endDate = dtf.format(currentETime);
            String endTime = formatter.format(hours)+":"+formatter.format(minutes);
            // ZonedDateTime zonedNZ = ZonedDateTime.of(now,ZoneId.of("5:00"));
            action.clearSendKeys(liveTripsPage.Textbox_PickupEndDate(),endDate);
            action.clearSendKeys(liveTripsPage.Textbox_PickupEndTime(),endTime);
            action.selectElementByText(liveTripsPage.Dropdown_ddlpickupEndTime(),splitedDate[3]);
            logger.detail("Selected End Date is : "+ endDate);
            logger.detail("Selected End Time is : "+ endTime);
            logger.detail("Selected End Time Dropdown is : "+ splitedDate[3]);

            log("I Enter the End Date and Time", "I entered the End Date and Time : "+endDate +" "+ endTime+" "+splitedDate[3] , false);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^Click on \"([^\"]*)\" button$")
    public void click_on_something_button(String button) throws Throwable {
        try{
            switch (button)
            {
                case "Calculate Cost":
                    action.click(liveTripsPage.Button_CalculateCost());
                    break;
                case "Confirm":
                    action.click(liveTripsPage.Button_Confirm());Thread.sleep(120000);
                    break;
                case "Cancel":
                    action.click(liveTripsPage.Button_Cancel());
                    break;
            }
            log("I click on "+button+" button", "I clicked on "+button+" button", true);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I view the Trips list on the admin portal$")
    public void i_view_the_trips_list_on_the_admin_portal() throws Throwable {
        try{
            Thread.sleep(120000);
            action.click(liveTripsPage.Menu_Trips());
            SetupManager.getDriver().navigate().refresh();
            action.selectElementByText(liveTripsPage.Dropdown_SearchForPeriod(),"The Beginning of Time");
            log("I view the Trips list on the admin portal",
                    "I viewed the Trips list on the admin portal", true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should be able to see the respective bungii with the below status$")
    public void i_should_be_able_to_see_the_respective_bungii_with_the_below_status(DataTable data) throws Throwable {
        try{
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String status = dataMap.get("Status").trim();
            String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String tripType[] = tripTypeAndCategory.split(" ");
            String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
            String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

            String geofenceName = getGeofence(geofence);
            //action.selectElementByText(liveTripsPage.Dropdown_Geofence(),geofenceName);
           // action.click(liveTripsPage.Button_ApplyGeofenceFilter());
            utility.selectGeofenceDropdown(geofenceName);

            cucumberContextManager.setScenarioContext("STATUS",status);
            String driver = driver1;
            if (tripType[0].equalsIgnoreCase("duo"))
                driver = driver1 + "," + driver2;
            if (status.equalsIgnoreCase("Scheduled") ||status.equalsIgnoreCase("Searching Drivers") || status.equalsIgnoreCase("Driver Removed") || (status.equalsIgnoreCase("Admin Canceled"))) {
                String xpath= String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[4]", tripType[0].toUpperCase(), customer);

                int retrycount =10;
                boolean retry = true;

                while (retry == true && retrycount >0) {
                    try {
                        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                        retry = false;
                    } catch (Exception ex) {
                        SetupManager.getDriver().navigate().refresh();
                       // action.selectElementByText(liveTripsPage.Dropdown_Geofence(),geofenceName);
                        //action.click(liveTripsPage.Button_ApplyGeofenceFilter());
                        utility.reApplyGeofenceDropdown();

                        retrycount--;
                        retry = true;
                    }

                }
                int retryCount = 1;
                while (!SetupManager.getDriver().findElement(By.xpath(xpath)).getText().equalsIgnoreCase(status)) {
                    if (retryCount >= 20) break;
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                    SetupManager.getDriver().navigate().refresh();
                }
                cucumberContextManager.setScenarioContext("XPATH",xpath);
                testStepAssert.isElementTextEquals(SetupManager.getDriver().findElement(By.xpath(xpath)), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

            } else {
                String XPath= String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[3]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND")?"Solo":StringUtils.capitalize(tripType[0]), driver, customer);
                int retrycount =10;
                boolean retry = true;
                while (retry == true && retrycount >0) {
                    try {
                        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                        retry = false;
                    } catch (Exception ex) {
                        SetupManager.getDriver().navigate().refresh();
                        //action.selectElementByText(liveTripsPage.Dropdown_Geofence(),geofenceName);
                        //action.click(liveTripsPage.Button_ApplyGeofenceFilter());
                        utility.reApplyGeofenceDropdown();
                        retrycount--;
                        retry = true;
                    }

                }
                int retryCount = 1;
                while (!SetupManager.getDriver().findElement(By.xpath(XPath)).getText().equalsIgnoreCase(status)) {
                    if (retryCount >= 20) break;
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                    SetupManager.getDriver().navigate().refresh();
                }
                //cucumberContextManager.setScenarioContext("XPATH",XPath);
                //logger.detail("XPATH is"+XPath);
                String XPath2= String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[2]/parent::tr/td[3]/a", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND")?"Solo":StringUtils.capitalize(tripType[0]), driver, customer);
                cucumberContextManager.setScenarioContext("XPATH",XPath2);
                logger.detail("XPATH is"+XPath2);

                testStepAssert.isElementTextEquals(SetupManager.getDriver().findElement(By.xpath(XPath)), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in vieweing status of the delivery in admin portal",
                    true);
        }

    }
    @When("^I view the trip details$")
    public void i_view_the_trip_details() throws Throwable {
        try{
            SetupManager.getDriver().navigate().refresh();
            String xpath=  (String)cucumberContextManager.getScenarioContext("XPATH");
            action.click(SetupManager.getDriver().findElement(By.xpath(xpath)));
            log("I view delivery details", "I viewed delivery details", false);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    public String getGeofence(String geofence)
    {
        String geofenceName = "";
        try{
            switch(geofence) {
                case "washingtondc":
                    geofenceName = "Washington DC";
                    break;

                case "miami":
                    geofenceName = "Miami";
                    break;

            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        return geofenceName;
    }
}