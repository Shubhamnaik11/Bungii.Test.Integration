package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BungiiCompletePage extends PageBase {



	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Text_TipValue() {return findElement("//XCUIElementTypeButton[@name='-']/following::XCUIElementTypeTextField", PageBase.LocatorType.XPath); }


	public WebElement Text_BungiiTime() {return findElement("//XCUIElementTypeStaticText[@name='Bungii time']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);}
	public WebElement Text_Distance() {return findElement("//XCUIElementTypeStaticText[@name='Distance']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);}
	public WebElement Text_FinalCost() {return findElement("//XCUIElementTypeStaticText[@name='Final Cost']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);}
	public WebElement Text_FinalCost_Duo() {return findElement("//XCUIElementTypeStaticText[@name='Final cost for two drivers']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);}
	public WebElement Text_Discount() {return findElement("//XCUIElementTypeStaticText[@name='Discount']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);}
	public WebElement Text_Email() {return findElement("//XCUIElementTypeStaticText[@name='A detailed receipt will be emailed to you.']", LocatorType.XPath);}

/*	public WebElement PageTitle_BungiiComplete() { return findElement("//XCUIElementTypeOther[@name=\"BUNGII COMPLETE\"]", PageBase.LocatorType.XPath); }
	public WebElement Button_Back() {return findElement("Back", PageBase.LocatorType.Name); }
	public List<WebElement> Button_Star() {return findElements("rating unselected star icon", PageBase.LocatorType.Name); }
	public WebElement Button_Plus() {return findElement("+", PageBase.LocatorType.Name); }
	public WebElement Button_Ok() {return findElement("OK", PageBase.LocatorType.Name); }
	public WebElement Button_Close() {return findElement("close button icon", PageBase.LocatorType.Name); }
	public WebElement Button_Minus() {return findElement("-", PageBase.LocatorType.Name); }*/
	//public WebElement PageTitle_BungiiComplete() { return findElement("//XCUIElementTypeOther[@name=\"BUNGII COMPLETE\"]", PageBase.LocatorType.XPath); }

	public WebElement PageTitle_BungiiComplete() { return findElement("type == 'XCUIElementTypeOther' AND name == 'BUNGII COMPLETE'", LocatorType.Predicate); }
	public WebElement Button_Back() {return findElement("Back", LocatorType.AccessibilityId); }
	public List<WebElement> Button_Star() {return findElements("rating unselected star icon", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Plus() {return findElement("+", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Ok() {return findElement("OK", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Close() {return findElement("close button icon", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Minus() {return findElement("-", PageBase.LocatorType.AccessibilityId); }

	public WebElement Button_Driver2Plus() {return findElements("+", PageBase.LocatorType.AccessibilityId).get(1); }
	public WebElement Button_Driver2Minus() {return findElements("-", PageBase.LocatorType.AccessibilityId).get(1); }

	public WebElement Image_Profile1Placeholder() {return findElements("//XCUIElementTypeImage[@name=\"profile_placeholder\"]", LocatorType.XPath).get(0); }
	public WebElement Text_Driver1Name() {return findElements("//XCUIElementTypeImage[@name=\"profile_placeholder\"]/following-sibling::XCUIElementTypeStaticText[1]", LocatorType.XPath).get(0); }
	public WebElement Text_GiveATip() {return findElements("//XCUIElementTypeImage[@name=\"profile_placeholder\"]/following-sibling::XCUIElementTypeStaticText[2]", LocatorType.XPath).get(0); }
	public WebElement Text_RateDriver() {return findElements("//XCUIElementTypeImage[@name=\"profile_placeholder\"]/following-sibling::XCUIElementTypeStaticText[3]", LocatorType.XPath).get(0); }
	public List<WebElement> Button_GenericDriver1star() {return findElements("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeButton[contains(@name,\"star icon\")]", LocatorType.XPath); }

	public WebElement Image_Profile2Placeholder() {return findElements("//XCUIElementTypeImage[@name=\"profile_placeholder\"]", LocatorType.XPath).get(1); }
	public WebElement Text_Driver2Name() {return findElements("//XCUIElementTypeImage[@name=\"profile_placeholder\"]/following-sibling::XCUIElementTypeStaticText[1]", LocatorType.XPath).get(1); }
	public WebElement Text_Driver2GiveATip() {return findElements("//XCUIElementTypeImage[@name=\"profile_placeholder\"]/following-sibling::XCUIElementTypeStaticText[2]", LocatorType.XPath).get(1); }
	public WebElement Text_Driver2RateDriver() {return findElements("//XCUIElementTypeImage[@name=\"profile_placeholder\"]/following-sibling::XCUIElementTypeStaticText[3]", LocatorType.XPath).get(1); }
	public List<WebElement> Button_GenericDriver2star() {return findElements("//XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeButton[contains(@name,\"star icon\")]", LocatorType.XPath); }

	public WebElement Text_EmailSend() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'A detailed receipt will be emailed to you.'", LocatorType.Predicate); }
	public WebElement Text_TabStar() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Tap a star to rate your driver.'", LocatorType.Predicate); }
	public List<WebElement> Button_Driver1Filled() {return findElements("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeButton[@name=\"rating filled star icon\"]", LocatorType.XPath); }
	public List<WebElement> Button_Driver1Empty() {return findElements("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeButton[@name=\"rating unselected star icon\"]", LocatorType.XPath); }


}
