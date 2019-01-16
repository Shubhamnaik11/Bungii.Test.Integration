package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.EstimatePage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.*;


public class EstimateSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(EstimateSteps.class);
    ActionManager action = new ActionManager();
    private EstimatePage estimatePage;
    private String[] loadTimeValue = {"15", "30", "45", "60", "75", "90+"};

    public EstimateSteps(EstimatePage estimatePage) {
        this.estimatePage = estimatePage;
    }

    @When("^I confirm trip with following details$")
    public void iEnterTripInformation(DataTable tripInformation) {

        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String loadTime = data.get("LoadTime"), promoCode = data.get("PromoCode"), time = data.get("Time"),
                    pickUpImage = data.get("PickUpImage");
            //Vishal[21/12]: added this to save time , It takes time to read trip value from estimate page
            boolean saveDetails=true;
            try{String save_trip_info = data.get("Save Trip Info"); saveDetails=save_trip_info.equalsIgnoreCase("No")?false:true;}
            catch (Exception e){}
            boolean isCorrectTime = false, isAlertCorrect;
            String strTime = "";

            enterLoadingTime(loadTime);
          //  addPromoCode(promoCode);
            addBungiiPickUpImage(pickUpImage);
            clickAcceptTerms();
            strTime = enterTime(time);

            String[] details = new String[4];
            if(saveDetails){
                details = getEstimateDetails();
                isCorrectTime =  details[1].equals(strTime);
            }
            else
                isCorrectTime =  action.getValueAttribute(estimatePage.Text_TimeValue()).equals(strTime);

            clickRequestBungii();
            // verification
            isAlertCorrect = verifyAndAcceptAlert(loadTime);

            // SAVE required values in scenario context
            cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);
            cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", details[0]);
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", details[2]);
            cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", details[3]);

            if(action.isAlertPresent()){
                if(action.getAlertMessage().equalsIgnoreCase(PropertyUtility.getMessage(PropertyUtility.getMessage("customer.alert.delay.scheduled")))) {
                    warning("I should able to select bungii time", "I am changing bungii time due to delay in bungii request",true);
                    SetupManager.getDriver().switchTo().alert().accept();
                    strTime = enterTime(time);
                    isCorrectTime =  action.getValueAttribute(estimatePage.Text_TimeValue()).equals(strTime);
                    cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);
                }
            }

            testStepVerify.isTrue(isAlertCorrect, "Heads up alert message should be correctly displayed",
                    "Heads up alert message is correctly displayed", "Heads up alert message is not correctly displayed");

            testStepVerify.isTrue(isCorrectTime , "I confirm trip with following details",
                    "I created new  trip for "+strTime, "Trip was not successfully confirmed ,Bungii request time"
                            + strTime + " not matching with entered time ");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    private void addPromoCode(String code){
        action.click(estimatePage.Button_AddPromoCode());

    }
    @Then("^Estimate value for trip should be properly displayed$")
    public void estimate_value_for_trip_should_be_properly_displayed() {
        try {
            String distance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
            String estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
            estimate = estimate.replace("$", "");
            String loadTime = (String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME");
            GeneralUtility utility = new GeneralUtility();
            double expectedValue = utility.bungiiEstimate(distance, loadTime, getEstimateTime(), "");

            String actualValue = estimate.substring(0, estimate.length() - 1);
            String truncValue = new DecimalFormat("#.#").format(expectedValue);
            testStepVerify.isEquals(actualValue, truncValue, "Estimate value for trip should be properly displayed", "Expected Estimate value for bungii is" + truncValue + " and Actual value is" + estimate + ",(Truncate to single float point)", "Expected Estimate value for bungii is" + truncValue + " and Actual value is" + estimate);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    private String getEstimateTime() {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        String custRef = DbUtility.getCustomerRefference(phoneNumber);
        return DbUtility.getEstimateTime(custRef);
    }

    public String enterTime(String time) {
        String strTime = "";
        if (time.equalsIgnoreCase("NOW")) {
            selectBungiiTimeNow();
            strTime = "Now";
        } else if (time.equalsIgnoreCase("NEXT_POSSIBLE")) {
            Date date = getNextScheduledBungiiTime();
            String[] dateScroll = bungiiTimeForScroll(date);
            strTime = bungiiTimeDisplayInTextArea(date);

            selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);

        } else if (time.equals("<OLD BUNGII TIME>")) {
            String expectedTripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            String tripDay = expectedTripTime.split(",")[0];
            String tripTime = expectedTripTime.split(",")[1];
            String[] timeSplit = tripTime.trim().split("\\W");
            selectBungiiTime(0, timeSplit[0], timeSplit[1], timeSplit[2]);
            strTime = expectedTripTime;
        } else {
            selectBungiiTime(0, "", "", "");
            strTime = "Now";
        }

        return strTime;
    }

    @When("^I select Bungii time as per table$")
    public void i_select_bungii_time_as_per_table(DataTable tripTimeDetails) {
        try {
            Map<String, String> data = tripTimeDetails.transpose().asMap(String.class, String.class);
            String bungiiTime = data.get("Time"), bungiiDate = data.get("Date");
            String[] dateScroll = getDateForScroll(bungiiTime);
            selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    /**
     * @param bungiiTime Bungii time took from user
     * @return
     */
    public String[] getDateForScroll(String bungiiTime) {
        int differance = 0;
        String[] dateScroll = new String[3];
        if (bungiiTime.equalsIgnoreCase("2 hour before"))
            differance = -120;
        else if (bungiiTime.equalsIgnoreCase("2 hour after"))
            differance = 120;

        Date date = getScheduledBungiiTime(differance);
        dateScroll = bungiiTimeForScroll(date);

        if (differance == 0)
            if (bungiiTime.equalsIgnoreCase("before 7 AM")) {
                dateScroll[0] = "6";
                dateScroll[1] = "45";
                dateScroll[2] = "AM";
            } else if (bungiiTime.equalsIgnoreCase("after 9 PM")) {
                dateScroll[0] = "9 ";
                dateScroll[1] = "45";
                dateScroll[2] = "PM";
            }

        return dateScroll;
    }

    /**
     * Format input date and return in required format
     *
     * @param date input date
     * @return formated date
     */
    public String bungiiTimeDisplayInTextArea(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm a");
        String formattedDate = sdf.format(date);
        //After sprint 27 /26 IST is being added in scheduled page
        formattedDate = formattedDate + " " + PropertyUtility.getDataProperties("time.label");
        return formattedDate;
    }

    /**
     * Format input date and return in required format
     *
     * @param date input date
     * @return formated date
     */
    public String[] bungiiTimeForScroll(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM|h|mm|a");
        String formattedDate = sdf.format(date);
        String[] SplitDate = formattedDate.split("\\|");
        if (DateUtils.isSameDay(date, new Date())) {
            SplitDate[0] = "Today";
        }
        return SplitDate;
    }

    /**
     * Read property file for minimum difference for next bunii time
     *
     * @return next possible valid bungii time
     */
    public Date getNextScheduledBungiiTime() {
        int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time"));
        Calendar calendar = Calendar.getInstance();
        // int mnts = calendar.get(Calendar.MINUTE);

        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + nextTripTime);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.MINUTE, (15 - unroundedMinutes % 15));

        Date nextQuatter = calendar.getTime();
        return nextQuatter;
    }

    public Date getScheduledBungiiTime(int minuteDifferance) {
        int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time"));
        Calendar calendar = Calendar.getInstance();
        // int mnts = calendar.get(Calendar.MINUTE);

        calendar.set(Calendar.MINUTE, (calendar.get(Calendar.MINUTE) + nextTripTime + minuteDifferance));
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.MINUTE, (15 - unroundedMinutes % 15));

        Date nextQuatter = calendar.getTime();
        return nextQuatter;
    }

    @Then("^\"([^\"]*)\" information icon should display correct information$")
    public void something_should_display_correct_information(String iconName) {
        try {

            String loadTime = (String) cucumberContextManager.getScenarioContext("BUNGII_LOAD_TIME");
            String expectedMessage = "", actualMessage = "";
            switch (iconName.toUpperCase()) {
                case "LOAD/UPLOAD TIME":
                    expectedMessage = PropertyUtility.getMessage("customer.info.loadtime");
                    actualMessage = getInfoMessage("LOAD/UPLOAD TIME");
                    break;
                case "TIME":
                    expectedMessage = PropertyUtility.getMessage("customer.info.time");
                    actualMessage = getInfoMessage("TIME");
                    break;
                case "TOTAL ESTIMATE":
                    expectedMessage = PropertyUtility.getMessage("customer.info.totalestimate").replaceAll("<TIME>", loadTime.trim());
                    actualMessage = getInfoMessage("TOTAL ESTIMATE");
                    break;
                default:
                    fail("Step  Should be successful",
                            "UnImplemented STEP , please verify test step", true);
                    break;
            }

            testStepVerify.isEquals(actualMessage, expectedMessage);
            getDriver().switchTo().alert().accept();

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^check if I have ability to select different load time and Estimate cost is re calculated$")
    public void check_if_i_have_ability_to_select_different_load_time_and_estimate_cost_is_re_calculated() {
        try {
            String oldEstimateValue = getElementValue("Total Estimate");
            for (int i = 0; i < loadTimeValue.length; i++) {

                boolean flag = checkLoadingTime(loadTimeValue[i]);
                testStepVerify.isTrue(flag,
                        "I should able to to select " + loadTimeValue[i] + "mins as load time",
                        "I was able to to select " + loadTimeValue[i] + "mins as load time",
                        "I was not able to to select " + loadTimeValue[i] + "mins as load time");
                String newEstimateValue = getElementValue("Total Estimate");

                if (i == 0)
                    testStepVerify.isTrue(newEstimateValue.equals(oldEstimateValue),
                            "total Estimated cost is calculated considering  Loading/unloading time",
                            "Total Estimate cost for first scroll value should be same as default one, Previous cost is " + oldEstimateValue + " , new cost is " + newEstimateValue,
                            "Total Estimate cost was recalculated");
                else
                    testStepVerify
                            .isFalse(newEstimateValue.equals(oldEstimateValue),
                                    "total Estimated cost is calculated considering  Loading/unloading time",
                                    "Total Estimate cost is recalculated , previous cost is" + oldEstimateValue + " , new cost is" + newEstimateValue,
                                    "Total Estimate cost was not recalculated");
                oldEstimateValue = newEstimateValue;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I enter following details on \"([^\"]*)\" screen$")
    public void i_enter_following_details_on_something_screen(String strArg1, DataTable tripInformation) {

        try {
            Map<String, String> data = tripInformation.transpose().asMap(String.class, String.class);
            String loadTime = data.get("LoadTime"), promoCode = data.get("PromoCode"), time = data.get("Time"),
                    pickUpImage = data.get("PickUpImage");
            if (!loadTime.equals("")) {
                enterLoadingTime(loadTime);
                cucumberContextManager.setScenarioContext("BUNGII_LOAD_TIME", loadTime);

            }
            if (!time.equals("")) {
                String strTime = enterTime(time);
                cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);
            }
            addBungiiPickUpImage(pickUpImage);
            clickAcceptTerms();
            String[] details = getEstimateDetails();
            // SAVE required values in scenario context
            cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", details[0]);
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", details[2]);
            cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", details[3]);
            cucumberContextManager.setScenarioContext("", "");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^Estimate Screen should have element as per below table$")
    public void estimate_screen_should_have_element_as_per_below_table(DataTable estimateInformation) {
        try {
            Map<String, String> data = estimateInformation.transpose().asMap(String.class, String.class);
            // Store expected data in variable
            String tripDistance = data.get("Trip Distance"), loadTime = data.get("Load/unload time"),
                    promoCode = data.get("Promo Code"), tripEstimate = data.get("Total Estimate"),
                    paymentMethod = data.get("Payment Method"), tripTime = data.get("Time"),
                    termsAndCondition = data.get("Terms And Condition"), requestBungii = data.get("REQUEST BUNGII");
            // check each field for expected value
            checkTripDistance(tripDistance);
            checkLoadTime(loadTime);
            checkPromoCode(promoCode);
            checkEstimate(tripEstimate);
            checkPayment(paymentMethod);
            checkTime(tripTime);
            checkTermsAndConditon(termsAndCondition);
            checkRequestBungii(requestBungii);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("^Trip Information should be correctly displayed on Estimate screen$")
    public void trip_information_should_be_correctly_displayed_on_something_screen() {
        try {
            String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            String pickUpLocation = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
            String dropOffLocation = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"));

            String[] tripLocation = new String[2];
            tripLocation[0] = action.getValueAttribute(estimatePage.Text_PickUpLocation());
            tripLocation[1] = action.getValueAttribute(estimatePage.Text_DropOffLocation());

            if (tripLocation[0].equals(pickUpLocation) && tripLocation[1].equals(dropOffLocation)) {
                pass("Trip Information should be correctly displayed on Estimate screen",
                        "Pick up location :" + pickUpLocation + " , Drop location: " + dropOffLocation
                                + "is correctly displayed on estimate screen ", true);

            } else {
                fail("Trip Information should be correctly displayed on Estimate screen",
                        "Pick up location on request screen is:" + pickUpLocation + " and on Estimate screen is"
                                + tripLocation[0] + " .Drop off location on request screen is:" + dropOffLocation + " and on Estimate screen is" + tripLocation[1],
                        true);
            }

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    /**
     * Verify if request bungii button is enabled or disabled
     *
     * @param expectedValue expected value for button
     */
    public void checkRequestBungii(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("REQUEST BUNGII");

        if (expectedValue.equalsIgnoreCase("DISABLED"))
            testStepVerify.isFalse(isElementPresent,
                    "REQUEST BUNGII' button should be disabled", "REQUEST BUNGII' button is disabled",
                    "REQUEST BUNGII' button is enabled");
        else
            testStepVerify.isTrue(isElementPresent,
                    "REQUEST BUNGII' button should be enabled", "REQUEST BUNGII' button is enabled",
                    "REQUEST BUNGII' button is disabled");

    }

    /**
     * Verify terms checkbox in estimate page
     *
     * @param expectedValue expected value for terms and condition check box
     */
    public void checkTermsAndConditon(String expectedValue) {
        boolean isElementPresent = false;
        if (expectedValue.equalsIgnoreCase("UNCHECK")) {
            isElementPresent = checkIfElementIsPresent("TERMS OFF");
        } else {
            isElementPresent = checkIfElementIsPresent("TERMS ON");
        }
        String value = getElementValue("TERMS AND CONDITION");
        String expectedMsg = PropertyUtility.getMessage("customer.text.terms");
        boolean isValueCorrect = value.equals(expectedMsg);

        testStepVerify.isTrue(isElementPresent,
                "Verify Terms & Condition checkBox should be " + expectedValue,
                "Verify Terms & Condition checkBox is " + expectedValue,
                "Verify Terms & Condition checkBox is not " + expectedValue);
        testStepVerify.isTrue(isValueCorrect,
                " Terms & Condition value Should be " + PropertyUtility.getMessage("customer.text.terms"),
                " Terms & Condition value is " + PropertyUtility.getMessage("customer.text.terms") + "as expected",
                "'Terms & Condition' value is not matching ,expected is" + PropertyUtility.getMessage("customer.text.terms")
                        + "but actual is" + value);

    }

    /**
     * Verify trip Time Estimate row and value in estimate page
     *
     * @param expectedValue expected value for Time
     */
    public void checkTime(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("Time");
        String value = getElementValue("Time");
        System.err.println("Value is " + value);
        boolean isValueCorrect = value.equals(expectedValue);
        testStepVerify.isTrue(isElementPresent, "'Time' row should be present",
                "'Time' row  is present'", "'Time' row  not present'");
        if (!expectedValue.equals(""))
            testStepVerify.isTrue(isValueCorrect,
                    " Time value Should be " + expectedValue, " Time value is " + expectedValue + "as expected",
                    "'Time' value is not matching ,expected is" + expectedValue + "but actual is" + value);

    }

    /**
     * Verify trip Payment Method row and value in estimate page
     *
     * @param expectedValue expected value for Payment Method
     */
    public void checkPayment(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("Payment Method");
        String value = getElementValue("Payment Method");
        System.err.println("Value is " + value);

        boolean isValueCorrect = value.equals(expectedValue);

        testStepVerify.isTrue(isElementPresent,
                "'Payment Method' row should be present", "'Payment Method' row  is present'",
                "'Payment Method' row  not present'");
        testStepVerify.isTrue(isValueCorrect,
                " Payment Method value Should be " + expectedValue,
                " Payment Method value is " + expectedValue + "as expected",
                "'Payment Method' value is not matching ,expected is" + expectedValue + "but actual is" + value);
    }

    /**
     * Verify trip Total Estimate row and value in estimate page
     *
     * @param expectedValue expected value for Total Estimate
     */
    public void checkEstimate(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("Total Estimate");
        String value = getElementValue("Total Estimate");
        System.err.println("Value is " + value);
        boolean isValueCorrect = false;

        if (expectedValue.equals("<IN DOLLAR>")) {
            String v = value.substring(1);
            boolean correct = v.matches("[0-9]*\\.?[0-9]+");

            isValueCorrect = value.startsWith("$") && correct;
        } else if (expectedValue.equals("{PREVIOUS VALUE}")) {

            isValueCorrect = value.equals((String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));

        } else {
            isValueCorrect = value.equals(expectedValue);
        }

        testStepVerify.isTrue(isElementPresent,
                "'Total Estimate' row should be present", "'Total Estimate' row  is present'",
                "'Total Estimate' row  not present'");
        testStepVerify.isTrue(isValueCorrect,
                " Total Estimate value Should be " + expectedValue, " Time value is " + value + " as expected",
                "' Total Estimate' value is not matching ,expected is" + expectedValue + "but actual is" + value);

    }

    /**
     * Verify trip Promo row and value in estimate page
     *
     * @param expectedValue expected value for Promo
     */
    public void checkPromoCode(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("Promo Code");
        String value = getElementValue("Promo Code");
        System.err.println("Value is " + value);

        boolean isValueCorrect = value.equals(expectedValue);

        testStepVerify.isTrue(isElementPresent,
                "'Promo Code' row should be present", "'Promo Code' row  is present'",
                "'Promo Code' row  not present'");
        testStepVerify.isTrue(isValueCorrect,
                " Promo Code value Should be " + expectedValue, " Promo Code value is " + expectedValue + "as expected",
                "'Promo Code' value is not matching ,expected is" + expectedValue + "but actual is" + value);

    }

    /**
     * Verify trip load time row and value in estimate page
     *
     * @param expectedValue expected value for load time
     */
    public void checkLoadTime(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("Load/unload time");
        String value = getElementValue("Load/unload time");
        System.err.println("Value is " + value);
        boolean isValueCorrect = false;
        if (expectedValue.equals("{PREVIOUS VALUE}")) {
            isValueCorrect = value.equals((String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));

        }
        isValueCorrect = value.equals(expectedValue);
        testStepVerify.isTrue(isElementPresent,
                "'Load/unload time' row should be present", "'Load/unload time' row  is present'",
                "'Load/unload time' row  not present'");
        testStepVerify.isTrue(isValueCorrect,
                " Load/unload time value Should be " + expectedValue,
                "Load/unload time value is " + expectedValue + "as expected",
                "'Load/unload time' value is not matching ,expected is" + expectedValue + "but actual is" + value);

    }

    /**
     * Verify trip distance row and value in estimate page
     *
     * @param expectedValue expected value for trip distance
     */
    public void checkTripDistance(String expectedValue) {
        boolean isElementPresent = checkIfElementIsPresent("Trip Distance");
        String value = getElementValue("Trip Distance");
        System.err.println("Value is " + value);

        boolean isValueCorrect = false;
        if (expectedValue.equals("<IN MILES>")) {
            int val = value.indexOf(" ");
            String v = value.substring(0, val);

            boolean correct = v.matches("[0-9]*\\.?[0-9]+");

            isValueCorrect = value.contains("miles") && correct;
        } else if (expectedValue.equals("{PREVIOUS VALUE}")) {
            isValueCorrect = value.equals((String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));

        } else {
            isValueCorrect = value.equals(expectedValue);
        }

        testStepVerify.isTrue(isElementPresent,
                "'Trip Distance' row should be present", "'Trip Distance' row  is present'",
                "'Trip Distance' row  not present'");
        testStepVerify.isTrue(isValueCorrect,
                " Trip Distance value Should be " + expectedValue, " Trip Distance value is " + value + " as expected",
                "'Trip Distance' value is not matching ,expected is" + expectedValue + "but actual is" + value);

    }


    /**
     * Get Estimate details
     *
     * @return return value of different row from estimate screen
     */
    public String[] getEstimateDetails() {
        String[] details = new String[4];
        details[0] = action.getValueAttribute(estimatePage.Text_DistanceValue());
        details[1] = action.getValueAttribute(estimatePage.Text_TimeValue());
        details[2] = action.getValueAttribute(estimatePage.Text_EstimateValue());
        details[3] = action.getValueAttribute(estimatePage.Text_LoadUnLoadTimeValue());
        return details;
    }

    /**
     * Get trip information .(Pickup / Drop location)
     *
     * @return trip information
     */
    public String[] getTripLocationInformation() {
        String[] tripInformation = new String[2];
        tripInformation[0] = action.getValueAttribute(estimatePage.Text_PickUpLocation());
        tripInformation[1] = action.getValueAttribute(estimatePage.Text_DropOffLocation());
        return tripInformation;
    }

    /**
     * Enter loading/unloading time
     *
     * @param timeValue input that is to be entered
     */
    public void enterLoadingTime(String timeValue) {
        String inputValue = timeValue + " mins";
        action.click(estimatePage.Text_LoadTime());
        WebElement timeScroll = estimatePage.Wheel_LoadTime();
        //WebElement timeScroll = waitForExpectedElement(Wheel_LoadTime);

        if (!timeScroll.getAttribute("value").equals(inputValue))
            timeScroll.sendKeys(inputValue);
        //action.invisibilityOfElementLocated(estimatePage.Indicator_Loading());
        action.click(estimatePage.Button_Set());
    }

    public boolean checkLoadingTime(String timeValue) {
        String inputValue = timeValue + " mins";
        action.click(estimatePage.Text_LoadTime());
        //	WebElement timeScroll = waitForExpectedElement(estimatePage.Wheel_LoadTime());
        WebElement timeScroll = estimatePage.Wheel_LoadTime();

        timeScroll.sendKeys(inputValue);
        String actualValue = timeScroll.getAttribute("value");

        action.click(estimatePage.Text_LoadTime());
        return actualValue.equals(inputValue);

    }

    /**
     * Click info icon of element and return info text
     *
     * @param key Element identifier whose info icon is to check
     * @return info string of alert
     */
    public String getInfoMessage(String key) {
        switch (key.toUpperCase()) {
            case "TIME":
                action.click(estimatePage.Button_InfoTime());
                break;
            case "LOAD/UPLOAD TIME":
                action.click(estimatePage.Button_InfoLoadingTime());
                break;
            case "TOTAL ESTIMATE":
                action.click(estimatePage.Button_InfoTotalEstimate());
                break;
            default:
                break;
        }

        return getDriver().switchTo().alert().getText();
    }


    /**
     * Select Bungii time in scroll wheel
     *
     * @param forwordDate integer of how far you want to scroll
     * @param hour        Trip hour
     * @param minutes     trip minutes
     * @param meridiem    AM/PM
     */
    public void selectBungiiTime(int forwordDate, String hour, String minutes, String meridiem) {
        action.click(estimatePage.Row_TimeSelect());
        action.dateTimePicker(estimatePage.DatePicker_BungiiTime, estimatePage.DateWheel_BungiiTime, forwordDate, hour, minutes, meridiem);
      //  action.click(estimatePage.Row_TimeSelect());

        action.click(estimatePage.Button_Set());

    }

    /**
     * Select Bungii trip time to Now
     */
    public void selectBungiiTimeNow() {
        action.click(estimatePage.Row_TimeSelect());
        action.click(estimatePage.Button_Now());
        action.click(estimatePage.Button_Set());

    }

    /**
     * Add Last image from camera roll folder as bungii pickup item image
     */
    public void addBungiiPickUpImage(String option) {
        if (option.equalsIgnoreCase("4 images")) {
            addImage(4);
        }else if(option.equalsIgnoreCase("Default")){
            addImage(1);
        }else
            addImage(1);

    }

    private void addImage(int numberOfImage) {
        for (int i = 1; i <= numberOfImage; i++) {
            estimatePage.Button_AddPhoto().click();
            estimatePage.Button_Gallary().click();
            estimatePage.PhotosFolder().click();
            List<WebElement> folder = estimatePage.Cell_Photo();
            folder.get(folder.size() - 1).click();
        }

    }

    /**
     * Accept terms and condition checkbox
     */
    public void clickAcceptTerms() {
        action.swipeUP();
        estimatePage.CheckBoxOff_Terms().click();
    }

    /**
     * Click cancel button on Navigation bar
     */
    public void clickCancel() {
        estimatePage.Button_Cancel().click();
    }

    /**
     * Click request bungii
     */
    public void clickRequestBungii() {
        estimatePage.Button_RequestBungii().click();
    }

    /**
     * Verify and accept alert message displayed while requesting bungii
     *
     * @param loadTime Load/UnLoad time
     * @return boolean value comparing actual alert message with that of expected message from properties file
     */
    public boolean verifyAndAcceptAlert(String loadTime) {

        String actualText = getDriver().switchTo().alert().getText();

        //Replace '<TIME>' keyword with load/unload time for current trip
        String bungiiType= (String) cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
        String expectedText = PropertyUtility.getMessage("alert.Request.Bungii").replaceAll("<TIME>", loadTime.trim());
        //VISHAL[21/12]: added message for duo as there is different message for duo trip
        if(bungiiType.equalsIgnoreCase("DUO"))
             expectedText = PropertyUtility.getMessage("alert.request.duo.bungii").replaceAll("<TIME>", loadTime.trim());
        getDriver().switchTo().alert().accept();
        logger.detail("Popup text on head up alert message:"+actualText);
        return actualText.equals(expectedText);
    }

    /**
     * return boolean value according to present of element on screen
     *
     * @param key Element identifier
     * @return boolean value according to presence of element
     */
    public boolean checkIfElementIsPresent(String key) {

        try {
            boolean isPresent = false;
            switch (key.toUpperCase()) {
                case "TRIP DISTANCE":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_Distance());
                    break;
                case "LOAD/UNLOAD TIME":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_LoadUnLoadTime());
                    break;
                case "PROMO CODE":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_PromoCode());
                    break;
                case "TOTAL ESTIMATE":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_Estimate());
                    break;
                case "PAYMENT METHOD":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_PaymentMethod());
                    break;
                case "TIME":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Text_Time());
                    break;
                case "REQUEST BUNGII":
                    isPresent = estimatePage.isElementEnabled(estimatePage.Button_RequestBungii());
                    break;
                case "TERMS OFF":
                    isPresent = estimatePage.isElementEnabled(estimatePage.CheckBoxOff_Terms());
                    break;
                case "TERMS ON":
                    isPresent = estimatePage.isElementEnabled(estimatePage.CheckBoxOn_Terms());
                    break;
                default:
                    System.err.println("ELEMET not found in case" + key);
                    break;
            }
            return isPresent;
        } catch (Exception e) {
            return false;

        }
    }

    /**
     * Return value of element displayed on screen
     *
     * @param key Element identifier
     * @return
     */
    public String getElementValue(String key) {
        String value = "";
        switch (key.toUpperCase()) {
            case "TRIP DISTANCE":
                value = action.getValueAttribute(estimatePage.Text_DistanceValue());
                break;
            case "LOAD/UNLOAD TIME":
                value = action.getValueAttribute(estimatePage.Text_LoadUnLoadTimeValue());
                break;
            case "PROMO CODE":
                value = action.getNameAttribute(estimatePage.Text_PromoCodeValue());
                break;
            case "TOTAL ESTIMATE":
                value = action.getValueAttribute(estimatePage.Text_EstimateValue());
                break;
            case "PAYMENT METHOD":
                value = action.getNameAttribute(estimatePage.Text_PaymentMethodValue());
                break;
            case "TIME":
                value = action.getValueAttribute(estimatePage.Text_TimeValue());
                break;
            case "TERMS AND CONDITION":
                value = action.getValueAttribute(estimatePage.CheckBox_Value());
                break;
            default:
                System.err.println("ELEMENT not found in case" + key);
                break;
        }
        return value;
    }
}
