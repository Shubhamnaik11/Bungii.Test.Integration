package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.bungiiDriver.AvailableTripsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import cucumber.api.java.en.And;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AvailableTripsSteps   extends DriverBase {
    AvailableTripsPage availableTrips = new AvailableTripsPage();
    ActionManager action = new ActionManager();
    @And("I Select Trip from driver available trip")
    public void iSelectTripFromDriverAvailableTrip() {
        String customerName=(String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String numberOfDriver=(String)cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
     //   customerName="Vishal Bagi";numberOfDriver="SOLO";
        boolean isSelected =selectBungiiFromList(numberOfDriver,customerName.substring(0, customerName.indexOf(" ")+2));
        testStepVerify.isTrue(isSelected,"I should able to select trip from available trip","I was not able find available trip for customer "+customerName +" Bungii type "+numberOfDriver);
    }
    public boolean selectBungiiFromList(String bungiiType, String customerName){
        boolean isSelected=false;
      //  action.scrollToBottom();
        //TODO: check diff between solo and duo on screen
        String expectedInstance="2";
        if(bungiiType.toUpperCase().equals("SOLO"))
        {
            expectedInstance="2";
        }else {
            expectedInstance="4";

        }

        //List_AvailableBungiis
        List<WebElement> elements = availableTrips.List_AvailableBungiis();
        for(WebElement element : elements){
            MobileElement image = element.findElement(By.id("com.bungii.driver:id/row_available_pickup_imageview_type"));
            WebElement actualCustomer = element.findElement(By.id("com.bungii.driver:id/row_available_pickup_drivername"));
            String actualCustomerName=actualCustomer.getText();
            System.out.println(SetupManager.getDriver().getPageSource());
      //      String  instance =image.getAttribute("instance");

            if(actualCustomerName.equals(customerName)){
                element.findElement(By.id("com.bungii.driver:id/row_available_pickup_imageview_arrow")).click();
                isSelected=true;
                break;
            }
        }
        return isSelected;
    }
}

