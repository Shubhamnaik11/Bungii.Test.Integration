package com.bungii.common.utilities;

import com.bungii.common.manager.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class ScreenshotUtility {
	private static LogUtility logger = new LogUtility(ScreenshotUtility.class);

	/**
	 * @param path_screenshot Folder Path in which screenshot will be captured
	 * @return absolute path of screenshot
	 * @throws IOException
	 */
	public String screenshot(String path_screenshot) throws IOException  {
		try {

		File srcFile = ((TakesScreenshot) DriverManager.getObject().getDriver()).getScreenshotAs(OutputType.FILE);
		
	    String filename=UUID.randomUUID().toString(); 
	    File targetFile=new File(path_screenshot+"/" + filename +".jpg");

			FileUtils.copyFile(srcFile,targetFile);
			return targetFile.getName();
			//return targetFile.getPath();
		}catch (Exception e){
			logger.detail(" Problem capturing screenshot");
			return "";
		}

	}
	///TODO check feasibility
	public void videoRecord(){
		
	}
}
