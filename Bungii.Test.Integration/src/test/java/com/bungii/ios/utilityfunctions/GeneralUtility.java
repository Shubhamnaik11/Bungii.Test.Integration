package com.bungii.ios.utilityfunctions;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.HomePage;
import com.bungii.ios.pages.driver.LoginPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

public class GeneralUtility extends DriverBase {
    static final double MIN_COST = 39;
    HomePage driverHomePage= new HomePage();
    ActionManager action = new ActionManager();
    LoginPage driverLoginPage = new LoginPage();
    /**
     * Calculate estimate cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance
     * @param loadTime
     * @param estTime
     * @param Promo
     * @return
     */
    public double bungiiEstimate(String tripDistance, String loadTime, String estTime, String Promo) {

        double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double loadUnloadTime = Double.parseDouble(loadTime.replace(" mins", ""));
        double tripTime = Double.parseDouble(estTime);

        double estimate = distance + loadUnloadTime + tripTime;
        estimate = estimate > MIN_COST ? estimate : MIN_COST;

        return estimate;
    }

    public boolean verifyPageHeader(String key){
        String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION");

        boolean isCorrectPage=false;
        switch (key.toUpperCase()){
            case "BUNGII.COM":
                // waitForExpectedElement(By.xpath("//XCUIElementTypeApplication[@name='Safari']"));
                isCorrectPage =driverHomePage.isElementEnabled(driverHomePage.findElement("//XCUIElementTypeApplication[@name='Safari']", PageBase.LocatorType.XPath))
                        && action.getValueAttribute(driverHomePage.findElement("//*[@label='Address']", PageBase.LocatorType.XPath)).contains("bungii.com");
                break;

            case "HOME":
                if (currentApplication.equals("DRIVER")) {
                    driverHomePage.visibilityOf(driverHomePage.Text_AvailableTrips());
                    isCorrectPage = action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals("ONLINE") || action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals("OFFLINE");
                    break;
                }else{}
                default:
                   String expectedMessage= getExpectedHeader(key.toUpperCase(),currentApplication);
                    action.textToBePresentInElementName(driverHomePage.Text_NavigationBar(),expectedMessage);
                    isCorrectPage= action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals(expectedMessage);

        }
        return isCorrectPage;
    }
    private String getExpectedHeader(String screen, String currentApplication){
        String expectedMessage="";
        switch (screen.toUpperCase()){
            case "BUNGII DETAILS":
                if (currentApplication.equals("CUSTOMER"))
                    expectedMessage=PropertyUtility.getMessage("customer.navigation.bungiidetails");
                else
                    expectedMessage=PropertyUtility.getMessage("driver.navigation.bungiidetails");
                break;
            case "TRIP DETAILS":
                expectedMessage=PropertyUtility.getMessage("driver.navigation.trip.details");
                break;
            case "HOME":
                    expectedMessage=PropertyUtility.getMessage("customer.navigation.home");
                break;
            case "FAQ":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.faq");
                break;
            case "ACCOUNT":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.account");
                break;
            case "SCHEDULED BUNGIIS":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.scheduled.bungii");
                break;
            case "PAYMENT":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.payment");
                break;
            case "SUPPORT":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.support");
                break;
            case "PROMOS":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.promos");
                break;

            case "ESTIMATE":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.estimate");
                break;
            case "SUCCESS":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.success");
                break;
            case "BUNGII COMPLETED":
                expectedMessage=PropertyUtility.getMessage("driver.navigation.bungii.completed");
                break;
            case "BUNGII COMPLETE":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.bungii.complete");
                break;
            case "PROMOTION":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.promotion");
                break;
            case "LOG IN":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.login");
                break;
            case "INVITE":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.invite");
                break;
            case "SIGN UP":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.signup");
                break;
            case "FORGOT PASSWORD":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.forgot.password");
                break;
            case "DRIVER NOT AVAILABLE":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.driver.not.found");
                break;
            case "BUNGII REQUEST":
                expectedMessage=PropertyUtility.getMessage("driver.navigation.bungii.request");
                break;
            case "BUNGII ACCEPTED":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.bungii.accepted");
                break;
            case "VERIFICATION":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.verification");
                break;
            case "TERMS AND CONDITION":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.terms.condition");
                break;
            case "DRIVER SEARCH":
            case "SEARCHING":
                expectedMessage=PropertyUtility.getMessage("customer.navigation.searching");
                break;
            default:
               // error("Verify Screen " + screen, "UnImplemented Step or in correct screen", "UnImplemented Step", true);
                break;
        }
        return  expectedMessage;
    }

    public void loginToDriverApp(String phone, String password) throws InterruptedException {
        if (action.isElementPresent(driverLoginPage.TextField_PhoneNumber(true))) {
            WebElement element=driverLoginPage.TextField_PhoneNumber();
            action.sendKeys(driverLoginPage.TextField_PhoneNumber(), phone);
            action.sendKeys(driverLoginPage.Textfield_Password(), password);
            action.click(driverLoginPage.Button_Login());
        } else {
            //Not on Login page
        }
    }
}
