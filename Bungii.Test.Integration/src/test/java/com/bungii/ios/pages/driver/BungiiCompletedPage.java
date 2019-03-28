package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiCompletedPage extends PageBase {
	public WebElement Button_NextTrip() {return findElement("On To The Next One", PageBase.LocatorType.Name); }

	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }


		public WebElement Title_Status() { return findElement("//XCUIElementTypeOther[@name='Bungii Completed']", LocatorType.XPath); }

		public WebElement Text_TotalTimeLabel(){return  findElement("//XCUIElementTypeStaticText[@name='Total Time']",LocatorType.XPath);}

		public WebElement Text_TotalTime() { return findElement("//XCUIElementTypeStaticText[@name='Total Time']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }

		public WebElement Text_TotalDistanceLabel(){return  findElement("//XCUIElementTypeStaticText[@name='Total Distance']",LocatorType.XPath);}

		public WebElement Text_TotalDistance() { return findElement("//XCUIElementTypeStaticText[@name='Total Distance']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }

		public WebElement Text_TotalEarningsLabel(){return  findElement("//XCUIElementTypeStaticText[@name='Total Earnings']",LocatorType.XPath);}

		public WebElement Text_TotalEarnings() { return findElement("//XCUIElementTypeStaticText[@name='Total Earnings']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }

		public WebElement Text_Label(){return  findElement("//XCUIElementTypeStaticText[@value='Cha-Ching!']",LocatorType.XPath);}
		public WebElement Image_Dollar(){return  findElement("icon_dollar",LocatorType.Name);}
}
