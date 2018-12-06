package com.bungii.common.manager;

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
	 * @param testStep Test step name
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isTrue(boolean value, String testStep, String expectedText, String errorMessage) {
		try {
			Assert.assertTrue(value, expectedText);
			ResultManager.pass(testStep, expectedText, "Success :" + expectedText, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail(testStep, expectedText, errorMessage, true);
		}

	}
	/**
	 * Check is boolean value is true
	 * @param value boolean value to be checked
	 * @param testStep Test step name
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isTrue(boolean value, String testStep, String expectedText,String sucessMessage, String errorMessage) {
		try {
			Assert.assertTrue(value, expectedText);
			ResultManager.pass(testStep, expectedText, sucessMessage, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail(testStep, expectedText, errorMessage, true);
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
			//mark test case fail and continue test
			ResultManager.fail(testStep, expectedText, errorMessage, true);
		}

	}
	/**
	 * @param expectedValue Expected value 
	 * @param actualValue Actual value
	 * @param testStep Test step name
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isEquals(String actualValue,String expectedValue, String testStep, String expectedText,String sucessMessage, String errorMessage) {
		try {
			Assert.assertEquals(expectedValue, actualValue);
			ResultManager.pass(testStep, expectedText, sucessMessage, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail(testStep, expectedText, errorMessage, true);
		}
	}
	
	/**
	 * @param expectedValue Expected value 
	 * @param actualValue Actual value
	 * @param testStep Test step name
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void contains(String actualValue,String expectedValue, String testStep, String expectedText,String sucessMessage, String errorMessage) {
		try {
			Assert.assertTrue(actualValue.contains( expectedValue));
			ResultManager.pass(testStep, expectedText, sucessMessage, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail(testStep, expectedText, errorMessage, true);
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
			ResultManager.pass(testStep, expectedValue+" should be displayed", actualValue+" is correctly displayed", true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail(testStep, expectedValue+" should be displayed", actualValue+" is displayed instead of"+expectedValue, true);
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
			//mark test case fail and continue test
			ResultManager.fail(testStep, expectedText, errorMessage, true);
		}

	}
	
	/**
	 * Check is boolean value is false
	 * @param value boolean value to be checked
	 * @param testStep Test step name
	 * @param expectedText Expected Output
	 * @param errorMessage If check if failed , this message will be displayed  in report
	 */
	public void isFalse(boolean value, String testStep, String expectedText,String sucessMessage, String errorMessage) {
		try {
			Assert.assertFalse(value, expectedText);
			ResultManager.pass(testStep, expectedText, sucessMessage, true);
		} catch (AssertionError e) {
			//mark test case fail and continue test
			ResultManager.fail(testStep, expectedText, errorMessage, true);
		}

	}
}
