package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.PromosPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class PromoSteps extends DriverBase {
	PromosPage promosPage;
	ActionManager action = new ActionManager();
	private static LogUtility logger = new LogUtility(PromoSteps.class);

	public PromoSteps(PromosPage promosPage) {
		this.promosPage = promosPage;
	}
	
	@When("^I add \"([^\"]*)\" PromoCode$")
	public void iAddPromoCode(String key) throws Throwable {
		List <String> codeList= new ArrayList<String>();
		try{
			switch (key.toUpperCase()) {
			case "VALID":
				codeList =(List<String>) cucumberContextManager.getFeatureContextContext("VALID");
				break;
			case "{EXPIRED}":
				codeList=(List<String>) cucumberContextManager.getFeatureContextContext("EXPIRED");
				break;
			default:
				throw new Exception(" UNIMPLEMENTED STEP");
			}

		String inputCode=addUniquePromoCode(codeList);
		cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputCode);
		pass( "I should able to add unique code", "I added code "+inputCode, true);
		
		testStepVerify.isFalse(inputCode.equals(""),

				"I should able to enter unique "+key +"promo code",
				"I added promocode :"+inputCode,
				"I was not able to find unique promocode to enter");

	} catch (Exception e) {
		logger.error("Error performing step" + e.getMessage());
		error( "Step  Should be sucessfull", "Error performing step,Error", true);
	}
	}

    @Then("^I should able to see expected promo code in available promo code$")
    public void i_should_able_to_see_expected_promo_code_in_available_promo_code()  {
    	try{
    	String usedPromoCode=(String)cucumberContextManager.getScenarioContext("ADDED_PROMO_CODE");
    	testStepVerify.isTrue(isPromoCodePresent(usedPromoCode), "I should able to see expected promo code '"+usedPromoCode+"' in available promo code", "I was able to see '"+usedPromoCode +"' in available promo code", "I was not able to see '"+usedPromoCode +"' in available promo code");
   
	} catch (Exception e) {
		logger.error("Error performing step" + e.getMessage());
		error( "Step  Should be sucessfull", "Error performing step,Error", true);
	}
    }
    
    @When("^I add Same Promo Code again$")
    public void i_add_same_promo_code_again() throws Throwable {
    	try{
    	String usedPromoCode=(String)cucumberContextManager.getScenarioContext("ADDED_PROMO_CODE");
    	addPromoCode(usedPromoCode);
		testStepAssert.isFalse(usedPromoCode.equals(""),

				"I should able to add Same Promo Code again",
				"I added promocode :"+usedPromoCode,
				"I was not able to find previous promocode to enter");	} catch (Exception e) {
		logger.error("Error performing step" + e.getMessage());
		error( "Step  Should be sucessfull", "Error performing step,Error", true);
	}
    }

	/**
	 * Add given promocode in the field
	 *
	 * @param newCode
	 *            Promo code that is to be entered
	 */
	public void addPromoCode(String newCode) {
		promosPage.TextBox_EnterCode().clear();
		promosPage.TextBox_EnterCode().sendKeys(newCode);
	}


	/**
	 * Click on promo image is present
	 *
	 * @return boolean value if promo image is present
	 */
	public boolean isPromoImagePresent() {
		return promosPage.isElementEnabled(promosPage.Image_Promo());
	}

	/**
	 * Add unique promo code
	 *
	 * @param newCode
	 *            list of promocode that is fetched from admin site
	 * @return unique promocode that is entered
	 */
	public String addUniquePromoCode(List<String> newCode) {
		List<String> availableCode = getListOfAvailablePromoCode();

		ArrayList<String> uniques = new ArrayList<String>(newCode);
		uniques.removeAll(availableCode);
		String validCode = "";
		validCode = uniques.get(0);
		promosPage.TextBox_EnterCode().clear();
		promosPage.TextBox_EnterCode().sendKeys(validCode);

		//waitClearEnterText(TextBox_EnterCode, validCode);

		return validCode;

	}

	/**
	 * Get list of available promo code that is already present on promo page
	 *
	 * @return
	 */
	private List<String> getListOfAvailablePromoCode() {
		List<WebElement> codes =promosPage.List_AvailablePromoCode();
		List<String> availableCodes = new ArrayList<>();
		for (WebElement code : codes) {
			String value = action.getValueAttribute(code);
			value = value.substring(value.indexOf("-") + 2);
			availableCodes.add(value);
		}
		return availableCodes;
	}

	/**
	 * Check if promo code is present in available promocode
	 *
	 * @param expectedCode
	 *            Promo code that is to be checked
	 * @return boolean value if promocode is present
	 */
	public boolean isPromoCodePresent(String expectedCode) {
		boolean isPresent = false;
		List<WebElement> codes =promosPage.List_AvailablePromoCode();
		for (WebElement code : codes) {
			if (action.getValueAttribute(code).contains(expectedCode)) {
				isPresent = true;
				break;

			}
		}

		return isPresent;
	}
}
