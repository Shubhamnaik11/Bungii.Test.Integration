package com.bungii.common.manager;

import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.ReportGeneratorUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author vishal.bagi
 *TODO:Wrapper for HTML Report com.bungii.android.manager
 */
public class ReportManager {
	private static LogUtility logger = new LogUtility(ReportManager.class);
	private static ReportGeneratorUtility htmlReportManager;
//	private static TestResultManager testResultManager;
	private String detailsFolderPath,/*detailFilepath,*/screenshotFolder,miscFolder,logFolder;
	
	//TODO : Create summary file and test details seperate
	/**
	 * Iniitialises and create all file name
	 * @throws Exception
	 */
	public void startSuiteFile(String resultFolderName) throws Exception {
		
		
		this.detailsFolderPath=FileUtility.getSuiteResource(PropertyUtility.getResultConfigProperties("RESULT_DIRECTORY"), GetResultFolderName(resultFolderName) );
		this.screenshotFolder=this.detailsFolderPath+PropertyUtility.getResultConfigProperties("SCREENSHOTS_DIRECTORY");
		this.miscFolder=this.detailsFolderPath+PropertyUtility.getResultConfigProperties("MISC_DIRECTORY");
		this.logFolder=this.detailsFolderPath+PropertyUtility.getResultConfigProperties("LOG_DIRECTORY");
		//removed detail files path as npew
		//this.detailFilepath=this.detailsFolderPath+PropertyUtility.getFileLocations("SUMMARY_FILE");
		htmlReportManager= new ReportGeneratorUtility(this.detailsFolderPath,this.screenshotFolder,this.miscFolder,this.logFolder);
		/*this.testResultManager= */new ResultManager(this);
		createInitialFolder();
		htmlReportManager.startSuiteFile();
	}
	
	/**Create inital folder for result
	 * @throws Exception
	 */
	private void createInitialFolder() throws Exception{
		try {
			//Create Result and screenshot sub folder
			FileUtility.makeFolder(this.detailsFolderPath);
			FileUtility.makeFolder(this.screenshotFolder);
			FileUtility.makeFolder(this.miscFolder);
			FileUtility.copyFile(FileUtility.getSuiteResource(PropertyUtility.getResultConfigProperties("LOGO_DIRECTORY"), PropertyUtility.getResultConfigProperties("LOGO_FILENAME")),this.miscFolder+"/"+PropertyUtility.getResultConfigProperties("LOGO_FILENAME"));
			} catch (Exception e) {
				logger.handleError("Error while creating HTML report folder", e);
			}
	}
	
	
	/**
	 * @return Create folder name 
	 */
	public static String GetResultFolderName(String resultFolderName) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date now = new Date();
		String parentFolder = resultFolderName.equals("")?"":resultFolderName+"/";
		System.out.println("parentFolder"+parentFolder);
		String strDate = parentFolder+PropertyUtility.getResultConfigProperties("RESULT_FOLDER_INITIAL").trim()+"_"+System.getProperty("runner.class")+"/";

		return strDate;
	}
	
	
	/**
	 * @return return folder name of Result folder
	 */
	public String getTestResultFolderName(){
		return this.detailsFolderPath;
	}


	/**
	 * @return return folder name of Result folder
	 */
	public String getTestScreenShotFolderName(){
		return this.screenshotFolder;
	}
	
	/**
	 * Method that will be called before start of test case
	 * @param tcName Name of test case 
	 */
	public void startTestCase(String tcName) {
		htmlReportManager.startTestCase(tcName);
	}

	/**
	 * Method that will be called end of test case
	 * @param isFailed
	 */
	public void endTestCase(boolean isFailed){
		htmlReportManager.endTestCase(isFailed);
	}
	/**
	 * Set test case failed flag to true
	 */
	public void verificationFailed(){
		htmlReportManager.verificationFailed();
	}

	public boolean isVerificationFailed(){return htmlReportManager.isScenarioFailed();}

	/**
	 *Add row in Result 
	 * @param eventData map of information
	 */
	public void addTestData(Map<String, String> eventData){
		htmlReportManager.addTestData(eventData);
	}
	/**
	 * Method that will be called at end of Test Suite
	 */
	public void endSuiteFile() {
		htmlReportManager.endSuiteFile();
		copyLogFile();
	}
	
	/**
	 * Copy logd file to result folder
	 */
	public void copyLogFile(){
		FileUtility.makeFolder(this.logFolder);
		FileUtility.copyFile(FileUtility.getSuiteResource(logger.getLogFileName(), ""), this.logFolder + "/Testlogs.log");

	}
	
/*	//TODO :REMOVE THIS
	*//**
	 * remove log file 
	 *//*
	public void deleteLogFile(){
		FileUtils.deletFile(ResourcePaths.getInstance().getSuiteResource(logger.getLogFileName(), ""));
	}*/
}
