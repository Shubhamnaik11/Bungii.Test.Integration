package com.bungii.ios.stepdefinitions.customer;

import com.bungii.ios.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.BungiiCompletePage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;


public class BungiiCompleteSteps extends DriverBase {
	BungiiCompletePage bungiiCompletePage;
	private static LogUtility logger = new LogUtility(BungiiCompleteSteps.class);
	ActionManager action = new ActionManager();
	GeneralUtility utility= new GeneralUtility();
	public BungiiCompleteSteps(BungiiCompletePage bungiiCompletePage) {
		this.bungiiCompletePage = bungiiCompletePage;

	}
	static final double MIN_COST = 39;

	@Then("^Bungii customer should see \"([^\"]*)\" on Bungii completed page$")
	public void bungii_customer_should_see_something_on_bungii_completed_page(String identifier) throws Throwable {
		try {
			verifyBungiiCompletedPage();

			switch (identifier) {
				case "correct details with promo":
					verifyTripValue();
					verifyDiscount();
					break;
				case "correct details":
					verifyTripValue();
					break;
				default:
					error("UnImplemented Step or incorrect button name", "UnImplemented Step");
					break;
			}

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}
	/**
	 * Verify Static texts on Bungii Completed page
	 */
	public void verifyBungiiCompletedPage(){
		testStepVerify.isElementEnabled(bungiiCompletePage.PageTitle_BungiiComplete(),"Bungii Complete Page should be displayed");
		//     testStepVerify.isElementEnabled(bungiiCompletePage.Title_RateYourDriver(),"'Rate Your driver'  should be displayed");
		String totalTime=action.getValueAttribute(bungiiCompletePage.Text_BungiiTime()),totalDistance=action.getValueAttribute(bungiiCompletePage.Text_Distance());
		int tripActualTime=Integer.parseInt(utility.getActualTime());
		String tripDistance =(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");

		testStepVerify.isTrue(totalTime.contains(tripActualTime+ "  minute"),"Total time should contains"+tripActualTime+" minute");
		testStepVerify.isTrue(totalDistance.equalsIgnoreCase(tripDistance),"Total distance should contains "+tripDistance );
		//Vishal[2503]:TODO: add more
	}

	/**
	 * Verify variable texts in Bungii Complete Page
	 */
	public void verifyTripValue(){        action.swipeDown();
		double tripActualTime=Double.parseDouble(utility.getActualTime());

		String totalCost=action.getValueAttribute(bungiiCompletePage.Text_FinalCost()).split(" ")[0];

	//	String totalTime=action.getValueAttribute(bungiiCompletePage.Text_BungiiTime()).split(" ")[0],totalDistance=action.getValueAttribute(bungiiCompletePage.Text_Distance()).split(" ")[0],totalCost=action.getValueAttribute(bungiiCompletePage.Text_FinalCost()).split(" ")[0];
		String promoValue=String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));

		String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
		String totalDistance=utility.getEstimateDistance();


		Double expectedTotalCost=utility.bungiiCustomerCost(totalDistance,String.valueOf(tripActualTime),promoValue,numberOfDriver);
		String truncValue = new DecimalFormat("#.##").format(expectedTotalCost);
		if(!truncValue.contains("."))truncValue=truncValue+".00";

		testStepVerify.isEquals(totalCost,"$" + String.valueOf(truncValue));
		cucumberContextManager.setScenarioContext("BUNGII_COST_CUSTOMER",totalCost);

	}
	public  void verifyDiscount(){
		double tripActualTime=Double.parseDouble(utility.getActualTime());
		String totalTime=action.getValueAttribute(bungiiCompletePage.Text_BungiiTime()).split(" ")[0],totalDistance=action.getValueAttribute(bungiiCompletePage.Text_Distance()).split(" ")[0];
		String Promo=String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));
		String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

		Double promoValue=0.0;
		String distanceValueDB=utility.getEstimateDistance();

		double distance =Double.parseDouble(distanceValueDB);// Double.parseDouble(totalDistance.replace(" miles", ""));

		//double tripActualTime = Double.parseDouble(totalTime);
		double tripValue = distance + tripActualTime;
		if(numberOfDriver.equalsIgnoreCase("DUO"))
			tripValue=tripValue*2;

		if(Promo.contains("$"))
			promoValue=Double.valueOf(Promo.replace("-$", ""));
		else if(Promo.contains("%"))
			promoValue=Double.valueOf(tripValue*Double.parseDouble(Promo.replace("-", "").replace("%", ""))/100);
		//if final cost with promo is less than 39, then discount is reduced
		if((tripValue-promoValue)<MIN_COST)
			promoValue=tripValue-MIN_COST;

		String promoDiscountValue = new DecimalFormat("#.##").format(promoValue);

		if(!promoDiscountValue.contains("."))promoDiscountValue=promoDiscountValue+".00";

		//  testStepVerify.isEquals(actualDiscount,"$" + promoValue);
		testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Discount(),"$" + promoDiscountValue,"Discount value should be promo Value"+Promo,"Discount value is "+promoDiscountValue,"Discount value is not "+promoDiscountValue);
	}

	@When("^I rate Bungii Driver  with following details and Press \"([^\"]*)\" Button$")
	public void iRateBungiiDriverWithFollowingDetailsAndPressButton(String button, DataTable tipInformation) {
		try {
			
			//Input from user
			Map<String, String> data = tipInformation.transpose().asMap(String.class, String.class);
			String ratting = data.get("Ratting"), tip = data.get("Tip");
			//give tip and fetch actual tip
			giveTip(Integer.parseInt(tip));
			String actualTip = bungiiCompletePage.Text_TipValue().getAttribute("value").replace("$","");
			
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
			testStepVerify.isTrue(  (int)Double.parseDouble(actualTip)==Integer.parseInt(tip), "driver should be given tip for "+tip, "Bungii driver is given tip for" + actualTip,
                    "Bungii driver is given tip for" + actualTip);
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
