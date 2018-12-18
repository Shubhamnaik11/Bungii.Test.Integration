package com.bungii.common.manager;

import org.openqa.selenium.WebElement;
import org.testng.Assert;


/**
 * @author vishal.bagi
 *
 *All verification method will go in this class
 */
public class VerificationManager {

	

	/**
	 * Check is boolean value is true
	 * @param value boolean value to be checked
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isTrue(boolean value, String expectedText, String errorMessage) {
		try {
			Assert.assertTrue(value, expectedText);
			ResultManager.pass( expectedText, "Success :" + expectedText, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail( expectedText, errorMessage, true);
		}

	}
	/**
	 * Check is boolean value is true
	 * @param value boolean value to be checked
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isTrue(boolean value, String expectedText,String sucessMessage, String errorMessage) {
		try {
			Assert.assertTrue(value, expectedText);
			ResultManager.pass( expectedText, sucessMessage, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail( expectedText, errorMessage, true);
		}

	}
	
	/**
	 * @param expectedValue Expected value 
	 * @param actualValue Actual value
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isEquals(String actualValue,String expectedValue, String expectedText, String errorMessage) {
		try {
			Assert.assertEquals(expectedValue, actualValue);
			ResultManager.pass( expectedText, "Success :" + expectedText, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail( expectedText, errorMessage, true);
		}

	}
	/**
	 * @param expectedValue Expected value 
	 * @param actualValue Actual value
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isEquals(String actualValue,String expectedValue, String expectedText,String sucessMessage, String errorMessage) {
		try {
			Assert.assertEquals(expectedValue, actualValue);
			ResultManager.pass( expectedText, sucessMessage, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail( expectedText, errorMessage, true);
		}
	}
	
	/**
	 * @param expectedValue Expected value 
	 * @param actualValue Actual value
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void contains(String actualValue,String expectedValue, String expectedText,String sucessMessage, String errorMessage) {
		try {
			Assert.assertTrue(actualValue.contains( expectedValue));
			ResultManager.pass( expectedText, sucessMessage, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail( expectedText, errorMessage, true);
		}
	}
	/**
	 * @param expectedValue Expected value 
	 * @param actualValue Actual value
	 * @param testStep Test step name
	 */
	public void isEquals(String actualValue,String expectedValue, String testStep) {
		try {
			Assert.assertEquals(expectedValue, actualValue);
			ResultManager.pass( expectedValue+" should be displayed", actualValue+" is correctly displayed", true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail( expectedValue+" should be displayed", actualValue+" is displayed instead of"+expectedValue, true);
		}

	}
	
	/**
	 * Check is boolean value is false
	 * @param value boolean value to be checked
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isFalse(boolean value,String expectedText,String sucessMessage, String errorMessage) {
		try {
			Assert.assertFalse(value, expectedText);
			ResultManager.pass( expectedText, sucessMessage, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail( expectedText, errorMessage, true);
		}

	}
	/**
	 * @param element Webelement object return from PageBase
	 * @param expectedText Expected Messaage to that is to be update in report
	 * @param successMessage If success this message will be published
	 * @param errorMessage If failed this message will be published
	 */
	public void isElementEnabled(WebElement element, String expectedText, String successMessage, String errorMessage) {
		Boolean isEnabled;
		try {
			isEnabled= element.isEnabled();
		} catch (Exception e) {
			isEnabled= false;
		}
		isTrue(isEnabled,expectedText,successMessage, errorMessage);
	}

	/**
	 * @param element Webelement object return from PageBase
	 * @param expectedMessage Expected Messaage to that is to be update in report
	 * @param successMessage If success this message will be published
	 * @param errorMessage If failed this message will be published
	 */
	public void isElementSelected(WebElement element,String expectedMessage,String successMessage, String errorMessage) {
		Boolean isSelected;
		try {
			isSelected= element.isSelected();
		} catch (Exception e) {
			isSelected= false;
		}
		isTrue(isSelected,expectedMessage,successMessage, errorMessage);
	}

	/**
	 * @param element Webelement object return from PageBase
	 * @param expectedMessage Expected Messaage to that is to be update in report
	 * @param successMessage If success this message will be published
	 * @param errorMessage If failed this message will be published
	 */
	public void isElementDisplayed(WebElement element,String expectedMessage,String successMessage, String errorMessage) {
		Boolean isDisplayed;
		try {
			isDisplayed= element.isSelected();
		} catch (Exception e) {
			isDisplayed= false;
		}
		isTrue(isDisplayed,expectedMessage,successMessage, errorMessage);
	}
}
