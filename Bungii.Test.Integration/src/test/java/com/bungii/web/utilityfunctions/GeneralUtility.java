package com.bungii.web.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_LoginPage;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.driver.Driver_RegistrationPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.bungii.common.manager.CucumberContextManager;

import java.util.HashMap;
import java.util.Map;

public class GeneralUtility {
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    DbUtility dbUtility = new DbUtility();
    ActionManager action = new ActionManager();
    Driver_DashboardPage driver_dashboardPage = new Driver_DashboardPage();
    Admin_LoginPage Page_AdminLogin = new Admin_LoginPage();

    private String GetDriverUrl()
{
    String driverURL = null;
    String environment = PropertyUtility.getProp("environment");
    if (environment.equalsIgnoreCase("DEV"))
        driverURL = PropertyUtility.getDataProperties("dev.driver.url");
    if (environment.equalsIgnoreCase("QA"))
        driverURL = PropertyUtility.getDataProperties("qa.driver.url");
    if (environment.equalsIgnoreCase("STAGE"))
        driverURL = PropertyUtility.getDataProperties("stage.driver.url");
    return driverURL;
}
    private String GetAdminUrl()
    {
        String adminURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("DEV"))
            adminURL = PropertyUtility.getDataProperties("dev.admin.url");
        if (environment.equalsIgnoreCase("QA"))
            adminURL = PropertyUtility.getDataProperties("qa.admin.url");
        if (environment.equalsIgnoreCase("STAGE"))
            adminURL = PropertyUtility.getDataProperties("stage.admin.url");
        return adminURL;
    }
    public void DriverLogin(String Phone, String Password) {
        String driverURL = GetDriverUrl();

        action.navigateTo(driverURL);
        action.click(Page_Driver_Login.Tab_LogIn());
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), Phone);
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), Password);
        action.click(Page_Driver_Login.Button_DriverLogin());
    }
    public void NavigateToDriverLogin() {
        String driverURL = GetDriverUrl();
        action.deleteAllCookies();
        action.navigateTo(driverURL);
    }

    public void AdminLogin() {
        String adminURL = GetAdminUrl();

        action.navigateTo(adminURL);
        action.sendKeys(Page_AdminLogin.TextBox_Phone(), PropertyUtility.getDataProperties("admin.user"));
        action.sendKeys(Page_AdminLogin.TextBox_Password(), PropertyUtility.getDataProperties("admin.password"));
        action.click(Page_AdminLogin.Button_AdminLogin());
    }

    public void DriverLogout() {
        action.click(driver_dashboardPage.Link_Logout());
    }


    public String generateMobileNumber() {

        String phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");
        while (!DbUtility.isPhoneNumberUnique(phoneNumber)) {
            phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");

        }
        return phoneNumber;
    }


    public void addImageInDropZone(WebElement dropZoneWebelement, String filePath) {

        ((JavascriptExecutor) SetupManager.getDriver()).executeScript(" arguments[0].style.visibility = 'visible';", dropZoneWebelement);
        action.sendKeys(dropZoneWebelement, filePath);

    }

    public void addImageInDropZone(WebElement dropZoneWebelement, String[] filePath) {
        String inputFilesString = "";
        for (String file : filePath) {
            inputFilesString = inputFilesString + " \n " + file;
        }
        ((JavascriptExecutor) SetupManager.getDriver()).executeScript(" arguments[0].style.visibility = 'visible';", dropZoneWebelement);
        action.sendKeys(dropZoneWebelement, inputFilesString.trim());

    }
    public String GetUniqueLastName()
    {
        String Lastname= RandomGeneratorUtility.getData("{RANDOM_STRING}", 4);
        return Lastname;

    }
}
