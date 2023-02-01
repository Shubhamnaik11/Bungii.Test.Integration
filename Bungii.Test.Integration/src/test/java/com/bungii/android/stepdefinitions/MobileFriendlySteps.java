package com.bungii.android.stepdefinitions;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.otherApps.ChromePage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;
import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class MobileFriendlySteps extends DriverBase {
    private static LogUtility logger = new LogUtility(NotificationSteps.class);
    ActionManager action = new ActionManager();
    ChromePage chromePage=new ChromePage();

    @And("I open {string} partner portal")
    public void iOpenPartnerPortal(String portal) {
        try{

            switch (portal){
                case "weight based":
                    action.clearSendKeys(chromePage.Textbox_GoogleSearchBar(),PropertyUtility.getDataProperties("qa.fnd_service_level_partner.url"));
                    action.click(chromePage.DropDown_FirstValue());
                    break;
                case "geofence based":
                    action.clearSendKeys(chromePage.Textbox_GoogleSearchBar(),PropertyUtility.getDataProperties("qa.equip-bid.url"));
                    action.click(chromePage.DropDown_FirstValue());
                    break;
                case "fixed pricing":
                    action.clearSendKeys(chromePage.Textbox_GoogleSearchBar(),PropertyUtility.getDataProperties("qa.service_level_partner.url"));
                    action.click(chromePage.DropDown_FirstValue());
                    break;
            }
            action.waitUntilIsElementExistsAndDisplayed(chromePage.Textbox_EnterPassword());
            action.clearSendKeys(chromePage.Textbox_EnterPassword(),PropertyUtility.getDataProperties("PartnerPassword"));
            action.click(chromePage.Button_SignIn());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

}
