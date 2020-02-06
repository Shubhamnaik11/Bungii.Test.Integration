package com.bungii.common.utilities;



import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;


/**
 * @author vishal.bagi
 *Write test result to HTML file
 */

public class ReportGeneratorUtility {
	//TODO create new summary file
	private String detailsFolderPath,/*detailFilepath,*/summaryPath,screenshotFolder,miscFolder,logFolder;
	private Writer bufWriter1, fileWriter;
	private ArrayList<String> detailsArray = new ArrayList<String>();
	private ArrayList<String> summaryArray = new ArrayList<String>();
	private ArrayList<String> stackTraceArray = new ArrayList<String>();


	private final static String SUMMARY_TITLE="TEST SUMMARY REPORT";
	private final static String logoPath =PropertyUtility.getResultConfigProperties("MISC_DIRECTORY")+"/"+PropertyUtility.getResultConfigProperties("LOGO_FILENAME");

	private static LogUtility logger = new LogUtility(ReportGeneratorUtility.class);
	private int testCases = 0;
	private int failed = 0;
	private int passed = 0;
	private int inconclusive=0;
	private int testStepCount=0;
	private Date  testFinish, startTime = null;

	private Date testStepStart, testStepEnd;
	private String tcName;
	private String featureName;
	private boolean isTcVerifyFailed;

	public ReportGeneratorUtility(String detailsFolderPath, String screenshotFolder, String miscFolder, String logFolder){
		this.detailsFolderPath=detailsFolderPath;
		this.screenshotFolder=screenshotFolder;
		this.miscFolder=miscFolder;
		this.logFolder=logFolder;
	}


	public void startSuiteFile() throws Exception {
		//startDetailsFile(this.detailFilepath, SUMMARY_TITLE);
	}
	


	
	/**
	 * Method that will be called at end of Test Suite
	 */
	public void endSuiteFile() {
		//logDetails(summaryArray);
		//endSuiteSummary();
		//testCaseHeaderTemplate();
		//logDetails(detailsArray);
		//finishDetailsFile();
		createResultFileFromTemplate();
	}
	
