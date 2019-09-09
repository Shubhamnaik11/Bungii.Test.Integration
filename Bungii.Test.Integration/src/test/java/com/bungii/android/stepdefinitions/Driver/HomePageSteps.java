package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.pages.driver.BungiiRequest;
import com.bungii.android.pages.driver.HomePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.android.manager.ActionManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.error;

public class HomePageSteps  extends DriverBase {
    ActionManager action = new ActionManager();
    HomePage homePage = new HomePage();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility= new GeneralUtility();
    private static LogUtility logger = new LogUtility(HomePageSteps.class);

    @And("^I Select \"([^\"]*)\" from driver App menu$")
    public void i_select_something_from_driver_app_memu(String menuItem) {
        try {
/*
            if (action.isNotificationAlertDisplayed()) {
                action.click(Page_BungiiRequest.AlertButton_Cancel(true));
                Thread.sleep(1000);
            }
*/

            if (action.isNotificationAlertDisplayed()) {
                if (action.getText(Page_BungiiRequest.Alert_Msg()).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))) {
                    utility.acceptNotificationAlert();
                } else {
                    action.click(Page_BungiiRequest.AlertButton_Cancel());
                }

            }
            boolean isClicked =false;
            action.click(homePage.Button_NavigationBar());
            List<WebElement> elements = homePage.Button_NavigationBarText();
            for(WebElement element: elements){
                if(element.getText().equalsIgnoreCase(menuItem)){
                    action.click(element);
                    isClicked=true;
                    break;
                }
            }
            testStepAssert.isTrue(isClicked, "I should able to click " + menuItem, "Not able to select " + menuItem + " from App menu");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
}
