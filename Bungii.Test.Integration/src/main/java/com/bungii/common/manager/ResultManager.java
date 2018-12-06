package com.bungii.common.manager;

import com.bungii.common.enums.ResultType;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.ScreenshotUtility;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResultManager {
	private static ReportManager reportManager;
	private String detailsPath;
	private static String screenShotFolder;
	private static LogUtility logger = new LogUtility(ResultManager.class);

	public ResultManager(ReportManager reportManager) {
		this.reportManager = reportManager;
		this.detailsPath = reportManager.getTestResultFolderName();

	}

	/**
	 * Log step details to Result
	 * 
	 * @param name
	 *            Name of step
	 * @param expected
	 *            Expected result of step
	 * @param actual
	 *            Actual result of step
	 * @param screenDump
	 *            take screenshot or not
	 */
	public static void log(String name, String expected, String actual, Boolean... screenDump) {
		reportManager.addTestData(getDataMap(name, expected, actual, ResultType.DONE.toString(), screenDump));
		logger.trace("For steps :" + name + " expected is :" + expected + " and actual is" + actual);
	}

	/**
	 * Log step details to Result with status pass
	 * 
	 * @param name
	 *            Name of step
	 * @param expected
	 *            Expected result of step
	 * @param actual
	 *            Actual result of step
	 * @param screenDump
	 *            take screenshot or not
	 */
	public static void pass(String name, String expected, String actual, Boolean... screenDump) {
		reportManager.addTestData(getDataMap(name, expected, actual, ResultType.PASSED.toString(), screenDump));
		logger.detail("For steps :" + name + " expected is :" + expected + " and actual is" + actual);
	}

	/**
	 * Log step details to Result with fail status . Dont stop test
	 * 
	 * @param name
	 *            Name of step
	 * @param expected
	 *            Expected result of step
	 * @param actual
	 *            Actual result of step
	 * @param screenDump
	 *            take screenshot or not
	 */
	public static void fail(String name, String expected, String actual, Boolean... screenDump) {
		reportManager.addTestData(getDataMap(name, expected, actual, ResultType.FAILED.toString(), screenDump));
		logger.error("For steps :" + name + " expected is :" + expected + " and actual is" + actual);
		reportManager.verificationFailed();
	}

	/**
	 * Log step details to Result with fail status . Stop test
	 * 
	 * @param name
	 *            Name of step
	 * @param expected
	 *            Expected result of step
	 * @param actual
	 *            Actual result of step
	 * @param screenDump
	 *            take screenshot or not
	 */
	public static void error(String name, String expected, String actual, Boolean... screenDump) {
		reportManager.addTestData(getDataMap(name, expected, actual, ResultType.ERROR.toString(), screenDump));
		logger.error("For steps :" + name + " expected is :" + expected + " and actual is" + actual);
		reportManager.verificationFailed();
		Assert.assertTrue(false, "Error in executon ,Please check logs/ report for more details");
	}

	/**
	 * Log step details to Result with warning status .
	 * 
	 * @param name
	 *            Name of step
	 * @param expected
	 *            Expected result of step
	 * @param actual
	 *            Actual result of step
	 * @param screenDump
	 *            take screenshot or not
	 */
	public static void warning(String name, String expected, String actual, Boolean... screenDump) {
		reportManager.addTestData(getDataMap(name, expected, actual, ResultType.WARNING.toString(), screenDump));
		logger.warning("For steps :" + name + " expected is :" + expected + " and actual is" + actual);
	}

	/**
	 * @param name
	 *            Name of step
	 * @param expected
	 *            Expected result of step
	 * @param actual
	 *            Actual result of step
	 * @param logType
	 *            Log type
	 * @param screenDump
	 *            capture screenshot or not
	 * @return combine input data and return it as map
	 */
	private static Map<String, String> getDataMap(String name, String expected, String actual, String logType,
			Boolean... screenDump) {

		ScreenshotUtility screenshotManager= new ScreenshotUtility();
		Map<String, String> data = new HashMap<String, String>();
		// If screendump flag is true, capture screenshot and put it data map
		try {
			if (screenDump.length > 0) {
				if (screenDump[0] == true)
					data.put("screenDump", PropertyUtility.getResultConfigProperties("SCREENSHOTS_DIRECTORY")+"/"+screenshotManager.screenshot(reportManager.getTestScreenShotFolderName()));
			}

		} catch (IOException e) {
			logger.error("Error while capturing/coping screenshot" + e.getMessage());
		}

		data.put("name", name);
		data.put("type", logType);
		data.put("expected", expected);
		data.put("actual", actual);
		return data;
	}

}