	public void createResultFileFromTemplate(){
	    try {

			File result= new File(detailsFolderPath+this.featureName.replace(".feature","").replace(" ","")+".html"); //PropertyUtility.getResultConfigProperties("SUMMARY_FILE"));
			BufferedReader br =new BufferedReader(new InputStreamReader(ReportGeneratorUtility.class.getResourceAsStream("/" + "Templates/resulttemplate.html")));
	    String s;
	    String totalStr = "";
	
			while ((s = br.readLine()) != null) {
			    totalStr += s;	        
			}
	        totalStr = totalStr.replaceAll("<!--LOGO.PATH-->",logoPath);
            totalStr = totalStr.replaceAll("<!--FEATURE.NAME-->",this.featureName);
	        totalStr = totalStr.replaceAll("<!--SUMARRY-->", Matcher.quoteReplacement(getLogDetails(summaryArray)));
			totalStr = totalStr.replaceAll("<!--DETAILS-->", Matcher.quoteReplacement(getLogDetails(detailsArray)));
	        totalStr = totalStr.replaceAll("<!--PASSED.COUNT-->",passed+"");
	        totalStr = totalStr.replaceAll("<!--FAILED.COUNT-->",failed+"");
	        totalStr = totalStr.replaceAll("<!--INCONCLUSIVE.COUNT-->",inconclusive+"");

	        FileWriter fw = new FileWriter(result);
	    fw.write(totalStr);
	    fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method that will be called before start of test case
	 * @param tcName Name of test case 
	 */
	public void startTestCase(String tcName , String featureName) {
		this.tcName = tcName;
		this.featureName = featureName;
		this.startTime = new Date();
		this.isTcVerifyFailed=false;
		this.testStepCount=0;
		addTestCaseEntryInDetailsTable(tcName, featureName);
		ThreadLocalStepDefinitionMatch.resetNumberOfSteps();
	}

	/**
	 * @param name Add Test case entry to details table
	 */
	public void addTestCaseEntryInDetailsTable(String name, String featureName) {
		String str = "<tr class='header'><td colspan='8'  >" +"Test case: "+ name + "</td></tr>"; ;
		detailsArray.add(str);
	}

	/**
	 * Add test case step data to file buffer
	 * @param eventData Map that contains test details
	 */
	public void addTestData(Map<String, String> eventData) {

		testStepStart = testStepEnd == null ? startTime : testStepEnd;
		testStepEnd = new Date();
		int stepCount= testStepCount+1;
		String str = "<tr><td + rightSpan + >" + stepCount + "</td>";
		str = str + "<td>" + eventData.get("name").toString() + "</td>";
		if (eventData.get("type").toString() == "PASSED") {
			str = str + "<td style='background-color:MediumSeaGreen;'>" + eventData.get("type").toString() + "</td>";
		}
		else {
			str = str + "<td style='background-color:pink;'>" + eventData.get("type").toString() + "</td>";
		}

		str = str + "<td>" + eventData.get("expected").toString() + "</td>";
		str = str + "<td>" + screenDumpLink((String) eventData.get("actual"), eventData) + "</td>";
		str = str + "<td>" + testStepStart + "</td>";
		str = str + "<td>" + testStepEnd + "</td>";
		str = str + "<td>" + calculateDuration(testStepEnd, testStepStart) + "</td>"+"</tr>";;

		detailsArray.add(str);
		if (eventData.get("type").toString() != "PASSED") {
			detailsArray.addAll(stackTraceArray);
			stackTraceArray.clear();
		}
		//increase step count ;
		testStepCount++;
	}
	/**
	 * Add test case step data to file buffer
	 * @param eventData Map that contains test details
	 */
	public void addStackTrace(Map<String, String> eventData) {
   if(eventData.get("actual").toString()!= "") {
       String str = "<tr><td + rightSpan + ></td>";
       str = str + "<td colspan=8 align='left'>Error Log : <div class='maincontent'><div class='content'>" + eventData.get("actual").toString() + "</div><div class='txtcol'><a>Show More</a></div></div></td>";
       stackTraceArray.add(str);
   }
   else {
       stackTraceArray.add("");
         }
	}

	/**
	 * @param message Message that is to be updated
	 * @param eventData Map in which screendump key is to be search
	 * @return String with link and message
	 */
	private String screenDumpLink(String message, Map<String, String> eventData) {
		String sDumpFile;
		try {
			sDumpFile = (String) (eventData.get("screenDump"));

		} catch (Exception e) {

			return message;
		}

		if (sDumpFile == null) {
			// return null;
			return message;
		}

		return "<a href='" + sDumpFile.replace("\\", "/") + "'>" + message + "</a>";
	}

	/**
	 * @param isFailed boolean flag set by testng assert statements
	 */
	public void endTestCase(boolean isFailed) {
		String str1;
		testCases++;
		testFinish = new Date();
		String str = "";
		String status = "";
		//check testng assert and local flag as well
		if (!isFailed &&!isTcVerifyFailed){
			status = "<td style='background-color:MediumSeaGreen;'>Pass</td>";
			passed++;
		}
		else {
			status = "<td style='background-color:pink;'>Fail</td>";
			failed++;
		}

		str = "<td>" + ThreadLocalStepDefinitionMatch.getNumberOfSteps() + "</td>" + "<td>" + this.startTime
				+ "</td><td>" + this.testFinish + "</td><td>" + calculateDuration(this.testFinish, this.startTime);
/*		str = "<td>" + this.testStepCount + "</td>" + "<td>" + this.startTime
				+ "</td><td>" + this.testFinish + "</td><td>" + calculateDuration(this.testFinish, this.startTime);*/

		str1 = "<td cursor:'pointer;'>" + tcName + "</td>" + status  + str;
		summaryArray.add(str1);
	}

	/**
	 * Mark test case as failed.  Dont stop test, use in case of verify
	 */
	public void verificationFailed(Map<String, String> eventData){
		this.isTcVerifyFailed=true;
		endTestDataContainer(eventData);
		logger.trace("Marked test case :"+this.tcName +" failed as verification got failed"  );
	}
	
	/**
	 * @param d2 End time
	 * @param d1 Start time
	 * @return difference inbetween time as string
	 */
	private String calculateDuration(Date d2, Date d1) {
		long diff = d2.getTime() - d1.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		try {
			String diffTime = cal(String.valueOf(diffHours)) + ":" + cal(String.valueOf(diffMinutes)) + ":"
					+ cal(String.valueOf(diffSeconds));
			return diffTime;
		} catch (Exception e) {
			logger.handleError("Error in calculating duration in HTML report", e);
			return null;
		}

	}

	/**
	 * @param time current  time as string
	 * @return Append time with two characters in return
	 */
	private String cal(String time) {
		while (time.length() != 2)
			time = "0" + time;
		return time;
	}

	/**
	 * Close summary file
	 */
	private void closeDetailsFile() {
		try {
			bufWriter1.close();
		} catch (IOException e) {
			logger.handleError("Exception caught while closing details file ", e);
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				logger.handleError("Exception caught while closing details file ", e);
			} finally {
				fileWriter = bufWriter1 = null;
			}
		}
	}

	/**
	 * Write input string to output stream
	 * @param lines String that is to be written
	 */
	private void writeDetails(String lines) {
		try {
			bufWriter1.write(lines);
		} catch (IOException e) {
			logger.handleError("Exception caught while writing details in HTML : ", e);
		}
	}
	public boolean isScenarioFailed(){
		return this.isTcVerifyFailed;
	}

	private String getLogDetails(ArrayList<String> strArray) {
		String strDetails = "";
		for (String str : strArray)
			strDetails+="<tr>" + str + "</tr>";
		final String cleansedString = StringUtils.normalizeSpace(strDetails);
		//logger.detail("Generated Report : "+cleansedString);
		return strDetails;
	}

	public void endTestDataContainer(Map<String, String> eventData)
	{
		String str = "<tr><td + rightSpan + ><td>Some steps are skipped due to error ..</td>";
		//str = str + "<td style='background-color:pink;'> " + eventData.get("type").toString() + "</td>";

	//	str = str + "<td>" + eventData.get("expected").toString() + "</td>";
	//	str = str + "<td>" + screenDumpLink((String) eventData.get("actual"), eventData) + "</td>";

		detailsArray.add(str);

	}
}
