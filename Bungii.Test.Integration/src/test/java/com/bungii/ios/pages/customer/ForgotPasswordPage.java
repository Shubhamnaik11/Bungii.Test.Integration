package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends PageBase {
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Text_Info() { return findElement("//XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_SmsCode() { return findElement("//XCUIElementTypeTextField", PageBase.LocatorType.XPath); }
	public WebElement Text_Password() { return findElement("//XCUIElementTypeSecureTextField", PageBase.LocatorType.XPath); }
	public WebElement Button_Back() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
	public WebElement Text_InputNumber() { return findElement("//XCUIElementTypeTextField", PageBase.LocatorType.XPath); }


	public WebElement Image_PhoneIcon() { return findElement("icon_phone", PageBase.LocatorType.Name); }
	public WebElement Image_Password() { return findElement("textfield_password_1", PageBase.LocatorType.Name); }
	public WebElement Button_Send() { return findElement("SEND", PageBase.LocatorType.Name); }
	public WebElement Button_Continue() { return findElement("CONTINUE", PageBase.LocatorType.Name); }
	public WebElement Button_Resend() { return findElement("RESEND", PageBase.LocatorType.Name); }

/*	*//**
	 * Get info message of forgot password page
	 * @return info message of forgot password page
	 *//*
	public String getInfoMessage(){
		return  getValueAttribute(Text_Info);
	}
	
	public void waitForContinueButtonToAppear(){
		waitForExpectedElement(Button_Continue);
	}
	*//**
	 * Enter phone number
	 * @param input phone number that is to be entered
	 *//*
	public void enterPhoneNumber(String input){
		clearEnterText(Text_InputNumeber, input);
	}
	
	*//**
	 * enter SMS code
	 * @param smsCode  code that is to be entered
	 *//*
	public void enterSMSCode(String smsCode){
		clearEnterText(Text_SmsCode, smsCode);
		hideKeyboard();

	}
	
	
	*//**
	 * Enter password
	 * @param password password that is to be entered
	 *//*
	public void enterNewPassWord(String password){
		clearEnterText(Text_Password, password);
		hideKeyboard();

	}
	*//**
	 * Click on Continue button
	 *//*
	public void clickContinueButton(){
		click(Button_Continue);

	}
	*//**
	 * Click on Send button
	 *//*
	public void clickSendButton(){
		click(Button_Send);
	}
	
	*//**
	 * Click back button
	 *//*
	public void clickButtonButton(){
		click(Button_Back);
	}*/
}
