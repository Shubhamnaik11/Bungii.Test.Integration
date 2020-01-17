package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.BungiiAcceptedPage;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;

import java.math.BigDecimal;

import static com.bungii.common.manager.ResultManager.error;

public class BungiiAcceptedSteps extends DriverBase {
    BungiiAcceptedPage bungiiAcceptedPage;
    ActionManager action = new ActionManager();
    GeneralUtility utility= new GeneralUtility();
    private static LogUtility logger = new LogUtility(BungiiAcceptedSteps.class);

    public  BungiiAcceptedSteps(BungiiAcceptedPage bungiiAcceptedPage){
        this.bungiiAcceptedPage=bungiiAcceptedPage;
    }


    @Then("^correct details should do be displayed on (.+) screen for Stack screen$")
    public void correct_details_should_do_be_displayed_on_bungii_accepted_screen_for_stack_screen(String key)  {
        switch (key.trim()){
            case"BUNGII ACCEPTED":
                String driverName=(String)cucumberContextManager.getScenarioContext("DRIVER_1");
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Label_DriverName(),driverName.substring(0, driverName.indexOf(" ") + 2));
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Text_StackInfo(), PropertyUtility.getMessage("customer.stack.accepted.info"));
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Text_BungiiAccepted(), PropertyUtility.getMessage("customer.bungii.accepted"));
                testStepVerify.isElementDisplayed(bungiiAcceptedPage.Image_RattingBar(),"Ratting bar should be displayed","Ratting bar is displayed","Ratting bar is not displayed");
                break;
            case"BUNGII ACCEPTED with arrival time":
                String driver1Name=(String)cucumberContextManager.getScenarioContext("DRIVER_1");
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Textlabel_DriverNearby(),PropertyUtility.getMessage("customer.stack.driver.neighborhood").replace("<DRIVER_NAME>",driver1Name.substring(0, driver1Name.indexOf(" ") + 2)));
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Textlabel_StackSubtitle(),PropertyUtility.getMessage("customer.stack.driver.subtitle.ios"));
                testStepVerify.isElementEnabled(bungiiAcceptedPage.Textlabel_ProjectedTime(),"Projected driver arrival time label should be displayed");

                String label="";
                String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

                if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
                    label =  PropertyUtility.getDataProperties("time.label");
                else
                    label =  utility.getTimeZoneBasedOnGeofence();

                testStepVerify.isElementEnabled(bungiiAcceptedPage.Button_CancelBungii(),"Cancel Bungii should be displayed");
                String expectedArrivalValue=(String)cucumberContextManager.getScenarioContext("DRIVER_MIN_ARRIVAL")+" - "+(String)cucumberContextManager.getScenarioContext("DRIVER_MAX_ARRIVAL")+" "+label;
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Textlabel_ProjectedTimeValue(),expectedArrivalValue);
                break;
        }


    }


    @Then("^ratting should be correctly displayed on Bungii accepted page$")
    public void ratting_should_be_correctly_displayed_on_bungii_accepteed_page() throws Throwable {
        try {
            String    driverPhoneNumber=(String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");

            String ratingString = DbUtility.getDriverRating(driverPhoneNumber);
            cucumberContextManager.setScenarioContext("DRIVER_CURRENT_RATTING",ratingString);
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(ratingString));
            int ratingInt = bigDecimal.intValue();
            BigDecimal ratingDecimal = bigDecimal.subtract(new BigDecimal(ratingInt));

            System.out.println("ratingString: " + ratingString);
            System.out.println("Integer Part: " + ratingInt);
            System.out.println("Decimal Part: " + ratingDecimal);

            bungiiAcceptedPage.WaitUntilElementIsDisplayed(By.xpath("//XCUIElementTypeButton[@name=\"rating filled star icon\"])"));

            int filledStarCount = bungiiAcceptedPage.FilledStars().size();
            int HalfFilledStarCount = bungiiAcceptedPage.HalfFilledStar().size();

            testStepVerify.isEquals(filledStarCount, ratingInt);

            if (ratingDecimal.doubleValue() >= 0.5) {
                testStepVerify.isEquals(HalfFilledStarCount, 1);
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}


