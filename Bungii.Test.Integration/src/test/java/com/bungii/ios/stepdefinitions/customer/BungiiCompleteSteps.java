package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.BungiiCompletePage;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;


public class BungiiCompleteSteps extends DriverBase {
	BungiiCompletePage bungiiCompletePage;
	private static LogUtility logger = new LogUtility(BungiiCompleteSteps.class);
	ActionManager action = new ActionManager();
	public BungiiCompleteSteps(BungiiCompletePage bungiiCompletePage) {
		this.bungiiCompletePage = bungiiCompletePage;

	}

	@When("^I rate Bungii Driver  with following details and Press \"([^\"]*)\" Button$")
	public void iRateBungiiDriverWithFollowingDetailsAndPressButton(String button, DataTable tipInformation) {
		try {
			
			//Input from user
			Map<String, String> data = tipInformation.transpose().asMap(String.class, String.class);
			String ratting = data.get("Ratting"), tip = data.get("Tip");
			//give tip and fetch actual tip
			giveTip(Integer.parseInt(tip));
			String actualTip = bungiiCompletePage.Text_TipValue().getAttribute("value");
			
			giveRatting(Integer.parseInt(ratting));

			switch (button.toUpperCase()) {
			case "OK":
				action.click(bungiiCompletePage.Button_Ok());
				break;
			case "CLOSE":
				action.click(bungiiCompletePage.Button_Close());;
				break;
			default:
				throw new Exception(" UNIMPLEMENTED STEP");
			}
			testStepVerify.isTrue(Integer.parseInt(actualTip)==Integer.parseInt(tip), "Driver should be given tip for "+tip, "Bungii Driver is given tip for" + actualTip,
                    "Bungii Driver is given tip for" + actualTip);
		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}
	}


	/**
	 * Give Tip to driver
	 * @param tipAmmount tip ammount
	 */
	public void giveTip(int tipAmmount){
		for(int i=0;i<tipAmmount;i++){
			action.click(bungiiCompletePage.Button_Plus());
		}
	}


	/**
	 * Give rating stars to driver
	 * @param starCount Number of stars
	 */
	public void giveRatting(int starCount){
		List<WebElement> Button_StarElement =bungiiCompletePage.Button_Star();
		if(starCount<=Button_StarElement.size()){
			action.click(Button_StarElement.get(starCount-1));
		}

	}
}
