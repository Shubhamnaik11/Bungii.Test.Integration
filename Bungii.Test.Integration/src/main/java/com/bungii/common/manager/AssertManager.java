package com.bungii.common.manager;

import org.testng.Assert;

/**
 * @author vishal.bagi
 *
 *All Assertion methods will go in this class
 */
public class AssertManager {
	
	

	/**
	 * Check is boolean value is true
	 * @param value boolean value to be checked
	 * @param testStep Test step name
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isTrue(boolean value, String testStep, String expectedText, String errorMessage) {
		try {
			Assert.assertTrue(value, expectedText);
			ResultManager.pass(testStep, expectedText, "Success :" + expectedText, true);
		} catch (AssertionError e) {
			//Stop test in case of failure
			ResultManager.error(testStep, expectedText, errorMessage, true);
		}

	}
	
	/**
	 * @param expectedValue Expected value 
	 * @param actualValue Actual value
	 * @param testStep Test step name
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isEquals(String actualValue,String expectedValue, String testStep, String expectedText, String errorMessage) {
		try {
			Assert.assertEquals(expectedValue, actualValue);
			ResultManager.pass(testStep, expectedText, "Success :" + expectedText, true);
		} catch (AssertionError e) {
			//Stop test in case of failure
			ResultManager.error(testStep, expectedText, errorMessage, true);
		}

	}
	

	/**
	 * Check is boolean value is false
	 * @param value boolean value to be checked
	 * @param testStep Test step name
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isFalse(boolean value, String testStep, String expectedText, String errorMessage) {
		try {
			Assert.assertFalse(value, expectedText);
			ResultManager.pass(testStep, expectedText, "Success :" + expectedText, true);
		} catch (AssertionError e) {
			//Stop test in case of failure
			ResultManager.error(testStep, expectedText, errorMessage, true);
		}

	}
	
	/**
	 * Check is boolean value is false
	 * @param value boolean value to be checked
	 * @param testStep Test step name
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isFalse(boolean value, String testStep, String expectedText, String successMessage,String errorMessage) {
		try {
			Assert.assertFalse(value, expectedText);
			ResultManager.pass(testStep, expectedText, successMessage, true);
		} catch (AssertionError e) {
			//Stop test in case of failure
			ResultManager.error(testStep, expectedText, errorMessage, true);
		}

	}
	

}
