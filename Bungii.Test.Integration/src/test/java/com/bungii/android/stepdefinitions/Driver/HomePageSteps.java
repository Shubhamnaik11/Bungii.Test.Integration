package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.pages.driver.HomePage;
import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.error;

public class HomePageSteps  extends DriverBase {
    ActionManager action = new ActionManager();
    HomePage homePage = new HomePage();
    @And("^I Select \"([^\"]*)\" from driver App menu$")
    public void i_select_something_from_driver_app_memu(String menuItem) {
        try {
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
           // logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
}
